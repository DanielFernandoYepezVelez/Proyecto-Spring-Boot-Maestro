package com.testvco.org.co.backendtest.entidades.acl;

import javax.persistence.*;

/**
 * Clase de la entidad de Roles
 * 
 * @Author 2023-02-24.celf
 */
@Entity
@Table(name="roles", schema = "seguridad")
public class Role {
    
    private Integer id;
    private ERole name;
    
    public Role() {
    }
    
    public Role(ERole name) {
        this.name = name;
    }
	
    /**
     * @return id: identificador de la entidad Roles
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    @Column(name = "id_role")
    public Integer getId() {
        return id;
    }

    /**
     * @param id: identificador de la entidad Roles
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name: nombre del Rol
     */	
    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    public ERole getName() {
        return name;
    }
	
    /**
     * @param name: nombre del Rol
     */
    public void setName(ERole name) {
        this.name = name;
    }
}
