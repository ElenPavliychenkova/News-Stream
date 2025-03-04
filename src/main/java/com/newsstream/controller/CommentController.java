package com.newsstream.controller;

import com.newsstream.service.comments.ICommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final ICommentsService commentsService;



    // 1) написать метод удаления коммента (айдишки коммента)

    // 2) написать метод апдейта коммента (айдишка коммента и новый текст)
}
