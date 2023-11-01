package ru.ishepelev.authenticationandauthorization.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ishepelev.authenticationandauthorization.service.UserService;

import java.security.Principal;

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
    @GetMapping("/secured")
    public String secured(){
        return "secured";
    }
    @GetMapping("/info")
    public String userData(Principal principal){
        return principal.getName();
    }

}
