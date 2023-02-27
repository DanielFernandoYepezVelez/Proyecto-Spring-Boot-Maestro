package com.testvco.org.co.backendtest.dto.acl;

import javax.validation.constraints.NotBlank;

/**
 * Clase que implementa los metodos de LoginRequest que interactua con el Front-End
 * 
 * @Author 2023-02-24.celf
 */
public class LoginRequest {
    @NotBlank
	private String username;
	@NotBlank
	private String password;

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
