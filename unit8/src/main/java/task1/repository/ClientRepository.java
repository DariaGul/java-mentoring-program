package task1.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task1.model.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>,
    JpaSpecificationExecutor<ClientEntity> {

    @Modifying
    @Query("delete from ClientEntity where uuid = :uuid")
    void deleteByUuid(@Param("uuid") UUID uuid);

    Optional<ClientEntity> findByUuid(@Param("uuid") UUID uuid);
}
