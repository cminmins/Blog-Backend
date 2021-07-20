package com.example.blog.service.security;

import com.example.blog.api.exception.InvalidAuthTokenException;
import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.responseDTO.UserData;
import com.example.blog.util.Util;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
                .setIssuer("sungmin.com")
                .setSubject(user.getId())
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(expireTimeFromNow())
                .compact();
    }

    public Optional<String> getSubFromToken(String token) {
        try{
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

    public boolean isVaildToken(String token) {
        if (StringUtils.hasText(token)) {
            try {
                Jwts.parser()
                        .setSigningKey(key)
                        .parseClaimsJws(token);
                return true;
            } catch (SignatureException e) {
                log.error("잘못된 JWT 서명입니다.", e);
            } catch (MalformedJwtException e) {
                log.error("잘못된 JWT 토큰입니다.", e);
            } catch (ExpiredJwtException e) {
                log.error("만료된 JWT 토근입니다.", e);
            } catch (UnsupportedJwtException e) {
                log.error("지원하지 않는 JWT 토큰입니다.", e);
            } catch (IllegalArgumentException e) {
                log.error("JWT claims String이 비어있습니다", e);
            }
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        getSubFromToken(token).ifPresent(id ->{
            userRepository.findById(id).map(user -> {
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                return authentication;
//                    //mapper      T          U
//                    new Function<User, Authentication>() {
//
//                        @Override                     value
//                        public Authentication apply(User user) {
//                            return new UsernamePasswordAuthenticationToken(user, null);
//                        }
//                    }
            });
        });
        return null;
    }

    public Date expireTimeFromNow() {
        return new Date(System.currentTimeMillis() + sessionTime);
    }
}
