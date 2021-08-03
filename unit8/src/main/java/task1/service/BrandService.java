package task1.service;

import task1.dto.BrandRequest;
import task1.model.BrandEntity;

public interface BrandService {

    BrandEntity create(BrandRequest brand);

    BrandEntity update(BrandRequest brand, Long id);

    void delete(Long id);

    BrandEntity get(Long id);
}
