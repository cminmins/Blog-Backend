package com.example.blog.api.security;

import com.example.blog.api.exception.InvalidAuthTokenException;
import com.example.blog.api.exception.InvalidAuthenticationException;
import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.security.JwtService;
import com.example.blog.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Token ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = parseTokenString(request);
        if (StringUtils.hasText(token)) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(token, null);
            SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    public String parseTokenString(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)){
            return token.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}