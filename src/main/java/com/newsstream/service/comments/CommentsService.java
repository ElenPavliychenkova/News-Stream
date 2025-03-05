package com.newsstream.service.comments;

import com.newsstream.model.entity.comments.Comment;
import com.newsstream.repositories.comments.ICommentsRepository;
import com.newsstream.service.news.INewsService;
import com.newsstream.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentsService implements ICommentsService {

    private final ICommentsRepository iCommentsRepository;
    private final INewsService newsService;

    @Override
    public void save(Integer newsId, String text) {

        Comment comment = new Comment();
        comment.setText(text);
        comment.setAuthor(AuthUtils.getUser());
        comment.setNews(newsService.getNewsById(newsId));
        iCommentsRepository.save(comment);
    }

    @Override
    public Comment updateComment(Integer id, Comment comment) {

        Optional<Comment> optionalComment = iCommentsRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();

            if (!StringUtils.isEmpty(comment.getText())) {
                existingComment.setText(comment.getText());
            }
            return iCommentsRepository.save(existingComment);
        } else {
            throw new RuntimeException("Comment not found with id " + id);
        }
    }

    @Override
    public void delete(Integer id) {
        iCommentsRepository.deleteById(id);
    }

    @Override
    public Comment getCommentById(Integer id) {
        return iCommentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id" + id));
    }
}
