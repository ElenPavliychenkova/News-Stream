package com.newsstream.service.role;

import com.newsstream.exception.EntityNotFoundException;
import com.newsstream.model.entity.user.Role;
import com.newsstream.repositories.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleById(Integer id) {

        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }

    @Override
    public List<Role> findAllRoles() {

        return roleRepository.findAll();
    }
}
