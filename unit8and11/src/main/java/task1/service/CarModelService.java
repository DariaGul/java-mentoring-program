package task1.service;

import task1.dto.request.CarModelRequest;
import task1.model.CarModelEntity;

public interface CarModelService {

    CarModelEntity create(CarModelRequest insurance);

    CarModelEntity update(CarModelRequest insurance, Long id);

    void delete(Long id);

    CarModelEntity get(Long id);
}
