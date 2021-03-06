package task1.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task1.model.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, UUID> {

    @Modifying
    @Query("delete from ClientEntity where id = :id")
    void deleteByUuid(@Param("id") UUID id);

    List<ClientEntity> findByIdIn(@Param("listClients") List<UUID> listClients);

    @EntityGraph("ClientEntityGraph")
    Optional<ClientEntity> findById(@Param("id") UUID id);
}
