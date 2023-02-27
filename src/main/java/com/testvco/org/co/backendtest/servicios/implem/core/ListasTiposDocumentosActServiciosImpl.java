package com.testvco.org.co.backendtest.servicios.implem.core;

import com.testvco.org.co.backendtest.dto.core.ListasTiposDocumentosActDto;
import com.testvco.org.co.backendtest.entidades.core.ListasTiposDocumentosAct;
import com.testvco.org.co.backendtest.excepciones.NameAlreadyExists;
import com.testvco.org.co.backendtest.excepciones.NoDataFoundException;
import com.testvco.org.co.backendtest.excepciones.NoRecordsAvailable;
import com.testvco.org.co.backendtest.excepciones.WrongIdentifiers;
import com.testvco.org.co.backendtest.repositorios.interfaces.core.ListasTiposDocumentosActRepositorios;
import com.testvco.org.co.backendtest.servicios.interfaces.core.ListasTiposDocumentosActServicios;
import com.testvco.org.co.backendtest.utils.Constans;
import com.testvco.org.co.backendtest.utils.Generics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de implementación del servicio de ListasTiposDocumentos que interactua
 * con el la interfaz del servicio y el repositorio
 * 
 * @Author 2023-02-24.celf
 */
@Service
public class ListasTiposDocumentosActServiciosImpl implements ListasTiposDocumentosActServicios {
    private Integer idUsuarioLogged = 1;
    @Autowired
    ListasTiposDocumentosActRepositorios _tipoDocumentoRepository;

    @Override
    public List<ListasTiposDocumentosActDto> getAllListaTipoDocumento() {
        List<ListasTiposDocumentosActDto> listTipoDocumentosDto = new ArrayList<>();
        List<ListasTiposDocumentosAct> listTipoDocumentos = (List<ListasTiposDocumentosAct>) _tipoDocumentoRepository
                .findAll();
        for (ListasTiposDocumentosAct tipoDocumento : listTipoDocumentos) {
            ListasTiposDocumentosActDto itemTipoDocumentoDto = new ListasTiposDocumentosActDto(tipoDocumento);
            listTipoDocumentosDto.add(itemTipoDocumentoDto);
        }
        if (Generics.isNullOrEmpty(listTipoDocumentosDto)) {
            throw new NoRecordsAvailable();
        }
        return listTipoDocumentosDto;
    }

    @Override
    public ListasTiposDocumentosActDto getByIdListaTipoDocumento(Integer idListaTipoDocumento) {
        ListasTiposDocumentosActDto tipoDocumentoDto = new ListasTiposDocumentosActDto();
        java.util.Optional<ListasTiposDocumentosAct> item = _tipoDocumentoRepository.findById(idListaTipoDocumento);
        if (item.isPresent()) {
            tipoDocumentoDto = new ListasTiposDocumentosActDto(item.get());
        } else {
            throw new NoDataFoundException();
        }
        return tipoDocumentoDto;
    }

    @Override
    public List<ListasTiposDocumentosActDto> getAllActivosListaTipoDocumento() {
        List<ListasTiposDocumentosActDto> listTipoDocumentosDto = new ArrayList<>();
        List<ListasTiposDocumentosAct> listTipoDocumentos = _tipoDocumentoRepository.findByEstadoTrueOrderByCodigo();
        for (ListasTiposDocumentosAct tipoDocumento : listTipoDocumentos) {
            ListasTiposDocumentosActDto itemTipoDocumentoDto = new ListasTiposDocumentosActDto(tipoDocumento);
            listTipoDocumentosDto.add(itemTipoDocumentoDto);
        }
        if (Generics.isNullOrEmpty(listTipoDocumentosDto)) {
            throw new NoRecordsAvailable();
        }
        return listTipoDocumentosDto;
    }

