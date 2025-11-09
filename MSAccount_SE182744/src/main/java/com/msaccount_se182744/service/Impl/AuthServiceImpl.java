package com.msaccount_se182744.service.Impl;

import com.msaccount_se182744.config.jwt.JwtProvider;
import com.msaccount_se182744.dto.request.LoginRequest;
import com.msaccount_se182744.dto.request.RegisterRequest;
import com.msaccount_se182744.dto.response.AuthResponse;
import com.msaccount_se182744.entity.SystemAccounts;
import com.msaccount_se182744.repository.SystemAccountRepository;
import com.msaccount_se182744.service.AuthService;
import com.msaccount_se182744.shared.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final SystemAccountRepository accountRepository;
    private final JwtProvider jwtProvider;
    @Override
    public AuthResponse login(LoginRequest loginRequest) throws ResourceNotFoundException {
            SystemAccounts  acc = accountRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Incorrrect user name or password. Please check again."));

            System.out.println(acc.isActive());
            if (!acc.isActive()) {
                throw new ResourceNotFoundException("Account is disabled or locked");
            }


            String sessionId = UUID.randomUUID().toString();
            String jti = UUID.randomUUID().toString();

            String access = jwtProvider.issueAccessToken(acc, sessionId, jti);

            return new AuthResponse(access);
    }

    @Override
    public SystemAccounts register(RegisterRequest registerRequest) {
        return null;
    }
}
