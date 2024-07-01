package com.example.chatapp.util;

import com.example.chatapp.dto.TokenResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.net.Authenticator;

@Component
public class JwtProvider {

    private final String secret;
    private final long timeoutMilliSecond;
    private final UserDetailsService userDetailsService;
    public JwtProvider(@Value("${jwt.secret}") String secret,
                       @Value("${jwt.timeout}") long tokenTimeoutSecond,
                       UserDetailsService userDetailsService){
        this.secret = secret;
        this.timeoutMilliSecond = tokenTimeoutSecond;
        this.userDetailsService = userDetailsService;
    }
    public TokenResponseDto createToken(Authentication authenticaton) {
        // TODO
    }

    public boolean validateToken(String token){
        // TODO
    }
}
