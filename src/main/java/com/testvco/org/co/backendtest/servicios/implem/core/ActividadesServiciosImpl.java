package com.testvco.org.co.backendtest.servicios.implem.core;

import com.testvco.org.co.backendtest.dto.core.ActividadesDto;
import com.testvco.org.co.backendtest.entidades.core.Actividades;
import com.testvco.org.co.backendtest.excepciones.NameAlreadyExists;
import com.testvco.org.co.backendtest.excepciones.NoDataFoundException;
import com.testvco.org.co.backendtest.excepciones.NoRecordsAvailable;
import com.testvco.org.co.backendtest.excepciones.WrongIdentifiers;
import com.testvco.org.co.backendtest.repositorios.interfaces.core.ActividadesRepositorios;
import com.testvco.org.co.backendtest.servicios.interfaces.core.ActividadesServicios;
import com.testvco.org.co.backendtest.utils.Constans;
import com.testvco.org.co.backendtest.utils.Generics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de implementación del servicio de Actividades que interactua
 * con el la interfaz del servicio y el repositorio
 *
 * @Author 2023-02-28.dfy
 */
@Service
public class ActividadesServiciosImpl implements ActividadesServicios {
    private Integer idUsuarioLogged = 1;
    @Autowired
    ActividadesRepositorios _actividadRepository;

    @Override
    public List<ActividadesDto> getAllActividad() {
        List<ActividadesDto> listActividadsDto = new ArrayList<>();
        List<Actividades> listActividads = (List<Actividades>) _actividadRepository
                .findAll();
        for (Actividades actividad : listActividads) {
            ActividadesDto itemActividadDto = new ActividadesDto(actividad);
            listActividadsDto.add(itemActividadDto);
        }
        if (Generics.isNullOrEmpty(listActividadsDto)) {
            throw new NoRecordsAvailable();
        }
        return listActividadsDto;
    }

    @Override
    public ActividadesDto getByIdActividad(Integer idActividad) {
        ActividadesDto actividadDto = new ActividadesDto();
        java.util.Optional<Actividades> item = _actividadRepository.findById(idActividad);
        if (item.isPresent()) {
            actividadDto = new ActividadesDto(item.get());
        } else {
            throw new NoDataFoundException();
        }
        return actividadDto;
    }

    @Override
    public List<ActividadesDto> getAllActivosActividad() {
        List<ActividadesDto> listActividadsDto = new ArrayList<>();
        List<Actividades> listActividads = _actividadRepository.findByEstadoTrueOrderByCodigo();
        for (Actividades actividad : listActividads) {
            ActividadesDto itemActividadDto = new ActividadesDto(actividad);
            listActividadsDto.add(itemActividadDto);
        }
        if (Generics.isNullOrEmpty(listActividadsDto)) {
            throw new NoRecordsAvailable();
        }
        return listActividadsDto;
    }

    @Override
    public ActividadesDto saveActividad(ActividadesDto itemActividadDto) {
        ActividadesDto actividadDto = new ActividadesDto();
        Actividades actividadExistByNombre = _actividadRepository
                .findByNombreIgnoreCase(itemActividadDto.getNombre());
        if (Generics.isNullOrEmpty(actividadExistByNombre)) {
            Actividades actividad = new Actividades();
            actividad.setNombre(itemActividadDto.getNombre());
            actividad.setCodigo(itemActividadDto.getCodigo());
            actividad.setEstado(itemActividadDto.getEstado());
            actividad.setCreadoPor(
                    (!Generics.isNullOrEmpty(itemActividadDto.getCreadoPor()) ? itemActividadDto.getCreadoPor()
                            : idUsuarioLogged));
            actividad.setCreadoFecha(
                    (!Generics.isNullOrEmpty(itemActividadDto.getCreadoFecha())
                            ? itemActividadDto.getCreadoFecha()
                            : new Timestamp(System.currentTimeMillis())));
            actividad = _actividadRepository.save(actividad);
            actividadDto = new ActividadesDto(actividad, Constans.AGREGAR);
        } else {
            throw new NameAlreadyExists();
        }
        return actividadDto;
    }

    @Override
    public ActividadesDto updatedActividad(Integer idActividad, ActividadesDto itemActividadDto) {
        ActividadesDto actividadDto = new ActividadesDto();
        Actividades findActividadById = _actividadRepository
                .findByIdActividad(idActividad);
        if (!Generics.isNullOrEmpty(itemActividadDto)
                && idActividad == itemActividadDto.getIdActividad()) {
            if (!Generics.isNullOrEmpty(findActividadById)) {
                Actividades actividadExistByNombre = _actividadRepository
                        .findByNombreIgnoreCase(itemActividadDto.getNombre());
                if (Generics.isNullOrEmpty(actividadExistByNombre)
                        || (!Generics.isNullOrEmpty(actividadExistByNombre) && findActividadById
                        .getIdActividad() == actividadExistByNombre.getIdActividad())) {
                    findActividadById.setNombre(itemActividadDto.getNombre());
                    findActividadById.setCodigo(itemActividadDto.getCodigo());
                    findActividadById.setEstado(itemActividadDto.getEstado());
                    findActividadById.setEditadoPor((!Generics.isNullOrEmpty(itemActividadDto.getEditadoPor())
                            ? itemActividadDto.getEditadoPor()
                            : idUsuarioLogged));
                    findActividadById
                            .setEditadoFecha((!Generics.isNullOrEmpty(itemActividadDto.getEditadoFecha())
                                    ? itemActividadDto.getEditadoFecha()
                                    : new Timestamp(System.currentTimeMillis())));
                    findActividadById = _actividadRepository.save(findActividadById);
                    actividadDto = new ActividadesDto(findActividadById, Constans.ACTUALIZAR);
                } else {
                    throw new NameAlreadyExists();
                }
            } else {
                throw new NoDataFoundException();
            }
        } else {
            throw new WrongIdentifiers();
        }
        return actividadDto;
    }

    @Override
    public void deleteActividad(Integer idActividad) {
        java.util.Optional<Actividades> item = _actividadRepository.findById(idActividad);
        if (item.isPresent()) {
            _actividadRepository.delete(item.get());
        } else {
            throw new NoDataFoundException();
        }
    }
}