package task1.service.impl;

import java.util.List;
import java.util.Optional;
import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task1.dto.InsuranceRequest;
import task1.mapper.InsuranceMapper;
import task1.model.CarEntity;
import task1.model.ClientEntity;
import task1.model.InsuranceEntity;
import task1.repository.CarRepository;
import task1.repository.ClientRepository;
import task1.repository.InsuranceRepository;
import task1.service.InsuranceService;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final InsuranceMapper mapper;

    @Override
    @Transactional
    public InsuranceEntity create(InsuranceRequest insurance) {

        get(insurance.getNumber())
            .ifPresent(
                c -> {
                    throw new BadRequestException("Insurance already exist");
                });

        return insuranceRepository.save(
            mapper.toInsuranceEntity(insurance, saveListClientsInsurance(insurance), getCar(insurance)));
    }

    @Override
    @Transactional
    public InsuranceEntity update(InsuranceRequest insurance) {

        InsuranceEntity insuranceEntityDb = get(insurance.getNumber())
            .orElseThrow(
                () -> new BadRequestException("Car does not exist")
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
            insuranceEntityDb.setCar(getCar(insurance));
        }
        if (insurance.getListClients() != null) {
            insuranceEntityDb.setListClients(saveListClientsInsurance(insurance));
        }

        return insuranceRepository.save(insuranceEntityDb);
    }

    private CarEntity getCar(InsuranceRequest insurance) {
        return carRepository
            .findById(insurance.getCarId())
            .orElseThrow(
                () -> new IllegalArgumentException("Car does not exist")
            );
    }

    public Optional<InsuranceEntity> get(String number) {
        return insuranceRepository.findByNumber(number);
    }

    @Override
    public Optional<InsuranceEntity> get(Long id) {
        return insuranceRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        insuranceRepository.findById(id)
            .ifPresent(insuranceRepository::delete);

    }

    private List<ClientEntity> saveListClientsInsurance(InsuranceRequest insurance) {
        List<ClientEntity> listClientsEntity = clientRepository.findByIdIn(insurance.getListClients());
        if (insurance.getListClients().size() != listClientsEntity.size()) {
            throw new IllegalArgumentException("Client(s) does not exist");
        }
        return listClientsEntity;
    }

}
