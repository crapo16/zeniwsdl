package ar.com.zeni.common;

/**
 *
 * @author ogagliano
 *
 */
public class ZeniConstants {

	public static final String id_mercado_matba = "-6";
	public static final String id_moneda_ARG = "1717";
	public static final String id_mercado_rofex = "-9";
	public static final String COMPROBANTE_DV = "DV";
	public static final String COMPROBANTE_CV = "CV";
	public static final String COMPROBANTE_LF = "LF";
	public static final String COMPROBANTE_LP = "LP";

	public interface WEBINFORMACION {
		String TABLE_NAME = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_MAIL_INFORMACION";
		String ID_MAIL_INFORMACION = "ID_MAIL_INFORMACION";
		String MAIL_BODY = "MAIL_BODY";
		String MAIL_FROM = "MAIL_FROM";
		String MAIL_TO = "MAIL_TO";
		String STATUS = "STATUS";
		String RETRIES = "RETRIES";
		String USER_INSERT = "USER_INSERT";
		String INSERT_DATE = "INSERT_DATE";
	}

	public interface WEB_SOLICITUD_INFORMACION {
		String TABLE_NAME = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_SOLICITUD_INFORMACION";
		String ID_MAIL_INFORMACION = "ID_SOLICITUD_INFORMACION";
		String CLIENTE_WEB_ID = "CLIENTE_WEB_ID";
		String USUARIO = "USUARIO";
		String MAIL_TO = "MAIL_TO";
		String AVISO_NEGOCIO_COMPRADOR = "AVISO_NEGOCIO_COMPRADOR";
		String AVISO_NEGOCIO_VENDEDOR = "AVISO_NEGOCIO_VENDEDOR";
		String BOLETOS_COMPRADOR = "BOLETOS_COMPRADOR";
		String BOLETOS_VENDEDOR = "BOLETOS_VENDEDOR";
		String CUPOS_COMPRADOR = "CUPOS_COMPRADOR";
		String CUPOS_VENDEDOR = "CUPOS_VENDEDOR";
		String DOC_IMPOSITIVO = "DOC_IMPOSITIVO";
		String INFO_DIGITAL = "INFO_DIGITAL";
		String RETENCION_COMPROBANTES = "RETENCION_COMPROBANTES";
		String FIJACIONES = "FIJACIONES";
		String HABILITACION_BOLSA = "HABILITACION_BOLSA";
		String INFORME_DIARIO = "INFORME_DIARIO";
		String TENENCIA_VALORIZADA = "TENENCIA_VALORIZADA";
		String MAT = "MAT";
		String OP_FCI = "OP_FCI";
		String ORDEN_CARGA = "ORDEN_CARGA";
		String USER_INSERT = "USER_INSERT";
		String UPDATE_DATE = "UPDATE_DATE";
		String INSERT_DATE = "INSERT_DATE";
		String CUENTA_ID = "CUENTA_ID";
	}

	public enum ENVIO_MAIL_TIPO {
		Clientes("L"), Habilitacion_Bolsa("H"), Nomina("N"), Aviso_Negocio("C"), Fijaciones("F"), Informe_Diario("I"),
		Informe_Tenencia_Valorizada("T"), Cupos("U"), Retenciones_Comprobantes("R"), Mercado_Termino("M"),
		Orden_Carga("O"), Operaciones_FCI("V"), Boletos("B"), Doc_Impositiva("D"), Informacion_Digital("G");

		public final String VALUE;

		ENVIO_MAIL_TIPO(String codigo) {
			VALUE = codigo;
		}
	}

}
