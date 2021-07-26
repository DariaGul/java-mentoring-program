package task1.service;

import java.util.Optional;
import task1.dto.CarRequest;
import task1.model.CarEntity;

public interface CarService {

    CarEntity create(CarRequest insurance);

    CarEntity update(CarRequest insurance);

    void delete(CarRequest client);

    Optional<CarEntity> get(Long id);
}
