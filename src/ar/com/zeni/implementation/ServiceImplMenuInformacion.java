package ar.com.zeni.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;

import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.DateUtil.Formats;
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
import ar.com.zeni.zeniwsdl.ArrayOfCartaDePorteType;
import ar.com.zeni.zeniwsdl.ArrayOfComprobanteType;
import ar.com.zeni.zeniwsdl.ArrayOfCuentaCorrienteContableType;
import ar.com.zeni.zeniwsdl.ArrayOfCupoType;
import ar.com.zeni.zeniwsdl.ArrayOfEntregaType;
import ar.com.zeni.zeniwsdl.ArrayOfEstadosDeContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfIdCuenta;
import ar.com.zeni.zeniwsdl.ArrayOfMuestraType;
import ar.com.zeni.zeniwsdl.ArrayOfOperatoriaDListadoType;
import ar.com.zeni.zeniwsdl.ArrayOfProductoType;
import ar.com.zeni.zeniwsdl.AuthType;
import ar.com.zeni.zeniwsdl.CartaDePorteType;
import ar.com.zeni.zeniwsdl.ComprobanteType;
import ar.com.zeni.zeniwsdl.CuentaCorrienteContableType;
import ar.com.zeni.zeniwsdl.CuentaType;
import ar.com.zeni.zeniwsdl.CupoType;
import ar.com.zeni.zeniwsdl.CuposResponseType;
import ar.com.zeni.zeniwsdl.EntregaType;
import ar.com.zeni.zeniwsdl.EnumCompradorVendedorType;
import ar.com.zeni.zeniwsdl.EnumOpcionesListadoEntrega;
import ar.com.zeni.zeniwsdl.EnumTipoComprobanteType;
import ar.com.zeni.zeniwsdl.EnumTipoConsultaComprobanteType;
import ar.com.zeni.zeniwsdl.EstadoDeContratoType;
import ar.com.zeni.zeniwsdl.FaultType;
import ar.com.zeni.zeniwsdl.FaultType_Exception;
import ar.com.zeni.zeniwsdl.FechaTimeType;
import ar.com.zeni.zeniwsdl.MonedaType;
import ar.com.zeni.zeniwsdl.MuestraType;
import ar.com.zeni.zeniwsdl.ObtenerCuentaCorrienteContableType;
import ar.com.zeni.zeniwsdl.OperatoriaDListadoType;
import ar.com.zeni.zeniwsdl.RangoFechaType;
import ar.com.zeni.zeniwsdl.SINOType;
import ar.com.zeni.zeniwsdl.SaldoType;

