package com.msaccount_se182744.config;

import com.msaccount_se182744.config.jwt.JwtProvider;
import com.msaccount_se182744.enums.Role;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String header = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                Claims c = jwtProvider.parse(token);

                Set<SimpleGrantedAuthority> authorities = new LinkedHashSet<>();

                String r = c.get("role", String.class);
                if (r != null) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + Role.valueOf(r).name()));
                }

                @SuppressWarnings("unchecked")
                List<String> perms = (List<String>) c.get("perms");
                if (perms != null) perms.forEach(p -> authorities.add(new SimpleGrantedAuthority(p)));

                var auth = new UsernamePasswordAuthenticationToken(c.getSubject(), null, authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        chain.doFilter(req, res);
    }

}
