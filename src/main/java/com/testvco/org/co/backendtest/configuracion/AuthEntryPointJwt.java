package com.testvco.org.co.backendtest.configuracion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testvco.org.co.backendtest.utils.Constans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Clase de componente AuthEntryPointJwt que contiene el punto de acceso
 * 
 * @Author 2022-05-30.celf
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
            throws IOException, ServletException {
        logger.error(Constans.NO_AUTORIZADO + ": {}" + arg2.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Constans.NO_AUTORIZADO);
    }    
}
