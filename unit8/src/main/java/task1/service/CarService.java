package task1.service;

import java.util.Optional;
import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task1.dto.CarRequest;
import task1.model.CarEntity;
import task1.model.CarModelEntity;
import task1.model.ClientEntity;
import task1.repository.CarModelRepository;
import task1.repository.CarRepository;
import task1.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarModelRepository carModelRepository;
    private final ClientRepository clientRepository;

    public Optional<CarEntity> findByCarModelAndRegion(String region, CarModelEntity carModel) {
        return carRepository.findCarEntityByCarModelAndRegion(carModel.getModel(), carModel.getBrand(), region);
    }

    public Optional<CarEntity> findByLicencePlateAndRegion(String licencePlate, Integer region) {
        return carRepository.findCarEntityByLicencePlateAndRegion(licencePlate, region);
    }

    @Transactional
    public CarEntity create(CarRequest car) {
        Integer region = car.getRegion();
        String licencePlate = car.getLicencePlate();

        findByLicencePlateAndRegion(licencePlate, region).ifPresent(
            c -> { throw new BadRequestException("Car already exist");});
        CarEntity carEntity =
            new CarEntity()
                .setRegion(region)
                .setLicencePlate(licencePlate);

        CarModelEntity carModelEntity =
            carModelRepository.findById(car.getCarModelId())
                .orElseThrow(() -> new IllegalArgumentException("Car model does not exist"));
        carEntity.setCarModel(carModelEntity);

        ClientEntity clientEntity =
            clientRepository
                .findById(car.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client does not exist"));
        carEntity.setClientId(clientEntity);

        return carRepository.save(carEntity);
    }

    @Transactional
    public void delete(CarRequest car) {
        findByLicencePlateAndRegion(car.getLicencePlate(), car.getRegion())
            .ifPresent(carRepository::delete);
    }

    @Transactional
    public CarEntity update(CarRequest car) {
        Integer region = car.getRegion();
        String licencePlate = car.getLicencePlate();
        CarEntity carEntityDb = findByLicencePlateAndRegion(car.getLicencePlate(), car.getRegion()).orElseThrow(
            () -> new BadRequestException("Car does not exist"));

        if (licencePlate != null) {
            carEntityDb.setLicencePlate(licencePlate);
        }
        if (region != null) {
            carEntityDb.setRegion(region);
        }

        if (car.getCarModelId() != null) {
            CarModelEntity carModelEntity =
                carModelRepository.findById(car.getCarModelId())
                    .orElseThrow(() -> new IllegalArgumentException("Car model does not exist"));

            carEntityDb.setCarModel(carModelEntity);

        }

        if (car.getClientId() != null) {
            ClientEntity en = clientRepository
                .findById(car.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client does not exist"));

            carEntityDb.setClientId(en);
        }

        return carRepository.save(carEntityDb);
    }

}
