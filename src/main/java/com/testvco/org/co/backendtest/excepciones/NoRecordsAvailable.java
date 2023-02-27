package com.testvco.org.co.backendtest.excepciones;

import com.testvco.org.co.backendtest.utils.Constans;

public class NoRecordsAvailable extends RuntimeException{
    public NoRecordsAvailable(){
        super(Constans.ERROR_NO_EXISTEN_REGISTROS_DISPONIBLES);
    }
}
