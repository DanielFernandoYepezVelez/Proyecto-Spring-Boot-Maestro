package com.testvco.org.co.backendtest.entidades.acl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Clase de la entidad de Users
 * 
 * @Author 2023-02-24.celf
 */
@Entity
@Table(name="users", schema = "seguridad",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
    })
public class User {

    private Long id;
    private String username;
    private String email;
    private String password;

    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username,  @Email String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * @return id: identificador de la entidad Users
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    public Long getId() {
        return id;
    }

    /**
     * @param id: identificador de la entidad Users
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return username: nombreUsuario de Users
     */
    @NotBlank
    @Size(max=20)
    public String getUsername() {
        return username;
    }

    /**
     * @param username: nombreUsuario de Users
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return email: email de Users
     */
    @NotBlank
    @Size(max=50)
    @Email
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * @param email: email de Users
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return password: clave de Users
     */
    @NotBlank
    @Size(max=120)
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    /**
     * @param password: clave de Users
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return roles: lista de roles del Users
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", schema = "seguridad",
               joinColumns = @JoinColumn(name = "id_user"),
               inverseJoinColumns = @JoinColumn(name = "id_role"))
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles: lista de roles del Users
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
