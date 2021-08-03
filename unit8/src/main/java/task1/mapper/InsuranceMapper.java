package task1.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import task1.dto.InsuranceRequest;
import task1.model.CarEntity;
import task1.model.ClientEntity;
import task1.model.InsuranceEntity;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    @Mapping(target = "listClients", source = "clientEntities")
    @Mapping(target = "car", source = "carEntity")
    InsuranceEntity toInsuranceEntity(InsuranceRequest request, List<ClientEntity> clientEntities, CarEntity carEntity);
}
