package com.newsstream.service.news;

import com.newsstream.model.entity.news.News;

import java.util.List;

public interface INewsService {
    List<News> getAll();

    void save(News news);


    News updateNews(Integer id, News updatedNews);


   // Page<News> getNews(int page, int pageSize);


    void deleteNews(Integer id);

    News getNewsById(Integer id);
}
