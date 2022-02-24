package ar.com.zeni.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import ar.com.zeni.admin.app.server.MbIdGenerator;
import ar.com.zeni.common.DateUtil.ToString;
import ar.com.zeni.common.exceptions.ZeniAuthTypeInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.common.exceptions.ZeniHashInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniIPDifiereExeption;
import ar.com.zeni.common.exceptions.ZeniServerExeption;
import ar.com.zeni.db.ZeniInsertBuilder;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.mail.MailProperties;
import ar.com.zeni.security.AuthNHashUtil;
import ar.com.zeni.security.UsuarioAuthenticationUtil;
import ar.com.zeni.security.UsuarioAuthenticationUtil.UserData;
import ar.com.zeni.zeniwsdl.ArrayOfBoletosCartaOferta;
import ar.com.zeni.zeniwsdl.ArrayOfCalidadEntregas;
import ar.com.zeni.zeniwsdl.ArrayOfCartaPorteReg;
import ar.com.zeni.zeniwsdl.ArrayOfSectorReclamos;
import ar.com.zeni.zeniwsdl.ArrayOfSolicitudInformacion;
import ar.com.zeni.zeniwsdl.AuthType;
import ar.com.zeni.zeniwsdl.BoletoCartaOfertaReg;
import ar.com.zeni.zeniwsdl.CalidadEntrega;
import ar.com.zeni.zeniwsdl.CartaPorteReg;
import ar.com.zeni.zeniwsdl.EnumBoletoCartaPorte;
import ar.com.zeni.zeniwsdl.FaultType;
import ar.com.zeni.zeniwsdl.FaultType_Exception;
import ar.com.zeni.zeniwsdl.FileType;
import ar.com.zeni.zeniwsdl.RangoFechaType;
import ar.com.zeni.zeniwsdl.SectorReclamo;
import ar.com.zeni.zeniwsdl.SolicitudInformacionReg;
import ar.com.zeni.zeniwsdl.rest.RestExecute;

/**
 *
 * @author ogagliano
 *
 */
public class ZeniHelper {

    private static final Logger LOG = Logger.getLogger("org.apache.cxf.services.ZeniWSDL.ZeniWSDLSOAP.ZeniWSDL");

