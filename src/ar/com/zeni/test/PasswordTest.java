package ar.com.zeni.test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


import ar.com.zeni.admin.app.server.MbIdGenerator;
import ar.com.zeni.codecs.GenericCodec;
import ar.com.zeni.common.exceptions.ZeniBaseExeption;
import ar.com.zeni.common.exceptions.ZeniMailServerExeption;
import ar.com.zeni.common.exceptions.ZeniUserPasswordErroneo;
import ar.com.zeni.common.exceptions.ZeniUserRecibeAtencionComercialErroneo;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.mail.MailSender;
import ar.com.zeni.security.AuthNHashUtil;
import ar.com.zeni.security.CodigoDeAutorizacionUtil;
import ar.com.zeni.security.UsuarioAuthenticationUtil;
import ar.com.zeni.zeniwsdl.AuthType;
import ar.com.zeni.zeniwsdl.HashNSeedType;

public class PasswordTest {
	public static void main(String[] args) {
		try {
//			testDES();
//			testMail();
//			testFechas();
//			testHash1000();
//			testArmadoDePassword();
//			armarVector();
//			testCodigoDeAutorizacion();
//			testAltaUsuario();
//			testCambioDePassword();
//			testResetDePassword();
			testPasswordGeneration("123456", "carcasone el vino barato");
//			armoPasswordConSHA1DESyBASE64("rodrigomolinaesuncaponadiesabe que capo que es", GenericCodec.stringToBase64("carcasone el vino barato"));
//			testPasswordGeneration("contraseña", "saldeltipouno");
//			testPasswordGeneration("password", "saldeltipodos");
//			testPasswordGeneration("minacimiento", "saldeltipotres");
//			testPasswordGeneration("10/12/2012", "supercalifragilisticoespialidoso");
//			testPasswordGeneration("mariaysofia22", "algomuycomplejocomoparaquenoserepita");
//			testPasswordGeneration("UsuarioPrueba", "UsuarioPrueba10UsuarioPrueba10supercalifragilisticoespialidoso");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void testDES() {
		String d;
		try {
			byte[] salt = {
		        (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
		        (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03
		    };
			d = GenericCodec.DESencrypt("hola que tal", "esta es la clave");
			System.out.println("b54 de " + "1234123465465*-*-*-*-sdf¡¡'¡'¡'¡çççç" + " es " + GenericCodec.stringToBase64("1234123465465*-*-*-*-sdf¡¡'¡'¡'¡çççç"));
			System.out.println("str de " + "MTIzNDEyMzQ2NTQ2NSotKi0qLSotc2RmwqHCoSfCoSfCoSfCocOnw6fDp8On" + " es " + GenericCodec.base64ToString("MTIzNDEyMzQ2NTQ2NSotKi0qLSotc2RmwqHCoSfCoSfCoSfCocOnw6fDp8On"));
			System.out.println("clave : " + GenericCodec.byteToBase64(salt));
			final ar.com.zeni.zeniwsdl.StringSHA1BASE64DESType _return = new ar.com.zeni.zeniwsdl.StringSHA1BASE64DESType();
			final String base64passsha1 = GenericCodec.stringToBase64("12341234123412341234123412341234123412341234");
			final byte[] byteArrayPassBase64ssha1 = GenericCodec.base64ToByte(base64passsha1);
			final byte[] bytesDePassConDESyBase64 = GenericCodec.DESencryptByte(byteArrayPassBase64ssha1, byteArrayPassBase64ssha1);
			final String passConDESyBase64 = GenericCodec.byteToBase64(bytesDePassConDESyBase64);
			System.out.println("bas64salt: " + base64passsha1);
			System.out.println("bas64pass: " + base64passsha1);
			System.out.println("resul: " + passConDESyBase64);
			System.out.println("resul: " + GenericCodec.DESdecrypt64("LAX1DZ+H5P8sBfUNn4fk/ywF9Q2fh+T/LAX1DZ+H5P8sBfUNn4fk/4r1MnJ2RuQd", base64passsha1));
			System.out.println("resul: " + GenericCodec.DESdecrypt64(passConDESyBase64, base64passsha1));

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void armarVector() {
		 int[] expandTbl = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 };
		System.out.println("var sboxes = new Array();");
		for (int i = 0; i < expandTbl.length; i++) {
			System.out.println("expandTbl["+i+"]="+expandTbl[i]+";");
		}
	}
	private static void testPasswordGeneration(String password,String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//		for (int i = 0; i < 50 ; i++){
//			password = "";
//			for (int j=0; j<i; j++) {
//				password += "a";
//			}
//			try {
				final String saltBase64 = GenericCodec.stringToBase64(salt);
				final String passwordSHA1 = GenericCodec.digestSHA1(password);
				final String passwordSHA1YBase64 = GenericCodec.stringToBase64(passwordSHA1);
				final byte[] bsalt = GenericCodec.base64ToByte("VXN1YXJpb1BydWViYTM5NDFVc3VhcmlvUHJ1ZWJhMzk0MXN1cGVyY2FsaWZyYWdpbGlzdGljb2VzcGlhbGlkb3Nv");
				final byte[] bcodigodigeridoporDES = GenericCodec.getHash(GenericCodec.ITERATION_NUMBER, passwordSHA1YBase64, bsalt, null);
				final String codigodigeridoporDESenBase64 = GenericCodec.byteToBase64(bcodigodigeridoporDES);
				final String passwordParaLaBase = codigodigeridoporDESenBase64;
				final String saltParaLaBase = saltBase64;
				System.out.println("-------------------------------------------------------------------------------------------------");
				System.out.println("--- Ejemplo de password -------------------------------------------------------------------------");
				System.out.println("-------------------------------------------------------------------------------------------------");
				System.out.println("Password                                      : " + password);
				System.out.println("Paso1: passwordSHA1                           : " + passwordSHA1);
				System.out.println("Paso2: passwordSHA1YBase64                    : " + passwordSHA1YBase64);
				System.out.println("Paso3: salt                                   : " + salt);
				System.out.println("       saltBase64                             : " + saltBase64);
				System.out.println("Paso4: codigodigeridoporDESenBase64_1000_veces: " + codigodigeridoporDESenBase64);
				System.out.println("Paso5: passwordParaLaBase                     : " + passwordParaLaBase);
				System.out.println("       saltParaLaBase                         : " + saltParaLaBase);
				System.out.println("-------------------------------------------------------------------------------------------------");
				System.out.println("");
//			} catch (Exception e){
//
//			}
//		}
	}
	private static void testArmadoDePassword() {
//		String pass = "pepe mujica";
//		String seed = GenericCodec.stringToBase64("aaaassss");
		String pass = "12345679";
//		String seed = GenericCodec.stringToBase64("LTc3Mjg2NDM4NjM3NTQyMTkzNTM=");
		String seed = "LTc3Mjg2NDM4NjM3NTQyMTkzNTM=";
		try {
			armoPasswordConSHA1DESyBASE64(pass, seed);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public static void testResetDePassword() {
		System.out.println("Test Reset de password de usuario:");
		System.out.println("----------------------------------------------");
		try {
			UsuarioAuthenticationUtil uauu = new UsuarioAuthenticationUtil();
			String codigoAuto = new CodigoDeAutorizacionUtil().obtenerCodigoDeAutorizacion("UsuarioPrueba4000", "rodrigo.molina@aperturasoftware.com.ar");
			try {
				System.out.println("Prueba con codigo invalido: " + uauu.resetPassword("UsuarioPrueba4000", "rodrigo.molina@aperturasoftware.com.ar", "codigoInvalido"));
			} catch (ZeniBaseExeption e) {
				e.printStackTrace();
			}
			try {
				System.out.println("Prueba con codigo valido  : " + uauu.resetPassword("UsuarioPrueba4000", "rodrigo.molina@aperturasoftware.com.ar", codigoAuto));
			} catch (ZeniBaseExeption e) {
				e.printStackTrace();
			}
		} catch (ZeniBaseExeption e) {
			e.printStackTrace();
		}
		System.out.println("----------------------------------------------");

	}
	public static void testAltaUsuario() {
		System.out.println("Test Alta de usuario:");
		System.out.println("----------------------------------------------");
		try {
			final String[] clienteId = new String[10000];
			final String[] desss = new String[10000];
			final ResulsetObjectBuilder resbCliente = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					int i=0;
					while (rset.next()) {
						clienteId[i]=rset.getString("US");
						desss[i++]=rset.getString("DSC");
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(" select unique c.US, c.DSC from zeniweb.WEB_SUIO c order by c.US", resbCliente);

			HashNSeedType hns = AuthNHashUtil.getInstance().getNewHashNSeed("127.0.0.1");
			UsuarioAuthenticationUtil uauu = new UsuarioAuthenticationUtil();
			String sPass = armoPasswordConSHA1DESyBASE64("UsuarioPrueba", hns.getSeed());
			for (int i = 0; i < 3000; i++){
				String username = "UsuarioPrueba" + i;
				String email_usuario = "UsuarioPrueba" + i + "@serverPrueba.com.ar";
				if (i%20 == 0) {
					System.out.println(clienteId[i] + " Usuario numero " + i + " de 3000 ");
				}
				try {
				String codigoAuto = new CodigoDeAutorizacionUtil().obtenerCodigoDeAutorizacion(username, email_usuario);
				uauu.altaUsuario(MbIdGenerator.getInstance().nextId(), clienteId[i], desss[i], desss[i], desss[i] + "@serverPrueba.com.ar", sPass, codigoAuto, hns.getHash());
//				uauu.altaUsuario("13758", username, username, username, email_usuario, sPass, codigoAuto, hns.getHash());
				} catch (Exception e){
					e.printStackTrace();
				}

			}
			ZeniQueryExcecutor.excecuteUpdate(" insert into zeniweb.web_usuario_cuenta ( select wu.cliente_web_id, wd.id from  CUENTA_CLIENTE wd, zeniweb.WEB_SUIO sui, zeniweb.WEB_USUARIOS wu where wd.nrocuenta = sui.CRA and sui.US = wu.USUARIO ) ");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ZeniBaseExeption e) {
			e.printStackTrace();
		}
		System.out.println("----------------------------------------------");
	}
	public static void testCambioDePassword() {
		System.out.println("Test cambio de password:");
		System.out.println("----------------------------------------------");
		String sPassOld = null;
		String sPassNew = null;
		try {
			HashNSeedType hns = AuthNHashUtil.getInstance().getNewHashNSeed("127.0.0.1");
//			CodigoDeAutorizacionUtil impl = new CodigoDeAutorizacionUtil();
//			String creado = impl.obtenerCodigoDeAutorizacion("clientePrueba", "rodrigo.molina@aperturasoftware.com.ar");
			UsuarioAuthenticationUtil uauu = new UsuarioAuthenticationUtil();
			sPassOld = armoPasswordConSHA1DESyBASE64("VXN1YXJpb1BydWViYTQwMDBXZWQgTm92IDA3IDE5OjIzOjM5IEFSVCAyMDEy", hns.getSeed());
//			AuthType auth = uauu.autorizarUsuario("UsuarioPrueba3941", hns.getHash(), sPassOld);
			AuthType auth = uauu.autorizarUsuario("UsuarioPrueba4000", hns.getHash(), sPassOld);
			sPassNew = armoPasswordConSHA1DESyBASE64("UsuarioPrueba", hns.getSeed());
			uauu.changePassword(auth, sPassNew);
		} catch (ZeniBaseExeption e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (ZeniUserPasswordErroneo e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println(e);
		} catch (ZeniUserRecibeAtencionComercialErroneo e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("----------------------------------------------");
		System.out.println("PassOld: " + sPassOld);
		System.out.println("PassNew: " + sPassNew);
		System.out.println("----------------------------------------------");

	}
	public static void testMail() {
		System.out.println("Prueba de envio de mail:");
		System.out.println("----------------------------------------------");
		ArrayList<String> to = new ArrayList<String>();
		to.add("rodrigo.molina@aperturasoftware.com.ar");
		try {
			MailSender.sendMail("rodrigo.peti@gmail.com", to, "hola", "hola");
		} catch (ZeniMailServerExeption e) {
			e.printStackTrace();
		}
//        try
//        {
//            // Propiedades de la conexión
//            Properties props = new Properties();
//            props.setProperty("mail.smtp.host", "smtp.gmail.com");
//            props.setProperty("mail.smtp.starttls.enable", "true");
//            props.setProperty("mail.smtp.port", "587");
//            props.setProperty("mail.smtp.user", "rodrigo.peti@gmail.com");
//            props.setProperty("mail.smtp.auth", "true");
//            // Preparamos la sesion
//            Session session = Session.getDefaultInstance(props);
//
//            // Construimos el mensaje
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("rodrigo.peti@gmail.com"));
//            message.addRecipient(
//                    Message.RecipientType.TO,
//                    new InternetAddress("rodrigo.molina@aperturasoftware.com.ar"));
////            message.addRecipient(
////                            Message.RecipientType.TO,
////                            new InternetAddress("rmolina@intelap.com"));
//            message.setText(
//                "2 pesos");
//
//            // Lo enviamos.
//            Transport t = session.getTransport("smtp");
//            t.connect("smtp.gmail.com", "rodrigo.peti@gmail.com", "rodrorodro");
////            t.connect("rodrigo.peti@gmail.com", "rodrorodro");
//            boolean TRUE = true;
////            while (TRUE){
//            	t.sendMessage(message, message.getAllRecipients());
////            	Thread.sleep(5000);
////            }
//
//            // Cierre.
//            t.close();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
		System.out.println("----------------------------------------------");
	}
	public static void testCodigoDeAutorizacion() {
		System.out.println("Prueba de obtencion de codigo de autorizacion:");
		System.out.println("----------------------------------------------");
		String creado;
		try {
			CodigoDeAutorizacionUtil impl = new CodigoDeAutorizacionUtil();
			creado = impl.obtenerCodigoDeAutorizacion("altaUsuarioUser", "rodrigo.molina@aperturasoftware.com.ar");
			String obtenido = impl.obtenerCodigoDeAutorizacion("altaUsuarioUser", "rodrigo.molina@aperturasoftware.com.ar");

			System.out.println("Codigo Obtenido:   " + creado);
			System.out.println("Codigo Recuperado: " + obtenido);
			System.out.println("Estado de validez: " + impl.validarCodigoDeAutorizacion("altaUsuarioUser", "rodrigo.molina@aperturasoftware.com.ar", obtenido));
		} catch (ZeniBaseExeption e) {
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("----------------------------------------------");
	}
	public static void testFechas() {
		try {
			System.out.println("Pruebo fechas:");
			System.out.println("--------------");
			SimpleDateFormat df = new SimpleDateFormat("yyyyddMMSSSssHHmm");
			Date de = new Date();
			String se = df.format(de);
			Date dse = df.parse(se);
			String sdse = df.format(dse);
			System.out.println("DE   " + de);
			System.out.println("SE   " + se);
			System.out.println("DSE  " + dse);
			System.out.println("SDSE " + sdse);
			System.out.println("-----------------------------------");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public static String armoPasswordConSHA1DESyBASE64( String password, String seedEnBase64) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		System.out.println("Generacion de password enviada por el cliente:");
		System.out.println("----------------------------------------------");
		String passConDESyBase64 = null;
		try {
			String passsha1 = GenericCodec.digestSHA1(password);
			String base64passsha1 = GenericCodec.stringToBase64(passsha1);
			byte[] byteArrayPassBase64ssha1 = GenericCodec.base64ToByte(base64passsha1);
			byte[] bytesDePassConDESyBase64 = GenericCodec.DESencryptByte(byteArrayPassBase64ssha1, GenericCodec.base64ToByte(seedEnBase64));
			passConDESyBase64 = GenericCodec.byteToBase64(bytesDePassConDESyBase64);
			System.out.println("Password  : " + password);
			System.out.println("SHA1      : " + passsha1);
			System.out.println("BASE64    : " + base64passsha1);
			System.out.println("SeedBASE64: " + seedEnBase64);
			System.out.println("ENCPASS   : " + passConDESyBase64);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("----------------------------------------------");
		return passConDESyBase64;
	}
	public static String armoPasswordParaBase( final String sha1PasswordB64, final String saltBase64) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		System.out.println("Generacion de password en la base:");
		System.out.println("----------------------------------");
		String rtn = null;
		try {
			final byte[] salt = GenericCodec.base64ToByte(saltBase64);
			final byte[] proposedDigest = GenericCodec.getHash(GenericCodec.ITERATION_NUMBER, sha1PasswordB64, salt, null);
			rtn =  GenericCodec.byteToBase64(proposedDigest);
			System.out.println("PasswordSHA1: " + sha1PasswordB64);
			System.out.println("saltB64     : " + saltBase64);
			System.out.println("salt        : " + GenericCodec.base64ToString(saltBase64));
			System.out.println("result      : " + rtn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("----------------------------------");
		return rtn;
	}
	public static HashNSeedType testHash() throws ZeniBaseExeption{
		HashNSeedType hns = AuthNHashUtil.getInstance().getNewHashNSeed("127.0.0.1");
		HashNSeedType hns2 = AuthNHashUtil.getInstance().getHashNSeed(hns.getHash());
		AuthType auth = AuthNHashUtil.getInstance().generateAuth(hns2.getHash(), "altaUsuarioUser");
		AuthNHashUtil.getInstance().isAuthValid(auth, "127.0.0.1");
		return hns;
	}
	public static void testHash1000() {
		int veces = 1000;
		System.out.println("Test " + veces + " hash comenzando");
		System.out.println(veces +" hashes test:");
		System.out.println("----------------------------------");
		Date ini = new Date();
		BlockingQueue<Runnable> lr = new ArrayBlockingQueue<Runnable>(veces);
		ThreadPoolExecutor tp = new ThreadPoolExecutor(10, 10, 1000, TimeUnit.MILLISECONDS, lr);
		tp.allowCoreThreadTimeOut(true);
		final HashMap<String, HashNSeedType> hm = new HashMap<String, HashNSeedType>();
		final Object sync = new Object();
		for (int i = 0; i<veces; i++){
			Runnable r = new Runnable(){
				@Override
				public void run() {
					HashNSeedType ty;
					try {
						ty = testHash();
						synchronized (sync) {
							hm.put(ty.getHash(), ty);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			tp.execute(r);
		}
		while (tp.getCompletedTaskCount()<veces){
			try {
				System.out.println("Hay " + tp.getActiveCount() + " tareas ejecutandose y " + tp.getCompletedTaskCount() + " ejecutadas ");
				tp.awaitTermination(1000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Date fini = new Date();
		System.out.println("Start Date    : " + ini);
		System.out.println("End Date      : " + fini);
		System.out.println("Hash Esperados: " + veces);
		System.out.println("Hash Obtenidos: " + hm.size());
		System.out.println("----------------------------------");
	}
}