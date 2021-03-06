package com.example.blog.service.security;

import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.responseDTO.UserData;
import com.example.blog.util.Util;
import com.mysql.cj.jdbc.Blob;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Component
public class JwtTokenProvider {
    private String key;
    private int sessionTime;
    private UserRepository userRepository;
    private static final String TOKEN_PREFIX = "Token ";

    @Autowired
    public JwtTokenProvider(@Value("${jwt.secret}") String key,
                            @Value("${jwt.sessionTime}") int sessionTime,
                            UserRepository userRepository) {
        this.key = key;
        this.sessionTime = sessionTime;
        this.userRepository = userRepository;
    }

    public String buildToken(UserData user) {
        return Jwts.builder()
                .setSubject(user.getId())
                .setExpiration(expireTimeFromNow())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public Optional<String> getSubFromToken(String token) {
        try {
            String user_id = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return Optional.ofNullable(user_id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean isValidToken(String token) {
        if (StringUtils.hasText(token)) {
            try {
                Jwts.parser()
                        .setSigningKey(key)
                        .parseClaimsJws(token);
                return true;
            } catch (SignatureException e) {
                log.error("????????? JWT ???????????????.", e);
            } catch (MalformedJwtException e) {
                log.error("????????? JWT ???????????????.", e);
            } catch (ExpiredJwtException e) {
                log.error("????????? JWT ???????????????.", e);
            } catch (UnsupportedJwtException e) {
                log.error("???????????? ?????? JWT ???????????????.", e);
            } catch (IllegalArgumentException e) {
                log.error("JWT claims String??? ??????????????????", e);
            }
        }
        return false;
    }

    public String getTokenFromHeader(String authorization) {
        return authorization.substring(TOKEN_PREFIX.length());
    }

    public Authentication getAuthentication(String id) {
        return userRepository.findById(id)
                .map(user -> new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        Collections.emptyList())).orElse(null);
    }

    public Date expireTimeFromNow() {
        return new Date(System.currentTimeMillis() + sessionTime);
    }
}
