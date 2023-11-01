package ru.ishepelev.authenticationandauthorization.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ishepelev.authenticationandauthorization.domain.User;
import ru.ishepelev.authenticationandauthorization.dto.JwtRequest;
import ru.ishepelev.authenticationandauthorization.dto.JwtResponse;
import ru.ishepelev.authenticationandauthorization.dto.RegistrationUserDto;
import ru.ishepelev.authenticationandauthorization.exception.AppError;
import ru.ishepelev.authenticationandauthorization.service.UserService;
import ru.ishepelev.authenticationandauthorization.service.impl.AuthService;
import ru.ishepelev.authenticationandauthorization.utils.JwtUtils;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createToken(@RequestBody JwtRequest request){
        return authService.createToken(request);
    }
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
        return authService.createNewUser(registrationUserDto);
    }
}
