package com.testvco.org.co.backendtest.controllers.core;

import com.testvco.org.co.backendtest.dto.ResponseDto;
import com.testvco.org.co.backendtest.dto.core.ActividadesDto;
import com.testvco.org.co.backendtest.servicios.interfaces.core.ActividadesServicios;
import com.testvco.org.co.backendtest.utils.Constans;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Clase de ActividadesController que contiene los microservios a
 * exponer para el Front-End
 *
 * @Author 2023-02-28.dfy
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/v1/actividad/")
@Api(value = "Microservicios para el manejo de los datos de actividades ACTIVIDAD")
public class ActividadesController {

    @Autowired
    ActividadesServicios _actividadService;

    @GetMapping(path = "/getAll")
    @ApiOperation(value = "Listar las actividades", notes = "Retorna una lista de actividad")
    public ResponseEntity<ResponseDto> GetAllActividad() {
        ResponseDto _responseDto = new ResponseDto();
        _responseDto.setCodigoRespuesta(200);
        _responseDto.setMensaje(Constans.MSG_REGISTROS_LISTADOS_CON_EXITO);
        _responseDto.setData(_actividadService.getAllActividad());
        return new ResponseEntity<ResponseDto>(_responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "/findByIdActividad/{idActividad}")
    @ApiOperation(value = "Consulta el actividad por id")
    public ResponseEntity<ResponseDto> findByIdActividad(@PathVariable Integer idActividad) {
        ResponseDto _responseDto = new ResponseDto(Constans.MSG_REGISTRO_ENCONTRADO_CORRECTAMENTE, 200,
                _actividadService.getByIdActividad(idActividad));
        return new ResponseEntity<ResponseDto>(_responseDto, HttpStatus.OK);
    }

    @GetMapping(path = "/getAllActivos")
    @ApiOperation(value = "Listar las actividades que estan activos", notes = "Retorna una lista de actividades activos")
    public ResponseEntity<ResponseDto> getAllActivosActividad() {
        ResponseDto _responseDto = new ResponseDto();
        _responseDto.setCodigoRespuesta(200);
        _responseDto.setMensaje(Constans.MSG_REGISTROS_LISTADOS_CON_EXITO);
        _responseDto.setData(_actividadService.getAllActivosActividad());
        return new ResponseEntity<>(_responseDto, HttpStatus.OK);
    }

    @PostMapping(path = "/addActividad")
    @ApiOperation(value = "Agrega el actividad", notes = "Retorna una instancia de actividad")
    public ResponseEntity<ResponseDto> addActividad(@RequestBody ActividadesDto itemActividadDto) {
        ResponseDto _responseDto = new ResponseDto();
        ActividadesDto actividadDtoResp = _actividadService.saveActividad(itemActividadDto);
        _responseDto.setCodigoRespuesta(200);
        _responseDto.setMensaje(Constans.MSG_REGISTRO_CREADO_CON_EXITO);
        _responseDto.setData(actividadDtoResp);
        return new ResponseEntity<ResponseDto>(_responseDto, HttpStatus.OK);
    }

    @PutMapping(path = "/updateActividad/{idActividad}")
    @ApiOperation(value = "Actualiza el actividad", notes = "Retorna una instancia de actividad")
    public ResponseEntity<ResponseDto> updateActividad(@PathVariable Integer idActividad,
                                                       @RequestBody ActividadesDto itemActividadDto) {
        ResponseDto _responseDto = new ResponseDto();
        ActividadesDto actividadDtoResp = _actividadService.updatedActividad(idActividad,
                itemActividadDto);
        _responseDto.setCodigoRespuesta(200);
        _responseDto.setMensaje(Constans.MSG_REGISTRO_ACTUALIZADO_CON_EXITO);
        _responseDto.setData(actividadDtoResp);
        return new ResponseEntity<ResponseDto>(_responseDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteActividad/{idActividad}")
    @ApiOperation(value = "Elimina el actividad por el id")
    public ResponseEntity<ResponseDto> deleteByIdActividad(@PathVariable Integer idActividad) {
        ResponseDto _responseDto = new ResponseDto();
        _responseDto.setCodigoRespuesta(200);
        _responseDto.setMensaje(Constans.MSG_REGISTRO_ELIMINADO_CON_EXITO);
        _responseDto.setData(idActividad);
        _actividadService.deleteActividad(idActividad);
        return new ResponseEntity<ResponseDto>(_responseDto, HttpStatus.OK);
    }
}