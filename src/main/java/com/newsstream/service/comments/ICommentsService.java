package com.newsstream.service.comments;
import com.newsstream.model.entity.comments.Comment;

public interface ICommentsService {

    void delete(int id);

    void save(Comment comment);

    void save(Integer newsId, String text);

}
