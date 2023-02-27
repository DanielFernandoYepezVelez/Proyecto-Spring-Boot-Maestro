package com.testvco.org.co.backendtest.excepciones;

import com.testvco.org.co.backendtest.utils.Constans;

@SuppressWarnings("serial")
public class ErrorGeneral extends RuntimeException {

	public ErrorGeneral() {
		super(Constans.ERROR_GENERAL);
	}
	
	public ErrorGeneral(String mensaje) {
		super(mensaje);
	}
	
}
