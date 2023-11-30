package com.example.demo.user.adapter.out.persistence;

import com.example.demo.common.annotaion.PersistenceAdapter;
import com.example.demo.common.utils.Token;
import com.example.demo.common.utils.TokenProvider;
import com.example.demo.user.application.port.out.LoginPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@PersistenceAdapter
@RequiredArgsConstructor
class AuthenticationAdapter implements LoginPort {
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Override
    public Token login(String email, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication auth = authenticationManager.authenticate(authToken);
        Token token = tokenProvider.generateToken(auth);
        return token;
    }
}
