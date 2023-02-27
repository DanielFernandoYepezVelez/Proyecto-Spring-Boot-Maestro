package com.testvco.org.co.backendtest.servicios.interfaces.core;

import com.testvco.org.co.backendtest.dto.core.ListasTiposDocumentosActDto;

import java.util.List;

/**
 * Interfaz del servicio de ListasTiposDocumentos que interactua con la
 * implementacion del servicio y el controller
 * 
 * @Author 2023-02-24.celf
 */
public interface ListasTiposDocumentosActServicios {
    List<ListasTiposDocumentosActDto> getAllListaTipoDocumento();

    ListasTiposDocumentosActDto getByIdListaTipoDocumento(Integer idListaTipoDocumento);

    List<ListasTiposDocumentosActDto> getAllActivosListaTipoDocumento();

    ListasTiposDocumentosActDto saveTipoDocumento(ListasTiposDocumentosActDto itemTipoDocumento);

    ListasTiposDocumentosActDto updatedTipoDocumento(Integer idListaTipoDocumento,
            ListasTiposDocumentosActDto itemTipoDocumento);

    void deleteListaTipoDocumento(Integer idListaTipoDocumento);
}
