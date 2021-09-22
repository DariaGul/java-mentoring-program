package task1.service.impl;

import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task1.dto.request.BrandRequest;
import task1.model.BrandEntity;
import task1.repository.BrandRepository;
import task1.service.BrandService;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    @Transactional
    public BrandEntity create(BrandRequest brand) {
        return brandRepository.save(new BrandEntity().setBrand(brand.getBrand()));
    }

    @Override
    @Transactional
    public BrandEntity update(BrandRequest brand, Long id) {
        BrandEntity brandEntity = brandRepository.findById(id)
            .orElseThrow(() -> new BadRequestException("Brand does not exist"))
            .setBrand(brand.getBrand());

        return brandRepository.save(brandEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        brandRepository.findById(id)
            .ifPresent(brandRepository::delete);
    }

    @Override
    public BrandEntity get(Long id) {
        return brandRepository
            .findById(id)
            .orElseThrow(
                () -> new BadRequestException("Brand does not exist")
            );
    }
}
