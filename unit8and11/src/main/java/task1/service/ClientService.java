package task1.service;

import java.util.List;
import java.util.UUID;
import task1.dto.request.UpdateClientRequest;
import task1.dto.request.CreateClientRequest;
import task1.dto.response.ClientResponse;
import task1.model.ClientEntity;

public interface ClientService {

    ClientEntity create(CreateClientRequest client);

    ClientEntity update(UpdateClientRequest client, UUID id);

    void delete(UUID id);

    ClientEntity get(UUID id);

    List<ClientEntity> get(List<UUID> ids);

    List<ClientResponse> getById(List<UUID> ids);

    ClientResponse getById(UUID id);
}
