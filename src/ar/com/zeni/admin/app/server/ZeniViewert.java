package ar.com.zeni.admin.app.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ar.com.zeni.admin.app.server.AyudasConstanst.AyudaTable;
import ar.com.zeni.admin.app.server.FileAdministrationConstanst.FileCategoriesTable;
import ar.com.zeni.admin.app.server.FileAdministrationConstanst.FileTable;
import ar.com.zeni.admin.app.server.NewsConstanst.NewsTable;
import ar.com.zeni.admin.app.server.NormasComerciaConstanst.NormasComerciaTable;
import ar.com.zeni.admin.app.server.NovedadConstanst.NovedadTable;
import ar.com.zeni.codecs.GenericCodec;
import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.FAULTCONSTANTS;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniAuthTypeInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.common.exceptions.ZeniHashInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniServerExeption;
import ar.com.zeni.db.ZeniDeleteBuilder;
import ar.com.zeni.db.ZeniInsertBuilder;
import ar.com.zeni.db.ZeniQueryBuilder;
import ar.com.zeni.db.ZeniQueryBuilder.Operator;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.db.ZeniUpdateBuilder;
import ar.com.zeni.security.AuthNHashUtil;
import ar.com.zeni.security.CodigoDeAutorizacionUtil;
import ar.com.zeni.security.UsuarioAuthenticationUtil;
import ar.com.zeni.security.UsuarioAuthenticationUtil.CUENTASXUSERTABLE;
import ar.com.zeni.security.UsuarioAuthenticationUtil.USERTABLE;
import ar.com.zeni.security.UsuarioAuthenticationUtil.UserData;
import ar.com.zeni.zeniadminwsdl.AdminClienteType;
import ar.com.zeni.zeniadminwsdl.AdminCuentaType;
import ar.com.zeni.zeniadminwsdl.ArrayOfAdminClienteType;
import ar.com.zeni.zeniadminwsdl.ArrayOfAdminCuentaType;
import ar.com.zeni.zeniadminwsdl.ArrayOfAyudaType;
import ar.com.zeni.zeniadminwsdl.ArrayOfCategoryType;
import ar.com.zeni.zeniadminwsdl.ArrayOfFilesType;
import ar.com.zeni.zeniadminwsdl.ArrayOfNewsType;
import ar.com.zeni.zeniadminwsdl.ArrayOfNormCromType;
import ar.com.zeni.zeniadminwsdl.ArrayOfNovedadType;
import ar.com.zeni.zeniadminwsdl.AyudaType;
import ar.com.zeni.zeniadminwsdl.CategoryType;
import ar.com.zeni.zeniadminwsdl.ClienteCompletoType;
import ar.com.zeni.zeniadminwsdl.FaultType;
import ar.com.zeni.zeniadminwsdl.FaultType_Exception;
import ar.com.zeni.zeniadminwsdl.FechaTimeType;
import ar.com.zeni.zeniadminwsdl.FileType;
import ar.com.zeni.zeniadminwsdl.FilesType;
import ar.com.zeni.zeniadminwsdl.NewsType;
import ar.com.zeni.zeniadminwsdl.NormCromType;
import ar.com.zeni.zeniadminwsdl.NovedadType;
import ar.com.zeni.zeniadminwsdl.ZeniAdminWSDL;
import ar.com.zeni.zeniwsdl.AuthType;

