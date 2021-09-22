package task1.repository.rolemodel;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.model.BrandEntity;
import task1.model.rolemodel.PrivilegeEntity;

public interface PrivilegeRepository  extends JpaRepository<PrivilegeEntity, Long> {

    PrivilegeEntity findByName(String name);
}
