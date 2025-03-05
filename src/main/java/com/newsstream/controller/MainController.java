package com.newsstream.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping
    public String redirectToNewsPage() {

        return "redirect:/news";
    }

    @GetMapping("/main")
    public String main() {

        return "main";
    }

}
