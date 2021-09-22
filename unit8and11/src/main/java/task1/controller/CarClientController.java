package task1.controller;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import task1.dto.response.CarResponse;
import task1.service.CarService;

@RestController
@RequestMapping("/unit/api/car/client")
@RequiredArgsConstructor
public class CarClientController {

    private final CarService carService;

    @GetMapping("/{clientId}")
    public List<CarResponse> getByClientId(@PathVariable UUID clientId) {
        return carService.getByClientId(clientId);
    }

    @GetMapping
    public List<CarResponse> getByClient(@RequestParam(required = false) String firstName,
                                         @RequestParam(required = false) String lastName,
                                         @RequestParam(required = false) String middleName,
                                         @RequestParam(required = false) String city) {
        if (StringUtils.isBlank(firstName)
            && StringUtils.isBlank(lastName)
            && StringUtils.isBlank(middleName)
            && StringUtils.isBlank(city)) {
            throw new IllegalArgumentException("need one of param");
        }
        return carService.getByClient(firstName, lastName, middleName, city);
    }
}
