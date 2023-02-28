package com.testvco.org.co.backendtest.repositorios.interfaces.core;

import com.testvco.org.co.backendtest.entidades.core.Actividades;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaz del repositorio de ActividadesServicios que interactua con
 * la implementación del servicio
 *
 * @Author 2023-02-28.dfy
 */
@Repository
public interface ActividadesRepositorios extends CrudRepository<Actividades, Integer> {

	Actividades findByIdActividad(Integer idActividad);

	List<Actividades> findByEstadoTrueOrderByCodigo();

	Actividades findByNombreIgnoreCase(String nombre);

	Actividades findByNombreIgnoreCaseAndCodigoIgnoreCase(String nombre, String codigo);
}