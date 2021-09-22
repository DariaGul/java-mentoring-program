package task1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import task1.dto.request.CarRequest;
import task1.dto.response.CarResponse;
import task1.model.CarEntity;
import task1.model.CarModelEntity;
import task1.model.ClientEntity;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "carModel", source = "carModelEntity")
    @Mapping(target = "client", source = "clientEntity")
    CarEntity toCarEntity(CarRequest request, CarModelEntity carModelEntity, ClientEntity clientEntity);

    CarResponse toCarResponse(CarEntity entity);
}
