package task1.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import task1.dto.CarRequest;
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
public class CarServiceTest {

    private final Integer REGION = 777;
    private final String LICENCE_PLATE = "х767кр";
    private final String LICENCE_PLATE_EXISTING = "х000кр";

    @Autowired
    public CarServiceImpl carService;
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
    public void whenSendNewCarThenThrowException() {
        CarRequest carRequest = new CarRequest()
            .setCarModelId(carModel.getId())
            .setRegion(REGION)
            .setLicencePlate(LICENCE_PLATE_EXISTING)
            .setClientId(client.getId())
            .setCarModelId(carModel.getId());

        assertThrows(Exception.class, () -> carService.create(carRequest));
    }

    @Test
    public void whenSendNewCarThenReturnEntity() {
        CarRequest createRequest = new CarRequest().setCarModelId(carModel.getId())
            .setRegion(REGION)
            .setClientId(client.getId())
            .setLicencePlate(LICENCE_PLATE);
        CarEntity entity = carService.create(createRequest);

        carRepository.findById(entity.getId()).orElseThrow(AssertionError::new);
        assertThat(entity.getClient())
            .isEqualToIgnoringGivenFields(client, "cars", "listInsurance");
        assertThat(entity.getRegion()).isEqualTo(REGION);
        assertThat(entity.getLicencePlate()).isEqualTo(LICENCE_PLATE);
        assertThat(entity.getCarModel().getModel()).isEqualTo(carModel.getModel());
    }

    @Test
    public void whenUpdateThenReturnEntity() {
        CarRequest carRequest = new CarRequest().setCarModelId(carModel.getId())
            .setRegion(REGION)
            .setLicencePlate(LICENCE_PLATE);
        CarEntity entity = carService.update(carRequest, car.getId());
        carRepository.findById(entity.getId()).orElseThrow(AssertionError::new);
        assertThat(entity.getRegion()).isEqualTo(REGION);
        assertThat(entity.getLicencePlate()).isEqualTo(LICENCE_PLATE);
        assertThat(entity.getCarModel().getModel()).isEqualTo(carModel.getModel());
    }

    @Test
    public void whenUpdateThenReturnEntit1y() {
        ClientEntity newClient = clientRepository.save(
            new ClientEntity()
                .setCity("Эшвилд")
                .setFirstName("Айлин")
                .setLastName("Гэлвин")
        );
        CarRequest carRequest = new CarRequest()
            .setClientId(newClient.getId());

        CarEntity entity = carService.update(carRequest, car.getId());

        carRepository.findById(entity.getId()).orElseThrow(AssertionError::new);
        assertThat(entity.getClient()).isEqualToIgnoringGivenFields(newClient,
                                                                    "cars",
                                                                    "listInsurance");
    }

    @Test
    public void whenUpdateDoesNotExistingCarThenReturnEntity() {
        CarEntity entity = carService.update(carRequest, car.getId());

        carRepository.findById(entity.getId()).orElseThrow(AssertionError::new);
        assertThat(entity.getRegion()).isEqualTo(REGION);
        assertThat(entity.getLicencePlate()).isEqualTo(LICENCE_PLATE);
        assertThat(entity.getCarModel().getModel()).isEqualTo("WRX");
    }

    @Test
    public void whenDeleteExistingCarThenReturnOk() {
        carService.delete(carRequest);
        carRepository
            .findById(car.getId())
            .ifPresent(AssertionError::new);
    }

    @Test
    public void whenDeleteDoesNotExistingCarThenReturnOk() {
        CarRequest carRequest = new CarRequest()
            .setCarModelId(carModel.getId())
            .setRegion(99)
            .setLicencePlate(LICENCE_PLATE);
        carService.delete(carRequest);
        carRepository
            .findById(car.getId())
            .ifPresent(AssertionError::new);
    }

    @Test
    public void whenGetExistingEntityThenReturnEntity() {
        CarEntity entity = carService.get(car.getId());
        carRepository
            .findById(car.getId())
            .ifPresent(AssertionError::new);
        assertThat(entity).isEqualToIgnoringGivenFields(car, "carModel", "client");

    }

    @Test
    public void whenGetDoesNotExistingEntityThenThrowException() {
        assertThrows(Exception.class, () -> carService.get(1L));
    }
}
