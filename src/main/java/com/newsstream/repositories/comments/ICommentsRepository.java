package com.newsstream.repositories.comments;

import com.newsstream.model.entity.comments.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ICommentsRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByNewsId(Integer id);

}
