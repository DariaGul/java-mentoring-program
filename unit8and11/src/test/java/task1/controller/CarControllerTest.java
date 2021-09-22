package task1.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static task1.ObjectMapperUtils.objectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import task1.dto.request.CarRequest;
import task1.model.BrandEntity;
import task1.model.CarEntity;
import task1.model.CarModelEntity;
import task1.model.ClientEntity;
import task1.repository.BrandRepository;
import task1.repository.CarModelRepository;
import task1.repository.CarRepository;
import task1.repository.ClientRepository;
import task1.repository.InsuranceRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    private final String URL = "/unit/api/car";
    private final String URL_WITH_ID = URL + "/{id}";
    private final Integer REGION = 777;
    private final String LICENCE_PLATE = "х767кр";
    private final String LICENCE_PLATE_EXISTING = "х000кр";

    @Autowired
    public CarController controller;
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
    private CarRequest carRequest;

    @BeforeEach
    public void setUp() {
        BrandEntity brand = brandRepository.save(new BrandEntity().setBrand("Subaru"));
        carModel = carModelRepository.save(
            new CarModelEntity()
                .setModel("WRX")
                .setBrand(brand)
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
                .setClient(client)
                .setCarModel(carModel)
        );

        carRequest = new CarRequest().
            setCarModelId(carModel.getId())
            .setRegion(REGION)
            .setLicencePlate(LICENCE_PLATE);
    }

    @AfterEach
    public void tearDown() {
        carRepository.deleteAll();
        clientRepository.deleteAll();
        carModelRepository.deleteAll();
        insuranceRepository.deleteAll();
    }

    @Test
    public void whenSendNewCarThenThrowException() throws Exception {
        CarRequest carRequest = new CarRequest()
            .setCarModelId(carModel.getId())
            .setRegion(REGION)
            .setLicencePlate(LICENCE_PLATE_EXISTING)
            .setClientId(client.getId())
            .setCarModelId(carModel.getId());

        mockMvc.perform(post(URL)
                            .content(objectMapper().writeValueAsBytes(carRequest))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void whenSendNewCarThenReturnEntity() throws Exception {

        CarRequest createRequest = new CarRequest().setCarModelId(carModel.getId())
            .setRegion(REGION)
            .setClientId(client.getId())
            .setLicencePlate(LICENCE_PLATE);

        mockMvc.perform(post(URL)
                            .content(objectMapper().writeValueAsBytes(createRequest))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    public void whenUpdateThenReturnEntity() throws Exception {

        CarRequest carRequest = new CarRequest().setCarModelId(carModel.getId())
            .setRegion(REGION)
            .setLicencePlate(LICENCE_PLATE);
        mockMvc.perform(put(URL_WITH_ID, car.getId())
                            .content(objectMapper().writeValueAsBytes(carRequest))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    public void whenUpdateDoesNotExistingCarThenReturnEntity() throws Exception {

        mockMvc.perform(put(URL_WITH_ID, car.getId())
                            .content(objectMapper().writeValueAsBytes(carRequest))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    public void whenDeleteExistingCarThenReturnOk() throws Exception {
        mockMvc.perform(delete(URL_WITH_ID, car.getId()))
            .andExpect(status().isNoContent());
        carRepository
            .findById(car.getId())
            .ifPresent(AssertionError::new);
    }

    @Test
    public void whenDeleteDoesNotExistingCarThenReturnOk() throws Exception {

        mockMvc.perform(delete(URL_WITH_ID, 1L))
            .andExpect(status().isNoContent());
        carRepository
            .findById(car.getId())
            .ifPresent(AssertionError::new);
    }

    @Test
    public void whenGetExistingEntityThenReturnEntity() throws Exception {
        mockMvc.perform(get(URL_WITH_ID, car.getId())
                            .content(objectMapper().writeValueAsBytes(carRequest))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(car.getId()))
            .andExpect(jsonPath("$.licencePlate").value(LICENCE_PLATE_EXISTING))
            .andExpect(jsonPath("$.region").value(REGION))
            .andExpect(jsonPath("$.client.id").value(client.getId().toString()))
            .andExpect(jsonPath("$.client.firstName").value(client.getFirstName()))
            .andExpect(jsonPath("$.client.lastName").value(client.getLastName()))
            .andExpect(jsonPath("$.client.middleName").value(client.getMiddleName()))
            .andExpect(jsonPath("$.client.city").value(client.getCity()))
            .andExpect(jsonPath("$.carModel.id").value(carModel.getId()))
            .andExpect(jsonPath("$.carModel.model").value(carModel.getModel()));
    }

    @Test
    public void whenGetDoesNotExistingEntityThenThrowException() throws Exception {
        mockMvc.perform(get(URL_WITH_ID, 1L)
                            .content(objectMapper().writeValueAsBytes(carRequest))
                            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}
