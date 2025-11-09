package com.apigateway_se182744.config.jwt;

import io.jsonwebtoken.Claims;

public interface JwtProvider {
    boolean isExpired(String token);
    Claims parseToken(String token);
}
