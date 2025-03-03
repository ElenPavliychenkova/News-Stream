package com.newsstream.service.authentication;

import com.newsstream.model.entity.user.Role;
import com.newsstream.model.entity.user.User;
import com.newsstream.model.requests.AuthenticationRequest;
import com.newsstream.model.requests.RegistrationRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface IAuthService {

    User authenticate(@Valid AuthenticationRequest request);

    User registration(RegistrationRequest registrationRequest);

    List<Role> getAllRolesNames();

}
