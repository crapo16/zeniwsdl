package ar.com.zeni.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import ar.com.zeni.codecs.GenericCodec;
import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.FAULTCONSTANTS;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniBaseExeption;
import ar.com.zeni.common.exceptions.ZeniCodigoDeAutorizacionEnviado;
import ar.com.zeni.common.exceptions.ZeniCodigoDeAutorizacionInvalido;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.db.ZeniInsertBuilder;
import ar.com.zeni.db.ZeniQueryBuilder;
import ar.com.zeni.db.ZeniQueryBuilder.Operator;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniUpdateBuilder;

public class CodigoDeAutorizacionUtil {
    public String obtenerCodigoDeAutorizacion(String clienteid, String email) throws ZeniDBExeption {
    	CodigoDeAutorizacion authcode;
    	// si hay alguno
    	authcode = getCodigoDeAutorizacionDB(clienteid, email);
    	if (authcode!=null){
    		// si esta vencido
    		if (!authcode.isValid()) {
    			//borro y genero uno nuevo.
            	authcode = getCodigoDeAutorizacion(clienteid, email);
    		}
    	} else {
			//borro y genero uno nuevo.
        	authcode = getCodigoDeAutorizacion(clienteid, email);
    	}
		return authcode.getCodigoDeAutorizacionEnString();
    }

    public boolean validarCodigoDeAutorizacion( String clienteId, String email, String codigoAuth) throws ZeniDBExeption, ZeniCodigoDeAutorizacionInvalido {
    	// levanto el de la base
    	CodigoDeAutorizacion authcode = getCodigoDeAutorizacionDB(clienteId, email);
    	if (authcode!=null){
    		// si esta vencido
    		if (authcode.getCodigoDeAutorizacionEnString().equals(codigoAuth)){
    			if (authcode.isValid()){
    				return true;
    			}
    		}
    	}
    	throw new ZeniCodigoDeAutorizacionInvalido(new ZeniBaseExeption("El codigo es invalido o esta vencido, solicite un nuevo codigo.", "0"));
    }
	private CodigoDeAutorizacion getCodigoDeAutorizacion(String clienteId, String email) throws ZeniDBExeption {
		//generar el codigo de autorizacion.
		//actualizar la base.
		String fechaExp = DateUtil.ToString.yyyyddMMSSSssHHmm(new Date(new Date().getTime() + DateUtil.DateMetrics.diaenmillis));
		String authCode = GenericCodec.stringToBase64(fechaExp+clienteId);
		CodigoDeAutorizacion codRtn = new CodigoDeAutorizacion(authCode, fechaExp);
		String instmt = new ZeniInsertBuilder().insertinsertinto(CodigoDeAutorizacionTable.TABLE_NAME)
				.fields(CodigoDeAutorizacionTable.CLIENTE_ID_FIELD, CodigoDeAutorizacionTable.MAIL_FIELD, CodigoDeAutorizacionTable.CODIGO_AUT_FIELD, CodigoDeAutorizacionTable.FECHA_EXPIRACION_FIELD)
				.values(clienteId, email, authCode, fechaExp)
				.getInsertQuery();
		try {
			executeInsert(instmt);
		} catch (ZeniCodigoDeAutorizacionEnviado e) {
			String upstm = new ZeniUpdateBuilder().update(CodigoDeAutorizacionTable.TABLE_NAME)
					.set(CodigoDeAutorizacionTable.CODIGO_AUT_FIELD, authCode)
					.coma(CodigoDeAutorizacionTable.FECHA_EXPIRACION_FIELD, fechaExp)
					.where(CodigoDeAutorizacionTable.CLIENTE_ID_FIELD, Operator.EQUALS, clienteId)
					.and(CodigoDeAutorizacionTable.MAIL_FIELD, Operator.EQUALS, email)
					.getupdateQuery();
			executeUpdate(upstm);
		}
		return codRtn;
	}

