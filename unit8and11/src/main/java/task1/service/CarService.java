package task1.service;

import java.util.List;
import java.util.UUID;
import task1.dto.request.CarRequest;
import task1.dto.response.CarResponse;
import task1.model.CarEntity;

public interface CarService {

    CarEntity create(CarRequest car);

    CarEntity update(CarRequest car, Long id);

    void delete(CarRequest car);

    void delete(Long id);

    CarEntity get(Long id);

    CarResponse getById(Long id);

    List<CarResponse> getByClientId(UUID id);

    List<CarResponse> getByClient(String firstName, String lastName, String middleName, String city);
}
