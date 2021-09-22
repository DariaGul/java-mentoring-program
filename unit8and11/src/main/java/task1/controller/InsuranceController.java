package task1.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import task1.dto.request.InsuranceRequest;
import task1.dto.response.InsuranceResponse;
import task1.service.InsuranceService;

@RestController
@RequestMapping("/unit/api/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void post(@Valid @RequestBody InsuranceRequest request) {
        insuranceService.create(request);
    }

    @GetMapping("/{id}")
    public InsuranceResponse get(@PathVariable Long id) {
        return insuranceService.get(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void put(@PathVariable Long id,
                    @RequestBody InsuranceRequest request) {
        insuranceService.update(request, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        insuranceService.delete(id);
    }

}
