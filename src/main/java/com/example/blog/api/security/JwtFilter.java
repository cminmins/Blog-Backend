package com.example.blog.api.security;

import com.example.blog.service.security.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Token ";

    private final JwtTokenProvider jwtTokenProvider;

    public JwtFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        parseTokenString(request).ifPresent(token -> {
            jwtTokenProvider.getSubFromToken(token).ifPresent(id -> {
                Authentication authentication = jwtTokenProvider.getAuthentication(id);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        });

        filterChain.doFilter(request, response);
    }

    public Optional<String> parseTokenString(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            return Optional.ofNullable(token.substring(TOKEN_PREFIX.length()));
        }
        return Optional.empty();
    }
}