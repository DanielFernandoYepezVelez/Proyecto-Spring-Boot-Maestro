package com.testvco.org.co.backendtest.servicios.implem.acl;

import javax.transaction.Transactional;

import com.testvco.org.co.backendtest.entidades.acl.User;
import com.testvco.org.co.backendtest.repositorios.interfaces.acl.UserRepositorios;
import com.testvco.org.co.backendtest.utils.Constans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Clase de implementación del servicio de UserDetailsServiceImpl que interactua con el la interfaz del servicio y el repositorio
 * 
 * @Author 2023-02-24.celf
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepositorios userRepositorio;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositorio.findByUsername(username)
                        .orElseThrow(()-> new UsernameNotFoundException(Constans.USUARIO + " " + Constans.NO_EXISTE +": " + username));
        return UserDetailsImpl.build(user);
    }    
}
