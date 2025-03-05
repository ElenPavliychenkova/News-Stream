package com.newsstream.controller;


import com.newsstream.model.entity.user.User;
import com.newsstream.model.requests.UpdateUserRequest;
import com.newsstream.service.user.IUserService;
import com.newsstream.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/editprofile")
    public String editProfilePage(Model model) {

        User user = AuthUtils.getUser();
        model.addAttribute("user", user);
        return "editprofile";
    }

    @PostMapping("/editprofile")
    public String editProfile(@ModelAttribute UpdateUserRequest updateUserRequest) {

        userService.updateProfile(updateUserRequest);
        return "redirect:/main";
    }
}
