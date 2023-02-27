package com.testvco.org.co.backendtest.pojo.configuracion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValidacionesPojo<T> {

	private T dto;
	private String[] metodoDto;
	private String metodoRepo;
	private String mensaje;
	private boolean existe;

	public ValidacionesPojo(String metodoRepo, String mensaje, T dto, String... metodoDto) {
		this.dto = dto;
		this.metodoDto = metodoDto;
		this.mensaje = mensaje;
		this.metodoRepo = metodoRepo;
		this.existe = false;
	}

	public ValidacionesPojo(String metodoRepo, String mensaje, T dto, boolean existe, String... metodoDto) {
		this.dto = dto;
		this.metodoDto = metodoDto;
		this.mensaje = mensaje;
		this.metodoRepo = metodoRepo;
		this.existe = existe;
	}

}
