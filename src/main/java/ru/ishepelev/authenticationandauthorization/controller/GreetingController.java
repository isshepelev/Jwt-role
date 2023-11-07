package ru.ishepelev.authenticationandauthorization.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ishepelev.authenticationandauthorization.domain.Role;
import ru.ishepelev.authenticationandauthorization.repository.RoleRepository;
import ru.ishepelev.authenticationandauthorization.utils.JwtUtils;

import java.security.Principal;

@RestController
public class GreetingController {
    private final JwtUtils jwtUtils;
    private final RoleRepository roleRepository;

    public GreetingController(JwtUtils jwtUtils, RoleRepository roleRepository) {
        this.jwtUtils = jwtUtils;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/unsecured")
    public String unsecured() {
        System.out.println(jwtUtils.getRoles("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsb2dpbjEiLCJwYXNzd29yZCI6IiQyYSQxMCRYRUhBdm03emE0SlFNWk1xaTU1ZmIuV09IbkNaZWpYM2FOVTFBSjNVOFJQMUQ0Rng3QlpkcSIsInJvbGVzIjpbeyJpZCI6MSwibmFtZSI6IlJPTEVfVVNFUiJ9XSwibmFtZSI6Im5hbWUxIiwiZXhwIjoxNjk5MzcxNjQ2LCJpYXQiOjE2OTkzNjk4NDZ9.If52-PWv-HpedumSKRrENVj3fT7oXxfEcavwr4Hy_24"));
        return "unsecured";
    }

    @GetMapping("/secured")
    public String secured() {
        return "secured";
    }

    @GetMapping("/info")
    public String userData(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @PostMapping("/roleuser")
    public void roleUser() {
        Role role = new Role();
        role.setName("ROLE_USER");
        roleRepository.save(role);
    }

    @PostMapping("/roleadmin")
    public void roleAdmin() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        roleRepository.save(role);
    }
}
