package com.example.demo.user.application.port.out;

import com.example.demo.common.utils.Token;

public interface LoginPort {
    Token login(String email, String password);
}
