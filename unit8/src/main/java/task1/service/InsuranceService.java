package task1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task1.dto.InsuranceRequest;
import task1.model.CarEntity;
import task1.model.ClientEntity;
import task1.model.InsuranceEntity;
import task1.repository.CarRepository;
import task1.repository.ClientRepository;
import task1.repository.InsuranceRepository;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public InsuranceEntity create(InsuranceRequest insurance) {

        findByNumber(insurance.getNumber()).ifPresent(
            c -> { throw new BadRequestException("Insurance already exist");});
        InsuranceEntity insuranceEntity =
            new InsuranceEntity()
                .setNumber(insurance.getNumber())
                .setStartDate(insurance.getStartDate())
                .setEndDate(insurance.getEndDate());

        CarEntity carEntity = carRepository.findById(insurance.getCarId())
            .orElseThrow(() -> new IllegalArgumentException("Car does not exist"));
        insuranceEntity.setCarId(carEntity);

        saveListClientsInsurance(insurance, insuranceEntity);

        return insuranceRepository.save(insuranceEntity);
    }

    @Transactional
    public InsuranceEntity update(InsuranceRequest insurance) {

        InsuranceEntity insuranceEntityDb = findByNumber(insurance.getNumber()).orElseThrow(
            () -> new BadRequestException("Car does not exist"));

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
            insuranceEntityDb.setCarId(carRepository.findById(insurance.getCarId())
                                           .orElseThrow(() -> new IllegalArgumentException("Car does not exist")));
        }
        if (insurance.getListClients() != null) {
            saveListClientsInsurance(insurance, insuranceEntityDb);
        }

        return insuranceRepository.save(insuranceEntityDb);
    }

    public Optional<InsuranceEntity> findByNumber(String number) {
        return insuranceRepository.findByNumber(number);
    }

    @Transactional
    public void delete(Long id) {
        insuranceRepository.findById(id)
            .ifPresent(insuranceRepository::delete);

    }

    private void saveListClientsInsurance(InsuranceRequest insurance, InsuranceEntity insuranceEntity) {
        List<ClientEntity> listClientsEntity = new ArrayList<>();
        insurance.getListClients().forEach(cur -> {
            ClientEntity clientEntity = clientRepository.findByUuid(cur)
                .orElseThrow(() -> new IllegalArgumentException("Client does not exist"));
            listClientsEntity.add(clientEntity);

        });
        insuranceEntity.setListClients(listClientsEntity);
    }

}
