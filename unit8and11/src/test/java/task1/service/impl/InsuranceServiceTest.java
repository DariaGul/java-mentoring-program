package task1.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class InsuranceServiceTest {

    private final Integer REGION = 777;
    private final String LICENCE_PLATE_EXISTING = "х000кр";
    private final LocalDateTime START_DATE = LocalDateTime.of(2016, 1, 16, 20, 38);
    private final LocalDateTime END_DATE = LocalDateTime.of(2022, 7, 19, 20, 38);
    private final String[] IGNORING_FIELDS = {"car", "listClients", "id"};

    @Autowired
    public InsuranceService insuranceService;
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
    public void whenCreateNewInsuranceThenReturnEntity() {
        InsuranceRequest request = new InsuranceRequest()
            .setStartDate(LocalDateTime.of(2010, 8, 22, 20, 38))
            .setEndDate(LocalDateTime.of(2015, 11, 13, 20, 38))
            .setNumber("22222222222")
            .setListClients(Collections.singletonList(client.getId()))
            .setCarId(car.getId());
        InsuranceEntity updatedEntity = insuranceService.create(request);
        insuranceRepository
            .findById(updatedEntity.getId())
            .orElseThrow(AssertionError::new);
        assertThat(updatedEntity).isEqualToIgnoringGivenFields(request, IGNORING_FIELDS);
        assertThat(updatedEntity.getListClients().get(0)).isEqualToIgnoringGivenFields(
            client,
            "cars",
            "listInsurance");
        assertThat(updatedEntity.getCar()).isEqualToIgnoringGivenFields(car, "carModel", "client");
    }

    @Test
    public void whenCreateAlreadyExistInsuranceThenThrowException() {
        InsuranceRequest request = new InsuranceRequest()
            .setNumber("1111111111111111")
            .setListClients(Collections.singletonList(client.getId()))
            .setCarId(car.getId());
        assertThrows(Exception.class, () -> insuranceService.create(request));
    }

    @Test
    public void whenUpdateExistingInsuranceThenReturnEntity() {
        InsuranceRequest request = new InsuranceRequest()
            .setStartDate(LocalDateTime.of(2010, 8, 22, 20, 38))
            .setEndDate(LocalDateTime.of(2015, 11, 13, 20, 38))
            .setNumber("22222222222");
        InsuranceEntity updatedEntity = insuranceService.update(request, insurance.getId());
        insuranceRepository.findById(insurance.getId()).orElseThrow(AssertionError::new);
        assertThat(updatedEntity).isEqualToIgnoringGivenFields(request, IGNORING_FIELDS);
        assertThat(updatedEntity.getId()).isEqualTo(insurance.getId());
    }

    @Test
    public void whenUpdateNonExistingInsuranceThenThrowException() {
        assertThrows(Exception.class, () -> insuranceService.update(new InsuranceRequest(), 1L));
    }

    @Test
    public void whenDeleteExistingInsuranceThenReturnOk() {
        insuranceService.delete(insurance.getId());
        insuranceRepository.findById(insurance.getId()).ifPresent(AssertionError::new);
    }

    @Test
    public void whenDeleteNotExistingInsuranceThenReturnOk() {
        insuranceService.delete(1L);
        insuranceRepository.findById(1L).ifPresent(AssertionError::new);
    }

    @Test
    public void whenGetExistingInsuranceThenReturnOk() {
        InsuranceResponse insuranceEntity = insuranceService.get(insurance.getId());
        assertThat(insuranceEntity)
            .isEqualToIgnoringGivenFields(insurance, IGNORING_FIELDS[0], IGNORING_FIELDS[1]);
        assertThat(insuranceEntity.getCar())
            .isEqualToIgnoringGivenFields(car, "client", "carModel");
        assertThat(insuranceEntity.getListClients().get(0))
            .isEqualToIgnoringGivenFields(client, "cars", "listInsurance");
    }

    @Test
    public void whenGetNonExistingInsuranceThenThrowException() {
        assertThrows(Exception.class, () -> insuranceService.get(1L));
    }
}
