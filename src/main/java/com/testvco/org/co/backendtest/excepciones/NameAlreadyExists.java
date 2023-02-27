package com.testvco.org.co.backendtest.excepciones;

import com.testvco.org.co.backendtest.utils.Constans;

@SuppressWarnings("serial")
public class NameAlreadyExists extends RuntimeException {

	public NameAlreadyExists() {
		super(Constans.ERROR_NOMBRE_YA_EXISTE);
	}

	public NameAlreadyExists(String msg) {
		super(msg);
	}
}
