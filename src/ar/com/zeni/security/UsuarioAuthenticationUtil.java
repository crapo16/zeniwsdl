package ar.com.zeni.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ar.com.zeni.codecs.GenericCodec;
import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.FAULTCONSTANTS;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.ZeniContextServer.APPL_SECURITY_PROPERTIES;
import ar.com.zeni.common.exceptions.ZeniAuthTypeInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniBaseExeption;
import ar.com.zeni.common.exceptions.ZeniCodigoDeAutorizacionInvalido;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.common.exceptions.ZeniHashInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniServerExeption;
import ar.com.zeni.common.exceptions.ZeniUserPasswordErroneo;
import ar.com.zeni.common.exceptions.ZeniUserRecibeAtencionComercialErroneo;
import ar.com.zeni.db.ZeniDeleteBuilder;
import ar.com.zeni.db.ZeniInsertBuilder;
import ar.com.zeni.db.ZeniQueryBuilder;
import ar.com.zeni.db.ZeniQueryBuilder.Operator;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.db.ZeniUpdateBuilder;
import ar.com.zeni.security.UsuarioAuthenticationUtil.USERTABLE;
import ar.com.zeni.security.UsuarioAuthenticationUtil.UserData;
import ar.com.zeni.zeniwsdl.AuthType;

public class UsuarioAuthenticationUtil {

	public static final String IS_NUMBER = "^[0-9]+$";
	
	private UsuarioWordPressUtil userWP = new UsuarioWordPressUtil();

