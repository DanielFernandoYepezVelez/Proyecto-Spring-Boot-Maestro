package com.testvco.org.co.backendtest.excepciones;

import java.time.LocalDateTime;

import com.testvco.org.co.backendtest.excepciones.TokenExpiradoException;
import com.testvco.org.co.backendtest.dto.ResponseDto;
import com.testvco.org.co.backendtest.utils.Constans;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Clase de controlador de mensajes que interactua con la implementacion del servicio y el controller
 * 
 * @Author 2023-02-24.celf
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class })
    public ResponseEntity<ResponseDto> handleConstraintViolationException(Exception ex, WebRequest request){
        ResponseDto _dto = new ResponseDto();
        _dto.setCodigoRespuesta(HttpStatus.CONFLICT.value());
        _dto.setMensaje(Constans.ERROR_REGISTRO_DUPLLICADO);
        _dto.setData(LocalDateTime.now());
        return new ResponseEntity<ResponseDto>(_dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoRecordsAvailable.class)
    public ResponseEntity<ResponseDto> handleNoRecordsAvailableException(NoRecordsAvailable ex, WebRequest request){
        ResponseDto _dto = new ResponseDto();
        _dto.setCodigoRespuesta(HttpStatus.NOT_FOUND.value());
        _dto.setMensaje(ex.getMessage());
        _dto.setData(null);
        return new ResponseEntity<ResponseDto>(_dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ResponseDto> handleNoDataFoundException(NoDataFoundException ex, WebRequest request){
        ResponseDto _dto = new ResponseDto();
        _dto.setCodigoRespuesta(HttpStatus.NOT_FOUND.value());
        _dto.setMensaje(ex.getMessage());
        _dto.setData(null);
        return new ResponseEntity<ResponseDto>(_dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameAlreadyExists.class)
    public ResponseEntity<ResponseDto> handleNameAlreadyExistsException(NameAlreadyExists ex, WebRequest request){
        ResponseDto _dto = new ResponseDto();
        _dto.setCodigoRespuesta(HttpStatus.NOT_ACCEPTABLE.value());
        _dto.setMensaje(ex.getMessage());
        _dto.setData(null);
        return new ResponseEntity<ResponseDto>(_dto, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(WrongIdentifiers.class)
    public ResponseEntity<ResponseDto> handleWrongIdentifiersException(WrongIdentifiers ex, WebRequest request){
        ResponseDto _dto = new ResponseDto();
        _dto.setCodigoRespuesta(HttpStatus.NOT_IMPLEMENTED.value());
        _dto.setMensaje(ex.getMessage());
        _dto.setData(null);
        return new ResponseEntity<ResponseDto>(_dto, HttpStatus.NOT_IMPLEMENTED);
    }
	
    @ExceptionHandler(NameAndTipoListaAlreadyExists.class)
    public ResponseEntity<ResponseDto> handleNameAndTipoListaAlreadyExistsException(NameAndTipoListaAlreadyExists ex, WebRequest request){
        ResponseDto dto = new ResponseDto(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),null);
        return new ResponseEntity<ResponseDto>(dto, HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(ErrorFormato.class)
    public ResponseEntity<ResponseDto> manejarExcepcionFormato(ErrorFormato ex, WebRequest request){
        ResponseDto _dto = new ResponseDto(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE.value(),null);
        return new ResponseEntity<ResponseDto>(_dto, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoDataDigitizedException.class)
    public ResponseEntity<ResponseDto> handleNoDataDigitizedException(NoDataDigitizedException ex, WebRequest request){
        ResponseDto _dto = new ResponseDto();
        _dto.setCodigoRespuesta(HttpStatus.NOT_IMPLEMENTED.value());
        _dto.setMensaje(ex.getMessage());
        _dto.setData(null);
        return new ResponseEntity<ResponseDto>(_dto, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(DocumentoAlreadyExists.class)
    public ResponseEntity<ResponseDto> handleDocumentoAlreadyExistsException(DocumentoAlreadyExists ex, WebRequest request){
        ResponseDto _dto = new ResponseDto();
        _dto.setCodigoRespuesta(HttpStatus.NOT_ACCEPTABLE.value());
        _dto.setMensaje(ex.getMessage());
        _dto.setData(null);
        return new ResponseEntity<ResponseDto>(_dto, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(TokenExpiradoException.class)
    public ResponseEntity<ResponseDto> handleTokenExpiradoException(TokenExpiradoException ex, WebRequest request){
        ResponseDto dto = new ResponseDto(ex.getMessage(),HttpStatus.UNAUTHORIZED.value(),null);
        return new ResponseEntity<ResponseDto>(dto, HttpStatus.UNAUTHORIZED);
    }

}