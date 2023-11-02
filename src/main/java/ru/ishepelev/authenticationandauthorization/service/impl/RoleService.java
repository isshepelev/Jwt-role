package ru.ishepelev.authenticationandauthorization.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ishepelev.authenticationandauthorization.domain.Role;
import ru.ishepelev.authenticationandauthorization.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