	public AuthType autorizarUsuario(String usuario, String hash, String password)
			throws ZeniUserPasswordErroneo, ZeniServerExeption, ZeniAuthTypeInvalidExeption, ZeniDBExeption,
			ZeniHashInvalidExeption, ZeniUserRecibeAtencionComercialErroneo {
		try {
			UserData ud = getUserData(usuario);
			String seed = findSeed4Hash(hash);
			if (isPasswordValid(password, seed, ud.getDBPassword(), ud.getDBSalt())) {

				if (validarClienteDadoBaja(ud)) {

					int valor = validarRecibeAtencionComercial(ud);

					if (valor == 0) {
						ZeniContextServer.getInstance()
								.printInfoLog("Cliente deshabilitada por recibeatencioncomercial.");
						throw new ZeniUserRecibeAtencionComercialErroneo();
					} else {
						AuthType a = AuthNHashUtil.getInstance().generateAuth(hash, usuario);
						return a;
					}
				} else {
					ZeniContextServer.getInstance()
							.printInfoLog("Usuario {" + usuario + "} tiese asociado un cliente dado de baja");
					throw new ZeniUserRecibeAtencionComercialErroneo();
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw new ZeniServerExeption(FAULTCONSTANTS.ERROR_DE_CODEC_MSG, FAULTCONSTANTS.ERROR_DE_CODEC, e);
		} catch (NoSuchAlgorithmException e) {
			throw new ZeniServerExeption(FAULTCONSTANTS.ERROR_DE_CODEC_MSG, FAULTCONSTANTS.ERROR_DE_CODEC, e);
		}
		throw new ZeniUserPasswordErroneo();
	}

	/**
	 *
	 * @param ud
	 * @return
	 * @throws ZeniDBExeption
	 */
	private int validarRecibeAtencionComercial(final UserData ud) throws ZeniDBExeption {

		int valido = -1;
		StringBuffer sql = new StringBuffer();
		try {

			sql.append(" SELECT COUNT(*) AS cantidad, min(recibeatencioncomercial) AS atencion ");
			sql.append(" FROM CUENTA_CLIENTE CC ");
			sql.append(" INNER JOIN CLIENTE C ON (CC.CLIENTE_ID = C.ID) ");
			sql.append(" WHERE cc.fechabaja IS NULL ");
			sql.append(" AND cc.id IN ( SELECT df.CUENTA_CLIENTE_ID ");
			sql.append(" FROM WEB_USUARIO_CUENTA df ");
			sql.append(" WHERE df.CLIENTE_WEB_ID = " + ud.getClienteid() + ") ");
//			sql.append(" AND c.recibeatencioncomercial = 0 ");

			final List<Integer> _data = new ArrayList<Integer>();

			final ResulsetObjectBuilder resbCuenta = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {

					if (rset.next()) {
						_data.add(rset.getInt("cantidad"));
						_data.add(rset.getInt("atencion"));
					}
				}
			};

			ZeniContextServer.getInstance().printVerboLog(sql.toString());
			ZeniQueryExcecutor.excecuteSelect(sql.toString(), resbCuenta);

			if (_data != null & _data.size() > 0) {
				ZeniContextServer.getInstance()
						.printInfoLog("Cantidad Cuentas{" + _data.get(0) + "} AtencionComercial{" + _data.get(1) + "}");
				// Si tiene mas de una cuenta, no se valida cuenta desabilitada.
				if (_data.get(0) > 1)
					valido = _data.get(0);
				else {
					valido = _data.get(1);
				}
			}

		} catch (Exception e) {
			throw new ZeniDBExeption(sql.toString(), e);
		}

		return valido;
	}

	/**
	 *
	 * @param ud
	 * @return
	 * @throws ZeniDBExeption
	 */
	private boolean validarClienteDadoBaja(final UserData ud) throws ZeniDBExeption {

		boolean valido = true;
		StringBuffer sql = new StringBuffer();
		try {

			// Busco si el usuario es cuenta padre
			
			if (ud.getUsername() == null || !ud.getUsername().matches(IS_NUMBER)) {
				ZeniContextServer.getInstance()
				.printInfoLog("Cuenta Alfanumeria no se valida la cuenta padre");
				return true;
			}
			
			
			UserCuenta cuenta = isCuentaPadre(ud.getUsername());

			if (cuenta == null) {
				ZeniContextServer.getInstance()
						.printInfoLog("No se valida el usuario {" + ud.getUsername() + "} No es cuenta padre");
				return true;

			} else {

				// Valido si esta habilitado.. si como cuenta padre no esta habilitado no lo
				// dejo ingresar.

				if (!cuenta.isHabilitado()) {
					ZeniContextServer.getInstance().printInfoLog("Cliente web asociado {" + ud.getClienteid()
							+ "} Username {" + ud.getUsername() + "} No esta habilitado para operar");
					return false;
				}

			}

			// Valido las cuentas hijas, de no tener habilitadas algunas hi

			sql.append("  SELECT cc.OPERADORDEMESA_ID OPE_CLIENTE, c.OPERADORDEMESA_ID, CC.ID ID_CUENTA_CLIENTE ");
			sql.append("  FROM CUENTA_CLIENTE CC  ");
			sql.append("  INNER JOIN CLIENTE C ON (CC.CLIENTE_ID = C.ID)  ");
			sql.append("  WHERE cc.fechabaja IS NULL   ");
			sql.append("  AND cc.id IN ( SELECT df.CUENTA_CLIENTE_ID ");
			sql.append("  FROM WEB_USUARIO_CUENTA df   ");
			sql.append("  WHERE df.CLIENTE_WEB_ID = " + ud.getClienteid() + ") ");
			sql.append("  AND (cc.OPERADORDEMESA_ID ='28199031' OR  c.OPERADORDEMESA_ID ='28199031') ");
			sql.append("  AND cc.NROCUENTA != '" + ud.getUsername() + "'");

			final List<String> _data = new ArrayList<String>();

			final ResulsetObjectBuilder resbCuenta = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {

					while (rset.next()) {
						_data.add(rset.getString("ID_CUENTA_CLIENTE"));
					}
				}
			};

			ZeniContextServer.getInstance().printVerboLog(sql.toString());
			ZeniQueryExcecutor.excecuteSelect(sql.toString(), resbCuenta);

			if (_data != null & _data.size() > 0) {

				for (String id : _data) {

					ZeniContextServer.getInstance().printInfoLog("Usuario/ClienteId  {" + ud.getUsername() + "/"
							+ ud.getClienteid() + "} Cuenta  Asignado{" + id + "} se elimina.");

					// Elimino
					deleteCuentaCliente(ud.getClienteid(), id);
				}

			}

		} catch (Exception e) {
			throw new ZeniDBExeption(sql.toString(), e);
		}

		return valido;
	}

