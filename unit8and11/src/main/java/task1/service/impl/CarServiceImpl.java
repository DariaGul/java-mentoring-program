package task1.service.impl;

import static task1.repository.CarRepository.createCarClientSpecification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import task1.dto.request.CarRequest;
import task1.dto.response.CarResponse;
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
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car already exist");
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
    @Transactional
    public void delete(Long id) {
        carRepository
            .findById(id)
            .ifPresent(carRepository::delete);
    }

    @Override
    public CarEntity get(Long id) {
        return carRepository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car does not exist")
            );
    }

    @Override
    public CarResponse getById(Long id) {
        return mapper.toCarResponse(
            carRepository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car does not exist")
                ));
    }

    @Override
    public List<CarResponse> getByClientId(UUID id) {
        return carRepository.findCarEntityByClient_Id(id)
            .stream()
            .map(mapper::toCarResponse)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CarEntity update(CarRequest car, Long id) {
        Integer region = car.getRegion();
        String licencePlate = car.getLicencePlate();
        CarEntity carEntityDb = carRepository.findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car does not exist")
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

    @Override
    public List<CarResponse> getByClient(String firstName, String lastName, String middleName, String city) {
        return carRepository.findAll(createCarClientSpecification(firstName, lastName, middleName, city))
            .stream()
            .map(mapper::toCarResponse).collect(Collectors.toList());
    }
}
