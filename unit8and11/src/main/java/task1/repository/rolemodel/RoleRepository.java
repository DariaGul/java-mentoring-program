package task1.repository.rolemodel;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.model.rolemodel.PrivilegeEntity;
import task1.model.rolemodel.RoleEntity;

public interface RoleRepository  extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}
