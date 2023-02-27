package com.testvco.org.co.backendtest.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Modelo Dto para la respuesta generica del microservicio")
public class ResponseDto {

	private String mensaje;
	private int codigoRespuesta;
	private Object data;

	public ResponseDto() {
	}

	public ResponseDto(String mensaje, int codigoRespuesta, Object data) {
		this.mensaje = mensaje;
		this.codigoRespuesta = codigoRespuesta;
		this.data = data;
	}

}
