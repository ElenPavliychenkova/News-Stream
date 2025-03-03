package com.newsstream.controller;

import com.newsstream.model.entity.news.News;
import com.newsstream.model.entity.user.User;
import com.newsstream.model.requests.CreateNewsRequest;
import com.newsstream.service.news.INewsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final INewsService newsService;

    @GetMapping
    public String newsPage(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 5;
        List<News> newsList = newsService.getNews(page, pageSize);
        model.addAttribute("newsList", newsList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", newsService.getTotalPages(pageSize));
        return "news";
    }

    @GetMapping("/add-news")
    public String addNewsPage() {
        log.info("add news redirect");
        return "addnews";
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNews(@ModelAttribute CreateNewsRequest createNewsRequest, HttpServletRequest request) {

        log.info("Save new news from request {}", request);
        User user = (User) request.getSession().getAttribute("user");
        log.info("Author = {}", user);

        News news = new News();
        news.setTitle(createNewsRequest.getTitle());
        news.setBrief(createNewsRequest.getBrief());
        news.setText(createNewsRequest.getText());
        news.setAuthor(user);
        newsService.save(news);
        return "redirect:/news";
    }

    @GetMapping("/editnews/{id}")
    public String editNewsPage(@PathVariable Integer id, Model model) {
        News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "editnews";
    }

    @PostMapping ("/editnews/{id}")
    public String editNews(@PathVariable Integer id, @ModelAttribute News news) {
        newsService.updateNews(id, news);
        return "redirect:/news";
    }

    @PostMapping("/delete/{id}")
    public String deleteNewsById(@PathVariable Integer id) {
        newsService.deleteNews(id);
        return "redirect:/news";
    }

}
