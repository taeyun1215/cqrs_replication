package com.example.demo.user.application.service;

import com.example.demo.common.annotaion.UseCase;
import com.example.demo.user.application.port.in.GetUserQuery;
import com.example.demo.user.application.port.out.LoadUserPort;
import com.example.demo.user.adapter.in.web.response.UserResponse;
import com.example.demo.user.domain.User;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
class LoadUserService implements GetUserQuery {
    private final LoadUserPort loadUserPort;
    @Override
    public UserResponse getUser(Long userId) {
        User user = loadUserPort.loadById(userId);
        return UserResponse.builder()
                .id(user.getId().getValue())
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .build();
    }
}
