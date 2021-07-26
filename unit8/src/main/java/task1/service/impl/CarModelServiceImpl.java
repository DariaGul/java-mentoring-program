package task1.service.impl;

import java.util.Optional;
import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task1.dto.CarModelRequest;
import task1.mapper.CarModelMapper;
import task1.model.BrandEntity;
import task1.model.CarModelEntity;
import task1.repository.BrandRepository;
import task1.repository.CarModelRepository;
import task1.service.CarModelService;

@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;
    private final BrandRepository brandRepository;
    private final CarModelMapper mapper;

    @Transactional
    public CarModelEntity create(CarModelRequest carModel) {
        String model = carModel.getModel();
        Long brand = carModel.getBrandId();
        findByModelAndBrand(model, brand)
            .ifPresent(
                c -> {
                    throw new BadRequestException("Car model already exist");
                });

        return carModelRepository.save(mapper.toCarModelEntity(model, getBrand(brand)));
    }

    @Transactional
    public CarModelEntity update(CarModelRequest carModel) {
        String model = carModel.getModel();
        Long brand = carModel.getBrandId();
        CarModelEntity carModelEntityDb = findByModelAndBrand(model, brand)
            .orElseThrow(
                () -> new BadRequestException("Car model does not exist")
            );

        if (model != null) {
            carModelEntityDb.setModel(model);
        }
        if (brand != null) {
            carModelEntityDb.setBrand(getBrand(brand));
        }

        return carModelRepository.save(carModelEntityDb);
    }

    public Optional<CarModelEntity> findByModelAndBrand(String model, Long brand) {
        return carModelRepository.findByModelAndBrandId(model, brand);
    }

    @Transactional
    public void delete(CarModelRequest carModel) {
        findByModelAndBrand(carModel.getModel(), carModel.getBrandId())
            .ifPresent(carModelRepository::delete);
    }

    @Override
    public Optional<CarModelEntity> get(Long id) {
        return carModelRepository.findById(id);
    }

    private BrandEntity getBrand(Long brandId) {
        return brandRepository.findById(brandId)
            .orElseThrow(
                () -> new IllegalArgumentException("Brand does not exist")
            );
    }
}
