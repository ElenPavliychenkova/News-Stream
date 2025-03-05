package com.newsstream.controller;

import com.newsstream.model.entity.comments.Comment;
import com.newsstream.service.comments.ICommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final ICommentsService commentsService;

    @PostMapping("/delete/{id}")
    public String deleteCommentById(@PathVariable Integer id) {

        commentsService.delete(id);
        return "redirect:/news";
    }

    @GetMapping("/editcomment/{id}")
    public String editCommentPage(@PathVariable Integer id, Model model) {

        Comment comment = commentsService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "editcomment";
    }

    @PostMapping("/editcomment/{id}")
    public String editComment(@PathVariable Integer id, @ModelAttribute Comment comment) {

        commentsService.updateComment(id, comment);
        return "redirect:/news";
    }

}
