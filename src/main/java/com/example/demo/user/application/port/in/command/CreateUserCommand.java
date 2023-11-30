package com.example.demo.user.application.port.in.command;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@Builder
public class CreateUserCommand {

    private final String email;
    private final String password;
    private final String nickname;
    private final String name;

}
