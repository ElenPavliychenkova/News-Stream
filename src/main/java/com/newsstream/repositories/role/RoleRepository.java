package com.newsstream.repositories.role;

import com.newsstream.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
}
