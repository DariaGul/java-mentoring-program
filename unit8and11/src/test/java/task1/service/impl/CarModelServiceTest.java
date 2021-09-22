package task1.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import task1.dto.request.CarModelRequest;
import task1.model.BrandEntity;
import task1.model.CarModelEntity;
import task1.repository.BrandRepository;
import task1.repository.CarModelRepository;

@SpringBootTest
public class CarModelServiceTest {

    private final String MODEL = "Golf";
    private final String LIST_CAR_MODEL = "listCarModel";

    @Autowired
    public CarModelServiceImpl carModelService;
    @Autowired
    private CarModelRepository carModelRepository;
    @Autowired
    private BrandRepository brandRepository;

    private BrandEntity brand;
    private CarModelEntity oldCarModel;
    private CarModelRequest request;

    @BeforeEach
    public void setUp() {
        brand = brandRepository.save(
            new BrandEntity()
                .setBrand("Volkswagen")
        );
        request = new CarModelRequest()
            .setModel(MODEL)
            .setBrandId(brand.getId());
        oldCarModel = carModelRepository.save(
            new CarModelEntity()
                .setBrand(brand)
                .setModel("Polo")
        );
    }

    @AfterEach
    public void tearDown() {
        brandRepository.deleteAll();
        carModelRepository.deleteAll();
    }

    @Test
    public void whenSendNewCarModelThenReturnEntity() {
        CarModelEntity createdEntity = carModelService.create(request);
        carModelRepository
            .findById(createdEntity.getId())
            .orElseThrow(AssertionError::new);
        assertThat(createdEntity.getModel()).isEqualTo(MODEL);
        assertThat(createdEntity.getBrand()).isEqualToIgnoringGivenFields(brand, LIST_CAR_MODEL);
    }

    @Test
    public void whenSendOnlyModelThenThrowException() {
        assertThrows(
            Exception.class,
            () -> carModelService.create(
                new CarModelRequest()
                    .setModel(MODEL))
        );
    }

    @Test
    public void whenCreateAlreadyExistCarModelThenReturnThrowException() {
        carModelRepository.save(
            new CarModelEntity()
                .setModel(MODEL)
                .setBrand(brand)
        );
        assertThrows(Exception.class, () -> carModelService.create(request));
    }

    @Test
    public void whenUpdateExistingCarModelWithAlreadyExistCarModelThenThrowException() {
        carModelRepository.save(
            new CarModelEntity()
                .setBrand(brand)
                .setModel("Touareg")
        );
        CarModelRequest request =
            new CarModelRequest()
                .setModel("Touareg")
                .setBrandId(brand.getId());

        assertThrows(
            Exception.class,
            () -> carModelService.update(request, oldCarModel.getId())
        );
    }

    @Test
    public void whenUpdateModelOfExistingCarThenReturnEntity() {
        CarModelEntity updatedEntity = carModelService.update(
            new CarModelRequest().setModel("Touareg"),
            oldCarModel.getId()
        );
        assertThat(updatedEntity.getModel()).isEqualTo("Touareg");
    }

    @Test
    public void whenUpdateExistingCarModelWithExistingThenReturnEntity() {
        CarModelEntity updatedEntity = carModelService.update(request, oldCarModel.getId());
        carModelRepository
            .findById(updatedEntity.getId())
            .orElseThrow(AssertionError::new);
        assertThat(updatedEntity.getModel()).isEqualTo(MODEL);
        assertThat(updatedEntity.getBrand()).isEqualToIgnoringGivenFields(brand, LIST_CAR_MODEL);
    }

    @Test
    public void whenUpdateDoesNotExistCarModelThenThrowException() {
        assertThrows(
            Exception.class,
            () -> carModelService.update(
                new CarModelRequest().setModel(MODEL),
                1L
            )
        );
    }

    @Test
    public void whenDeleteExistingEntityThenReturnOk() {
        carModelService.delete(oldCarModel.getId());
        carModelRepository
            .findById(oldCarModel.getId())
            .ifPresent(AssertionError::new);
    }

    @Test
    public void whenDeleteDoesNotExistingEntityThenReturnOk() {
        carModelService.delete(1L);
        carModelRepository
            .findById(1L)
            .ifPresent(AssertionError::new);
    }

    @Test
    public void whenGetExistingEntityThenReturnEntity() {
        CarModelEntity entity = carModelService.get(oldCarModel.getId());
        assertThat(entity.getModel()).isEqualTo(oldCarModel.getModel());
        assertThat(entity.getBrand()).isEqualToIgnoringGivenFields(oldCarModel.getBrand(), LIST_CAR_MODEL);
    }

    @Test
    public void whenGetNonExistingEntityThenThrowException() {
        assertThrows(Exception.class, () -> carModelService.get(1L));
    }
}
