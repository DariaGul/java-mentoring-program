package task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.model.BrandEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}
