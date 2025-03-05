package com.newsstream.service.authentication;

import com.newsstream.exception.EntityNotFoundException;
import com.newsstream.model.entity.user.Role;
import com.newsstream.model.entity.user.User;
import com.newsstream.model.requests.AuthenticationRequest;
import com.newsstream.model.requests.RegistrationRequest;
import com.newsstream.repositories.user.IUserRepository;
import com.newsstream.service.role.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {


    private final IUserRepository userRepository;


    private final IRoleService roleService;

    @Override
    public User authenticate(@Valid AuthenticationRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        return user;
    }


    @Override
    public User registration(RegistrationRequest request) {

        if (request.getRoleId() == null) {
            throw new IllegalArgumentException("Role ID is required");
        }
        if (!request.isValidPassword()) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        User user = new User()
                .setUsername(request.getUsername())
                .setPassword(request.getPassword())
                .setEmail(request.getEmail())
                .setRole(roleService.getRoleById(request.getRoleId()));

        return userRepository.save(user);
    }

    @Override
    public List<Role> getAllRolesNames() {
        return roleService.findAllRoles();
    }

}