	/**
	 *
	 * @param username
	 * @param id
	 */
	private void deleteCuentaCliente(String cliente_web_id, String cuenta_Cliente_id) {
		try {

			String delete = "DELETE FROM " + CUENTASXUSERTABLE.TABLE_NAME + " WHERE " + USERTABLE.CLIENTE_WEBID_FIELD
					+ " =" + stringValue(cliente_web_id);
			delete += " AND " + CUENTASXUSERTABLE.CUENTA_ID_FIELD + " =" + stringValue(cuenta_Cliente_id);

			ZeniQueryExcecutor.excecuteUpdate(delete);

		} catch (Exception e) {
			ZeniContextServer.getInstance().printErrorLog("Error al eliminar la Cuenta Cliente cuenta_Cliente_id {"
					+ cuenta_Cliente_id + "} cliente_web_id{" + cliente_web_id + "} de la tabla. WP_USERS");
		}

	}

	
	private String stringValue(String data) {

		if (data == null)
			return "''";
		else
			return "'".concat(data.trim()).concat("'");
	}

	
	/**
	 * 
	 * @param username
	 * @return
	 * @throws ZeniDBExeption
	 */
	private UserCuenta isCuentaPadre(String username) throws ZeniDBExeption {
 
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT CC.OPERADORDEMESA_ID OPCC, C.OPERADORDEMESA_ID OPC ");
		sql.append("FROM CUENTA_CLIENTE CC  ");
		sql.append("INNER JOIN CLIENTE C ON (CC.CLIENTE_ID = C.ID) ");
		sql.append("WHERE NROCUENTA  ='" + username + "' ");

		final List<UserCuenta> _data = new ArrayList<UserCuenta>();

		final ResulsetObjectBuilder resbCuenta = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {

				if (rset.next()) {

					UserCuenta us = new UserCuenta();
					us.setOpc(rset.getString("OPC"));
					us.setOpcc(rset.getString("OPCC"));
					if (!"28199031".equals(us.getOpc()) && !"28199031".equals(us.getOpcc()))
						us.setHabilitado(true);
					else
						us.setHabilitado(false);

					_data.add(us);

				}
			}
		};

		ZeniContextServer.getInstance().printVerboLog(sql.toString());
		ZeniQueryExcecutor.excecuteSelect(sql.toString(), resbCuenta);

