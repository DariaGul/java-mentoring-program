package task1.service;

import java.util.Optional;
import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task1.dto.CarModelRequest;
import task1.dto.CarRequest;
import task1.model.CarEntity;
import task1.model.CarModelEntity;
import task1.repository.CarModelRepository;
import task1.repository.CarRepository;
import task1.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class CarModelService {

    private final CarModelRepository carModelRepository;

    @Transactional
    public CarModelEntity create(CarModelRequest carModel) {
        String model = carModel.getModel();
        String brand = carModel.getBrand();
        findByModelAndBrand(model, brand).ifPresent(
            c -> { throw new BadRequestException("Car already exist");});
        CarModelEntity carModelEntity =
            new CarModelEntity()
                .setModel(model)
                .setBrand(brand);

        return carModelRepository.save(carModelEntity);
    }

    @Transactional
    public CarModelEntity update(CarModelRequest carModel) {
        String model = carModel.getModel();
        String brand = carModel.getBrand();
        CarModelEntity carModelEntityDb = findByModelAndBrand(model, brand).orElseThrow(
            () -> new BadRequestException("Car model does not exist"));

        if (model != null) {
            carModelEntityDb.setModel(model);
        }
        if (brand != null) {
            carModelEntityDb.setBrand(brand);
        }

        return carModelRepository.save(carModelEntityDb);
    }

    public Optional<CarModelEntity> findByModelAndBrand(String model, String brand) {
        return carModelRepository.findByModelAndBrand(model, brand);
    }

    @Transactional
    public void delete(CarModelRequest carModel) {
        findByModelAndBrand(carModel.getModel(), carModel.getBrand())
            .ifPresent(carModelRepository::delete);
    }
}
