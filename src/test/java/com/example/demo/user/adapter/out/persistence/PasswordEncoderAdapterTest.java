package com.example.demo.user.adapter.out.persistence;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

@DisplayName("비밀번호 암호화 어댑터 테스트")
@ExtendWith(MockitoExtension.class)
class PasswordEncoderAdapterTest {
    @InjectMocks
    private PasswordEncoderAdapter passwordEncoderAdapter;
    @Mock
    private PasswordEncoder passwordEncoder;

    @DisplayName("비밀번호 암호화 테스트")
    @Test
    public void encode_test(){
        //Given
        String rawPassword = "zxczxczxc";
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        //When
        String resultPassword = passwordEncoderAdapter.encode(rawPassword);

        //Then
        verify(passwordEncoder, times(1)).encode(any(String.class));
        Assertions.assertEquals(encodedPassword, resultPassword);
    }

    @DisplayName("비밀번호 암호화 검사")
    @Test
    public void matches_test(){
        //Given
        String rawPassword = "zxczxczxc";
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        //When
        boolean result = passwordEncoderAdapter.matches(rawPassword, encodedPassword);

        //Then
        verify(passwordEncoder,times(1)).matches(rawPassword, encodedPassword);
        Assertions.assertTrue(result);

    }
}
