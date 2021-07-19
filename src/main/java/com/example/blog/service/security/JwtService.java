package com.example.blog.service.security;

import com.example.blog.domain.user.User;
import com.example.blog.service.responseDTO.UserData;
import com.example.blog.util.Util;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
public class JwtService {
    private String key;
    private int sessionTime;

    public JwtService(@Value("${jwt.secret}") String key,
                      @Value("${jwt.sessionTime}") int sessionTime) {
        this.key = key;
        this.sessionTime = sessionTime;
    }

    public String buildToken(UserData user) {
        return Jwts.builder()
                .setIssuer("sungmin.com")
                .setSubject(user.getId())
                .signWith(SignatureAlgorithm.HS256, key)
                .setExpiration(expireTimeFromNow())
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public boolean isVaildToken(String token) {
        if (!Util.isEmpty(token)) {
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

    public Date expireTimeFromNow() {
        return new Date(System.currentTimeMillis() + sessionTime);
    }
}
