package com.testvco.org.co.backendtest.excepciones;

import com.testvco.org.co.backendtest.utils.Constans;

public class NameAndTipoListaAlreadyExists  extends RuntimeException{
    public NameAndTipoListaAlreadyExists(){
        super(Constans.ERROR_NOMBRE_Y_TIPOLISTA_YA_EXISTE);
    }
}
