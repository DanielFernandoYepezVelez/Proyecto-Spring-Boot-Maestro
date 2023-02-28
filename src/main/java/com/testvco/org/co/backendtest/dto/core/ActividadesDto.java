package com.testvco.org.co.backendtest.dto.core;

import com.testvco.org.co.backendtest.entidades.core.Actividades;
import com.testvco.org.co.backendtest.utils.Constans;
import com.testvco.org.co.backendtest.utils.Generics;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Clase que implementa los metodos de ActividadesServicios que
 * interactua con el Front-End
 *
 * @Author 2023-02-28.dfy
 */
@Getter
@Setter
public class ActividadesDto {
    private int idActividad;

    private String nombre;

    private String codigo;

    private Boolean estado;

    private Timestamp creadoFecha;

    private Integer creadoPor;

    private Timestamp editadoFecha;

    private Integer editadoPor;

    private String userCreadoPorInfo;

    private String userEditadoPorInfo;

    public ActividadesDto() {
    }

    public ActividadesDto(Actividades actividad, String tipoAccion) {
        Integer idUsuarioLogged = 1;
        this.idActividad = actividad.getIdActividad();
        this.nombre = actividad.getNombre();
        this.codigo = actividad.getCodigo();
        this.estado = !Generics.isNullOrEmpty(actividad.getEstado()) ? actividad.getEstado() : true;
        if (tipoAccion.equals(Constans.AGREGAR)) {
            this.creadoPor = !Generics.isNullOrEmpty(actividad.getCreadoPor())
                    ? actividad.getCreadoPor()
                    : idUsuarioLogged;
            this.creadoFecha = !Generics.isNullOrEmpty(actividad.getCreadoFecha())
                    ? actividad.getCreadoFecha()
                    : new Timestamp(System.currentTimeMillis());
        } else if (tipoAccion.equals(Constans.ACTUALIZAR)) {
            this.editadoPor = !Generics.isNullOrEmpty(actividad.getEditadoPor())
                    ? actividad.getEditadoPor()
                    : idUsuarioLogged;
            this.editadoFecha = !Generics.isNullOrEmpty(actividad.getEditadoFecha())
                    ? actividad.getEditadoFecha()
                    : new Timestamp(System.currentTimeMillis());
        }
        this.userCreadoPorInfo = actividad.getUserCreadoPorInfo();
        this.userEditadoPorInfo = actividad.getUserEditadoPorInfo();
    }

    public ActividadesDto(Actividades actividad) {
        this.idActividad = actividad.getIdActividad();
        this.nombre = actividad.getNombre();
        this.codigo = actividad.getCodigo();
        this.estado = actividad.getEstado();
        this.creadoPor = actividad.getCreadoPor();
        this.creadoFecha = actividad.getCreadoFecha();
        this.editadoPor = actividad.getEditadoPor();
        this.editadoFecha = actividad.getEditadoFecha();
        this.userCreadoPorInfo = actividad.getUserCreadoPorInfo();
        this.userEditadoPorInfo = actividad.getUserEditadoPorInfo();
    }

    public ActividadesDto(int idActividad, String nombre, String codigo, Boolean estado, Integer creadoPor) {
        Integer idUsuarioLogged = 1;
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.codigo = codigo;
        this.estado = !Generics.isNullOrEmpty(estado) ? estado : true;
        this.creadoPor = !Generics.isNullOrEmpty(creadoPor) ? creadoPor : idUsuarioLogged;
        this.creadoFecha = new Timestamp(System.currentTimeMillis());
    }

}