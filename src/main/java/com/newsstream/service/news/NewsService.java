package com.newsstream.service.news;

import com.newsstream.exception.PermissionDeniedException;
import com.newsstream.model.entity.comments.Comment;
import com.newsstream.model.entity.news.News;
import com.newsstream.model.entity.user.User;
import com.newsstream.repositories.comments.ICommentsRepository;
import com.newsstream.repositories.news.INewsRepository;
import com.newsstream.utils.AuthUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService implements INewsService {


    private final INewsRepository iNewsRepository;
    private final ICommentsRepository iCommentsRepository;


    @Override
    public List<News> getAll() {
        return iNewsRepository.findAll();
    }

    @Override
    public void save(News news) {
        iNewsRepository.save(news);
    }

    @Override
    public Comment addComment(Integer id, String content, User author) {
        News news = iNewsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Новость не найдена"));
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAuthor(author);
        comment.setNews(news);
        return iCommentsRepository.save(comment);
    }

    @Override
    @Transactional
    public News updateNews(Integer id, News updatedNews) {
        Optional<News> newsOptional = iNewsRepository.findById(id);
        if (newsOptional.isPresent()) {
            News existingNews = newsOptional.get();

            if (updatedNews.getTitle() != null) {
                existingNews.setTitle(updatedNews.getTitle());
            }
            if (updatedNews.getBrief() != null) {
                existingNews.setBrief(updatedNews.getBrief());
            }
            if (updatedNews.getText() != null) {
                existingNews.setText(updatedNews.getText());
            }

            return iNewsRepository.save(existingNews);
        } else {
            throw new RuntimeException("News not found with id " + id);
        }
    }

    @Override
    @Transactional
    public void deleteNews(Integer Id) {

        User user = AuthUtils.getUser();

        News news = iNewsRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("News not found"));

        if (user.getRole().getName().equals("ADMIN") || news.getAuthor().getId().equals(user.getId())) {
            iNewsRepository.deleteById(news.getId());
        } else {
            throw new PermissionDeniedException();
        }
    }

    @Override
    public News getNewsById(Integer id) {
        return iNewsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found with id" + id));
    }

    @Override
    public void deleteComments(int Id, User user) {
        Comment comment = iCommentsRepository.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        if (!comment.getAuthor().equals(user)) {
            throw new IllegalArgumentException("You can't remove this comment");
        }
        iCommentsRepository.deleteById(Id);
    }

    public List<News> getNews(int page, int pageSize) {
        return iNewsRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    public Integer getTotalPages(int pageSize) {
        return (int) Math.ceil((double) iNewsRepository.count() / pageSize);
    }


}
