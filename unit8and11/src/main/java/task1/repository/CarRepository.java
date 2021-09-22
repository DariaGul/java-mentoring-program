package task1.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task1.model.CarEntity;
import task1.model.ClientEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long>, JpaSpecificationExecutor<CarEntity>  {

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

    List<CarEntity> findCarEntityByClient_Id(@Param("clientId") UUID clientId);

    @EntityGraph("CarEntityGraph")
    Optional<CarEntity> findById(@Param("id") Long id);

    static Specification<CarEntity> createCarClientSpecification(String firstName, String lastName, String middleName,
                                                                 String city){
        return  ((root, query, cb) -> {
            Predicate predicate = cb.and();

            Path<ClientEntity> client = root.get("client");
            if (StringUtils.isNotBlank(firstName)) {
                predicate = cb.and(predicate, cb.equal(client.get("firstName"), firstName));
            }
            if (StringUtils.isNotBlank(lastName)) {
                predicate = cb.and(predicate, cb.equal(client.get("lastName"), lastName));
            }
            if (StringUtils.isNotBlank(middleName)) {
                predicate = cb.and(predicate, cb.equal(client.get("middleName"), middleName));
            }
            if (StringUtils.isNotBlank(city)) {
                predicate = cb.and(predicate, cb.equal(client.get("city"), city));
            }

            return predicate;
        });
    }
}
