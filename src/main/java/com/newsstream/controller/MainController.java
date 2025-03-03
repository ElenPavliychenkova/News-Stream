package com.newsstream.controller;


import com.newsstream.service.news.INewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final INewsService newsService;

    @GetMapping
    public String redirectToNewsPage(Model model) {

        model.addAttribute("newsList", newsService.getAll());
        log.info("redirect to news page");
        return "news";
    }

}
