package com.testvco.org.co.backendtest.dto.core;

import com.testvco.org.co.backendtest.entidades.core.ListasTiposDocumentosAct;
import com.testvco.org.co.backendtest.utils.Constans;
import com.testvco.org.co.backendtest.utils.Generics;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Clase que implementa los metodos de ListasTiposDocumentosServicios que
 * interactua con el Front-End
 * 
 * @Author 2023-02-24.celf
 */
@Getter
@Setter
public class ListasTiposDocumentosActDto {
    private int idListaTipoDocumento;

    private String nombre;

    private String codigo;

    private Boolean estado;

    private Timestamp creadoFecha;

    private Integer creadoPor;

    private Timestamp editadoFecha;

    private Integer editadoPor;

    private String userCreadoPorInfo;

    private String userEditadoPorInfo;

    public ListasTiposDocumentosActDto() {
    }

    public ListasTiposDocumentosActDto(ListasTiposDocumentosAct listaTipoDocumento, String tipoAccion) {
        Integer idUsuarioLogged = 1;
        this.idListaTipoDocumento = listaTipoDocumento.getIdListaTipoDocumento();
        this.nombre = listaTipoDocumento.getNombre();
        this.codigo = listaTipoDocumento.getCodigo();
        this.estado = !Generics.isNullOrEmpty(listaTipoDocumento.getEstado()) ? listaTipoDocumento.getEstado() : true;
        if (tipoAccion.equals(Constans.AGREGAR)) {
            this.creadoPor = !Generics.isNullOrEmpty(listaTipoDocumento.getCreadoPor())
                    ? listaTipoDocumento.getCreadoPor()
                    : idUsuarioLogged;
            this.creadoFecha = !Generics.isNullOrEmpty(listaTipoDocumento.getCreadoFecha())
                    ? listaTipoDocumento.getCreadoFecha()
                    : new Timestamp(System.currentTimeMillis());
        } else if (tipoAccion.equals(Constans.ACTUALIZAR)) {
            this.editadoPor = !Generics.isNullOrEmpty(listaTipoDocumento.getEditadoPor())
                    ? listaTipoDocumento.getEditadoPor()
                    : idUsuarioLogged;
            this.editadoFecha = !Generics.isNullOrEmpty(listaTipoDocumento.getEditadoFecha())
                    ? listaTipoDocumento.getEditadoFecha()
                    : new Timestamp(System.currentTimeMillis());
        }
        this.userCreadoPorInfo = listaTipoDocumento.getUserCreadoPorInfo();
        this.userEditadoPorInfo = listaTipoDocumento.getUserEditadoPorInfo();
    }

    public ListasTiposDocumentosActDto(ListasTiposDocumentosAct listaTipoDocumento) {
        this.idListaTipoDocumento = listaTipoDocumento.getIdListaTipoDocumento();
        this.nombre = listaTipoDocumento.getNombre();
        this.codigo = listaTipoDocumento.getCodigo();
        this.estado = listaTipoDocumento.getEstado();
        this.creadoPor = listaTipoDocumento.getCreadoPor();
        this.creadoFecha = listaTipoDocumento.getCreadoFecha();
        this.editadoPor = listaTipoDocumento.getEditadoPor();
        this.editadoFecha = listaTipoDocumento.getEditadoFecha();
        this.userCreadoPorInfo = listaTipoDocumento.getUserCreadoPorInfo();
        this.userEditadoPorInfo = listaTipoDocumento.getUserEditadoPorInfo();
    }

    public ListasTiposDocumentosActDto(int idListaTipoDocumento, String nombre, String codigo, Boolean estado, Integer creadoPor) {
        Integer idUsuarioLogged = 1;
        this.idListaTipoDocumento = idListaTipoDocumento;
        this.nombre = nombre;
        this.codigo = codigo;
        this.estado = !Generics.isNullOrEmpty(estado) ? estado : true;
        this.creadoPor = !Generics.isNullOrEmpty(creadoPor) ? creadoPor : idUsuarioLogged;
        this.creadoFecha = new Timestamp(System.currentTimeMillis());
    }

}