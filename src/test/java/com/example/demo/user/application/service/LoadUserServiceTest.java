package com.example.demo.user.application.service;

import com.example.demo.user.application.port.out.LoadUserPort;
import com.example.demo.user.adapter.in.web.response.UserResponse;
import com.example.demo.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@DisplayName("유저 조회관련 서비스")
@ExtendWith(SpringExtension.class)
class LoadUserServiceTest {
    @InjectMocks
    private LoadUserService loadUserService;
    @Mock
    private LoadUserPort loadUserPort;
    @BeforeEach
    public void setup(){

    }
    @DisplayName("유저 id 기반 단일조회 테스트")
    @Test
    public void getUser(){
        // Given
        Long userId = 1L;
        User user = User.builder()
                .id(new User.UserId(userId))
                .email("zxc123@naver.com")
                .password("encodedPassword")
                .nickname("닉네임1")
                .name("홍길동")
                .build();
        when(loadUserPort.loadById(userId)).thenReturn(user);

        // When
        UserResponse userResponse = loadUserService.getUser(userId);

        //then
        verify(loadUserPort, times(1)).loadById(1L);
        Assertions.assertEquals(userId, userResponse.getId());
    }

}
