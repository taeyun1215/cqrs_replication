package com.example.demo.user.application.port.out;

import com.example.demo.user.domain.User;

public interface CreateUserPort {
    User createUser(User user);
}
