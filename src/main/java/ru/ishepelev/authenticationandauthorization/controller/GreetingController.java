package ru.ishepelev.authenticationandauthorization.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ishepelev.authenticationandauthorization.domain.Auth;
import ru.ishepelev.authenticationandauthorization.domain.Registration;
import ru.ishepelev.authenticationandauthorization.service.UserService;

@RestController
public class GreetingController {
    private final UserService myUserService;

    public GreetingController(UserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping("/unsecured")
    public String unsecured(){
        return "unsecured";
    }
    @PostMapping("/secured")
    public String secured(){
        return "secured";
    }

}
