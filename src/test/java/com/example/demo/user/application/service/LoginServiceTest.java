package com.example.demo.user.application.service;

import com.example.demo.common.utils.Token;
import com.example.demo.user.application.port.in.command.LoginCommand;
import com.example.demo.user.application.port.out.LoadUserPort;
import com.example.demo.user.application.port.out.LoginPort;
import com.example.demo.user.application.port.out.PasswordEncoderPort;
import com.example.demo.user.adapter.in.web.response.LoginResponse;
import com.example.demo.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.time.Instant;

import static org.mockito.Mockito.*;

@DisplayName("로그인 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @InjectMocks
    private LoginService loginService;
    @Mock
    private LoadUserPort loadUserPort;
    @Mock
    private PasswordEncoderPort passwordEncoderPort;
    @Mock
    private LoginPort loginPort;


    @DisplayName("로그인 테스트")
    @Test
    void loginWithValidCredentialsReturnsLoginResponse() {
        String email = "zxc123@naver.com";
        String rawPassword = "rawPassword";
        String encodedPassword = "encodedPassword";

        LoginCommand loginCommand = LoginCommand.builder()
                .email(email)
                .password(rawPassword)
                .build();
        // given
        User user = User.builder()
                .id(new User.UserId(1L))
                .name("홍길동")
                .nickname("닉네임")
                .password(encodedPassword)
                .email(email)
                .build();
        Token token = Token.builder()
                .accessToken("Bearer accessToken")
                .refreshToken("refreshToken")
                .expiration(Instant.now().plusMillis(3600000L))
                .build();
        Authentication auth = Mockito.mock(Authentication.class);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginCommand.getEmail(), loginCommand.getPassword());
        when(loadUserPort.loadByEmail(loginCommand.getEmail())).thenReturn(user);
        when(passwordEncoderPort.matches(rawPassword, encodedPassword)).thenReturn(true);
        when(loginPort.login(loginCommand.getEmail(), loginCommand.getPassword())).thenReturn(token);
        // When
        LoginResponse loginResponse = loginService.login(loginCommand);

        // Then
        verify(loadUserPort, times(1)).loadByEmail(email);
        verify(passwordEncoderPort, times(1)).matches(loginCommand.getPassword(), user.getPassword());
        Assertions.assertEquals(loginResponse.getAccessToken(), token.getAccessToken());
        Assertions.assertEquals(loginResponse.getRefreshToken(), token.getRefreshToken());
        Assertions.assertEquals(loginResponse.getExpiration(), token.getExpiration().toString());
    }
}