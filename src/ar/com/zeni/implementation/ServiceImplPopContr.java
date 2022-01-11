package ar.com.zeni.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;

import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.FAULTCONSTANTS;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniAuthTypeInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.common.exceptions.ZeniHashInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniIPDifiereExeption;
import ar.com.zeni.common.exceptions.ZeniServerExeption;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.security.AuthNHashUtil;
import ar.com.zeni.zeniwsdl.AjusteComentarioFijacionByContratoType;
import ar.com.zeni.zeniwsdl.AnalisisMuestraByContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfAjusteComentarioFijacionByContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfCanjeByContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfComentarioComentarioFijacionByContrato;
import ar.com.zeni.zeniwsdl.ArrayOfComentarioFijacionByContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfComentarioMesaByContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfComentariosEntContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfCupoByContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfEntregaByContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfEntregaCubiertaType;
import ar.com.zeni.zeniwsdl.ArrayOfFijacionByContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfLiquidacionByContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfMuestraAnalisisByContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfMuestraByContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfSeguimientoBoletoType;
import ar.com.zeni.zeniwsdl.AuthType;
import ar.com.zeni.zeniwsdl.CanjeByContratoType;
import ar.com.zeni.zeniwsdl.ComentarioComentarioFijacionByContratoType;
import ar.com.zeni.zeniwsdl.ComentarioEntregaByContratoType;
import ar.com.zeni.zeniwsdl.ComentarioFijacionByContratoType;
import ar.com.zeni.zeniwsdl.ComentarioMesaByContratoType;
import ar.com.zeni.zeniwsdl.ContratoType;
import ar.com.zeni.zeniwsdl.CupoByContratoType;
import ar.com.zeni.zeniwsdl.EntregaByContratoType;
import ar.com.zeni.zeniwsdl.EntregaCubiertaType;
import ar.com.zeni.zeniwsdl.FaultType;
import ar.com.zeni.zeniwsdl.FaultType_Exception;
import ar.com.zeni.zeniwsdl.FijacionByContratoType;
import ar.com.zeni.zeniwsdl.LiquidacionByContratoType;
import ar.com.zeni.zeniwsdl.MonedaType;
import ar.com.zeni.zeniwsdl.MuestraAnalisisByContratoType;
import ar.com.zeni.zeniwsdl.MuestraByContratoType;
import ar.com.zeni.zeniwsdl.SINOType;
import ar.com.zeni.zeniwsdl.SeguimientoBoletoType;
import ar.com.zeni.zeniwsdl.SeguimientoDeBoletoByContratoType;

public abstract class ServiceImplPopContr extends ServiceImplMenuMisOperaciones {

    @Override
    public ContratoType obtenerContratoByContrato(AuthType auth, String contratoId, String cuentaId)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerContratoByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(contratoId);
        ZeniContextServer.getInstance().printVerboLog(cuentaId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato id no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (cuentaId == null || cuentaId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final StringBuilder querySBuilder = new StringBuilder().append("SELECT c.id contrato_id, ")
                    .append("c.numerocontrato, ").append("c.numerocontrato nrozeni, ")
                    .append("cc.denominacioncuenta contraparte, ").append("c.nrocontratocomprador comprobante, ")
                    .append("c.cantidadpactada cantidad, ").append("c.preciomercaderia precio, ")
                    .append("c.cantidadpactada -NVL(ent.cantidadentregada,0) saldo, ")
                    .append("TO_CHAR(c.fechaentregadesde, 'yyyy-MM-dd') fechaentregadesde, ")
                    .append("TO_CHAR(c.fechaentregahasta, 'yyyy-MM-dd') fechaentregahasta, ")
                    .append("proc.nombre procedencia, ").append("dest.nombre destino, ")
                    // #1445
                    .append("c.VENCPARCDIASENTREGA VENCPARCDIASENTREGA, ")
                    .append("c.VENCPARCTIPODIAS VENCPARCTIPODIAS, ")
                    .append("NVL(cf.cantidadfacturada,0) kilosfacturados, ").append("cal.codigo catcod, ")
                    .append("cal.nombre catdesc, ").append("c.fechaoperacion fa, ")
                    .append("c.cobrodemercaderia opcod, ")
                    .append("NVL(c.monedaiva_id, NVL(c.MONEDAFACTURACIONMERCADERIA_ID, NVL(c.MONEDAPRECIOOLEICO_ID, '1717'))) moneda_id, ")
                    .append("c.cantidadpactada pac, ")
                    .append("( select de.descripcion from " + ZeniContextServer.getInstance().getDBWebOwner()
                            + "NOESTA_OPERATORIACBRMER de where de.id = c.cobrodemercaderia) opdesc, ")
                    .append("( select dd.descripcion from " + ZeniContextServer.getInstance().getDBWebOwner()
                            + "NOESTA_OPERATORIA dd where dd.id = c.operatoria) operatoria, ")
                    .append("ent.cantidadentregada entr, ").append("c.cumplido, ")
                    .append("TO_CHAR(c.fechaentregadesde, 'yyyy-MM-dd') periododesde, ")
                    .append("TO_CHAR(c.fechaentregahasta, 'yyyy-MM-dd') periodohasta, ").append("tt.nombre tte, ")
                    .append("c.cantidadpactada -NVL(ent.cantidadentregada,0) pdte, ")
                    .append("NVL(cf.cantidadfacturada,0) fact, ").append("NVL(fija.cantidad,0) fij, ")
                    .append("NVL(ent.cantidadentregada,0)-NVL(fija.cantidad,0) afijar, ")
                    .append("TO_CHAR(fija.fechadesde, 'yyyy-MM-dd') fechafijaciondesde, ")
                    .append("TO_CHAR(fija.fechahasta, 'yyyy-MM-dd') fechafijacionhasta, ").append("p.nombre producto, ")
                    .append("cc2.denominacioncuenta vendrazonsocial, ")
                    .append("cc.denominacioncuenta comprazonsocial, ")
                    .append("TO_CHAR(c.vencParcFechaCierta, 'yyyy-MM-dd') vto, ")
                    .append("c.vencParcDiasEntrega vtodias, ").append("c.cesionderecho cesionderecho, ")
                    .append("act.nombre vendactiv ").append("FROM ZENIAPP.contrato c ")
                    .append("INNER JOIN ZENIAPP.producto p ").append("ON (p.id=c.producto_id) ")
                    .append("INNER JOIN ZENIAPP.cuenta_cliente cc ").append("ON (cc.id         =c.comprador_id ")
                    .append("AND cc.fechabaja IS NULL) ").append("INNER JOIN ZENIAPP.cuenta_cliente cc2 ")
                    .append("ON (cc2.id         =c.vendedor_id ").append("AND cc2.fechabaja IS NULL) ")
                    .append("INNER JOIN ZENIAPP.ciudad PROC ").append("ON (proc.id=c.procedencia_id) ")
                    .append("INNER JOIN ZENIAPP.ciudad dest ").append("ON (dest.id=c.destino_id) ")
                    .append("LEFT OUTER JOIN ZENIAPP.calidad cal ").append("ON (cal.id=c.calidad_id) ")
                    .append("LEFT OUTER JOIN ZENIAPP.tipo_transporte tt ").append("ON (tt.id=c.tipotransporte_id) ")
                    .append("LEFT OUTER JOIN ZENIAPP.actividad act  ").append("ON (act.id=c.ACTIVIDADVENDEDOR_ID) ")
                    .append("LEFT OUTER JOIN ").append("(SELECT ep.contrato_id, ")
                    .append("SUM(ep.cantidad) cantidadentregada ").append("FROM ZENIAPP.entrega_partida ep ")
                    .append("WHERE ep.fechabaja IS NULL ").append("GROUP BY contrato_id ").append(") ent ")
                    .append("ON (ent.contrato_id=c.id) ").append("LEFT OUTER JOIN ").append("(SELECT fp.contrato_id, ")
                    .append("SUM(fp.cantidadfacturada) cantidadfacturada ").append("FROM ZENIAPP.factura_producto fp ")
                    .append("INNER JOIN ZENIAPP.tipo_comprobante tc ").append("ON (tc.id =fp.tipocomprobante_id) ")
                    .append("INNER JOIN ZENIAPP.contrato cont ").append("ON (fp.contrato_id = cont.id) ")
                    .append("WHERE upper(fp.facturador) NOT LIKE '%FINAL%' ")
                    .append("AND tc.abreviatura NOT          IN ('FA','ND','NC','LF','BF') ")
                    .append("AND (tc.abreviatura             <> 'FC' ").append("OR fp.descontarCantidadFacturada = 1) ")
                    .append("AND ((cont.operatoria           != 'M') ").append("OR ((fp.emisormat                ='V' ")
                    .append("AND cont.vendedor_id             =").append(cuentaId).append(") ")
                    .append("OR (fp.emisormat                 ='C' ").append("AND cont.comprador_id            =")
                    .append(cuentaId).append("))) ").append("AND fp.anulador_id              IS NULL ")
                    .append("AND fp.anulada_id               IS NULL ")
                    .append("AND fp.fechabaja                IS NULL ").append("GROUP BY fp.contrato_id ")
                    .append(") cf ON (cf.contrato_id=c.id) ").append("LEFT OUTER JOIN ")
                    .append("(SELECT gf.contrato_id, ").append("    SUM(f.cantidadafijar) cantidad, ")
                    .append("    MAX(gf.fechafijacionhasta) fechahasta, ")
                    .append("    MIN(gf.fechafijaciondesde) fechadesde ").append(" FROM ZENIAPP.grupo_fijaciones gf ")
                    .append(" INNER JOIN ZENIAPP.fijacion f ").append("     ON (f.grupofijaciones_id = gf.id ")
                    .append("     AND f.fechabaja         IS NULL) ")
                    .append(" INNER JOIN ZENIAPP.workflow_state ws ON (ws.id = f.state_id) ")
                    .append(" INNER JOIN ZENIAPP.workflow_definition wd ON (wd.id = ws.workflowdefinition_id) ")
                    .append("WHERE gf.fechabaja      IS NULL ").append("  AND  wd.estadoActual <> \'A\' ")
                    .append("GROUP BY gf.contrato_id ").append(") fija ON (fija.contrato_id=c.id) ")
                    .append("WHERE c.fechabaja           IS NULL ").append("AND c.id                     = ")
                    .append(contratoId);
            final ContratoType _return = new ContratoType();
            final HashMap<String, MonedaType> monedaMap = innerObtenerMoneda();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            final ar.com.zeni.zeniwsdl.RangoFechaType _returnContratosVal1EntRango = new ar.com.zeni.zeniwsdl.RangoFechaType();
                            final ar.com.zeni.zeniwsdl.RangoFechaType _returnContratosVal1Periodo = new ar.com.zeni.zeniwsdl.RangoFechaType();
                            final ar.com.zeni.zeniwsdl.RangoFechaType _returnContratosVal1FijacionRango = new ar.com.zeni.zeniwsdl.RangoFechaType();

                            _returnContratosVal1EntRango.setDesde(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.yyyyMMdd(rset.getString("fechaentregadesde"))));
                            _returnContratosVal1EntRango.setHasta(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.yyyyMMdd(rset.getString("fechaentregahasta"))));
                            _returnContratosVal1FijacionRango.setDesde(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.yyyyMMdd(rset.getString("fechafijaciondesde"))));
                            _returnContratosVal1FijacionRango.setHasta(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.yyyyMMdd(rset.getString("fechafijacionhasta"))));
                            _returnContratosVal1Periodo.setDesde(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("periododesde"))));
                            _returnContratosVal1Periodo.setHasta(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("periodohasta"))));
                            _return.setVto(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("vto"))));
                            _return.setContratId(rset.getString("contrato_id"));
                            _return.setContratoId(rset.getString("contrato_id"));
                            _return.setNumeroZeni(rset.getString("nrozeni"));
                            _return.setNumeroContrato(rset.getString("nrozeni"));
                            _return.setNumeroComprobante(rset.getString("comprobante"));
                            _return.setContraparte(rset.getString("contraparte"));
                            _return.setVendRazonSocial(rset.getString("vendrazonsocial"));
                            _return.setCompRazonSocial(rset.getString("comprazonsocial"));
