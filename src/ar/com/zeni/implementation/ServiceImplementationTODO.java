package ar.com.zeni.implementation;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.zeniwsdl.ArrayOfCondicionDePagoType;
import ar.com.zeni.zeniwsdl.AuthType;
import ar.com.zeni.zeniwsdl.CondicionDePagoType;
import ar.com.zeni.zeniwsdl.FaultType_Exception;
import ar.com.zeni.zeniwsdl.FileType;

public abstract class ServiceImplementationTODO extends ServiceSecurityImpl {
	public static String	owner	= ZeniContextServer.getInstance().getDBConsultaOwner();

	/* (non-Javadoc)
	 * @see ZeniWSDL#obtenerCondicionesDePago(AuthType  auth )*
	 */
	public ArrayOfCondicionDePagoType obtenerCondicionesDePago(AuthType auth) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCondicionesDePago");
		ZeniContextServer.getInstance().printVerboLog(auth);
		try {
			ArrayOfCondicionDePagoType _return = new ArrayOfCondicionDePagoType();
			List<CondicionDePagoType> _returnCondiciones = new ArrayList<CondicionDePagoType>();
			CondicionDePagoType _returnCondicionesVal1 = new CondicionDePagoType();
			_returnCondicionesVal1.setId("Id349250563");
			_returnCondicionesVal1.setDescripcion("Descripcion-1437245111");
			_returnCondiciones.add(_returnCondicionesVal1);
			_return.getCondiciones().addAll(_returnCondiciones);
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		//throw new FaultType_Exception("FaultType...");
	}

	/* (non-Javadoc)
	 * @see ZeniWSDL#newOperation(java.lang.String  in )*
	 */
	public FileType newOperation(java.lang.String in) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation newOperation");
		ZeniContextServer.getInstance().printVerboLog(in);
		try {
			URL fileremote = new URL("http://www.zeninet.com.ar/comprobantes/out/66332224/0001-00494108.pdf");
//    			URL fileremote = new URL(ZeniContextServer.getInstance().getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_URL_BASE)
//    					+ZeniContextServer.getInstance().getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_PARAM_1) + in
//    					+ZeniContextServer.getInstance().getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_PARAM_2) + in);
			URLConnection connection = fileremote.openConnection();
			final String casiName = connection.getHeaderField("Content-disposition");
			final String name = casiName != null ? casiName.substring(casiName.indexOf('=') != -1 ? casiName.indexOf('=') + 1 : 0) : "noname";
			final String nameAlone = name.substring(0, name.indexOf('.') != -1 ? name.indexOf('.') : name.length());
			final String extAlone = name.indexOf('.') != -1 ? name.substring(name.indexOf('.') + 1) : "";
//    			BufferedInputStream bifin = new BufferedInputStream(fileremote.openStream());
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
			} catch (Exception e) {
				throw e;
			} finally {
				icon.close();
				bifin.close();
				bais.flush();
			}
			_returnData = bais.toByteArray();
			bais.close();
			final FileType _return = new FileType();
			_return.setData(_returnData);
			_return.setNombreConExtension(name);
			_return.setNombre(nameAlone);
			_return.setExtension(extAlone);
			_return.setTamanio(_returnData.length);
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
		}
	}

	public ar.com.zeni.zeniwsdl.ArrayOfExtensionesPerIVAType obtenerSituacionImpositivaInformacionImpositiva(ar.com.zeni.zeniwsdl.AuthType auth, java.lang.String cuentaId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerSituacionImpositivaInformacionImpositiva");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(cuentaId);
		try {
			ar.com.zeni.zeniwsdl.ArrayOfExtensionesPerIVAType _return = new ar.com.zeni.zeniwsdl.ArrayOfExtensionesPerIVAType();
			java.util.List<ar.com.zeni.zeniwsdl.ExtensionesPerIVAType> _returnExtes = new java.util.ArrayList<ar.com.zeni.zeniwsdl.ExtensionesPerIVAType>();
			ar.com.zeni.zeniwsdl.ExtensionesPerIVAType _returnExtesVal1 = new ar.com.zeni.zeniwsdl.ExtensionesPerIVAType();
			_returnExtesVal1.setPorcentaje("Porcentaje-396731798");
			ar.com.zeni.zeniwsdl.FechaTimeType _returnExtesVal1Fecalta = new ar.com.zeni.zeniwsdl.FechaTimeType();
			_returnExtesVal1Fecalta.setFecha(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar("2012-11-01T16:07:51.282-03:00"));
			_returnExtesVal1.setFecalta(_returnExtesVal1Fecalta);
			ar.com.zeni.zeniwsdl.FechaTimeType _returnExtesVal1Fecvto = new ar.com.zeni.zeniwsdl.FechaTimeType();
			_returnExtesVal1Fecvto.setFecha(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar("2012-11-01T16:07:51.282-03:00"));
			_returnExtesVal1.setFecvto(_returnExtesVal1Fecvto);
			_returnExtesVal1.setNro("Nro1683652096");
			_returnExtes.add(_returnExtesVal1);
			_return.getExtes().addAll(_returnExtes);
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		//throw new FaultType_Exception("FaultType...");
	}

	public ar.com.zeni.zeniwsdl.SituacionImpositivaRegistroFiscalType obtenerSituacionImpositivaRegistroFiscal(ar.com.zeni.zeniwsdl.AuthType auth, java.lang.String cuentaId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerSituacionImpositivaRegistroFiscal");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(cuentaId);
		try {
			ar.com.zeni.zeniwsdl.SituacionImpositivaRegistroFiscalType _return = new ar.com.zeni.zeniwsdl.SituacionImpositivaRegistroFiscalType();
			_return.setCbu("Cbu1195743382");
			_return.setNroBoletin("NroBoletin1187586858");
			ar.com.zeni.zeniwsdl.FechaTimeType _returnFechaAlta = new ar.com.zeni.zeniwsdl.FechaTimeType();
			_returnFechaAlta.setFecha(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar("2012-11-01T15:58:16.314-03:00"));
			_return.setFechaAlta(_returnFechaAlta);
			ar.com.zeni.zeniwsdl.ArrayOfResolGeneralType _returnResGeneral = new ar.com.zeni.zeniwsdl.ArrayOfResolGeneralType();
			java.util.List<ar.com.zeni.zeniwsdl.ResolGeneralType> _returnResGeneralExtes = new java.util.ArrayList<ar.com.zeni.zeniwsdl.ResolGeneralType>();
			ar.com.zeni.zeniwsdl.ResolGeneralType _returnResGeneralExtesVal1 = new ar.com.zeni.zeniwsdl.ResolGeneralType();
			_returnResGeneralExtesVal1.setCuenta("Cuenta-242231663");
			_returnResGeneralExtesVal1.setRazonSocial("RazonSocial-1283927646");
			_returnResGeneralExtesVal1.setDomicilio("Domicilio-962441496");
			_returnResGeneralExtes.add(_returnResGeneralExtesVal1);
			_returnResGeneral.getExtes().addAll(_returnResGeneralExtes);
			_return.setResGeneral(_returnResGeneral);
			return _return;
		} catch (java.lang.Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		//throw new FaultType_Exception("FaultType...");
	}
}
