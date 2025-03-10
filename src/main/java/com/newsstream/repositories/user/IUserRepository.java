package com.newsstream.repositories.user;

import com.newsstream.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    User save(User user);

}
