package com.newsstream.service.user;

import com.newsstream.model.requests.UpdateUserRequest;

public interface IUserService {
    void updateProfile(UpdateUserRequest updateUserRequest);
}
