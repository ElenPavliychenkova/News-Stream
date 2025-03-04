package com.newsstream.service.news;

import com.newsstream.exception.PermissionDeniedException;
import com.newsstream.model.entity.news.News;
import com.newsstream.model.entity.user.User;
import com.newsstream.repositories.comments.ICommentsRepository;
import com.newsstream.repositories.news.INewsRepository;
import com.newsstream.utils.AuthUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @Transactional
    public void save(News news) {
        iNewsRepository.save(news);
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
    @Transactional
    public News getNewsById(Integer id) {
        return iNewsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found with id" + id));
    }


   // public Page<News> getNews(int page, int pageSize) {
       // Pageable pageable = PageRequest.of(page, pageSize);
       // return iNewsRepository.findAll(pageable);
  //  }
}
