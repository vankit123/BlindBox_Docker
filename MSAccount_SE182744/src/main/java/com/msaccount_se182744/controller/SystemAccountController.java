package com.msaccount_se182744.controller;

import com.msaccount_se182744.dto.request.LoginRequest;
import com.msaccount_se182744.dto.request.RegisterRequest;
import com.msaccount_se182744.dto.response.AuthResponse;
import com.msaccount_se182744.entity.SystemAccounts;
import com.msaccount_se182744.service.AuthService;
import com.msaccount_se182744.shared.dto.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SystemAccountController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest req,
                                                           HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(
                ApiResponse.<AuthResponse>builder()
                        .data(authService.login(req))
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .message("Login successful")
                        .build()
        );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<SystemAccounts>> register(@RequestBody @Valid RegisterRequest request, HttpServletRequest httpRequest) {
        return ResponseEntity.ok(
                ApiResponse.<SystemAccounts>builder()
                        .data(authService.register(request))
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .message("Login successful")
                        .build()
        );
    }
}