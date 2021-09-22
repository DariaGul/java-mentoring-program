package task1.controller;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static task1.ObjectMapperUtils.objectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import task1.dto.request.InsuranceRequest;
import task1.dto.response.InsuranceResponse;
import task1.model.BrandEntity;
import task1.model.CarEntity;
import task1.model.CarModelEntity;
import task1.model.ClientEntity;
import task1.model.InsuranceEntity;
import task1.repository.BrandRepository;
import task1.repository.CarModelRepository;
import task1.repository.CarRepository;
import task1.repository.ClientRepository;
import task1.repository.InsuranceRepository;
import task1.service.InsuranceService;

@SpringBootTest
@AutoConfigureMockMvc
public class InsuranceControllerTest {

    private final String URL = "/unit/api/insurance";
    private final String URL_WITH_ID = URL + "/{id}";
    private final Integer REGION = 777;
    private final String LICENCE_PLATE_EXISTING = "х000кр";
    private final LocalDateTime START_DATE = LocalDateTime.of(2016, 1, 16, 20, 38);
    private final LocalDateTime END_DATE = LocalDateTime.of(2022, 7, 19, 20, 38);
    public static final DateTimeFormatter TIME_FORMATTER = ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    public InsuranceController controller;
    @Autowired
    public MockMvc mockMvc;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarModelRepository carModelRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private InsuranceRepository insuranceRepository;

    private CarModelEntity carModel;
    private ClientEntity client;
    private CarEntity car;
    private InsuranceEntity insurance;

    @BeforeEach
    public void setUp() {
        BrandEntity brand = brandRepository.save(new BrandEntity().setBrand("Volkswagen"));
        carModel = carModelRepository.save(new CarModelEntity().setModel("Golf").setBrand(brand));

        List<CarEntity> cars = new ArrayList<>();
        cars.add(car);
        client = new ClientEntity()
            .setCity("Эшвилд")
            .setFirstName("Генри")
            .setLastName("Тауншенд")
            .setCars(cars);
        clientRepository.save(client);
        List<ClientEntity> clients = new ArrayList<>();
        clients.add(client);

        car = carRepository.save(new CarEntity()
                                     .setRegion(REGION)
                                     .setLicencePlate(LICENCE_PLATE_EXISTING)
                                     .setCarModel(carModel)
                                     .setClient(client)
        );
        insurance = new InsuranceEntity()
            .setNumber("1111111111111111")
            .setStartDate(START_DATE)
            .setEndDate(END_DATE)
            .setCar(car)
            .setListClients(clients);

        insurance = insuranceRepository.save(insurance);
    }

    @AfterEach
    public void tearDown() {
        carRepository.deleteAll();
        clientRepository.deleteAll();
        carModelRepository.deleteAll();
        insuranceRepository.deleteAll();
    }

    @Test
    public void whenCreateNewInsuranceThenReturnEntity() throws Exception {
        InsuranceRequest request = new InsuranceRequest()
            .setStartDate(LocalDateTime.of(2010, 8, 22, 20, 38))
            .setEndDate(LocalDateTime.of(2015, 11, 13, 20, 38))
            .setNumber("22222222222")
            .setListClients(Collections.singletonList(client.getId()))
            .setCarId(car.getId());

        mockMvc.perform(post(URL)
                            .content(objectMapper().writeValueAsBytes(request))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    public void whenCreateAlreadyExistInsuranceThenThrowException() throws Exception {
        InsuranceRequest request = new InsuranceRequest()
            .setNumber("1111111111111111")
            .setListClients(Collections.singletonList(client.getId()))
            .setCarId(car.getId());

        mockMvc.perform(post(URL)
                            .content(objectMapper().writeValueAsBytes(request))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void whenUpdateExistingInsuranceThenReturnEntity() throws Exception  {
        InsuranceRequest request = new InsuranceRequest()
            .setStartDate(LocalDateTime.of(2010, 8, 22, 20, 38))
            .setEndDate(LocalDateTime.of(2015, 11, 13, 20, 38))
            .setNumber("22222222222");
        mockMvc.perform(put(URL_WITH_ID, insurance.getId())
                            .content(objectMapper().writeValueAsBytes(request))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    public void whenUpdateNonExistingInsuranceThenThrowException() throws Exception {
        mockMvc.perform(put(URL_WITH_ID, 1L)
                            .content(objectMapper().writeValueAsBytes(new InsuranceRequest()))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void whenDeleteExistingInsuranceThenReturnOk() throws Exception{
        mockMvc.perform(delete(URL_WITH_ID, insurance.getId())
                            .content(objectMapper().writeValueAsBytes(new InsuranceRequest()))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
        insuranceRepository.findById(insurance.getId()).ifPresent(AssertionError::new);
    }

    @Test
    public void whenDeleteNotExistingInsuranceThenReturnOk() throws Exception{
        mockMvc.perform(delete(URL_WITH_ID, 1L)
                            .content(objectMapper().writeValueAsBytes(new InsuranceRequest()))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
        insuranceRepository.findById(1L).ifPresent(AssertionError::new);
    }

    @Test
    public void whenGetExistingInsuranceThenReturnOk() throws Exception{
        mockMvc.perform(get(URL_WITH_ID, insurance.getId())
                            .content(objectMapper().writeValueAsBytes(new InsuranceRequest()))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(insurance.getId()))
            .andExpect(jsonPath("$.startDate").value(TIME_FORMATTER.format(START_DATE)))
            .andExpect(jsonPath("$.endDate").value(TIME_FORMATTER.format(END_DATE)))
            .andExpect(jsonPath("$.number").value(insurance.getNumber()))
            .andExpect(jsonPath("$.listClients[0].id").value(client.getId().toString()))
            .andExpect(jsonPath("$.listClients[0].firstName").value(client.getFirstName()))
            .andExpect(jsonPath("$.listClients[0].lastName").value(client.getLastName()))
            .andExpect(jsonPath("$.listClients[0].middleName").value(client.getMiddleName()))
            .andExpect(jsonPath("$.listClients[0].city").value(client.getCity()))
            .andExpect(jsonPath("$.car.id").value(car.getId()))
            .andExpect(jsonPath("$.car.licencePlate").value(LICENCE_PLATE_EXISTING))
            .andExpect(jsonPath("$.car.region").value(REGION));


    }

    @Test
    public void whenGetNonExistingInsuranceThenThrowException() throws Exception{
        mockMvc.perform(get(URL_WITH_ID, 1L)
                            .content(objectMapper().writeValueAsBytes(new InsuranceRequest()))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}
