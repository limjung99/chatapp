package com.example.chatapp.filter;

import com.example.chatapp.exception.HeaderInvalidException;
import com.example.chatapp.security.CustomUserDetailsService;
import com.example.chatapp.security.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService;

    private String extractTokenFromAuthHeader(String tokenHeader) throws HeaderInvalidException {
        if(!tokenHeader.startsWith("Bearer"))
            throw new HeaderInvalidException("Authorization header should starts with 'Bearer' prefix");
        return tokenHeader.substring(7);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        String token = null;
        try{
            token = extractTokenFromAuthHeader(tokenHeader);
        } catch (HeaderInvalidException e){
            throw new ServletException(e.getMessage());
        }

        if(!jwtProvider.validateToken(token))
            throw new ServletException("invalid token"); // TODO : redirect to new token generate

        String userId = jwtProvider.extractUserFromToken(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request,response);
    }
}
