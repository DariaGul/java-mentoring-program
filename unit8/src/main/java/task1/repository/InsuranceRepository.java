package task1.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import task1.model.InsuranceEntity;

public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long>,
    JpaSpecificationExecutor<InsuranceEntity> {

    Optional<InsuranceEntity> findByNumber(@Param("number") String number);
}
