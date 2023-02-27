package com.testvco.org.co.backendtest.utils;

/**
 * Esta clase contiene las constantes que seran utilizadas en la aplicación
 * 
 * @Author 2023-02-24.celf
 */
public class Constans {

	/* Generic System */
	public static final String AGREGAR = "Agregar";
	public static final String ACTUALIZAR = "Actualizar";
	public static final String ELIMINAR = "Eliminar";
	public static final String USUARIO = "Usuario";
	public static final String ROL = "Rol";
	public static final String EMAIL = "Email";
	public static final String YA_EXISTE = "ya existe";
	public static final String NO_EXISTE = "no existe";
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer";
	public static final String NO_AUTORIZADO = "No autorizado";

	// Mensajes
	public static String MSG_REGISTROS_LISTADOS_CON_EXITO = "Registros listados con exito";
	public static String MSG_REGISTRO_CREADO_CON_EXITO = "Registro creado con exito";
	public static String MSG_REGISTRO_ACTUALIZADO_CON_EXITO = "Registro actualizado con exito";
	public static String MSG_REGISTRO_ELIMINADO_CON_EXITO = "Registro eliminado con exito";
	public static String MSG_REGISTRO_NO_ENCONTRADO = "Registro no encontrado...";
	public static String MSG_REGISTRO_ENCONTRADO_CORRECTAMENTE = "Registro encontrado correctamente";

	// Errores
	public static String ERROR_NOMBRE_YA_EXISTE = "El nombre ya existe";
	public static String ERROR_DOCUMENTO_YA_EXISTE = "El documento ya existe";
	public static String ERROR_NOMBRE_Y_TIPOLISTA_YA_EXISTE = "La relación nombre y tipoLista ya existe";
	public static String ERROR_REGISTRO_DUPLLICADO = "Registro duplicado o violacion de la integridad de la informacion";
	public static String ERROR_IDENTIFICADORES_INCORRECTOS = "Identificadores incorrectos";
	public static String ERROR_NO_EXISTEN_REGISTROS_DISPONIBLES = "No existen registros disponibles";
	public static String ERROR_CREAR_AUTENTICACION_USUARIO = "No es posible crear la autenticacion para el usuario";
	public static String ERROR_FORMATO = "El formato no corresponde al requerido";
	public static String ERROR_GENERAL = "Ocurrio un error en el sistema";
	public static String ERROR_NO_EXISTE = "No existe un registro con {0}: {1}";
	public static String ERROR_FALTA_DATO_OBIGATORIO = "Falta ingresar datos";

	// Login
	public static String LOGGER_TOKEN_INVALIDO_POR_FIRMA = "Token invalido por la firma";
	public static String LOGGER_TOKEN_INVALIDO = "Token Invalido";
	public static String LOGGER_TOKEN_EXPIRADO = "Token expirado";
	public static String LOGGER_TOKEN_NO_SOPORTADO = "Token no es soportado";
	public static String LOGGER_JWT_CLAIMS_VACIO = "JWT claims esta vacio";

}
