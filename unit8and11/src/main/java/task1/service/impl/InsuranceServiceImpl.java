package task1.service.impl;

import java.util.List;
import java.util.Optional;
import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import task1.dto.request.InsuranceRequest;
import task1.dto.response.InsuranceResponse;
import task1.mapper.InsuranceMapper;
import task1.model.ClientEntity;
import task1.model.InsuranceEntity;
import task1.repository.InsuranceRepository;
import task1.service.CarService;
import task1.service.ClientService;
import task1.service.InsuranceService;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final CarService carService;
    private final ClientService clientService;
    private final InsuranceMapper mapper;

    @Override
    @Transactional
    public InsuranceEntity create(InsuranceRequest insurance) {
        get(insurance.getNumber())
            .ifPresent(
                c -> {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insurance already exist");
                });

        return insuranceRepository.save(
            mapper.toInsuranceEntity(
                insurance,
                saveListClientsInsurance(insurance),
                carService.get(insurance.getCarId())
            )
        );
    }

    @Override
    @Transactional
    public InsuranceEntity update(InsuranceRequest insurance, Long id) {

        InsuranceEntity insuranceEntityDb = insuranceRepository.findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insurance does not exist")
            );

        if (insurance.getNumber() != null) {
            insuranceEntityDb.setNumber(insurance.getNumber());
        }
        if (insurance.getStartDate() != null) {
            insuranceEntityDb.setStartDate(insurance.getStartDate());
        }
        if (insurance.getEndDate() != null) {
            insuranceEntityDb.setEndDate(insurance.getEndDate());
        }
        if (insurance.getCarId() != null) {
            insuranceEntityDb.setCar(carService.get(insurance.getCarId()));
        }
        if (insurance.getListClients() != null) {
            insuranceEntityDb.setListClients(saveListClientsInsurance(insurance));
        }

        return insuranceRepository.save(insuranceEntityDb);
    }

    public Optional<InsuranceEntity> get(String number) {
        return insuranceRepository.findByNumber(number);
    }

    @Override
    public InsuranceResponse get(Long id) {
        return mapper.toInsuranceResponse(
            insuranceRepository
                .findById(id)
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insurance does not exist")
                ));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        insuranceRepository.findById(id)
            .ifPresent(insuranceRepository::delete);

    }

    private List<ClientEntity> saveListClientsInsurance(InsuranceRequest insurance) {
        List<ClientEntity> listClientsEntity = clientService.get(insurance.getListClients());
        if (insurance.getListClients().size() != listClientsEntity.size()) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Client(s) does not exist");
        }
        return listClientsEntity;
    }

}
