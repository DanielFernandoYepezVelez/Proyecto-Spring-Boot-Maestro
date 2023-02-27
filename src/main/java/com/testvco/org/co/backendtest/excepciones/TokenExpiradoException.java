package com.testvco.org.co.backendtest.excepciones;

@SuppressWarnings("serial")
public class TokenExpiradoException extends RuntimeException {

	public TokenExpiradoException(String mensaje) {
		super(mensaje);
	}

}
