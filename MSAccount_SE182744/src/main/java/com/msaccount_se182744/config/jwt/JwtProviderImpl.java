package com.msaccount_se182744.config.jwt;

import com.msaccount_se182744.entity.SystemAccounts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
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
    public String issueAccessToken(SystemAccounts acc, String sessionId, String jti) {
        var now = Instant.now();

        return Jwts.builder()
                .setSubject(String.valueOf(acc.getAccountId()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(ttlSeconds)))
                .claim("email", acc.getEmail())
                .claim("role", acc.getRole())
                .claim("accountId", acc.getAccountId())
                .setId(jti)
                .signWith(key)
                .compact();
    }

    @Override
    public Claims parse(String token) {
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token).getPayload();

    }
}
