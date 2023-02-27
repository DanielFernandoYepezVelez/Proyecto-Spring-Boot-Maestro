package com.testvco.org.co.backendtest.servicios.interfaces.core;

import java.util.List;

import com.testvco.org.co.backendtest.dto.core.ListasTiposDocumentosDto;

/**
 * Interfaz del servicio de ListasTiposDocumentos que interactua con la
 * implementacion del servicio y el controller
 * 
 * @Author 2023-02-24.celf
 */
public interface ListasTiposDocumentosServicios {
    List<ListasTiposDocumentosDto> getAllListaTipoDocumento();

    ListasTiposDocumentosDto getByIdListaTipoDocumento(Integer idListaTipoDocumento);

    List<ListasTiposDocumentosDto> getAllActivosListaTipoDocumento();

    ListasTiposDocumentosDto saveTipoDocumento(ListasTiposDocumentosDto itemTipoDocumento);

    ListasTiposDocumentosDto updatedTipoDocumento(Integer idListaTipoDocumento,
            ListasTiposDocumentosDto itemTipoDocumento);

    void deleteListaTipoDocumento(Integer idListaTipoDocumento);
}
