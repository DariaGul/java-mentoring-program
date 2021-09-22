package task1.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import task1.model.CarModelEntity;

public interface CarModelRepository extends JpaRepository<CarModelEntity, Long> {

    Optional<CarModelEntity> findByModelAndBrandId(String model, Long brand);


}
