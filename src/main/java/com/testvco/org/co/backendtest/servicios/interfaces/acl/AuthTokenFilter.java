package com.testvco.org.co.backendtest.servicios.interfaces.acl;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testvco.org.co.backendtest.configuracion.JwtUtils;
import com.testvco.org.co.backendtest.servicios.implem.acl.UserDetailsServiceImpl;
import com.testvco.org.co.backendtest.utils.Constans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                try {
                    String jwt = parseJwt(request);
                    if (jwt != null && jwtUtils.validateJwtToke(jwt)){
                        String username = jwtUtils.getUserNameFromJwtTok(jwt);

                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }

                } catch (Exception e) {
                    logger.error(Constans.ERROR_CREAR_AUTENTICACION_USUARIO, e);
                }

                filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader(Constans.AUTHORIZATION);
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith(Constans.BEARER + " ")){
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
