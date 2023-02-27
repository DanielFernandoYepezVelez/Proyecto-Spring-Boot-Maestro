package com.testvco.org.co.backendtest.utils;

import java.util.List;

/**
 * Clase de funciones genericas usadas en general
 *
 * @Author 2023-02-24.celf
 */
public class Generics {

	/**
	 * Este metodo permite validar si un objeto es nulo o vacio entre varios
	 * escenarios.
	 * 
	 * @Author 2021-12-04.ghp (GerardoHerrera)
	 * 
	 * @param o: objeto a validar.
	 * @return boolean: True si el objeto es nulo o vacio, false en caso contrario.
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmptyGeneral(Object o) {
		if (o != null) {
			if (o instanceof List) {
				return ((List) o).isEmpty();
			}
			if (o instanceof String) {
				return "".equals(o);
			}
			return false;
		}
		return true;
	}

	/**
	 * Este metodo permite validar si el campo es nulo o esta vacio.
	 * 
	 * @param str: Parametro para conocer si es nulo o esta vacio.
	 * @return boolean: True si el parametro es nulo o vacio, false en caso
	 *         contrario.
	 */
	public static boolean isNullOrEmpty(Object str) {
		return str == null || "".equals(str);
	}

	/**
	 * Este metodo permite validar si el campo de tipo lista es nulo o esta vacio.
	 * El metodo .isEmpty() y .size() == 0 son lo mismo.
	 * 
	 * @param list: Lista para conocer si es nula o esta vacia.
	 * @return boolean: True si la lista es nula o vacia, false en caso contrario.
	 */
	public static boolean isNullOrEmpty(List<?> list) {
		return list == null || list.isEmpty();
	}

}
