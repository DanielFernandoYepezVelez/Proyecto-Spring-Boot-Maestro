package com.testvco.org.co.backendtest.servicios.impl.configuracion;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

/**
 * Esta clase se encarga de encriptar cualquier tipo de cadena que sea requerida
 * por temas relacionados con seguridad.
 * 
 * @Author 2023-02-24.celf
 */
@Service
public class EncriptacionServicio {

	@Value("${testv.app.cadenaSeguraUno}")
	String cadenaSeguraUno;

	@Value("${testv.app.cadenaSeguraDos}")
	String cadenaSeguraDos;

	private CharSequence convertStringToHex(String str) {
		char[] chars = new char[0];
		if (str != null) {
			chars = Hex.encode(str.getBytes(StandardCharsets.UTF_8));
			return String.valueOf(chars);
		}
		return String.valueOf(chars);
	}

	public String encode(String cadenaAEncriptar) {
		TextEncryptor encryptor = Encryptors.text(cadenaSeguraUno, convertStringToHex(cadenaSeguraDos));
		return encryptor.encrypt(cadenaAEncriptar);
	}

	public String decode(String cadenaEncriptada) {
		TextEncryptor encryptor = Encryptors.text(cadenaSeguraUno, convertStringToHex(cadenaSeguraDos));
		return encryptor.decrypt(cadenaEncriptada);
	}

}
