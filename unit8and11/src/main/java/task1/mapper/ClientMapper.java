package task1.mapper;

import org.mapstruct.Mapper;
import task1.dto.request.CreateClientRequest;
import task1.dto.response.ClientResponse;
import task1.model.ClientEntity;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientEntity toClientEntity(CreateClientRequest request);

    ClientResponse toClientResponse(ClientEntity entity);
}
