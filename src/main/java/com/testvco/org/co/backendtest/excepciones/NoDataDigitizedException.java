package com.testvco.org.co.backendtest.excepciones;

import com.testvco.org.co.backendtest.utils.Constans;

public class NoDataDigitizedException extends RuntimeException{

    public NoDataDigitizedException(){
        super(Constans.ERROR_FALTA_DATO_OBIGATORIO);
    }

	public NoDataDigitizedException(String msg) {
		super(msg);
	}
}