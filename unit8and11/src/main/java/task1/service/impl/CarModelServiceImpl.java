package task1.service.impl;

import java.util.Optional;
import javax.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task1.dto.request.CarModelRequest;
import task1.mapper.CarModelMapper;
import task1.model.CarModelEntity;
import task1.repository.CarModelRepository;
import task1.service.BrandService;
import task1.service.CarModelService;

@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;
    private final BrandService brandService;
    private final CarModelMapper mapper;

    @Transactional
    public CarModelEntity create(CarModelRequest carModel) {
        findByModelAndBrand(carModel.getModel(), carModel.getBrandId())
            .ifPresent(
                c -> {
                    throw new BadRequestException("Car model already exist");
                });
        return carModelRepository.save(
            mapper.toCarModelEntity(
                carModel.getModel(),
                brandService.get(carModel.getBrandId())
            )
        );
    }

    @Transactional
    public CarModelEntity update(CarModelRequest carModel, Long id) {
        String model = carModel.getModel();
        Long brand = carModel.getBrandId();
        findByModelAndBrand(model, brand)
            .ifPresent(
                c -> {
                    throw new BadRequestException("Car model already exist");
                });

        CarModelEntity carModelEntityDb = carModelRepository
            .findById(id)
            .orElseThrow(() -> new BadRequestException("Car model does not exist"));

        if (model != null) {
            carModelEntityDb.setModel(model);
        }
        if (brand != null) {
            carModelEntityDb.setBrand(brandService.get(brand));
        }

        return carModelRepository.save(carModelEntityDb);
    }

    @Transactional
    public void delete(Long id) {
        carModelRepository.findById(id)
            .ifPresent(carModelRepository::delete);
    }

    @Override
    public CarModelEntity get(Long id) {
        return carModelRepository
            .findById(id)
            .orElseThrow(
                () -> new BadRequestException("Car model does not exist")
            );
    }

    public Optional<CarModelEntity> findByModelAndBrand(String model, Long brand) {
        return carModelRepository.findByModelAndBrandId(model, brand);
    }
}
