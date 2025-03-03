package com.newsstream.service.comments;
import com.newsstream.model.entity.comments.Comment;



import java.util.List;

public interface ICommentsService {

    List<Comment> getAll();

    void save(Comment comment);

    void delete(int id);
}
