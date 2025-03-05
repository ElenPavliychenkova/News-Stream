package com.newsstream.service.comments;

import com.newsstream.model.entity.comments.Comment;

public interface ICommentsService {

    void save(Integer newsId, String text);

    Comment updateComment(Integer id, Comment comment);

    void delete(Integer id);

    Comment getCommentById(Integer id);
}
