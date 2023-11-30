package com.example.demo.user.application.service;

import com.example.demo.common.annotaion.*;
import com.example.demo.common.utils.Token;
import com.example.demo.user.application.port.in.*;
import com.example.demo.user.application.port.in.command.LoginCommand;
import com.example.demo.user.application.port.out.*;
import com.example.demo.user.adapter.in.web.response.LoginResponse;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class LoginService implements LoginUseCase {
    private final LoadUserPort loadUserPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final LoginPort loginPort;

    @Override
    public LoginResponse login(LoginCommand command) {
        User user = loadUserPort.loadByEmail(command.getEmail());
        passwordEncoderPort.matches(command.getPassword(), user.getPassword());
        Token jwtToken = loginPort.login(command.getEmail(), command.getPassword());

        return LoginResponse.builder()
                .accessToken(jwtToken.getAccessToken())
                .refreshToken(jwtToken.getRefreshToken())
                .expiration(jwtToken.getExpiration().toString())
                .build();
    }
}
