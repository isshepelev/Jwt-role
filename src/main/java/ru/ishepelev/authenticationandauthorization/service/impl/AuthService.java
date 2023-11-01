package ru.ishepelev.authenticationandauthorization.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.ishepelev.authenticationandauthorization.domain.Role;
import ru.ishepelev.authenticationandauthorization.domain.User;
import ru.ishepelev.authenticationandauthorization.dto.JwtRequest;
import ru.ishepelev.authenticationandauthorization.dto.JwtResponse;
import ru.ishepelev.authenticationandauthorization.dto.RegistrationUserDto;
import ru.ishepelev.authenticationandauthorization.exception.AppError;
import ru.ishepelev.authenticationandauthorization.repository.RoleRepository;
import ru.ishepelev.authenticationandauthorization.repository.UserRepository;
import ru.ishepelev.authenticationandauthorization.service.UserService;
import ru.ishepelev.authenticationandauthorization.utils.JwtUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    public ResponseEntity<?> createToken(@RequestBody JwtRequest request){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(),request.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"),HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(request.getLogin());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByLogin(registrationUserDto.getLogin()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь уже существует"), HttpStatus.BAD_REQUEST);
        }

        userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok().build();
    }

}
