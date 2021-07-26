package task1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import task1.dto.CarModelRequest;
import task1.dto.ClientRequest;
import task1.model.CarModelEntity;
import task1.model.ClientEntity;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    ClientEntity toClientEntity(ClientRequest request);
}
