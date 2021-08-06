package task1.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import task1.dto.ClientRequest;
import task1.model.ClientEntity;
import task1.model.InsuranceEntity;

public interface ClientService {

    ClientEntity create(ClientRequest insurance);

    ClientEntity update(ClientRequest insurance, UUID id);

    void delete(UUID id);

    ClientEntity get(UUID id);

    List<ClientEntity> get(List<UUID> ids);
}
