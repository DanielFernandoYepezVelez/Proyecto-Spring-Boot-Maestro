package com.testvco.org.co.backendtest.servicios.implem.core;

import com.testvco.org.co.backendtest.dto.core.ListasTiposDocumentosDto;
import com.testvco.org.co.backendtest.entidades.core.ListasTiposDocumentos;
import com.testvco.org.co.backendtest.excepciones.*;
import com.testvco.org.co.backendtest.repositorios.interfaces.core.ListasTiposDocumentosRepositorios;
import com.testvco.org.co.backendtest.servicios.interfaces.core.ListasTiposDocumentosServicios;
import com.testvco.org.co.backendtest.utils.Constans;
import com.testvco.org.co.backendtest.utils.Generics;

import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase de implementación del servicio de ListasTiposDocumentos que interactua
 * con el la interfaz del servicio y el repositorio
 * 
 * @Author 2023-02-24.celf
 */
@Service
public class ListasTiposDocumentosServiciosImpl implements ListasTiposDocumentosServicios {

    private Integer idUsuarioLogged = 1;

    @Autowired
    ListasTiposDocumentosRepositorios _tipoDocumentoRepository;

    @Override
    public List<ListasTiposDocumentosDto> getAllListaTipoDocumento() {
        List<ListasTiposDocumentosDto> listTipoDocumentosDto = new ArrayList<>();
        List<ListasTiposDocumentos> listTipoDocumentos = (List<ListasTiposDocumentos>) _tipoDocumentoRepository
                .findAll();
        for (ListasTiposDocumentos tipoDocumento : listTipoDocumentos) {
            ListasTiposDocumentosDto itemTipoDocumentoDto = new ListasTiposDocumentosDto(tipoDocumento);
            listTipoDocumentosDto.add(itemTipoDocumentoDto);
        }
        if (Generics.isNullOrEmpty(listTipoDocumentosDto)) {
            throw new NoRecordsAvailable();
        }
        return listTipoDocumentosDto;
    }

    @Override
    public ListasTiposDocumentosDto getByIdListaTipoDocumento(Integer idListaTipoDocumento) {
        ListasTiposDocumentosDto tipoDocumentoDto = new ListasTiposDocumentosDto();
        java.util.Optional<ListasTiposDocumentos> item = _tipoDocumentoRepository.findById(idListaTipoDocumento);
        if (item.isPresent()) {
            tipoDocumentoDto = new ListasTiposDocumentosDto(item.get());
        } else {
            throw new NoDataFoundException();
        }
        return tipoDocumentoDto;
    }

    @Override
    public List<ListasTiposDocumentosDto> getAllActivosListaTipoDocumento() {
        List<ListasTiposDocumentosDto> listTipoDocumentosDto = new ArrayList<>();
        List<ListasTiposDocumentos> listTipoDocumentos = _tipoDocumentoRepository.findByEstadoTrueOrderByOrden();
        for (ListasTiposDocumentos tipoDocumento : listTipoDocumentos) {
            ListasTiposDocumentosDto itemTipoDocumentoDto = new ListasTiposDocumentosDto(tipoDocumento);
            listTipoDocumentosDto.add(itemTipoDocumentoDto);
        }
        if (Generics.isNullOrEmpty(listTipoDocumentosDto)) {
            throw new NoRecordsAvailable();
        }
        return listTipoDocumentosDto;
    }

