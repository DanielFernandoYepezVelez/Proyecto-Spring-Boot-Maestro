package com.testvco.org.co.backendtest.configuracion;

import java.util.Date;

import com.testvco.org.co.backendtest.servicios.implem.acl.UserDetailsImpl;
import com.testvco.org.co.backendtest.utils.Constans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Clase de componente JwtUtils que contiene la configuración para los parametros de acceso y token
 * 
 * @Author 2022-05-30.celf
 */
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${testv.app.jwtSecret}")
    private String jwtSecret;

    @Value("${testv.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl)authentication.getPrincipal();

        return Jwts.builder()
            .setSubject((userPrincipal.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public String getUserNameFromJwtTok(String token)
    {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToke(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error(Constans.LOGGER_TOKEN_INVALIDO_POR_FIRMA, e.getMessage());
        }catch (MalformedJwtException e) {
            logger.error(Constans.LOGGER_TOKEN_INVALIDO, e.getMessage());
        }catch (ExpiredJwtException e) {
            logger.error(Constans.LOGGER_TOKEN_EXPIRADO, e.getMessage());
        }catch (UnsupportedJwtException e) {
            logger.error(Constans.LOGGER_TOKEN_NO_SOPORTADO, e.getMessage());
        }catch (IllegalArgumentException e) {
            logger.error(Constans.LOGGER_JWT_CLAIMS_VACIO, e.getMessage());
        }
        return false;
    }

}
