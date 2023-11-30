package com.example.demo.user.application.port.in;

import com.example.demo.user.application.port.in.command.CreateUserCommand;

public interface CreateUserUseCase {
    void createUser(CreateUserCommand command);
}
