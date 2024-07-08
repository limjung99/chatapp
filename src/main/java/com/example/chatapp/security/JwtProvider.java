package com.example.chatapp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.chatapp.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.time}")
    private long expireTimeDelta;
    private final CustomUserDetailsService userDetailsService;
    @Value("${jwt.pub-key}")
    private RSAPublicKey publicKey;
    @Value("${jwt.private-key}")
    private RSAPrivateKey privateKey;

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

    public TokenResponse createTokenPair(Authentication authenticaton) throws JWTCreationException {
        try {
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            String token = createToken(algorithm, authenticaton);
            String refreshToken = createRefreshToken(algorithm, authenticaton);
            return TokenResponse.builder()
                    .token(token)
                    .refreshToken(refreshToken)
                    .build();
        } catch (JWTCreationException exception){
            throw exception;
        }
    }

    public boolean validateToken(String token){
        String claimUser = JWT.decode(token)
                .getSubject();

        try {
            userDetailsService.loadUserByUsername(claimUser);
        } catch (UsernameNotFoundException exception){
            return false;
        }

        return true;
    }

    public String extractUserFromToken(String token){
        // TODO
        return null;
    }
}
