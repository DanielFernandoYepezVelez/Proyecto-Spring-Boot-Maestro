package com.testvco.org.co.backendtest.controllers.acl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testvco.org.co.backendtest.configuracion.JwtUtils;
import com.testvco.org.co.backendtest.dto.MessageResponse;
import com.testvco.org.co.backendtest.dto.acl.JwtResponse;
import com.testvco.org.co.backendtest.dto.acl.LoginRequest;
import com.testvco.org.co.backendtest.entidades.acl.ERole;
import com.testvco.org.co.backendtest.entidades.acl.Role;
import com.testvco.org.co.backendtest.entidades.acl.SignupRequest;
import com.testvco.org.co.backendtest.entidades.acl.User;
import com.testvco.org.co.backendtest.repositorios.interfaces.acl.RoleRepositorios;
import com.testvco.org.co.backendtest.repositorios.interfaces.acl.UserRepositorios;
import com.testvco.org.co.backendtest.servicios.implem.acl.UserDetailsImpl;
import com.testvco.org.co.backendtest.utils.Constans;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Clase de AuthController que contiene los microservios a exponer para el
 * Front-End
 * 
 * @Author 2023-02-24.celf
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
@Api(value = "Microservicios para la gestión del login")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepositorios userRepositorio;

	@Autowired
	RoleRepositorios roleRepositorio;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	@ApiOperation(value = "Parametros de login", notes = "Retorna entidad loggeada")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

		/* Autenticar el usuario */
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/* Generar Token */
		String jwt = jwtUtils.generateJwtToken(authentication);

		/* Establecer roles de usuario */
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

}
