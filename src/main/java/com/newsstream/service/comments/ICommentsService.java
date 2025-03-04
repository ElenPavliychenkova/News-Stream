package com.newsstream.service.comments;
import com.newsstream.model.entity.comments.Comment;

import java.util.List;

public interface ICommentsService {

    List<Comment> getCommentsByNewsId(Integer id);

    void delete(int id);

    void save(Comment comment);

    void save(Integer newsId, String text);

    Comment updateComment(Integer id, String newText);
}
