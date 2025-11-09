package com.msaccount_se182744.config.jwt;

import com.msaccount_se182744.entity.SystemAccounts;
import io.jsonwebtoken.Claims;

public interface JwtProvider {
    String issueAccessToken(SystemAccounts acc, String sessionId, String jti);
    Claims parse(String token);
}
