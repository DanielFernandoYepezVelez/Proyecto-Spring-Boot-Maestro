package com.testvco.org.co.backendtest.excepciones;

import com.testvco.org.co.backendtest.utils.Constans;

@SuppressWarnings("serial")
public class DocumentoAlreadyExists extends RuntimeException {

	public DocumentoAlreadyExists() {
		super(Constans.ERROR_DOCUMENTO_YA_EXISTE);
	}

	public DocumentoAlreadyExists(String msg) {
		super(msg);
	}
}
