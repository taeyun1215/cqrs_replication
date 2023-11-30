package com.example.demo.user.application.port.in;

import com.example.demo.user.adapter.in.web.response.UserResponse;

public interface GetUserQuery {
    UserResponse getUser(Long userId);
}