	private CodigoDeAutorizacion getCodigoDeAutorizacionDB(String clienteId, String email) throws ZeniDBExeption {
		//buscar en la base.
		String authCode = ZeniContextServer.VACIO;
		String authDate = ZeniContextServer.VACIO;
		String query = new ZeniQueryBuilder().select(CodigoDeAutorizacionTable.CLIENTE_ID_FIELD, CodigoDeAutorizacionTable.MAIL_FIELD, CodigoDeAutorizacionTable.CODIGO_AUT_FIELD, CodigoDeAutorizacionTable.FECHA_EXPIRACION_FIELD)
						.from(CodigoDeAutorizacionTable.TABLE_NAME)
						.where(CodigoDeAutorizacionTable.CLIENTE_ID_FIELD, Operator.EQUALS, clienteId)
						.and(CodigoDeAutorizacionTable.MAIL_FIELD, Operator.EQUALS, email)
						.getQuey();
		Connection conn = null;
		CodigoDeAutorizacion codRtn = null;
		if (conn == null)	{
			
		}
		try {
			conn = ZeniContextServer.getInstance().getConnectionGetter().getConnection();
			PreparedStatement slstmt = null;
			try {
				slstmt = conn.prepareStatement(query);
				ResultSet rset = slstmt.executeQuery();
				if (rset.next()) {
					authCode = rset.getString(CodigoDeAutorizacionTable.CODIGO_AUT_FIELD);
					authDate = rset.getString(CodigoDeAutorizacionTable.FECHA_EXPIRACION_FIELD);
					codRtn = new CodigoDeAutorizacion(authCode, authDate);
				} else {
				}
			} catch (final SQLException e1) {
			} finally {
				try {
					slstmt.close();
				} catch (final SQLException e) {
					ZeniContextServer.getInstance().printInfoLog("Cant close rset: " + query);
					e.printStackTrace();
				}
			}
		} catch (final SQLException e2) {
			throw new ZeniDBExeption(FAULTCONSTANTS.ERROR_CREANDO_CONEXION_MSG, FAULTCONSTANTS.ERROR_CREANDO_CONEXION, e2);
		} finally {
			try {
				conn.close();
			} catch (final SQLException e) {
				ZeniContextServer.getInstance().printInfoLog("Cant close rset: " + query);
				e.printStackTrace();
			}
		}
		return codRtn;
	}
	private void executeUpdate(String upstm) throws ZeniDBExeption {
		ZeniQueryExcecutor.excecuteUpdate(upstm);
	}
	private static void executeInsert( String insertStmt) throws ZeniDBExeption, ZeniCodigoDeAutorizacionEnviado {
		Connection con;
		try {
			con = ZeniContextServer.getInstance().getConnectionGetter().getConnection();
		} catch (SQLException e) {
			ZeniContextServer.getInstance().printInfoLog("Can't get con to DBase: " + e.getMessage());
			throw new ZeniDBExeption("Error creando conexion", FAULTCONSTANTS.DB_ERROR, e);
		}
		try {
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement(insertStmt);
			} catch (SQLException e) {
				ZeniContextServer.getInstance().printInfoLog("Can't create stmt: " + e.getMessage());
				throw e;
			}
	
			try {
				stmt.executeUpdate();
			} catch (SQLException e) {
				//// error en base de datos leyendo las propiedades del mail.
				//e.printStackTrace();
				ZeniContextServer.getInstance().printInfoLog("Can't insert in DBase: " + e.getMessage() + " sql : " + insertStmt);
				if (e.getErrorCode()==1){
					throw new ZeniCodigoDeAutorizacionEnviado(e);
				}
				throw new ZeniDBExeption(FAULTCONSTANTS.ERROR_LEYENDO_CODIGO_AUTORIZACION_MSG, FAULTCONSTANTS.ERROR_LEYENDO_CODIGO_AUTORIZACION, e);
			} finally {
				stmt.close();
			}
		} catch (SQLException e) {
		} finally {
			try { con.commit(); con.close(); } catch (Exception e2){}
		}
	}
	interface CodigoDeAutorizacionTable {
		final String	TABLE_NAME				= ZeniContextServer.getInstance().getDBWebOwner() + "WEB_CODIGOS_AUTORIZACION";
		final String	MAIL_FIELD				= "EMAIL_CLIENTE";
		final String	CLIENTE_ID_FIELD		= "CLIENTE_ID";
		final String	CODIGO_AUT_FIELD		= "CODIGO_AUTH";
		final String	FECHA_EXPIRACION_FIELD	= "EXPIRATION_DATE";
	}
	class CodigoDeAutorizacion {
		private final String	codi;
		private final String	fechi;
		CodigoDeAutorizacion(String codi, String fechi){
			this.codi = codi;
			this.fechi = fechi;
		}
		public boolean isValid(){
			try {
				return DateUtil.Parsers.yyyyddMMSSSssHHmm(fechi).compareTo(new Date())>0;
			} catch (ParseException e) {
			}
			return false;
		}
		public String getCodigoDeAutorizacionEnString(){
			return this.codi;
		}
	}
}
