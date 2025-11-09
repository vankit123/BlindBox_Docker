package com.msaccount_se182744.service;

import com.msaccount_se182744.dto.request.LoginRequest;
import com.msaccount_se182744.dto.request.RegisterRequest;
import com.msaccount_se182744.dto.response.AuthResponse;
import com.msaccount_se182744.entity.SystemAccounts;


public interface AuthService {
    AuthResponse login(LoginRequest loginRequest) throws Exception;
    SystemAccounts register(RegisterRequest registerRequest);
}
