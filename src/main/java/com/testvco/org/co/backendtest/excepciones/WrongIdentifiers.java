package com.testvco.org.co.backendtest.excepciones;

import com.testvco.org.co.backendtest.utils.Constans;

public class WrongIdentifiers  extends RuntimeException{
    public WrongIdentifiers(){
        super(Constans.ERROR_IDENTIFICADORES_INCORRECTOS);
    }
}
