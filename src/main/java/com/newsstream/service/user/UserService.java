package com.newsstream.service.user;


import com.newsstream.model.entity.user.User;
import com.newsstream.model.requests.UpdateUserRequest;
import com.newsstream.repositories.user.IUserRepository;
import com.newsstream.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public void updateProfile(UpdateUserRequest updateUserRequest) {

        User user = AuthUtils.getUser();
        user.setUsername(updateUserRequest.getUsername());
        user.setPassword(updateUserRequest.getPassword());

        userRepository.save(user);
    }
}
