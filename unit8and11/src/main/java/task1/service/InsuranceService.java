package task1.service;

import task1.dto.request.InsuranceRequest;
import task1.dto.response.InsuranceResponse;
import task1.model.InsuranceEntity;

public interface InsuranceService {

    InsuranceEntity create(InsuranceRequest insurance);

    InsuranceEntity update(InsuranceRequest insurance, Long id);

    void delete(Long id);

    InsuranceResponse get(Long id);
}