    /**
     *
     * @param cuentaCliente
     * @param mercadoID
     * @param monedaID
     * @return
     */
    public static Float obtenerRequisitoGtias(String cuentaCliente, String mercadoID, String monedaID) {
        final List<Float> _returnGarantias = new ArrayList<Float>();
        try {

            final StringBuilder querySBuilder = new StringBuilder()
                    .append("SELECT " + obtenerCampoDBRequisitoGtias(cuentaCliente, mercadoID, monedaID)
                            + " data FROM cuenta_cliente WHERE id = '" + cuentaCliente + "' ");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            _returnGarantias.add(rset.getFloat("data"));
                        } catch (Exception e) {
                            ZeniContextServer.getInstance().printErrorLog(e);
                            throw new SQLException(e.getMessage(), e);
                        }
                    }
                }
            };

            ZeniContextServer.getInstance().printErrorLog("ANTES DE EJECUTAR: ");
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            ZeniContextServer.getInstance().printErrorLog("TERMINO: ");

        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }

        return _returnGarantias.size() > 0 ? _returnGarantias.get(0) : null;
    }

    /**
     *
     * @param cuentaCliente
     * @param mercadoID
     * @param monedaID
     * @return
     */
    public static String obtenerCampoDBRequisitoGtias(String cuentaCliente, String mercadoID, String monedaID) {
        String req = "";
        try {
            String cuentaClienteId = cuentaCliente;
            String mercadoId = mercadoID;
            String monedaId = monedaID;

            if (!"".equals(cuentaClienteId)) {

                if (mercadoId.equals(ZeniConstants.id_mercado_matba)) {
                    if (monedaId.equals(ZeniConstants.id_moneda_ARG)) {
                        req = "requisitoGarantias";
                    } else {
                        req = "requisitoGarantiasMD";
                    }
                } else if (mercadoId.equals(ZeniConstants.id_mercado_rofex)) {
                    if (monedaId.equals(ZeniConstants.id_moneda_ARG)) {
                        req = "requisitoGarantiasRS";
                    } else {
                        req = "requisitoGarantiasRD";
                    }
                }
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
        LOG.info("campo DB[" + req + "]");
        return req;
    }

    /**
     *
     * #1808
     *
     * @param cuenta
     * @param rangoFecha
     * @param producto
     * @return
     * @throws FaultType_Exception
     */
    public static ArrayOfCalidadEntregas obtenerCalidadEntregas(AuthType auth, WebServiceContext wsContext,
            String cuenta, final RangoFechaType rangoFecha, String producto) throws FaultType_Exception {

        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCalidadEntregas");
        ZeniContextServer.getInstance()
                .printInfoLog("cuenta[" + cuenta + "] rangoFecha[" + rangoFecha + "] producto[" + producto + "]");

        final ArrayOfCalidadEntregas _return = new ArrayOfCalidadEntregas();

        try {

            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            if (cuenta == null || cuenta.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("la cuenta no pueden ser nulo o vacia", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (producto == null || producto.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El producto no pueden ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFecha == null || rangoFecha.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El rango de fecha no pueden ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            if (rangoFecha.getDesde() == null || rangoFecha.getDesde().getFecha() == null
                    || rangoFecha.getDesde().getFecha().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("La fecha desde no pueden ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFecha.getHasta() == null || rangoFecha.getHasta().getFecha() == null
                    || rangoFecha.getHasta().getFecha().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("La fecha hasta no pueden ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }

            final List<CalidadEntrega> _registros = new ArrayList<CalidadEntrega>();

            final StringBuilder querySBuilder = new StringBuilder()
                    .append(" SELECT 	ep.fechaalta fecha, e.NROCARTADEPORTE NroComOrig, e.nroremito Turno, ")
                    .append(" coalesce(eda.pesoprocedencia,0) KgsOrigen,  coalesce(e.cantidadentrega,0) KgsDestino, ")
                    .append(" (eda.pesoprocedencia  -  e.cantidadentrega) KgsDiferencia , ")
                    // Pedido Norberto Mail 31 Mar 2017 15:37:04
                    // .append(" e.CANTIDADMERMAPORSECADO MermaHumedad,
                    // ").append(" eda.KGSVOLATILIDAD MermaVolatil, ")
                    .append(" coalesce(e.CANTIDADMERMAPORSECADO,0) MermaHumedad, ")
                    .append(" coalesce(e.CANTIDADOTRASMERMAS,0) MermaVolatil, ")
                    .append(" coalesce(detalle.mermacalculada,0) mermaCalidad, ")
                    // .append("
                    // e.cantidadentrega-(coalesce(e.CANTIDADMERMAPORSECADO,0)+coalesce(detalle.mermacalculada,0)+coalesce(eda.kgsVolatilidad,0))
                    // kgsNetos, ")
                    .append(" e.cantidadentrega - (coalesce(e.CANTIDADMERMAPORSECADO,0) + coalesce(e.CANTIDADOTRASMERMAS,0) + coalesce(e.CANTIDADMERMAPORZARANDEO,0))  kgsNetos, ")
                    .append(" CalidadCalculada  BonifRebaja, ").append(" e.porcentajehumedad Humedad, ")
                    .append(" coalesce(detalle.CposExtranos,0) CposExtranos, ")
                    .append(" coalesce(detalle.Tierra,0) Tierra, ").append(" coalesce(detalle.Partidos,0) Partidos, ")
                    .append(" coalesce(detalle.daniados,0) daniados, ")
                    .append(" coalesce(detalle.GranosVerdes,0) GranosVerdes, ")
                    .append(" coalesce(detalle.CalidadCalculada,0) CalidadCalculada, prod.nombre producto")
                    .append(" FROM entrega_partida ep ").append(" INNER JOIN entrega  e ON (ep.entrega_id = e.id) ")
                    .append(" INNER JOIN contrato c ON (c.id = ep.contrato_id) ")
                    .append(" INNER JOIN cuenta_cliente cc ON (c.VENDEDOR_ID = cc.id) ")
                    .append(" INNER JOIN cliente cl ON (cl.id = cc.CLIENTE_ID) ")
                    .append(" INNER JOIN producto prod on (c.producto_id = prod.id) ")
                    .append(" LEFT JOIN  analisis_muestra am ON am.id = e.ANALISISMUESTRA_ID ")
                    .append(" LEFT JOIN entrega_datos_adicionales eda ON eda.ENTREGA_ID = e.id ").append(" LEFT JOIN  ")
                    .append(" ( 	SELECT dam.ANALISISMUESTRA_ID analisismuestra_id, ")
                    .append(" sum(dam.mermaencantidad) mermacalculada, ")
                    .append(" sum(case crc.CODFINAL when 204 then dam.resultado else 0 end) CposExtranos , ")
                    .append(" sum(case crc.CODFINAL when 226 then dam.resultado else 0 end) Tierra  , ")
                    .append(" sum(case crc.CODFINAL when 208 then dam.resultado else 0 end) Partidos , ")
                    .append(" sum(case crc.CODFINAL when 213 then dam.resultado else 0 end) daniados, ")
                    .append(" sum(case crc.CODFINAL when 263 then dam.resultado else 0 end) GranosVerdes , ")
                    .append(" sum(case crc.CODFINAL when 204 then dam.porcentajebonificacionrebaja  ")
                    .append(" when 226 then dam.porcentajebonificacionrebaja  ")
                    .append(" when 208 then dam.porcentajebonificacionrebaja ")
                    .append(" when 213 then dam.porcentajebonificacionrebaja ")
                    .append(" when 263 then dam.porcentajebonificacionrebaja ").append(" else 0 end) CalidadCalculada ")
                    .append(" FROM DETALLE_ANALISIS_MUESTRA dam ")
                    .append(" INNER JOIN CONCEPTO_RUBRO_CALIDAD crc ON (crc.id = dam.CONCEPTORUBROCALIDAD_ID) ")
                    .append(" GROUP BY dam.ANALISISMUESTRA_ID  ) detalle on (detalle.analisismuestra_id = e.ANALISISMUESTRA_ID) ")
                    .append(" WHERE  cc.id IN ('" + cuenta + "')  ")
                    .append(" AND TRUNC(ep.FECHAALTA) >= TO_DATE('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getDesde())
                            + "','yyyy-MM-dd') ")
                    .append(" AND TRUNC(ep.FECHAALTA) <= TO_DATE('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getHasta())
                            + "','yyyy-MM-dd') ")
                    .append(" AND c.producto_id = '" + producto + "' ").append(" AND e.fechabaja IS NULL ")
                    .append(" AND ep.fechabaja IS NULL ");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            CalidadEntrega _calidad = new CalidadEntrega();

                            _calidad.setBonifRebaja(rset.getFloat("BonifRebaja"));
                            _calidad.setCalidadCalculada(rset.getFloat("CalidadCalculada"));
                            _calidad.setCposExtranos(rset.getFloat("CposExtranos"));
                            _calidad.setDaniados(rset.getFloat("daniados"));
                            _calidad.setFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecha"))));
                            _calidad.setGranosVerdes(rset.getFloat("GranosVerdes"));
                            _calidad.setHumedad(rset.getFloat("Humedad"));
                            _calidad.setKgsDestino(rset.getFloat("KgsDestino") * 1000);
                            _calidad.setKgsDiferencia(rset.getFloat("KgsDiferencia"));
                            _calidad.setKgsNetos(rset.getFloat("kgsNetos") * 1000);
                            _calidad.setKgsOrigen(rset.getFloat("KgsOrigen"));
                            _calidad.setMermaCalidad(rset.getFloat("mermaCalidad") * 1000);
                            _calidad.setMermaHumedad(rset.getFloat("MermaHumedad") * 1000);
                            _calidad.setMermaVolatil(rset.getFloat("MermaVolatil") * 1000);
                            _calidad.setNroComOrig(rset.getString("NroComOrig"));
                            _calidad.setPartidos(rset.getFloat("Partidos"));
                            _calidad.setProducto(rset.getString("producto"));
                            _calidad.setTierra(rset.getFloat("Tierra"));
                            _calidad.setTurno(rset.getString("Turno"));

                            _registros.add(_calidad);

                        } catch (Exception e) {
                            ZeniContextServer.getInstance().printErrorLog(e);
                            throw new SQLException(e.getMessage(), e);
                        }
                    }
                }
            };

            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getCalidadDeEntrega().addAll(_registros);

        } catch (ZeniIPDifiereExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
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
        return _return;
    }

    /**
     *
     * @param auth
     * @param wsContext
     * @param cuentas
     * @param email
     * @param body
     * @param productos
     * @param cuenta
     * @return
     */
    public static int solicitudDeInformacion(AuthType auth, WebServiceContext wsContext, String email, String body,
            String cuenta, List<String> table, String bodyTitle) throws FaultType_Exception {
        // Inserto la solicitud del
        int result = -1;
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            String id = MbIdGenerator.getInstance().nextId();
            String queryInsert = new ZeniInsertBuilder().insertinsertinto(ZeniConstants.WEBINFORMACION.TABLE_NAME)
                    .fields(ZeniConstants.WEBINFORMACION.ID_MAIL_INFORMACION, ZeniConstants.WEBINFORMACION.MAIL_BODY,
                            ZeniConstants.WEBINFORMACION.MAIL_FROM, ZeniConstants.WEBINFORMACION.MAIL_TO,
                            ZeniConstants.WEBINFORMACION.STATUS, ZeniConstants.WEBINFORMACION.RETRIES,
                            ZeniConstants.WEBINFORMACION.USER_INSERT, ZeniConstants.WEBINFORMACION.INSERT_DATE)
                    .values(id, body, "GestionDeAvisos@zeni.com.ar", email, "P", "0", auth.getUser(),
                            ToString.yyyyMMddHHmmss(GregorianCalendar.getInstance().getTime()))
                    .getInsertQuery();

            int db = ZeniQueryExcecutor.excecuteUpdateReturn(queryInsert);

            ZeniContextServer.getInstance().printInfoLog("result Insert {" + db + "}");
            RestExecute exucute = new RestExecute("http://localhost:8080/mailSrv/send", "18000");

            MailProperties prop2 = new MailProperties();
            prop2.setSubject("Solicitud de Informacion");

            prop2.setIdMail(id);
            prop2.setBodyTitle(bodyTitle);
            prop2.setBodySection1("Email: " + email);
            prop2.setBodySection2(cuenta);
            prop2.getData().put("TH", "Solicitud-Requerido");
            prop2.setTabla(table);
            prop2.setTo("GestionDeAvisos@zeni.com.ar");

            result = exucute.send(prop2);

            if (result != 0) {
                throw new ZeniServerExeption("Ocurrio un error al enviar el mail", FAULTCONSTANTS.ERROR_ERRONEO);
            }

        } catch (ZeniIPDifiereExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
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
        return result;
    }

    /**
     *
     * @param auth
     * @param wsContext
     * @return
     * @throws FaultType_Exception
     */
    public static ArrayOfSolicitudInformacion obtenerSolicitudInformacion(AuthType auth, String cuentaId,
            WebServiceContext wsContext) throws FaultType_Exception {

        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerSolicitudInformacion");

        final ArrayOfSolicitudInformacion _return = new ArrayOfSolicitudInformacion();

        try {

            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            final List<SolicitudInformacionReg> _registros = new ArrayList<SolicitudInformacionReg>();

            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select cl.cuit, cl.razonSocial, em.correo, cc.nroCuenta, cc.denominacionCuenta, ")
                    .append("CASE de.tipoOperacion ")
                    .append("WHEN 'C' THEN 'Aviso de Negocio' ")
                    .append("WHEN 'B' THEN 'Boletos' ")
                    .append("WHEN 'F' THEN 'Fijaciones' ")
                    .append("WHEN 'L' THEN 'Clientes' ")
                    .append("WHEN 'U' THEN 'Cupos' ")
                    .append("WHEN 'H' THEN 'Habilitacion en Bolsa' ")
                    .append("WHEN 'O' THEN 'Orden de Carga (Subproductos)' ")
                    .append("WHEN 'D' THEN 'Doc. Impositiva' ")
                    .append("WHEN 'I' THEN 'Informe Diario' ")
                    .append("WHEN 'M' THEN 'Mercado a Termino' ")
                    .append("WHEN 'N' THEN 'Nomina' ")
                    .append("WHEN 'G' THEN 'Envio de Informacion Digital' ")
                    .append("WHEN 'T' THEN 'Informe Tenencia Valorizada' ")
                    .append("WHEN 'V' THEN 'Operaciones FCI' ")
                    .append("WHEN 'R' THEN 'Envio de Retenciones y Comprobantes' ")
                    .append("ELSE '' END as tipoOperacionDesc, ")
                    .append("tipoOperacion, de.recibesiescomprador comprador, de.recibesiesvendedor vendedor ")
                    .append("from cliente cl ")
                    .append("inner join cuenta_cliente cc on cc.cliente_id = cl.id ")
                    .append("inner join email em on em.cuentacliente_id = cc.id ")
                    .append("inner join destinatario_envio_email de on de.email_id = em.id ")
                    .append("where em.fechaBaja IS NULL ")
                    .append("and cc.fechaBaja IS NULL ")
                    //.append("--and cl.cuit LIKE '30-67172933-8'  ")
                    .append("AND cc.ID = '" + cuentaId + "' ")
                    .append("and em.correo NOT LIKE '%zeni.com.ar%'  ")
                    .append("order by cc.nroCuenta,correo, de.tipoOperacion ");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {

                    String email = null;
                    SolicitudInformacionReg _reg = null;
                    while (rset.next()) {
                        try {

                            if (email == null) {
                                email = rset.getString("CORREO");
                                _reg = new SolicitudInformacionReg();
                                _reg.setMailTo(email);
                            }

                            if (!rset.getString("CORREO").equals(email)) {
                                _registros.add(_reg);
                                email = rset.getString("CORREO");
                                _reg = new SolicitudInformacionReg();
                                _reg.setMailTo(email);
                            }

                            setInfo(_reg, rset.getString("tipoOperacion"), rset.getBoolean("COMPRADOR"),
                                    rset.getBoolean("VENDEDOR"));

                        } catch (Exception e) {
                            ZeniContextServer.getInstance().printErrorLog(e);
                            throw new SQLException(e.getMessage(), e);
                        }
                    }

                    if (_reg != null && _registros != null) {
                        _registros.add(_reg);
                    }
                }

            };

            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getSolicitudes().addAll(_registros);

        } catch (ZeniIPDifiereExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
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
        return _return;
    }

    /**
     *
     * @param _reg
     * @param tipo
     * @param comprador
     * @param vendedor
     */
    private static void setInfo(SolicitudInformacionReg _reg, String tipo, boolean comprador, boolean vendedor) {

        if (ZeniConstants.ENVIO_MAIL_TIPO.Aviso_Negocio.VALUE.equals(tipo)) {
            if (comprador) {
                _reg.setAvisoNegocioComprador(true);
            }
            if (vendedor) {
                _reg.setAvisoNegocioVendedor(true);
            }

        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Boletos.VALUE.equals(tipo)) {

            if (comprador) {
                _reg.setBoletosComprador(true);
            }
            if (vendedor) {
                _reg.setBoletosVendedor(true);
            }

        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Cupos.VALUE.equals(tipo)) {

            if (comprador) {
                _reg.setCuposComprador(true);
            }
            if (vendedor) {
                _reg.setCuposVendedor(true);
            }

        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Doc_Impositiva.VALUE.equals(tipo)) {
            _reg.setDocImpositivo(true);
        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Fijaciones.VALUE.equals(tipo)) {

            if (comprador) {
                _reg.setFijacionesComprador(true);
            }
            if (vendedor) {
                _reg.setFijacionesVendedor(true);
            }

        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Habilitacion_Bolsa.VALUE.equals(tipo)) {
            _reg.setHabilitacionBolsa(true);
        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Informacion_Digital.VALUE.equals(tipo)) {

            if (comprador) {
                _reg.setInfoDigitalComprador(true);
            }
            if (vendedor) {
                _reg.setInfoDigitalVendedor(true);
            }

        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Informe_Diario.VALUE.equals(tipo)) {
            _reg.setInformeDiario(true);
        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Mercado_Termino.VALUE.equals(tipo)) {
            _reg.setMat(true);
        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Orden_Carga.VALUE.equals(tipo)) {
            _reg.setOrdenCarga(true);
        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Retenciones_Comprobantes.VALUE.equals(tipo)) {

            if (comprador) {
                _reg.setRetencionComprobantesComprador(true);
            }
            if (vendedor) {
                _reg.setRetencionComprobantesVendedor(true);
            }

        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Informe_Tenencia_Valorizada.VALUE.equals(tipo)) {
            _reg.setTenenciaValorizada(true);
        } else if (ZeniConstants.ENVIO_MAIL_TIPO.Operaciones_FCI.VALUE.equals(tipo)) {
            _reg.setOpFCI(true);
        }
    }

    /**
     *
     * @param auth
     * @param wsContext
     * @param reg
     * @return
     * @throws FaultType_Exception
     */
    public static int addSolicitudDeInformacion(AuthType auth, WebServiceContext wsContext, SolicitudInformacionReg reg)
            throws FaultType_Exception {
        int result = -1;
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            final UserData u = new UsuarioAuthenticationUtil()
                    .getUserData(AuthNHashUtil.getInstance().getHashNSeed(auth.getHash()).getUser());
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            String id = MbIdGenerator.getInstance().nextId();
            String queryInsert = new ZeniInsertBuilder()
                    .insertinsertinto(ZeniConstants.WEB_SOLICITUD_INFORMACION.TABLE_NAME)
                    .fields(ZeniConstants.WEB_SOLICITUD_INFORMACION.ID_MAIL_INFORMACION,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.CLIENTE_WEB_ID,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.USUARIO,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.MAIL_TO,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.AVISO_NEGOCIO_COMPRADOR,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.AVISO_NEGOCIO_VENDEDOR,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.BOLETOS_COMPRADOR,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.BOLETOS_VENDEDOR,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.CUPOS_COMPRADOR,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.CUPOS_VENDEDOR,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.DOC_IMPOSITIVO,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.INFO_DIGITAL,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.RETENCION_COMPROBANTES,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.FIJACIONES,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.HABILITACION_BOLSA,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.INFORME_DIARIO,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.TENENCIA_VALORIZADA,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.MAT, ZeniConstants.WEB_SOLICITUD_INFORMACION.OP_FCI,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.ORDEN_CARGA,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.USER_INSERT,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.INSERT_DATE,
                            ZeniConstants.WEB_SOLICITUD_INFORMACION.CUENTA_ID)
                    .values(id, u.getClienteid(), u.getUsername(), reg.getMailTo(),
                            resolveString(reg.isAvisoNegocioComprador()), resolveString(reg.isAvisoNegocioVendedor()),
                            resolveString(reg.isBoletosComprador()), resolveString(reg.isBoletosVendedor()),
                            resolveString(reg.isCuposComprador()), resolveString(reg.isCuposVendedor()),
                            resolveString(reg.isDocImpositivo()), resolveString(reg.isInfoDigitalComprador()),
                            resolveString(reg.isRetencionComprobantesComprador()), resolveString(reg.isFijacionesComprador()),
                            resolveString(reg.isHabilitacionBolsa()), resolveString(reg.isInformeDiario()),
                            resolveString(reg.isTenenciaValorizada()), resolveString(reg.isMat()),
                            resolveString(reg.isOpFCI()), resolveString(reg.isOrdenCarga()), auth.getUser(),
                            ToString.yyyyMMddHHmmss(GregorianCalendar.getInstance().getTime()), reg.getCuentaID())
                    .getInsertQuery();

            result = ZeniQueryExcecutor.excecuteUpdateReturn(queryInsert);

            ZeniContextServer.getInstance().printInfoLog("result Insert {" + result + "}");

        } catch (ZeniIPDifiereExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
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
        return result;
    }

    /**
     *
     * @param auth
     * @param wsContext
     * @param id
     * @return
     * @throws FaultType_Exception
     */
    public static int deleteSolicitudDeInformacion(AuthType auth, WebServiceContext wsContext, String id)
            throws FaultType_Exception {
        int result = -1;
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            String queryDelete = "DELETE FROM " + ZeniConstants.WEB_SOLICITUD_INFORMACION.TABLE_NAME;
            queryDelete += " WHERE  ID_SOLICITUD_INFORMACION IN ('" + id + "')";

            result = ZeniQueryExcecutor.excecuteUpdateReturn(queryDelete);
            ZeniContextServer.getInstance().printInfoLog("result delete {" + result + "}");

        } catch (ZeniIPDifiereExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
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
        return result;
    }

    /**
     *
     * @param auth
     * @param wsContext
     * @param solicitud
     * @return
     */
    public static int editSolicitudDeInformacion(AuthType auth, WebServiceContext wsContext,
            SolicitudInformacionReg reg) throws FaultType_Exception {

        int result = -1;
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            StringBuffer queryUpdate = new StringBuffer();
            queryUpdate.append("UPDATE " + ZeniConstants.WEB_SOLICITUD_INFORMACION.TABLE_NAME + " SET ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.MAIL_TO + " = '" + reg.getMailTo() + "', ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.AVISO_NEGOCIO_COMPRADOR + "="
                    + resolveStringDB(reg.isAvisoNegocioComprador()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.AVISO_NEGOCIO_VENDEDOR + "="
                    + resolveStringDB(reg.isAvisoNegocioVendedor()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.BOLETOS_COMPRADOR + "="
                    + resolveStringDB(reg.isBoletosComprador()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.BOLETOS_VENDEDOR + "="
                    + resolveStringDB(reg.isBoletosVendedor()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.CUPOS_COMPRADOR + "="
                    + resolveStringDB(reg.isCuposComprador()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.CUPOS_VENDEDOR + "="
                    + resolveStringDB(reg.isCuposVendedor()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.DOC_IMPOSITIVO + "="
                    + resolveStringDB(reg.isDocImpositivo()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.INFO_DIGITAL + "="
                    + resolveStringDB(reg.isInfoDigitalComprador()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.RETENCION_COMPROBANTES + "="
                    + resolveStringDB(reg.isRetencionComprobantesComprador()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.FIJACIONES + "="
                    + resolveStringDB(reg.isFijacionesComprador()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.HABILITACION_BOLSA + "="
                    + resolveStringDB(reg.isHabilitacionBolsa()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.INFORME_DIARIO + "="
                    + resolveStringDB(reg.isInformeDiario()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.TENENCIA_VALORIZADA + "="
                    + resolveStringDB(reg.isTenenciaValorizada()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.MAT + "=" + resolveStringDB(reg.isMat()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.ORDEN_CARGA + "="
                    + resolveStringDB(reg.isOrdenCarga()) + ", ");
            queryUpdate.append(
                    ZeniConstants.WEB_SOLICITUD_INFORMACION.OP_FCI + "=" + resolveStringDB(reg.isOpFCI()) + ", ");
            queryUpdate.append(ZeniConstants.WEB_SOLICITUD_INFORMACION.UPDATE_DATE + "='"
                    + ToString.yyyyMMddHHmmss(GregorianCalendar.getInstance().getTime()) + "' ");
            queryUpdate.append(" WHERE " + ZeniConstants.WEB_SOLICITUD_INFORMACION.ID_MAIL_INFORMACION + "= '"
                    + reg.getIdSolicitudInformacion() + "'");

            result = ZeniQueryExcecutor.excecuteUpdateReturn(queryUpdate.toString());

            ZeniContextServer.getInstance().printInfoLog("result Insert {" + result + "}");

        } catch (ZeniIPDifiereExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
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
        return result;

    }

    /**
     *
     * @param valor
     * @return
     */
    private static String resolveString(boolean valor) {
        if (valor) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     *
     * @param valor
     * @return
     */
    private static String resolveStringDB(boolean valor) {
        if (valor) {
            return "'1'";
        } else {
            return "'0'";
        }
    }

    /**
     *
     * @param auth
     * @param wsContext
     * @param email
     * @param nombre
     * @param cuenta
     * @return
     * @throws FaultType_Exception
     */
    public static int solicitudZeniApi(AuthType auth, WebServiceContext wsContext, String email, String nombre,
            String cuenta) throws FaultType_Exception { // Inserto la solicitud del
        int result = -1;
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            String body = cuenta + " - " + nombre + " - " + " - " + email;

            String id = MbIdGenerator.getInstance().nextId();
            String queryInsert = new ZeniInsertBuilder().insertinsertinto(ZeniConstants.WEBINFORMACION.TABLE_NAME)
                    .fields(ZeniConstants.WEBINFORMACION.ID_MAIL_INFORMACION, ZeniConstants.WEBINFORMACION.MAIL_BODY,
                            ZeniConstants.WEBINFORMACION.MAIL_FROM, ZeniConstants.WEBINFORMACION.MAIL_TO,
                            ZeniConstants.WEBINFORMACION.STATUS, ZeniConstants.WEBINFORMACION.RETRIES,
                            ZeniConstants.WEBINFORMACION.USER_INSERT, ZeniConstants.WEBINFORMACION.INSERT_DATE)
                    .values(id, body, null, email, "P", "0", auth.getUser(),
                            ToString.yyyyMMddHHmmss(GregorianCalendar.getInstance().getTime()))
                    .getInsertQuery();

            int db = ZeniQueryExcecutor.excecuteUpdateReturn(queryInsert);

            ZeniContextServer.getInstance().printInfoLog("result Insert {" + db + "}");
            RestExecute exucute = new RestExecute("http://localhost:8080/mailSrv/send", "18000");

            MailProperties prop2 = new MailProperties();
            prop2.setIdMail(id);
            prop2.setSubject("Solicitud ZeniAPI");
            prop2.setBodyTitle("Solicitud Credenciales - ZeniAPI");
            prop2.setBodySection1(cuenta);
            prop2.setBodySection2(nombre);
            prop2.setBodySection3(email);

            result = exucute.send(prop2);

            if (result != 0) {
                throw new ZeniServerExeption("Ocurrio un error al enviar el mail", FAULTCONSTANTS.ERROR_ERRONEO);
            }

        } catch (ZeniIPDifiereExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
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
        return result;
    }

    /**
     *
     * @param wsContext
     * @param email
     * @param usuario
     * @param nombre
     * @return
     */
    public static int recuperaPassword(WebServiceContext wsContext, String email, String usuario, String nombre) throws FaultType_Exception {
        int result = -1;
        try {
            String body = usuario + " - " + nombre + " - " + " - " + email;

            String id = MbIdGenerator.getInstance().nextId();
            String queryInsert = new ZeniInsertBuilder().insertinsertinto(ZeniConstants.WEBINFORMACION.TABLE_NAME)
                    .fields(ZeniConstants.WEBINFORMACION.ID_MAIL_INFORMACION, ZeniConstants.WEBINFORMACION.MAIL_BODY,
                            ZeniConstants.WEBINFORMACION.MAIL_FROM, ZeniConstants.WEBINFORMACION.MAIL_TO,
                            ZeniConstants.WEBINFORMACION.STATUS, ZeniConstants.WEBINFORMACION.RETRIES,
                            ZeniConstants.WEBINFORMACION.USER_INSERT, ZeniConstants.WEBINFORMACION.INSERT_DATE)
                    .values(id, body, null, email, "P", "0", usuario,
                            ToString.yyyyMMddHHmmss(GregorianCalendar.getInstance().getTime()))
                    .getInsertQuery();

            int db = ZeniQueryExcecutor.excecuteUpdateReturn(queryInsert);

            ZeniContextServer.getInstance().printInfoLog("result Insert {" + db + "}");
            RestExecute exucute = new RestExecute("http://localhost:8080/mailSrv/send", "18000");

            MailProperties prop2 = new MailProperties();
            prop2.setIdMail(id);
            prop2.setSubject("Recuperar Contraseña");
            prop2.setBodyTitle("Solicitud de Contraseña");
            prop2.setBodySection1("Usuario: " + usuario);
            prop2.setBodySection2("Nombre: " + nombre);
            prop2.setBodySection3("Email: " + email);
//			prop2.setTo("omar.gagliano@gmail.com");
            prop2.setTo("info@zeni.com.ar;SoporteTecnico@zeni.com.ar");

            prop2.setFrom("noreply@zeni.com.ar");

            result = exucute.send(prop2);

            if (result != 0) {
                throw new ZeniServerExeption("Ocurrio un error al enviar el mail de recuperarContraseña", FAULTCONSTANTS.ERROR_ERRONEO);
            }

        } catch (Exception ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
            ZeniContextServer.getInstance().printErrorLog(ex);
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        }
        return result;
    }

    /**
     *
     * @param wsContext
     * @param auth
     * @return
     * @throws FaultType_Exception
     */
    public static ArrayOfSectorReclamos obtenerSectorReclamos(WebServiceContext wsContext, AuthType auth)
            throws FaultType_Exception {

        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerSectorReclamos");

        final ArrayOfSectorReclamos _return = new ArrayOfSectorReclamos();

        try {

            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);

            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            final List<SectorReclamo> _registros = new ArrayList<SectorReclamo>();

            final StringBuilder querySBuilder = new StringBuilder().append("SELECT * FROM WEB_SECTOR_RECLAMOS");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {

                    SectorReclamo _reg = null;
                    while (rset.next()) {
                        try {
                            _reg = new SectorReclamo();
                            _reg.setDescripcion(rset.getString("DESCRIPCION"));
                            _reg.setIdSectorReclamo(rset.getString("ID_SECTOR_RECLAMOS"));
                            _reg.setMailTo(rset.getString("MAIL_TO"));

                            _registros.add(_reg);

                        } catch (Exception e) {
                            ZeniContextServer.getInstance().printErrorLog(e);
                            throw new SQLException(e.getMessage(), e);
                        }
                    }

                }

            };

            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getSectorReclamos().addAll(_registros);

        } catch (ZeniIPDifiereExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
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
        return _return;

    }

    /**
     *
     * @param wsContext
     * @param auth
     * @param email
     * @param asunto
     * @param descripcion
     * @param sector
     * @return
     * @throws FaultType_Exception
     */
    public static int solicitarReclamo(WebServiceContext wsContext, AuthType auth, String email, String asunto,
            String descripcion, String sector) throws FaultType_Exception {

        ZeniContextServer.getInstance().printInfoLog("Executing operation solicitarReclamo");
        ZeniContextServer.getInstance().printInfoLog(
                " email[" + email + "] asunto[" + asunto + "] descripcion[" + descripcion + "] sector[" + sector + "]");
        int result = -1;

        try {
            final UserData u = new UsuarioAuthenticationUtil()
                    .getUserData(AuthNHashUtil.getInstance().getHashNSeed(auth.getHash()).getUser());

            final String mail_to = getSectorReclamo(sector);
            String body = asunto + " - " + email + " - " + " - " + u.getUsername();

            String id = MbIdGenerator.getInstance().nextId();
            String queryInsert = new ZeniInsertBuilder().insertinsertinto(ZeniConstants.WEBINFORMACION.TABLE_NAME)
                    .fields(ZeniConstants.WEBINFORMACION.ID_MAIL_INFORMACION, ZeniConstants.WEBINFORMACION.MAIL_BODY,
                            ZeniConstants.WEBINFORMACION.MAIL_FROM, ZeniConstants.WEBINFORMACION.MAIL_TO,
                            ZeniConstants.WEBINFORMACION.STATUS, ZeniConstants.WEBINFORMACION.RETRIES,
                            ZeniConstants.WEBINFORMACION.USER_INSERT, ZeniConstants.WEBINFORMACION.INSERT_DATE)
                    .values(id, body, null, email, "P", "0", u.getUsername(),
                            ToString.yyyyMMddHHmmss(GregorianCalendar.getInstance().getTime()))
                    .getInsertQuery();

            int db = ZeniQueryExcecutor.excecuteUpdateReturn(queryInsert);

            ZeniContextServer.getInstance().printInfoLog("result Insert {" + db + "}");
            RestExecute exucute = new RestExecute("http://localhost:8080/mailSrv/send", "18000");

            MailProperties prop2 = new MailProperties();
            prop2.setIdMail(id);
            prop2.setSubject(asunto);
            prop2.setBodyTitle("Solicitud de Reclamo");
            prop2.setBodySection1("Usuario: " + u.getUsername());
            prop2.setBodySection2("Email: " + email);
            prop2.setBodySection3Title("Descripcion:");
            prop2.setBodySection3(descripcion);
//			prop2.setTo("omar.gagliano@gmail.com");
            prop2.setTo(mail_to);
            prop2.setFrom("noreply@zeni.com.ar");

            result = exucute.send(prop2);

            if (result != 0) {
                throw new ZeniServerExeption("Ocurrio un error al enviar el mail de solicitarReclamo",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

        } catch (ZeniHashInvalidExeption ex) {
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
        } catch (Exception ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
            ZeniContextServer.getInstance().printErrorLog(ex);
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        }
        return 0;
    }

    /**
     *
     * @param id
     * @return
     * @throws ZeniDBExeption
     * @throws ZeniServerExeption
     */
    private static String getSectorReclamo(String id) throws ZeniDBExeption, ZeniServerExeption {

        final StringBuilder querySBuilder = new StringBuilder()
                .append("SELECT MAIL_TO FROM WEB_SECTOR_RECLAMOS WHERE ID_SECTOR_RECLAMOS ='" + id + "'");

        final List<String> data = new ArrayList<String>();

        final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {

            @Override
            public void thisIsTheResulset(ResultSet rset) throws SQLException {

                while (rset.next()) {
                    try {
                        data.add(rset.getString("MAIL_TO"));

                    } catch (Exception e) {
                        ZeniContextServer.getInstance().printErrorLog(e);
                        throw new SQLException(e.getMessage(), e);
                    }
                }

            }

        };

        ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);

        if (data.size() > 0) {
            return data.get(0);
        } else {
            return null;
        }

    }

    /**
     *
     * @param wsContext
     * @param auth
     * @param numeroContrato
     * @return
     * @throws FaultType_Exception
     */
    public static String obtenerContratoId(WebServiceContext wsContext, AuthType auth, String numeroContrato)
            throws FaultType_Exception {

        ZeniContextServer.getInstance().printInfoLog("Executing obtenerContratoId contrato {" + numeroContrato + "} user{" + auth.getUser() + "}");

        final StringBuilder querySBuilder = new StringBuilder()
                .append(" SELECT (cuenta.id || '-' || c.id) ID ").append(" FROM CONTRATO c ")
                .append(" INNER JOIN cuenta_cliente cuenta ON ").append(" (cuenta.id = c.vendedor_id ")
                .append(" AND cuenta.fechabaja IS NULL) ").append(" WHERE 	numerocontrato = '" + numeroContrato + "' ")
                .append(" AND (c.vendedor_id IN ( ")
                .append("SELECT wuc.CUENTA_CLIENTE_ID ")
                .append("FROM WEB_USUARIO_CUENTA wuc ")
                .append("INNER JOIN WEB_USUARIOS wu ON (wuc.CLIENTE_WEB_ID = wu.CLIENTE_WEB_ID) ")
                .append("WHERE usuario='" + auth.getUser() + "')")
                .append(" OR c.comprador_id IN ( ")
                .append("SELECT wuc.CUENTA_CLIENTE_ID ")
                .append("FROM WEB_USUARIO_CUENTA wuc ")
                .append("INNER JOIN WEB_USUARIOS wu ON (wuc.CLIENTE_WEB_ID = wu.CLIENTE_WEB_ID) ")
                .append("WHERE usuario='" + auth.getUser() + "'))");

        final List<String> data = new ArrayList<String>();

        try {

            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);

            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {

                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {

                    while (rset.next()) {
                        try {
                            data.add(rset.getString("ID"));

                        } catch (Exception e) {
                            ZeniContextServer.getInstance().printErrorLog(e);
                            throw new SQLException(e.getMessage(), e);
                        }
                    }

                }

            };

            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);

            if (data.size() > 0) {
                return data.get(0);
            } else {
                return null;
            }

        } catch (ZeniHashInvalidExeption ex) {
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
        } catch (Exception ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
            ZeniContextServer.getInstance().printErrorLog(ex);
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        }

    }

    /**
     *
     * @param wsContext
     * @param auth
     * @param cartaPorte
     * @return
     */
    public static ArrayOfCartaPorteReg obtenerCartaPorteId(WebServiceContext wsContext, AuthType auth,
            String cartaPorte) throws FaultType_Exception {

        final ArrayOfCartaPorteReg _return = new ArrayOfCartaPorteReg();
        try {

            if (cartaPorte == null || cartaPorte.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("la carta de porte  no pueden ser nulo o vacia",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);

            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            final List<CartaPorteReg> _registros = new ArrayList<CartaPorteReg>();

            final StringBuilder querySBuilder = new StringBuilder().append("SELECT  ")
                    .append(" entrega.fechaDescargaMercaderia as fechaDescarga, ")
                    .append(" entrega.ctg as ctg,")
                    .append(" entrega.nroCartaDePorte as nro_Carta_Porte,")
                    .append(" contrato.numeroContrato as numeroContrato, ").append(" contrato.anioCosecha as Cosecha, ")
                    .append(" ctacte_Vend.nroCuenta as cuenta_vendedor, ")
                    .append(" cl_Vend.razonSocial as razonSocial_vendedor, ")
                    .append(" ctacte_Comp.nroCuenta as cuenta_comprador, ")
                    .append("  cl_Comp.razonSocial as razonSocial_comprador, ")
                    .append(" contrato.nroContratoComprador as contrato_comprador, ")
                    .append(" produ.nombre as producto, ").append(" entrPartida.cantidad as cantidad_a_aplicar, ")
                    .append(" unidMedida.formato as formato_unidMedida, ")
                    .append(" entrega.cantidadEntrega as cantidad, ").append(" entrega.nroRecibo as nro_Recibo, ")
                    .append(" contrato_s13_.estadoDestino as estado, ").append(" proced.nombre as procedencia, ")
                    .append("  desti.nombre as destino, ")
                    .append("  state_work3_.estadoDestino as estado_de_la_entrega, ")
                    .append(" entrPartida.fechaAlta as fecha_aplicacion, ").append(" (SELECT ").append("    CASE ")
                    .append("        WHEN EP.USERMODIF IS NOT NULL THEN EP.USERMODIF ")
                    .append("        ELSE EP.USERALTA ").append("    END ").append("    FROM ENTREGA_PARTIDA EP ")
                    .append("    WHERE EP.id = entrPartida.id) as usuario_ult_actualizacion, ")
                    .append(" entrPartida.fechaBaja as fechaBaja ").append("  from entrega_partida entrPartida ")
                    .append(" left outer join workflow_state state2_ on entrPartida.state_id = state2_.id ")
                    .append(" left outer join workflow_definition state_work3_ on state2_.workflowDefinition_id = state_work3_.id ")
                    .append(" left outer join contrato contrato on entrPartida.contrato_id = contrato.id ")
                    .append(" left outer join workflow_state contrato_s12_ on contrato.state_id = contrato_s12_.id ")
                    .append(" left outer join workflow_definition contrato_s13_ on contrato_s12_.workflowDefinition_id = contrato_s13_.id ")
                    .append(" left outer join cuenta_cliente ctacte_Comp on contrato.comprador_id = ctacte_Comp.id ")
                    .append(" left outer join cliente cl_Comp on ctacte_Comp.cliente_id = cl_Comp.id ")
                    .append(" left outer join unidad_de_medida unidMedida on contrato.unidadDeMedida_id = unidMedida.id ")
                    .append(" left outer join cuenta_cliente ctacte_Vend on contrato.vendedor_id = ctacte_Vend.id ")
                    .append(" left outer join cliente cl_Vend on ctacte_Vend.cliente_id = cl_Vend.id ")
                    .append(" left outer join entrega entrega on entrPartida.entrega_id = entrega.id ")
                    .append(" left outer join ciudad desti on entrega.destinoCiudad_id = desti.id ")
                    .append(" left outer join ciudad proced on entrega.procedencia_id = proced.id ")
                    .append("  left outer join producto produ on entrega.producto_id = produ.id ")
                    .append(" left outer join entrega_partida escubierta5_ on entrPartida.esCubiertaPor_id = escubierta5_.id ")
                    .append("  where entrega.ctg = '" + cartaPorte + "'   ")
                    .append(" order by fechaDescarga asc, ctg ASC ");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rs) throws SQLException {

                    CartaPorteReg _reg = null;
                    while (rs.next()) {
                        try {
                            _reg = new CartaPorteReg();
                            _reg.setCantidad(rs.getFloat("cantidad"));
                            _reg.setCantidadAplicar(rs.getFloat("cantidad_a_aplicar"));
                            _reg.setContratoComprador(rs.getString("contrato_comprador"));
                            _reg.setCosecha(rs.getString("Cosecha"));
                            _reg.setCtaComp(rs.getString("cuenta_vendedor"));
                            _reg.setCtaVend(rs.getString("cuenta_comprador"));
                            _reg.setDestino(rs.getString("destino"));
                            _reg.setEstado(rs.getString("estado"));
                            _reg.setEstadoEntrega(rs.getString("estado_de_la_entrega"));
                            _reg.setFechaAplicacion(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.yyyyMMddHHmmsss(rs.getString("fecha_aplicacion"))));
                            _reg.setFechaBaja(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMddHHmmsss(rs.getString("fechaBaja"))));
                            _reg.setFechaDescarga(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.yyyyMMddHHmmsss(rs.getString("fechaDescarga"))));
                            _reg.setNroCartaPorte(rs.getString("nro_Carta_Porte"));
                            _reg.setCtg(rs.getString("ctg"));
                            _reg.setNroRecibo(rs.getString("nro_Recibo"));
                            _reg.setNumeroContrato(rs.getString("numeroContrato"));
                            _reg.setProcedencia(rs.getString("procedencia"));
                            _reg.setProducto(rs.getString("producto"));
                            _reg.setRazonSocialComp(rs.getString("razonSocial_comprador"));
                            _reg.setRazonSocialVend(rs.getString("razonSocial_vendedor"));
                            _reg.setUsuarioActualizacion(rs.getString("usuario_ult_actualizacion"));
                            _registros.add(_reg);

                        } catch (Exception e) {
                            ZeniContextServer.getInstance().printErrorLog(e);
                            throw new SQLException(e.getMessage(), e);
                        }
                    }
                }
            };

            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getRegistros().addAll(_registros);

        } catch (ZeniIPDifiereExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
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
        return _return;

    }

    /**
     *
     * @param wsContext
     * @param auth
     * @param cartaPorte
     * @return
     * @throws FaultType_Exception
     */
    public static String validarCartaPorte(WebServiceContext wsContext, AuthType auth, String cartaPorte) throws FaultType_Exception {

        ZeniContextServer.getInstance().printInfoLog("Executing validarCartaPorte cartaPorte {" + cartaPorte + "} user{" + auth.getUser() + "}");

        final StringBuilder querySBuilder = new StringBuilder()
                .append(" SELECT clientecomprador_id  as id ")
                .append(" FROM ENTREGA   ")
                .append(" WHERE nroCartaDePorte='" + cartaPorte + "' ")
                .append("  AND (clientevendedor_id IN (SELECT c.id FROM CLIENTE C ")
                .append("	INNER JOIN CUENTA_CLIENTE cc ON (cc.cliente_id = c.id) ")
                .append(" WHERE cc.id IN (	")
                .append("SELECT wuc.CUENTA_CLIENTE_ID ")
                .append("FROM WEB_USUARIO_CUENTA wuc ")
                .append("INNER JOIN WEB_USUARIOS wu ON (wuc.CLIENTE_WEB_ID = wu.CLIENTE_WEB_ID) ")
                .append("WHERE usuario='" + auth.getUser() + "')) OR")
                .append(" clientecomprador_id IN (SELECT c.id FROM CLIENTE C ")
                .append(" INNER JOIN CUENTA_CLIENTE cc ON (cc.cliente_id = c.id) ")
                .append(" WHERE cc.id IN ( ")
                .append("SELECT wuc.CUENTA_CLIENTE_ID ")
                .append("FROM WEB_USUARIO_CUENTA wuc ")
                .append("INNER JOIN WEB_USUARIOS wu ON (wuc.CLIENTE_WEB_ID = wu.CLIENTE_WEB_ID) ")
                .append("WHERE usuario='" + auth.getUser() + "'))) ");

        final List<String> data = new ArrayList<String>();

        try {

            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);

            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {

                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {

                    while (rset.next()) {
                        try {
                            data.add(rset.getString("ID"));

                        } catch (Exception e) {
                            ZeniContextServer.getInstance().printErrorLog(e);
                            throw new SQLException(e.getMessage(), e);
                        }
                    }
                }
            };

            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);

            if (data.size() > 0) {
                return data.get(0);
            } else {
                return null;
            }

        } catch (ZeniHashInvalidExeption ex) {
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
        } catch (Exception ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
            ZeniContextServer.getInstance().printErrorLog(ex);
            throw new FaultType_Exception(ex.getMessage(), f, ex);
        }

    }

    /**
     *
     * @param wsContext
     * @param auth
     * @param cuentaId
     * @param nroContrato
     * @param rangoDeFechas
     * @return
     */
    public static ArrayOfBoletosCartaOferta obtenerBoletosCartaOferta(WebServiceContext wsContext, AuthType auth,
            String cuentaId, String nroContrato, RangoFechaType rangoDeFechas) throws FaultType_Exception {

        final ArrayOfBoletosCartaOferta _return = new ArrayOfBoletosCartaOferta();
        try {

            if (cuentaId == null || cuentaId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("la cuentaID no puede ser nulo o vacia",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            if (rangoDeFechas == null || rangoDeFechas.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El rango de fecha no pueden ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);

            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            final List<BoletoCartaOfertaReg> _registros = new ArrayList<BoletoCartaOfertaReg>();

            final StringBuilder querySBuilder = new StringBuilder().append(" ")
                    .append("	SELECT co.numeroContrato, to_char(co.fechaOperacion, 'dd-MM-yyyy') AS fechaOperacion, ")
                    .append("clC.razonSocial AS comprador, clV.razonSocial AS vendedor, ")
                    .append("(CASE WHEN bo.contrato_id IS NOT NULL THEN tdn.nombre  ELSE tdn1.nombre END) AS tipoDocumento, ")
                    .append("(CASE WHEN wd.estadoDestino IS NOT NULL THEN wd.estadoDestino   ELSE wd1.estadoDestino END) AS estado, ")
                    .append("(CASE WHEN bo.contrato_id IS NOT NULL THEN 'Boleto' ELSE 'CartaPorte' END) AS tipo, ")
                    .append("(CASE WHEN bo.contrato_id IS NOT NULL THEN bo.id ELSE oft.id END) AS id_descarga ")
                    .append("FROM contrato co ")
                    .append("LEFT JOIN boleto bo ON bo.contrato_id = co.id ")
                    .append("LEFT JOIN carta_oferta oft ON oft.contrato_id = co.id ")
                    .append("LEFT JOIN workflow_state ws on ws.id = bo.state_id ")
                    .append("LEFT JOIN workflow_definition wd on wd.id = ws.workflowDefinition_id ")
                    .append("LEFT JOIN workflow_state ws1 on ws1.id = oft.state_id ")
                    .append("LEFT JOIN workflow_definition wd1 on wd1.id = ws1.workflowDefinition_id ")
                    .append("LEFT JOIN tipo_documento_negocio tdn on tdn.id = bo.tipoDocumentoNegocio_id ")
                    .append("LEFT JOIN tipo_documento_negocio tdn1 on tdn1.id = oft.tipoDocumentoNegocio_id ")
                    .append("LEFT JOIN cuenta_cliente ccC ON ccC.id = co.comprador_id ")
                    .append("LEFT JOIN cliente clC ON clC.id = ccC.cliente_id ")
                    .append("LEFT JOIN cuenta_cliente ccV ON ccV.id = co.vendedor_id ")
                    .append("LEFT JOIN cliente clV ON clV.id = ccV.cliente_id ")
                    .append("WHERE co.fechaOperacion >= to_date('" + DateUtil.ToString.yyyyMMdd(rangoDeFechas.getDesde()) + "','yyyy-MM-dd')")
                    .append("AND co.fechaOperacion <= to_timestamp('" + DateUtil.ToString.yyyyMMdd(rangoDeFechas.getHasta()) + " 23:59','yyyy-MM-dd HH24:MI') ")
                    .append("AND (tdn.id IN(3956,3963,3964) OR tdn1.id IN(3956,3963,3964)) ")
                    .append("AND (co.comprador_id = " + cuentaId + " OR co.vendedor_id = " + cuentaId + ") ");

            if (nroContrato != null && !"".equals(nroContrato)) {
                querySBuilder.append("AND co.numeroContrato = '" + nroContrato + "'");
            }

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rs) throws SQLException {

                    BoletoCartaOfertaReg _reg = null;
                    while (rs.next()) {
                        try {
                            _reg = new BoletoCartaOfertaReg();
                            _reg.setComprador(rs.getString("comprador"));
                            _reg.setEstado(rs.getString("estado"));
                            _reg.setFechaOperacion(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.ddmmyyyy(rs.getString("fechaOperacion"))));
                            _reg.setNroContrato(rs.getString("numeroContrato"));
                            _reg.setTipoDocumento(rs.getString("tipoDocumento"));
                            _reg.setVendedor(rs.getString("vendedor"));
                            _reg.setTipo(rs.getString("tipo"));
                            _reg.setIdDescarga(rs.getString("id_descarga"));
                            _registros.add(_reg);

                        } catch (Exception e) {
                            ZeniContextServer.getInstance().printErrorLog(e);
                            throw new SQLException(e.getMessage(), e);
                        }
                    }
                }
            };

            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getRegistros().addAll(_registros);

        } catch (ZeniIPDifiereExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
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
        return _return;
    }

    /**
     *
     * @param wsContext
     * @param auth
     * @param comprobante
     * @param tipo
     * @return
     */
    public static FileType descargarBoletoCartaOferta(WebServiceContext wsContext, AuthType auth, String comprobante,
            EnumBoletoCartaPorte tipo) throws FaultType_Exception {

        ZeniContextServer.getInstance().printInfoLog("Executing operation descargarBoletoCartaOferta");
        ZeniContextServer.getInstance().printInfoLog("comprobante {" + comprobante + "} + tipo{" + tipo + "}");

        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            if (comprobante == null || comprobante.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El comprobante no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            final URL fileremote = new URL(
                    ZeniContextServer.getInstance().getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_URL_BOLETOCARTA)
                    + ZeniContextServer.getInstance()
                            .getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_PARAM_1_BOLETOCARTA)
                    + comprobante + ZeniContextServer.getInstance()
                            .getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_PARAM_2_BOLETOCARTA)
                    + tipo.value());

            ZeniContextServer.getInstance()
                    .printInfoLog("fileRemote: " + (fileremote != null ? fileremote.toString() : "null"));

            URLConnection connection = fileremote.openConnection();

            connection.setConnectTimeout(15 * 1000);
            String fileName = getFileName(connection.getHeaderFields());

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
                if (icon != null) {
                    icon.close();
                }
                if (bifin != null) {
                    bifin.close();
                }
                if (bais != null) {
                    bais.flush();
                }
            }

            final String extAlone = "pdf";
            _returnData = bais.toByteArray();
            bais.close();

            if (_returnData != null && _returnData.length > 0 && fileName != null) {
                final FileType _return = new FileType();
                _return.setData(_returnData);
                _return.setNombreConExtension(fileName);
                _return.setNombre(fileName.substring(0, fileName.lastIndexOf('.')));
                _return.setExtension(extAlone);
                _return.setTamanio(_returnData.length);
                return _return;
            } else {

                throw new ZeniServerExeption("No se pudo descargar el comprobante{" + comprobante + "} tipo {" + tipo + "}",
                        FAULTCONSTANTS.ERROR_ERRONEO);

            }

        } catch (ZeniIPDifiereExeption ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(ex.getCode());
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
        } catch (IOException ex) {
            FaultType f = new FaultType();
            f.setMensaje(ex.getMessage());
            f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
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

    /**
     *
     * @param map
     * @return
     */
    public static String getFileName(Map<String, List<String>> map) {
        String fileName = null;
        if (map != null) {
            String keyFinal = null;
            for (String key : map.keySet()) {
                if (key != null && key.equalsIgnoreCase("Content-disposition")) {
                    keyFinal = key;
                    break;
                }
            }

            if (keyFinal != null) {
                String raw = map.get(keyFinal).toString();
                // raw = "attachment; filename=abc.jpg"
                if (raw != null && raw.indexOf("=") != -1) {
                    fileName = raw.split("=")[1]; // getting value after '='
                    fileName = fileName.replaceAll("\"", "").replaceAll("]", "");
                }
            }
        }

        return fileName;
    }
}
