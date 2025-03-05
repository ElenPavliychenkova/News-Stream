package com.newsstream.controller;

import com.newsstream.model.entity.user.Role;
import com.newsstream.model.entity.user.User;
import com.newsstream.model.requests.AuthenticationRequest;
import com.newsstream.model.requests.RegistrationRequest;
import com.newsstream.service.authentication.IAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthService authService;

    @GetMapping("/registration")
    public String registrationPage(Model model) {

        log.info("GET /registration (page)");
        List<Role> roles = authService.getAllRolesNames();
        model.addAttribute("roles", roles);
        log.info("GET /registration (page) roles {}", roles);

        return "registration";
    }

    @GetMapping
    public String loginPage() {

        return "auth";
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String login(@Valid @ModelAttribute AuthenticationRequest authenticationRequest,
                        HttpServletRequest request) {

        log.info("Authenticating user: {}", authenticationRequest.getUsername());
        User user = authService.authenticate(authenticationRequest);
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("role", user.getRole().getName());
        return "main";
    }

    @PostMapping(
            value = "/registration",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    )
    public String registration(@Valid @ModelAttribute RegistrationRequest request,
                               Model model) {

        try {
            User savedUser = authService.registration(request);
            model.addAttribute("user", savedUser);
            return "redirect:/auth";
        } catch (Exception e) {
            log.error(e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        request.getSession().invalidate();
        return "redirect:/news";
    }
}
