package com.api.service.reporting.service;

import com.api.service.reporting.model.TokenResponse;
import com.api.service.reporting.model.LoginRequest;

import java.util.Optional;

public interface LoginService {
    Optional<TokenResponse> getUserToken(LoginRequest loginRequest);
}
