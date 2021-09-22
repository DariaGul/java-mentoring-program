package task1.service.rolemodel;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import task1.model.rolemodel.PrivilegeEntity;
import task1.model.rolemodel.RoleEntity;
import task1.model.rolemodel.UserEntity;
import task1.repository.rolemodel.PrivilegeRepository;
import task1.repository.rolemodel.RoleRepository;
import task1.repository.rolemodel.UserRepository;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }
        PrivilegeEntity readPrivilege
            = createPrivilegeIfNotFound("READ_PRIVILEGE");
        PrivilegeEntity writePrivilege
            = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<PrivilegeEntity> adminPrivileges = Arrays.asList(
            readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        RoleEntity adminRole = roleRepository.findByName("ROLE_ADMIN");
        createAdminIfNotFound(adminRole);

        alreadySetup = true;
    }

    @Transactional
    PrivilegeEntity createPrivilegeIfNotFound(String name) {

        PrivilegeEntity privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new PrivilegeEntity();
            privilege.setName(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    RoleEntity createRoleIfNotFound(
        String name, List<PrivilegeEntity> privileges) {

        RoleEntity role = roleRepository.findByName(name);
        if (role == null) {
            role = new RoleEntity();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }

    @Transactional
     void createAdminIfNotFound(RoleEntity adminRole) {
        UserEntity user = userRepository.findByEmail("test@test.com");
        if (user == null) {
            user = new UserEntity();
            user.setPassword(passwordEncoder.encode("test"));
            user.setEmail("test@test.com");
            user.setRoles(Arrays.asList(adminRole));
            user.setEnabled(true);
            userRepository.save(user);
        }
    }
}