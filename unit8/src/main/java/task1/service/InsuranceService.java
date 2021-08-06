package task1.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import task1.dto.InsuranceRequest;
import task1.model.InsuranceEntity;

public interface InsuranceService {

    InsuranceEntity create(InsuranceRequest insurance);

    InsuranceEntity update(InsuranceRequest insurance, Long id);

    void delete(Long id);

    InsuranceEntity get(Long id);
}
