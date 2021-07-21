package task1.service;

import java.util.Optional;
import java.util.UUID;
import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task1.dto.ClientRequest;
import task1.model.ClientEntity;
import task1.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public ClientEntity create(ClientRequest client) {
        findByUuid(client.getUuid()).ifPresent(
            c -> { throw new BadRequestException("Car already exist");});
        ClientEntity clientEntity =
            new ClientEntity()
                .setUuid(client.getUuid())
                .setFirstName(client.getFirstName())
                .setLastName(client.getLastName())
                .setMiddleName(client.getMiddleName())
                .setCity(client.getCity());

        return clientRepository.save(clientEntity);
    }

    @Transactional
    public ClientEntity update(ClientRequest client) {
        UUID uuid = client.getUuid();
        ClientEntity clientEntityDb = findByUuid(uuid).orElseThrow(() -> new BadRequestException("Car does not exist"));

        if (uuid != null) {
            clientEntityDb.setUuid(uuid);
        }
        if (client.getFirstName() != null) {
            clientEntityDb.setFirstName(client.getFirstName());
        }
        if (client.getLastName() != null) {
            clientEntityDb.setLastName(client.getLastName());
        }
        if (client.getMiddleName() != null) {
            clientEntityDb.setMiddleName(client.getMiddleName());
        }
        if (client.getCity() != null) {
            clientEntityDb.setCity(client.getCity());
        }
        return clientRepository.save(clientEntityDb);
    }

    public Optional<ClientEntity> findByUuid(UUID uuid) {
        return clientRepository.findByUuid(uuid);
    }

    @Transactional
    public void delete(ClientRequest client) {
        findByUuid(client.getUuid())
            .ifPresent(clientDb -> clientRepository.deleteByUuid(clientDb.getUuid()));
    }

}
