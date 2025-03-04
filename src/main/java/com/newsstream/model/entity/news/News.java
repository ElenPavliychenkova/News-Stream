package com.newsstream.model.entity.news;

import com.newsstream.model.entity.comments.Comment;
import com.newsstream.model.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Entity
@Table(name = "news")
@Data
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private String brief;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;
}

