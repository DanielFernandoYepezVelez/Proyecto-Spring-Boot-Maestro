package com.testvco.org.co.backendtest.entidades.core;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testvco.org.co.backendtest.entidades.acl.User;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase de la entidad de ListasTiposDocumentos
 * 
 * @Author 2023-02-24.celf
 */
@Entity
@Table(name = "listas_tipos_documentos", schema = "generico")
@Getter
@Setter
@EqualsAndHashCode
public class ListasTiposDocumentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lista_tipo_documento")
    private int idListaTipoDocumento;

    @Basic
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Basic
    @Column(name = "sigla", length = 3)
    private String sigla;

    @Basic
    @Column(name = "orden")
    private int orden;

    @Basic
    @Column(name = "estado")
    private Boolean estado;

    @Basic
    @Column(name = "creadoFecha")
    private Timestamp creadoFecha;

    @Basic
    @Column(name = "creado_por")
    private Integer creadoPor;

    @Basic
    @Column(name = "editadoFecha")
    private Timestamp editadoFecha;

    @Basic
    @Column(name = "editado_por")
    private Integer editadoPor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(referencedColumnName = "id_user", name = "creado_por", insertable = false, updatable = false, nullable = false)
    private User userCreadoPor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(referencedColumnName = "id_user", name = "editado_por", insertable = false, updatable = false)
    private User userEditadoPor;

    @Transient
    public String getUserCreadoPorInfo() {
        return userCreadoPor != null ? userCreadoPor.getUsername() : null;
    }

    @Transient
    public String getUserEditadoPorInfo() {
        return userEditadoPor != null ? userEditadoPor.getUsername() : null;
    }

    public ListasTiposDocumentos() {
    }

}
