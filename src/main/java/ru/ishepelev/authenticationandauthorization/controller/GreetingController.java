package ru.ishepelev.authenticationandauthorization.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ishepelev.authenticationandauthorization.domain.Auth;
import ru.ishepelev.authenticationandauthorization.domain.MyUser;
import ru.ishepelev.authenticationandauthorization.domain.Registration;
import ru.ishepelev.authenticationandauthorization.service.MyUserService;

import java.util.Date;
import java.util.List;

@RestController
public class GreetingController {
    private final MyUserService myUserService;

    public GreetingController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping()
    public ResponseEntity<List<MyUser>> getAll(){
        return ResponseEntity.ok().body(myUserService.getAll());
    }
    @PostMapping("/reg")
    public ResponseEntity<MyUser> registration(@RequestBody Registration registration){
        return ResponseEntity.ok(myUserService.registrationUser(registration));
    }

}
