package task1.repository.rolemodel;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.model.rolemodel.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String name);
}
