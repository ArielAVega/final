package com.vegasystems.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vegasystems.security.service.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter{
	private final UserDetailsService userDetailsService;
	private final JWTService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String jwtToken;
		final String userName;
		
		if(authHeader==null || !authHeader.startsWith("Bearer")) {
			System.out.println("Pide sin token de autorzación");
			filterChain.doFilter(request, response);
			return;
		}
		
		jwtToken = authHeader.substring(7);
		userName = jwtService.getUserName(jwtToken);
		
		System.out.println("Si existe el token");
		System.out.println(jwtToken);
		System.out.println("Usuario extraido del token: "+userName);
		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			System.out.println("El nombre de usuario no es nulo y no está validado"
					+ "");
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			System.out.println("----UserDetails---");
			System.out.println(userDetails.getUsername());
			System.out.println(userDetails.getPassword());
			System.out.println(userDetails.getAuthorities());
			
			boolean isTokenValidate = jwtService.validateToken(jwtToken, userDetails);
			System.out.println("El token es valido: "+isTokenValidate);
			if(isTokenValidate) {
				UsernamePasswordAuthenticationToken authenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				// util cuando nos conectamos a aplicaciones moviles
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		
		
	}

}
