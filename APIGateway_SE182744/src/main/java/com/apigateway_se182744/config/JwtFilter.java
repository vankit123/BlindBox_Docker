package com.apigateway_se182744.config;

import com.apigateway_se182744.config.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter implements GlobalFilter, Ordered {

    private final JwtProvider jwtProvider;

    private final List<String> AUTH_PERMISSION = List.of(
            "/account-service/auth/**"
    );

    private boolean isPublicAPI(String uri) {
        AntPathMatcher matcher = new AntPathMatcher();
        return AUTH_PERMISSION.stream().anyMatch(pattern -> matcher.match(pattern, uri));
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();

        log.info("path: {}", path);
        // Nếu nằm trong danh sách public -> bỏ qua JWT

        if (isPublicAPI(path)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = authHeader.substring(7);

        try {
            if (jwtProvider.isExpired(token)) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            jwtProvider.parseToken(token);
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        Claims claims = jwtProvider.parseToken(token);
        String username = claims.getSubject();
        String role = claims.get("role", String.class);

        // Gắn thêm thông tin user vào header khi forward
        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(builder -> builder
                        .header("X-User-Name", username)
                        .header("X-User-Role", role)
                )
                .build();


        log.info("Added headers -> X-User-Name: {}, X-User-Role: {}", username, role);
        return chain.filter(mutatedExchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
