package ru.ishepelev.authenticationandauthorization.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.ishepelev.authenticationandauthorization.domain.User;


import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Optional<User> findByLogin(String login);
    void createNewUser(User user);
}