package com.newsstream.service.role;


import com.newsstream.model.entity.user.Role;

import java.util.List;

public interface IRoleService {

    Role getRoleById(Integer id);

    List<Role> findAllRoles();
}