		if (_data != null & _data.size() > 0)
			return _data.get(0);
		else
			return null;
	}

	public String altaUsuario(java.lang.String clienteId, java.lang.String username, java.lang.String nombre,
			java.lang.String apellido, java.lang.String email, java.lang.String password,
			java.lang.String codigoDeAutorizacion, String hash)
			throws ZeniServerExeption, ZeniHashInvalidExeption, ZeniAuthTypeInvalidExeption, ZeniDBExeption {
		String seed = findSeed4Hash(hash);
		try {
			byte[] bPassword = GenericCodec.base64ToByte(password);
			byte[] bSeeed = GenericCodec.base64ToByte(seed);
			byte[] bPasswordDecrypted = GenericCodec.DESdecryptByte(bPassword, bSeeed);
			String sPasswordDecrypted = GenericCodec.byteToBase64(bPasswordDecrypted);
			String sSalt = GenericCodec.stringToBase64(nombre + apellido + "supercalifragilisticoespialidoso");
			byte[] bSalt = GenericCodec.base64ToByte(sSalt);
			String sPasswordToStore;
			byte[] bPasswordToStore = GenericCodec.getHash(GenericCodec.ITERATION_NUMBER, sPasswordDecrypted, bSalt,
					null);
			sPasswordToStore = GenericCodec.byteToBase64(bPasswordToStore);
			UserData ud = new UserDataImpl(clienteId, username, sPasswordToStore, sSalt, nombre, apellido, email);
			ud.setDBPassword(sPasswordToStore);
			insertUserData(ud);
		} catch (NoSuchAlgorithmException ex) {
			throw new ZeniServerExeption(FAULTCONSTANTS.ERROR_DE_CODEC_MSG, FAULTCONSTANTS.ERROR_DE_CODEC, ex);
		} catch (UnsupportedEncodingException ex) {
			throw new ZeniServerExeption(FAULTCONSTANTS.ERROR_DE_CODEC_MSG, FAULTCONSTANTS.ERROR_DE_CODEC, ex);
		} catch (ZeniDBExeption e) {
			throw e;
		}
		return "Bienvenido";
	}

	public boolean isPasswordValid(String receivedPassword, String seed, String storedPassword, String storedSalt)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		// receivedPassword esta DES con el seed
		// storedPassword y storedSalt esta guardado con sha256
		// unDES receivedPassword, sumo storedSalt pass, hasheo y comparo, si son
		// iguales entonces es el mismo password
		String sec_level = ZeniContextServer.getInstance()
				.getProperty(APPL_SECURITY_PROPERTIES.APPL_SECURITY_LEVEL) != null
						? ZeniContextServer.getInstance().getProperty(APPL_SECURITY_PROPERTIES.APPL_SECURITY_LEVEL)
						: ZeniContextServer.VACIO;
		if (sec_level.equals(APPL_SECURITY_PROPERTIES.LEVEL_LEASE)) {
			byte[] bSalt = GenericCodec.base64ToByte(storedSalt);
			byte[] bDigest = GenericCodec.base64ToByte(storedPassword);
			try {
				final byte[] proposedDigest = GenericCodec.getHash(GenericCodec.ITERATION_NUMBER, receivedPassword,
						bSalt, bDigest);
				return Arrays.equals(proposedDigest, bDigest);
			} catch (NoSuchAlgorithmException e) {
				throw new NoSuchAlgorithmException("Exception reading hash an salt.\n" + e.getMessage());
			} catch (UnsupportedEncodingException e) {
				throw new UnsupportedEncodingException("Exception reading hash an salt.\n" + e.getMessage());
			}
		} else if (sec_level.equals(APPL_SECURITY_PROPERTIES.LEVEL_SIMPLE)) {
			byte[] bSalt = GenericCodec.base64ToByte(storedSalt);
			byte[] bDigest = GenericCodec.base64ToByte(storedPassword);
			try {
				final byte[] proposedDigest = GenericCodec.getHash(GenericCodec.ITERATION_NUMBER,
						GenericCodec.stringToBase64(receivedPassword), bSalt, bDigest);
				return Arrays.equals(proposedDigest, bDigest);
			} catch (NoSuchAlgorithmException e) {
				throw new NoSuchAlgorithmException("Exception reading hash an salt.\n" + e.getMessage());
			} catch (UnsupportedEncodingException e) {
				throw new UnsupportedEncodingException("Exception reading hash an salt.\n" + e.getMessage());
			}
		} else if (sec_level.equals(APPL_SECURITY_PROPERTIES.LEVEL_UNACTIVE)) {
			return receivedPassword.equals(storedPassword);
		} else { // sec_level.equals(APPL_SECURITY_PROPERTIES.LEVEL_STRICT)
			String sPasswordDecrypted = GenericCodec.DESdecrypt64(receivedPassword, seed);
			byte[] bSalt = GenericCodec.base64ToByte(storedSalt);
			byte[] bDigest = GenericCodec.base64ToByte(storedPassword);
			try {
				final byte[] proposedDigest = GenericCodec.getHash(GenericCodec.ITERATION_NUMBER, sPasswordDecrypted,
						bSalt, bDigest);
				return Arrays.equals(proposedDigest, bDigest);
			} catch (NoSuchAlgorithmException e) {
				throw new NoSuchAlgorithmException("Exception reading hash an salt.\n" + e.getMessage());
			} catch (UnsupportedEncodingException e) {
				throw new UnsupportedEncodingException("Exception reading hash an salt.\n" + e.getMessage());
			}
		}
	}

	public String resetPassword(java.lang.String usuario, java.lang.String email, java.lang.String codigoDeAutorizacion)
			throws ZeniCodigoDeAutorizacionInvalido, ZeniDBExeption, ZeniServerExeption {
		// cod auth valido
		CodigoDeAutorizacionUtil cau = new CodigoDeAutorizacionUtil();
		if (cau.validarCodigoDeAutorizacion(usuario, email, codigoDeAutorizacion)) {
			try {
				UserData ud = getUserData(usuario);
				byte[] bSalt = GenericCodec.base64ToByte(ud.getDBSalt());
				String sPasswordSolo = GenericCodec.stringToBase64(usuario + new Date().toString());
				String sec_level = ZeniContextServer.getInstance()
						.getProperty(APPL_SECURITY_PROPERTIES.APPL_SECURITY_LEVEL) != null
								? ZeniContextServer.getInstance()
										.getProperty(APPL_SECURITY_PROPERTIES.APPL_SECURITY_LEVEL)
								: ZeniContextServer.VACIO;
				if (sec_level.equals(APPL_SECURITY_PROPERTIES.LEVEL_UNACTIVE)) {
					ud.setDBPassword(sPasswordSolo);
				} else { // sec_level.equals(APPL_SECURITY_PROPERTIES.LEVEL_STRICT)
					String sPasswordSHA1 = GenericCodec.digestSHA1(sPasswordSolo);
					String sPasswordbase64SHA1 = GenericCodec.stringToBase64(sPasswordSHA1);
					final byte[] bPasswordToStore = GenericCodec.getHash(GenericCodec.ITERATION_NUMBER,
							sPasswordbase64SHA1, bSalt, null);
					String sPasswordToStore = GenericCodec.byteToBase64(bPasswordToStore);
					ud.setDBPassword(sPasswordToStore);
				}
				updateUserData(ud);
				return sPasswordSolo;
			} catch (NoSuchAlgorithmException e) {
				throw new ZeniServerExeption(FAULTCONSTANTS.ERROR_DE_CODEC_MSG, FAULTCONSTANTS.ERROR_DE_CODEC, e);
			} catch (UnsupportedEncodingException e) {
				throw new ZeniServerExeption(FAULTCONSTANTS.ERROR_DE_CODEC_MSG, FAULTCONSTANTS.ERROR_DE_CODEC, e);
			}
		}
		return "No hubo cambio de password.";
	}

	public String changePassword(AuthType auth, String passwordNuevo)
			throws ZeniServerExeption, ZeniAuthTypeInvalidExeption, ZeniHashInvalidExeption {
		try {
			if (auth == null || auth.getHash() == null || auth.getUser() == null)
				throw new ZeniAuthTypeInvalidExeption();
			UserData ud = getUserData(auth.getUser());
			String seed = findSeed4Hash(auth.getHash());
			String sPasswordDecrypted = GenericCodec.DESdecrypt64(passwordNuevo, seed);
			byte[] bSalt = GenericCodec.base64ToByte(ud.getDBSalt());
			final byte[] bPasswordToStore = GenericCodec.getHash(GenericCodec.ITERATION_NUMBER, sPasswordDecrypted,
					bSalt, null);
			String sPasswordToStore = GenericCodec.byteToBase64(bPasswordToStore);
			ud.setDBPassword(sPasswordToStore);
			updateUserData(ud);
		} catch (final ZeniDBExeption e) {
			throw new ZeniServerExeption(FAULTCONSTANTS.ERROR_DE_CODEC_MSG, FAULTCONSTANTS.ERROR_DE_CODEC, e);
		} catch (NoSuchAlgorithmException e) {
			throw new ZeniServerExeption(FAULTCONSTANTS.ERROR_DE_CODEC_MSG, FAULTCONSTANTS.ERROR_DE_CODEC, e);
		} catch (UnsupportedEncodingException e) {
			throw new ZeniServerExeption(FAULTCONSTANTS.ERROR_DE_CODEC_MSG, FAULTCONSTANTS.ERROR_DE_CODEC, e);
		}
		return "Password cambiado satisfactoriamente.";
	}

	public void updateUserData(UserData ud) throws ZeniDBExeption {

		// Busico el usuario anterior antes de actualizar el userName
		UserData oldData = null;
		try {
			oldData = getClientData(ud.getClienteid());
		} catch (ZeniServerExeption e) {
			ZeniContextServer.getInstance().printErrorLog("Error al buscar el usuario ID{" + ud.getClienteid() + "}.");
		}

		ZeniUpdateBuilder zeu = new ZeniUpdateBuilder().update(USERTABLE.TABLE_NAME).set(USERTABLE.EMAIL_FIELD,
				ud.getEmail());
		if (ud.getDBPassword() != null && !ud.getDBPassword().equals("")) {
			zeu.coma(USERTABLE.PASSWORD_FIELD, ud.getDBPassword());
		}
		zeu.coma(USERTABLE.NOMBRE_FIELD, ud.getNombre()).coma(USERTABLE.APELLIDO_FIELD, ud.getApellido())
				.coma(USERTABLE.USER_FIELD, ud.getUsername())
				.where(USERTABLE.CLIENTE_WEBID_FIELD, ZeniQueryBuilder.Operator.EQUALS, ud.getClienteid());
		String queryUpdate = zeu.getupdateQuery();
		int result = ZeniQueryExcecutor.excecuteUpdateReturn(queryUpdate);

		// Si se actulizo alguna informacion, cambio el dato
		if (result > 0 && oldData != null)
			userWP.updateUSer(oldData.getUsername(), ud);

	}

	public UserData getUserData(String usuario) throws ZeniDBExeption, ZeniServerExeption {
		String querySelect = new ZeniQueryBuilder()
				.select(USERTABLE.CLIENTE_WEBID_FIELD, USERTABLE.USER_FIELD, USERTABLE.PASSWORD_FIELD,
						USERTABLE.SALT_FIELD, USERTABLE.NOMBRE_FIELD, USERTABLE.APELLIDO_FIELD, USERTABLE.EMAIL_FIELD)
				.from(USERTABLE.TABLE_NAME).where(USERTABLE.USER_FIELD, ZeniQueryBuilder.Operator.EQUALS, usuario)
				.getQuey();
		final UserDataResulsetObjectBuilder resb = new UserDataResulsetObjectBuilder();
		ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
		if (resb.getUserData() != null) {
			return resb.getUserData();
		}
		throw new ZeniServerExeption(FAULTCONSTANTS.USUARIO_NO_EXISTE_MSG, FAULTCONSTANTS.USUARIO_NO_EXISTE);
	}

	public UserData getClientData(String clientid) throws ZeniDBExeption, ZeniServerExeption {
		String querySelect = new ZeniQueryBuilder()
				.select(USERTABLE.CLIENTE_WEBID_FIELD, USERTABLE.USER_FIELD, USERTABLE.PASSWORD_FIELD,
						USERTABLE.SALT_FIELD, USERTABLE.NOMBRE_FIELD, USERTABLE.APELLIDO_FIELD, USERTABLE.EMAIL_FIELD)
				.from(USERTABLE.TABLE_NAME)
				.where(USERTABLE.CLIENTE_WEBID_FIELD, ZeniQueryBuilder.Operator.EQUALS, clientid).getQuey();
		final UserDataResulsetObjectBuilder resb = new UserDataResulsetObjectBuilder();
		ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
		if (resb.getUserData() != null) {
			return resb.getUserData();
		}
		throw new ZeniServerExeption(FAULTCONSTANTS.USUARIO_NO_EXISTE_MSG, FAULTCONSTANTS.USUARIO_NO_EXISTE);
	}

	public void insertUserData(UserData ud) throws ZeniDBExeption {
		String queryInsert = new ZeniInsertBuilder().insertinsertinto(USERTABLE.TABLE_NAME)
				.fields(USERTABLE.CLIENTE_WEBID_FIELD, USERTABLE.USER_FIELD, USERTABLE.SALT_FIELD,
						USERTABLE.PASSWORD_FIELD, USERTABLE.NOMBRE_FIELD, USERTABLE.APELLIDO_FIELD,
						USERTABLE.EMAIL_FIELD, USERTABLE.FECHA_ALTA)
				.values(ud.getClienteid(), ud.getUsername(), ud.getDBSalt(), ud.getDBPassword(), ud.getNombre(),
						ud.getApellido(), ud.getEmail(), DateUtil.ToString.yyyyMMddHHmmss(new Date()))
				.getInsertQuery();
		// TODO manejar todo como transaccion?

		int result = ZeniQueryExcecutor.excecuteUpdateReturn(queryInsert);
		// Si el result es >0 implica que realizo el insert en la DB
		if (result > 0)
			userWP.addUSer(ud);

	}

	/**
	 * Encuentra el seed para el hash creado
	 * 
	 * @param hash
	 * @return
	 * @throws ZeniHashInvalidExeption
	 * @throws ZeniBaseExeption
	 */
	private String findSeed4Hash(String hash) throws ZeniHashInvalidExeption {
		return AuthNHashUtil.getInstance().getHashNSeed(hash).getSeed();
	}

	public interface LOGDEINGRESO {
		String TABLE_NAME = ZeniContextServer.getInstance().getDBWebOwner() + "LOGDEINGRESO";
		String USER_FIELD = "USUARIO";
		String FECHA_FIELD = "FECHA";
	}

	public interface USERTABLE {
		String TABLE_NAME = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_USUARIOS";
		String PASSWORD_FIELD = "PASSWORD";
		String USER_FIELD = "USUARIO";
		String SALT_FIELD = "SALT";
		String NOMBRE_FIELD = "NOMBRE";
		String APELLIDO_FIELD = "APELLIDO";
		String EMAIL_FIELD = "EMAIL";
		String CLIENTE_WEBID_FIELD = "CLIENTE_WEB_ID";
		String FECHA_ALTA = "FECHA_ALTA";
		String FECHA_ULTIMO_LOGIN = "FECHA_ULTIMO_LOGIN";
	}

	public interface CUENTASXUSERTABLE {
		String TABLE_NAME = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_USUARIO_CUENTA";
		String CUENTA_ID_FIELD = "CUENTA_CLIENTE_ID";
		String CLIENTE_WEBID_FIELD = "CLIENTE_WEB_ID";
	}

	public interface UserData {
		/**
		 * Encuentra el salt para el usuario y password
		 * 
		 * @param usuario
		 * @return
		 */
		public String getDBSalt();

		public void setDBPassword(String sPasswordToStore);

		public void setUsername(String sPasswordToStore);

		public void setClienteid(String sPasswordToStore);

		/**
		 * Encuentra el password para el usuario
		 * 
		 * @param usuario
		 * @return
		 */
		public String getDBPassword();

		/**
		 * Encuentra el username para el usuario
		 * 
		 * @param usuario
		 * @return
		 */
		public String getUsername();

		public String getClienteid();

		public String getApellido();

		public String getNombre();

		public String getEmail();
	}

	public void deleteClient(String clientid) {
		final String del = new ZeniDeleteBuilder().deleteFrom(CUENTASXUSERTABLE.TABLE_NAME)
				.where(CUENTASXUSERTABLE.CLIENTE_WEBID_FIELD, Operator.EQUALS, clientid).getDeleteQuery();
		try {
			ZeniQueryExcecutor.excecuteUpdate(del);
		} catch (ZeniDBExeption e1) {
			e1.printStackTrace();
		}
		final String delA = new ZeniDeleteBuilder().deleteFrom(USERTABLE.TABLE_NAME)
				.where(USERTABLE.CLIENTE_WEBID_FIELD, Operator.EQUALS, clientid).getDeleteQuery();
		try {

			// Obtengo antes de borrar el username
			UserData uData = null;
			try {
				uData = getClientData(clientid);
			} catch (ZeniServerExeption e) {
				ZeniContextServer.getInstance().printInfoLog("Se produjo un error inesperado. " + e.getMessage());
			}

			ZeniQueryExcecutor.excecuteUpdate(delA);

			if (uData != null)
				userWP.deleteUSer(uData);

		} catch (ZeniDBExeption e1) {
			ZeniContextServer.getInstance()
					.printErrorLog("Error al borrar  el usuario Username{" + clientid + "}" + e1.getMessage());
			e1.printStackTrace();
		}
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws ZeniDBExeption
	 */
	public String actualizarAccesoUsuario(String usuario) throws ZeniDBExeption {
		final Date ahora = new Date();
		String queryInsert = new ZeniInsertBuilder().insertinsertinto(LOGDEINGRESO.TABLE_NAME)
				.fields(LOGDEINGRESO.USER_FIELD, LOGDEINGRESO.FECHA_FIELD)
				.values(usuario, DateUtil.ToString.fechahorammss(ahora)).getInsertQuery();
		ZeniQueryExcecutor.excecuteUpdate(queryInsert);
		return DateUtil.ToString.fechahorammss(ahora);
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 * @throws ZeniDBExeption
	 */
	public int actualizarFechaUltimoLogin(String usuario) throws ZeniDBExeption {

		ZeniUpdateBuilder zeu = new ZeniUpdateBuilder().update(USERTABLE.TABLE_NAME)
				.set(USERTABLE.FECHA_ULTIMO_LOGIN, DateUtil.ToString.yyyyMMddHHmmss(new Date()))
				.where(USERTABLE.USER_FIELD, ZeniQueryBuilder.Operator.EQUALS, usuario);

		return ZeniQueryExcecutor.excecuteUpdateReturn(zeu.getupdateQuery());

	}
}

class UserDataResulsetObjectBuilder implements ResulsetObjectBuilder {
	UserData us;

	@Override
	public void thisIsTheResulset(ResultSet rset) throws SQLException {
		if (rset.next()) {
			us = new UserDataImpl(rset.getString(USERTABLE.CLIENTE_WEBID_FIELD), rset.getString(USERTABLE.USER_FIELD),
					rset.getString(USERTABLE.PASSWORD_FIELD), rset.getString(USERTABLE.SALT_FIELD),
					rset.getString(USERTABLE.NOMBRE_FIELD), rset.getString(USERTABLE.APELLIDO_FIELD),
					rset.getString(USERTABLE.EMAIL_FIELD));
		}
	}

	public UserData getUserData() {
		return us;
	}
};

class UserDataImpl implements UserData {
	String username;
	String passdb;
	String saltdb;
	String clienteid;
	String apellido;
	String nombre;
	String email;

	public UserDataImpl(String clienteid, String username, String passdb, String saltdb, String nombre, String apellido,
			String email) {
		this.username = username;
		this.passdb = passdb;
		this.saltdb = saltdb;
		this.clienteid = clienteid;
		this.apellido = apellido;
		this.nombre = nombre;
		this.email = email;
	}

	public void setDBPassword(String sPasswordToStore) {
		this.passdb = sPasswordToStore;
	}

	/**
	 * Encuentra el salt para el usuario y password
	 * 
	 * @param usuario
	 * @return
	 */
	public String getDBSalt() {
		return this.saltdb;
	}

	/**
	 * Encuentra el password para el usuario
	 * 
	 * @param usuario
	 * @return
	 */
	public String getDBPassword() {
		return this.passdb;
	}

	/**
	 * Encuentra el username para el usuario
	 * 
	 * @param usuario
	 * @return
	 */
	public String getUsername() {
		return this.username;
	}

	public String getClienteid() {
		return clienteid;
	}

	public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public void setUsername(String sPasswordToStore) {
		this.username = sPasswordToStore;
	}

	@Override
	public void setClienteid(String sPasswordToStore) {
		this.clienteid = sPasswordToStore;
	}
}