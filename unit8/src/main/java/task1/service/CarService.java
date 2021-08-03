package task1.service;

import java.util.Optional;
import task1.dto.CarRequest;
import task1.model.CarEntity;

public interface CarService {

    CarEntity create(CarRequest insurance);

    CarEntity update(CarRequest insurance, Long id);

    void delete(CarRequest client);

    CarEntity get(Long id);
}
