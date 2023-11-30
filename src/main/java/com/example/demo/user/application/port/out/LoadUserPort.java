package com.example.demo.user.application.port.out;

import com.example.demo.user.domain.User;

public interface LoadUserPort {
    User loadById(Long id);
    User loadByEmail(String email);
    User loadByNickname(String nickname);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
}
