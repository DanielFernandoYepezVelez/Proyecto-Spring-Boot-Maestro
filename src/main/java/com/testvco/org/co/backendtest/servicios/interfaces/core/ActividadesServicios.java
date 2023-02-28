package com.testvco.org.co.backendtest.servicios.interfaces.core;

import com.testvco.org.co.backendtest.dto.core.ActividadesDto;
import java.util.List;

/**
 * Interfaz del servicio de Actividades que interactua con la
 * implementacion del servicio y el controller
 *
 * @Author 2023-02-28.dfy
 */
public interface ActividadesServicios {
    List<ActividadesDto> getAllActividad();

    ActividadesDto getByIdActividad(Integer idActividad);

    List<ActividadesDto> getAllActivosActividad();

    ActividadesDto saveActividad(ActividadesDto itemActividad);

    ActividadesDto updatedActividad(Integer idActividad, ActividadesDto itemActividad);

    void deleteActividad(Integer idActividad);
}