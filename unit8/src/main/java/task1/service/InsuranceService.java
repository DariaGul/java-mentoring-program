package task1.service;

import java.util.Optional;
import task1.dto.InsuranceRequest;
import task1.model.InsuranceEntity;

public interface InsuranceService {

    InsuranceEntity create(InsuranceRequest insurance);

    InsuranceEntity update(InsuranceRequest insurance);

    void delete(Long id);

    Optional<InsuranceEntity> get(Long id);
}
