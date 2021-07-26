package task1.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task1.model.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long>,
    JpaSpecificationExecutor<CarEntity> {

    @EntityGraph("CarEntityGraph")
    Optional<CarEntity> findCarEntityByCarModelModelAndCarModelBrandAndRegion(@Param("model") String model,
                                                                              @Param("brand") String brand,
                                                                              @Param("region") String region);

    @EntityGraph("CarEntityGraph")
    Optional<CarEntity> findCarEntityByLicencePlateAndRegion(@Param("licencePlate") String licencePlate,
                                                             @Param("region") Integer region);

    @Modifying
    @Query("delete from CarEntity where licencePlate = :licencePlate and region = :region")
    void deleteByLicencePlateAndRegion(@Param("licencePlate") String licencePlate, @Param("region") String region);
}