//							_return.setVendCuenta(rset.getString("vendcuenta"));
                            _return.setVendActividad(rset.getString("vendactiv"));
                            _return.setCantidad(rset.getFloat("cantidad"));
                            _return.setSaldo(rset.getFloat("saldo"));
                            _return.setPrecio(rset.getFloat("precio"));
                            _return.setKgFacturado(rset.getFloat("kilosfacturados"));
                            _return.setProductoDescripcion(rset.getString("producto"));
//							_return.setProductoCodigo(rset.getString("productcod"));
//							_return.setProductoId(rset.getString("productid"));
//							_return.setCp(rset.getString("fechavalor"));
                            _return.setProcedencia(rset.getString("procedencia"));
                            _return.setDestino(rset.getString("destino"));
                            _return.setCatCod(rset.getString("catcod"));
                            _return.setCatDesc(rset.getString("catdesc"));
                            _return.setOpCod(rset.getString("opcod"));
                            _return.setOpDesc(rset.getString("opdesc"));
                            _return.setPac(rset.getFloat("pac"));
                            _return.setEntr(rset.getFloat("entr"));
                            _return.setCumplido(rset.getInt("cumplido") > 0 ? SINOType.SI : SINOType.NO);
                            _return.setTte(rset.getString("tte"));
                            _return.setPdte(rset.getFloat("pdte"));
                            _return.setMoneda(monedaMap.get(rset.getString("moneda_id")));
