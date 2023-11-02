package ru.ishepelev.authenticationandauthorization.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ishepelev.authenticationandauthorization.domain.Role;
import ru.ishepelev.authenticationandauthorization.repository.RoleRepository;
import ru.ishepelev.authenticationandauthorization.service.UserService;

import java.security.Principal;

@RestController
public class GreetingController {
    private final RoleRepository roleRepository;

    public GreetingController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
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
    @GetMapping("/user")
    public String user(){
        return "user";
    }







    @PostMapping("/roleuser")
    public void roleUser(){
        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);
    }
    @PostMapping("/roleadmin")
    public void roleAdmin(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        roleRepository.save(role);
    }
}
