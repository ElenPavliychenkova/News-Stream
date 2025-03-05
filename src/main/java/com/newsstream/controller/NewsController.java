package com.newsstream.controller;

import com.newsstream.model.entity.news.News;
import com.newsstream.model.requests.CreateNewsRequest;
import com.newsstream.service.comments.ICommentsService;
import com.newsstream.service.news.INewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final INewsService newsService;
    private final ICommentsService commentsService;

    @GetMapping
    public String newsPage(Model model,
                           @RequestParam(name = "show_comment_news_id", required = false) Integer showCommentNewsId,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size) {

        Page<News> newsPage = newsService.getAllPageable(page, size);

        model.addAttribute("newsList", newsPage.getContent());
        model.addAttribute("show_comment_news_id", showCommentNewsId);
        model.addAttribute("currentPage", page);
        model.addAttribute("isLastPage", newsPage.isLast());

        log.info("Render news page with show_comment_news_id={}, page={}, size={}", showCommentNewsId, page, size);
        return "news";
    }

    @GetMapping("/add-news")
    public String addNewsPage() {

        log.info("add news redirect");
        return "addnews";
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNews(@ModelAttribute CreateNewsRequest createNewsRequest) {

        newsService.save(createNewsRequest);
        return "redirect:/news";
    }

    @GetMapping("/editnews/{id}")
    public String editNewsPage(@PathVariable Integer id, Model model) {

        News news = newsService.getNewsById(id);
        model.addAttribute("news", news);
        return "editnews";
    }

    @PostMapping("/editnews/{id}")
    public String editNews(@PathVariable Integer id, @ModelAttribute News news) {

        newsService.updateNews(id, news);
        return "redirect:/news";
    }

    @PostMapping("/delete/{id}")
    public String deleteNewsById(@PathVariable Integer id) {

        newsService.deleteNews(id);
        return "redirect:/news";
    }

    @PostMapping("/{newsId}/comment")
    public String addComment(@PathVariable Integer newsId, @RequestParam String text) {

        commentsService.save(newsId, text);
        return "redirect:/news";
    }
}
