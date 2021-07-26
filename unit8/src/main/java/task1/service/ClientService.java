package task1.service;

import java.util.Optional;
import java.util.UUID;
import task1.dto.ClientRequest;
import task1.model.ClientEntity;

public interface ClientService {

    ClientEntity create(ClientRequest insurance);

    ClientEntity update(ClientRequest insurance, UUID id);

    void delete(UUID id);

    Optional<ClientEntity> get(UUID id);
}
