package ru.ishepelev.authenticationandauthorization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ishepelev.authenticationandauthorization.domain.User;
import ru.ishepelev.authenticationandauthorization.dto.RegistrationUserDto;
import ru.ishepelev.authenticationandauthorization.repository.UserRepository;
import ru.ishepelev.authenticationandauthorization.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private UserRepository userRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", login)
        ));
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User createNewUser(RegistrationUserDto registrationUserDto) {
        User user = new User();
        user.setName(registrationUserDto.getName());
        user.setLastname(registrationUserDto.getLastname());
        user.setLogin(registrationUserDto.getLogin());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRoles(List.of(roleService.getUserRole()));
        return userRepository.save(user);
    }

}