    @Override
    public ListasTiposDocumentosDto saveTipoDocumento(ListasTiposDocumentosDto itemTipoDocumentoDto) {
        ListasTiposDocumentosDto tipoDocumentoDto = new ListasTiposDocumentosDto();
        ListasTiposDocumentos tipoDocumentoExistByNombre = _tipoDocumentoRepository
                .findByNombreIgnoreCase(itemTipoDocumentoDto.getNombre());
        if (Generics.isNullOrEmpty(tipoDocumentoExistByNombre)) {
            ListasTiposDocumentos tipoDocumento = new ListasTiposDocumentos();
            tipoDocumento.setNombre(itemTipoDocumentoDto.getNombre());
            tipoDocumento.setSigla(itemTipoDocumentoDto.getSigla());
            tipoDocumento.setOrden(itemTipoDocumentoDto.getOrden());
            tipoDocumento.setEstado(itemTipoDocumentoDto.getEstado());
            tipoDocumento.setCreadoPor(
                    (!Generics.isNullOrEmpty(itemTipoDocumentoDto.getCreadoPor()) ? itemTipoDocumentoDto.getCreadoPor()
                            : idUsuarioLogged));
            tipoDocumento.setCreadoFecha(
                    (!Generics.isNullOrEmpty(itemTipoDocumentoDto.getCreadoFecha())
                            ? itemTipoDocumentoDto.getCreadoFecha()
                            : new Timestamp(System.currentTimeMillis())));
            tipoDocumento = _tipoDocumentoRepository.save(tipoDocumento);
            tipoDocumentoDto = new ListasTiposDocumentosDto(tipoDocumento, Constans.AGREGAR);
        } else {
            throw new NameAlreadyExists();
        }
        return tipoDocumentoDto;
    }

    @Override
    public ListasTiposDocumentosDto updatedTipoDocumento(Integer idListaTipoDocumento,
            ListasTiposDocumentosDto itemTipoDocumentoDto) {
        ListasTiposDocumentosDto tipoDocumentoDto = new ListasTiposDocumentosDto();
        ListasTiposDocumentos findTipoDocumentoById = _tipoDocumentoRepository
                .findByIdListaTipoDocumento(idListaTipoDocumento);
        if (!Generics.isNullOrEmpty(itemTipoDocumentoDto)
                && idListaTipoDocumento == itemTipoDocumentoDto.getIdListaTipoDocumento()) {
            if (!Generics.isNullOrEmpty(findTipoDocumentoById)) {
                ListasTiposDocumentos tipoDocumentoExistByNombre = _tipoDocumentoRepository
                        .findByNombreIgnoreCase(itemTipoDocumentoDto.getNombre());
                if (Generics.isNullOrEmpty(tipoDocumentoExistByNombre)
                        || (!Generics.isNullOrEmpty(tipoDocumentoExistByNombre) && findTipoDocumentoById
                                .getIdListaTipoDocumento() == tipoDocumentoExistByNombre.getIdListaTipoDocumento())) {
                    findTipoDocumentoById.setNombre(itemTipoDocumentoDto.getNombre());
                    findTipoDocumentoById.setSigla(itemTipoDocumentoDto.getSigla());
                    findTipoDocumentoById.setOrden(itemTipoDocumentoDto.getOrden());
                    findTipoDocumentoById.setEstado(itemTipoDocumentoDto.getEstado());
                    findTipoDocumentoById.setEditadoPor((!Generics.isNullOrEmpty(itemTipoDocumentoDto.getEditadoPor())
                            ? itemTipoDocumentoDto.getEditadoPor()
                            : idUsuarioLogged));
                    findTipoDocumentoById
                            .setEditadoFecha((!Generics.isNullOrEmpty(itemTipoDocumentoDto.getEditadoFecha())
                                    ? itemTipoDocumentoDto.getEditadoFecha()
                                    : new Timestamp(System.currentTimeMillis())));
                    findTipoDocumentoById = _tipoDocumentoRepository.save(findTipoDocumentoById);
                    tipoDocumentoDto = new ListasTiposDocumentosDto(findTipoDocumentoById, Constans.ACTUALIZAR);
                } else {
                    throw new NameAlreadyExists();
                }
            } else {
                throw new NoDataFoundException();
            }
        } else {
            throw new WrongIdentifiers();
        }
        return tipoDocumentoDto;
    }

    @Override
    public void deleteListaTipoDocumento(Integer idListaTipoDocumento) {
        java.util.Optional<ListasTiposDocumentos> item = _tipoDocumentoRepository.findById(idListaTipoDocumento);
        if (item.isPresent()) {
            _tipoDocumentoRepository.delete(item.get());
        } else {
            throw new NoDataFoundException();
        }
    }
}
