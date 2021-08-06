package task1.service.impl;

import java.util.Optional;
import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task1.dto.CarRequest;
import task1.mapper.CarMapper;
import task1.model.CarEntity;
import task1.repository.CarRepository;
import task1.service.CarModelService;
import task1.service.CarService;
import task1.service.ClientService;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarModelService carModelService;
    private final ClientService clientService;
    private final CarMapper mapper;

    public Optional<CarEntity> findByLicencePlateAndRegion(String licencePlate, Integer region) {
        return carRepository.findCarEntityByLicencePlateAndRegion(licencePlate, region);
    }

    @Override
    @Transactional
    public CarEntity create(CarRequest car) {
        Integer region = car.getRegion();
        String licencePlate = car.getLicencePlate();

        findByLicencePlateAndRegion(licencePlate, region)
            .ifPresent(
                c -> {
                    throw new BadRequestException("Car already exist");
                }
            );
        return carRepository.save(
            mapper.toCarEntity(
                car,
                carModelService.get(car.getCarModelId()),
                clientService.get(car.getClientId()))
        );
    }

    @Override
    @Transactional
    public void delete(CarRequest car) {
        findByLicencePlateAndRegion(car.getLicencePlate(), car.getRegion())
            .ifPresent(carRepository::delete);
    }

    @Override
    public CarEntity get(Long id) {
        return carRepository
            .findById(id)
            .orElseThrow(
                () -> new BadRequestException("Car does not exist")
            );
    }

    @Override
    @Transactional
    public CarEntity update(CarRequest car, Long id) {
        Integer region = car.getRegion();
        String licencePlate = car.getLicencePlate();
        CarEntity carEntityDb = carRepository.findById(id)
            .orElseThrow(
                () -> new BadRequestException("Car does not exist")
            );
        if (licencePlate != null) {
            carEntityDb.setLicencePlate(licencePlate);
        }
        if (region != null) {
            carEntityDb.setRegion(region);
        }
        if (car.getCarModelId() != null) {
            carEntityDb.setCarModel(carModelService.get(car.getCarModelId()));
        }
        if (car.getClientId() != null) {
            carEntityDb.setClient(clientService.get(car.getClientId()));
        }
        return carRepository.save(carEntityDb);
    }

}
