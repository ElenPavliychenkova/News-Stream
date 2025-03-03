package com.newsstream.service.comments;

import com.newsstream.repositories.comments.ICommentsRepository;
import lombok.RequiredArgsConstructor;
import com.newsstream.model.entity.comments.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentsService implements ICommentsService {

    private final ICommentsRepository iCommentsRepository;

    @Override
    public List<Comment> getAll() {
        return iCommentsRepository.findAll();
    }



    public void save(Comment comment) {
        iCommentsRepository.save(comment);
    }


    @Override
    public void delete(int id) {
        iCommentsRepository.deleteById(id);

    }
}
