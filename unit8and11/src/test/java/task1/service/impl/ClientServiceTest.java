package task1.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import task1.dto.request.UpdateClientRequest;
import task1.dto.request.CreateClientRequest;
import task1.model.BrandEntity;
import task1.model.CarEntity;
import task1.model.CarModelEntity;
import task1.model.ClientEntity;
import task1.repository.BrandRepository;
import task1.repository.CarModelRepository;
import task1.repository.CarRepository;
import task1.repository.ClientRepository;
import task1.repository.InsuranceRepository;
import task1.service.ClientService;

@SpringBootTest
public class ClientServiceTest {

    private final Integer REGION = 777;
    private final String LICENCE_PLATE_EXISTING = "х000кр";
    private final String[] IGNORING_FIELDS = {"cars", "listInsurance", "id"};

    @Autowired
    public ClientService clientService;
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
                .setCars(Collections.singletonList(car))
        );

        car = carRepository.save(
            new CarEntity()
                .setRegion(REGION)
                .setLicencePlate(LICENCE_PLATE_EXISTING)
                .setCarModel(carModel)
                .setClient(client)
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
    public void whenCreateAlreadyExistClientThenReturnEntity() {
        CreateClientRequest clientRequest = new CreateClientRequest()
            .setCity("Эшвилд")
            .setFirstName("Генри")
            .setLastName("Тауншенд");

        ClientEntity newClient = clientService.create(clientRequest);
        assertThat(newClient).isEqualToIgnoringGivenFields(client, IGNORING_FIELDS);
        assertThat(newClient.getId()).isNotEqualTo(client.getId());
    }

    @Test
    public void whenUpdateExistClientThenReturnEntity() {
        UpdateClientRequest updateClientRequest = new UpdateClientRequest()
            .setCity("Сайлент-Хилл")
            .setFirstName("Уолтер")
            .setLastName("Салливан");

        ClientEntity newClient = clientService.update(updateClientRequest, client.getId());

        assertThat(newClient).isEqualToIgnoringGivenFields(updateClientRequest, IGNORING_FIELDS);
        assertThat(newClient.getId()).isEqualTo(client.getId());
    }

    @Test
    public void whenUpdateNotExistingClientThenReturnEntity() {
        assertThrows(Exception.class,
                     () -> clientService.update(new UpdateClientRequest(), UUID.randomUUID())
        );
    }

    @Test
    public void whenDeleteExistingClientThenReturnOk() {
        clientService.delete(client.getId());
        clientRepository
            .findById(client.getId())
            .ifPresent(AssertionError::new);
    }

    @Test
    public void whenDeleteNotExistingClientThenReturnOk() {
        UUID nonExistClient = UUID.randomUUID();
        clientService.delete(nonExistClient);
        clientRepository
            .findById(nonExistClient)
            .ifPresent(AssertionError::new);
    }

    @Test
    public void whenGetExistingClientThenReturnOk() {
        ClientEntity clientEntity = clientService.get(client.getId());
        assertThat(clientEntity).isEqualToIgnoringGivenFields(client, "cars", "listInsurance");
        assertThat(clientEntity.getCars().get(0)).isEqualToIgnoringGivenFields(car,
                                                                               "client",
                                                                               "carModel");
    }

    @Test
    public void whenGetNonExistingClientThenThrowException() {
        assertThrows(Exception.class, () -> clientService.get(UUID.randomUUID()));
    }

}
