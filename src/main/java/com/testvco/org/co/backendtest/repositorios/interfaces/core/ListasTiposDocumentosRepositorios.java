package com.testvco.org.co.backendtest.repositorios.interfaces.core;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.testvco.org.co.backendtest.entidades.core.ListasTiposDocumentos;

/**
 * Interfaz del repositorio de ListasTiposDocumentosServicios que interactua con
 * la implementación del servicio
 * 
 * @Author 2023-02-24.celf
 */
@Repository
public interface ListasTiposDocumentosRepositorios extends CrudRepository<ListasTiposDocumentos, Integer> {

	ListasTiposDocumentos findByIdListaTipoDocumento(Integer idListaTipoDocumento);

	List<ListasTiposDocumentos> findByEstadoTrueOrderByOrden();

	ListasTiposDocumentos findByNombreIgnoreCase(String nombre); 

	ListasTiposDocumentos findByNombreIgnoreCaseAndSiglaIgnoreCase(String nombre, String sigla);
}
