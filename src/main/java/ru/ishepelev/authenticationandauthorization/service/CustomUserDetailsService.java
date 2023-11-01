package ru.ishepelev.authenticationandauthorization.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ishepelev.authenticationandauthorization.domain.MyUser;
import ru.ishepelev.authenticationandauthorization.repository.MyUserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MyUserRepository myUserRepository;

    public CustomUserDetailsService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        MyUser myUserOptional = myUserRepository.findByLogin(login);
        if (myUserOptional == null){
            throw new RuntimeException("Пользователь с таким логином не найден");
        }
        return new User(myUserOptional.getLogin(), myUserOptional.getPassword(), new ArrayList<>());
    }
}
