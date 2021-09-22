package task1.controller;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static task1.ObjectMapperUtils.objectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import task1.dto.request.CreateClientRequest;
import task1.dto.request.UpdateClientRequest;
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
import task1.service.ClientService;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    private final String URL = "/unit/api/client";
    private final String URL_WITH_ID = URL + "/{id}";
    private final Integer REGION = 777;
    private final String LICENCE_PLATE_EXISTING = "х000кр";
    private final LocalDateTime START_DATE = LocalDateTime.of(2016, 1, 16, 20, 38);
    private final LocalDateTime END_DATE = LocalDateTime.of(2022, 7, 19, 20, 38);
    public static final DateTimeFormatter TIME_FORMATTER = ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Autowired
    public ClientController controller;

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
        BrandEntity brand = brandRepository.save(
            new BrandEntity()
                .setBrand("Volkswagen")
        );
        carModel = carModelRepository.save(
            new CarModelEntity()
                .setModel("Golf").
                setBrand(brand)
        );

        client = clientRepository.save(
            new ClientEntity()
                .setCity("Эшвилд")
                .setFirstName("Генри")
                .setLastName("Тауншенд")
        );

        car = carRepository.save(
            new CarEntity()
                .setRegion(REGION)
                .setLicencePlate(LICENCE_PLATE_EXISTING)
                .setCarModel(carModel)
                .setClient(client)
        );

        insurance = insuranceRepository.save(
            new InsuranceEntity()
                .setNumber("1111111111111111")
                .setStartDate(START_DATE)
                .setEndDate(END_DATE)
                .setCar(car)
                .setListClients(Collections.singletonList(client))
        );
    }

    @AfterEach
    public void tearDown() {
        carRepository.deleteAll();
        clientRepository.deleteAll();
        carModelRepository.deleteAll();
        insuranceRepository.deleteAll();
    }

    @Test
    public void whenCreateAlreadyExistClientThenReturnEntity() throws Exception {
        CreateClientRequest clientRequest = new CreateClientRequest()
            .setCity("Эшвилд")
            .setFirstName("Генри")
            .setLastName("Тауншенд");

        mockMvc.perform(post(URL)
                            .content(objectMapper().writeValueAsBytes(clientRequest))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    public void whenUpdateExistClientThenReturnEntity() throws Exception {
        UpdateClientRequest updateClientRequest = new UpdateClientRequest()
            .setCity("Сайлент-Хилл")
            .setFirstName("Уолтер")
            .setLastName("Салливан");

        mockMvc.perform(put(URL_WITH_ID, client.getId())
                            .content(objectMapper().writeValueAsBytes(updateClientRequest))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    public void whenUpdateNotExistingClientThenReturnEntity() throws Exception {
        mockMvc.perform(put(URL_WITH_ID, UUID.randomUUID())
                            .content(objectMapper().writeValueAsBytes(new UpdateClientRequest()))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void whenDeleteExistingClientThenReturnOk() throws Exception {
        mockMvc.perform(delete(URL_WITH_ID, client.getId())
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
        clientRepository
            .findById(client.getId())
            .ifPresent(AssertionError::new);
    }

    @Test
    public void whenDeleteNotExistingClientThenReturnOk() throws Exception {
        UUID nonExistClient = UUID.randomUUID();
        mockMvc.perform(delete(URL_WITH_ID, nonExistClient)
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        clientRepository
            .findById(nonExistClient)
            .ifPresent(AssertionError::new);
    }

    @Test
    public void whenGetExistingClientThenReturnOk() throws Exception {
        mockMvc.perform(get(URL_WITH_ID, client.getId())
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(client.getId().toString()))
            .andExpect(jsonPath("$.firstName").value(client.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(client.getLastName()))
            .andExpect(jsonPath("$.middleName").value(client.getMiddleName()))
            .andExpect(jsonPath("$.city").value(client.getCity()))
            .andExpect(jsonPath("$.cars[0].id").value(car.getId()))
            .andExpect(jsonPath("$.cars[0].licencePlate").value(LICENCE_PLATE_EXISTING))
            .andExpect(jsonPath("$.cars[0].region").value(REGION))
            .andExpect(jsonPath("$.listInsurance[0].id").value(insurance.getId()))
            .andExpect(jsonPath("$.listInsurance[0].startDate").value(TIME_FORMATTER.format(START_DATE)))
            .andExpect(jsonPath("$.listInsurance[0].endDate").value(TIME_FORMATTER.format(END_DATE)));
    }

    @Test
    public void whenGetNonExistingClientThenThrowException() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(URL_WITH_ID, UUID.randomUUID()));
        resultActions.andExpect(status().isBadRequest());
    }
}
