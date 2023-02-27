package com.testvco.org.co.backendtest.excepciones;

import com.testvco.org.co.backendtest.utils.Constans;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException(){
        super(Constans.MSG_REGISTRO_NO_ENCONTRADO);
    }
}
