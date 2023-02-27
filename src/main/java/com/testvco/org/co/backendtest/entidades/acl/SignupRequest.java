package com.testvco.org.co.backendtest.entidades.acl;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Clase utilizada para el login del SignupRequest
 * 
 * @Author 2023-02-24.celf
 */
public class SignupRequest {

    private String username;
    private String email;
    private String password;
	
    private Set<String> role;

    public SignupRequest() {
    }

    /**
     * @return username: nombreUsuario del SignupRequest
     */
    @NotBlank
    @Size(min=3, max=20)
    public String getUsername() {
        return username;
    }

    /**
     * @param username: nombreUsuario del SignupRequest
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return email: email del SignupRequest
     */
    @Size(max=50)
    @Email
    public String getEmail() {
        return email;
    }

    /**
     * @param email: email del SignupRequest
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return password: clave del SignupRequest
     */
    @NotBlank
    @Size(min=6, max=40)
    public String getPassword() {
        return password;
    }

    /**
     * @param password: clave del SignupRequest
     */
    public void setPassword(String password) {
        this.password = password;
    }
	
    /**
     * @return role: lista de roles del SignupRequest
     */
    public Set<String> getRole() {
        return role;
    }

    /**
     * @param role: lista de roles del SignupRequest
     */
    public void setRole(Set<String> role) {
        this.role = role;
    }
}