//   				        _return.setCpagoCod(rset.getString("fechavalor"));
//   				        _return.setCpagoDesc(rset.getString("fechavalor"));
//   				        _return.setCpagoProciento(rset.getString("fechavalor"));
                            _return.setVtoDias(rset.getInt("vtodias"));
                            _return.setFact(rset.getFloat("fact"));
                            _return.setFij(rset.getFloat("fij"));
                            _return.setAfijar(rset.getFloat("afijar"));
                            _return.setCesionderecho(rset.getString("cesionderecho"));
                            _return.setFijacionRango(_returnContratosVal1FijacionRango);
                            _return.setPeriodo(_returnContratosVal1Periodo);
                            _return.setEntRango(_returnContratosVal1EntRango);
                            // #1445
                            _return.setVencimiento(rset.getInt("VENCPARCDIASENTREGA"));
                            _return.setTipoDias(rset.getString("VENCPARCTIPODIAS"));
                            _return.setFA(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fa"))));
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            return _return;
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
    }

    // #1610
    private ar.com.zeni.zeniwsdl.ArrayOfSeguimientoBoletoType obtenerSeguimientoBoletoByContrato(AuthType auth,
            String idContrato) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerSeguimientoBoletoByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(idContrato);

        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (idContrato == null || idContrato.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id del contrato no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            final ArrayOfSeguimientoBoletoType _return = new ArrayOfSeguimientoBoletoType();
            final List<SeguimientoBoletoType> _returnSeguimientoBoleto = new ArrayList<SeguimientoBoletoType>();

            final StringBuilder querySBuilder = new StringBuilder().append(" select ws.fechaOperacion fecha, ")
                    .append(" wd.estadoDestino as Estado, ").append(" ws.userAlta as Usuario, ")
                    .append(" ws.observacion ").append(" from boleto bo ")
                    .append(" inner join contrato co on co.id = bo.contrato_id ")
                    .append(" inner join workflow_state ws on ws.ref_id = bo.id ")
                    .append(" inner join workflow_definition wd on wd.id = ws.workflowDefinition_id ")
                    .append(" where co.id =  ").append(idContrato).append(" order by ws.fechaOperacion ");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            SeguimientoBoletoType _returnSeguimientoBoletoVal1 = new SeguimientoBoletoType();
                            _returnSeguimientoBoletoVal1.setEstado(rset.getString("estado"));
                            _returnSeguimientoBoletoVal1.setUsuario(rset.getString("usuario"));
                            _returnSeguimientoBoletoVal1.setObservacion(rset.getString("observacion"));
                            _returnSeguimientoBoletoVal1.setFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecha"))));
                            _returnSeguimientoBoleto.add(_returnSeguimientoBoletoVal1);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getSeguimientoBoleto().addAll(_returnSeguimientoBoleto);
            return _return;
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
    }

    public ar.com.zeni.zeniwsdl.ArrayOfCartaDePorteType obtenerCartasDePorteByContrato(AuthType auth, String idContrato)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCartasDePorteByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(idContrato);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (idContrato == null || idContrato.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id del contrato no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            return innerCartaDePorte(null, null, null, idContrato);
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
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see ZeniWSDL#obtenerMuestrasByContrato(AuthType auth ,)CuentaType cuenta
	 * ,)RangoFechaType rangoFechasAlta ,)java.lang.String contrato
	 * ,)ArrayOfProductoType productos )*
     */
    @Override
    public ArrayOfMuestraByContratoType obtenerMuestrasByContrato(AuthType auth, String contratoId)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerMuestrasByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(contratoId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            ArrayOfMuestraByContratoType _return = new ArrayOfMuestraByContratoType();
            final List<MuestraByContratoType> _returnMuestrasByContrato = new ArrayList<MuestraByContratoType>();
            final StringBuilder querySBuilder = new StringBuilder().append(
                    "select a.id analisis_id, a.nrosolicitudanalisis nrocertif,a.nrocertificadoanalisis nro,e.nrocartadeporte cporte,e.ctg, e.fechadescargamercaderia fechaentr, ep.cantidad kilos, null fechacarta, a.nrosolicitudanalisis nrosolic, null compr, null analisis, null sinnombre, null nromuestra, null factor from ")
                    .append(owner).append("analisis_muestra a inner join ").append(owner)
                    .append("entrega e on (e.analisismuestra_id=a.id and e.fechabaja is null) inner join ")
                    .append(owner)
                    .append("entrega_partida ep on (ep.entrega_id=e.id and ep.fechabaja is null) where a.fechabaja is null and ep.contrato_id=")
                    .append(contratoId);
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            MuestraByContratoType _returnMuestrasByContratoVal1 = new MuestraByContratoType();
                            _returnMuestrasByContratoVal1.setAnalisisId(rset.getString("analisis_id"));
                            _returnMuestrasByContratoVal1.setCporte(rset.getString("cporte"));
                            _returnMuestrasByContratoVal1.setCtg(rset.getString("ctg"));
                            _returnMuestrasByContratoVal1.setFactor(rset.getString("factor"));
                            _returnMuestrasByContratoVal1.setFeccarta(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechacarta"))));
                            _returnMuestrasByContratoVal1.setFecentr(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechaentr"))));
                            _returnMuestrasByContratoVal1.setKilos(rset.getFloat("kilos"));
                            _returnMuestrasByContratoVal1.setNrocertif(rset.getString("nrocertif"));
                            _returnMuestrasByContratoVal1.setNroMuestra("nromuestra");
                            _returnMuestrasByContratoVal1.setNrosolic("nrosolic");
                            _returnMuestrasByContratoVal1.setSinnombre("sinnombre");
                            _returnMuestrasByContrato.add(_returnMuestrasByContratoVal1);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getMuestrasByContrato().addAll(_returnMuestrasByContrato);
            return _return;
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
    }

    @Override
    public AnalisisMuestraByContratoType obtenerAnalisisMuestraByContrato(AuthType auth, String idContrato,
            String idMuestra) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerAnalisisMuestraByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(idContrato);
        ZeniContextServer.getInstance().printVerboLog(idMuestra);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (idContrato == null || idContrato.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (idMuestra == null || idMuestra.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("La muestra no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final StringBuilder querySBuilder = new StringBuilder().append(
                    "select NULL Sinnombre, null nroanalisis, crc.nombre, dam.resultado,dam.porcentajebonificacionrebaja bonreb from ")
                    .append(owner).append("detalle_analisis_muestra dam inner join ").append(owner)
                    .append("concepto_rubro_calidad crc on (crc.id=dam.conceptorubrocalidad_id) inner join ")
                    .append(owner).append("analisis_muestra am on (am.id = dam.analisismuestra_id) inner join ")
                    .append(owner).append("entrega e on (e.analisismuestra_id=am.id) inner join ").append(owner)
                    .append("entrega_partida ep on (ep.entrega_id=e.id and ep.fechabaja is null) where dam.historico=0 and am.id = ")
                    .append(idMuestra).append(" and ep.contrato_id = ").append(idContrato);
            final ArrayOfMuestraAnalisisByContratoType _returnAnalisis = new ArrayOfMuestraAnalisisByContratoType();
            final List<MuestraAnalisisByContratoType> _returnAnalisisAnalisis = new java.util.ArrayList<MuestraAnalisisByContratoType>();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        MuestraAnalisisByContratoType _returnAnalisisAnalisisVal1 = new MuestraAnalisisByContratoType();
                        _returnAnalisisAnalisisVal1.setDescripcion(rset.getString("nombre"));
                        _returnAnalisisAnalisisVal1.setCantidad(rset.getFloat("resultado"));
                        _returnAnalisisAnalisisVal1.setBonreb(rset.getString("bonreb"));
                        _returnAnalisisAnalisisVal1.setSinnombre(rset.getString("Sinnombre"));
                        _returnAnalisisAnalisis.add(_returnAnalisisAnalisisVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _returnAnalisis.getAnalisis().addAll(_returnAnalisisAnalisis);
            final AnalisisMuestraByContratoType _return = new AnalisisMuestraByContratoType();
            _return.setAnalisis(_returnAnalisis);
            final StringBuilder querySMainBuilder = new StringBuilder().append(
                    "select a.nrocertificadoanalisis nrocertificado, a.fechaanalisis, c.nombre camara,a.honorarios, a.observaciones, ep.cantidad kilos from ")
                    .append(owner).append("analisis_muestra a inner join ").append(owner)
                    .append("entrega e on (e.analisismuestra_id=a.id) inner join ").append(owner)
                    .append("entrega_partida ep on (ep.entrega_id=e.id and ep.fechabaja is null) inner join ")
                    .append(owner).append("camara c on (c.id=a.camara_id) where a.id=").append(idMuestra)
                    .append(" and EP.contrato_id=").append(idContrato);
            final ResulsetObjectBuilder mainResb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            _return.setNrocertificado(rset.getString("nrocertificado"));
                            _return.setFechaemision(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechaanalisis"))));
                            _return.setCamara(rset.getString("camara"));
                            _return.setHonorarios(rset.getFloat("honorarios"));
                            _return.setSobrekilos(rset.getString("kilos"));
                            _return.setObservaciones(rset.getString("observaciones"));
                            _return.setFactor("factor");
                            _return.setSinnombre(rset.getString("nrocertificado"));
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySMainBuilder.toString(), mainResb);
            return _return;
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
    }

    @Override
    public ArrayOfLiquidacionByContratoType obtenerLiquidacionesByContrato(AuthType auth, String idCuenta,
            String contratoId) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerLiquidacionesByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(idCuenta);
        ZeniContextServer.getInstance().printVerboLog(contratoId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (idCuenta == null || idCuenta.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final ArrayOfLiquidacionByContratoType _return = new ArrayOfLiquidacionByContratoType();
            final List<LiquidacionByContratoType> _retLiqui = new ArrayList<LiquidacionByContratoType>();
            final HashMap<String, MonedaType> monedaMap = innerObtenerMoneda();
            final StringBuilder querySBuilder = new StringBuilder().append("SELECT fp.id comprobante_id, ")
                    .append("tc.abreviatura tipocomprobante, ").append("tc.abreviatura ").append("||' ' ")
                    .append("||fp.letra ").append("||' ' ").append("||fp.nrofacturasucursal ").append("||' ' ")
                    .append("||fp.nrofacturanumero comprobante, ")
                    .append("to_char(fp.fechafactura, 'yyyy-MM-dd') fecha,").append("fp.monedafactura_id, ")
                    .append("fp.cantidadfacturada kilos, ").append("fp.totalhaber importevendedor, ")
                    .append("fp.importeacuenta anticipo, ").append("m.simbolo moneda, ")
                    .append("NVL(fp.preciooperacion,0)*NVL(fp.cantidadfacturada,0) compfinal, ")
                    .append("fp.useralta usu , ")
                    // #1634
                    .append("decode(fp.estadocobranza,'A', ").append(" 'ANULADA', ").append("tca.abreviatura ")
                    .append("||' ' ").append("||fpa.letra ").append("||' ' ").append("||fpa.nrofacturasucursal ")
                    .append("||' ' ").append("||fpa.nrofacturanumero) anuladopor ")
                    .append("FROM factura_producto fp ").append("INNER JOIN tipo_comprobante tc ")
                    .append("ON (tc.id               =fp.tipocomprobante_id ")
                    .append("AND tc.abreviatura NOT IN ('FA', 'ND', 'NC')) ").append("INNER JOIN moneda m ")
                    .append("ON (m.id=fp.monedafactura_id) ").append("LEFT OUTER JOIN factura_producto fpa ")
                    .append("ON (fpa.id=fp.anulador_id) ").append("LEFT OUTER JOIN tipo_comprobante tca ")
                    .append("ON (tca.id          =fpa.tipocomprobante_id) ")
                    // #1505
                    // #1634
                    .append("WHERE ( fp.fechabaja IS NULL OR fp.estadocobranza = 'A' ) ")
                    // .append(" AND fp.estadocobranza <> 'A' ")
                    .append(" AND fp.contrato_id  = ").append(contratoId).append("  ").append("AND (fp.comprador_id=")
                    .append(idCuenta).append("  or fp.vendedor_id=").append(idCuenta).append(" )  ")
                    .append("ORDER BY fp.fechafactura ");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            LiquidacionByContratoType obj = new LiquidacionByContratoType();
                            obj.setAnticip(rset.getFloat("anticipo"));
                            obj.setAnuladopor(rset.getString("anuladopor"));
                            obj.setCompfinal(rset.getFloat("compfinal"));
                            obj.setComprobanteId(rset.getString("comprobante_id"));
                            obj.setCpte(rset.getString("comprobante"));
                            obj.setFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecha"))));
                            obj.setImpvend(rset.getFloat("importevendedor"));
                            obj.setKgsfact(rset.getFloat("kilos"));
                            obj.setMon(monedaMap.get(rset.getString("monedafactura_id")));
                            obj.setTipo(rset.getString("tipocomprobante"));
                            obj.setUsu(rset.getString("usu"));
                            _retLiqui.add(obj);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getLiqui().addAll(_retLiqui);
            return _return;
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
    }

    @Override
    public ArrayOfFijacionByContratoType obtenerFijacionesByContrato(AuthType auth, String contratoId)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerFijacionesByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(contratoId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }

            final ArrayOfFijacionByContratoType _return = new ArrayOfFijacionByContratoType();
            final List<FijacionByContratoType> _retFija = new ArrayList<FijacionByContratoType>();
            final StringBuilder querySBuilder = new StringBuilder().append("select ").append("francisco.tipo,")
                    .append("francisco.nro,").append("to_char(francisco.fecpizarra, 'yyyy-MM-dd') fecpizarra,")
                    .append("francisco.kgfijados,").append("francisco.pizarra,").append("francisco.precio,")
                    .append("to_char(francisco.fechavencimiento, 'yyyy-MM-dd') fechavencimiento,")
                    .append("francisco.sit,").append("francisco.tipocomp,").append("francisco.letra,")
                    .append("francisco.nrofacturasucursal,").append("francisco.nrofacturanumero, ")
                    .append("francisco.cantidadafijar, ").append("francisco.simbolo ").append(" from ( ")
                    .append("select 'F' tipo,f.nrodeorden nro, fechafijacion fecpizarra, ff.cantidad kgfijados,pizarra.pizarra, ")
                    .append("preciofijacion precio,f.fechavencimiento fechavencimiento, 'F' sit, tc.abreviatura tipocomp, fp.letra, ")
                    .append("fp.nrofacturasucursal, fp.nrofacturanumero, f.cantidadafijar,  mon.simbolo  from ")
                    .append(owner).append("fijacion f inner join ").append(owner)
                    .append("grupo_fijaciones gf on (f.grupofijaciones_id=gf.id and gf.fechabaja is null) inner join ")
                    .append(owner).append("contrato c on (c.id = gf.contrato_id and c.fechabaja is null) inner join ")
                    .append(owner)
                    .append("moneda mon ON (mon.id = f.moneda_id AND mon.fechaBaja IS NULL) left outer join ")
                    .append(owner)
                    .append("fijacion_factura ff on (ff.fijacion_id=f.id and ff.fechabaja is null) left outer join ")
                    .append(owner)
                    .append("factura_producto fp on (fp.id=ff.facturaproducto_id and fp.fechabaja is null) left outer join ")
                    .append(owner)
                    .append("tipo_comprobante tc on (tc.id=fp.tipocomprobante_id) left outer join (select mm.grupofijaciones_id,trim( rowconcat( 'select m.nombre ||'' ''|| ppm.porcentaje || ''% '' from ")
                    .append(owner)
                    .append("ponderado_producto_mercado ppm inner join mercado m on (m.id=ppm.mercado_id) where grupofijaciones_id='||mm.grupofijaciones_id,' '))  pizarra from ")
                    .append(owner)
                    .append("ponderado_producto_mercado mm where mm.fechabaja is null) pizarra on (pizarra.grupofijaciones_id=gf.id) where f.fechabaja is null and c.id=")
                    .append(contratoId).append(" ) francisco order by francisco.fechavencimiento desc ");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            FijacionByContratoType obj = new FijacionByContratoType();
                            obj.setNro(rset.getString("nro"));
                            obj.setFecpizarra(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecpizarra"))));
                            obj.setKgfijados(rset.getFloat("kgfijados"));
                            obj.setPizarra(rset.getString("pizarra"));
                            obj.setPreciofij(rset.getFloat("precio"));
                            obj.setVtofijaciones(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.yyyyMMdd(rset.getString("fechavencimiento"))));
                            obj.setSit(rset.getString("sit"));
                            obj.setTipoComp(rset.getString("tipocomp"));
                            obj.setLetra(rset.getString("letra"));
                            obj.setSuc(rset.getString("nrofacturasucursal"));
                            obj.setNumero(rset.getString("nrofacturanumero"));
                            obj.setCantidadAFijar(rset.getFloat("cantidadafijar"));
                            obj.setSimbolo(rset.getString("simbolo"));
                            _retFija.add(obj);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getFija().addAll(_retFija);
            return _return;
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
    }

    @Override
    public ArrayOfComentarioMesaByContratoType obtenerComentariosMesaByContrato(AuthType auth, String contratoId)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerComentariosMesaByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(contratoId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            ArrayOfComentarioMesaByContratoType _return = new ArrayOfComentarioMesaByContratoType();
            final List<ComentarioMesaByContratoType> _returnComentsByContrato = new ArrayList<ComentarioMesaByContratoType>();
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select cc.fechaalta fecha,cc.comentario, cc.useralta inic from ").append(owner)
                    .append("comentario_contrato cc inner join ").append(owner)
                    .append("sub_tipo_comentario stc on (stc.id=cc.subtipocomentario_id and stc.privada=0) inner join ")
                    .append(owner)
                    .append("tipo_comentario tc on (tc.id=stc.tipocomentario_id and tc.nombre='Mesa') where cc.fechabaja is null and cc.contrato_id=")
                    .append(contratoId);
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        ComentarioMesaByContratoType _returnComentsByContratoVal1 = new ComentarioMesaByContratoType();
                        try {
                            _returnComentsByContratoVal1.setFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecha"))));
                            _returnComentsByContratoVal1.setComentario(rset.getString("comentario"));
                            _returnComentsByContratoVal1.setInic(rset.getString("inic"));
                            _returnComentsByContrato.add(_returnComentsByContratoVal1);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getComen().addAll(_returnComentsByContrato);
            return _return;
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
    }

    public ar.com.zeni.zeniwsdl.ArrayOfEntregaByContratoType obtenerEntregasByContrato(AuthType auth, String idContrato)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerEntregasByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(idContrato);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (idContrato == null || idContrato.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id del contrato no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final ArrayOfEntregaByContratoType _return = new ArrayOfEntregaByContratoType();
            final List<EntregaByContratoType> _returnEntre = new ArrayList<EntregaByContratoType>();
            final StringBuilder querySBuilder = new StringBuilder().append("")
                    .append("SELECT a.entrega_id, ").append("a.fechadescargamercaderia, ").append("a.cptenro, ")
                    .append("a.kilos, ").append("a.cport, ").append("a.producto, ").append("a.procedencia, ")
                    .append("a.comentario, ").append("a.conf, ")
                    .append("DECODE(a.columna,1,a.cantidadafijar,0) kgfijar, ")
                    .append("a.zarandeo porcentajezarandeo, ").append("a.mermaz cantmermaporzarandeo, ")
                    .append("a.mermasecado cantmermaporsecado, ").append("a.mermaotras cantotrasmermas, ")
                    .append("a.humedad porcentajehumedad, ").append("a.cantidadneta cantidadneta, ")
                    .append("a.umedida unidaddemedida, ").append("a.destino destino, ").append("a.recibo nrorecibo, ")
                    .append("a.remito nroremito, a.observaciones, a.ctg ").append("FROM ( ").append("SELECT rownum columna, ")
                    .append("e.id entrega_id, ")
                    .append("TO_CHAR(e.fechadescargamercaderia, 'yyyy-MM-dd') fechadescargamercaderia, ")
                    .append("e.nroremito cptenro, ").append("NVL(ep.cantidad,0) kilos, ").append("epf.cantidadafijar, ")
                    .append("e.nrocartadeporte cport, ").append("p.nombre producto, ")
                    .append("proc.nombre procedencia, ").append("e.observaciones comentario, ")
                    .append("e.useralta conf, ").append("e.porcentajeZarandeo zarandeo, ")
                    .append("e.cantidadMermaPorZarandeo mermaz, ").append("e.cantidadMermaPorSecado mermasecado, ")
                    .append("e.cantidadOtrasMermas mermaotras, ").append("e.porcentajeHumedad humedad, ")
                    .append("decode(nvl(ep.escubiertapor_id,0),0, ")
                    .append("e.cantidadEntrega - e.cantidadMermaPorZarandeo - e.cantidadMermaPorSecado - ")
                    .append("e.cantidadOtrasMermas ").append(", ep.cantidad) cantidadneta, ")
                    .append("udm.nombre umedida, ").append("dest.nombre destino, ").append("e.nroRecibo recibo, ")
                    .append("e.nroRemito remito, e.observaciones, e.ctg ").append("FROM entrega_partida ep ")
                    .append("INNER JOIN entrega e ").append("ON (e.id = entrega_id ")
                    .append("AND e.fechabaja IS NULL) ").append("INNER JOIN producto p ")
                    .append("ON (p.id=e.producto_id) ").append("LEFT OUTER JOIN ciudad PROC ")
                    .append("ON (proc.id=e.procedencia_id) ").append("LEFT OUTER JOIN ciudad DEST ")
                    .append("on (dest.id=e.destinociudad_id) ").append("INNER JOIN contrato c ")
                    .append("on (c.id=ep.contrato_id) ").append("INNER JOIN WORKFLOW_STATE ws ON ").append("(ws.id = ep.state_id)  ")
                    .append("INNER JOIN WORKFLOW_DEFINITION wd ON  ").append("(wd.id = ws.workflowDefinition_id) ")
                    .append("INNER JOIN WORKFLOW_DEFINITION wd ON ").append("(wd.id = ws.workflowDefinition_id) ")                   
                    .append("LEFT OUTER JOIN unidad_de_medida udm ").append("on (udm.id=c.unidaddemedida_id) ")
                    .append("LEFT OUTER JOIN ").append("(SELECT gf.contrato_id, ")
                    .append("  ROUND(COALESCE(SUM(f.cantidadAFijar),0),3) cantidadafijar ")
                    .append("FROM grupo_fijaciones gf ").append("INNER JOIN fijacion f ")
                    .append("ON (f.grupofijaciones_id=gf.id) ").append("WHERE f.fechabaja IS NULL ")
                    .append("AND gf.fechabaja IS NULL ").append("GROUP BY gf.contrato_id ")
                    .append(") epf ON (epf.contrato_id=ep.contrato_id) ").append("WHERE ep.fechabaja IS NULL ")
                    .append("AND wd.estadoActual NOT IN ('N') ").append("AND ep.contrato_id  = ").append(idContrato)
                    .append(" ) a ");
            class Colageno {

                private float k;
                private float f;

                public float getF() {
                    return f;
                }

                public void setF(float f) {
                    this.f = f;
                }

                public float getK() {
                    return k;
                }

                public void setK(float k) {
                    this.k = k;
                }
            }
            final Colageno kilos = new Colageno();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    kilos.setK(0f);
                    kilos.setF(0f);
                    while (rset.next()) {
                        try {
                            EntregaByContratoType _returnEntreVal1 = new EntregaByContratoType();
                            _returnEntreVal1.setIdEntrega(rset.getString("entrega_id"));
                            _returnEntreVal1.setFechaDescargaMercaderia(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.yyyyMMdd(rset.getString("fechadescargamercaderia"))));
                            _returnEntreVal1.setKilos(rset.getFloat("kilos"));
                            _returnEntreVal1.setCtg(rset.getString("ctg"));
                            _returnEntreVal1.setKgfijar(rset.getFloat("kgfijar"));
                            _returnEntreVal1.setCport(rset.getString("cport"));
                            _returnEntreVal1.setProducto(rset.getString("producto"));
                            _returnEntreVal1.setConf(rset.getString("conf"));
                            _returnEntreVal1.setComentarios(rset.getString("comentario"));
                            _returnEntreVal1.setProc(rset.getString("procedencia"));
                            _returnEntreVal1.setCpteNro(rset.getString("cptenro"));
                            _returnEntreVal1.setPorcentajezarandeo(rset.getFloat("porcentajezarandeo"));
                            _returnEntreVal1.setCantmermaporsecado(rset.getFloat("cantmermaporzarandeo"));
                            _returnEntreVal1.setCantmermaporsecado(rset.getFloat("cantmermaporsecado"));
                            _returnEntreVal1.setCantotrasmermas(rset.getFloat("cantotrasmermas"));
                            _returnEntreVal1.setPorcentajehumedad(rset.getFloat("porcentajehumedad"));
                            _returnEntreVal1.setCantidadneta(rset.getFloat("cantidadneta"));
                            _returnEntreVal1.setUnidaddemedida(rset.getString("unidaddemedida"));
                            _returnEntreVal1.setDestino(rset.getString("destino"));
                            _returnEntreVal1.setNrorecibo(rset.getString("nrorecibo"));
                            _returnEntreVal1.setNrorecibo(rset.getString("nroremito"));

                            String tmp = rset.getString("observaciones");

                            String analisiPorContrato = getAnalisisContrato(_returnEntreVal1.getCport());

                            String contratoCartaPorte = getContratoCartPorte(_returnEntreVal1.getCport());

                            String observacion = "";

                            if (tmp != null && !"".equalsIgnoreCase(tmp.trim())) {
                                observacion += tmp + " | ";
                            }

                            if (analisiPorContrato != null && !"".equalsIgnoreCase(analisiPorContrato.trim())) {
                                observacion += analisiPorContrato + " | ";
                            }

                            if (contratoCartaPorte != null && !"".equalsIgnoreCase(contratoCartaPorte.trim())) {
                                observacion += "Cubres: " + contratoCartaPorte;
                            }

                            if (observacion.trim().endsWith("|")) {
                                _returnEntreVal1.setObservaciones(
                                        observacion.trim().substring(0, observacion.trim().length() - 1));
                            } else {
                                _returnEntreVal1.setObservaciones(observacion.trim());
                            }

                            _returnEntre.add(_returnEntreVal1);

                            kilos.setK(kilos.getK() + rset.getFloat("kilos"));
                            kilos.setF(kilos.getF() + rset.getFloat("kgfijar"));
                        } catch (Exception e) {
                            throw new SQLException("Error al procesar el SQL.", e);
                        }
                    }
                }

            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.setKgfijar(kilos.getF());
            _return.setKilos(kilos.getK());
            _return.getEntre().addAll(_returnEntre);
            return _return;
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
    }

    /**
     *
     * @param cport
     * @return
     * @throws Exception
     */
    private String getContratoCartPorte(String cport) throws Exception {

        StringBuffer querySBuilder = new StringBuffer();

        final ArrayList<String> _data = new ArrayList<String>();

        // @formatter:off
        querySBuilder.append("select (LISTAGG(co.numeroContrato, ' - ') WITHIN GROUP (ORDER BY ep.esCubiertaPor_id)) as cubres ");
        querySBuilder.append("from entrega en ");
        querySBuilder.append("left join entrega_partida ep on ep.entrega_id = en.id ");
        querySBuilder.append("left join contrato co on co.id = ep.contrato_id ");
        querySBuilder.append("where en.nroCartaDePorte = '" + cport + "' ");
        querySBuilder.append("	and en.fechaBaja is null ");
        querySBuilder.append("and ep.fechaBaja is null ");
        querySBuilder.append("and ep.esCubiertaPor_id is not NULL ");

        // @formatter:on
        final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
            @Override
            public void thisIsTheResulset(ResultSet rset) throws SQLException {

                if (rset.next()) {
                    _data.add(rset.getString("cubres"));
                }
            }
        };

        ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);

        if (_data.size() > 0) {
            return _data.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @param cport
     * @return
     * @throws Exception
     */
    private String getAnalisisContrato(String cport) throws Exception {

        StringBuffer querySBuilder = new StringBuffer();

        final ArrayList<String> _data = new ArrayList<String>();

        // @formatter:off
        querySBuilder.append(
                "select LISTAGG(crc.nombre || ' ' || dam.resultado, ' - ') WITHIN GROUP (ORDER BY crc.nombre) as resultados ");
        querySBuilder.append("from analisis_muestra am ");
        querySBuilder.append("inner join entrega en on en.analisisMuestra_id = am.id ");
        querySBuilder.append("inner join detalle_analisis_muestra dam on dam.analisisMuestra_id = am.id ");
        querySBuilder.append("inner join concepto_rubro_calidad crc on crc.id = conceptoRubroCalidad_id ");
        querySBuilder.append("where en.nroCartaDePorte = '" + cport + "' ");
        querySBuilder.append("and dam.tipo = 'D' ");
        querySBuilder.append("and en.fechaBaja is null ");
        querySBuilder.append("and am.fechaBaja is null ");
        querySBuilder.append("and dam.fechaBaja is NULL ");

        // @formatter:on
        final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
            @Override
            public void thisIsTheResulset(ResultSet rset) throws SQLException {

                if (rset.next()) {
                    _data.add(rset.getString("resultados"));
                }
            }
        };

        ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);

        if (_data.size() > 0) {
            return _data.get(0);
        } else {
            return null;
        }
    }

    ;

	@Override
    public ArrayOfCupoByContratoType obtenerCuposByContrato(AuthType auth, String contratoId)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCuposByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(contratoId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final ArrayOfCupoByContratoType _return = new ArrayOfCupoByContratoType();
            final List<CupoByContratoType> _retCupos = new ArrayList<CupoByContratoType>();
            final StringBuilder querySBuilder = new StringBuilder().append(
                    "select cupos.codcupo codigocupo, cupos.numerocontrato contrato,trim(cupos.numerocontrato)||trim(cupos.fecha)||trim(cupos.destinatario)||trim(nvl(cupos.lugar,' ')) id, ")
                    .append("cupos.fecha,cupos.destinatario,cupos.lugar,cupos.transporte,cupos.otorgado otorgado,cupos.anulado anulado ")
                    .append("from (select  c.numerocontrato,cup.fechaasignacion fecha, cup.codcupo, ")
                    .append("cc.denominacioncuenta destinatario, l.nombre lugar, tt.nombre transporte, ")
                    .append("decode(cup.estado,'O',1,0) otorgado, decode(cup.estado,'A',1,0) anulado ").append("from ")
                    .append(owner).append("cupo cup ").append("inner join ").append(owner)
                    .append("cupo_agrupador ca on (ca.id=cup.cupoagrupador_id and ca.fechabaja is null) ")
                    .append("inner join ").append(owner)
                    .append("cuenta_cliente cc on (cc.id=ca.ctaclidest_id and cc.fechabaja is null) ")
                    .append("inner join ").append(owner)
                    .append("tipo_transporte tt on (tt.id=ca.tipotransporte_id and tt.fechabaja is null) ")
                    .append("left outer join ").append(owner).append("lugar l on (l.id=ca.lugar_id) ")
                    .append("inner join ").append(owner)
                    .append("contrato c on ( ca.contrato_id = c.id and c.fechabaja is null) ")
                    .append("where cup.fechabaja is null ").append("and ca.contrato_id=").append(contratoId)
                    .append(" ) cupos ")
                    .append("order by trim(cupos.numerocontrato)||trim(cupos.fecha)||trim(cupos.destinatario)||trim(nvl(cupos.lugar,' ')) asc ");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    String key = "valoracionamiento";
                    CupoByContratoType obj = new CupoByContratoType();
                    while (rset.next()) {
                        try {
                            if (key.equals(rset.getString("id"))) {
                                obj.setCuposAnulados(obj.getCuposAnulados() + rset.getInt("anulado"));
                                obj.setCuposOtorgados(obj.getCuposOtorgados() + rset.getInt("otorgado"));
                                if (rset.getInt("otorgado") > 0) {
                                    obj.getListaCupos().add(rset.getString("codigocupo"));
                                }
                            } else {
                                key = rset.getString("id");
                                obj = new CupoByContratoType();
                                _retCupos.add(obj);
                                obj.setContrato(rset.getString("contrato"));
                                obj.setCuposAnulados(obj.getCuposAnulados() + rset.getInt("anulado"));
                                obj.setCuposOtorgados(obj.getCuposOtorgados() + rset.getInt("otorgado"));
                                obj.setDestinatario(rset.getString("destinatario"));
                                obj.setFecha(DateUtil.Converters
                                        .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecha"))));
                                obj.setLugar(rset.getString("lugar"));
                                obj.setMedioDeTransporte(rset.getString("transporte"));
                                obj.getListaCupos().add(rset.getString("codigocupo"));
                            }
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getCup().addAll(_retCupos);
            return _return;
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
    }

    public ar.com.zeni.zeniwsdl.ArrayOfEntregaCubiertaType obtenerEntregasCubiertas(ar.com.zeni.zeniwsdl.AuthType auth,
            java.lang.String idContrato, java.lang.String idEntrega) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerEntregasCubiertas");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(idContrato);
        ZeniContextServer.getInstance().printVerboLog(idEntrega);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (idContrato == null || idContrato.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (idEntrega == null || idEntrega.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("La entrega no puede ser nula o vacia", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final StringBuilder querySBuilder = new StringBuilder().append(
                    "select arbol.id, arbol.entrega_id, arbol.escubiertapor_id, arbol.contrato_id, arbol.numerocontrato, arbol.fecha from ( SELECT ep.id, ep.entrega_id, ep.escubiertapor_id, ep.contrato_id, c.numerocontrato, to_char(e.fechadescargamercaderia, 'yyyy-MM-dd') fecha FROM ")
                    .append(owner).append("entrega_partida ep inner join ").append(owner)
                    .append("entrega e on ( e.id = ep.entrega_id) inner join ").append(owner)
                    .append("contrato c on ( c.id = ep.contrato_id) START WITH ep.entrega_id = ").append(idEntrega)
                    .append(" and ep.contrato_id = ").append(idContrato)
                    .append(" CONNECT BY prior ep.id = ep.escubiertapor_id ) arbol union select arbol.id, arbol.entrega_id, arbol.escubiertapor_id, arbol.contrato_id, arbol.numerocontrato, arbol.fecha from ( SELECT ep.id, ep.entrega_id, ep.escubiertapor_id, ep.contrato_id, c.numerocontrato, to_char(e.fechadescargamercaderia, 'yyyy-MM-dd') fecha FROM ")
                    .append(owner).append("entrega_partida ep inner join ").append(owner)
                    .append("entrega e on ( e.id = ep.entrega_id) inner join ").append(owner)
                    .append("contrato c on ( c.id = ep.contrato_id) START WITH ep.entrega_id = ").append(idEntrega)
                    .append(" and ep.contrato_id = ").append(idContrato)
                    .append(" CONNECT BY prior ep.escubiertapor_id = ep.id ) arbol");
//	    	final ArrayList<Prematuro> lista = new ArrayList<Prematuro>();
            final ArrayOfEntregaCubiertaType _return = new ArrayOfEntregaCubiertaType();
            final List<EntregaCubiertaType> _returnEntrecubi = new ArrayList<EntregaCubiertaType>();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            final EntregaCubiertaType entregacion = new EntregaCubiertaType();
                            entregacion.setIdEntrega(rset.getString("entrega_id"));
                            entregacion.setEntregaPartidaId(rset.getString("id"));
                            entregacion.setContrato(rset.getString("numerocontrato"));
                            entregacion.setContratoId(rset.getString("contrato_id"));
                            entregacion.setFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecha"))));
                            if (rset.getString("escubiertapor_id") == null
                                    || rset.getString("escubiertapor_id").equals(ZeniContextServer.VACIO)) {
                                entregacion.setCubiertaPorEntregaPartidaId("-");
                            } else {
                                entregacion.setCubiertaPorEntregaPartidaId(rset.getString("escubiertapor_id"));
                            }
                            _returnEntrecubi.add(entregacion);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            if ((_returnEntrecubi.size() <= 0)
                    || (_returnEntrecubi.size() == 1 && _returnEntrecubi.get(0).getContratoId().equals(idContrato))) {
                _returnEntrecubi.clear();
                final StringBuilder querySBuilder2 = new StringBuilder().append(
                        "select ep.id, ep.entrega_id, null escubiertapor_id, ep.contrato_id, c.numerocontrato,to_char(e.fechadescargamercaderia, 'yyyy-MM-dd') fecha ")
                        .append("from entrega_partida ep ").append("inner join entrega e on (ep.entrega_id = e.id) ")
                        .append("inner join contrato c on (ep.contrato_id = c.id) ")
                        .append("where ep.fechabaja is null ").append("and e.fechabaja is null ")
                        .append("and c.fechabaja is null ").append("and ep.entrega_id = ").append(idEntrega);
                final ResulsetObjectBuilder resb2 = new ResulsetObjectBuilder() {
                    @Override
                    public void thisIsTheResulset(ResultSet rset) throws SQLException {
                        while (rset.next()) {
                            try {
                                final EntregaCubiertaType entregacion = new EntregaCubiertaType();
                                entregacion.setIdEntrega(rset.getString("entrega_id"));
                                entregacion.setEntregaPartidaId(rset.getString("id"));
                                entregacion.setContrato(rset.getString("numerocontrato"));
                                entregacion.setContratoId(rset.getString("contrato_id"));
                                entregacion.setFecha(DateUtil.Converters
                                        .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecha"))));
                                entregacion.setCubiertaPorEntregaPartidaId("-");
                                _returnEntrecubi.add(entregacion);
                            } catch (ParseException e) {
                                throw new SQLException("No se puede determinar la fecha en el query.", e);
                            }
                        }
                    }
                };
                ZeniQueryExcecutor.excecuteSelect(querySBuilder2.toString(), resb2);
            }
            _return.getEntrecubi().addAll(_returnEntrecubi);
            return _return;
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
    }

    @Override
    public SeguimientoDeBoletoByContratoType obtenerSeguimientoDeBoletoByContrato(final AuthType auth,
            final String contratoId) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerSeguimientoDeBoletoByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
//        ZeniContextServer.getInstance().printVerboLog(idCuenta);
        ZeniContextServer.getInstance().printVerboLog(contratoId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final SeguimientoDeBoletoByContratoType _return = new SeguimientoDeBoletoByContratoType();
            final StringBuilder querySBuilder = new StringBuilder().append("SELECT bole.contrato_id, ")
                    .append("bole.id, ").append("DECODE(wde.enbol,1,wst.fechaoperacion, NULL) Envioabolsa, ")
                    .append("DECODE(wde.regbol,1,wst.fechaoperacion, NULL) Regresosbolsa, ")
                    .append("DECODE(wde.enbcom,1,wst.fechaoperacion, NULL) EnvioaComprador, ")
                    .append("DECODE(wde.envsel,1,wst.fechaoperacion, NULL) EnvioaSellar, ")
                    .append("DECODE(wde.zenicia,1,wst.fechaoperacion, NULL) Zenicia, ")
                    .append("DECODE(wde.archi,1,wst.fechaoperacion, NULL) Archivado, ")
                    .append("com.comentario Comentarios ").append("FROM ").append(owner).append("boleto bole ")
                    .append("INNER JOIN ").append(owner).append("WORKFLOW_STATE wst ")
                    .append("ON ( wst.id = bole.state_id) ").append("INNER JOIN ").append("(SELECT ddddd.id idwf, ")
                    .append("1 enbol, ").append("0 regbol, ").append("0 enbcom, ").append("0 envsel, ")
                    .append("0 zenicia, ").append("0 archi ").append("FROM ").append(owner)
                    .append("WORKFLOW_DEFINITION ddddd ").append("WHERE ddddd.id IN (-615, -630) ").append("UNION ")
                    .append("SELECT ddddd.id idwf, ").append("0 enbol, ").append("1 regbol, ").append("0 enbcom, ")
                    .append("0 envsel, ").append("0 zenicia, ").append("0 archi ").append("FROM ").append(owner)
                    .append("WORKFLOW_DEFINITION ddddd ").append("WHERE ddddd.id IN (-625) ").append("UNION ")
                    .append("SELECT ddddd.id idwf, ").append("0 enbol, ").append("0 regbol, ").append("1 enbcom, ")
                    .append("0 envsel, ").append("0 zenicia, ").append("0 archi ").append("FROM ").append(owner)
                    .append("WORKFLOW_DEFINITION ddddd ").append("WHERE ddddd.id IN (-660, -637, -617, -616, -635) ")
                    .append("UNION ").append("SELECT ddddd.id idwf, ").append("0 enbol, ").append("0 regbol, ")
                    .append("0 enbcom, ").append("1 envsel, ").append("0 zenicia, ").append("0 archi ").append("FROM ")
                    .append(owner).append("WORKFLOW_DEFINITION ddddd ").append("WHERE ddddd.id IN (-665, -670) ")
                    .append("UNION ").append("SELECT ddddd.id idwf, ").append("0 enbol, ").append("0 regbol, ")
                    .append("0 enbcom, ").append("0 envsel, ").append("1 zenicia, ").append("0 archi ").append("FROM ")
                    .append(owner).append("WORKFLOW_DEFINITION ddddd ").append("WHERE ddddd.id IN (-600) ")
                    .append("UNION ").append("SELECT ddddd.id idwf, ").append("0 enbol, ").append("0 regbol, ")
                    .append("0 enbcom, ").append("0 envsel, ").append("0 zenicia, ").append("1 archi ").append("FROM ")
                    .append(owner).append("WORKFLOW_DEFINITION ddddd ")
                    .append("WHERE ddddd.id IN (-643,-675,-680,-685) ").append(") wde ")
                    .append("ON (wst.workflowdefinition_id = wde.idwf) ")
                    .append("LEFT OUTER JOIN (select cc.contrato_id,cc.comentario from ").append(owner)
                    .append("comentario_contrato cc ").append("where cc.subtipocomentario_id=1742 ")
                    .append("and cc.fechaalta = (select max(cc2.fechaalta) from ").append(owner)
                    .append("comentario_contrato cc2 ").append("where cc2.contrato_id=cc.contrato_id ")
                    .append("and cc2.fechabaja is null ").append("and cc2.subtipocomentario_id=1742) ")
                    .append("and cc.fechabaja is null) com on (com.contrato_id=bole.contrato_id) ")
                    .append("WHERE bole.contrato_id = ").append(contratoId);
            _return.setComentarios("Sin seguimiento.");
            _return.setAbcd(SINOType.NO);
            _return.setArchivado(SINOType.NO);
            _return.setEnvioABolsa(SINOType.NO);
            _return.setEnvioAComprador(SINOType.NO);
            _return.setEnvioASellar(SINOType.NO);
            _return.setEnvioVendedor(SINOType.NO);
            _return.setRegresoBolsa(SINOType.NO);
            _return.setRegresoVendedor(SINOType.NO);
            _return.setZenicia(SINOType.NO);
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            _return.setComentarios(rset.getString("Comentarios"));
                            _return.setAbcdFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("Archivado"))));
                            _return.setArchivadoFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("Archivado"))));
                            _return.setEnvioABolsaFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("Envioabolsa"))));
                            _return.setEnvioACompradorFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("EnvioaComprador"))));
                            _return.setEnvioASellarFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("EnvioaSellar"))));
                            _return.setEnvioVendedorFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("Envioabolsa"))));
                            _return.setRegresoBolsaFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("Regresosbolsa"))));
                            _return.setRegresoVendedorFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("Regresosbolsa"))));
                            _return.setZeniciaFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("Zenicia"))));
                            _return.setAbcd(rset.getString("Archivado") == null ? SINOType.NO : SINOType.SI);
                            _return.setArchivado(rset.getString("Archivado") == null ? SINOType.NO : SINOType.SI);
                            _return.setEnvioABolsa(rset.getString("Envioabolsa") == null ? SINOType.NO : SINOType.SI);
                            _return.setEnvioAComprador(
                                    rset.getString("EnvioaComprador") == null ? SINOType.NO : SINOType.SI);
                            _return.setEnvioASellar(rset.getString("EnvioaSellar") == null ? SINOType.NO : SINOType.SI);
                            _return.setEnvioVendedor(rset.getString("Envioabolsa") == null ? SINOType.NO : SINOType.SI);
                            _return.setRegresoBolsa(
                                    rset.getString("Regresosbolsa") == null ? SINOType.NO : SINOType.SI);
                            _return.setRegresoVendedor(
                                    rset.getString("Regresosbolsa") == null ? SINOType.NO : SINOType.SI);
                            _return.setZenicia(rset.getString("Zenicia") == null ? SINOType.NO : SINOType.SI);

                            // #1610
                            _return.setSeguimientoboleto(obtenerSeguimientoBoletoByContrato(auth, contratoId));

                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        } catch (Exception ex) {
                            FaultType f = new FaultType();
                            f.setMensaje(ex.getMessage());
                            f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
                            ZeniContextServer.getInstance().printErrorLog(ex);
                            throw new SQLException(ex.getMessage(), ex);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            return _return;
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
    }

    @Override
    public ArrayOfComentarioFijacionByContratoType obtenerComentariosFijacionesByContrato(final AuthType auth,
            final String contratoId) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerComentariosFijacionesByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(contratoId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final ArrayOfComentarioFijacionByContratoType _return = new ArrayOfComentarioFijacionByContratoType();
            final List<ComentarioFijacionByContratoType> _retCome = new ArrayList<ComentarioFijacionByContratoType>();
            final StringBuilder querySBuilder = new StringBuilder().append("select  ")
                    .append("gf.id GRUPOFIJACIONESID, ").append("m.nombre mercado, ").append("cp.nombre pizarra, ")
                    .append("to_char(gf.fechafijaciondesde, 'yyyy-MM-dd') fechadesde, ")
                    .append("to_char(gf.fechafijacionhasta, 'yyyy-MM-dd') fechahasta, ")
                    .append("gf.cantidadminimadiaria cantmindia, ").append("gf.cantidadmaximadiaria cantmaxdia, ")
                    .append("gf.cantidadminimasemanal cantminsem, ").append("gf.cantidadmaximasemanal cantmaxsem, ")
                    .append("gf.cantidadminimamensual cantminmes, ").append("gf.cantidadmaximamensual cantmaxmes, ")
                    .append("um.abreviatura unidad ").append("from grupo_fijaciones gf ")
                    .append("left outer join Condicion_pizarra cp on (cp.id=gf.condicionpizarra_id) ")
                    .append("inner join ponderado_producto_mercado ppm on (ppm.grupofijaciones_id=gf.id) ")
                    .append("inner join mercado m on (m.id=ppm.mercado_id) ")
                    .append("inner join unidad_de_medida um on (um.id=gf.unidaddemedida_id) ")
                    .append("where contrato_id=").append(contratoId).append(" ").append("and gf.fechabaja is null ")
                    .append("order by gf.fechafijaciondesde");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            ComentarioFijacionByContratoType obj = new ComentarioFijacionByContratoType();
                            obj.setCantMaxDia(rset.getFloat("cantmaxdia"));
                            obj.setCantMaxMes(rset.getFloat("cantmaxmes"));
                            obj.setCantMaxSem(rset.getFloat("cantmaxsem"));
                            obj.setCantMinDia(rset.getFloat("cantmindia"));
                            obj.setCantMinSem(rset.getFloat("cantminsem"));
                            obj.setCantMinMes(rset.getFloat("cantminmes"));
                            obj.setFechaDesde(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechadesde"))));
                            obj.setFechaHasta(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechahasta"))));
                            obj.setMercado(rset.getString("mercado"));
                            obj.setPizarra(rset.getString("pizarra"));
                            obj.setUnidad(rset.getString("unidad"));
                            obj.setGrupoFijacionesId(rset.getString("GRUPOFIJACIONESID"));
                            _retCome.add(obj);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getComentarios().addAll(_retCome);
            return _return;
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
    }

    public ArrayOfAjusteComentarioFijacionByContratoType obtenerAjustesComentarioFijacionByContrato(AuthType auth,
            String contratoId) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerAjustesComentarioFijacionByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(contratoId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final ArrayOfAjusteComentarioFijacionByContratoType _return = new ArrayOfAjusteComentarioFijacionByContratoType();
            final List<AjusteComentarioFijacionByContratoType> _retAju = new ArrayList<AjusteComentarioFijacionByContratoType>();
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("   select vaf.grupofijaciones_id GrupoFijacionesId,  ").append("taf.nombre tipoajuste, ")
                    .append("m.nombre moneda,m.simbolo monedasimbolo, ")
                    .append("vaf.importeajustefijacion importeajuste,  ")
                    .append("vaf.porcentajeajustefijacion porcentajeajuste ").append("from Valor_Ajuste_fijacion vaf ")
                    .append("inner join tipo_ajuste_fijacion taf on (taf.id=vaf.tipoajustefijacion_id) ")
                    .append("left outer join moneda m on(m.id=vaf.monedaajuste_id) ")
                    .append("where  vaf.fechabaja is null ").append("and vaf.grupofijaciones_id in (select gf.id  ")
                    .append("from grupo_fijaciones gf ").append("where gf.fechabaja is null ")
                    .append("and gf.contrato_id = ").append(contratoId).append(") ")
                    .append("order by vaf.grupofijaciones_id ");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        AjusteComentarioFijacionByContratoType _returnAjustesVal1 = new AjusteComentarioFijacionByContratoType();
                        _returnAjustesVal1.setGrupoFijacionesId(rset.getString("GrupoFijacionesId"));
                        _returnAjustesVal1.setTipoAjuste(rset.getString("tipoajuste"));
                        _returnAjustesVal1.setMoneda(rset.getString("moneda"));
                        _returnAjustesVal1.setMonedaSimbolo(rset.getString("monedasimbolo"));
                        _returnAjustesVal1.setImporteAjuste(rset.getFloat("importeajuste"));
                        _returnAjustesVal1.setPorcentajeAjuste(rset.getFloat("porcentajeajuste"));
                        _retAju.add(_returnAjustesVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getAjustes().addAll(_retAju);
            return _return;
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
    }

    public ArrayOfComentarioComentarioFijacionByContrato obtenerComentariosComentarioFijacionByContrato(AuthType auth,
            String contratoId) throws FaultType_Exception {
        ZeniContextServer.getInstance()
                .printInfoLog("Executing operation obtenerComentariosComentarioFijacionByContrato");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(contratoId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final ArrayOfComentarioComentarioFijacionByContrato _return = new ArrayOfComentarioComentarioFijacionByContrato();
            final List<ComentarioComentarioFijacionByContratoType> _returnComentarios = new ArrayList<ComentarioComentarioFijacionByContratoType>();
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select grupofijaciones_id GrupoFijacionesId, comentario ")
                    .append("from Comentario_Grupo_Fijaciones ").append("where fechabaja is null ")
                    .append("and grupofijaciones_id in (select gf.id ").append("from grupo_fijaciones gf ")
                    .append("where gf.fechabaja is null ").append("and gf.contrato_id = ").append(contratoId)
                    .append(") ").append("order by grupofijaciones_id");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        ComentarioComentarioFijacionByContratoType _returnComentariosVal1 = new ComentarioComentarioFijacionByContratoType();
                        _returnComentariosVal1.setGrupoFijacionesId(rset.getString("GrupoFijacionesId"));
                        _returnComentariosVal1.setComentario(rset.getString("comentario"));
                        _returnComentarios.add(_returnComentariosVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getComentarios().addAll(_returnComentarios);
            return _return;
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
    }

    @Override
    public ArrayOfCanjeByContratoType obtenerCanjeByContrato(AuthType auth, String contratoId)
            throws FaultType_Exception {

        ZeniContextServer.getInstance()
                .printInfoLog("Executing operation obtenerCanjeByContrato contratoId{" + contratoId + "}");
        final ArrayOfCanjeByContratoType _data = new ArrayOfCanjeByContratoType();

        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }

            final StringBuilder querySBuilder = new StringBuilder()
                    .append("	select ccVdr1.nroCuenta, clVdr1.razonSocial VendedorCompra, ")
                    .append(" (CASE WHEN coCpr.numeroContrato = co.numeroContrato THEN coVdr.numeroContrato ")
                    .append(" ELSE coCpr.numeroContrato END) as Contrapartida ").append(" from contrato co ")
                    .append(" left join canje cje on cje.id = co.canje_id ")
                    .append(" left join canje_contrato_comp_cereal cjeCpr on cjeCpr.canje_id = co.canje_id ")
                    .append(" left join contrato coCpr on coCpr.id = cjeCpr.contrato_id ")
                    .append(" left join cuenta_cliente ccCpr1 on ccCpr1.id = coCpr.comprador_id ")
                    .append(" left join cliente clCpr1 on clCpr1.id = ccCpr1.cliente_id ")
                    .append(" left join cuenta_cliente ccVdr1 on ccVdr1.id = coCpr.vendedor_id ")
                    .append(" left join cliente clVdr1 on clVdr1.id = ccVdr1.cliente_id ")
                    .append(" left join canje_contrato_vta_cereal cjeVdr on cjeVdr.canje_id = co.canje_id ")
                    .append(" left join contrato coVdr on coVdr.id = cjeVdr.contrato_id ")
                    .append(" where co.id = " + contratoId);

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        CanjeByContratoType _type = new CanjeByContratoType();

                        _type.setContrapartida(rset.getString("Contrapartida"));
                        _type.setNroCuenta(rset.getString("nroCuenta"));
                        _type.setVendedorCompra(rset.getString("VendedorCompra"));

                        _data.getCanje().add(_type);
                    }
                }
            };

            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);

            return _data;
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

    }

    @Override
    public ArrayOfComentariosEntContratoType obtenerComentariosEntregaByContrato(AuthType auth, String contratoId)
            throws FaultType_Exception {

        ZeniContextServer.getInstance()
                .printInfoLog("Executing operation obtenerComentariosEntregaByContrato contratoId{" + contratoId + "}");
        final ArrayOfComentariosEntContratoType _data = new ArrayOfComentariosEntContratoType();

        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            if (contratoId == null || contratoId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El contrato no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }

            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select tc.nombre as TipoComentario, stc.nombre as SubTipoComentario, ")
                    .append("cc.comentario, cc.userAlta as Usuario, to_char(cc.fechaAlta,'dd/MM/yyyy') as Fecha ")
                    .append("from comentario_contrato cc ")
                    .append("left join sub_tipo_comentario stc on stc.id = cc.subTipoComentario_id ")
                    .append("left join tipo_comentario tc on tc.id = stc.tipoComentario_id ")
                    .append("where cc.contrato_id = " + contratoId);

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        ComentarioEntregaByContratoType _type = new ComentarioEntregaByContratoType();
                        _type.setComentario(rset.getString("comentario"));
                        _type.setFecha(rset.getString("Fecha"));
                        _type.setSubTipCcomentario(rset.getString("TipoComentario"));
                        _type.setTipoComentario(rset.getString("TipoComentario"));
                        _type.setUsuario(rset.getString("Usuario"));

                        _data.getCanje().add(_type);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);

            return _data;

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

    }

}