public class ZeniViewert implements ZeniAdminWSDL {
	public ArrayOfAdminCuentaType obtCuentas(String clienteId) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtCuentas");
        ZeniContextServer.getInstance().printVerboLog(clienteId);
        try {
            ArrayOfAdminCuentaType _return = new ArrayOfAdminCuentaType();
            final List<AdminCuentaType> _returnCuentas = new ArrayList<AdminCuentaType>();
			final StringBuilder querySBuilderCuenta = new StringBuilder()
				.append("SELECT cc.id id_cuenta_cliente, ").append("cc.nrocuenta, ").append("cc.denominacioncuenta ")
				.append("FROM ").append(ZeniContextServer.getInstance().getDBConsultaOwner()).append("cuenta_cliente cc ")
				.append("WHERE cc.fechabaja IS NULL ");
			if (clienteId != null && !clienteId.equals("")) {
				querySBuilderCuenta.append("AND cc.id IN ")
				.append("( SELECT df.").append(CUENTASXUSERTABLE.CUENTA_ID_FIELD)
				.append(" FROM ").append(CUENTASXUSERTABLE.TABLE_NAME).append(" df ")
				.append("where df.").append(CUENTASXUSERTABLE.CLIENTE_WEBID_FIELD).append(" = ").append(clienteId)
				.append(") ");
			}
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						AdminCuentaType _returnCuentasVal1 = new AdminCuentaType();
						_returnCuentasVal1.setId(rset.getString("id_cuenta_cliente"));
						_returnCuentasVal1.setDesc(rset.getString("denominacioncuenta"));
						_returnCuentasVal1.setNro(rset.getString("nrocuenta"));
						_returnCuentas.add(_returnCuentasVal1);
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySBuilderCuenta.toString(), resb);
            _return.getCuentas().addAll(_returnCuentas);
            return _return;
   		} catch (ZeniDBExeption ex) {
   			FaultType f = new FaultType();
   			f.setMensaje(ex.getMessage());
   			f.setCodigoError(ex.getCode());
   			ZeniContextServer.getInstance().printErrorLog(ex);
   			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
   			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public java.lang.String modCliente(java.lang.String clienteId,java.lang.String username,java.lang.String name,java.lang.String lastName,java.lang.String email,ArrayOfAdminCuentaType cuentas) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation modCliente");
        ZeniContextServer.getInstance().printVerboLog(clienteId);
        ZeniContextServer.getInstance().printVerboLog(username);
        ZeniContextServer.getInstance().printVerboLog(name);
        ZeniContextServer.getInstance().printVerboLog(lastName);
        ZeniContextServer.getInstance().printVerboLog(email);
        ZeniContextServer.getInstance().printVerboLog(cuentas);
        try {
        	UserDataImplementation ud = new UserDataImplementation(clienteId, username, null, null, name, lastName, email);
			final UsuarioAuthenticationUtil uauu = new UsuarioAuthenticationUtil();			//
			uauu.updateUserData(ud);
			final List<String> borrar = new ArrayList<String>();
			final List<String> noagreg = new ArrayList<String>();
			final List<String> agreg = new ArrayList<String>();
		   	for ( AdminCuentaType cta : cuentas.getCuentas()){
		   		agreg.add(cta.getId());
		   	}
		   	ArrayOfAdminCuentaType ctasAhora = obtCuentas(clienteId);
		   	for ( AdminCuentaType cta : ctasAhora.getCuentas()){
		   		boolean finded = false;
		   		for ( AdminCuentaType ctn : cuentas.getCuentas()  ) {
		   			if (cta.getId().equals(ctn.getId())){
		   				finded = true;
		   			}
		   		}
		   		if (!finded){
		   			borrar.add(cta.getId());
		   		} else {
		   			noagreg.add(cta.getId());
		   		}
		   	}
			agreg.removeAll(borrar);
			agreg.removeAll(noagreg);
			for ( int i=0; i<borrar.size(); i++) {
				String del = new ZeniDeleteBuilder().deleteFrom(CUENTASXUSERTABLE.TABLE_NAME).where(CUENTASXUSERTABLE.CLIENTE_WEBID_FIELD, Operator.EQUALS, clienteId).and(CUENTASXUSERTABLE.CUENTA_ID_FIELD, Operator.EQUALS, borrar.get(i)).getDeleteQuery();
				ZeniQueryExcecutor.excecuteUpdate(del);
			}
			for ( int i=0; i<agreg.size(); i++) {
				String ins = new ZeniInsertBuilder().insertinsertinto(CUENTASXUSERTABLE.TABLE_NAME).fields(CUENTASXUSERTABLE.CLIENTE_WEBID_FIELD, CUENTASXUSERTABLE.CUENTA_ID_FIELD).values(clienteId, agreg.get(i)).getInsertQuery();
				ZeniQueryExcecutor.excecuteUpdate(ins);
			}
			return clienteId;
   		} catch (ZeniDBExeption ex) {
   			FaultType f = new FaultType();
   			f.setMensaje(ex.getMessage());
   			f.setCodigoError(ex.getCode());
   			ZeniContextServer.getInstance().printErrorLog(ex);
   			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
   			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public String cloneUsuario(String clienteId) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation cloneUsuario");
        ZeniContextServer.getInstance().printVerboLog(clienteId);
        try {
    		String us = clienteId;
    		if (us!=null&&!us.equals("")) {
				ClienteCompletoType newUser = obtClienteCompleto(us);
				newUser.setId(getNextClientid());
				newUser.setUsername(("Clone"+newUser.getId()).substring(0, 20));
//				String idUSer = addUsuario(newUser.getId(), newUser.getUsername(), newUser.getName(), newUser.getLastName(), newUser.getEmail());
				modCliente(newUser.getId(), newUser.getUsername(), newUser.getName(), newUser.getLastName(), newUser.getEmail(), newUser.getCuentas());
				return newUser.getId();
    		}
   			throw new FaultType_Exception("Usuario no existe");
   		} catch (ZeniDBExeption ex) {
   			FaultType f = new FaultType();
   			f.setMensaje(ex.getMessage());
   			f.setCodigoError(ex.getCode());
   			ZeniContextServer.getInstance().printErrorLog(ex);
   			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
   			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	private String getNextClientid() throws ZeniDBExeption {
//		final String[] vecino = new String[1];
//		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
//			@Override
//			public void thisIsTheResulset(ResultSet rset) throws SQLException {
//				while (rset.next()) {
//					vecino[0] = rset.getString(USERTABLE.CLIENTE_WEBID_FIELD);
//				}
//			}
//		};
//		ZeniQueryExcecutor.excecuteSelect("select max(" + USERTABLE.CLIENTE_WEBID_FIELD + ") + 1 " + USERTABLE.CLIENTE_WEBID_FIELD + " from " + USERTABLE.TABLE_NAME, resb);
//		return vecino[0];
		return MbIdGenerator.getInstance().nextId();
	}

	public String addUsuario(String clienteId,String username,String name,String lastName,String email) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation altaUsuario");
        ZeniContextServer.getInstance().printVerboLog(clienteId);
        ZeniContextServer.getInstance().printVerboLog(username);
        ZeniContextServer.getInstance().printVerboLog(name);
        ZeniContextServer.getInstance().printVerboLog(lastName);
        ZeniContextServer.getInstance().printVerboLog(email);
        try {
    		final UsuarioAuthenticationUtil uauu = new UsuarioAuthenticationUtil();
    		String us = (clienteId==null||clienteId.equals(""))?getNextClientid():clienteId;
    		UserDataImplementation ud = new UserDataImplementation(us, username, null, null, name, lastName, email);
   			if (us!=null&&!us.equals("")) {
   				ar.com.zeni.zeniwsdl.HashNSeedType hns = AuthNHashUtil.getInstance().getNewHashNSeed("127.0.0.1");
   				String passConDESyBase64 = null;
//   				ZeniContextServer.getInstance().printInfoLog("passTemporal{"+"new"+ud.getClienteid()+"}");
   				String passsha1 = GenericCodec.digestSHA1("new"+ud.getClienteid());
   				String base64passsha1 = GenericCodec.stringToBase64(passsha1);
   				byte[] byteArrayPassBase64ssha1 = GenericCodec.base64ToByte(base64passsha1);
   				byte[] bytesDePassConDESyBase64 = GenericCodec.DESencryptByte(byteArrayPassBase64ssha1, GenericCodec.base64ToByte(hns.getSeed()));
   				passConDESyBase64 = GenericCodec.byteToBase64(bytesDePassConDESyBase64);
   				String sPass = passConDESyBase64;
   				String codigoAuto = new CodigoDeAutorizacionUtil().obtenerCodigoDeAutorizacion(ud.getUsername(), ud.getEmail());
   				uauu.altaUsuario(ud.getClienteid(), ud.getUsername(), ud.getNombre(), ud.getApellido(), ud.getEmail(), sPass, codigoAuto, hns.getHash());
   				return ud.getClienteid();
   			}
			throw new FaultType_Exception("Usuario no existe");
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniServerExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (NoSuchAlgorithmException ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (UnsupportedEncodingException ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniHashInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniAuthTypeInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public ArrayOfAdminClienteType obtClientes(String clienteId) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtClientes");
        ZeniContextServer.getInstance().printVerboLog(clienteId);
        try {
            ArrayOfAdminClienteType _return = new ArrayOfAdminClienteType();
            final List<AdminClienteType> _returnClis = new ArrayList<AdminClienteType>();
    		String querySelect = new ZeniQueryBuilder()
    		.select(USERTABLE.CLIENTE_WEBID_FIELD,USERTABLE.USER_FIELD, USERTABLE.PASSWORD_FIELD, USERTABLE.SALT_FIELD
    				,USERTABLE.NOMBRE_FIELD,USERTABLE.APELLIDO_FIELD,USERTABLE.EMAIL_FIELD)
    		.from(USERTABLE.TABLE_NAME)
    		.getQuey();
    		querySelect += " order by " + USERTABLE.USER_FIELD;
    		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
    			@Override
    			public void thisIsTheResulset(ResultSet rset) throws SQLException {
    				while (rset.next()) {
    					AdminClienteType _returnCli1 = new AdminClienteType();
    		            _returnCli1.setId(rset.getString(USERTABLE.CLIENTE_WEBID_FIELD));
    		            _returnCli1.setUsername(rset.getString(USERTABLE.USER_FIELD));
    		            _returnClis.add(_returnCli1);
    				}
    			}
    		};
   			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
            _return.getClientes().addAll(_returnClis);
            return _return;
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public String resetPassCliente(String clienteId) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation resetPassCliente");
        ZeniContextServer.getInstance().printVerboLog(clienteId);
		try {
			final UsuarioAuthenticationUtil uauu = new UsuarioAuthenticationUtil();
			String us = clienteId;
			if (us!=null&&!us.equals("")) {
				final UserData ud = uauu.getClientData(us);
				final String codigoAuto = new CodigoDeAutorizacionUtil().obtenerCodigoDeAutorizacion(ud.getUsername(), ud.getEmail());
				final String passt = uauu.resetPassword(ud.getUsername(), ud.getEmail(), codigoAuto);
				final ar.com.zeni.zeniwsdl.HashNSeedType hns = AuthNHashUtil.getInstance().getNewHashNSeed("127.0.0.1");
   				String passsha1 = GenericCodec.digestSHA1(passt);
				String base64passsha1 = GenericCodec.stringToBase64(passsha1);
				byte[] byteArrayPassBase64ssha1 = GenericCodec.base64ToByte(base64passsha1);
				byte[] bytesDePassConDESyBase64 = GenericCodec.DESencryptByte(byteArrayPassBase64ssha1, GenericCodec.base64ToByte(hns.getSeed()));
				String passConDESyBase64 = GenericCodec.byteToBase64(bytesDePassConDESyBase64);
				AuthType au = uauu.autorizarUsuario(ud.getUsername(), hns.getHash(), passConDESyBase64);
				Date fecha = new Date();
				Random random = new Random();
				long oo =  fecha.getTime() +
				random.nextInt(99999) * 10000000000000000l;
				oo += random.nextInt(99999);
				final String passnew = Long.toString(oo).substring(Long.toString(oo).length() - 8, Long.toString(oo).length());
				passsha1 = GenericCodec.digestSHA1(passnew);
				base64passsha1 = GenericCodec.stringToBase64(passsha1);
				byteArrayPassBase64ssha1 = GenericCodec.base64ToByte(base64passsha1);
				bytesDePassConDESyBase64 = GenericCodec.DESencryptByte(byteArrayPassBase64ssha1, GenericCodec.base64ToByte(hns.getSeed()));
				passConDESyBase64 = GenericCodec.byteToBase64(bytesDePassConDESyBase64);
				uauu.changePassword(au, passConDESyBase64);
				return passnew;
			}
			return clienteId;
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniServerExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (NoSuchAlgorithmException ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (UnsupportedEncodingException ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniHashInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniAuthTypeInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public java.lang.String delCliente(java.lang.String clienteId) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation delCliente {}");
        ZeniContextServer.getInstance().printVerboLog(clienteId);
        try {
    		final UsuarioAuthenticationUtil uauu = new UsuarioAuthenticationUtil();
    		String us = clienteId;
    		if (us!=null&&!us.equals("")) {
   				uauu.deleteClient(us);
    		}
    		return "borrado";
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public ClienteCompletoType obtClienteCompleto(String clienteId) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtClienteCompleto");
        ZeniContextServer.getInstance().printVerboLog(clienteId);
        try {
            ClienteCompletoType _return = new ClienteCompletoType();
			final UsuarioAuthenticationUtil uauu = new UsuarioAuthenticationUtil();
			UserData us = uauu.getClientData(clienteId);
            _return.setId(us.getClienteid());
            _return.setUsername(us.getUsername());
            _return.setName(us.getNombre());
            _return.setLastName(us.getApellido());
            _return.setEmail(us.getEmail());
            _return.setCuentas(obtCuentas(clienteId));
            return _return;
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniServerExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	@Override
	public ArrayOfCategoryType obtCategories(String catId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtCategories");
		ZeniContextServer.getInstance().printVerboLog(catId);
		try {
			String querySelect = new ZeniQueryBuilder().select(
					FileCategoriesTable.FIELD_ID,
					FileCategoriesTable.FILED_CATEGORY)
			.from(FileCategoriesTable.FILE_CATEGORIES_TABLE)
			.getQuey();
			final ArrayOfCategoryType ar = new ArrayOfCategoryType();
    		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
    			@Override
    			public void thisIsTheResulset(ResultSet rset) throws SQLException {
    				while (rset.next()) {
    					CategoryType ca = new CategoryType();
    					ca.setId(rset.getString(FileCategoriesTable.FIELD_ID));
    					ca.setDesc(rset.getString(FileCategoriesTable.FILED_CATEGORY));
    					ar.getCategs().add(ca);
    				}
    			}
    		};
   			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
   			return ar;
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}

	@Override
	public String uploadFile(final FileType file, final String category) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation uploadFile");
		ZeniContextServer.getInstance().printVerboLog(file);
		ZeniContextServer.getInstance().printVerboLog(category);
        try {
			delFile(file.getId());
        	String filesId =  MbIdGenerator.getInstance().nextId();
        	file.setId(filesId);
			String ins = new ZeniInsertBuilder().insertinsertinto(FileTable.FILETABLE)
			.fields(FileTable.FIELD_ID,
					FileTable.FIELD_NAME,
					FileTable.FIELD_EXTENSION,
					FileTable.FILED_CATEGORY,
					FileTable.FIELD_DESCRIPTION,
					FileTable.FIELD_MESSAGE,
					FileTable.FIELD_MODIF_DATE)
			.values(file.getId(),
					file.getNombre(),
					file.getExtension(),
					category,
					file.getDescription(),
					file.getMessage(),
					DateUtil.ToString.yyyyddMMSSSssHHmm(new Date()))
			.getInsertQuery();
			ZeniQueryExcecutor.excecuteUpdate(ins);
			new Thread( new Runnable() {
				public void run() {
					File f = new File(ZeniContextServer.getInstance().getProperty(FileAdministrationConstanst.FILE_STORE_DIR) + file.getId());
					System.out.println("Grabo en -> " + ZeniContextServer.getInstance().getProperty(FileAdministrationConstanst.FILE_STORE_DIR) + file.getId());
					FileOutputStream fos;
					try {
						fos = new FileOutputStream(f);
						fos.write(file.getData());
						fos.flush();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
			return file.getId();
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}

	@Override
	public FileType downloadFile(String fileId, String category) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation downloadFile");
		ZeniContextServer.getInstance().printVerboLog(fileId);
		ZeniContextServer.getInstance().printVerboLog(category);
		try {
			String querySelect = new ZeniQueryBuilder().select(
					FileTable.FIELD_ID,
					FileTable.FIELD_NAME,
					FileTable.FIELD_EXTENSION,
					FileTable.FILED_CATEGORY,
					FileTable.FIELD_DESCRIPTION,
					FileTable.FIELD_MESSAGE,
					FileTable.FIELD_MODIF_DATE)
			.from(FileTable.FILETABLE)
			.where(FileTable.FIELD_ID, Operator.EQUALS, fileId)
			.and(FileTable.FILED_CATEGORY, Operator.EQUALS, category)
			.getQuey();
			final FileType _return = new FileType();
    		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
    			@Override
    			public void thisIsTheResulset(ResultSet rset) throws SQLException {
    				while (rset.next()) {
    					_return.setNombre(rset.getString(FileTable.FIELD_NAME));
    					_return.setExtension(rset.getString(FileTable.FIELD_EXTENSION));
    					_return.setNombreConExtension(rset.getString(FileTable.FIELD_NAME) + "." + rset.getString(FileTable.FIELD_NAME));
    					_return.setMessage(rset.getString(FileTable.FIELD_MESSAGE));
    					_return.setDescription(rset.getString(FileTable.FIELD_DESCRIPTION));
    				}
    			}
    		};
   			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
	    	URL fileremote = ZeniContextServer.class.getResource(ZeniContextServer.getInstance().getProperty(FileAdministrationConstanst.FILE_STORE_DIR) + fileId);
            URLConnection connection = fileremote.openConnection();
            final InputStream icon = connection.getInputStream();
			final BufferedInputStream bifin = new BufferedInputStream(icon);
			final ByteArrayOutputStream bais = new ByteArrayOutputStream();
			final byte data[] = new byte[1024];
			int count;
			final byte[] _returnData;
			try {
				while ((count = bifin.read(data, 0, 1024)) != -1) {
					bais.write(data, 0, count);
				}
			} finally {
				icon.close();
				bifin.close();
				bais.flush();
			}
   			_returnData = bais.toByteArray();
			bais.close();
			_return.setData(_returnData);
			_return.setTamanio(_returnData.length);
			return _return;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}

	public String modAyuda(AyudaType ayuda) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation modAyuda");
		ZeniContextServer.getInstance().printVerboLog(ayuda);
        try {
        	String queIn = new ZeniUpdateBuilder()
        	.update(AyudaTable.HELPTABLE)
        	.set(AyudaTable.FILED_QUESTION, ayuda.getPregunta())
        	.coma(AyudaTable.FIELD_ANSWER, ayuda.getRespuesta())
        	.coma(AyudaTable.FIELD_ORDER, ayuda.getOrden()+"")
        	.where(AyudaTable.FIELD_ID, Operator.EQUALS, ayuda.getId())
        	.getupdateQuery();
        	ZeniQueryExcecutor.excecuteUpdate(queIn);
            return ayuda.getId();
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }
	public String delAyuda(String ayudaId) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation delAyuda");
		ZeniContextServer.getInstance().printVerboLog(ayudaId);
        try {
        	if (ayudaId!=null&&!ayudaId.equals("")) {
	        	String queDel = new ZeniDeleteBuilder()
	        	.deleteFrom(AyudaTable.HELPTABLE)
	        	.where(AyudaTable.FIELD_ID, Operator.EQUALS, ayudaId)
	        	.getDeleteQuery();
	        	ZeniQueryExcecutor.excecuteUpdate(queDel);
        	}
            return  ayudaId;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }
	public String addAyuda(AyudaType ayuda) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation addAyuda");
		ZeniContextServer.getInstance().printVerboLog(ayuda);
        try {
        	String ifd = MbIdGenerator.getInstance().nextId();
        	String queIn = new ZeniInsertBuilder()
        	.insertinsertinto(AyudaTable.HELPTABLE)
        	.fields(AyudaTable.FIELD_ID, AyudaTable.FILED_QUESTION, AyudaTable.FIELD_ANSWER, AyudaTable.FIELD_ORDER)
        	.values( ifd, ayuda.getPregunta(), ayuda.getRespuesta(), ayuda.getOrden()+"")
        	.getInsertQuery();
        	ZeniQueryExcecutor.excecuteUpdate(queIn);
            return ifd;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }
	public ArrayOfAyudaType obtAyuda(String ayudaId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtAyuda");
		ZeniContextServer.getInstance().printVerboLog(ayudaId);
        try {
            ArrayOfAyudaType _returnAyudas = new ArrayOfAyudaType();
            final java.util.List<AyudaType> _returnAyudasAyus = new ArrayList<AyudaType>();
   			String querySelect = new ZeniQueryBuilder().select(
   					AyudaTable.FIELD_ID,
   					AyudaTable.FILED_QUESTION,
   					AyudaTable.FIELD_ANSWER,
   					AyudaTable.FIELD_ORDER)
   			.from(AyudaTable.HELPTABLE)
   			.getQuey();
       		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
       			@Override
       			public void thisIsTheResulset(ResultSet rset) throws SQLException {
       				while (rset.next()) {
       		            AyudaType _returnAyudasAyusVal1 = new AyudaType();
       		            _returnAyudasAyusVal1.setId(rset.getString(AyudaTable.FIELD_ID));
       		            _returnAyudasAyusVal1.setPregunta(rset.getString(AyudaTable.FILED_QUESTION));
       		            _returnAyudasAyusVal1.setRespuesta(rset.getString(AyudaTable.FIELD_ANSWER));
       		            _returnAyudasAyusVal1.setOrden(rset.getInt(AyudaTable.FIELD_ORDER));
       		            _returnAyudasAyus.add(_returnAyudasAyusVal1);
      				}
       			}
       		};
   			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
            _returnAyudas.getAyus().addAll(_returnAyudasAyus);
            return _returnAyudas;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public ArrayOfNewsType obtNews(String newId) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtNews");
		ZeniContextServer.getInstance().printVerboLog(newId);
        try {
        	final ArrayOfNewsType _return = new ArrayOfNewsType();
            final List<NewsType> _returnNews = new ArrayList<NewsType>();
   			String querySelect = new ZeniQueryBuilder().select(
   					NewsTable.FIELD_ID,
   					NewsTable.FIELD_NEWS,
   					NewsTable.FIELD_DATE)
   			.from(NewsTable.NEWSTABLE)
   			.getQuey();
       		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
       			@Override
       			public void thisIsTheResulset(ResultSet rset) throws SQLException {
       				while (rset.next()) {
       		            try {
	       		            NewsType _returnNewsVal1 = new NewsType();
	       		            _returnNewsVal1.setId(rset.getString(NewsTable.FIELD_ID));
	       		            _returnNewsVal1.setDesc(rset.getString(NewsTable.FIELD_NEWS));
	       		            ar.com.zeni.zeniwsdl.FechaTimeType s = DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString(NewsTable.FIELD_DATE)));
	       		            FechaTimeType d = new FechaTimeType();
	       		            d.setFecha(s.getFecha());
							_returnNewsVal1.setFecha(d);
							_returnNews.add(_returnNewsVal1);
						} catch (ParseException e) {
							throw new SQLException("No se puede determinar la fecha en el query.", e);
						}
      				}
       			}
       		};
   			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
            _return.getNews().addAll(_returnNews);
            return _return;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }
	public String delNews(String newsId) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation delNews");
		ZeniContextServer.getInstance().printVerboLog(newsId);
        try {
        	if (newsId!=null&&!newsId.equals("")) {
	        	String queDel = new ZeniDeleteBuilder()
	        	.deleteFrom(NewsTable.NEWSTABLE)
	        	.where(NewsTable.FIELD_ID, Operator.EQUALS, newsId)
	        	.getDeleteQuery();
	        	ZeniQueryExcecutor.excecuteUpdate(queDel);
        	}
            return newsId;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }
	public String modNews(NewsType news) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation modNews");
		ZeniContextServer.getInstance().printVerboLog(news);
        try {
        	ar.com.zeni.zeniwsdl.FechaTimeType s = new ar.com.zeni.zeniwsdl.FechaTimeType();
        	s.setFecha(news.getFecha().getFecha());
	        String fecha = DateUtil.ToString.yyyyMMdd(s);
        	String queIn = new ZeniUpdateBuilder()
        	.update(NewsTable.NEWSTABLE)
        	.set(NewsTable.FIELD_NEWS, news.getDesc())
        	.coma(NewsTable.FIELD_DATE, fecha)
        	.where(NewsTable.FIELD_ID, Operator.EQUALS, news.getId())
        	.getupdateQuery();
        	ZeniQueryExcecutor.excecuteUpdate(queIn);
            return news.getId();
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }
	public String addNews(NewsType news) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation addNews");
		ZeniContextServer.getInstance().printVerboLog(news);
        try {
        	ar.com.zeni.zeniwsdl.FechaTimeType s = new ar.com.zeni.zeniwsdl.FechaTimeType();
        	s.setFecha(news.getFecha().getFecha());
	        String fecha = DateUtil.ToString.yyyyMMdd(s);
        	String ifd = MbIdGenerator.getInstance().nextId();
        	String queIn = new ZeniInsertBuilder()
        	.insertinsertinto(NewsTable.NEWSTABLE)
        	.fields(NewsTable.FIELD_ID, NewsTable.FIELD_NEWS, NewsTable.FIELD_DATE)
        	.values( ifd, news.getDesc(), fecha)
        	.getInsertQuery();
        	ZeniQueryExcecutor.excecuteUpdate(queIn);
            return ifd;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public ArrayOfFilesType obtFiles(String fileId) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtFiles");
		ZeniContextServer.getInstance().printVerboLog(fileId);
        try {
            ArrayOfFilesType _return = new ArrayOfFilesType();
            final List<FilesType> _returnFiles = new ArrayList<FilesType>();
            ZeniQueryBuilder qe = new ZeniQueryBuilder().select(
   					FileTable.ALFIELD_ID,
   					FileTable.ALFIELD_NAME,
   					FileTable.ALFIELD_EXTENSION,
   					FileTable.ALFIELD_DESCRIPTION,
   					FileTable.ALFIELD_MESSAGE,
   					FileTable.ALFIELD_MODIF_DATE,
   					FileTable.ALFILED_CATEGORY)
   			.from(FileTable.ALFILETABLE);
   			if (fileId!=null&&!fileId.equals("")){
   				qe.where(FileTable.ALFILED_CATEGORY, Operator.EQUALS, fileId);
   			}
   			String querySelect = qe.getQuey();
       		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
       			@Override
       			public void thisIsTheResulset(ResultSet rset) throws SQLException {
       				while (rset.next()) {
       		            try {
       		            	ar.com.zeni.zeniwsdl.FechaTimeType ss = DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyddMMSSSssHHmm(rset.getString(FileTable.FIELD_MODIF_DATE)));
							FechaTimeType sa = new FechaTimeType();
							sa.setFecha(ss.getFecha());
							FilesType _returnFilesVal1 = new FilesType();
				            _returnFilesVal1.setId(rset.getString(FileTable.FIELD_ID));
				            _returnFilesVal1.setExt(rset.getString(FileTable.FIELD_EXTENSION));
				            _returnFilesVal1.setName(rset.getString(FileTable.FIELD_NAME));
				            _returnFilesVal1.setNameExt(rset.getString(FileTable.FIELD_NAME) + "." + rset.getString(FileTable.FIELD_EXTENSION));
				            _returnFilesVal1.setDesc(rset.getString(FileTable.FIELD_DESCRIPTION));
				            _returnFilesVal1.setMess(rset.getString(FileTable.FIELD_MESSAGE));
				            _returnFilesVal1.setCatId(rset.getString(FileTable.FILED_CATEGORY));
							_returnFilesVal1.setFecha(sa);
							_returnFiles.add(_returnFilesVal1);
						} catch (ParseException e) {
							throw new SQLException("No se puede determinar la fecha en el query.", e);
						}
      				}
       			}
       		};
   			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
            _return.getFiles().addAll(_returnFiles);
            return _return;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public java.lang.String delFile(final java.lang.String fileId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation delFile");
		ZeniContextServer.getInstance().printVerboLog(fileId);
        try {
        	new Thread( new Runnable() {
        		@Override
        		public void run() {
        			if (fileId!=null&&!fileId.equals("")) {
        				try {
	        				String queDel = new ZeniDeleteBuilder()
	        				.deleteFrom(FileTable.FILETABLE)
	        				.where(FileTable.FIELD_ID, Operator.EQUALS, fileId)
	        				.getDeleteQuery();
							ZeniQueryExcecutor.excecuteUpdate(queDel);
	        				File f = new File((ZeniContextServer.getInstance().getProperty(FileAdministrationConstanst.FILE_STORE_DIR) + fileId));
	        				f.delete();
						} catch (ZeniDBExeption e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
            		}
        		}}).start();
            return fileId;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public String modFiles(FilesType file) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation modFiles");
		ZeniContextServer.getInstance().printVerboLog(file);
        try {
        	ar.com.zeni.zeniwsdl.FechaTimeType s = new ar.com.zeni.zeniwsdl.FechaTimeType();
        	s.setFecha(file.getFecha().getFecha());
	        String fecha = DateUtil.ToString.yyyyddMMSSSssHHmm(s);
        	String queIn = new ZeniUpdateBuilder()
        	.update(FileTable.FILETABLE)
        	.set(FileTable.FILED_CATEGORY, file.getCatId())
        	.coma(FileTable.FIELD_MESSAGE, file.getMess())
        	.coma(FileTable.FIELD_DESCRIPTION,file.getDesc())
        	.coma(FileTable.FIELD_NAME, file.getName())
        	.coma(FileTable.FIELD_EXTENSION, file.getExt())
        	.coma(FileTable.FIELD_MODIF_DATE, fecha)
        	.where(FileTable.FIELD_ID, Operator.EQUALS, file.getId())
        	.getupdateQuery();
        	ZeniQueryExcecutor.excecuteUpdate(queIn);
            return file.getId();
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}
	public String addNormCrom(NormCromType norm) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation addNormCrom");
		ZeniContextServer.getInstance().printVerboLog(norm);
        try {
        	String ifd = MbIdGenerator.getInstance().nextId();
        	String queIn = new ZeniInsertBuilder()
        	.insertinsertinto(NormasComerciaTable.HELPTABLE)
        	.fields(NormasComerciaTable.FIELD_ID, NormasComerciaTable.FIELD_NORMAVIG, NormasComerciaTable.FIELD_PRODUCTO,NormasComerciaTable.FIELD_NORMA_FILE_ID,NormasComerciaTable.FIELD_TMPD_FILE_ID)
        	.values( ifd, norm.getNormvig(), norm.getProd(), norm.getFileNormaId(), norm.getFileTMPSId())
        	.getInsertQuery();
        	ZeniQueryExcecutor.excecuteUpdate(queIn);
            return ifd;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public java.lang.String modNormCrom(ar.com.zeni.zeniadminwsdl.NormCromType norm) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation modNormCrom");
		ZeniContextServer.getInstance().printVerboLog(norm);
        try {
        	String queIn = new ZeniUpdateBuilder()
        	.update(NormasComerciaTable.HELPTABLE)
        	.set(NormasComerciaTable.FIELD_NORMAVIG, norm.getNormvig())
        	.coma(NormasComerciaTable.FIELD_PRODUCTO,norm.getProd())
        	.coma(NormasComerciaTable.FIELD_NORMA_FILE_ID, norm.getFileNormaId())
        	.coma(NormasComerciaTable.FIELD_TMPD_FILE_ID, norm.getFileTMPSId())
        	.where(NormasComerciaTable.FIELD_ID, Operator.EQUALS, norm.getId())
        	.getupdateQuery();
        	ZeniQueryExcecutor.excecuteUpdate(queIn);
            return norm.getId();
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public java.lang.String delNormCrom(java.lang.String norm) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation delNormCrom");
		ZeniContextServer.getInstance().printVerboLog(norm);
        try {
        	if (norm!=null&&!norm.equals("")) {
	        	String queDel = new ZeniDeleteBuilder()
	        	.deleteFrom(NormasComerciaTable.HELPTABLE)
	        	.where(NormasComerciaTable.FIELD_ID, Operator.EQUALS, norm)
	        	.getDeleteQuery();
	        	ZeniQueryExcecutor.excecuteUpdate(queDel);
        	}
        	return norm;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	public ArrayOfNormCromType obtNormCrom(String norm) throws FaultType_Exception    {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtNormCrom");
		ZeniContextServer.getInstance().printVerboLog(norm);
        try {
            ArrayOfNormCromType _return = new ArrayOfNormCromType();
            final List<NormCromType> _returnNormas = new ArrayList<NormCromType>();
   			String querySelect = new ZeniQueryBuilder().select(
   					NormasComerciaTable.FIELD_ID,
   					NormasComerciaTable.FIELD_NORMAVIG,
   					NormasComerciaTable.FIELD_PRODUCTO,
   					NormasComerciaTable.FIELD_NORMA_FILE_ID,
   					NormasComerciaTable.FIELD_TMPD_FILE_ID)
   			.from(NormasComerciaTable.HELPTABLE)
   			.getQuey();
       		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
       			@Override
       			public void thisIsTheResulset(ResultSet rset) throws SQLException {
       				while (rset.next()) {
			            final NormCromType _returnNormasVal1 = new NormCromType();
			            _returnNormasVal1.setId(rset.getString(NormasComerciaTable.FIELD_ID));
			            _returnNormasVal1.setProd(rset.getString(NormasComerciaTable.FIELD_PRODUCTO));
			            _returnNormasVal1.setNormvig(rset.getString(NormasComerciaTable.FIELD_NORMAVIG));
			            _returnNormasVal1.setFileNormaId(rset.getString(NormasComerciaTable.FIELD_NORMA_FILE_ID));
			            _returnNormasVal1.setFileTMPSId(rset.getString(NormasComerciaTable.FIELD_TMPD_FILE_ID));
			            _returnNormas.add(_returnNormasVal1);
      				}
       			}
       		};
   			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
       		for ( final NormCromType  sd : _returnNormas) {
	            String sel = "select " + FileTable.FIELD_DESCRIPTION + " from " + FileTable.FILETABLE + " where " + FileTable.FIELD_ID + " = '";
	            String tmps = sel + sd.getFileTMPSId() + "'";
	            String norma = sel + sd.getFileNormaId() + "'";
	       		final ResulsetObjectBuilder resb1 = new ResulsetObjectBuilder() {
	       			@Override
	       			public void thisIsTheResulset(ResultSet rset) throws SQLException {
	       				while (rset.next()) {
	       					sd.setFileNorma(rset.getString(FileTable.FIELD_DESCRIPTION));
	       				}
	       			}
	       		};
	       		final ResulsetObjectBuilder resb2 = new ResulsetObjectBuilder() {
	       			@Override
	       			public void thisIsTheResulset(ResultSet rset) throws SQLException {
	       				while (rset.next()) {
	       					sd.setFileTMPS(rset.getString(FileTable.FIELD_DESCRIPTION));
	           			}
	       			}
	       		};
	   			try {
					ZeniQueryExcecutor.excecuteSelect(norma, resb1);
					ZeniQueryExcecutor.excecuteSelect(tmps, resb2);
				} catch (ZeniDBExeption e) {
					e.printStackTrace();
				}
       		}
            _return.getNormas().addAll(_returnNormas);
            return _return;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	@Override
	public String delNovedad(String idNovedad) throws FaultType_Exception {

		ZeniContextServer.getInstance().printInfoLog("Executing operation delNovedad");
		ZeniContextServer.getInstance().printVerboLog(idNovedad);
        try {
        	if (idNovedad!=null&&!idNovedad.equals("")) {
	        	String queDel = new ZeniDeleteBuilder()
	        	.deleteFrom(NovedadTable.TABLE)
	        	.where(NovedadTable.FIELD_ID, Operator.EQUALS, idNovedad)
	        	.getDeleteQuery();
	        	ZeniQueryExcecutor.excecuteUpdate(queDel);
        	}
            return idNovedad;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}

	}

	@Override
	public String addNovedad(NovedadType in) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation addNovedad");
		ZeniContextServer.getInstance().printVerboLog(in);
        try {
        	ar.com.zeni.zeniwsdl.FechaTimeType s = new ar.com.zeni.zeniwsdl.FechaTimeType();
        	s.setFecha(in.getDate().getFecha());

	        String fecha = DateUtil.ToString.yyyyMMdd(s);
        	String ifd = MbIdGenerator.getInstance().nextId();

        	String queIn = new ZeniInsertBuilder()
        	.insertinsertinto(NovedadTable.TABLE)
        	.fields(NovedadTable.FIELD_ID, NovedadTable.FIELD_TITLE, NovedadTable.FIELD_DESCRIPTION, NovedadTable.FIELD_FECHA)
        	.values( ifd,in.getTitle(), in.getDescription(), fecha)
        	.getInsertQuery();
        	ZeniQueryExcecutor.excecuteUpdate(queIn);
            return ifd;

		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	@Override
	public ArrayOfNovedadType obtNovedad(String novedadId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtNovedad nodedadId{"+novedadId+"}");
		try {
        	final ArrayOfNovedadType _return = new ArrayOfNovedadType();
            final List<NovedadType> _returnNews = new ArrayList<NovedadType>();

            String querySelect = "";

            if (novedadId != null && !"".equals(novedadId.trim())) {

            	querySelect = new ZeniQueryBuilder().select(
            			NovedadTable.FIELD_ID,
            			NovedadTable.FIELD_TITLE,
            			NovedadTable.FIELD_DESCRIPTION,
            			NovedadTable.FIELD_FECHA)
   			.from(NovedadTable.TABLE).where(NovedadTable.FIELD_ID, Operator.EQUALS, novedadId)
   			.getQuey();
            }else {
            	querySelect = new ZeniQueryBuilder().select(
            			NovedadTable.FIELD_ID,
            			NovedadTable.FIELD_TITLE,
            			NovedadTable.FIELD_DESCRIPTION,
            			NovedadTable.FIELD_FECHA)
   			.from(NovedadTable.TABLE)
   			.getQuey();
            }

       		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
       			@Override
       			public void thisIsTheResulset(ResultSet rset) throws SQLException {
       				while (rset.next()) {
       		            try {
	       		            NovedadType _returnNovedadVal = new NovedadType();
	       		            _returnNovedadVal.setId(rset.getString(NovedadTable.FIELD_ID));
	       		            _returnNovedadVal.setDescription(rset.getString(NovedadTable.FIELD_DESCRIPTION));
	       		            _returnNovedadVal.setTitle(rset.getString(NovedadTable.FIELD_TITLE));
	       		            ar.com.zeni.zeniwsdl.FechaTimeType s = DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString(NovedadTable.FIELD_FECHA)));
	       		            FechaTimeType d = new FechaTimeType();
	       		            d.setFecha(s.getFecha());
	       		            _returnNovedadVal.setDate(d);
							_returnNews.add(_returnNovedadVal);
						} catch (ParseException e) {
							throw new SQLException("No se puede determinar la fecha en el query.", e);
						}
      				}
       			}
       		};
   			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
            _return.getNovedades().addAll(_returnNews);
            return _return;
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
    }

	@Override
	public String modNovedad(NovedadType in) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation modNovedad");
		ZeniContextServer.getInstance().printVerboLog(in);
        try {
        	ar.com.zeni.zeniwsdl.FechaTimeType s = new ar.com.zeni.zeniwsdl.FechaTimeType();
        	s.setFecha(in.getDate().getFecha());

	        String fecha = DateUtil.ToString.yyyyMMdd(s);

	        String queIn = new ZeniUpdateBuilder()
        	.update(NovedadTable.TABLE)
        	.set(NovedadTable.FIELD_DESCRIPTION, in.getDescription())
        	.coma(NovedadTable.FIELD_TITLE, in.getTitle())
        	.coma(NovedadTable.FIELD_FECHA, fecha)
           	.where(NovedadTable.FIELD_ID, Operator.EQUALS, in.getId())
        	.getupdateQuery();

        	ZeniQueryExcecutor.excecuteUpdate(queIn);
            return in.getId();

		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}
}
