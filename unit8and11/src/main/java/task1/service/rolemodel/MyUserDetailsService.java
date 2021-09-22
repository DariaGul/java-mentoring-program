package task1.service.rolemodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task1.model.rolemodel.PrivilegeEntity;
import task1.model.rolemodel.RoleEntity;
import task1.model.rolemodel.UserEntity;
import task1.repository.rolemodel.RoleRepository;
import task1.repository.rolemodel.UserRepository;

@Service("userDetailsService")
@Transactional
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

   // private IUserService service;

    private MessageSource messages;

    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email)
        throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            return new org.springframework.security.core.userdetails.User(
                " ", " ", true, true, true, true,
                getAuthorities(Arrays.asList(
                    roleRepository.findByName("ROLE_USER"))));
        }

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), user.isEnabled(), true, true,
            true, getAuthorities(user.getRoles()));
    }

//    public UserEntity registerNewUserAccount(UserDto accountDto) throws Exception {
//
//        if (emailExist(accountDto.getEmail())) {
//            throw new IllegalArgumentException("User with this email exist");
//        }
//        UserEntity user = new UserEntity();
//        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
//        user.setEmail(accountDto.getEmail());
//
//        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
//        return userRepository.save(user);
//    }

    private List<? extends GrantedAuthority> getAuthorities(
        List<RoleEntity> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(List<RoleEntity> roles) {

        List<String> privileges = new ArrayList<>();
        List<PrivilegeEntity> collection = new ArrayList<>();
        for (RoleEntity role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (PrivilegeEntity item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}
