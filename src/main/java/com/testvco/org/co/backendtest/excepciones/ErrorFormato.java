package com.testvco.org.co.backendtest.excepciones;

import com.testvco.org.co.backendtest.utils.Constans;

@SuppressWarnings("serial")
public class ErrorFormato extends RuntimeException {

	public ErrorFormato() {
		super(Constans.ERROR_FORMATO);
	}
	
	public ErrorFormato(String mensaje) {
		super(mensaje);
	}

}
