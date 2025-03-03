package com.newsstream.service.news;

import com.newsstream.model.entity.comments.Comment;
import com.newsstream.model.entity.news.News;
import com.newsstream.model.entity.user.User;


import java.util.List;

public interface INewsService {
    List<News> getAll();

    void save(News news);


    Comment addComment(Integer id, String content, User author);

    News updateNews(Integer id, News updatedNews);

    void deleteComments(int Id, User user);

    List<News> getNews(int page, int pageSize);

    Object getTotalPages(int pageSize);

    void deleteNews(Integer id);

    News getNewsById(Integer id);
}
