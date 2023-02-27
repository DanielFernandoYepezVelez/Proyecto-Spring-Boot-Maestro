package com.testvco.org.co.backendtest.repositorios.interfaces.acl;

import java.util.Optional;

import com.testvco.org.co.backendtest.entidades.acl.ERole;
import com.testvco.org.co.backendtest.entidades.acl.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz del repositorio de Rol que interactua con la implementación del servicio
 * 
 * @Author 2023-02-24.celf
 */
@Repository
public interface RoleRepositorios extends CrudRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
