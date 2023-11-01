package ru.ishepelev.authenticationandauthorization.service.impl;

import org.springframework.stereotype.Service;
import ru.ishepelev.authenticationandauthorization.domain.MyUser;
import ru.ishepelev.authenticationandauthorization.domain.Registration;
import ru.ishepelev.authenticationandauthorization.repository.MyUserRepository;
import ru.ishepelev.authenticationandauthorization.service.MyUserService;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserServiceImpl implements MyUserService {
    private final MyUserRepository myUserRepository;


    public MyUserServiceImpl(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public List<MyUser> getAll() {
        return myUserRepository.findAll();
    }

    @Override
    public MyUser registrationUser(Registration registration) {
        Optional<MyUser> myUserOptional = Optional.ofNullable(myUserRepository.findByLogin(registration.getLogin()));
        if (myUserOptional.isPresent()) {
            throw new RuntimeException("пользователь с таким логином существует");
        }
        if (!registration.getPassword().equals(registration.getRepeatPassword())) {
            throw new RuntimeException("Пароли не совпадают");
        }

        MyUser myUser = new MyUser();
        myUser.setName(registration.getName());
        myUser.setLastname(registration.getLastname());
        myUser.setLogin(registration.getLogin());
        myUser.setPassword(registration.getPassword());
        return myUserRepository.save(myUser);                            //TODO asdfasdasdasdasdasdasdasdadasdasdasdasdasdasdasdasdadasdasdasdasdasdasdasdasdadasdasdasdasdasdasdasdasdadasdasdasdasdasdasd
    }
}
