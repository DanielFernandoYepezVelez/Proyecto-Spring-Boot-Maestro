package com.testvco.org.co.backendtest.controllers.core;

import com.testvco.org.co.backendtest.dto.ResponseDto;
import com.testvco.org.co.backendtest.dto.core.ListasTiposDocumentosDto;

import org.springframework.web.bind.annotation.RestController;

import com.testvco.org.co.backendtest.servicios.interfaces.core.ListasTiposDocumentosServicios;
import com.testvco.org.co.backendtest.utils.Constans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Clase de ListasTiposDocumentosController que contiene los microservios a
 * exponer para el Front-End
 * 
 * @Author 2023-02-24.celf
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/tipodocumento/")
@Api(value = "Microservicios para el manejo de los datos de tipos de documentos")
public class ListasTiposDocumentosController {

    @Autowired
    ListasTiposDocumentosServicios _tipoDocumentoService;

    @GetMapping(path = "/getAll")
    @ApiOperation(value = "Listar los tipo de documentos", notes = "Retorna una lista de tipo de documento")
    public ResponseEntity<ResponseDto> GetAllTipoDocumento() {
        ResponseDto _responseDto = new ResponseDto();
        _responseDto.setCodigoRespuesta(200);
        _responseDto.setMensaje(Constans.MSG_REGISTROS_LISTADOS_CON_EXITO);
        _responseDto.setData(_tipoDocumentoService.getAllListaTipoDocumento());
        return new ResponseEntity<ResponseDto>(_responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "/findByIdTipoDocumento/{idTipoDocumento}")
    @ApiOperation(value = "Consulta el tipo de documento por id")
    public ResponseEntity<ResponseDto> findByIdTipoDocumento(@PathVariable Integer idTipoDocumento) {
        ResponseDto _responseDto = new ResponseDto(Constans.MSG_REGISTRO_ENCONTRADO_CORRECTAMENTE, 200,
                _tipoDocumentoService.getByIdListaTipoDocumento(idTipoDocumento));
        return new ResponseEntity<ResponseDto>(_responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllActivos")
    @ApiOperation(value = "Listar los tipos de documentos que estan activos", notes = "Retorna una lista de tipos de documentos activos")
    public ResponseEntity<ResponseDto> getAllActivosTipoDocumento() {
        ResponseDto _responseDto = new ResponseDto();
        _responseDto.setCodigoRespuesta(200);
        _responseDto.setMensaje(Constans.MSG_REGISTROS_LISTADOS_CON_EXITO);
        _responseDto.setData(_tipoDocumentoService.getAllActivosListaTipoDocumento());
        return new ResponseEntity<>(_responseDto, HttpStatus.OK);
    }

    @PostMapping(path = "/addTipoDocumento")
    @ApiOperation(value = "Agrega el tipo de documento", notes = "Retorna una instancia de tipo de documento")
    public ResponseEntity<ResponseDto> addTipoDocumento(@RequestBody ListasTiposDocumentosDto itemTipoDocumentoDto) {
        ResponseDto _responseDto = new ResponseDto();
        ListasTiposDocumentosDto tipoDocumentoDtoResp = _tipoDocumentoService.saveTipoDocumento(itemTipoDocumentoDto);
        _responseDto.setCodigoRespuesta(200);
        _responseDto.setMensaje(Constans.MSG_REGISTRO_CREADO_CON_EXITO);
        _responseDto.setData(tipoDocumentoDtoResp);
        return new ResponseEntity<ResponseDto>(_responseDto, HttpStatus.OK);
    }

    @PutMapping(path = "/updateTipoDocumento/{idListaTipoDocumento}")
    @ApiOperation(value = "Actualiza el tipo de documento", notes = "Retorna una instancia de tipo de documento")
    public ResponseEntity<ResponseDto> updateTipoDocumento(@PathVariable Integer idListaTipoDocumento,
            @RequestBody ListasTiposDocumentosDto itemTipoDocumentoDto) {
        ResponseDto _responseDto = new ResponseDto();
        ListasTiposDocumentosDto tipoDocumentoDtoResp = _tipoDocumentoService.updatedTipoDocumento(idListaTipoDocumento,
                itemTipoDocumentoDto);
        _responseDto.setCodigoRespuesta(200);
        _responseDto.setMensaje(Constans.MSG_REGISTRO_ACTUALIZADO_CON_EXITO);
        _responseDto.setData(tipoDocumentoDtoResp);
        return new ResponseEntity<ResponseDto>(_responseDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteTipoDocumento/{idTipoDocumento}")
    @ApiOperation(value = "Elimina el tipo de documento por el id")
    public ResponseEntity<ResponseDto> deleteByIdTipoDocumento(@PathVariable Integer idTipoDocumento) {
        ResponseDto _responseDto = new ResponseDto();
        _responseDto.setCodigoRespuesta(200);
        _responseDto.setMensaje(Constans.MSG_REGISTRO_ELIMINADO_CON_EXITO);
        _responseDto.setData(idTipoDocumento);
        _tipoDocumentoService.deleteListaTipoDocumento(idTipoDocumento);
        return new ResponseEntity<ResponseDto>(_responseDto, HttpStatus.OK);
    }
}
