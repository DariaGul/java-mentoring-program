package task1.controller;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import task1.dto.request.CreateClientRequest;
import task1.dto.request.UpdateClientRequest;
import task1.dto.response.ClientResponse;
import task1.service.ClientService;

@RestController
@RequestMapping("/unit/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void post(@Valid @RequestBody CreateClientRequest request) {
        clientService.create(request);
    }

    @GetMapping("/{id}")
    public ClientResponse get(@PathVariable UUID id) {
        return clientService.getById(id);
    }

    @GetMapping
    public List<ClientResponse> get(@RequestParam("clientIds") List<UUID> ids) {
        return clientService.getById(ids);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(@PathVariable UUID id,
                    @RequestBody UpdateClientRequest request) {
        clientService.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        clientService.delete(id);
    }

}