package task1.service;

import java.util.Optional;
import task1.dto.CarModelRequest;
import task1.dto.CarRequest;
import task1.model.CarEntity;
import task1.model.CarModelEntity;

public interface CarModelService {

    CarModelEntity create(CarModelRequest insurance);

    CarModelEntity update(CarModelRequest insurance, Long id);

    void delete(Long id);

    CarModelEntity get(Long id);
}
