package com.testvco.org.co.backendtest.entidades.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testvco.org.co.backendtest.entidades.acl.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Clase de la entidad de ListasTiposDocumentos
 * 
 * @Author 2023-02-24.celf
 */
@Entity
@Table(name = "listas_tipos_documentos_act", schema = "generico")
@Getter
@Setter
@EqualsAndHashCode
public class ListasTiposDocumentosAct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lista_tipo_documento")
    private int idListaTipoDocumento;

    @Basic
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Basic
    @Column(name = "codigo", length = 4)
    private String codigo;

    @Basic
    @Column(name = "estado")
    private Boolean estado;

    @Basic
    @Column(name = "creado_fecha")
    private Timestamp creadoFecha;

    @Basic
    @Column(name = "creado_por")
    private Integer creadoPor;

    @Basic
    @Column(name = "editado_fecha")
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

    public ListasTiposDocumentosAct() {
        // TODO document why this constructor is empty
    }

}
