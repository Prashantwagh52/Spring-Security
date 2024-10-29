package com.prashant.SpringSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prashant.SpringSecurity.auth.JwtService;
import com.prashant.SpringSecurity.dto.AuthenticationRequest;
import com.prashant.SpringSecurity.dto.AuthenticationResponse;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {

		try {

			AuthenticationResponse authenticationResponse = authenticate(authenticationRequest);
			return ResponseEntity.ok(authenticationResponse);

		} catch (BadCredentialsException e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	private AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword());
			authenticationManager.authenticate(usernamePasswordAuthenticationToken);

			UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

			String accessToken = jwtService.generateToken(userDetails);

			return new AuthenticationResponse(accessToken);

		} catch (BadCredentialsException ex) {

			throw new BadCredentialsException(ex.getMessage());
		}
	}

	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}
}
