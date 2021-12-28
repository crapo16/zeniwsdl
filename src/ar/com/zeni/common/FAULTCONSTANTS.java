package ar.com.zeni.common;

public interface FAULTCONSTANTS {
	String	PASSWORD_ERRONEO						= "0";
	String	ERROR_ERRONEO							= "1";
	String	CONTEXT_ERROR							= "2";
	String	CONTEXT_HASH_NO_VALIDO					= "3";
	String	IP_ACTUAL_DIFIERE						= "4";
	String	ERROR_DE_CODEC							= "5";
	String	MAIL_ERROR								= "6";
	String	ERROR_LEYENDO_CODIGO_AUTORIZACION		= "13";
	String	ERROR_PARSE_FECHA						= "18";
	String	ERROR_CREANDO_CONEXION					= "45";
	String	CONTEXT_AUTH_NO_VALIDO					= "46";
	String	DB_ERROR								= "109";
	String	USUARIO_NO_EXISTE						= "324";
	String	CODIGO_DE_AUTORIZACION_REENVIADO		= "456";
	String	CODIGO_DE_AUTORIZACION_INVALIDO			= "556";
	String	CONTEXT_AUTHTYPE_NO_VALIDO				= "741";
	String	USUARIO_EXISTENTE						= "785";
	String	PASSWORD_ERRONEO_MSG					= "Usuario o password erroneo.";
	String	CUENTA_DESABILITADA_ERRONEO_MSG			= "Usuario o cuenta deshabilitada.";
	String	CONTEXT_ERROR_MSG						= "Error Resolviendo o inicializando el contexto.";
	String	CONTEXT_HASH_NO_VALIDO_MSG				= "El hash ha caducado o no es valido.";
	String	CONTEXT_AUTH_NO_VALIDO_MSG				= "El hash ha caducado o no es valido.";
	String	IP_ACTUAL_DIFIERE_MSG					= "El ip de autorizacion no coincide con el ip guardado, esta utilizando una pc distinta? reutilizando el hash?";
	String	ERROR_DE_CODEC_MSG						= "El codec no es compatible, no se puede generar ni descomprimir";
	String	MAIL_ERROR_MSG							= "Error enviando mail";
	String	ERROR_PARSE_FECHA_MSG					= "Error parseando fecha de autorizacion.";
	String	ERROR_CREANDO_CONEXION_MSG				= "Error creando conexion";
	String	ERROR_LEYENDO_CODIGO_AUTORIZACION_MSG	= "Error leyendo en base de datos el codigo de autorizacion";
	String	CODIGO_DE_AUTORIZACION_REENVIADO_MSG	= "Codigo de autorizacion ya ha sido enviado, revise su correo por favor.";
	String	CODIGO_DE_AUTORIZACION_INVALIDO_MSG		= "El codigo de autorizacion es invalido o esta vencido. Realice la peticion de un codigo de autorizacion nuevamente";
	String	USUARIO_EXISTENTE_MSG					= "El usuario ya existe";
	String	USUARIO_NO_EXISTE_MSG					= "No se encuentra el usuario";
	String	DB_ERROR_MSG							= "Ocurrio un error ejecutando la query: ";
	String	CONTEXT_AUTHTYPE_NO_VALIDO_MSG			= "El objeto de autorizacion no es valido, reintente loguearse.";
}
