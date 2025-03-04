package com.newsstream.service.comments;

import com.newsstream.model.entity.comments.Comment;
import com.newsstream.repositories.comments.ICommentsRepository;
import com.newsstream.service.news.INewsService;
import com.newsstream.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentsService implements ICommentsService {

    private final ICommentsRepository iCommentsRepository;
    private final INewsService newsService;

    @Override
    public void save(Comment comment) {
        iCommentsRepository.save(comment);
    }

    @Override
    public void save(Integer newsId, String text) {

        Comment comment = new Comment();
        comment.setText(text);
        comment.setAuthor(AuthUtils.getUser());
        comment.setNews(newsService.getNewsById(newsId));
        iCommentsRepository.save(comment);
    }

    @Override
    public void delete(int id) {
        iCommentsRepository.deleteById(id);
    }

}
