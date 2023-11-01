package ru.ishepelev.authenticationandauthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ishepelev.authenticationandauthorization.domain.MyUser;
import ru.ishepelev.authenticationandauthorization.repository.MyUserRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AuthenticationAndAuthorizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationAndAuthorizationApplication.class, args);
    }
    private final MyUserRepository myUserRepository;

    public AuthenticationAndAuthorizationApplication(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }


    @PostConstruct
    public void initUsers(){
        List<MyUser> users = new ArrayList<>(Arrays.asList(
                new MyUser(null, "name1", "lastname1", "1","1"),
                new MyUser(null, "name2", "lastname2", "2","2"),
                new MyUser(null, "name3", "lastname3", "3","3")

        ));
        myUserRepository.saveAll(users);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
