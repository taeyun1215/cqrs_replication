package com.example.demo.user.application.port.in;


import com.example.demo.user.application.port.in.command.LoginCommand;
import com.example.demo.user.adapter.in.web.response.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginCommand command);
}
