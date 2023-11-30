package com.example.demo.user.adapter.in.web;

import com.example.demo.common.SuccessApiResponse;
import com.example.demo.user.adapter.in.web.request.LoginRequest;
import com.example.demo.user.adapter.in.web.response.LoginResponse;
import com.example.demo.user.application.port.in.LoginUseCase;
import com.example.demo.user.application.port.in.command.LoginCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@DisplayName("인증 컨트롤러 테스트")
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private LoginUseCase loginUseCase;

    @DisplayName("로그인 테스트")
    @Test
    public void loginTest(){
        // Given
        LoginRequest loginRequest= new LoginRequest("zxc123@naver.com", "zxczxczxc");
        // when
        SuccessApiResponse successApiResponse = authController.login(loginRequest);

        // then
        Mockito.verify(loginUseCase, Mockito.times(1)).login(any());
        Assertions.assertThat(successApiResponse.getStatus())
                .isEqualTo(200);
    }
}
