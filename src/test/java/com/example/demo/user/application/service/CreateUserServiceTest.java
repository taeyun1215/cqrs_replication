package com.example.demo.user.application.service;


import com.example.demo.user.application.port.in.command.CreateUserCommand;
import com.example.demo.user.application.port.out.CreateUserPort;
import com.example.demo.user.application.port.out.PasswordEncoderPort;
import com.example.demo.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@DisplayName("유저 생성관련 서비스")
@ExtendWith(SpringExtension.class)
class CreateUserServiceTest {
    @InjectMocks
    private CreateUserService createUserService;
    @Mock
    private CreateUserPort createUserPort;
    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    private CreateUserCommand createUserCommand;
    private User expectedUser;
    @BeforeEach
    public void setup(){
        createUserCommand = CreateUserCommand.builder()
                .email("zxc123@naver.com")
                .password("originPassword")
                .nickname("닉네임1")
                .name("홍길동")
                .build();
        expectedUser = User.builder()
                .id(new User.UserId(1L))
                .email("zxc123@naver.com")
                .password("encodedPassword")
                .nickname("닉네임1")
                .name("홍길동")
                .build();
    }
    @DisplayName("유저 생성")
    @Test
    public void createUserTest(){
        when(passwordEncoderPort.encode(createUserCommand.getPassword())).thenReturn("encodedPassword");
        when(createUserPort.createUser(any(User.class))).thenReturn(expectedUser);

        // When
        createUserService.createUser(createUserCommand);
        // Then
        verify(createUserPort, times(1)).createUser(any(User.class));
        verify(passwordEncoderPort, times(1)).encode(createUserCommand.getPassword());

    }

}