    @Override
    public ListasTiposDocumentosActDto saveTipoDocumento(ListasTiposDocumentosActDto itemTipoDocumentoDto) {
        ListasTiposDocumentosActDto tipoDocumentoDto = new ListasTiposDocumentosActDto();
        ListasTiposDocumentosAct tipoDocumentoExistByNombre = _tipoDocumentoRepository
                .findByNombreIgnoreCase(itemTipoDocumentoDto.getNombre());
        if (Generics.isNullOrEmpty(tipoDocumentoExistByNombre)) {
            ListasTiposDocumentosAct tipoDocumento = new ListasTiposDocumentosAct();
            tipoDocumento.setNombre(itemTipoDocumentoDto.getNombre());
            tipoDocumento.setCodigo(itemTipoDocumentoDto.getCodigo());
            tipoDocumento.setEstado(itemTipoDocumentoDto.getEstado());
            tipoDocumento.setCreadoPor(
                    (!Generics.isNullOrEmpty(itemTipoDocumentoDto.getCreadoPor()) ? itemTipoDocumentoDto.getCreadoPor()
                            : idUsuarioLogged));
            tipoDocumento.setCreadoFecha(
                    (!Generics.isNullOrEmpty(itemTipoDocumentoDto.getCreadoFecha())
                            ? itemTipoDocumentoDto.getCreadoFecha()
                            : new Timestamp(System.currentTimeMillis())));
            tipoDocumento = _tipoDocumentoRepository.save(tipoDocumento);
            tipoDocumentoDto = new ListasTiposDocumentosActDto(tipoDocumento, Constans.AGREGAR);
        } else {
            throw new NameAlreadyExists();
        }
        return tipoDocumentoDto;
    }

    @Override
    public ListasTiposDocumentosActDto updatedTipoDocumento(Integer idListaTipoDocumento,
            ListasTiposDocumentosActDto itemTipoDocumentoDto) {
        ListasTiposDocumentosActDto tipoDocumentoDto = new ListasTiposDocumentosActDto();
        ListasTiposDocumentosAct findTipoDocumentoById = _tipoDocumentoRepository
                .findByIdListaTipoDocumento(idListaTipoDocumento);
        if (!Generics.isNullOrEmpty(itemTipoDocumentoDto)
                && idListaTipoDocumento == itemTipoDocumentoDto.getIdListaTipoDocumento()) {
            if (!Generics.isNullOrEmpty(findTipoDocumentoById)) {
                ListasTiposDocumentosAct tipoDocumentoExistByNombre = _tipoDocumentoRepository
                        .findByNombreIgnoreCase(itemTipoDocumentoDto.getNombre());
                if (Generics.isNullOrEmpty(tipoDocumentoExistByNombre)
                        || (!Generics.isNullOrEmpty(tipoDocumentoExistByNombre) && findTipoDocumentoById
                                .getIdListaTipoDocumento() == tipoDocumentoExistByNombre.getIdListaTipoDocumento())) {
                    findTipoDocumentoById.setNombre(itemTipoDocumentoDto.getNombre());
                    findTipoDocumentoById.setCodigo(itemTipoDocumentoDto.getCodigo());
                    findTipoDocumentoById.setEstado(itemTipoDocumentoDto.getEstado());
                    findTipoDocumentoById.setEditadoPor((!Generics.isNullOrEmpty(itemTipoDocumentoDto.getEditadoPor())
                            ? itemTipoDocumentoDto.getEditadoPor()
                            : idUsuarioLogged));
                    findTipoDocumentoById
                            .setEditadoFecha((!Generics.isNullOrEmpty(itemTipoDocumentoDto.getEditadoFecha())
                                    ? itemTipoDocumentoDto.getEditadoFecha()
                                    : new Timestamp(System.currentTimeMillis())));
                    findTipoDocumentoById = _tipoDocumentoRepository.save(findTipoDocumentoById);
                    tipoDocumentoDto = new ListasTiposDocumentosActDto(findTipoDocumentoById, Constans.ACTUALIZAR);
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
        java.util.Optional<ListasTiposDocumentosAct> item = _tipoDocumentoRepository.findById(idListaTipoDocumento);
        if (item.isPresent()) {
            _tipoDocumentoRepository.delete(item.get());
        } else {
            throw new NoDataFoundException();
        }
    }
}
