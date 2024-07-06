package com.example.chatapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.chatapp.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


import java.net.Authenticator;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.time}")
    private long expireTimeDelta;
    private UserDetailsService userDetailsService;
    @Value("${jwt.rsa-pub-key}")
    private RSAPublicKey rsaPublicKey;
    @Value("${jwt.rsa-private-key}")
    private RSAPrivateKey rsaPrivateKey;

    private String createToken(Algorithm algorithm, Authentication authentication) throws JWTCreationException{
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTimeDelta))
                .withSubject(authentication.getName())
                .sign(algorithm);
    }

    private String createRefreshToken(Algorithm algorithm, Authentication authentication) throws JWTCreationException{
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTimeDelta*10))
                .withSubject(authentication.getName())
                .sign(algorithm);
    }

    public TokenResponseDto createTokenPair(Authentication authenticaton) throws JWTCreationException {
        try {
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            String token = createToken(algorithm, authenticaton);
            String refreshToken = createRefreshToken(algorithm, authenticaton);
            return TokenResponseDto.builder()
                    .token(token)
                    .refreshToken(refreshToken)
                    .build();
        } catch (JWTCreationException exception){
            throw exception;
        }
    }

    public boolean validateToken(String token){
    }
}
