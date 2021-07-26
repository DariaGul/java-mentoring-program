package task1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import task1.model.BrandEntity;
import task1.model.CarModelEntity;

@Mapper(componentModel = "spring")
public interface CarModelMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "brand", source = "brandEntity")
    CarModelEntity toCarModelEntity(String model, BrandEntity brandEntity);
}
