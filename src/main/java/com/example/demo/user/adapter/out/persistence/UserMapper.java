package com.example.demo.user.adapter.out.persistence;

import com.example.demo.user.domain.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {
    User mapToDomainEntity(UserJpaEntity userJpaEntity){
        return User.builder()
                .id(new User.UserId(userJpaEntity.getId()))
                .email(userJpaEntity.getEmail())
                .nickname(userJpaEntity.getNickname())
                .password(userJpaEntity.getPassword())
                .name(userJpaEntity.getName())
                .build();
    }
    UserJpaEntity mapToJpaEntity(User user){
        return UserJpaEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .name(user.getName())
                .build();
        }
}
