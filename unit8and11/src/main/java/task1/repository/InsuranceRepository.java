package task1.repository;

import java.util.Optional;
import javax.persistence.NamedEntityGraph;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import task1.model.InsuranceEntity;

public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long> {

    Optional<InsuranceEntity> findByNumber(@Param("number") String number);

    @EntityGraph("InsuranceEntityGraph")
    Optional<InsuranceEntity> findById(Long id);
}
