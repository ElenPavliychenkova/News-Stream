package com.newsstream.service.news;

import com.newsstream.model.entity.news.News;
import com.newsstream.model.requests.CreateNewsRequest;
import org.springframework.data.domain.Page;

public interface INewsService {

    Page<News> getAllPageable(Integer page, Integer size);

    void save(CreateNewsRequest createNewsRequest);

    News updateNews(Integer id, News updatedNews);

    void deleteNews(Integer id);

    News getNewsById(Integer id);
}
