package com.newsstream.service.news;

import com.newsstream.exception.PermissionDeniedException;
import com.newsstream.model.entity.news.News;
import com.newsstream.model.entity.user.User;
import com.newsstream.model.requests.CreateNewsRequest;
import com.newsstream.repositories.news.INewsRepository;
import com.newsstream.utils.AuthUtils;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService implements INewsService {


    private final INewsRepository iNewsRepository;


    @Override
    public List<News> getAll() {
        return iNewsRepository.findAll();
    }

    @Override
    public Page<News> getAllPageable(Integer page, Integer size) {

        PageRequest pageable = PageRequest.of(page, size);
        return iNewsRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void save(CreateNewsRequest createNewsRequest) {

        User user = AuthUtils.getUser();

        News news = new News();
        news.setTitle(createNewsRequest.getTitle());
        news.setBrief(createNewsRequest.getBrief());
        news.setText(createNewsRequest.getText());
        news.setAuthor(user);

        iNewsRepository.save(news);
    }


    @Override
    @Transactional
    public News updateNews(Integer id, News updatedNews) {

        Optional<News> newsOptional = iNewsRepository.findById(id);
        if (newsOptional.isPresent()) {
            News existingNews = newsOptional.get();

            if (!StringUtils.isBlank(updatedNews.getTitle())) {
                existingNews.setTitle(updatedNews.getTitle());
            }
            if (!StringUtils.isBlank(updatedNews.getBrief())) {
                existingNews.setBrief(updatedNews.getBrief());
            }
            if (!StringUtils.isBlank(updatedNews.getText())) {
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
}