public abstract class ServiceImplMenuInformacion extends
        ServiceImplementationTODO {

    protected final static HashMap<String, MonedaType> innerObtenerMoneda()
            throws ZeniDBExeption {
        final HashMap<String, MonedaType> monedaMap = new HashMap<String, MonedaType>();
        final StringBuilder querySBuilder = new StringBuilder()
                .append("select m.id,m.nombre,m.simbolo,m.codigooncca from ")
                .append(owner)
                .append("moneda m where fechabaja is null order by orden");
        final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
            @Override
            public void thisIsTheResulset(ResultSet rset) throws SQLException {
                while (rset.next()) {
                    MonedaType _returnMonedasVal1 = new MonedaType();
                    _returnMonedasVal1.setId(rset.getString("id"));
                    _returnMonedasVal1.setDescripcion(rset.getString("nombre"));
                    _returnMonedasVal1.setSimbolo(rset.getString("simbolo"));
                    _returnMonedasVal1.setCodigoOnca(rset
                            .getString("codigooncca"));
                    monedaMap.put(_returnMonedasVal1.getId(),
                            _returnMonedasVal1);
                }
            }
        };
        ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
        return monedaMap;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see ZeniWSDL#obtenerComprobantes(ar.com.zeni.zeniwsdl .AuthType auth
	 * ,)CuentaType cuenta ,)RangoFechaType rangoFechas )*
     */
    @Override
    public ArrayOfComprobanteType obtenerComprobantes(AuthType auth,
            CuentaType cuenta,
            EnumTipoConsultaComprobanteType tipoConsultaComprobante,
            RangoFechaType rangoFechas,
            EnumCompradorVendedorType tipoComprobante, String contraparteId,
            EnumTipoComprobanteType tipoDeComprobante, SINOType descargado)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog(
                "Executing operation obtenerComprobantes");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuenta);
        ZeniContextServer.getInstance().printVerboLog(tipoConsultaComprobante);
        ZeniContextServer.getInstance().printVerboLog(rangoFechas);
        ZeniContextServer.getInstance().printVerboLog(tipoComprobante);
        ZeniContextServer.getInstance().printVerboLog(contraparteId);
        ZeniContextServer.getInstance().printVerboLog(tipoDeComprobante);
        ZeniContextServer.getInstance().printVerboLog(descargado);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc
                    .get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (cuenta == null || cuenta.getId() == null
                    || cuenta.getId().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption(
                        "El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            String filtroCuentaComprador = " AND ((c.operatoria = 'M' and f.comprador_id = "
                    + cuenta.getId()
                    + " ) OR (c.operatoria <> 'M' AND c.comprador_id = "
                    + cuenta.getId() + " )) ";
            String filtroCuentaVendedor = " AND ((c.operatoria = 'M' and f.vendedor_id = "
                    + cuenta.getId()
                    + " ) OR (c.operatoria <> 'M' AND c.vendedor_id = "
                    + cuenta.getId() + " )) ";
            String fechaDesde = ZeniContextServer.VACIO;
            String fechaHasta = ZeniContextServer.VACIO;
            if (tipoConsultaComprobante
                    .equals(EnumTipoConsultaComprobanteType.EMITIDO)) {
                fechaDesde = DateUtil.ToString.yyyyMMdd(rangoFechas.getDesde());
                fechaHasta = DateUtil.ToString.yyyyMMdd(rangoFechas.getHasta());
                if (tipoComprobante.equals(EnumCompradorVendedorType.VENDEDOR)) {
                    filtroCuentaComprador = " AND 1 = 2 ";
                } else if (tipoComprobante
                        .equals(EnumCompradorVendedorType.COMPRADOR)) {
                    filtroCuentaVendedor = " AND 1 = 2 ";
                } else {
                }
            } else {
                Calendar calendar = Calendar.getInstance();
                if (tipoConsultaComprobante
                        .equals(EnumTipoConsultaComprobanteType.MES)) {
                    GregorianCalendar gDesde = new GregorianCalendar();
                    gDesde.set(GregorianCalendar.DAY_OF_MONTH,
                            calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
                    GregorianCalendar gHasta = new GregorianCalendar();
                    gHasta.set(GregorianCalendar.DAY_OF_MONTH,
                            calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                    fechaDesde = DateUtil.ToString.yyyyMMdd(gDesde.getTime());
                    fechaHasta = DateUtil.ToString.yyyyMMdd(gHasta.getTime());
                } else {
                    GregorianCalendar gDesde = new GregorianCalendar();
                    fechaDesde = DateUtil.ToString.yyyyMMdd(gDesde.getTime());
                    fechaHasta = DateUtil.ToString.yyyyMMdd(gDesde.getTime());
                }
            }
            String filtroFechas = new StringBuilder()
                    .append("AND DECODE('")
                    .append(tipoConsultaComprobante
                            .equals(EnumTipoConsultaComprobanteType.EMITIDO) ? "EMITIDO"
                            : "DIAMES")
                    .append("', 'EMITIDO', f.fechafactura, f.fechafactura) >= to_date('")
                    .append(fechaDesde)
                    .append("','yyyy-MM-dd') ")
                    .append("AND DECODE('")
                    .append(tipoConsultaComprobante
                            .equals(EnumTipoConsultaComprobanteType.EMITIDO) ? "EMITIDO"
                            : "DIAMES")
                    .append("', 'EMITIDO', f.fechafactura, f.fechafactura) <= to_date('")
                    .append(fechaHasta).append("','yyyy-MM-dd') ").toString();
            String filtroOuter = new StringBuilder()
                    .append((tipoConsultaComprobante
                            .equals(EnumTipoConsultaComprobanteType.DIA)
                            || tipoConsultaComprobante
                                    .equals(EnumTipoConsultaComprobanteType.MES)
                            || descargado.equals(SINOType.INDISTINTO) || descargado
                            .equals(SINOType.DEFAULT)) ? "LEFT OUTER JOIN WEB_COMPROBANTE_DESC cdb ON (cdb.ID_COMPRO = TO_CHAR(f.ID))"
                            : (descargado.equals(SINOType.NO) ? "LEFT OUTER JOIN WEB_COMPROBANTE_DESC cdb ON (cdb.ID_COMPRO = TO_CHAR(f.ID) AND cdb.descargado = 'N' ) "
                            : "INNER JOIN WEB_COMPROBANTE_DESC cdb ON (cdb.ID_COMPRO = TO_CHAR(f.ID) AND cdb.descargado = 'S' ) "))
                    .toString();
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("SELECT retribucion.tipo_vendedor, ")
                    .append("retribucion.parteId, ")
                    .append("retribucion.comprobanteId, ")
                    .append("retribucion.comprobante, ")
                    .append("retribucion.contraparteId, ")
                    .append("retribucion.contraparte, ")
                    .append("retribucion.cuit, ")
                    .append("retribucion.contratid, ")
                    .append("retribucion.numero_contrato, ")
                    .append("retribucion.fechafactura, ")
                    .append("retribucion.fechavto, ")
                    .append("retribucion.abreviatura, ")
                    .append("retribucion.letra, ")
                    .append("retribucion.nrofacturasucursal, ")
                    .append("retribucion.nrofacturanumero, ")
                    .append("retribucion.nrofactura, ")
                    .append("retribucion.iva, ")
                    .append("retribucion.total, ")
                    .append("retribucion.moneda_id, ")
                    .append("retribucion.descargado ")
                    // #1633
                    .append(", retribucion.facturador ")
                    .append("FROM ")
                    .append("(SELECT ' ' tipo_vendedor, ")
                    .append("NULL parteId, ")
                    .append("NULL contraparteId, ")
                    .append("NULL contraparte, ")
                    .append("NULL contratid, ")
                    .append("f.id comprobanteId, ")
                    .append("f.fechafactura, ")
                    .append("f.fechavto, ")
                    .append("tc.abreviatura, ")
                    .append("f.letra, ")
                    .append("f.nrofacturasucursal, ")
                    .append("f.nrofacturanumero, ")
                    .append("f.nrofactura, ")
                    .append("tc.abreviatura ")
                    .append("||' ' ")
                    .append("||f.letra ")
                    .append("||' ' ")
                    .append("||f.nrofacturasucursal ")
                    .append("||' ' ")
                    .append("||f.nrofacturanumero comprobante, ")
                    .append("ABS(NVL(f.importeiva,0))*SIGN(NVL(f.totalhaber,f.totaldebe)) iva, ")
                    .append("NVL(f.totalhaber,f.totaldebe) total, ")
                    .append("f.monedafactura_id moneda_id, ")
                    .append("NULL cuit, ")
                    .append("NULL numero_contrato, ")
                    .append("NVL(cdb.DESCARGADO,'N') descargado ")
                    // #1633
                    .append(", f.facturador ")
                    .append("FROM factura_producto f ")
                    .append("INNER JOIN tipo_comprobante tc ")
                    .append("ON (tc.id = f.tipocomprobante_id) ")
                    .append(filtroOuter)
                    .append(" WHERE f.fechabaja                                                IS NULL ")
                    .append(filtroFechas)
                    .append("AND f.cuentacliente_id                                            = ")
                    .append(cuenta.getId())
                    .append(" AND (f.contrato_id IS NULL or tc.abreviatura IN ('FA','NC','ND')) ")
                    .append("UNION ")
                    .append("SELECT 'vendedor' tipo_vendedor, ")
                    .append("c.vendedor_id parteId, ")
                    .append("c.comprador_id contraparteId, ")
                    .append("cc.denominacioncuenta contraparte, ")
                    .append("c.id contratid, ")
                    .append("f.id comprobanteId, ")
                    .append("f.fechafactura, ")
                    .append("f.fechavto, ")
                    .append("tc.abreviatura, ")
                    .append("f.letra, ")
                    .append("f.nrofacturasucursal, ")
                    .append("f.nrofacturanumero, ")
                    .append("f.nrofactura, ")
                    .append("tc.abreviatura ")
                    .append("||' ' ")
                    .append("||f.letra ")
                    .append("||' ' ")
                    .append("||f.nrofacturasucursal ")
                    .append("||' ' ")
                    .append("||f.nrofacturanumero comprobante, ")
                    .append("ABS(NVL(f.importeiva,0))*SIGN(NVL(f.totalhaber,f.totaldebe)) iva, ")
                    .append("NVL(f.totalhaber,f.totaldebe) total, ")
                    .append("f.monedafactura_id moneda_id, ")
                    .append("clie.cuit cuit, ")
                    .append("c.nrocontratovendedor numero_contrato, ")
                    .append("NVL(cdb.DESCARGADO,'N') descargado ")
                    // #1633
                    .append(", f.facturador ")
                    .append("FROM factura_producto f ")
                    .append("INNER JOIN contrato c ")
                    .append("ON (c.id         = f.contrato_id ")
                    .append("AND c.fechabaja IS NULL) ")
                    .append("INNER JOIN cuenta_cliente cc ")
                    .append("ON (cc.id         = c.comprador_id ")
                    .append("AND cc.fechabaja IS NULL) ")
                    .append("INNER JOIN tipo_comprobante tc ")
                    .append("ON (tc.id=f.tipocomprobante_id) ")
                    .append("INNER JOIN cliente clie ")
                    .append("ON ( clie.id = cc.cliente_id ) ")
                    .append(filtroOuter)
                    .append(" WHERE f.fechabaja                                                IS NULL ")
                    .append(filtroFechas)
                    .append(filtroCuentaVendedor)
                    .append("AND tc.abreviatura NOT IN ('FA','NC','ND') ")
                    .append("UNION ")
                    .append("SELECT 'comprador' tipo_vendedor, ")
                    .append("c.comprador_id parteId, ")
                    .append("c.vendedor_id contraparteId, ")
                    .append("cc.denominacioncuenta contraparte, ")
                    .append("c.id contratid, ")
                    .append("f.id comprobanteId, ")
                    .append("f.fechafactura, ")
                    .append("f.fechavto, ")
                    .append("tc.abreviatura, ")
                    .append("f.letra, ")
                    .append("f.nrofacturasucursal, ")
                    .append("f.nrofacturanumero, ")
                    .append("f.nrofactura, ")
                    .append("tc.abreviatura ")
                    .append("||' ' ")
                    .append("||f.letra ")
                    .append("||' ' ")
                    .append("||f.nrofacturasucursal ")
                    .append("||' ' ")
                    .append("||f.nrofacturanumero comprobante, ")
                    .append("ABS(NVL(f.importeiva,0))*SIGN(NVL(f.totalhaber,f.totaldebe)) iva, ")
                    .append("NVL(f.totalhaber,f.totaldebe) total, ")
                    .append("f.monedafactura_id moneda_id, ")
                    .append("clie.cuit cuit, ")
                    .append("c.nrocontratocomprador numero_contrato, ")
                    .append("NVL(cdb.DESCARGADO,'N') descargado ")
                    // #1633
                    .append(", f.facturador ")
                    .append("FROM factura_producto f ")
                    .append("INNER JOIN contrato c ")
                    .append("ON (c.id         =f.contrato_id ")
                    .append("AND c.fechabaja IS NULL) ")
                    .append("INNER JOIN cuenta_cliente cc ")
                    .append("ON (cc.id         = c.vendedor_id ")
                    .append("AND cc.fechabaja IS NULL) ")
                    .append("INNER JOIN tipo_comprobante tc ")
                    .append("ON (tc.id = f.tipocomprobante_id) ")
                    .append("INNER JOIN cliente clie ")
                    .append("ON ( clie.id = cc.cliente_id ) ")
                    .append(filtroOuter)
                    .append(" WHERE f.fechabaja                                                IS NULL ")
                    .append(filtroFechas)
                    .append(filtroCuentaComprador)
                    .append("AND tc.abreviatura NOT IN ('FA','NC','ND') ")
                    .append(") retribucion ")
                    .append("ORDER BY retribucion.fechafactura, retribucion.comprobante ");
            ZeniContextServer.getInstance().printVerboLog(cuenta);

            final ArrayOfComprobanteType _return = new ArrayOfComprobanteType();
            final List<ComprobanteType> _returnProductos = new ArrayList<ComprobanteType>();
            final HashMap<String, MonedaType> monedaMap = innerObtenerMoneda();
            final String contraid = (contraparteId == null) ? ""
                    : contraparteId;
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset)
                        throws SQLException {
                    while (rset.next()) {
                        try {
                            final String contra = (rset
                                    .getString("contraparteId") == null) ? ""
                                    : rset.getString("contraparteId");
                            if ((!contraid.equals("") && contraid
                                    .equals(contra)) || contraid.equals("")) {
                                final ComprobanteType _returnProductosVal1 = new ComprobanteType();
                                _returnProductosVal1
                                        .setFecha(DateUtil.Converters
                                                .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
                                                        .getString("fechafactura"))));
                                _returnProductosVal1
                                        .setFechaVto(DateUtil.Converters
                                                .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
                                                        .getString("fechavto"))));
                                _returnProductosVal1.setComprobanteId(rset
                                        .getString("comprobanteId"));
                                _returnProductosVal1.setComprobante(rset
                                        .getString("comprobante"));
                                _returnProductosVal1.setContratoId(rset
                                        .getString("contratid"));
                                _returnProductosVal1.setTipo(rset
                                        .getString("abreviatura"));
                                _returnProductosVal1.setLetra(rset
                                        .getString("letra"));
                                _returnProductosVal1.setSuc(rset
                                        .getString("nrofacturasucursal"));
                                _returnProductosVal1.setNroComprobante(rset
                                        .getString("nrofacturanumero"));
                                _returnProductosVal1.setMoneda(monedaMap
                                        .get(rset.getString("moneda_id")));
                                _returnProductosVal1.setParteTipo(rset
                                        .getString("tipo_vendedor"));
                                _returnProductosVal1.setParteId(rset
                                        .getString("parteId"));
                                _returnProductosVal1.setContraparteId(rset
                                        .getString("contraparteId"));
                                _returnProductosVal1.setContraparte(rset
                                        .getString("contraparte"));
                                _returnProductosVal1.setCuit(rset
                                        .getString("cuit"));
                                _returnProductosVal1.setImporteIVA(rset
                                        .getDouble("iva"));
                                _returnProductosVal1.setTotal(rset
                                        .getDouble("total"));
                                _returnProductosVal1
                                        .setDescargado(rset.getString(
                                                "descargado").equals("S") ? SINOType.SI
                                                : SINOType.NO);

                                // #1633
                                _returnProductosVal1.setEsFinal(rset.getString(
                                        "facturador").contains("FINAL") ? SINOType.SI
                                        : SINOType.NO);

                                String in = rset.getString("numero_contrato");
                                StringBuffer out = new StringBuffer();
                                char current;
                                if (in == null || ("".equals(in))) {
                                    _returnProductosVal1.setContrato("-");
                                } else {
                                    for (int i = 0; i < in.length(); i++) {
                                        current = in.charAt(i);
                                        if ((current == 0x9)
                                                || (current == 0xA)
                                                || (current == 0xD)
                                                || ((current >= 0x20) && (current <= 0xD7FF))
                                                || ((current >= 0xE000) && (current <= 0xFFFD))
                                                || ((current >= 0x10000) && (current <= 0x10FFFF))) {
                                            out.append(current);
                                        } else {
                                            out.append("_");
                                        }
                                    }
                                    _returnProductosVal1.setContrato(out
                                            .toString());
                                }
                                _returnProductos.add(_returnProductosVal1);
                            }
                        } catch (ParseException e) {
                            throw new SQLException(
                                    "No se puede determinar la fecha en el query.",
                                    e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getComprobantes().addAll(_returnProductos);
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
    public CuposResponseType obtenerCupos(AuthType auth, RangoFechaType rangoFechas, String clienteId,
            String contratoNro) throws FaultType_Exception {

        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCupos");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(rangoFechas);
        ZeniContextServer.getInstance().printVerboLog(clienteId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (clienteId == null || clienteId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El cliente no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFechas == null || DateUtil.ToString.ddMMyyyy(rangoFechas.getDesde()) == null) {
                throw new ZeniServerExeption("La fecha rangoFecha desde no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFechas == null || DateUtil.ToString.ddMMyyyy(rangoFechas.getHasta()) == null) {
                throw new ZeniServerExeption("La fecha rangoFecha hasta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            String filtroContrato = "";

            if (contratoNro != null && !contratoNro.equals(ZeniContextServer.VACIO)) {
                filtroContrato = " AND c.numerocontrato ='" + contratoNro + "' ";
            }

            final CuposResponseType _return = new CuposResponseType();
            final ArrayOfCupoType _returnCupos = new ArrayOfCupoType();
            final List<CupoType> _returnCuposCupos = new ArrayList<CupoType>();

            StringBuilder querySBuilder = new StringBuilder();

            querySBuilder.append(" SELECT TO_CHAR(cupo.fecha, 'yyyy-MM-dd') fecha, ");
            querySBuilder.append("c.numerocontrato ncto, ");
            querySBuilder.append("ccomp.denominacioncuenta comprador, ");
            querySBuilder.append("cvend.denominacioncuenta vendedor, ");
            querySBuilder.append("cupo.producto, cupo.destino, cupo.lugar, ");
            querySBuilder.append("cupo.destinatario, cupo.cantidad otorgados, ");
            querySBuilder.append("cupo.CODCUPO, cupo.contrato_id,  cupo.id as cupoId ");
            querySBuilder.append("FROM " + owner + "contrato c ");
            querySBuilder.append("INNER JOIN " + owner + "cuenta_cliente ccomp ON ");
            querySBuilder.append("(ccomp.id = c.comprador_id ");
            querySBuilder.append("AND ccomp.fechabaja IS NULL) ");
            querySBuilder.append("INNER JOIN " + owner + "cuenta_cliente cvend ON ");
            querySBuilder.append("(cvend.id = c.vendedor_id ");
            querySBuilder.append("AND cvend.fechabaja IS NULL) ");
            querySBuilder.append("INNER JOIN ( ");
            querySBuilder.append("SELECT ca.contrato_id contrato_id, ");
            querySBuilder.append("TRUNC(cup.fechaasignacion) fecha, ");
            querySBuilder.append("p.nombre producto, ");
            querySBuilder.append("l.nombre destino, ");
            querySBuilder.append("l.localidad lugar, ");
            querySBuilder.append("CODCUPO, cup.id, ");
            querySBuilder.append("(CASE CTACLIDEST_ID ");
            querySBuilder.append("WHEN NULL THEN ca.DESTINATARIORAZONSOCIAL ");
            querySBuilder.append("ELSE cc.DENOMINACIONCUENTA END) destinatario, ");
            querySBuilder.append("COUNT(1) cantidad ");
            querySBuilder.append("FROM " + owner + "cupo cup ");
            querySBuilder.append("INNER JOIN " + owner + "cupo_agrupador ca ON ");
            querySBuilder.append("(ca.id = cup.cupoagrupador_id ");
            querySBuilder.append("AND ca.fechabaja IS NULL) ");
            querySBuilder.append("INNER JOIN " + owner + "producto p ON ");
            querySBuilder.append("(p.id = ca.producto_id) ");
            querySBuilder.append("LEFT OUTER JOIN " + owner + "lugar l ON ");
            querySBuilder.append("(l.id = ca.lugar_id) ");
            querySBuilder.append("LEFT OUTER JOIN " + owner + "CUENTA_CLIENTE cc ON ");
            querySBuilder.append("(cc.id = ca.CTACLIDEST_ID) ");
            querySBuilder.append("WHERE cup.fechabaja IS NULL ");
            querySBuilder.append("GROUP BY ");
            querySBuilder.append("ca.contrato_id, ");
            querySBuilder.append("TRUNC(cup.fechaasignacion), ");
            querySBuilder.append("p.nombre, l.nombre, ");
            querySBuilder.append("l.localidad, CODCUPO, cup.id, ");
            querySBuilder.append("(CASE CTACLIDEST_ID ");
            querySBuilder.append("WHEN NULL THEN ca.DESTINATARIORAZONSOCIAL ");
            querySBuilder.append("ELSE cc.DENOMINACIONCUENTA END)) cupo ON ");
            querySBuilder.append("(cupo.contrato_id = c.id) ");
            querySBuilder.append("WHERE ");
            querySBuilder.append("c.fechabaja IS NULL ");
            querySBuilder.append("AND (c.comprador_id = " + clienteId + " ");
            querySBuilder.append("OR c.vendedor_id = " + clienteId + ") ");
            querySBuilder.append(filtroContrato);
            querySBuilder.append("AND cupo.fecha >= TO_DATE('");
            querySBuilder.append(DateUtil.ToString.yyyyMMdd(rangoFechas.getDesde()) + "','yyyy-MM-dd')");
            querySBuilder.append("AND cupo.fecha <= TO_DATE('");
            querySBuilder.append(DateUtil.ToString.yyyyMMdd(rangoFechas.getHasta()) + "','yyyy-MM-dd')");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {

                        try {
                            CupoType _returnCuposCuposVal1 = new CupoType();
                            _returnCuposCuposVal1.setFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecha"))));
                            _returnCuposCuposVal1.setNumeroContrato(rset.getString("ncto"));
                            _returnCuposCuposVal1.setComprador(rset.getString("comprador"));
                            _returnCuposCuposVal1.setVendedor(rset.getString("vendedor"));
                            _returnCuposCuposVal1.setDestino(rset.getString("destino"));
                            _returnCuposCuposVal1.setLugarDescarga(rset.getString("lugar"));
                            _returnCuposCuposVal1.setOtorgados(rset.getString("otorgados"));
                            _returnCuposCuposVal1.setProducto(rset.getString("producto"));
                            _returnCuposCuposVal1.setNumeroCupo(rset.getString("CODCUPO"));
                            _returnCuposCuposVal1.setDestinatario(rset.getString("destinatario"));
                            _returnCuposCuposVal1.setCupoid(rset.getString("cupoId"));

                            try {
                                setCuits(rset.getString("contrato_id"), _returnCuposCuposVal1);
                            } catch (Exception e) {
                                ZeniContextServer.getInstance().printErrorLog(e);
                                ZeniContextServer.getInstance()
                                        .printErrorLog("Ocurrio un error al obtener los cuits de los Cupos. ");

                            }
                            _returnCuposCupos.add(_returnCuposCuposVal1);

                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }

            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _returnCupos.getCupos().addAll(_returnCuposCupos);
            _return.setCuposList(_returnCupos);
            _return.setTotalCantidad(0.12208575f);
            _return.setTotalKilosFacturados(0.7974899f);
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
     * Setea los cuits de los cupos.
     *
     * @param string
     * @param _returnCuposCuposVal1
     * @throws Ex
     */
    private void setCuits(String contrato_id, final CupoType _returnCuposCuposVal1) throws Exception {

        StringBuffer querySBuilder = new StringBuffer();

        // @formatter:off
        querySBuilder.append("SELECT  ");
        querySBuilder.append("(CASE WHEN CTACLIDEST_ID IS NOT NULL THEN (SELECT cci.DENOMINACIONCUENTA from CUENTA_CLIENTE cci where cci.id = CTACLIDEST_ID) ");
        querySBuilder.append("ELSE DESTINATARIORAZONSOCIAL END) AS DESTINATARIORAZONSOCIAL, ");
        querySBuilder.append("(CASE WHEN CTACLIDEST_ID IS NOT NULL THEN (SELECT ca.cuit from cliente ca inner join CUENTA_CLIENTE cca ");
        querySBuilder.append("on cca.CLIENTE_ID = ca.id where cca.id = CTACLIDEST_ID) ELSE ");
        querySBuilder.append("DESTINATARIOCUIT END) as DESTINATARIOCUIT, ");
        querySBuilder.append("(CASE WHEN CORREDOR_ID IS NOT NULL THEN (SELECT co.cuit from cliente co where co.id = corredor_id) ELSE ");
        querySBuilder.append("CORREDORCUIT END) as CORREDORCOMPRADORCUIT, ");
        querySBuilder.append("(CASE WHEN (CORREDOR_ID IS NOT NULL AND DIRECTO = 0) THEN (SELECT co.razonsocial from cliente co where co.id = corredor_id) ELSE ");
        querySBuilder.append("CASE WHEN (CORREDOR_ID IS NULL AND DIRECTO = 0) THEN CORREDORRAZONSOCIAL ELSE 'Directo' END END) as CORREDORCOMPRADORRAZONSOCIAL, ");
        querySBuilder.append("(CASE WHEN INTERMEDIARIO_ID IS NOT NULL THEN (SELECT ci.cuit from cliente ci inner join CUENTA_CLIENTE cci on cci.CLIENTE_ID = ci.id where cci.id = INTERMEDIARIO_ID) ");
        querySBuilder.append(" ELSE INTERMEDIARIOCUIT END) AS INTERMEDIARIOCUIT, ");
        querySBuilder.append("(CASE WHEN INTERMEDIARIO_ID IS NOT NULL THEN (SELECT cci.DENOMINACIONCUENTA from CUENTA_CLIENTE cci where cci.id = INTERMEDIARIO_ID) ");
        querySBuilder.append(" ELSE  INTERMEDIARIORAZONSOCIAL END) AS INTERMEDIARIORAZONSOCIAL, ");
        querySBuilder.append("(CASE WHEN CARGADOR_ID IS NOT NULL THEN (SELECT ca.cuit from cliente ca inner join CUENTA_CLIENTE cca on cca.CLIENTE_ID = ca.id where cca.id = CARGADOR_ID) ELSE ");
        querySBuilder.append(" CARGADORCUIT END) as CARGADORCUIT, ");
        querySBuilder.append("(CASE WHEN CARGADOR_ID IS NOT NULL THEN (SELECT cca.DENOMINACIONCUENTA from CUENTA_CLIENTE cca where cca.id = CARGADOR_ID) ELSE ");
        querySBuilder.append("CARGADORRAZONSOCIAL END) as CARGADORRAZONSOCIAL, ");
        querySBuilder.append("(CASE WHEN REMITENTECOMERCIAL_ID IS NOT NULL THEN (SELECT cr.cuit from cliente cr inner join CUENTA_CLIENTE ccr on ccr.CLIENTE_ID = cr.id where ccr.id = REMITENTECOMERCIAL_ID) ELSE ");
        querySBuilder.append("REMITENTECOMERCIALCUIT END) as REMITENTECOMERCIALCUIT, ");
        querySBuilder.append("(CASE WHEN REMITENTECOMERCIAL_ID IS NOT NULL THEN (SELECT ccr.DENOMINACIONCUENTA from CUENTA_CLIENTE ccr where ccr.id = REMITENTECOMERCIAL_ID) ELSE ");
        querySBuilder.append(" REMITENTECOMERCIALRAZONSOCIAL END) as REMITENTECOMERCIALRAZONSOCIAL, ");
        querySBuilder.append("(CASE WHEN CORREDORVEND_ID IS NOT NULL THEN (SELECT cr.cuit from cliente cr where cr.id = CORREDORVEND_ID) ELSE ");
        querySBuilder.append("CORREDORVENDCUIT END) as CORREDORVENDCUIT, ");
        querySBuilder.append("(CASE WHEN CORREDORVEND_ID IS NOT NULL THEN (SELECT cr.RAZONSOCIAL from cliente cr where cr.id = CORREDORVEND_ID) ELSE ");
        querySBuilder.append(" CORREDORVENDRAZONSOCIAL END) as CORREDORVENDRAZONSOCIAL, ");

        querySBuilder.append(" (case   when LUGAR_ID is not null then (  select lu.nombre  from LUGAR lu ");
        querySBuilder.append("   where lu.ID = LUGAR_ID) else ''  end) as DESTINO, ");
        querySBuilder.append("  (case  when lu.cuit is not null then lu.cuit  else (select cuit from cliente cte ");
        querySBuilder.append("  where lu.cliente_id = cte.id) end) as CUITDESTINO, ");
        querySBuilder.append(" (CASE WHEN LUGAR_ID IS NOT NULL THEN (SELECT lu.DIRECCION FROM LUGAR lu WHERE lu.ID = LUGAR_ID) ");
        querySBuilder.append(" ELSE '' END) AS DIRECCIONDESTINO, ");
        querySBuilder.append(" (CASE WHEN LUGAR_ID IS NOT NULL THEN (SELECT lu.LOCALIDAD FROM LUGAR lu WHERE lu.ID = LUGAR_ID) ");
        querySBuilder.append(" ELSE '' END) AS LOCALIDADDESTINO, ");
        querySBuilder.append(" (CASE WHEN LUGAR_ID IS NOT NULL THEN (SELECT prov.nombre FROM LUGAR lu ");
        querySBuilder.append(" INNER JOIN PROVINCIA prov ON lu.PROVINCIA_ID = prov.id ");
        querySBuilder.append(" WHERE lu.ID = LUGAR_ID) ELSE '' END) AS PROVINCIADESTINO ");

        // @formatter:on
        querySBuilder.append(" FROM cupo cup ");
        querySBuilder.append(" INNER JOIN cupo_agrupador ca ON ");
        querySBuilder.append(" (ca.id = cup.cupoagrupador_id ");
        querySBuilder.append(" AND ca.fechabaja IS NULL)  ");
        querySBuilder.append(" left join lugar lu on lugar_id = lu.id ");
        querySBuilder.append(" WHERE contrato_id = '" + contrato_id + "' AND CODCUPO ='" + _returnCuposCuposVal1.getNumeroCupo() + "' ");

        final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
            @Override
            public void thisIsTheResulset(ResultSet rset) throws SQLException {

                if (rset.next()) {
                    _returnCuposCuposVal1.setDestinatariocuit(rset.getString("DESTINATARIOCUIT"));
                    _returnCuposCuposVal1.setCorredorcuit(rset.getString("CORREDORCOMPRADORCUIT"));
                    _returnCuposCuposVal1.setIntermediariocuit(rset.getString("INTERMEDIARIOCUIT"));
                    _returnCuposCuposVal1.setCargadorcuit(rset.getString("CARGADORCUIT"));
                    _returnCuposCuposVal1.setRemitentecomercialcuit(rset.getString("REMITENTECOMERCIALCUIT"));
                    _returnCuposCuposVal1.setCorredorvendcuit(rset.getString("CORREDORVENDCUIT"));

                    _returnCuposCuposVal1.setDestinatariorazonsocial(rset.getString("DESTINATARIORAZONSOCIAL"));
                    _returnCuposCuposVal1.setCorredorrazonsocial(rset.getString("CORREDORCOMPRADORRAZONSOCIAL"));
                    _returnCuposCuposVal1.setIntermediariorazonsocial(rset.getString("INTERMEDIARIORAZONSOCIAL"));
                    _returnCuposCuposVal1.setCargadorrazonsocial(rset.getString("CARGADORRAZONSOCIAL"));
                    _returnCuposCuposVal1.setRemitentecomercialrazonsocial(rset.getString("REMITENTECOMERCIALRAZONSOCIAL"));
                    _returnCuposCuposVal1.setCorredorvendrazonsocial(rset.getString("CORREDORVENDRAZONSOCIAL"));

                    _returnCuposCuposVal1.setDirecciondestino(rset.getString("DIRECCIONDESTINO"));
                    _returnCuposCuposVal1.setProvinciadestino(rset.getString("PROVINCIADESTINO"));
                    _returnCuposCuposVal1.setLocalidaddestino(rset.getString("LOCALIDADDESTINO"));
                    _returnCuposCuposVal1.setDestino(rset.getString("DESTINO"));
                    _returnCuposCuposVal1.setCuitdestino(rset.getString("CUITDESTINO"));

                }
            }
        };
        ZeniQueryExcecutor.excecuteSelectNoLog(querySBuilder.toString(), resb);

    }


    /*
	 * (non-Javadoc)
	 *
	 * @see ZeniWSDL#obtenerCartasDePorte(ar.com.zeni.zeniwsdl .AuthType auth
	 * ,)java.lang.String id )*
     */
    @Override
    public ArrayOfCartaDePorteType obtenerCartasDePorte(AuthType auth,
            CuentaType cuenta, RangoFechaType rangoFecha,
            ArrayOfProductoType productos) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog(
                "Executing operation obtenerCartasDePorte");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuenta);
        ZeniContextServer.getInstance().printVerboLog(rangoFecha);
        ZeniContextServer.getInstance().printVerboLog(productos);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc
                    .get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (rangoFecha == null
                    || DateUtil.ToString.ddMMyyyy(rangoFecha.getDesde()) == null) {
                throw new ZeniServerExeption(
                        "La fecha rangoFecha desde no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFecha == null
                    || DateUtil.ToString.ddMMyyyy(rangoFecha.getHasta()) == null) {
                throw new ZeniServerExeption(
                        "La fecha rangoFecha hasta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (cuenta == null || cuenta.getId() == null
                    || cuenta.getId().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption(
                        "El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            return innerCartaDePorte(cuenta, rangoFecha, productos, null);
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

    protected final ArrayOfCartaDePorteType innerCartaDePorte(
            CuentaType cuenta, RangoFechaType rangoFecha,
            ArrayOfProductoType productos, String idContrato)
            throws ZeniDBExeption {
        final ArrayOfCartaDePorteType _return = new ArrayOfCartaDePorteType();
        final List<CartaDePorteType> _returnEntregas = new ArrayList<CartaDePorteType>();
        String listaDeProductos = ZeniContextServer.VACIO;
        if (productos != null && productos.getProductos() != null
                && productos.getProductos().size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < productos.getProductos().size(); i++) {
                sb.append(",'").append(productos.getProductos().get(i).getId())
                        .append("'");
            }
            listaDeProductos = sb.deleteCharAt(0)
                    .insert(0, " and e.producto_id in (").append(") ")
                    .toString();
        }
        String opciones;
        if (idContrato == null || idContrato.equals(ZeniContextServer.VACIO)) {
            String sb = " and (c.vendedor_id=" + cuenta.getId() + " or c.comprador_id=" + cuenta.getId() + ") and trunc(c.fechaoperacion) >= to_date('" + DateUtil.ToString.ddMMyyyy(rangoFecha.getDesde()) + "','dd-mm-yyyy') and trunc(c.fechaoperacion) <= to_date('" + DateUtil.ToString.ddMMyyyy(rangoFecha.getHasta()) + "','dd-mm-yyyy') " + listaDeProductos;
            opciones = sb;
        } else {
            opciones = " and c.id = " + idContrato;
        }
        final StringBuilder querySBuilder = new StringBuilder()
                .append("select c.numerocontrato numerocontrato, e.nrocartadeporte nrocartadeporte, ep.cantidad cantidad, ciudadd.nombre destino, ciudadp.nombre procedencia, to_char(e.fechadescargamercaderia, 'yyyy-MM-dd') fechadescargamercaderia, e.nrovagon vagasig, e.ctg from ")
                .append(owner)
                .append("entrega e inner join ")
                .append(owner)
                .append("ciudad ciudadd ON ( ciudadd.id = e.destinociudad_id) inner join ")
                .append(owner)
                .append("ciudad ciudadp ON ( ciudadp.id = e.procedencia_id) inner join ")
                .append(owner)
                .append("entrega_partida ep on (ep.entrega_id=e.id and ep.fechabaja is null) inner join ")
                .append(owner)
                .append("contrato c on (c.id=ep.contrato_id and c.fechabaja is null) where e.fechabaja is null ")
                .append(opciones).append(" order by e.nrocartadeporte");
        final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
            @Override
            public void thisIsTheResulset(ResultSet rset) throws SQLException {
                while (rset.next()) {
                    try {
                        CartaDePorteType _returnEntregasVal1 = new CartaDePorteType();
                        _returnEntregasVal1.setNumeroContrato(rset
                                .getString("numerocontrato"));
                        _returnEntregasVal1.setNumeroCartaPorte(rset
                                .getString("nrocartadeporte"));
                        _returnEntregasVal1.setKilos(rset.getFloat("cantidad"));
                        _returnEntregasVal1.setVagasig(rset
                                .getString("vagasig"));
                        _returnEntregasVal1.setCtg(rset
                                .getString("ctg"));
                        _returnEntregasVal1.setDestino(rset
                                .getString("destino"));
                        _returnEntregasVal1.setProcedencia(rset
                                .getString("procedencia"));
                        _returnEntregasVal1
                                .setFechadescargamercaderia(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
                                        .getString("fechadescargamercaderia"))));
                        _returnEntregas.add(_returnEntregasVal1);
                    } catch (ParseException e) {
                        throw new SQLException(
                                "No se puede determinar la fecha en el query.",
                                e);
                    }
                }
            }
        };
        ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
        _return.getEntregas().addAll(_returnEntregas);
        return _return;
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see ZeniWSDL#obtenerListadoDeEntregas(ar.com.zeni.zeniwsdl
	 * .AuthType,CuentaType ,RangoFechaType rangoFecha,EnumCompradorVendedorType
	 * ,ArrayOfProductoType ), ArrayOfEntregaType )*
     */
    @Override
    public ArrayOfEntregaType obtenerListadoDeEntregas(AuthType auth,
            CuentaType cuenta, RangoFechaType rangoFecha,
            EnumCompradorVendedorType condicion, ArrayOfProductoType productos)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog(
                "Executing operation obtenerListadoDeEntregas");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuenta);
        ZeniContextServer.getInstance().printVerboLog(rangoFecha);
        ZeniContextServer.getInstance().printVerboLog(condicion);
        ZeniContextServer.getInstance().printVerboLog(productos);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc
                    .get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (rangoFecha == null
                    || DateUtil.ToString.ddMMyyyy(rangoFecha.getDesde()) == null) {
                throw new ZeniServerExeption(
                        "La fecha rangoFecha desde no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFecha == null
                    || DateUtil.ToString.ddMMyyyy(rangoFecha.getHasta()) == null) {
                throw new ZeniServerExeption(
                        "La fecha rangoFecha hasta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (cuenta == null || cuenta.getId() == null
                    || cuenta.getId().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption(
                        "El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final ArrayOfEntregaType _return = new ArrayOfEntregaType();
            final List<EntregaType> _returnEntregas = new ArrayList<EntregaType>();
            String listaDeProductos = ZeniContextServer.VACIO;
            if (productos != null && productos.getProductos() != null
                    && productos.getProductos().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < productos.getProductos().size(); i++) {
                    sb.append(",'")
                            .append(productos.getProductos().get(i).getId())
                            .append("'");
                }
                listaDeProductos = sb.deleteCharAt(0)
                        .insert(0, " and e.producto_id in (").append(") ")
                        .toString();
            }
            final StringBuilder querySBuilder = new StringBuilder();
            querySBuilder.append("select * from (");
            if (condicion.equals(EnumCompradorVendedorType.COMPRADOR)
                    || condicion.equals(EnumCompradorVendedorType.AMBOS)) {
                querySBuilder
                        .append("select c.id contrato_id, e.fechadescargamercaderia fecha,c.numerocontrato,e.nrocartadeporte,e.ctg, ")
                        .append("round(ep.cantidad,3) cantidad, ")
                        .append("p.descripcion,cparte.razonsocial contraparte,proc.nombre procedencia, ")
                        .append("dest.nombre ")
                        .append("from entrega e ")
                        .append("inner join entrega_partida ep on (ep.entrega_id=e.id and ep.fechabaja is null) ")
                        .append("inner join contrato c on (c.id=ep.contrato_id and c.fechabaja is null) ")
                        .append("inner join producto p on (p.id=e.producto_id) ")
                        .append("inner join cliente cparte on (cparte.id=e.clientevendedor_id) ")
                        .append("inner join ciudad proc on (proc.id=e.procedencia_id) ")
                        .append("inner join ciudad dest on (dest.id=e.destinociudad_id) ")
                        .append("where e.fechabaja is null ")
                        .append("and c.comprador_id=")
                        .append(cuenta.getId())
                        .append(listaDeProductos)
                        .append(" ")
                        .append("and trunc(e.fechadescargamercaderia) >= to_date('")
                        .append(DateUtil.ToString.yyyyMMdd(rangoFecha
                                .getDesde()))
                        .append("','yyyy-MM-dd') ")
                        .append("and trunc(e.fechadescargamercaderia) <= to_date('")
                        .append(DateUtil.ToString.yyyyMMdd(rangoFecha
                                .getHasta())).append("','yyyy-MM-dd') ");
            }
            if (condicion.equals(EnumCompradorVendedorType.AMBOS)) {
                querySBuilder.append(" union ");
            }
            if (condicion.equals(EnumCompradorVendedorType.VENDEDOR)
                    || condicion.equals(EnumCompradorVendedorType.AMBOS)) {
                querySBuilder
                        .append("select c.id contrato_id,e.fechadescargamercaderia fecha,c.numerocontrato,e.nrocartadeporte,e.ctg, ")
                        .append("round(ep.cantidad,3) cantidad, ")
                        .append("p.descripcion,cparte.razonsocial contraparte,proc.nombre procedencia, ")
                        .append("dest.nombre ")
                        .append("from entrega e ")
                        .append("inner join entrega_partida ep on (ep.entrega_id=e.id and ep.fechabaja is null) ")
                        .append("inner join contrato c on (c.id=ep.contrato_id and c.fechabaja is null) ")
                        .append("inner join producto p on (p.id=e.producto_id) ")
                        .append("inner join cliente cparte on (cparte.id=e.clientecomprador_id) ")
                        .append("inner join ciudad proc on (proc.id=e.procedencia_id) ")
                        .append("inner join ciudad dest on (dest.id=e.destinociudad_id) ")
                        .append("where e.fechabaja is null ")
                        .append("and c.vendedor_id=")
                        .append(cuenta.getId())
                        .append(listaDeProductos)
                        .append(" ")
                        .append("and trunc(e.fechadescargamercaderia) >= to_date('")
                        .append(DateUtil.ToString.yyyyMMdd(rangoFecha
                                .getDesde()))
                        .append("','yyyy-MM-dd') ")
                        .append("and trunc(e.fechadescargamercaderia) <= to_date('")
                        .append(DateUtil.ToString.yyyyMMdd(rangoFecha
                                .getHasta())).append("','yyyy-MM-dd') ");
            }
            querySBuilder.append(" ) order by fecha desc");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset)
                        throws SQLException {
                    while (rset.next()) {
                        final EntregaType _returnEntregasVal1 = new EntregaType();
                        try {
                            _returnEntregasVal1
                                    .setFecha(DateUtil.Converters
                                            .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
                                                    .getString("fecha"))));
                            _returnEntregasVal1.setContrato(rset
                                    .getString("numerocontrato"));
                            _returnEntregasVal1.setContratId(rset
                                    .getString("contrato_id"));
                            _returnEntregasVal1.setKgEntrega(rset
                                    .getFloat("cantidad"));
                            _returnEntregasVal1.setContraparte(rset
                                    .getString("contraparte"));
                            _returnEntregasVal1.setProcedencia(rset
                                    .getString("procedencia"));
                            _returnEntregasVal1.setProductoDescripcion(rset
                                    .getString("descripcion"));
                            _returnEntregasVal1.setDestino(rset
                                    .getString("nombre"));
                            _returnEntregasVal1.setNroCPorte(rset
                                    .getString("nrocartadeporte"));
                            _returnEntregasVal1.setCtg(rset
                                    .getString("ctg"));
                            // _returnEntregasVal1.setTipoCompradorVendedor(rset.getString("tipoComp").equals("comprador")?EnumCompradorVendedorType.COMPRADOR:EnumCompradorVendedorType.VENDEDOR);
                            _returnEntregas.add(_returnEntregasVal1);
                        } catch (ParseException e) {
                            throw new SQLException(
                                    "No se puede determinar la fecha en el query.",
                                    e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getEntregas().addAll(_returnEntregas);
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

    public ArrayOfMuestraType obtenerMuestras(AuthType auth, CuentaType cuenta,
            RangoFechaType rangoFechasAlta, String campaniaId, String contrato,
            ArrayOfProductoType productos) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog(
                "Executing operation obtenerMuestras");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuenta);
        ZeniContextServer.getInstance().printVerboLog(rangoFechasAlta);
        ZeniContextServer.getInstance().printVerboLog(campaniaId);
        ZeniContextServer.getInstance().printVerboLog(contrato);
        ZeniContextServer.getInstance().printVerboLog(productos);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc
                    .get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (DateUtil.ToString.ddMMyyyy(rangoFechasAlta.getDesde()) == null) {
                throw new ZeniServerExeption(
                        "La fecha rangoFecha desde no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (DateUtil.ToString.ddMMyyyy(rangoFechasAlta.getHasta()) == null) {
                throw new ZeniServerExeption(
                        "La fecha rangoFecha hasta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (cuenta.getId() == null
                    || cuenta.getId().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption(
                        "El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            String listaDeProductos = ZeniContextServer.VACIO;
            if (productos != null && productos.getProductos() != null
                    && productos.getProductos().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < productos.getProductos().size(); i++) {
                    sb.append(",'")
                            .append(productos.getProductos().get(i).getId())
                            .append("'");
                }
                listaDeProductos = sb.deleteCharAt(0)
                        .insert(0, " and am.producto_id in (").append(") ")
                        .toString();
            }
            // final ArrayOfMuestraWrapperType _return = new
            // ArrayOfMuestraWrapperType();
            // final List<MuestraWrapperType> _returnArrayListEEEEE = new
            // ArrayList<MuestraWrapperType>();
            final ArrayOfMuestraType _return = new ArrayOfMuestraType();
            final List<MuestraType> _returnArrayListEEEEE = new ArrayList<MuestraType>();
            String opcionesFiltro = listaDeProductos
                    + (campaniaId != null
                    && !campaniaId.equals(ZeniContextServer.VACIO) ? " AND c.aniocosecha ='"
                    + campaniaId + "'"
                    : ZeniContextServer.VACIO)
                    + (contrato != null
                    && !contrato.equals(ZeniContextServer.VACIO) ? " AND c.numerocontrato ='"
                    + contrato + "'"
                    : ZeniContextServer.VACIO)
                    + ((rangoFechasAlta != null
                    && DateUtil.ToString.ddMMyyyy(rangoFechasAlta
                            .getDesde()) != null && DateUtil.ToString
                            .ddMMyyyy(rangoFechasAlta.getHasta()) != null) ? " AND c.fechaoperacion <= to_date('"
                    + DateUtil.ToString.ddMMyyyy(rangoFechasAlta
                            .getHasta())
                    + "','"
                    + Formats.ddMMyyyy
                    + "') AND c.fechaoperacion >= to_date('"
                    + DateUtil.ToString.ddMMyyyy(rangoFechasAlta
                            .getDesde())
                    + "','"
                    + Formats.ddMMyyyy
                    + "') "
                    : ZeniContextServer.VACIO);
            class AsetCont {

                String Contrato;
                float TotKilos;
                float PromPond;
                protected String ContratoId;
            }
            ;
            class AsetMuse {

                String MuestraId;
                float Kilos;
                String CPorte;
                String Ctg;
                String NroCertificado;
                FechaTimeType FechaCertificado;
            }
            ;
            final StringBuilder querySBuilderMain = new StringBuilder()
                    .append("select c.id contrato_id, c.numerocontrato, sum(ep.cantidad) totalkilos, sum(nvl(factor.factor,0))/decode(count(factor.factor),0,1,count(factor.factor)) ppp ")
                    .append("from ")
                    .append(owner)
                    .append("contrato c ")
                    .append("inner join ")
                    .append(owner)
                    .append("entrega_partida ep on (ep.contrato_id=c.id and ep.fechabaja is null) ")
                    .append("inner join ")
                    .append(owner)
                    .append("entrega e on (e.id=ep.entrega_id and e.fechabaja is null) ")
                    .append("inner join ")
                    .append(owner)
                    .append("analisis_muestra am on (am.id = e.analisismuestra_id and am.fechabaja is null) ")
                    .append("left outer join (select am2.id id,100+(sum(decode(rc2.modificafactor,1,dam2.porcentajebonificacionrebaja,0))/100) factor ")
                    .append("from ")
                    .append(owner)
                    .append("analisis_muestra am2 ")
                    .append("inner join ")
                    .append(owner)
                    .append("detalle_analisis_muestra dam2 on (dam2.analisismuestra_id=am2.id and dam2.fechabaja is null) ")
                    .append("inner join ")
                    .append(owner)
                    .append("concepto_rubro_calidad crc2 on (crc2.id = dam2.conceptorubrocalidad_id) ")
                    .append("inner join ")
                    .append(owner)
                    .append("rubro_calidad rc2 on (rc2.conceptorubrocalidad_id = crc2.id and rc2.producto_id=am2.producto_id) ")
                    .append("where am2.fechabaja is null ")
                    .append("group by am2.id ) factor on (factor.id=am.id) ")
                    .append("where c.fechabaja is null and ( c.vendedor_id = ")
                    .append(cuenta.getId()).append(" or c.comprador_id = ")
                    .append(cuenta.getId()).append(" ) ")
                    .append(opcionesFiltro)
                    .append(" group by c.id,c.numerocontrato ");
            final ArrayList<AsetCont> AAsetCont = new ArrayList<AsetCont>();
            final ResulsetObjectBuilder resbMain = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(final ResultSet rsetCont)
                        throws SQLException {
                    while (rsetCont.next()) {
                        AsetCont a = new AsetCont();
                        a.ContratoId = rsetCont.getString("contrato_id");
                        a.Contrato = rsetCont.getString("numerocontrato");
                        a.PromPond = rsetCont.getFloat("ppp");
                        a.TotKilos = rsetCont.getFloat("totalkilos");
                        AAsetCont.add(a);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilderMain.toString(),
                    resbMain);
            for (final AsetCont a : AAsetCont) {
                final ArrayList<AsetMuse> AAsetMuse = new ArrayList<AsetMuse>();
                final String contratid = a.ContratoId;
                final StringBuilder querySBuilderMuest = new StringBuilder()
                        .append("select am.id,am.nrocertificadoanalisis certificado, to_char(am.fechaanalisis, 'dd-MM-yyyy') fecha,ep.cantidad kilos, ")
                        .append("e.nrocartadeporte,e.ctg, ")
                        .append("100+(sum(decode(rc.modificafactor,1,dam.porcentajebonificacionrebaja,0))/100) factor ")
                        .append("from ")
                        .append(owner)
                        .append("entrega_partida ep ")
                        .append("inner join ")
                        .append(owner)
                        .append("entrega e on (e.id=ep.entrega_id and e.fechabaja is null) ")
                        .append("inner join ")
                        .append(owner)
                        .append("analisis_muestra am on (e.analisismuestra_id=am.id and am.fechabaja is null) ")
                        .append("inner join ")
                        .append(owner)
                        .append("detalle_analisis_muestra dam on (dam.analisismuestra_id=am.id and dam.fechabaja is null) ")
                        .append("inner join ")
                        .append(owner)
                        .append("concepto_rubro_calidad crc on (crc.id = dam.conceptorubrocalidad_id) ")
                        .append("inner join ")
                        .append(owner)
                        .append("rubro_calidad rc on (rc.conceptorubrocalidad_id = crc.id and rc.producto_id=am.producto_id) ")
                        .append("where ep.fechabaja is null ")
                        .append("and ep.contrato_id = ")
                        .append(contratid)
                        .append(" group by am.id,am.nrocertificadoanalisis,to_char(am.fechaanalisis, 'dd-MM-yyyy'),ep.cantidad,e.nrocartadeporte,e.ctg ");
                final ResulsetObjectBuilder resbMues = new ResulsetObjectBuilder() {
                    @Override
                    public void thisIsTheResulset(final ResultSet rsetMuse)
                            throws SQLException {
                        while (rsetMuse.next()) {
                            try {
                                final AsetMuse er = new AsetMuse();
                                er.MuestraId = rsetMuse.getString("id");
                                er.Kilos = rsetMuse.getFloat("kilos");
                                er.CPorte = rsetMuse
                                        .getString("nrocartadeporte");
                                er.Ctg = rsetMuse
                                        .getString("ctg");
                                er.NroCertificado = rsetMuse
                                        .getString("certificado");
                                er.FechaCertificado = DateUtil.Converters
                                        .DateToFechaTimeType(DateUtil.Parsers
                                                .yyyyMMdd(rsetMuse
                                                        .getString("fecha")));
                                AAsetMuse.add(er);
                            } catch (Exception e) {

                            }
                        }
                    }
                };
                ZeniQueryExcecutor.excecuteSelect(
                        querySBuilderMuest.toString(), resbMues);
                for (final AsetMuse b : AAsetMuse) {
                    final String muestrid = b.MuestraId;
                    final StringBuilder querySBuilder = new StringBuilder()
                            .append("select  crc.nombre analisis, dam.resultado resultado ")
                            .append("from ")
                            .append(owner)
                            .append("analisis_muestra am ")
                            .append("inner join ")
                            .append(owner)
                            .append("detalle_analisis_muestra dam on (dam.analisismuestra_id=am.id and dam.fechabaja is null) ")
                            .append("inner join ")
                            .append(owner)
                            .append("concepto_rubro_calidad crc on (crc.id = dam.conceptorubrocalidad_id) ")
                            .append("inner join ")
                            .append(owner)
                            .append("rubro_calidad rc on (rc.conceptorubrocalidad_id = crc.id and rc.producto_id=am.producto_id) ")
                            .append("where dam.fechabaja is null ")
                            .append("and dam.analisismuestra_id = ")
                            .append(muestrid);
                    final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                        @Override
                        public void thisIsTheResulset(ResultSet rsetAna)
                                throws SQLException {
                            while (rsetAna.next()) {
                                try {
                                    final MuestraType erasmo = new MuestraType();
                                    erasmo.setContrato(a.Contrato);
                                    erasmo.setTotKilos(a.TotKilos);
                                    erasmo.setPromPond(a.PromPond);
                                    erasmo.setMuestraId(b.MuestraId);
                                    erasmo.setKilos(b.Kilos);
                                    erasmo.setCPorte(b.CPorte);
                                    erasmo.setCtg(b.Ctg);
                                    erasmo.setNroCertificado(b.NroCertificado);
                                    erasmo.setFechaCertificado(b.FechaCertificado);
                                    erasmo.setAnalisis(rsetAna
                                            .getString("analisis"));
                                    erasmo.setNroAnalisis(rsetAna
                                            .getString("analisis"));
                                    erasmo.setResult(rsetAna
                                            .getFloat("resultado"));
                                    _returnArrayListEEEEE.add(erasmo);
                                } catch (Exception e) {

                                }
                            }
                        }
                    };
                    ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(),
                            resb);
                }
            }
            _return.getMuestras().addAll(_returnArrayListEEEEE);
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

    /*
	 * (non-Javadoc)
	 *
	 * @see ZeniWSDL#obtenerListadoDeOperatoria(AuthType auth ,)CuentaType
	 * cuenta ,)RangoFechaType rangoFecha ,)java.lang.String opciones
	 * ,)java.lang.String campaniaId ,)ArrayOfProductoType productos )*
     */
    @Override
    public ArrayOfOperatoriaDListadoType obtenerListadoDeOperatoria(
            final AuthType auth, CuentaType cuenta, RangoFechaType rangoFecha,
            EnumOpcionesListadoEntrega opciones, java.lang.String campaniaId,
            ArrayOfProductoType productos) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog(
                "Executing operation obtenerListadoDeOperatoria");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuenta);
        ZeniContextServer.getInstance().printVerboLog(rangoFecha);
        ZeniContextServer.getInstance().printVerboLog(opciones);
        ZeniContextServer.getInstance().printVerboLog(campaniaId);
        ZeniContextServer.getInstance().printVerboLog(productos);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc
                    .get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (rangoFecha == null
                    || DateUtil.ToString.ddMMyyyy(rangoFecha.getDesde()) == null) {
                throw new ZeniServerExeption(
                        "La fecha rangoFecha desde no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFecha == null
                    || DateUtil.ToString.ddMMyyyy(rangoFecha.getHasta()) == null) {
                throw new ZeniServerExeption(
                        "La fecha rangoFecha hasta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (cuenta == null || cuenta.getId() == null
                    || cuenta.getId().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption(
                        "El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final ArrayOfOperatoriaDListadoType _return = new ArrayOfOperatoriaDListadoType();
            final List<OperatoriaDListadoType> _returnOperatorias = new ArrayList<OperatoriaDListadoType>();
            String listaDeProductos = ZeniContextServer.VACIO;
            if (productos != null && productos.getProductos() != null
                    && productos.getProductos().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < productos.getProductos().size(); i++) {
                    sb.append(",'")
                            .append(productos.getProductos().get(i).getId())
                            .append("'");
                }
                listaDeProductos = sb.deleteCharAt(0)
                        .insert(0, " and p.id in (").append(") ").toString();
            }
            final String filtroCampania = campaniaId != null
                    && !campaniaId.equals(ZeniContextServer.VACIO) ? " AND c.aniocosecha = '"
                    + campaniaId + "' "
                    : ZeniContextServer.VACIO;
            final String filtroCuenta = " AND (c.vendedor_id = "
                    + cuenta.getId() + " or c.comprador_id = " + cuenta.getId()
                    + ") ";
            final String filtroOpciones = " AND c.cobrodemercaderia "
                    + ((opciones != null
                    && !opciones.equals(ZeniContextServer.VACIO) && !opciones
                    .equals(EnumOpcionesListadoEntrega.SIN_CONTRATO_A_FIJAR)) ? "= 'F' "
                    : "<> 'F' ");
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select ")
                    .append("to_char(perfumo.fechaoperacion, 'yyyy-MM-dd') FechaAlta,")
                    .append("to_char(perfumo.plazofijaciondesde , 'yyyy-MM-dd') PlazoFijacionDesde,")
                    .append("to_char(perfumo.plazofijacionhasta , 'yyyy-MM-dd') PlazoFijacionHasta,")
                    .append("to_char(perfumo.periodoentregadesde , 'yyyy-MM-dd') PeriodoEntreDesde,")
                    .append("to_char(perfumo.plazofijacionhasta , 'yyyy-MM-dd') PeriodoEntreHasta,")
                    .append("perfumo.contrato_id ContratoId,")
                    .append("perfumo.contrato Contrato,")
                    .append("perfumo.contraparte Contraparte,")
                    .append("perfumo.producto ProductoDescripcion,")
                    .append("'noexiste' cpago,")
                    .append("perfumo.monedadida moneda_id,")
                    .append("perfumo.precio precio,")
                    .append("perfumo.cantidadpactada CantidadPactada,")
                    .append("perfumo.cantidadentregada Entregado,")
                    .append("perfumo.pendienteentrega PendientaEntrega,")
                    .append("perfumo.kgfijada KgFijados,")
                    .append("perfumo.pendientefijar PendienteFijacion,")
                    .append("perfumo.kilosfacturados KgFacturados,")
                    .append("perfumo.importecobrado ImporteFacturado,")
                    .append("perfumo.importecobrado ImporteCobradoPesos,")
                    .append("perfumo.pendientecobro PendienteDeCobroEnPesos,")
                    .append("perfumo.kgpendientefacturar KgPendienteDeFacturar,")
                    .append("perfumo.imppedientedefacturar ImporteEstimPesosPendFact,")
                    .append("perfumo.finalesfactcobradas FinalesFacturadasCobradas,")
                    .append("perfumo.finfactpendientecobro FinalesFacturadasPendCobro,")
                    .append("perfumo.cantidadpendfactfinal KgPendienteFacturarPorFinal")
                    .append(" from (")
                    .append("select c.fechaoperacion, c.id contrato_id,c.numerocontrato contrato, decode(c.comprador_id,")
                    .append(cuenta.getId())
                    .append(",ccv.denominacioncuenta,ccc.denominacioncuenta) contraparte, p.nombre producto,m.id monedadida, m.simbolo moneda, c.preciomercaderia precio, fija.fechadesde plazofijaciondesde, fija.fechahasta plazofijacionhasta, c.fechaentregadesde periodoentregadesde,c.fechaentregahasta periodoentregahasta, c.cantidadpactada cantidadpactada,ent.cantidadentregada cantidadentregada, decode(sign( c.cantidadpactada -nvl(ent.cantidadentregada,0)),1,c.cantidadpactada -nvl(ent.cantidadentregada,0),0) pendienteentrega, nvl(fija.cantidad,0) kgfijada, decode(sign( c.cantidadpactada-nvl(fija.cantidad,0)),1,c.cantidadpactada-nvl(fija.cantidad,0),0) pendientefijar, nvl(cf.cantidadfacturada,0) kilosfacturados, decode(c.comprador_id,")
                    .append(cuenta.getId())
                    .append(",abs(cf.debe),abs(cf.haber)) importefacturado, cobra.importecobrado importecobrado, decode(c.comprador_id,")
                    .append(cuenta.getId())
                    .append(",abs(cf.debe),abs(cf.haber)) -cobra.importecobrado pendientecobro, c.cantidadpactada -nvl(cf.cantidadfacturada,0) kgpendientefacturar, c.preciomercaderia * (ent.cantidadentregada-nvl(cf.cantidadfacturada,0)) imppedientedefacturar, cobra.importecobradofinales finalesfactcobradas, decode(c.comprador_id,")
                    .append(cuenta.getId())
                    .append(",abs(ffactpend.debe),abs(ffactpend.haber)) finfactpendientecobro, kgpendfact.cantidadpend cantidadpendfactfinal from ")
                    .append(owner)
                    .append("contrato c inner join ")
                    .append(owner)
                    .append("producto p on (p.id=c.producto_id) inner join ")
                    .append(owner)
                    .append("cuenta_cliente ccc on (ccc.id=c.comprador_id and ccc.fechabaja is null) inner join ")
                    .append(owner)
                    .append("cuenta_cliente ccv on (ccv.id=c.vendedor_id and ccv.fechabaja is null) inner join ")
                    .append(owner)
                    .append("ciudad proc on (proc.id=c.procedencia_id) inner join ")
                    .append(owner)
                    .append("ciudad dest on (dest.id=c.destino_id) inner join ")
                    .append(owner)
                    .append("calidad cal on (cal.id=c.calidad_id) inner join ")
                    .append(owner)
                    .append("tipo_transporte tt on (tt.id=c.tipotransporte_id) inner join ")
                    .append(owner)
                    .append("moneda m on (m.id=c.monedafacturacionmercaderia_id) left outer join (select ep.contrato_id,sum(ep.cantidad) cantidadentregada from ")
                    .append(owner)
                    .append("entrega_partida ep where ep.fechabaja is null group by contrato_id) ent on (ent.contrato_id=c.id) left outer join (select fp.contrato_id,sum(fp.cantidadfacturada) cantidadfacturada,sum(nvl(fp.totaldebe,0)) debe,sum(nvl(fp.totalhaber,0)) haber from ")
                    .append(owner)
                    .append("factura_producto fp inner join ")
                    .append(owner)
                    .append("tipo_comprobante tc on (tc.id=fp.tipocomprobante_id and tc.abreviatura in ('LF','LP','BP','BF')) where fp.fechabaja is null group by fp.contrato_id) cf on (cf.contrato_id=c.id) left outer join (select fpfinal.contrato_id,sum(fpfinal.cantidadfacturada) cantidadfacturada,sum(nvl(fpfinal.totaldebe,0)) debe,sum(nvl(fpfinal.totalhaber,0)) haber from ")
                    .append(owner)
                    .append("factura_producto fpfinal inner join ")
                    .append(owner)
                    .append("tipo_comprobante tcomp on (tcomp.id=fpfinal.tipocomprobante_id and tcomp.abreviatura in ('LF','BF')) where fpfinal.fechabaja is null group by fpfinal.contrato_id) cfinal on (cfinal.contrato_id=c.id) left outer join (select gf.contrato_id,sum(f.cantidadafijar) cantidad, max(f.fechafijacion) fechahasta, min(f.fechafijacion) fechadesde from ")
                    .append(owner)
                    .append("grupo_fijaciones gf inner join ")
                    .append(owner)
                    .append("fijacion f on (f.grupofijaciones_id = gf.id and f.fechabaja is null) where gf.fechabaja is null group by gf.contrato_id) fija on (fija.contrato_id=c.id) left outer join (select contrato_id, sum(rf.importe) importecobrado, sum(decode(tc.abreviatura,'LF',rf.importe,'BF',rf.importe,0)) importecobradofinales from ")
                    .append(owner)
                    .append("factura_producto fprod inner join ")
                    .append(owner)
                    .append("tipo_comprobante tc on (tc.id=fprod.tipocomprobante_id and tc.abreviatura in ('LF','LP','BP','BF')) inner join ")
                    .append(owner)
                    .append("recibo_factura rf on (rf.factura_id=fprod.id and rf.fechabaja is null) inner join ")
                    .append(owner)
                    .append("recibo r on (r.id=rf.recibo_id and r.fechabaja is null) where fprod.fechabaja is null and r.fechaconfirmacion is not null and fprod.fechabaja is null group by fprod.contrato_id) cobra on (cobra.contrato_id=c.id) left outer join (select pcobr.contrato_id, sum(nvl(pcobr.totaldebe,0)-nvl(rec.importe,0)) debe, sum(nvl(pcobr.totalhaber,0)-nvl(rec.importe,0)) haber from ")
                    .append(owner)
                    .append("factura_producto pcobr inner join ")
                    .append(owner)
                    .append("tipo_comprobante tc on (tc.id=pcobr.tipocomprobante_id and tc.abreviatura in ('LF','BF')) left outer join (select rfc.factura_id factura_id, sum(rfc.importe) importe from ")
                    .append(owner)
                    .append("recibo_factura rfc inner join ")
                    .append(owner)
                    .append("recibo rr on (rr.id=rfc.recibo_id and rr.fechabaja is null) where rfc.fechabaja is null and rr.fechaconfirmacion is not null group by rfc.factura_id) rec on (rec.factura_id=pcobr.id) where pcobr.fechabaja is null group by pcobr.contrato_id) ffactpend on (ffactpend.contrato_id=c.id)  left outer join (select kgpend.contrato_id,sum(kgpend.cantidad) cantidadpend from (select  fppendf.contrato_id, sum(abs(nvl(fppendf.cantidadfacturada,0)))*-1 cantidad from ")
                    .append(owner)
                    .append("factura_producto fppendf inner join ")
                    .append(owner)
                    .append("tipo_comprobante tcpendf on (tcpendf.id=fppendf.tipocomprobante_id and tcpendf.abreviatura in ('LF','BF')) where fppendf.fechabaja is null group by fppendf.contrato_id union select  fppend.contrato_id, sum(abs(nvl(fppend.cantidadfacturada,0))) cantidad from ")
                    .append(owner)
                    .append("factura_producto fppend inner join ")
                    .append(owner)
                    .append("tipo_comprobante tcpend on (tcpend.id=fppend.tipocomprobante_id and tcpend.abreviatura in ('LP','BP')) where fppend.fechabaja is null group by fppend.contrato_id) kgpend group by kgpend.contrato_id) kgpendfact on (kgpendfact.contrato_id=c.id) where c.fechabaja is null and c.cumplido = 0 ")
                    .append(filtroCuenta).append(filtroCampania)
                    .append(filtroOpciones).append(listaDeProductos)
                    .append(" AND c.fechaoperacion >= to_date('")
                    .append(DateUtil.ToString.yyyyMMdd(rangoFecha.getDesde()))
                    .append("','yyyy-MM-dd') ")
                    .append(" AND c.fechaoperacion <= to_date('")
                    .append(DateUtil.ToString.yyyyMMdd(rangoFecha.getHasta()))
                    .append("','yyyy-MM-dd') ")
                    .append(") perfumo order by perfumo.fechaoperacion desc ");
            final HashMap<String, MonedaType> monedaMap = innerObtenerMoneda();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset)
                        throws SQLException {
                    while (rset.next()) {
                        try {
                            OperatoriaDListadoType _returnOperatoriasVal1 = new OperatoriaDListadoType();
                            _returnOperatoriasVal1
                                    .setFechaAlta(DateUtil.Converters
                                            .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
                                                    .getString("FechaAlta"))));
                            _returnOperatoriasVal1
                                    .setPlazoFijacionDesde(DateUtil.Converters
                                            .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
                                                    .getString("PlazoFijacionDesde"))));
                            _returnOperatoriasVal1
                                    .setPlazoFijacionHasta(DateUtil.Converters
                                            .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
                                                    .getString("PlazoFijacionHasta"))));
                            _returnOperatoriasVal1
                                    .setPeriodoEntreDesde(DateUtil.Converters
                                            .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
                                                    .getString("PeriodoEntreDesde"))));
                            _returnOperatoriasVal1
                                    .setPeriodoEntreHasta(DateUtil.Converters
                                            .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
                                                    .getString("PeriodoEntreHasta"))));
                            _returnOperatoriasVal1.setContrato(rset
                                    .getString("Contrato"));
                            _returnOperatoriasVal1.setContratId(rset
                                    .getString("ContratoId"));
                            _returnOperatoriasVal1.setContratoId(rset
                                    .getString("ContratoId"));
                            _returnOperatoriasVal1.setContraparte(rset
                                    .getString("Contraparte"));
                            _returnOperatoriasVal1.setProductoDescripcion(rset
                                    .getString("ProductoDescripcion"));
                            _returnOperatoriasVal1.setCPago(rset
                                    .getString("cpago"));
                            _returnOperatoriasVal1.setMoneda(monedaMap.get(rset
                                    .getString("moneda_id")));
                            _returnOperatoriasVal1.setPrecio(rset
                                    .getDouble("precio"));
                            _returnOperatoriasVal1.setCantidadPactada(rset
                                    .getFloat("CantidadPactada"));
                            _returnOperatoriasVal1.setEntregado(rset
                                    .getFloat("Entregado"));
                            _returnOperatoriasVal1.setPendientaEntrega(rset
                                    .getFloat("PendientaEntrega"));
                            _returnOperatoriasVal1.setKgFijados(rset
                                    .getFloat("KgFijados"));
                            _returnOperatoriasVal1.setPendienteFijacion(rset
                                    .getFloat("PendienteFijacion"));
                            _returnOperatoriasVal1.setKgFacturados(rset
                                    .getFloat("KgFacturados"));
                            _returnOperatoriasVal1.setImporteFacturado(rset
                                    .getDouble("ImporteFacturado"));
                            _returnOperatoriasVal1.setImporteCobradoPesos(rset
                                    .getDouble("ImporteCobradoPesos"));
                            _returnOperatoriasVal1
                                    .setPendienteDeCobroEnPesos(rset
                                            .getDouble("PendienteDeCobroEnPesos"));
                            _returnOperatoriasVal1
                                    .setKgPendienteDeFacturar(rset
                                            .getFloat("KgPendienteDeFacturar"));
                            _returnOperatoriasVal1
                                    .setImporteEstimadoPesosPendienteFacturar(rset
                                            .getDouble("ImporteEstimPesosPendFact"));
                            _returnOperatoriasVal1
                                    .setFinalesFacturadasCobradas(rset
                                            .getFloat("FinalesFacturadasCobradas"));
                            _returnOperatoriasVal1
                                    .setFinalesFacturadasPendienteCobro(rset
                                            .getFloat("FinalesFacturadasPendCobro"));
                            _returnOperatoriasVal1
                                    .setKgPendienteFacturarPorFinal(rset
                                            .getFloat("KgPendienteFacturarPorFinal"));
                            _returnOperatorias.add(_returnOperatoriasVal1);
                        } catch (ParseException e) {
                            throw new SQLException(
                                    "No se puede determinar la fecha en el query.",
                                    e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getOperatorias().addAll(_returnOperatorias);
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

    /*
	 * (non-Javadoc)
	 *
	 * @see ZeniWSDL#obtenerCuentaCorrienteContable(AuthType auth ,)CuentaType
	 * cuenta ,)RangoFechaType rangoFechas )*
     */
    @Override
    public ObtenerCuentaCorrienteContableType obtenerCuentaCorrienteContable(
            final AuthType auth, CuentaType cuenta, RangoFechaType rangoFechas,
            MonedaType filtroDeMoneda) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog(
                "Executing operation obtenerCuentaCorrienteContable");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuenta);
        ZeniContextServer.getInstance().printVerboLog(rangoFechas);
        ZeniContextServer.getInstance().printVerboLog(filtroDeMoneda);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc
                    .get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (rangoFechas == null
                    || DateUtil.ToString.ddMMyyyy(rangoFechas.getDesde()) == null) {
                throw new ZeniServerExeption(
                        "La fecha rangoFecha desde no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFechas == null
                    || DateUtil.ToString.ddMMyyyy(rangoFechas.getHasta()) == null) {
                throw new ZeniServerExeption(
                        "La fecha rangoFecha hasta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (cuenta == null || cuenta.getId() == null
                    || cuenta.getId().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption(
                        "El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final StringBuilder queryComproSBuilder = new StringBuilder()
                    .append("SELECT TO_CHAR(fapu.fecha, 'yyyy-MM-dd') fecha, ")
                    .append("TO_CHAR(fapu.fechavalor, 'yyyy-MM-dd') fechavalor, ")
                    .append("fapu.abreviatura, ")
                    .append("fapu.letra, ")
                    .append("fapu.pvta, ")
                    .append("fapu.nro nrocomprobante, ")
                    .append("fapu.comprobanteId, ")
                    .append("fapu.abreviatura ")
                    .append("||' ' ")
                    .append("||fapu.letra ")
                    .append("||' ' ")
                    .append("||fapu.pvta ")
                    .append("||' ' ")
                    .append("||fapu.nro comprobante, ")
                    .append("fapu.numerocontrato, ")
                    .append("fapu.descripcion, ")
                    .append("fapu.importep, ")
                    .append("fapu.imported, ")
                    .append("fapu.importeped, ")
                    .append("fapu.monedaid monedafactura_id, ")
                    .append("fapu.saldo, ")
                    .append("fapu.contratid ")
                    .append("FROM ")
                    .append("(SELECT f.fechavto fecha, ")
                    .append("f.fechafactura fechavalor, ")
                    .append("tc.abreviatura abreviatura, ")
                    .append("f.letra letra, ")
                    .append("f.nrofacturasucursal pvta, ")
                    .append("f.nrofacturanumero nro, ")
                    .append("f.id comprobanteId, ")
                    .append("c.numerocontrato numerocontrato, ")
                    .append("f.OBSERVACIONESCOBRANZA descripcion, ")
                    .append("DECODE( f.monedafactura_id, '1717', f.importeSaldo, 0) * decode (f.comprador_id,")
                    .append(cuenta.getId())
                    .append(",1,decode(f.cuentacliente_id,")
                    .append(cuenta.getId())
                    .append(",-1,-1)) importep, ")
                    .append("DECODE( f.monedafactura_id, '1718', f.importeSaldo, 0) * decode (f.comprador_id,")
                    .append(cuenta.getId())
                    .append(",1,decode(f.cuentacliente_id,")
                    .append(cuenta.getId())
                    .append(",-1,-1))imported, ")
                    .append("DECODE( f.monedafactura_id, '1718', f.importeSaldo * f.COTIZACIONPESIFICACION, 0) * decode (f.comprador_id,")
                    .append(cuenta.getId())
                    .append(",1,decode(f.cuentacliente_id,")
                    .append(cuenta.getId())
                    .append(",-1,-1)) importeped, ")
                    .append("f.IMPORTESALDO saldo, ")
                    .append("c.id contratid, ")
                    .append("f.monedafactura_id monedaid ")
                    .append("FROM ZENIAPP.factura_producto f ")
                    .append("LEFT OUTER JOIN contrato c ")
                    .append("ON (c.id =f.contrato_id ")
                    .append("AND c.fechabaja IS NULL) ")
                    .append("INNER JOIN tipo_comprobante tc ")
                    .append("ON (tc.id =f.tipocomprobante_id ) ")
                    .append("WHERE f.fechabaja IS NULL ")
                    .append("AND TRUNC(f.fechafactura) >= to_date('2012-11-02','yyyy-MM-dd') ")
                    .append((filtroDeMoneda != null
                            && filtroDeMoneda.getId() != null && !filtroDeMoneda
                            .getId().equals(ZeniContextServer.VACIO)) ? " AND f.monedafactura_id = "
                            + filtroDeMoneda.getId() + " "
                            : ZeniContextServer.VACIO)
                    .append("AND TRUNC(f.fechafactura) >= to_date('")
                    .append(DateUtil.ToString.yyyyMMdd(rangoFechas.getDesde()))
                    .append("','yyyy-MM-dd') ")
                    .append("AND TRUNC(f.fechafactura) <= to_date('")
                    .append(DateUtil.ToString.yyyyMMdd(rangoFechas.getHasta()))
                    .append("','yyyy-MM-dd') ")
                    .append("AND f.estadocobranza = 'P' ")
                    .append("AND (f.vendedor_id = ")
                    .append(cuenta.getId())
                    .append(" ")
                    .append("OR f.comprador_id = ")
                    .append(cuenta.getId())
                    .append(" ")
                    .append("OR f.cuentacliente_id = ")
                    .append(cuenta.getId())
                    .append(") ")
                    .append("UNION ")
                    .append("SELECT m.fecha, ")
                    .append("m.fechavalor fechavalor, ")
                    .append("tc.abreviatura abreviatura, ")
                    .append("m.letra letra, ")
                    .append("m.pvta pvta, ")
                    .append("m.nro nro, ")
                    .append("m.ref_id comprobanteId, ")
                    .append("c1.numerocontrato numerocontrato, ")
                    .append("m.descripcion descripcion, ")
                    .append("DECODE( m.moneda_id, '1717', m.importe, 0) importep, ")
                    .append("DECODE( m.moneda_id, '1718', m.importe, 0) imported, ")
                    .append("DECODE( m.moneda_id, '1718', m.importe * m.valorcotizacion, 0) importeped, ")
                    .append("m.saldopendiente saldo, ")
                    .append("c1.id contratid, ")
                    .append("m.moneda_id monedaid ")
                    .append("FROM ctacte_mov m ")
                    .append("LEFT OUTER JOIN contrato c1 ")
                    .append("ON (c1.id =m.contrato_id ")
                    .append("AND c1.fechabaja IS NULL) ")
                    .append("INNER JOIN tipo_comprobante tc ")
                    .append("ON (tc.id=m.tipocomprobante_id ) ")
                    .append("INNER JOIN ctacte_cuenta c ")
                    .append("ON m.cuenta_id =c.id ")
                    .append("WHERE TRUNC(m.fecha) >= to_date('2012-11-02','yyyy-MM-dd') ")
                    .append((filtroDeMoneda != null
                            && filtroDeMoneda.getId() != null && !filtroDeMoneda
                            .getId().equals(ZeniContextServer.VACIO)) ? " AND c.moneda_id = "
                            + filtroDeMoneda.getId() + " "
                            : ZeniContextServer.VACIO)
                    .append("AND TRUNC(m.fecha) >= to_date('")
                    .append(DateUtil.ToString.yyyyMMdd(rangoFechas.getDesde()))
                    .append("','yyyy-MM-dd') ")
                    .append("AND TRUNC(m.fecha) <= to_date('")
                    .append(DateUtil.ToString.yyyyMMdd(rangoFechas.getHasta()))
                    .append("','yyyy-MM-dd') ")
                    .append("AND c.cuentacliente_id=")
                    .append(cuenta.getId())
                    .append(" ")
                    .append(") fapu ")
                    .append("ORDER BY TO_CHAR(fapu.fechavalor, 'yyyy-MM-dd')  ");
            final ArrayOfCuentaCorrienteContableType _comproArrayCC = new ArrayOfCuentaCorrienteContableType();
            final List<CuentaCorrienteContableType> _comproList = new ArrayList<CuentaCorrienteContableType>();
            final HashMap<String, MonedaType> monedaMap = innerObtenerMoneda();
            final ResulsetObjectBuilder resCompro = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rsetCompro)
                        throws SQLException {
                    while (rsetCompro.next()) {
                        try {
                            final CuentaCorrienteContableType compro = new CuentaCorrienteContableType();
                            compro.setFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers
                                            .yyyyMMdd(rsetCompro
                                                    .getString("fechavalor"))));
                            compro.setTipo(rsetCompro.getString("abreviatura"));
                            compro.setLetra(rsetCompro.getString("letra"));
                            compro.setSuc(rsetCompro.getString("pvta"));
                            compro.setNumeroComprobante(rsetCompro
                                    .getString("nrocomprobante"));
                            compro.setComprobanteId(rsetCompro
                                    .getString("comprobanteId"));
                            compro.setInterno(rsetCompro
                                    .getString("comprobante"));
                            compro.setDetalle(rsetCompro
                                    .getString("descripcion"));
                            compro.setContrato(rsetCompro
                                    .getString("numerocontrato"));
                            compro.setContratId(rsetCompro
                                    .getString("contratId"));
                            compro.setImportePesos(rsetCompro
                                    .getDouble("importep"));
                            compro.setImporteDolares(rsetCompro
                                    .getDouble("imported"));
                            compro.setImporteEquivalentePesos(rsetCompro
                                    .getDouble("importeped"));
                            compro.setSaldo(rsetCompro.getDouble("saldo"));
                            compro.setMoneda(monedaMap.get(rsetCompro
                                    .getString("monedafactura_id")));
                            _comproList.add(compro);
                        } catch (ParseException e) {
                            throw new SQLException(
                                    "No se puede determinar la fecha en el query.",
                                    e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(queryComproSBuilder.toString(),
                    resCompro);
            _comproArrayCC.getComprobantesCC().addAll(_comproList);
            final StringBuilder querySaldoSBuilder = new StringBuilder()
                    .append("SELECT fecha_saldo,saldo_pesos,saldo_dolar ")
                    .append("FROM web_cuenta_saldo ")
                    .append("where cuenta_cliente_id = ")
                    .append(cuenta.getId());
            final SaldoType _saldo = new SaldoType();
            final ResulsetObjectBuilder resSaldo = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rsetSaldo)
                        throws SQLException {
                    try {
                        _saldo.setFecha(DateUtil.Converters
                                .DateToFechaTimeType(DateUtil.Parsers
                                        .yyyyMMdd("2012-11-02")));
                    } catch (ParseException e1) {
                        throw new SQLException(
                                "No se puede determinar la fecha en el query.",
                                e1);
                    }
                    _saldo.setPesos(0);
                    _saldo.setDolares(0);
                    while (rsetSaldo.next()) {
                        try {
                            _saldo.setFecha(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers
                                            .yyyyMMdd(rsetSaldo
                                                    .getString("fecha_saldo"))));
                            _saldo.setPesos(rsetSaldo.getDouble("saldo_pesos"));
                            _saldo.setDolares(rsetSaldo
                                    .getDouble("saldo_dolar"));
                        } catch (ParseException e) {
                            throw new SQLException(
                                    "No se puede determinar la fecha en el query.",
                                    e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySaldoSBuilder.toString(),
                    resSaldo);
            final StringBuilder querySaldoSaldoAFechaSBuilder = new StringBuilder()
                    .append("SELECT SUM(sasa.saldopesos) saldopesos, ")
                    .append("SUM(sasa.saldodolar) saldodolar ")
                    .append("FROM ")
                    .append("(SELECT SUM(DECODE( f.monedafactura_id, '1717', f.importeSaldo, 0)*decode (f.comprador_id,")
                    .append(cuenta.getId())
                    .append(",1,decode(f.cuentacliente_id,")
                    .append(cuenta.getId())
                    .append(",-1,-1))) saldopesos, ")
                    .append("SUM(DECODE( f.monedafactura_id, '1718', f.importeSaldo, 0)*decode (f.comprador_id,")
                    .append(cuenta.getId())
                    .append(",decode(f.cuentacliente_id,")
                    .append(cuenta.getId())
                    .append(",-1,-1))) saldodolar ")
                    .append("FROM ZENIAPP.factura_producto f ")
                    .append("LEFT OUTER JOIN contrato c ")
                    .append("ON (c.id =f.contrato_id ")
                    .append("AND c.fechabaja IS NULL) ")
                    .append("INNER JOIN tipo_comprobante tc ")
                    .append("ON (tc.id =f.tipocomprobante_id ) ")
                    .append("WHERE f.fechabaja IS NULL ")
                    .append("AND TRUNC(f.fechafactura) >= to_date('2012-11-02','yyyy-MM-dd') ")
                    .append((filtroDeMoneda != null
                            && filtroDeMoneda.getId() != null && !filtroDeMoneda
                            .getId().equals(ZeniContextServer.VACIO)) ? " AND f.monedafactura_id = "
                            + filtroDeMoneda.getId() + " "
                            : ZeniContextServer.VACIO)
                    .append("AND TRUNC(f.fechafactura) < to_date('")
                    .append(DateUtil.ToString.yyyyMMdd(rangoFechas.getDesde()))
                    .append("','yyyy-MM-dd') ")
                    .append("AND f.estadocobranza = 'P' ")
                    .append("AND (f.vendedor_id = ")
                    .append(cuenta.getId())
                    .append(" ")
                    .append("OR f.comprador_id = ")
                    .append(cuenta.getId())
                    .append(" ")
                    .append("OR f.cuentacliente_id = ")
                    .append(cuenta.getId())
                    .append(") ")
                    .append("UNION ")
                    .append("SELECT SUM(DECODE( m.moneda_id, '1717', m.importe, 0)) saldopesos, ")
                    .append("SUM(DECODE( m.moneda_id, '1718', m.importe, 0)) saldodolar ")
                    .append("FROM ctacte_mov m ")
                    .append("LEFT OUTER JOIN contrato c1 ")
                    .append("ON (c1.id =m.contrato_id ")
                    .append("AND c1.fechabaja IS NULL) ")
                    .append("INNER JOIN tipo_comprobante tc ")
                    .append("ON (tc.id=m.tipocomprobante_id ) ")
                    .append("INNER JOIN ctacte_cuenta c ")
                    .append("ON m.cuenta_id =c.id ")
                    .append("WHERE TRUNC(m.fecha) >= to_date('2012-11-02','yyyy-MM-dd') ")
                    .append((filtroDeMoneda != null
                            && filtroDeMoneda.getId() != null && !filtroDeMoneda
                            .getId().equals(ZeniContextServer.VACIO)) ? " AND c.moneda_id = "
                            + filtroDeMoneda.getId() + " "
                            : ZeniContextServer.VACIO)
                    .append("AND TRUNC(m.fecha) < to_date('")
                    .append(DateUtil.ToString.yyyyMMdd(rangoFechas.getDesde()))
                    .append("','yyyy-MM-dd') ")
                    .append("AND c.cuentacliente_id=").append(cuenta.getId())
                    .append(" ").append(") sasa  ");
            final SaldoType _saldoAFecha = new SaldoType();
            final ResulsetObjectBuilder resSaldoAFecha = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rsetSaldoAFecha)
                        throws SQLException {
                    _saldoAFecha.setPesos(0);
                    _saldoAFecha.setDolares(0);
                    while (rsetSaldoAFecha.next()) {
                        // try {
                        // _saldoAFecha.setFecha(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rsetSaldoAFecha.getString("fechavalor"))));
                        _saldoAFecha.setPesos(rsetSaldoAFecha
                                .getDouble("saldopesos"));
                        _saldoAFecha.setDolares(rsetSaldoAFecha
                                .getDouble("saldodolar"));
                        // } catch (ParseException e) {
                        // throw new
                        // SQLException("No se puede determinar la fecha en el query.",
                        // e);
                        // }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(
                    querySaldoSaldoAFechaSBuilder.toString(), resSaldoAFecha);
            ObtenerCuentaCorrienteContableType _return = new ObtenerCuentaCorrienteContableType();
            _return.setComprobantes(_comproArrayCC);
            _return.setSaldo(_saldo);
            _return.setSaldoAFecha(_saldoAFecha);
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

    /*
	 * (non-Javadoc)
	 *
	 * @see ZeniWSDL#obtenerEstadoDeContratos(AuthType auth ,)CuentaType cuenta
	 * ,)FechaTimeType fechaTope ,)java.lang.String actividadId )*
     */
    // # 1535
    @Override
    public ArrayOfEstadosDeContratoType obtenerEstadoDeContratos(AuthType auth,
            ArrayOfIdCuenta cuentas, FechaTimeType fechaTope,
            EnumCompradorVendedorType tipoCompradorVendedor, String actividadId)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog(
                "Executing operation obtenerEstadoDeContratos");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuentas);
        ZeniContextServer.getInstance().printVerboLog(fechaTope);
        ZeniContextServer.getInstance().printVerboLog(actividadId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc
                    .get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (fechaTope == null
                    || DateUtil.ToString.ddMMyyyy(fechaTope) == null) {
                throw new ZeniServerExeption(
                        "La fecha fechaTope no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            // #1535
            // if (cuenta == null || cuenta.getId() == null ||
            // cuenta.getId().equals(ZeniContextServer.VACIO)) {
            // throw new
            // ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
            // FAULTCONSTANTS.ERROR_ERRONEO);
            // }
            final ArrayOfEstadosDeContratoType _return = new ArrayOfEstadosDeContratoType();
            final List<EstadoDeContratoType> _returnEstados = new ArrayList<EstadoDeContratoType>();
            final HashMap<String, MonedaType> monedaMap = innerObtenerMoneda();

            for (String cuenta : cuentas.getCuentas()) {

                final String filtroActividadComprador = actividadId != null
                        && !actividadId.equals(ZeniContextServer.VACIO) ? " AND c.actividadvendedor_id = "
                        + actividadId + " "
                        : ZeniContextServer.VACIO;
                final String filtroActividadVendedor = actividadId != null
                        && !actividadId.equals(ZeniContextServer.VACIO) ? " AND c.actividadcomprador_id = "
                        + actividadId + " "
                        : ZeniContextServer.VACIO;
                // # 1535
                final String filtroCuenta = (tipoCompradorVendedor == null || tipoCompradorVendedor
                        .equals(EnumCompradorVendedorType.COMPRADOR)) ? " and (c.comprador_id = "
                        + cuenta + filtroActividadComprador + " ) "
                        : " and (c.vendedor_id = " + cuenta
                        + filtroActividadVendedor + " ) ";
                final StringBuilder querySBuilder = new StringBuilder()
                        .append("SELECT c.id contrato_id, ")
                        // #1535
                        .append(" cuenta_desc.id cuentaid,")
                        .append(" cuenta_desc.denominacioncuenta cuenta, ")
                        .append("c.numerocontrato contrato, ")
                        .append("p.nombre producto, ")
                        .append("DECODE(c.comprador_id, ")
                        .append(" cuenta_desc.id ")
                        .append(",actcomp.nombre,actvend.nombre) actividad, ")
                        .append("m.simbolo ")
                        .append("||' ' ")
                        .append("||trim(TO_CHAR(NVL(c.preciomercaderia,0),'999999.99')) precio, ")
                        .append("NVL(c.preciomercaderia,0) precio_verdad, ")
                        .append("m.id precio_moneda, ")
                        .append("c.fechaentregahasta vencimientoentrega, ")
                        .append("fija.fechahasta vencimientofijacion, ")
                        .append("c.cantidadpactada cantidad, ")
                        .append("c.cantidadpactada -NVL(ent.cantidadentregada,0) pendienteentrega, ")
                        .append("ent.cantidadentregada -NVL(fija.cantidad,0) pendientefijar, ")
                        .append("dest.nombre puestoen, ")
                        .append("ccc.denominacioncuenta comprador, esta.estadodestino ")
                        .append("FROM contrato c ")
                        .append("INNER JOIN producto p ")
                        .append("ON (p.id=c.producto_id) ")
                        .append("INNER JOIN cuenta_cliente ccc ")
                        .append("ON (ccc.id         =c.comprador_id ")
                        .append("AND ccc.fechabaja IS NULL) ")
                        .append("INNER JOIN actividad actvend ")
                        .append("ON (actvend.id=c.actividadvendedor_id) ")
                        .append("INNER JOIN actividad actcomp ")
                        .append("ON (actcomp.id=c.actividadcomprador_id) ")
                        // #1535
                        .append(" INNER JOIN cuenta_cliente cuenta_desc ")
                        .append(" ON (cuenta_desc.id = " + cuenta + ") ")
                        .append("INNER JOIN cuenta_cliente ccv ")
                        .append("ON (ccv.id         =c.vendedor_id ")
                        .append("AND ccv.fechabaja IS NULL) ")
                        .append("INNER JOIN ciudad PROC ")
                        .append("ON (proc.id=c.procedencia_id) ")
                        .append("INNER JOIN ciudad dest ")
                        .append("ON (dest.id=c.destino_id) ")
                        .append("INNER JOIN calidad cal ")
                        .append("ON (cal.id=c.calidad_id) ")
                        .append("INNER JOIN tipo_transporte tt ")
                        .append("ON (tt.id=c.tipotransporte_id) ")
                        .append("INNER JOIN moneda m ")
                        .append("ON (m.id=c.monedafacturacionmercaderia_id) ")
                        .append("LEFT OUTER JOIN ")
                        .append("(SELECT ep.contrato_id, ")
                        .append("SUM(ep.cantidad) cantidadentregada ")
                        .append("FROM entrega_partida ep ")
                        .append("WHERE ep.fechabaja IS NULL ")
                        .append("GROUP BY contrato_id ")
                        .append(") ent ")
                        .append("ON (ent.contrato_id=c.id) ")
                        .append("LEFT OUTER JOIN ")
                        .append("(SELECT fp.contrato_id, ")
                        .append("SUM(fp.cantidadfacturada) cantidadfacturada, ")
                        .append("SUM(NVL(fp.totaldebe,0)) debe, ")
                        .append("SUM(NVL(fp.totalhaber,0)) haber ")
                        .append("FROM factura_producto fp ")
                        .append("INNER JOIN tipo_comprobante tc ")
                        .append("ON (tc.id           =fp.tipocomprobante_id) ")
                        .append("where upper(fp.facturador) not like '%FINAL%' ")
                        .append("and tc.abreviatura not in ('FA','ND','NC','LF','BF') and (tc.abreviatura <> 'FC' or fp.descontarCantidadFacturada = 1) ")
                        .append("and fp.anulador_id is null ")
                        .append("and fp.anulada_id is null ")
                        .append("GROUP BY fp.contrato_id ")
                        .append(") cf ON (cf.contrato_id=c.id) ")
                        .append("LEFT OUTER JOIN ")
                        .append("(SELECT gf.contrato_id, ")
                        .append("SUM(f.cantidadafijar) cantidad, ")
                        .append("MAX(f.fechafijacion) fechahasta, ")
                        .append("MIN(f.fechafijacion) fechadesde ")
                        .append("FROM grupo_fijaciones gf ")
                        .append("INNER JOIN fijacion f ")
                        .append("ON (f.grupofijaciones_id = gf.id ")
                        .append("AND f.fechabaja         IS NULL) ")
                        .append("WHERE gf.fechabaja      IS NULL ")
                        .append("GROUP BY gf.contrato_id ")
                        .append(") fija ON (fija.contrato_id=c.id) ")
                        //EstadoDestino
                        .append("LEFT OUTER JOIN ( ")
                        .append("select wd.ESTADODESTINO AS ESTADODESTINO, cto.id AS contrato_id ")
                        .append("from contrato cto ")
                        .append("inner join boleto b ON b.CONTRATO_ID = cto.id ")
                        .append("inner join workflow_state ws ON b.STATE_ID = ws.id ")
                        .append("inner join CONTRATO_T_DOCUMENTO_NEGOCIO ctdn ON cto.id = ctdn.contrato_id ")
                        .append("inner join TIPO_DOCUMENTO_NEGOCIO tdn ON tdn.id = ctdn.TIPO_DOCUMENTO_NEGOCIO_ID ")
                        .append("inner join WORKFLOW_DEFINITION wd ON ws.WORKFLOWDEFINITION_ID = wd.ID ")
                        .append("where cto.FECHABAJA is null ")
                        .append("and b.FECHABAJA is null ")
                        .append("and tdn.id <> 3962 ) esta ON ")
                        .append("(esta.contrato_id = c.id) ")
                        .append("WHERE c.fechabaja           IS NULL ")
                        .append("AND c.cumplido               =0 ")
                        .append("AND c.fechaoperacion        <= to_date('")
                        .append(DateUtil.ToString.yyyyMMdd(fechaTope))
                        .append("','yyyy-mm-dd') ").append(filtroCuenta)
                        .append(" ");
                final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                    @Override
                    public void thisIsTheResulset(ResultSet rset)
                            throws SQLException {
                        while (rset.next()) {
                            try {
                                final EstadoDeContratoType _returnEstadosVal1 = new EstadoDeContratoType();
                                _returnEstadosVal1.setCuenta(rset
                                        .getString("cuenta"));
                                _returnEstadosVal1.setCuentaId("" + rset
                                        .getInt("cuentaid"));
                                _returnEstadosVal1.setContrato(rset
                                        .getString("contrato"));
                                _returnEstadosVal1.setContratId(rset
                                        .getString("contrato_id"));
                                _returnEstadosVal1.setContratoId(rset
                                        .getString("contrato_id"));
                                _returnEstadosVal1.setProductoDescripcion(rset
                                        .getString("producto"));
                                _returnEstadosVal1.setActividad(rset
                                        .getString("actividad"));
                                _returnEstadosVal1.setMoneda(monedaMap.get(rset
                                        .getString("precio_moneda")));
                                _returnEstadosVal1.setPrecio(rset
                                        .getDouble("precio_verdad"));
                                _returnEstadosVal1.setCantidad(rset
                                        .getFloat("cantidad"));
                                _returnEstadosVal1.setPendienteEntrega(rset
                                        .getFloat("pendienteentrega"));
                                _returnEstadosVal1.setPendienteFijacion(rset
                                        .getFloat("pendientefijar"));
                                _returnEstadosVal1
                                        .setVencimientoEntrega(DateUtil.Converters
                                                .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
                                                        .getString("vencimientoentrega"))));
                                _returnEstadosVal1
                                        .setVencimientoFijacion(DateUtil.Converters
                                                .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
                                                        .getString("vencimientofijacion"))));
                                // _returnEstadosVal1.setCondicionDePago(rset.getString("precio"));
                                _returnEstadosVal1.setCondicionDePago("");
                                _returnEstadosVal1.setPuestoEn(rset
                                        .getString("puestoen"));
                                _returnEstadosVal1.setComprador(rset
                                        .getString("comprador"));

                                String tmp = rset.getString("estadodestino");
                                _returnEstadosVal1.setEstadoDestino(tmp != null ? ("".equals(tmp) ? "Sin boleto" : tmp) : "Sin boleto");

                                _returnEstados.add(_returnEstadosVal1);
                            } catch (ParseException e) {
                                throw new SQLException(
                                        "No se puede determinar la fecha en el query.",
                                        e);
                            }
                        }
                    }
                };
                ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(),
                        resb);
            }
            _return.getEstados().addAll(_returnEstados);
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
}
