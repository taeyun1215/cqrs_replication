package com.example.demo.user.application.port.in.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;


@Builder
@Getter
public class LoginCommand {
    private final String email;
    private final String password;
}
