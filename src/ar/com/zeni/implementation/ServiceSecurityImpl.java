package ar.com.zeni.implementation;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import ar.com.zeni.codecs.GenericCodec;
import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.FAULTCONSTANTS;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniAuthTypeInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniCodigoDeAutorizacionInvalido;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.common.exceptions.ZeniHashInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniIPDifiereExeption;
import ar.com.zeni.common.exceptions.ZeniServerExeption;
import ar.com.zeni.common.exceptions.ZeniUserPasswordErroneo;
import ar.com.zeni.common.exceptions.ZeniUserRecibeAtencionComercialErroneo;
import ar.com.zeni.mail.MailSender;
import ar.com.zeni.security.AuthNHashUtil;
import ar.com.zeni.security.CodigoDeAutorizacionUtil;
import ar.com.zeni.security.UsuarioAuthenticationUtil;
import ar.com.zeni.security.UsuarioAuthenticationUtil.UserData;
import ar.com.zeni.zeniwsdl.AuthType;
import ar.com.zeni.zeniwsdl.FaultType;
import ar.com.zeni.zeniwsdl.FaultType_Exception;
import ar.com.zeni.zeniwsdl.FechaDiferenciaType;
import ar.com.zeni.zeniwsdl.FechaTimeType;
import ar.com.zeni.zeniwsdl.HashNSeedType;
import ar.com.zeni.zeniwsdl.StringSHA1BASE64DESType;
import ar.com.zeni.zeniwsdl.ZeniWSDL;

/**
 * Implementada la parte de seguridad
 *
 * @author rodrigo
 *
 */
public abstract class ServiceSecurityImpl implements ZeniWSDL {

    @Override
    public String actualizarAccesoUsuario(AuthType auth, String usuario) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation actualizarAccesoUsuario");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(usuario);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);

            //Valida el acceso.
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            UsuarioAuthenticationUtil uauu = new UsuarioAuthenticationUtil();

            //Insert una traza de logueo en LOGDEINGRESO
            final String _return = uauu.actualizarAccesoUsuario(usuario);

