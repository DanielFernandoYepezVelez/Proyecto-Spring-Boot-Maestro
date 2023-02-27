package com.testvco.org.co.backendtest.servicios.implem.acl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.testvco.org.co.backendtest.entidades.acl.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interfaz del servicio de UserDetailsImpl que interactua con la implementacion del servicio y el controller
 * 
 * @Author 2023-02-24.celf
 */
public class UserDetailsImpl implements UserDetails {
    private static final Long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;
    
    private Collection<? extends GrantedAuthority> authorities;    

    public UserDetailsImpl(Long id, String username, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role-> new SimpleGrantedAuthority(role.getName().name()))
            .collect(Collectors.toList());

        return new UserDetailsImpl(user.getId(), 
            user.getUsername(), 
            user.getEmail(), 
            user.getPassword(), 
            authorities);
    }

    public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass()!= o.getClass())
            return false;

        UserDetailsImpl user = (UserDetailsImpl)o;    

        return Objects.equal(id, user.id);
    }
}
