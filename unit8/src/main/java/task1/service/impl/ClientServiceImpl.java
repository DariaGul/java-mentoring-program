package task1.service.impl;

import java.util.List;
import java.util.UUID;
import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task1.dto.ClientRequest;
import task1.mapper.ClientMapper;
import task1.model.ClientEntity;
import task1.repository.ClientRepository;
import task1.service.ClientService;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    @Override
    @Transactional
    public ClientEntity create(ClientRequest client) {
        return clientRepository.save(mapper.toClientEntity(client));
    }

    @Override
    @Transactional
    public ClientEntity update(ClientRequest client, UUID id) {
        ClientEntity clientEntityDb = clientRepository.findById(id)
            .orElseThrow(
                () -> new BadRequestException("Car does not exist")
            );

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

    @Override
    @Transactional
    public void delete(UUID id) {
        clientRepository.findById(id)
            .ifPresent(
                clientDb -> clientRepository.deleteByUuid(id)
            );
    }

    @Override
    public ClientEntity get(UUID id) {
        return clientRepository
            .findById(id)
            .orElseThrow(
                () -> new BadRequestException("Client does not exist")
            );
    }

    @Override
    public List<ClientEntity> get(List<UUID> ids) {
        return clientRepository
            .findByIdIn(ids);
    }

}
