package com.vegasystems.security.service;

import com.vegasystems.security.dto.AuthResponse;
import com.vegasystems.security.dto.AuthenticationRequest;
import com.vegasystems.security.dto.RegisterRequest;

public interface AuthService {
	AuthResponse register(RegisterRequest request);
	
	AuthResponse authenticate(AuthenticationRequest request);

}
