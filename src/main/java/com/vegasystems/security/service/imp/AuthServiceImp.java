package com.vegasystems.security.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vegasystems.entity.Rol;
import com.vegasystems.entity.Usuario;
import com.vegasystems.repository.IUsuarioRepository;
import com.vegasystems.security.dto.AuthResponse;
import com.vegasystems.security.dto.AuthenticationRequest;
import com.vegasystems.security.dto.RegisterRequest;
import com.vegasystems.security.service.AuthService;
import com.vegasystems.security.service.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService{
	private final PasswordEncoder passwordEncoder;
	private final IUsuarioRepository usuarioRepository;
	private final JWTService jwtService;
	private final AuthenticationManager authenticationManager;
	
	@Override
	public AuthResponse register(RegisterRequest request) {
		List<Rol> roles = new ArrayList<>();
		Rol rol = new Rol();
		rol.setNombreRol("ROLE_USER");
		roles.add(rol);
		var user = Usuario.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.roles(roles)
				.build();
		
		usuarioRepository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}

	@Override
	public AuthResponse authenticate(AuthenticationRequest request) {
		System.out.println("Autenticación");
		System.out.println(request.getEmail());
		System.out.println(request.getPassword());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthResponse.builder().token(jwtToken).build();
	}

}
