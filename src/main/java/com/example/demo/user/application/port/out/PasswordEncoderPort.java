package com.example.demo.user.application.port.out;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
    boolean matches(String password, String encodedPassword);
}
