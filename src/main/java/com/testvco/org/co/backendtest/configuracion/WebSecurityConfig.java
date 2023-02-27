package com.testvco.org.co.backendtest.configuracion;

import com.testvco.org.co.backendtest.servicios.interfaces.acl.AuthTokenFilter;
import com.testvco.org.co.backendtest.servicios.implem.acl.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Clase de WebSecurityConfig que contiene los parametros de configuración para el swagger y la autenticación
 * 
 * @Author 2022-05-30.celf
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
        // -- Swagger UI v2
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",  
        // -- Swagger UI v3 (OpenAPI)
        "/v3/api-docs/**",
        "/swagger-ui/**"
        // other public endpoints of your API may be appended to this array
    };

    private static final String[] PUBLIC_WHITELIST = {
        "/api/v1/auth/**"
    };

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    AuthEntryPointJwt unauthorizedHandler;
    
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }
	
    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
            .antMatchers(PUBLIC_WHITELIST).permitAll()
			.antMatchers(AUTH_WHITELIST).permitAll()
			.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }   
}
