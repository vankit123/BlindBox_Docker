package com.apigateway_se182744.config.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtProviderImpl implements JwtProvider {

    @Value("${app.jwt.secret}")
    private String secretBase64;
    @Value("${app.jwt.access-ttl-seconds}")
    private long ttlSeconds;


    private SecretKey key;

    @PostConstruct
    void init() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretBase64));
    }

    @Override
    public boolean isExpired(String token) {
        Date expiration = parseToken(token).getExpiration();
        return expiration.before(new Date());
    }

    public Claims parseToken(String token) {
        if (token == null || !token.contains(".") || token.split("\\.").length != 3) {
            throw new IllegalArgumentException("Token không hợp lệ.");
        }

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigninKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            throw new RuntimeException("Không thể phân tích token: " + e.getMessage(), e);
        }
    }

    public SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretBase64);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
