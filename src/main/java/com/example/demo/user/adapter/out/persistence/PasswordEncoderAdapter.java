package com.example.demo.user.adapter.out.persistence;

import com.example.demo.common.annotaion.PersistenceAdapter;
import com.example.demo.common.exception.UserBadCredentialsException;
import com.example.demo.user.application.port.out.PasswordEncoderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


@PersistenceAdapter
@RequiredArgsConstructor
class PasswordEncoderAdapter implements PasswordEncoderPort {
    private final PasswordEncoder passwordEncoder;
    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword)  {
        return Optional.of(passwordEncoder.matches(rawPassword, encodedPassword))
                .filter(matches -> matches)
                .orElseThrow(UserBadCredentialsException::new);
    }
}
