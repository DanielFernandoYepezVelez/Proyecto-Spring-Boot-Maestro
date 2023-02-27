package com.testvco.org.co.backendtest.repositorios.interfaces.acl;

import java.util.Optional;

import com.testvco.org.co.backendtest.entidades.acl.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz del repositorio de Usuario que interactua con la implementación del servicio
 * 
 * @Author 2023-02-24.celf
 */
@Repository
public interface UserRepositorios extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByEmail(String email);  
}
