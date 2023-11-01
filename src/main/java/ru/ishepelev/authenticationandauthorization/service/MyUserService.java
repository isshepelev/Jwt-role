package ru.ishepelev.authenticationandauthorization.service;

import ru.ishepelev.authenticationandauthorization.domain.Auth;
import ru.ishepelev.authenticationandauthorization.domain.MyUser;
import ru.ishepelev.authenticationandauthorization.domain.Registration;

import java.util.List;

public interface MyUserService {
    List<MyUser> getAll();


    MyUser registrationUser(Registration registration);
}
