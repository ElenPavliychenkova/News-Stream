package com.newsstream.repositories.news;

import com.newsstream.model.entity.news.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INewsRepository extends JpaRepository <News, Integer> {

   // Page<News> findAll(Pageable pageable);

}