            //Actualizo el campo de FECHA_ULTIMO_LOGIN.			
            uauu.actualizarFechaUltimoLogin(usuario);

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
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        }
    }

    @Override
    public String obtenerCodigoDeAutorizacion(String usuario, String email) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCodigoDeAutorizacion");
        ZeniContextServer.getInstance().printVerboLog(usuario);
        ZeniContextServer.getInstance().printVerboLog(email);
        try {
            ArrayList<String> addresses = new ArrayList<String>();
            addresses.add(email);
            CodigoDeAutorizacionUtil impl = new CodigoDeAutorizacionUtil();
            MailSender.sendMailInAFuture("zeni@webmaster.com.ar", addresses, "Codigo de autorizacion.", "El codigo de autorizacion es: \n" + impl.obtenerCodigoDeAutorizacion(usuario, email) + " \n utilicelo en la pagina. \n Saludos.");
            String _return = "Se enviara el codigo de autorizacion al mail " + email + ". En breve revise su correo.";
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

    @Override
    public HashNSeedType obtenerHashYSeed() throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerHashYSeed");
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
        ZeniContextServer.getInstance().printVerboLog("Cliente IP = " + req.getRemoteAddr());
        try {
            ar.com.zeni.zeniwsdl.HashNSeedType _return = AuthNHashUtil.getInstance().getNewHashNSeed(req.getRemoteAddr());
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

    @Override
    public String actualizarPassword(AuthType auth, String passwordNuevo) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation actualizarPassword");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(passwordNuevo);
        try {
            MessageContext mc = wsContext.getMessageContext();
            HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            return new UsuarioAuthenticationUtil().changePassword(auth, passwordNuevo);
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
        } catch (ZeniServerExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
            ZeniContextServer.getInstance().printErrorLog(ex);
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        } catch (ZeniIPDifiereExeption ex) {
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
    public String resetearPassword(String usuario, String email, String codigoDeAutorizacion) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation resetearPassword");
        ZeniContextServer.getInstance().printVerboLog(usuario);
        ZeniContextServer.getInstance().printVerboLog(email);
        ZeniContextServer.getInstance().printVerboLog(codigoDeAutorizacion);
        try {
            UsuarioAuthenticationUtil uauu = new UsuarioAuthenticationUtil();
            String sPasswordSolo = uauu.resetPassword(usuario, email, codigoDeAutorizacion);
            UserData u = uauu.getUserData(usuario);
            ArrayList<String> addresses = new ArrayList<String>();
            addresses.add(u.getEmail());
            MailSender.sendMailInAFuture("zeni@webmaster.com.ar", addresses, "Reseteo de password: ", "Sr: " + u.getNombre() + "El password nuevo es: " + sPasswordSolo + "\n utilicelo en la pagina.\nRecuerde cambiarlo en su primer ingreso al sistema.\nSaludos.");
            String _return = "Se enviara el password nuevo su mail.\nEn breve revise su correo.";
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
        } catch (ZeniCodigoDeAutorizacionInvalido ex) {
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

    @Resource
    WebServiceContext wsContext;

    @Override
    public ar.com.zeni.zeniwsdl.AuthType autorizarUsuario(String usuario, String hash, String password) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation autorizarUsuario");
        ZeniContextServer.getInstance().printVerboLog(usuario);
        ZeniContextServer.getInstance().printVerboLog(hash);
        ZeniContextServer.getInstance().printVerboLog(password);
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
        ZeniContextServer.getInstance().printVerboLog("Cliente IP = " + req.getRemoteAddr());
        try {
            return new UsuarioAuthenticationUtil().autorizarUsuario(usuario, hash, password);
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
        } catch (ZeniUserPasswordErroneo ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
            ZeniContextServer.getInstance().printErrorLog(ex);
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        } catch (ZeniUserRecibeAtencionComercialErroneo ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
            ZeniContextServer.getInstance().printErrorLog(ex);
            throw new FaultType_Exception(ex.getMessage() + "Usuario: " + usuario, f, ex);
        } catch (Exception ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
            ZeniContextServer.getInstance().printErrorLog(ex);
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        }
    }

    @Override
    public String altaUsuario(String clienteId, String username, String nombre, String apellido, String email, String password, String codigoDeAutorizacion, String hash) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation altaUsuario");
        ZeniContextServer.getInstance().printVerboLog(clienteId);
        ZeniContextServer.getInstance().printVerboLog(nombre);
        ZeniContextServer.getInstance().printVerboLog(apellido);
        ZeniContextServer.getInstance().printVerboLog(email);
        ZeniContextServer.getInstance().printVerboLog(password);
        ZeniContextServer.getInstance().printVerboLog(codigoDeAutorizacion);
        try {
            return new UsuarioAuthenticationUtil().altaUsuario(clienteId, username, nombre, apellido, email, password, codigoDeAutorizacion, hash);
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
    public StringSHA1BASE64DESType obtenerStringSHA1BASE64DES(final String stringAEncodear, final String stringSeedEnBase64) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerStringSHA1BASE64DES");
        ZeniContextServer.getInstance().printVerboLog(stringAEncodear);
        ZeniContextServer.getInstance().printVerboLog(stringSeedEnBase64);
        try {
            final ar.com.zeni.zeniwsdl.StringSHA1BASE64DESType _return = new ar.com.zeni.zeniwsdl.StringSHA1BASE64DESType();
            final String passsha1 = GenericCodec.digestSHA1(stringAEncodear);
            final String base64passsha1 = GenericCodec.stringToBase64(passsha1);
            final byte[] byteArrayPassBase64ssha1 = GenericCodec.base64ToByte(base64passsha1);
            final byte[] bytesDePassConDESyBase64 = GenericCodec.DESencryptByte(byteArrayPassBase64ssha1, GenericCodec.base64ToByte(stringSeedEnBase64));
            final String passConDESyBase64 = GenericCodec.byteToBase64(bytesDePassConDESyBase64);
            _return.setStringSHA1(passsha1);
            _return.setStringSHA1BASE64(base64passsha1);
            _return.setStringSHA1BASE64DESBASE64(passConDESyBase64);
            _return.setEstoFuncionaSoloParaTestYNoEnProduccionString("Si, solo en test.");
            return _return;
        } catch (NoSuchAlgorithmException ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        } catch (UnsupportedEncodingException ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        } catch (Exception ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
            ZeniContextServer.getInstance().printErrorLog(ex);
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        }
    }

//	@Override
//	public String getTestIP(String in) {
//		MessageContext mc = wsContext.getMessageContext();
//		HttpServletRequest request = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
//		String ip = request.getHeader("X-Forwarded-For");
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("Proxy-Client-IP");
//			ZeniContextServer.getInstance().printDebugLog("Proxy-Client-IP " + ip);
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("WL-Proxy-Client-IP");
//			ZeniContextServer.getInstance().printDebugLog("WL-Proxy-Client-IP "+ip);
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("HTTP_CLIENT_IP");
//			ZeniContextServer.getInstance().printDebugLog("HTTP_CLIENT_IP "+ip);
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//			ZeniContextServer.getInstance().printDebugLog("HTTP_X_FORWARDED_FOR "+ip);
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getHeader("REMOTE_ADDR");
//			ZeniContextServer.getInstance().printDebugLog("REMOTE_ADDR "+ip);
//		}
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			ip = request.getRemoteAddr();
//			ZeniContextServer.getInstance().printDebugLog("getRemoteAddr "+ip);
//		}
//		return ip;
//	}

    /* (non-Javadoc)
	 * @see ar.com.zeni.zeniwsdl.ZeniWSDL#obtenerFechaDeSincronizacion(ar.com.zeni.zeniwsdl.FechaTimeType  fechaEnPc )*
     */
    @Override
    public FechaDiferenciaType obtenerFechaDeSincronizacion(FechaTimeType fechaEnPc) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerFechaDeSincronizacion");
        ZeniContextServer.getInstance().printVerboLog(fechaEnPc);
        try {
            final Date ahora = new Date();
            final Date alla = DateUtil.Converters.XMLGregorianCalendarToDate(fechaEnPc.getFecha());
            final FechaDiferenciaType _return = new ar.com.zeni.zeniwsdl.FechaDiferenciaType();
            final FechaTimeType _returnFechaOriginal = new FechaTimeType();
            final FechaTimeType _returnFechaServer = new FechaTimeType();
            _returnFechaOriginal.setFecha(fechaEnPc.getFecha());
            _returnFechaServer.setFecha(DateUtil.Converters.DateToXMLGregorianCalendar(ahora));
            _return.setFechaOriginal(_returnFechaOriginal);
            _return.setFechaServer(_returnFechaServer);
            _return.setDiferencia((int) (ahora.getTime() - alla.getTime()));
            return _return;
        } catch (Exception ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        }
    }
}
