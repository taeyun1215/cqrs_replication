package com.example.demo.user.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String name;
}
