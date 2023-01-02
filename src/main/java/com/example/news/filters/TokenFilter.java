package com.example.news.filters;

import com.example.news.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);

    private final String authToken;

    public TokenFilter(@Value("${api.key}") String authToken) {
        this.authToken = authToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Authentication auth = getAuthentication(request.getHeader(HttpHeaders.AUTHORIZATION));
            SecurityContextHolder.getContext().setAuthentication(auth);
            LOGGER.warn("Валидный ключ");
        } catch (Exception e) {
            LOGGER.warn("Не авторизован - невалидный ключ", e);
        } finally {
            filterChain.doFilter(request, response);
        }

    }

    private Authentication getAuthentication(String token) {
        if (!authToken.equals(token)) {
            throw new RuntimeException();
        }
        return new UsernamePasswordAuthenticationToken(
                new User("user"),
                null,
                null);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION) == null;
    }
}
