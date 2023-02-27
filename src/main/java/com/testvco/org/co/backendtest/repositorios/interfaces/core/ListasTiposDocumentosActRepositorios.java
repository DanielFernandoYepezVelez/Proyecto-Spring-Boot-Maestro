package com.testvco.org.co.backendtest.repositorios.interfaces.core;

import com.testvco.org.co.backendtest.entidades.core.ListasTiposDocumentosAct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaz del repositorio de ListasTiposDocumentosServicios que interactua con
 * la implementación del servicio
 * 
 * @Author 2023-02-24.celf
 */
@Repository
public interface ListasTiposDocumentosActRepositorios extends CrudRepository<ListasTiposDocumentosAct, Integer> {

	ListasTiposDocumentosAct findByIdListaTipoDocumento(Integer idListaTipoDocumento);

	List<ListasTiposDocumentosAct> findByEstadoTrueOrderByCodigo();

	ListasTiposDocumentosAct findByNombreIgnoreCase(String nombre);

	ListasTiposDocumentosAct findByNombreIgnoreCaseAndCodigoIgnoreCase(String nombre, String codigo);
}
