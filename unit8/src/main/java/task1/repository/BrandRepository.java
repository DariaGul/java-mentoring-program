package task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import task1.model.BrandEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, Long>,
    JpaSpecificationExecutor<BrandEntity> {
}
