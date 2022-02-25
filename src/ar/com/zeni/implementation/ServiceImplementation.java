package ar.com.zeni.implementation;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.handler.MessageContext;

import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.FAULTCONSTANTS;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.ZeniHelper;
import ar.com.zeni.common.exceptions.ZeniAuthTypeInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.common.exceptions.ZeniHashInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniIPDifiereExeption;
import ar.com.zeni.common.exceptions.ZeniServerExeption;
import ar.com.zeni.db.ZeniInsertBuilder;
import ar.com.zeni.db.ZeniQueryBuilder;
import ar.com.zeni.db.ZeniQueryBuilder.Operator;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.db.ZeniUpdateBuilder;
import ar.com.zeni.security.AuthNHashUtil;
import ar.com.zeni.security.UsuarioAuthenticationUtil;
import ar.com.zeni.security.UsuarioAuthenticationUtil.CUENTASXUSERTABLE;
import ar.com.zeni.security.UsuarioAuthenticationUtil.UserData;
import ar.com.zeni.zeniwsdl.ActividadType;
import ar.com.zeni.zeniwsdl.AdministracionGarantiaType;
import ar.com.zeni.zeniwsdl.ArrayOfActividadesType;
import ar.com.zeni.zeniwsdl.ArrayOfAdministracionGarantiaType;
import ar.com.zeni.zeniwsdl.ArrayOfBoletosCartaOferta;
import ar.com.zeni.zeniwsdl.ArrayOfCalidadEntregas;
import ar.com.zeni.zeniwsdl.ArrayOfCalidadType;
import ar.com.zeni.zeniwsdl.ArrayOfCampaniaType;
import ar.com.zeni.zeniwsdl.ArrayOfCartaPorteReg;
import ar.com.zeni.zeniwsdl.ArrayOfCompradorType;
import ar.com.zeni.zeniwsdl.ArrayOfContraparteType;
import ar.com.zeni.zeniwsdl.ArrayOfContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfCuentaType;
import ar.com.zeni.zeniwsdl.ArrayOfDestinoMATType;
import ar.com.zeni.zeniwsdl.ArrayOfDetalleFacturaType;
import ar.com.zeni.zeniwsdl.ArrayOfEstadoMercadoATerminoType;
import ar.com.zeni.zeniwsdl.ArrayOfFacturaDetalleType;
import ar.com.zeni.zeniwsdl.ArrayOfFacturaRegistroType;
import ar.com.zeni.zeniwsdl.ArrayOfFacturaType;
import ar.com.zeni.zeniwsdl.ArrayOfGarantiasMercadoATerminoType;
import ar.com.zeni.zeniwsdl.ArrayOfId;
import ar.com.zeni.zeniwsdl.ArrayOfIdCuenta;
import ar.com.zeni.zeniwsdl.ArrayOfMercadoType;
import ar.com.zeni.zeniwsdl.ArrayOfMermasByEntregaType;
import ar.com.zeni.zeniwsdl.ArrayOfMonedaType;
import ar.com.zeni.zeniwsdl.ArrayOfOperacionMATType;
import ar.com.zeni.zeniwsdl.ArrayOfOperacionMercadoATerminoType;
import ar.com.zeni.zeniwsdl.ArrayOfOperatoriaType;
import ar.com.zeni.zeniwsdl.ArrayOfOperatoriasCobroMercaderiaType;
import ar.com.zeni.zeniwsdl.ArrayOfProductoMATType;
import ar.com.zeni.zeniwsdl.ArrayOfProductoMercadoATerminoType;
import ar.com.zeni.zeniwsdl.ArrayOfProductoType;
import ar.com.zeni.zeniwsdl.ArrayOfReferenciaType;
import ar.com.zeni.zeniwsdl.ArrayOfResultadosMercadoATerminoType;
import ar.com.zeni.zeniwsdl.ArrayOfResumenMATType;
import ar.com.zeni.zeniwsdl.ArrayOfSaldoDeClienteType;
import ar.com.zeni.zeniwsdl.ArrayOfSectorReclamos;
import ar.com.zeni.zeniwsdl.ArrayOfSolicitudInformacion;
import ar.com.zeni.zeniwsdl.ArrayOfString;
import ar.com.zeni.zeniwsdl.ArrayOfTipoComprobanteType;
import ar.com.zeni.zeniwsdl.ArrayOfVendedorType;
import ar.com.zeni.zeniwsdl.AuthType;
import ar.com.zeni.zeniwsdl.CalidadType;
import ar.com.zeni.zeniwsdl.ClienteType;
import ar.com.zeni.zeniwsdl.CompradorType;
import ar.com.zeni.zeniwsdl.ContraparteType;
import ar.com.zeni.zeniwsdl.CuentaDatosType;
import ar.com.zeni.zeniwsdl.CuentaType;
import ar.com.zeni.zeniwsdl.DestinoMATType;
import ar.com.zeni.zeniwsdl.DetalleFacturaType;
import ar.com.zeni.zeniwsdl.EnumBoletoCartaPorte;
import ar.com.zeni.zeniwsdl.EnumCompraVentaType;
import ar.com.zeni.zeniwsdl.EnumCompradorVendedorType;
import ar.com.zeni.zeniwsdl.EnumEstadoType;
import ar.com.zeni.zeniwsdl.EnumIncluirType;
import ar.com.zeni.zeniwsdl.EnumOrdenamientoContratosType;
import ar.com.zeni.zeniwsdl.EnumSucursalType;
import ar.com.zeni.zeniwsdl.EnumTipoOperacionMatType;
import ar.com.zeni.zeniwsdl.EnumTipoOperacionMercadoATerminoType;
import ar.com.zeni.zeniwsdl.EnumTipoOperacionType;
import ar.com.zeni.zeniwsdl.EstadoMercadoATerminoType;
import ar.com.zeni.zeniwsdl.FacturaType;
import ar.com.zeni.zeniwsdl.FaultType;
import ar.com.zeni.zeniwsdl.FaultType_Exception;
import ar.com.zeni.zeniwsdl.FechaTimeType;
import ar.com.zeni.zeniwsdl.FileType;
import ar.com.zeni.zeniwsdl.GarantiasMercadoATerminoType;
import ar.com.zeni.zeniwsdl.MercadoType;
import ar.com.zeni.zeniwsdl.MermaByEntregaType;
import ar.com.zeni.zeniwsdl.MonedaType;
import ar.com.zeni.zeniwsdl.OperacionMATType;
import ar.com.zeni.zeniwsdl.OperacionMercadoATerminoType;
import ar.com.zeni.zeniwsdl.OperatoriaType;
import ar.com.zeni.zeniwsdl.OperatoriasCobroMercaderiaType;
import ar.com.zeni.zeniwsdl.ProductoMATType;
import ar.com.zeni.zeniwsdl.ProductoMercadoATerminoType;
import ar.com.zeni.zeniwsdl.ProductoType;
import ar.com.zeni.zeniwsdl.RangoFechaType;
import ar.com.zeni.zeniwsdl.ReferenciaType;
import ar.com.zeni.zeniwsdl.ResultadosMercadoATerminoType;
import ar.com.zeni.zeniwsdl.ResumenCuentaCorrienteType;
import ar.com.zeni.zeniwsdl.ResumenMATType;
import ar.com.zeni.zeniwsdl.SINOType;
import ar.com.zeni.zeniwsdl.SaldoDeClienteType;
import ar.com.zeni.zeniwsdl.SolicitudInformacionReg;
import ar.com.zeni.zeniwsdl.TipoComprobanteType;
import ar.com.zeni.zeniwsdl.VendedorType;

/**
 * Implemento los metodos de ws la de seguridad ya esta hecha.
 *
 * @author rodrigo
 *
 */
public class ServiceImplementation extends ServiceImplMenuReportes {

    /*
	 * (non-Javadoc)
	 *
	 * @see ZeniWSDL#obtenerMoneda(AuthType auth ,)java.lang.String id )*
     */
    @Override
    public MonedaType obtenerMoneda(AuthType auth, String id) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerMoneda");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(id);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (id == null || id.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id de la moneda no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            return innerObtenerMoneda().get(id);
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
    public ArrayOfMonedaType obtenerMonedas(AuthType auth) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerMonedas");
        ZeniContextServer.getInstance().printVerboLog(auth);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            final ArrayOfMonedaType _return = new ArrayOfMonedaType();
            _return.getMonedas().addAll(innerObtenerMoneda().values());
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

    // #1510
    @Override
    public ArrayOfTipoComprobanteType obtenerTiposComprobante(AuthType auth) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerTiposComprobante");
        ZeniContextServer.getInstance().printVerboLog(auth);

        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            final ArrayOfTipoComprobanteType _return = new ArrayOfTipoComprobanteType();
            final List<TipoComprobanteType> _returnTipoComprobante = new ArrayList<TipoComprobanteType>();
            final StringBuilder querySBuilder = new StringBuilder().append(" select id,nombre,abreviatura ")
                    .append(" from tipo_comprobante").append(" where fechabaja is null order by nombre");
            ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        TipoComprobanteType _returnTipoComprobanteVal1 = new TipoComprobanteType();
                        _returnTipoComprobanteVal1.setID(rset.getInt("id"));
                        _returnTipoComprobanteVal1.setNOMBRE(rset.getString("nombre"));
                        _returnTipoComprobanteVal1.setABREVIATURA(rset.getString("ABREVIATURA"));
                        _returnTipoComprobante.add(_returnTipoComprobanteVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getTiposcomprobante().addAll(_returnTipoComprobante);
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
    public ArrayOfActividadesType obtenerActividades(AuthType auth, String idCliente) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerActividades");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(idCliente);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (idCliente == null || idCliente.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id del cliente no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final ArrayOfActividadesType _return = new ArrayOfActividadesType();
            final List<ActividadType> _returnActividades = new ArrayList<ActividadType>();
            final StringBuilder querySBuilder = new StringBuilder().append("select unique a.id,a.nombre from ")
                    .append(owner).append("actividad a inner join ").append(owner)
                    .append("cliente_actividad ca on (ca.actividad_id = a.id) ").append(" inner join ").append(owner)
                    .append("cuenta_cliente cc on ( cc.cliente_id = ca.cliente_id ) inner join ")
                    .append(CUENTASXUSERTABLE.TABLE_NAME).append(" df on ( df.")
                    .append(CUENTASXUSERTABLE.CUENTA_ID_FIELD).append(" = cc.id ").append(" ) where df.")
                    .append(CUENTASXUSERTABLE.CLIENTE_WEBID_FIELD).append(" = ").append(idCliente)
                    .append(" and a.fechabaja is null order by a.nombre");
            ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        ActividadType _returnActividadesVal1 = new ActividadType();
                        // _returnActividadesVal1.setId(rset.getInt("id")+ZeniContextServer.VACIO);
                        _returnActividadesVal1.setId(rset.getString("id"));
                        _returnActividadesVal1.setDescripcion(rset.getString("nombre"));
                        _returnActividades.add(_returnActividadesVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getActividades().addAll(_returnActividades);
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
    public ProductoType obtenerProducto(AuthType auth, String id) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerProducto");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(id);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (id == null || id.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id del producto no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final ProductoType _return = new ProductoType();
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select p.id,descripcion || ' ('|| p.codigo ||')' descripcion from ").append(owner)
                    .append("producto p where p.fechabaja is null and p.id = ").append(id).append(" order by nombre");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        // _return.setId(rset.getInt("id")+ZeniContextServer.VACIO);
                        _return.setId(rset.getString("id"));
                        _return.setDescripcion(rset.getString("descripcion"));
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
    public ArrayOfProductoType obtenerProductos(AuthType auth) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerProductos");
        ZeniContextServer.getInstance().printVerboLog(auth);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            final ArrayOfProductoType _return = new ArrayOfProductoType();
            final List<ProductoType> _returnProductos = new ArrayList<ProductoType>();
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select p.id,p.descripcion || ' ('|| p.codigo ||')' descripcion from ").append(owner)
                    .append("producto p where fechabaja is null order by p.descripcion || ' ('|| p.codigo ||')' ");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        ProductoType _returnProductosVal1 = new ProductoType();
                        // _returnProductosVal1.setId(rset.getInt("id") +
                        // ZeniContextServer.VACIO);
                        _returnProductosVal1.setId(rset.getString("id"));
                        _returnProductosVal1.setDescripcion(rset.getString("descripcion"));
                        _returnProductos.add(_returnProductosVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getProductos().addAll(_returnProductos);
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
    public ArrayOfCalidadType obtenerCalidades(AuthType auth, ArrayOfIdCuenta cuentas) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCalidades");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuentas);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select id, abreviatura || ' (' || codigo ||')' descripcion from ").append(owner)
                    .append("calidad where fechabaja is null order by abreviatura || ' (' || codigo ||')'");
            final ArrayOfCalidadType _return = new ArrayOfCalidadType();
            final List<CalidadType> _returnCalidades = new ArrayList<CalidadType>();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        final CalidadType _returnCalidadesVal1 = new CalidadType();
                        _returnCalidadesVal1.setId(rset.getString("id"));
                        _returnCalidadesVal1.setDescripcion(rset.getString("descripcion"));
                        _returnCalidades.add(_returnCalidadesVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getCalidades().addAll(_returnCalidades);
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
    public ArrayOfCompradorType obtenerCompradores(AuthType auth, ArrayOfIdCuenta cuentas) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCompradores");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuentas);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            String cuentas_cuentas = ZeniContextServer.VACIO;
            if (cuentas != null && cuentas.getCuentas() != null && cuentas.getCuentas().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < cuentas.getCuentas().size(); i++) {
                    sb.append(",").append(cuentas.getCuentas().get(i)).append("");
                }
                cuentas_cuentas = sb.deleteCharAt(0).insert(0, "(").append(") ").toString();
            }
            if (cuentas == null) {
                throw new ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select cc.id comprador_id, cc.denominacioncuenta||' ('||cc.nrocuenta||')' comprador from ")
                    .append(owner).append("contrato c inner join ").append(owner)
                    .append("cuenta_cliente cc on (cc.id=c.comprador_id) where c.fechabaja is null and c.vendedor_id in ")
                    .append(cuentas_cuentas).append(" group by cc.id, cc.denominacioncuenta||' ('||cc.nrocuenta||')'")
                    .append(" order by cc.denominacioncuenta||' ('||cc.nrocuenta||')'");
            final ArrayOfCompradorType _return = new ArrayOfCompradorType();
            final List<CompradorType> _returnCompradores = new ArrayList<CompradorType>();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        final CompradorType _returnCompradoresVal1 = new CompradorType();
                        // _returnCompradoresVal1.setId(rset.getInt("comprador_id")+ZeniContextServer.VACIO);
                        _returnCompradoresVal1.setId(rset.getString("comprador_id"));
                        _returnCompradoresVal1.setDescripcion(rset.getString("comprador"));
                        _returnCompradores.add(_returnCompradoresVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getCompradores().addAll(_returnCompradores);
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
    public ArrayOfVendedorType obtenerVendedores(AuthType auth, ArrayOfIdCuenta cuentas) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerVendedores");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuentas);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (cuentas == null) {
                throw new ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            String cuentas_cuentas = ZeniContextServer.VACIO;
            if (cuentas != null && cuentas.getCuentas() != null && cuentas.getCuentas().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < cuentas.getCuentas().size(); i++) {
                    sb.append(",").append(cuentas.getCuentas().get(i)).append("");
                }
                cuentas_cuentas = sb.deleteCharAt(0).insert(0, "(").append(") ").toString();
            }
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select cc.id vendedor_id, cc.denominacioncuenta||' ('||cc.nrocuenta||')' vendedor from ")
                    .append(owner).append("contrato c inner join ").append(owner)
                    .append("cuenta_cliente cc on (cc.id=c.vendedor_id) where c.fechabaja is null and c.comprador_id in ")
                    .append(cuentas_cuentas).append(" group by cc.id, cc.denominacioncuenta||' ('||cc.nrocuenta||')'")
                    .append(" order by cc.denominacioncuenta||' ('||cc.nrocuenta||')'");
            final ArrayOfVendedorType _return = new ArrayOfVendedorType();
            final List<VendedorType> _returnVendedores = new ArrayList<VendedorType>();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        final VendedorType _returnVendedoresVal1 = new VendedorType();
                        // _returnVendedoresVal1.setId(rset.getInt("vendedor_id")+ZeniContextServer.VACIO);
                        _returnVendedoresVal1.setId(rset.getString("vendedor_id"));
                        _returnVendedoresVal1.setDescripcion(rset.getString("vendedor"));
                        _returnVendedores.add(_returnVendedoresVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getVendedores().addAll(_returnVendedores);
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
    public ClienteType obtenerCliente(AuthType auth) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCliente");
        ZeniContextServer.getInstance().printVerboLog(auth);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            final UserData u = new UsuarioAuthenticationUtil()
                    .getUserData(AuthNHashUtil.getInstance().getHashNSeed(auth.getHash()).getUser());
            // clientes
            final ClienteType _return = new ClienteType();
            _return.setCUIT("30-30-30");
            _return.setId(u.getClienteid());
            _return.setRazonSocial(u.getNombre());
            final ArrayOfCuentaType _returnCuentas = new ArrayOfCuentaType();
            final List<CuentaType> _returnCuentasCuentas = new ArrayList<CuentaType>();
            final StringBuilder querySBuilderCuenta = new StringBuilder().append("SELECT cc.id id_cuenta_cliente, ")
                    .append("cc.denominacioncuenta descripcion, ")
                    .append("cc.nrocuenta, ")
                    .append("(select cli.cuit from cliente cli where cli.id = cc.CLIENTE_ID) cuit, ")
                    .append("cc.denominacioncuenta ").append("FROM cuenta_cliente cc ")
                    .append("WHERE cc.fechabaja IS NULL ").append("AND cc.id          IN ").append("(SELECT df.")
                    .append(CUENTASXUSERTABLE.CUENTA_ID_FIELD).append(" FROM ").append(CUENTASXUSERTABLE.TABLE_NAME)
                    .append(" df ").append("WHERE df.").append(CUENTASXUSERTABLE.CLIENTE_WEBID_FIELD).append(" = ")
                    .append(u.getClienteid()).append(") ").append("AND cc.nrocuenta < 50000 ")
                    .append("ORDER BY cc.denominacioncuenta ");
            final ResulsetObjectBuilder resbCuenta = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        final CuentaType _returnCuentasCuentasVal1 = new CuentaType();
                        _returnCuentasCuentasVal1.setId(rset.getString("id_cuenta_cliente"));
                        _returnCuentasCuentasVal1.setDescripcion(rset.getString("descripcion"));
                        _returnCuentasCuentasVal1.setNumeroDeCuenta(rset.getString("nrocuenta"));
                        _returnCuentasCuentasVal1.setDenominacionCuenta(rset.getString("denominacioncuenta"));
                        _returnCuentasCuentasVal1.setCuit(rset.getString("cuit"));
                        _returnCuentasCuentas.add(_returnCuentasCuentasVal1);
                    }
                }
            };
            ZeniContextServer.getInstance().printVerboLog(querySBuilderCuenta.toString());
            ZeniQueryExcecutor.excecuteSelect(querySBuilderCuenta.toString(), resbCuenta);
            _returnCuentas.getCuentas().addAll(_returnCuentasCuentas);
            final ArrayOfCuentaType _returnCuentasOF = new ArrayOfCuentaType();
            final List<CuentaType> _returnCuentasCuentasOF = new ArrayList<CuentaType>();
            final StringBuilder querySBuilderCuentaOF = new StringBuilder().append("SELECT cc.id id_cuenta_cliente, ")
                    .append("cc.denominacioncuenta descripcion, ")
                    .append("cc.nrocuenta, ")
                    .append("(select cli.cuit from cliente cli where cli.id = cc.CLIENTE_ID) cuit, ")
                    .append("cc.denominacioncuenta ").append("FROM cuenta_cliente cc ")
                    .append("WHERE cc.fechabaja IS NULL ").append("AND cc.id          IN ").append("(SELECT df.")
                    .append(CUENTASXUSERTABLE.CUENTA_ID_FIELD).append(" FROM ").append(CUENTASXUSERTABLE.TABLE_NAME)
                    .append(" df ").append("WHERE df.").append(CUENTASXUSERTABLE.CLIENTE_WEBID_FIELD).append(" = ")
                    .append(u.getClienteid()).append(") ").append("AND cc.nrocuenta >= 50000 ")
                    .append("ORDER BY cc.denominacioncuenta ");
            final ResulsetObjectBuilder resbCuentaOF = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        final CuentaType _returnCuentasCuentasValOF = new CuentaType();
                        _returnCuentasCuentasValOF.setId(rset.getString("id_cuenta_cliente"));
                        _returnCuentasCuentasValOF.setDescripcion(rset.getString("descripcion"));
                        _returnCuentasCuentasValOF.setNumeroDeCuenta(rset.getString("nrocuenta"));
                        _returnCuentasCuentasValOF.setDenominacionCuenta(rset.getString("denominacioncuenta"));
                        _returnCuentasCuentasValOF.setCuit(rset.getString("cuit"));
                        _returnCuentasCuentasOF.add(_returnCuentasCuentasValOF);
                    }
                }
            };
            ZeniContextServer.getInstance().printVerboLog(querySBuilderCuentaOF.toString());
            ZeniQueryExcecutor.excecuteSelect(querySBuilderCuentaOF.toString(), resbCuentaOF);
            _returnCuentasOF.getCuentas().addAll(_returnCuentasCuentasOF);
            _return.setCuentas(_returnCuentas);
            _return.setCuentasOF(_returnCuentasOF);
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
    public ArrayOfContraparteType obtenerContrapartesByCuenta(AuthType auth, CuentaType cuenta)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerContrapartesByCuenta");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuenta);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (cuenta == null || cuenta.getId() == null || cuenta.getId().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select cc.denominacioncuenta||' ('||cc.nrocuenta||')' contraparte, cc.id contraperte_id from ")
                    .append(owner).append("contrato c, ").append(owner)
                    .append("cuenta_cliente cc where c.fechabaja is null and cc.fechabaja is null and ( c.comprador_id=")
                    .append(cuenta.getId()).append(" and cc.id=c.vendedor_id or c.vendedor_id=").append(cuenta.getId())
                    .append(" and cc.id=c.comprador_id) GROUP BY cc.denominacioncuenta||' ('||cc.nrocuenta||')', cc.id order by cc.denominacioncuenta||' ('||cc.nrocuenta||')'");
            final ArrayOfContraparteType _return = new ArrayOfContraparteType();
            final List<ContraparteType> _returnContrapartes = new ArrayList<ContraparteType>();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        final ContraparteType _returnContrapartesVal1 = new ContraparteType();
                        // _returnContrapartesVal1.setId(rset.getInt("contraperte_id")+ZeniContextServer.VACIO);
                        _returnContrapartesVal1.setId(rset.getString("contraperte_id"));
                        _returnContrapartesVal1.setDescripcion(rset.getString("contraparte"));
                        _returnContrapartes.add(_returnContrapartesVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getContrapartes().addAll(_returnContrapartes);
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
    public CuentaDatosType obtenerDatosDeCuenta(final AuthType auth, CuentaType cuenta) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerDatosDeCuenta");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuenta);
        try {
            final CuentaDatosType _return = new CuentaDatosType();
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (cuenta == null || cuenta.getId() == null || cuenta.getId().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select ca.id cuenta_id, ca.tipocuenta, ca.moneda_id, ca.saldo, ca.saldoanterior, ca.saldous, to_char(ca.fechacreacion, 'yyyy-MM-dd') fechacreacion, to_char(ca.fechasaldoanterior, 'yyyy-MM-dd') fechasaldoanterior from ")
                    .append(owner).append("ctacte_cuenta ca where ca.cuentacliente_id=").append(cuenta.getId());
            final HashMap<String, MonedaType> monedaMap = innerObtenerMoneda();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            _return.setIdCuenta(rset.getString("cuenta_id"));
                            _return.setTipoCuenta(rset.getString("tipocuenta"));
                            _return.setSaldo(rset.getFloat("saldo"));
                            _return.setSaldoAnterior(rset.getFloat("saldoanterior"));
                            _return.setSaldoUS(rset.getFloat("saldous"));
                            _return.setMoneda(monedaMap.get(rset.getString("moneda_id")));
                            _return.setFechaCreacion(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechacreacion"))));
                            _return.setFechaSaldoAnterior(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.yyyyMMdd(rset.getString("fechasaldoanterior"))));
                        } catch (ParseException ex) {
                            throw new SQLException("No se puede determinar la fecha en el query.", ex);
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

    public ArrayOfCampaniaType obtenerCampanias(AuthType auth, ArrayOfIdCuenta cuentas) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCampanias");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuentas);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (cuentas == null) {
                throw new ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            String cuentas_cuentas = ZeniContextServer.VACIO;
            if (cuentas != null && cuentas.getCuentas() != null && cuentas.getCuentas().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < cuentas.getCuentas().size(); i++) {
                    sb.append(",").append(cuentas.getCuentas().get(i)).append("");
                }
                cuentas_cuentas = sb.deleteCharAt(0).insert(0, "(").append(") ").toString();
            }
            final StringBuilder querySBuilder = new StringBuilder().append("select unique c.aniocosecha campania from ")
                    .append(owner).append("contrato c where c.fechabaja is null and ( c.vendedor_id in ")
                    .append(cuentas_cuentas).append(" or c.comprador_id in ").append(cuentas_cuentas)
                    .append(" ) and c.aniocosecha is not null order by c.aniocosecha desc ");
            final ArrayOfCampaniaType _return = new ArrayOfCampaniaType();
            final List<java.lang.String> _returnCampanias = new ArrayList<java.lang.String>();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        _returnCampanias.add(rset.getString("campania"));
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getCampanias().addAll(_returnCampanias);
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
    public java.lang.String marcarComprobanteDescargado(AuthType auth, String comprobanteId, SINOType descargado)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation marcarComprobanteDescargado");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(comprobanteId);
        ZeniContextServer.getInstance().printVerboLog(descargado);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (comprobanteId == null || comprobanteId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id del comprobante no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            // si esta update, sino insert!
            final String sexiste = new ZeniQueryBuilder().select("DESCARGADO")
                    .from(ZeniContextServer.getInstance().getDBWebOwner() + "WEB_COMPROBANTE_DESC")
                    .where("ID_COMPRO", Operator.EQUALS, comprobanteId).getQuey();
            final int[] bexiste = new int[1];
            bexiste[0] = 0;
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        bexiste[0] = 5;
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(sexiste, resb);
            if (bexiste[0] > 0) {
                ZeniQueryExcecutor.excecuteUpdate(new ZeniUpdateBuilder()
                        .update(ZeniContextServer.getInstance().getDBWebOwner() + "WEB_COMPROBANTE_DESC")
                        .set("DESCARGADO", descargado.equals(SINOType.SI) ? "S" : "N")
                        .where("ID_COMPRO", Operator.EQUALS, comprobanteId).getupdateQuery());
            } else {
                ZeniQueryExcecutor.excecuteUpdate(new ZeniInsertBuilder()
                        .insertinsertinto(ZeniContextServer.getInstance().getDBWebOwner() + "WEB_COMPROBANTE_DESC")
                        .fields("ID_COMPRO", "DESCARGADO")
                        .values(comprobanteId, descargado.equals(SINOType.SI) ? "S" : "N").getInsertQuery());
            }
            return "El comprobante " + comprobanteId + " fue marcado como " + descargado + " descargado";
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
    public ArrayOfOperatoriaType obtenerOperatorias(AuthType auth, ArrayOfIdCuenta cuentas) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerOperatorias");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuentas);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (cuentas == null) {
                throw new ZeniServerExeption("El id del cliente no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            ArrayOfOperatoriaType _return = new ArrayOfOperatoriaType();
            final List<OperatoriaType> _returnOperatorias = new ArrayList<OperatoriaType>();
            final String query = new ZeniQueryBuilder().select("ID", "DESCRIPCION")
                    .from(ZeniContextServer.getInstance().getDBWebOwner() + "NOESTA_OPERATORIA").getQuey();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        OperatoriaType _returnOperatoriasVal1 = new OperatoriaType();
                        _returnOperatoriasVal1.setId(rset.getString("ID"));
                        _returnOperatoriasVal1.setDescripcion(rset.getString("DESCRIPCION"));
                        _returnOperatorias.add(_returnOperatoriasVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(query, resb);
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

    @Override
    public ArrayOfOperatoriasCobroMercaderiaType obtenerOperatoriasCobroMercaderia(AuthType auth,
            java.lang.String idCliente) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerOperatoriasCobroMercaderia");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(idCliente);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            ArrayOfOperatoriasCobroMercaderiaType _return = new ArrayOfOperatoriasCobroMercaderiaType();
            final List<OperatoriasCobroMercaderiaType> _returnOperatorias = new ArrayList<OperatoriasCobroMercaderiaType>();
            final String query = new ZeniQueryBuilder().select("ID", "DESCRIPCION")
                    .from(ZeniContextServer.getInstance().getDBWebOwner() + "NOESTA_OPERATORIACBRMER").getQuey();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        OperatoriasCobroMercaderiaType _returnOperatoriasVal1 = new OperatoriasCobroMercaderiaType();
                        _returnOperatoriasVal1.setId(rset.getString("ID"));
                        _returnOperatoriasVal1.setDescripcion(rset.getString("DESCRIPCION"));
                        _returnOperatorias.add(_returnOperatoriasVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(query, resb);
            _return.getOperatoriascobro().addAll(_returnOperatorias);
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

    public FileType descargarComprobante(AuthType auth, String idCuenta, String comprobanteId, String tipo,
            SINOType esAnexo) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation descargarComprobante");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(idCuenta);
        ZeniContextServer.getInstance().printVerboLog(comprobanteId);
        ZeniContextServer.getInstance().printVerboLog(tipo);
        ZeniContextServer.getInstance().printVerboLog(esAnexo);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (idCuenta == null || idCuenta.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (comprobanteId == null || comprobanteId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id del comprobante no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (tipo == null || tipo.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El tipo de comprobante no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final URL fileremote = new URL(
                    ZeniContextServer.getInstance().getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_URL_BASE)
                    + ZeniContextServer.getInstance()
                            .getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_PARAM_1)
                    + tipo
                    + ZeniContextServer.getInstance()
                            .getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_PARAM_2)
                    + comprobanteId
                    + ((esAnexo != null && esAnexo.equals(SINOType.SI))
                    ? (ZeniContextServer.getInstance()
                            .getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_PARAM_3) + "true")
                    : ZeniContextServer.VACIO));
            ZeniContextServer.getInstance().printVerboLog(fileremote);
            // final URL fileremote = new
            // URL("http://10.2.0.201:8081/zeni/obtenerComprobante.html?tipoComprobante=FA&idComprobante=8561442");
            URLConnection connection = fileremote.openConnection();
            // BufferedInputStream bifin = new
            // BufferedInputStream(fileremote.openStream());
            connection.setConnectTimeout(15 * 1000);
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
            // Map<String, List<String>> pepea = connection.getHeaderFields();
            // Set<Entry<String, List<String>>> lete = pepea.entrySet();
            // Iterator<Entry<String, List<String>>> it = lete.iterator();
            // while (it.hasNext()) {
            // Entry<String, List<String>> sale = it.next();
            // System.out.println(sale.getKey());
            // for (int i = 0; i<sale.getValue().size(); i++) {
            // System.out.println(" " + i + " : " + sale.getValue().get(i));
            // }
            // }
            // final String casiName =
            // connection.getHeaderField("Content-disposition");
            final String casiName = "Content-disposition=comprobante-" + comprobanteId
                    + ((esAnexo != null && esAnexo.equals(SINOType.SI)) ? "Anexo" : ZeniContextServer.VACIO) + ".pdf";
            final String name = casiName != null
                    ? casiName.substring(casiName.indexOf('=') != -1 ? casiName.indexOf('=') + 1 : 0)
                    : "comprobante-" + comprobanteId
                    + ((esAnexo != null && esAnexo.equals(SINOType.SI)) ? "Anexo" : ZeniContextServer.VACIO)
                    + ".pdf";
            final String nameAlone = name.substring(0, name.indexOf('.') != -1 ? name.indexOf('.') : name.length());
            final String extAlone = name.indexOf('.') != -1 ? name.substring(name.indexOf('.') + 1)
                    : ZeniContextServer.VACIO;
            _returnData = bais.toByteArray();
            bais.close();
            marcarComprobanteDescargado(auth, comprobanteId, SINOType.SI);
            final FileType _return = new FileType();
            _return.setData(_returnData);
            _return.setNombreConExtension(name);
            _return.setNombre(nameAlone);
            _return.setExtension(extAlone);
            _return.setTamanio(_returnData.length);
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

    @Override
    public ResumenCuentaCorrienteType obtenerResumenDeCuentaCorriente(AuthType auth, CuentaType cuenta)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerResumenDeCuentaCorriente");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuenta);
        try {
            if (cuenta == null || cuenta.getId() == null || cuenta.getId().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            final ResumenCuentaCorrienteType _return = new ResumenCuentaCorrienteType();
            final CuentaDatosType ds = obtenerDatosDeCuenta(auth, cuenta);
            _return.setCuenta(cuenta);
            _return.setSaldo(ds.getSaldo());
            _return.setSaldoAnterior(ds.getSaldoAnterior());
            _return.setSaldouss(ds.getSaldoUS());
            return _return;
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
	 * @see ZeniWSDL#obtenerSaldoDeCliente(AuthType auth ,)java.lang.String clienteId )*
     */
    @Override
    public ArrayOfSaldoDeClienteType obtenerSaldoDeCliente(AuthType auth, java.lang.String clienteId)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerSaldoDeCliente");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(clienteId);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            if (clienteId == null || clienteId.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id del cliente no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            final ArrayOfSaldoDeClienteType _return = new ArrayOfSaldoDeClienteType();
            final List<SaldoDeClienteType> _returnSaldos = new ArrayList<SaldoDeClienteType>();
            final StringBuilder querySBuilder = new StringBuilder();

            querySBuilder.append(" SELECT cc.id as id, ");
            querySBuilder.append(" suc.nombre as sucursal, ");
            querySBuilder.append(" suc.orden as sucursalOrden, ");
            querySBuilder.append("opm.legajo as operadorId, ");
            querySBuilder.append("opm.nombre as operador, ");
            querySBuilder.append("cc.nroCuenta as nroCuenta, ");
            querySBuilder.append("cc.denominacionCuenta as razonSocial, ");
            querySBuilder.append("max(mov.fecha) as fechaUltimo, ");
            querySBuilder.append("sum(CASE WHEN mon.simbolo = '$' AND mov.importe < 0 THEN mov.importe ELSE 0 END) as debePesos, ");
            querySBuilder.append("sum(CASE WHEN mon.simbolo = '$' AND mov.importe >=0 THEN mov.importe ELSE 0 END) as haberPesos, ");
            querySBuilder.append("sum(CASE WHEN mon.simbolo = '$' THEN mov.importe ELSE 0 END) as saldoPesos, ");
            querySBuilder.append("sum(CASE WHEN mon.simbolo = 'US' AND mov.importe < 0 THEN mov.importe ELSE 0 END) as debeUS, ");
            querySBuilder.append("sum(CASE WHEN mon.simbolo = 'US' AND mov.importe >=0 THEN mov.importe ELSE 0 END) as haberUS, ");
            querySBuilder.append("sum(CASE WHEN mon.simbolo = 'US' THEN mov.importe ELSE 0 END) as saldoUS ");
            querySBuilder.append("FROM ctacte_mov mov ");
            querySBuilder.append("inner join CTACTE_CUENTA ccm ON ccm.id = mov.CUENTA_ID ");
            querySBuilder.append("INNER JOIN cuenta_Cliente cc ON cc.id = ccm.CUENTACLIENTE_ID ");
            querySBuilder.append("inner join moneda mon ON mon.id = mov.MONEDA_ID ");
            querySBuilder.append("inner join SUCURSAL suc ON suc.id = cc.SUCURSAL_ID ");
            querySBuilder.append("inner join OPERADOR_DE_MESA opm ON opm.id = cc.OPERADORDEMESA_ID ");
            querySBuilder.append("inner join cliente  cl ON cc.CLIENTE_ID = cl.ID ");
            querySBuilder.append("WHERE cl.id in ( SELECT cliente_id FROM CUENTA_CLIENTE WHERE id = " + clienteId + ") ");
            querySBuilder.append("GROUP BY cc.id, suc.nombre, suc.orden,opm.legajo,opm.nombre, cc.nroCuenta, cc.denominacionCuenta ");
            querySBuilder.append("HAVING sum(CASE WHEN mon.simbolo = '$'  THEN mov.importe ELSE 0 END) < -1 OR ");
            querySBuilder.append("  sum(CASE WHEN mon.simbolo = '$'  THEN mov.importe ELSE 0 END) >  1 OR ");
            querySBuilder.append("sum(CASE WHEN mon.simbolo = 'US' THEN mov.importe ELSE 0 END) < -1 OR ");
            querySBuilder.append("sum(CASE WHEN mon.simbolo = 'US' THEN mov.importe ELSE 0 END) >  1 ");
            querySBuilder.append("ORDER BY suc.nombre, cc.denominacionCuenta ");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        SaldoDeClienteType _returnSaldosVal1 = new SaldoDeClienteType();
                        _returnSaldosVal1.setCuenta(rset.getString("nroCuenta"));
                        _returnSaldosVal1.setRazonSocial(rset.getString("razonSocial"));

                        if (rset.getDouble("saldoPesos") > 0) {
                            _returnSaldosVal1.setAcreedorPesos(rset.getDouble("saldoPesos"));
                            _returnSaldosVal1.setDeudorPesos(0);
                        } else {
                            _returnSaldosVal1.setAcreedorPesos(0);
                            _returnSaldosVal1.setDeudorPesos(rset.getDouble("saldoPesos"));
                        }

                        if (rset.getDouble("saldoUS") > 0) {
                            _returnSaldosVal1.setDeudorDolares(0);
                            _returnSaldosVal1.setAcreedorDolares(rset.getDouble("saldoUS"));
                        } else {
                            _returnSaldosVal1.setDeudorDolares(rset.getDouble("saldoUS"));
                            _returnSaldosVal1.setAcreedorDolares(0);
                        }

                        _returnSaldosVal1.setOperador(rset.getString("operador"));
                        _returnSaldosVal1.setSucursal(rset.getString("sucursal"));

                        _returnSaldos.add(_returnSaldosVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getSaldos().addAll(_returnSaldos);
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
    public ArrayOfMermasByEntregaType obtenerMermasByEntrega(AuthType auth, String idEntrega)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerMermasByEntrega");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(idEntrega);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (idEntrega == null || idEntrega.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El id del idEntrega no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            ArrayOfMermasByEntregaType _return = new ArrayOfMermasByEntregaType();
            final java.util.List<MermaByEntregaType> _returnMermas = new java.util.ArrayList<MermaByEntregaType>();
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select r.nombre, d.resultado from concepto_rubro_calidad r join detalle_analisis_muestra d on d.conceptorubrocalidad_id=r.id join analisis_muestra a on a.id=d.analisismuestra_id join entrega e on e.analisismuestra_id=a.id join rubro_calidad rc on rc.conceptorubrocalidad_id=r.id and rc.producto_id=a.producto_id where e.id= ")
                    .append(idEntrega).append("and rc.afectaa='C'");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        MermaByEntregaType _returnMermasVal1 = new MermaByEntregaType();
                        _returnMermasVal1.setNombre(rset.getString("nombre"));
                        _returnMermasVal1.setResultado(rset.getFloat("resultado"));
                        _returnMermas.add(_returnMermasVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getMermas().addAll(_returnMermas);
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
    public ArrayOfProductoMATType obtenerProductosMAT(AuthType auth) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerProductosMAT");
        ZeniContextServer.getInstance().printVerboLog(auth);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            ArrayOfProductoMATType _return = new ArrayOfProductoMATType();
            final java.util.List<ProductoMATType> _returnProductoMATs = new java.util.ArrayList<ProductoMATType>();
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select p.id,mp.id mat_id,p.descripcion nombre,mp.nombre descripcion,p.codigo, mp.mercado_id ")
                    .append("from producto p ").append("inner join mat_producto mp on (mp.producto_id=p.id) ")
                    .append("where p.fechabaja is null ").append("and mp.fechabaja is null AND mp.tipoProducto = 'G' ")
                    .append("order by mp.nombre ");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        ProductoMATType _returnProductoMAT = new ProductoMATType();
                        _returnProductoMAT.setId(rset.getString("id"));
                        _returnProductoMAT.setNombre(rset.getString("nombre"));
                        _returnProductoMAT.setMatId(rset.getString("mat_id"));
                        _returnProductoMAT.setDescripcion(rset.getString("descripcion"));
                        _returnProductoMAT.setCodigo(rset.getString("codigo"));
                        _returnProductoMAT.setMercado(rset.getString("mercado_id"));
                        _returnProductoMATs.add(_returnProductoMAT);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getProductos().addAll(_returnProductoMATs);
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
    public ArrayOfProductoMercadoATerminoType obtenerProductosMercadoATermino(AuthType auth)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerProductosMercadoATermino");
        ZeniContextServer.getInstance().printVerboLog(auth);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            ArrayOfProductoMercadoATerminoType _return = new ArrayOfProductoMercadoATerminoType();
            final java.util.List<ProductoMercadoATerminoType> _returnProductoMATs = new java.util.ArrayList<ProductoMercadoATerminoType>();
            final StringBuilder querySBuilder = new StringBuilder()
                    .append("select mp.id id,mp.nombre descripcion,mp.codigo,udm.nombre unidaddemedida ")
                    .append("from mat_producto mp ")
                    .append("inner join unidad_de_medida udm ON (udm.id=mp.unidaddemedida_id) ")
                    .append("where mp.fechabaja is null ").append("order by mp.nombre ");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        ProductoMercadoATerminoType _returnProductoMAT = new ProductoMercadoATerminoType();
                        _returnProductoMAT.setId(rset.getString("id"));
                        _returnProductoMAT.setDescripcion(rset.getString("descripcion"));
                        _returnProductoMAT.setCodigo(rset.getString("codigo"));
                        _returnProductoMAT.setUnidaddemedida(rset.getString("unidaddemedida"));
                        _returnProductoMATs.add(_returnProductoMAT);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getProductos().addAll(_returnProductoMATs);
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
    public ArrayOfEstadoMercadoATerminoType obtenerEstadosMercadoATermino(AuthType auth) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerEstadosMercadoATermino");
        ZeniContextServer.getInstance().printVerboLog(auth);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            ArrayOfEstadoMercadoATerminoType _return = new ArrayOfEstadoMercadoATerminoType();
            final java.util.List<EstadoMercadoATerminoType> _returnEstadoMATs = new java.util.ArrayList<EstadoMercadoATerminoType>();
            final StringBuilder querySBuilder = new StringBuilder().append("select id id,estadodestino descripcion ")
                    .append("from workflow_definition ").append("where fechabaja is null ")
                    .append(" and entidad   ='MatRegistro'");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        EstadoMercadoATerminoType _returnEstadoMAT = new EstadoMercadoATerminoType();
                        _returnEstadoMAT.setId(rset.getString("id"));
                        _returnEstadoMAT.setDescripcion(rset.getString("descripcion"));
                        _returnEstadoMATs.add(_returnEstadoMAT);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getEstados().addAll(_returnEstadoMATs);
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
    public ArrayOfMercadoType obtenerMercados(AuthType auth) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerMercados");
        ZeniContextServer.getInstance().printVerboLog(auth);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            ArrayOfMercadoType _return = new ArrayOfMercadoType();
            final java.util.List<MercadoType> _returnMercados = new java.util.ArrayList<MercadoType>();
            final StringBuilder querySBuilder = new StringBuilder().append("")
                    .append("select id,nombre,codigo,tipomercado ").append("from mercado ")
                    .append("where fechabaja is null ").append("and trabajaconmat = 1 ");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        MercadoType _returnMercado = new MercadoType();
                        _returnMercado.setId(rset.getString("id"));
                        _returnMercado.setNombre(rset.getString("nombre"));
                        _returnMercado.setCodigo(rset.getString("codigo"));
                        _returnMercado.setTipomercado(rset.getString("tipomercado"));
                        _returnMercados.add(_returnMercado);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getMercados().addAll(_returnMercados);
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

    public ArrayOfOperacionMATType obtenerOperacionesMAT(AuthType auth, CuentaType cuenta,
            EnumCompraVentaType condicion, EnumEstadoType estado, EnumSucursalType sucursal,
            ar.com.zeni.zeniwsdl.ArrayOfMercadoType mercados, RangoFechaType rangoFecha, EnumTipoOperacionMatType tipo,
            ArrayOfProductoMATType productos, ArrayOfDestinoMATType destinos, String precio, String prima,
            String posicion, EnumTipoOperacionType tipoOperacion) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerOperacionesMAT");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuenta);
        ZeniContextServer.getInstance().printVerboLog(condicion);
        ZeniContextServer.getInstance().printVerboLog(estado);
        ZeniContextServer.getInstance().printVerboLog(sucursal);
        ZeniContextServer.getInstance().printVerboLog(mercados);
        ZeniContextServer.getInstance().printVerboLog(rangoFecha);
        ZeniContextServer.getInstance().printVerboLog(tipo);
        ZeniContextServer.getInstance().printVerboLog(productos);
        ZeniContextServer.getInstance().printVerboLog(destinos);
        ZeniContextServer.getInstance().printVerboLog(precio);
        ZeniContextServer.getInstance().printVerboLog(prima);
        ZeniContextServer.getInstance().printVerboLog(posicion);
        ZeniContextServer.getInstance().printVerboLog(tipoOperacion);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            ArrayOfOperacionMATType _return = new ArrayOfOperacionMATType();
            final List<OperacionMATType> _returnOperaciones = new ArrayList<OperacionMATType>();
            if (cuenta == null || cuenta.getId().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El cliente no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFecha == null || DateUtil.ToString.ddMMyyyy(rangoFecha.getDesde()) == null) {
                throw new ZeniServerExeption("La fecha rangoFecha desde no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFecha == null || DateUtil.ToString.ddMMyyyy(rangoFecha.getHasta()) == null) {
                throw new ZeniServerExeption("La fecha rangoFecha hasta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            // if (condicion == null ||
            // condicion.equals(ZeniContextServer.VACIO)) {
            // throw new
            // ZeniServerExeption("La condicion no puede ser nulo o vacio",
            // FAULTCONSTANTS.ERROR_ERRONEO);
            // }
            // if (estado == null || estado.equals(ZeniContextServer.VACIO)) {
            // throw new
            // ZeniServerExeption("El estado no puede ser nulo o vacio",
            // FAULTCONSTANTS.ERROR_ERRONEO);
            // }
            // if (sucursal == null || sucursal.equals(ZeniContextServer.VACIO))
            // {
            // throw new
            // ZeniServerExeption("La sucursal no puede ser nulo o vacio",
            // FAULTCONSTANTS.ERROR_ERRONEO);
            // }
            // if (tipo == null || tipo.equals(ZeniContextServer.VACIO)) {
            // throw new ZeniServerExeption("El tipo no puede ser nulo o vacio",
            // FAULTCONSTANTS.ERROR_ERRONEO);
            // }
            if (tipoOperacion == null || tipoOperacion.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El tipo de operacion no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            // String filtroCondiciones = ZeniContextServer.VACIO;
            // String filtroPrecio = ZeniContextServer.VACIO;
            // if (precio != null && !precio.equals(ZeniContextServer.VACIO)) {
            // try {
            // filtroPrecio += "and mp.precio= " + precio + " ";
            // } catch (Exception e) {
            // }
            // }
            String listaDeProductos = ZeniContextServer.VACIO;
            if (productos != null && productos.getProductos() != null && productos.getProductos().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < productos.getProductos().size(); i++) {
                    sb.append(",'").append(productos.getProductos().get(i).getMatId()).append("'");
                }
                listaDeProductos = sb.deleteCharAt(0).insert(0, " and mp.matproducto_id in (").append(") ").toString();
            }

            String listaDeMercad = ZeniContextServer.VACIO;
            if (mercados != null && mercados.getMercados() != null && mercados.getMercados().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mercados.getMercados().size(); i++) {
                    sb.append(",'").append(mercados.getMercados().get(i).getId()).append("'");
                }
                listaDeMercad = sb.deleteCharAt(0).insert(0, " and mp.mercado_id in (").append(") ").toString();
            }
            // String listaDeDestino = ZeniContextServer.VACIO;
            // if (destinos != null && destinos.getDestinos() != null &&
            // destinos.getDestinos().size() > 0) {
            // StringBuilder sb = new StringBuilder();
            // for (int i = 0; i < destinos.getDestinos().size(); i++) {
            // sb.append(",'").append(destinos.getDestinos().get(i).getId()).append("'");
            // }
            // listaDeDestino = sb.deleteCharAt(0).insert(0,
            // " and mp.destino_id in (").append(") ").toString();
            // }

            final StringBuilder querySBuilder = new StringBuilder().append("");

            if (tipoOperacion.equals(EnumTipoOperacionType.ABIERTAS)) {

                ZeniContextServer.getInstance()
                        .printInfoLog("Executing obtenerOperacionesMAT  tipoConsulta {" + tipo + "} tipoOperacion {" + tipoOperacion + "} ");

                querySBuilder.append("SELECT mp.condicion condicion, ").append("merc.nombre mercado, ")
                        .append("op.nombre operador, ")
                        .append("DECODE(mp.tipo,'F','Futuros','T', 'Futuros','C','Opciones','P','Opciones','Otros') tipo, ")
                        .append("mprod.nombre producto, ").append("SUBSTR(mp.posicion,1,2) ").append("||'/' ")
                        .append("||SUBSTR(mp.posicion,3,4) posicion, ")
                        .append("DECODE(mp.tipo,'C',mp.precio,'P',mp.precio,NULL) precio_ejercicio, ")
                        .append("mp.preciooprima preciooprima, ").append("destino.nombre destino, ")
                        .append("mr.nroregistro registro, ")
                        // #1765
                        // .append("mp.fechaalta fecha_alta, ")
                        .append("mp.fecha fecha_alta, ")
                        .append("DECODE(mp.matcontrato_id,NULL,0,mp.volumen/mprod.volumencontrato) en_contratos, ")
                        // #1765
                        // .append("DECODE(mprod.operavolumenporboleta,1,mp.volumen,DECODE(wd.estadoActual,'V',mp.volumen,'T',DECODE(mp.mercado_id,sysc.value,mp.volumen,mprod.volumencontrato),'TA',
                        // DECODE(mp.mercado_id,sysc.value,mp.volumen,mprod.volumencontrato),'TC',DECODE(mp.mercado_id,sysc.value,
                        // mp.volumen,mprod.volumencontrato),mprod.volumencontrato))
                        // volumen, ")
                        .append("DECODE(mprod.operavolumenporboleta,1,mp.volumen,mprod.volumencontrato) volumen, ")
                        .append("DECODE(mp.tipo,'F','Futuro','T','Futuro sobre base','I','Inmediato','C','Call','P','Put','Otros') tipo_operacion, ")
                        .append("DECODE(wd.estadoActual,'A','Anulada','AJ','Candelado por Ajuste','C','Cancelada','E','Expirado','F','Abierta','I','Ingresada','J','Ejercicio', 'M','Ingresada Manual','O','Oferta Entrega','OA','Oferta Entrega Agrupada','P','Pedido Ejercicio','T','Caratulada','TA','Caratulada Ajustada', 'TC','Caratulada Cerrada','V','Voceo','Sin Codificar') estado, ")
                        .append("regCancelado.nroregistro AS Reg_Cancela, ")
                        .append("opCancelada.fecha        AS Fecha_Inicial, ")
                        .append("opCancelada.precioOPrima AS Precio_Inicial ").append("FROM mat_operacion mp ")
                        .append("INNER JOIN mat_registro mr ").append("ON (mr.matoperacion_id=mp.id) ")
                        .append("INNER JOIN mat_producto mprod ").append("ON (mprod.id=mp.matproducto_id) ")
                        .append("INNER JOIN workflow_state ws ").append("ON(ws.id = mr.state_id) ")
                        .append("INNER JOIN workflow_definition wd ").append("ON (wd.id = ws.workflowdefinition_id) ")
                        .append("INNER JOIN mercado merc ").append("ON (merc.id=mp.mercado_id) ")
                        .append("INNER JOIN sys_config sysc ").append("ON (sysc.id='id_mercado_matba') ")
                        .append("INNER JOIN cuenta_cliente ctacte ").append("ON (ctacte.id=mp.cuentacliente_id) ")
                        .append("LEFT OUTER JOIN operador_de_mesa op ").append("ON (op.id=ctacte.operadordemesa_id) ")
                        .append("LEFT OUTER JOIN mat_base destino ").append("ON (destino.id =mp.destino_id) ")
                        .append("LEFT JOIN mat_registro regCancelado ")
                        .append("ON (mr.liquida_a_id = regCancelado.id) ")
                        .append("LEFT JOIN mat_operacion opCancelada ")
                        .append("ON (regCancelado.matoperacion_id = opCancelada.id) ")
                        .append("WHERE mp.fechabaja              IS NULL ")
                        .append("AND mr.fechabaja                IS NULL ")
                        .append("and trunc(mp.fechaalta) >= to_date('"
                                + DateUtil.ToString.yyyyMMdd(rangoFecha.getDesde()) + "','yyyy-MM-dd') ")
                        .append("and trunc(mp.fechaalta) <= to_date('"
                                + DateUtil.ToString.yyyyMMdd(rangoFecha.getHasta()) + "','yyyy-MM-dd') ")
                        .append("and mp.cuentacliente_id=").append(cuenta.getId()).append(" ")
                        .append("AND merc.tipomercado            = 'G' ").append(
                        (tipo.equals(EnumTipoOperacionMatType.AMBOS) ? ZeniContextServer.VACIO
                        : (tipo.equals(EnumTipoOperacionMatType.FUTURO) ? "and mp.tipo='F' "
                        : (tipo.equals(EnumTipoOperacionMatType.OPCIONES)
                        ? "and mp.tipo in ('C','P') " : ZeniContextServer.VACIO))))
                        .append(listaDeMercad)
                        .append(listaDeProductos)
                        .append("AND mr.state_id                  = ").append("(SELECT id ")
                        .append("FROM workflow_state ws ").append("WHERE ws.fechabaja           IS NULL ")
                        .append("AND ws.id                     = mr.state_id ")
                        .append("AND ws.workflowdefinition_id IN ").append("(SELECT wd.id ")
                        .append("FROM workflow_definition wd ").append("WHERE wd.entidad   ='MatRegistro' ")
                        .append("AND wd.estadoactual='F' ").append("AND wd.fechabaja  IS NULL ").append(") ")
                        .append(") ");
            } else if (tipoOperacion.equals(EnumTipoOperacionType.ESTADISTICO)) {

                ZeniContextServer.getInstance()
                        .printInfoLog("Executing obtenerOperacionesMAT  tipoConsulta {" + tipo + "} tipoOperacion {" + tipoOperacion + "} ");

                querySBuilder.append("SELECT mp.condicion condicion, ").append("merc.nombre mercado, ")
                        .append("op.nombre operador, ")
                        .append("DECODE(mp.tipo,'F','Futuros','T', 'Futuros','C','Opciones','P','Opciones','Otros') tipo, ")
                        .append("mprod.nombre producto, ").append("SUBSTR(mp.posicion,1,2) ").append("||'/' ")
                        .append("||SUBSTR(mp.posicion,3,4) posicion, ")
                        .append("DECODE(mp.tipo,'C',mp.precio,'P',mp.precio,NULL) precio_ejercicio, ")
                        .append("mp.preciooprima preciooprima, ").append("destino.nombre destino, ")
                        .append("mr.nroregistro registro, ")
                        // #1765
                        // .append("mp.fechaalta fecha_alta, ")
                        .append("mp.fecha fecha_alta, ")
                        .append("DECODE(mp.matcontrato_id,NULL,0,mp.volumen/mprod.volumencontrato) en_contratos, ")
                        // #1765
                        // .append("DECODE(wd.estadoActual,'V',mp.volumen,'T',DECODE(mp.mercado_id,sysc.value,mp.volumen,mprod.volumencontrato),'TA',
                        // DECODE(mp.mercado_id,sysc.value,mp.volumen,mprod.volumencontrato),'TC',DECODE(mp.mercado_id,sysc.value,
                        // mp.volumen,mprod.volumencontrato),mprod.volumencontrato)
                        // volumen, ")
                        .append("DECODE(mprod.operavolumenporboleta,1,mp.volumen,mprod.volumencontrato) volumen, ")
                        .append("DECODE(mp.tipo,'F','Futuro','T','Futuro sobre base','I','Inmediato','C','Call','P','Put','Otros') tipo_operacion, ")
                        .append("DECODE(wd.estadoActual,'A','Anulada','AJ','Candelado por Ajuste','C','Cancelada','E','Expirado','F','Abierta','I','Ingresada','J','Ejercicio', 'M','Ingresada Manual','O','Oferta Entrega','OA','Oferta Entrega Agrupada','P','Pedido Ejercicio','T','Caratulada','TA','Caratulada Ajustada', 'TC','Caratulada Cerrada','V','Voceo','Sin Codificar') estado, ")
                        .append("regCancelado.nroregistro AS Reg_Cancela, ")
                        .append("opCancelada.fecha        AS Fecha_Inicial, ")
                        .append("opCancelada.precioOPrima AS Precio_Inicial ").append("FROM mat_operacion mp ")
                        .append("INNER JOIN mat_registro mr ").append("ON (mr.matoperacion_id=mp.id) ")
                        .append("INNER JOIN mat_producto mprod ").append("ON (mprod.id=mp.matproducto_id) ")
                        .append("INNER JOIN workflow_state ws ").append("ON(ws.id = mr.state_id) ")
                        .append("INNER JOIN workflow_definition wd ").append("ON (wd.id = ws.workflowdefinition_id) ")
                        .append("INNER JOIN mercado merc ").append("ON (merc.id=mp.mercado_id) ")
                        .append("INNER JOIN sys_config sysc ").append("ON (sysc.id='id_mercado_matba') ")
                        .append("INNER JOIN cuenta_cliente ctacte ").append("ON (ctacte.id=mp.cuentacliente_id) ")
                        .append("LEFT OUTER JOIN operador_de_mesa op ").append("ON (op.id=ctacte.operadordemesa_id) ")
                        .append("LEFT OUTER JOIN mat_base destino ").append("ON (destino.id =mp.destino_id) ")
                        .append("LEFT JOIN mat_registro regCancelado ")
                        .append("ON (mr.liquida_a_id = regCancelado.id) ")
                        .append("LEFT JOIN mat_operacion opCancelada ")
                        .append("ON (regCancelado.matoperacion_id = opCancelada.id) ")
                        .append("WHERE mp.fechabaja              IS NULL ")
                        .append("AND mr.fechabaja                IS NULL ")
                        .append("and trunc(mp.fechaalta) >= to_date('"
                                + DateUtil.ToString.yyyyMMdd(rangoFecha.getDesde()) + "','yyyy-MM-dd') ")
                        .append("and trunc(mp.fechaalta) <= to_date('"
                                + DateUtil.ToString.yyyyMMdd(rangoFecha.getHasta()) + "','yyyy-MM-dd') ")
                        .append("and mp.cuentacliente_id=").append(cuenta.getId()).append(" ")
                        .append("AND merc.tipomercado            = 'G' ").append(
                        (tipo.equals(EnumTipoOperacionMatType.AMBOS) ? ZeniContextServer.VACIO
                        : (tipo.equals(EnumTipoOperacionMatType.FUTURO) ? "and mp.tipo='F' "
                        : (tipo.equals(EnumTipoOperacionMatType.OPCIONES)
                        ? "and mp.tipo in ('C','P') " : ZeniContextServer.VACIO))))
                        .append(listaDeMercad)
                        .append(listaDeProductos)
                        .append("AND mr.state_id                  = ").append("(SELECT id ")
                        .append("FROM workflow_state ws ").append("WHERE ws.fechabaja           IS NULL ")
                        .append("AND ws.id                     = mr.state_id ")
                        .append("AND ws.workflowdefinition_id IN ").append("(SELECT wd.id ")
                        .append("FROM workflow_definition wd ").append("WHERE wd.entidad      ='MatRegistro' ")
                        .append("AND (wd.estadoactual IN ('F', 'C', 'AJ', 'J', 'E','O', 'OA') ")
                        .append("OR (wd.estadoactual   = 'I' ").append("AND TRUNC(mp.fecha)  >= TRUNC(sysdate-1) ")
                        .append("AND TRUNC(mp.fecha)  <= TRUNC(sysdate) )) ").append("AND wd.fechabaja     IS NULL ")
                        .append(") ").append(") ");
            }
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            OperacionMATType _returnOperacionesVal1 = new OperacionMATType();
                            _returnOperacionesVal1.setCondicion(rset.getString("condicion"));
                            _returnOperacionesVal1.setMercado(rset.getString("mercado"));
                            _returnOperacionesVal1.setOperador(rset.getString("operador"));
                            _returnOperacionesVal1.setTipo(rset.getString("tipo"));
                            _returnOperacionesVal1.setProducto(rset.getString("producto"));
                            _returnOperacionesVal1.setPosicion(rset.getString("posicion"));
                            _returnOperacionesVal1.setVolumen(rset.getFloat("volumen"));
                            _returnOperacionesVal1.setPrecioEjercicio(rset.getFloat("precio_ejercicio"));
                            _returnOperacionesVal1.setPreciooprima(rset.getFloat("preciooprima"));
                            _returnOperacionesVal1.setDestino(rset.getString("destino"));
                            _returnOperacionesVal1.setRegistro(rset.getString("registro"));
                            _returnOperacionesVal1.setFechaAlta(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecha_alta"))));
                            _returnOperacionesVal1.setEnContratos(rset.getFloat("en_contratos"));
                            _returnOperacionesVal1.setTipoOperacion(rset.getString("tipo_operacion"));
                            _returnOperacionesVal1.setEstado(rset.getString("estado"));
                            _returnOperacionesVal1.setRegCancela(rset.getString("Reg_Cancela"));
                            _returnOperacionesVal1.setFechaInicial(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("Fecha_Inicial"))));
                            _returnOperacionesVal1.setPrecioInicial(rset.getFloat("Precio_Inicial"));
                            _returnOperaciones.add(_returnOperacionesVal1);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getOperaciones().addAll(_returnOperaciones);
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

    public ArrayOfDestinoMATType obtenerDestinosMAT(AuthType auth) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerDestinosMAT");
        ZeniContextServer.getInstance().printVerboLog(auth);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            ArrayOfDestinoMATType _return = new ArrayOfDestinoMATType();
            final List<DestinoMATType> _returnDestinos = new ArrayList<DestinoMATType>();
            final StringBuilder querySBuilder = new StringBuilder().append("select id,nombre,codigo,abreviatura ")
                    .append("from mat_base ").append("where fechabaja is null ");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        DestinoMATType _returnDestinosVal1 = new DestinoMATType();
                        _returnDestinosVal1.setId(rset.getString("id"));
                        _returnDestinosVal1.setNombre(rset.getString("nombre"));
                        _returnDestinosVal1.setCodigo(rset.getString("codigo"));
                        _returnDestinosVal1.setAvreviatura(rset.getString("abreviatura"));
                        _returnDestinos.add(_returnDestinosVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getDestinos().addAll(_returnDestinos);
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

    public ArrayOfResumenMATType obtenerResumenMAT(AuthType auth, CuentaType cuenta, EnumCompraVentaType condicion,
            EnumEstadoType estado, EnumSucursalType sucursal, ArrayOfMercadoType mercados, RangoFechaType rangoFecha,
            EnumTipoOperacionMatType tipo, ArrayOfProductoMATType productos, ArrayOfDestinoMATType destinos,
            String posicion) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerResumenMAT");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuenta);
        ZeniContextServer.getInstance().printVerboLog(condicion);
        ZeniContextServer.getInstance().printVerboLog(estado);
        ZeniContextServer.getInstance().printVerboLog(sucursal);
        ZeniContextServer.getInstance().printVerboLog(mercados);
        ZeniContextServer.getInstance().printVerboLog(rangoFecha);
        ZeniContextServer.getInstance().printVerboLog(tipo);
        ZeniContextServer.getInstance().printVerboLog(productos);
        ZeniContextServer.getInstance().printVerboLog(destinos);
        ZeniContextServer.getInstance().printVerboLog(posicion);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (cuenta == null || cuenta.getId().equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El cliente no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFecha == null || DateUtil.ToString.ddMMyyyy(rangoFecha.getDesde()) == null) {
                throw new ZeniServerExeption("La fecha rangoFecha desde no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (rangoFecha == null || DateUtil.ToString.ddMMyyyy(rangoFecha.getHasta()) == null) {
                throw new ZeniServerExeption("La fecha rangoFecha hasta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (condicion == null || condicion.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("La condicion no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (estado == null || estado.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El estado no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (sucursal == null || sucursal.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("La sucursal no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (tipo == null || tipo.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El tipo no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            String listaDeProductos = ZeniContextServer.VACIO;
            if (productos != null && productos.getProductos() != null && productos.getProductos().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < productos.getProductos().size(); i++) {
                    sb.append(",'").append(productos.getProductos().get(i).getMatId()).append("'");
                }
                listaDeProductos = sb.deleteCharAt(0).insert(0, " and mo.matproducto_id in (").append(") ").toString();
            }
            String listaDeMercad = ZeniContextServer.VACIO;
            if (mercados != null && mercados.getMercados() != null && mercados.getMercados().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mercados.getMercados().size(); i++) {
                    sb.append(",'").append(mercados.getMercados().get(i).getId()).append("'");
                }
                listaDeMercad = sb.deleteCharAt(0).insert(0, " and mo.mercado_id in (").append(") ").toString();
            }
            String listaDeDestino = ZeniContextServer.VACIO;
            if (destinos != null && destinos.getDestinos() != null && destinos.getDestinos().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < destinos.getDestinos().size(); i++) {
                    sb.append(",'").append(destinos.getDestinos().get(i).getId()).append("'");
                }
                listaDeDestino = sb.deleteCharAt(0).insert(0, " and mo.destino_id in (").append(") ").toString();
            }
            ArrayOfResumenMATType _return = new ArrayOfResumenMATType();
            final List<ResumenMATType> _returnResumenes = new ArrayList<ResumenMATType>();
            final StringBuilder querySBuilder = new StringBuilder().append("SELECT mo.condicion condicion, ")
                    .append("max(merc.nombre) mercado, ").append("max(op.nombre) operador, ")
                    .append("max(mprod.nombre) producto, ")
                    .append("substr(mo.posicion,1,2)||'/'||substr(mo.posicion,3,4) posicion, ")
                    .append("max(destino.nombre) destino, ")
                    // #1765
                    // .append("sum(mo.volumen) volumen ")
                    .append(" sum( DECODE(mprod.operavolumenporboleta,1,mo.volumen,mprod.volumencontrato)) volumen ")
                    .append("FROM mat_operacion mo ")
                    .append("INNER JOIN mat_registro mr ON (mr.matoperacion_id=mo.id) ")
                    .append("INNER JOIN mat_producto mprod ON (mprod.id=mo.matproducto_id) ")
                    .append("inner join mercado merc on (merc.id=mo.mercado_id) ")
                    .append("inner join cuenta_cliente ctacte on (ctacte.id=mo.cuentacliente_id) ")
                    .append("left outer join operador_de_mesa op on (op.id=ctacte.operadordemesa_id) ")
                    .append("LEFT OUTER JOIN mat_base destino ON (destino.id =mo.destino_id) ")
                    .append("WHERE mo.fechabaja    IS NULL ").append("AND mr.fechabaja      IS NULL ")
                    .append("and trunc(mo.fecha) >= to_date('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getDesde())
                            + "','yyyy-MM-dd') ")
                    .append("and trunc(mo.fecha) <= to_date('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getHasta())
                            + "','yyyy-MM-dd') ")
                    .append("and mo.cuentacliente_id=").append(cuenta.getId()).append(" ").append(listaDeMercad)
                    .append((tipo.equals(EnumTipoOperacionMatType.AMBOS) ? ZeniContextServer.VACIO
                            : (tipo.equals(EnumTipoOperacionMatType.FUTURO) ? "and mo.tipo='F' "
                            : (tipo.equals(EnumTipoOperacionMatType.OPCIONES) ? "and mo.tipo in ('C','P') "
                            : ZeniContextServer.VACIO))))
                    .append((condicion.equals(EnumCompraVentaType.AMBOS) ? ZeniContextServer.VACIO
                            : (condicion.equals(EnumCompraVentaType.COMPRA) ? "and mo.condicion='C' "
                            : (condicion.equals(EnumCompraVentaType.VENTA) ? "and mo.condicion='V' "
                            : ZeniContextServer.VACIO))))
                    .append(listaDeProductos)
                    .append((posicion != null && !posicion.equals(ZeniContextServer.VACIO)
                            ? "and mo.posicion='" + posicion + "' " : ZeniContextServer.VACIO))
                    .append(listaDeDestino)
                    .append("group by mo.tipo,mo.matproducto_id,mo.mercado_id,mo.condicion,mo.posicion,mo.destino_id ")
                    .append("order by mo.posicion,destino ");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        ResumenMATType _returnResumenesVal1 = new ResumenMATType();
                        _returnResumenesVal1.setCondicion(rset.getString("condicion"));
                        _returnResumenesVal1.setMercado(rset.getString("mercado"));
                        _returnResumenesVal1.setOperador(rset.getString("operador"));
                        _returnResumenesVal1.setProducto(rset.getString("producto"));
                        _returnResumenesVal1.setPosicion(rset.getString("posicion"));
                        _returnResumenesVal1.setDestino(rset.getString("destino"));
                        _returnResumenesVal1.setVolumen(rset.getFloat("volumen"));
                        _returnResumenes.add(_returnResumenesVal1);
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            _return.getResumenes().addAll(_returnResumenes);
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
    public ArrayOfOperacionMercadoATerminoType obtenerOperacionesMercadoATermino(AuthType auth, ArrayOfIdCuenta Cuentas,
            EnumTipoOperacionMercadoATerminoType TipoFiltro, RangoFechaType RangoDeFechas, String IdOperacion)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerOperacionesMercadoATermino");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(Cuentas);
        ZeniContextServer.getInstance().printVerboLog(RangoDeFechas);
        ZeniContextServer.getInstance().printVerboLog(TipoFiltro);

        ZeniContextServer.getInstance().printErrorLog("Tipo Filtro: " + TipoFiltro);
        ZeniContextServer.getInstance().printErrorLog("IdOperacion: " + IdOperacion);
        ZeniContextServer.getInstance().printErrorLog("Cuentas: " + Cuentas);
        ZeniContextServer.getInstance().printErrorLog("Rango: " + RangoDeFechas);

        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (Cuentas == null || Cuentas.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("la cuentas no pueden ser nulo o vacias", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (RangoDeFechas == null || DateUtil.ToString.ddMMyyyy(RangoDeFechas.getDesde()) == null) {
                throw new ZeniServerExeption("La fecha rangoFecha desde no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (RangoDeFechas == null || DateUtil.ToString.ddMMyyyy(RangoDeFechas.getHasta()) == null) {
                throw new ZeniServerExeption("La fecha rangoFecha hasta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            if (TipoFiltro == null || TipoFiltro.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("El filtro no puede ser nulo o vacio " + TipoFiltro,
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            String cuentas_cuentas = ZeniContextServer.VACIO;
            if (Cuentas != null && Cuentas.getCuentas() != null && Cuentas.getCuentas().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < Cuentas.getCuentas().size(); i++) {
                    sb.append(",").append(Cuentas.getCuentas().get(i)).append("");
                }
                cuentas_cuentas = sb.deleteCharAt(0).insert(0, "(").append(") ").toString();
            }

            StringBuffer filtroAppend = new StringBuffer(" ");

            if (IdOperacion != null && !IdOperacion.equals("")) {
                filtroAppend.append(" AND mp.id = " + IdOperacion);
            } else {

                XMLGregorianCalendar fd = RangoDeFechas.getDesde().getFecha();
                XMLGregorianCalendar fh = RangoDeFechas.getHasta().getFecha();

                Calendar cd = new GregorianCalendar(fd.getYear(), fd.getMonth(), fd.getDay());
                cd.add(Calendar.DAY_OF_MONTH, 31);

                Calendar ch = new GregorianCalendar(fh.getYear(), fh.getMonth(), fh.getDay());

                if (cd.before(ch)) {
                    throw new ZeniServerExeption("El Rango de fechas debe ser inferior a 30 días",
                            FAULTCONSTANTS.ERROR_ERRONEO);
                }

                if (TipoFiltro.equals(EnumTipoOperacionMercadoATerminoType.ABIERTAS)) {
                    filtroAppend.append(" AND mr.state_id  = ");
                    filtroAppend.append(" (SELECT id ");
                    filtroAppend.append(" FROM workflow_state ws1 ");
                    filtroAppend.append(" WHERE ws1.fechabaja   IS NULL ");
                    filtroAppend.append(" AND ws1.id   = mr.state_id ");
                    filtroAppend.append(" AND ws1.workflowdefinition_id IN ");
                    filtroAppend.append(" (SELECT wd1.id ");
                    filtroAppend.append(" FROM workflow_definition wd1 ");
                    filtroAppend.append(" WHERE wd1.entidad   ='MatRegistro' ");
                    filtroAppend.append(" AND wd1.estadoactual='F' ");
                    filtroAppend.append(" AND wd1.fechabaja  IS NULL ");
                    filtroAppend.append(" ) ");
                    filtroAppend.append(" ) ");
                }

                if (TipoFiltro.equals(EnumTipoOperacionMercadoATerminoType.RANGOFECHAAPERTURA)
                        || TipoFiltro.equals(EnumTipoOperacionMercadoATerminoType.ABIERTAS)) {
                    filtroAppend.append(" AND TRUNC(mp.fechaalta)         >= to_date('"
                            + DateUtil.ToString.yyyyMMdd(RangoDeFechas.getDesde()) + "','yyyy-MM-dd') ");
                    filtroAppend.append(" AND TRUNC(mp.fechaalta)         <= to_date('"
                            + DateUtil.ToString.yyyyMMdd(RangoDeFechas.getHasta()) + "','yyyy-MM-dd') ");
                }

                if (TipoFiltro.equals(EnumTipoOperacionMercadoATerminoType.RANGOFECHAACTUALIZACION)) {
                    filtroAppend.append(" AND TRUNC(mp.fechamodif)         >= to_date('"
                            + DateUtil.ToString.yyyyMMdd(RangoDeFechas.getDesde()) + "','yyyy-MM-dd') ");
                    filtroAppend.append(" AND TRUNC(mp.fechamodif)         <= to_date('"
                            + DateUtil.ToString.yyyyMMdd(RangoDeFechas.getHasta()) + "','yyyy-MM-dd') ");
                }
            }

            final ArrayOfOperacionMercadoATerminoType _return = new ArrayOfOperacionMercadoATerminoType();
            final List<OperacionMercadoATerminoType> _returnMercado = new ArrayList<OperacionMercadoATerminoType>();
            final StringBuilder querySBuilder = new StringBuilder().append("SELECT ").append("OP.ID ID, ")
                    .append("OP.MERCADO MERCADO, ")
                    // #1563
                    .append("OP.MONEDA MONEDA, ").append("OP.NROSUBCTA NROSUBCTA, ").append("OP.ESTADO ESTADO, ")
                    .append("OP.PRODUCTO PRODUCTO, ").append("OP.PUERTO PUERTO, ").append("OP.POSICION POSICION, ")
                    .append("OP.REGISTRO REGISTRO, ").append("OP.FECHA FECHA, ")
                    .append("OP.TIPO_OPERACION TIPO_OPERACION, ").append("OP.PRIMA PRIMA, ")
                    .append("OP.PRECIOAPERTURA PRECIOAPERTURA, ").append("OP.TONELADAS TONELADAS, ")
                    .append("OP.CANTIDAD CANTIDAD, ").append("OP.NROCUENTA NROCUENTA, ")
                    .append("OP.LIQUIDA_A_ID LIQUIDA_A_ID, ").append("OP.ES_LIQUIDADO_POR_ID, ").append("OP.ACCION, ")
                    .append("OP.FECHAACTUALIZADO, ").append("OP.CONTRATOZENI ")
                    // .append("OP.GARANTIA GARANTIA, ")
                    // .append("OP.VALORAJUSTE VALORAJUSTE ")
                    // .append("ROUND(DECODE(OP.MONEDA,1718,(OP.PRECIOAPERTURA-OP.VALORAJUSTE)*OP.VOLUMEN,0),2)
                    // RESULTADO_USD, ")
                    // .append("ROUND(DECODE(OP.MONEDA,1718,0,(OP.PRECIOAPERTURA-OP.VALORAJUSTE)*OP.VOLUMEN),2)
                    // RESULTADO_PES ")
                    .append("FROM ( ").append("SELECT mr.id id, ").append("mr.nrosubctareg nrosubcta, ")
                    .append("nvl(mp.moneda_id,1718) moneda, ").append("mp.mercado_id mercado, ")
                    .append("mp.matproducto_id producto, ").append("wd.id estado, ").append("mp.destino_id puerto, ")
                    .append("SUBSTR(mp.posicion,1,2) ||'/' ||SUBSTR(mp.posicion,3,4) posicion, ")
                    .append("mr.nroregistro registro, ").append("mp.fechaalta fecha, ")
                    .append("mp.condicion ||'-'||mp.tipo tipo_operacion, ")
                    .append("DECODE(mp.tipo,'C',mp.preciooprima,'P',mp.preciooprima,0) prima, ")
                    .append("DECODE(mp.tipo,'C',0,'P',0,mp.preciooprima) precioapertura, ")
                    .append("DECODE(udm.abreviatura,'TN',DECODE(wd.estadoActual,'V',mp.volumen,'T',DECODE(mp.mercado_id,sysc.value,mp.volumen,mprod.volumencontrato),'TA', DECODE(mp.mercado_id,sysc.value,mp.volumen,mprod.volumencontrato),'TC',DECODE(mp.mercado_id,sysc.value, mp.volumen,mprod.volumencontrato),mprod.volumencontrato),0) toneladas, ")
                    .append("DECODE(wd.estadoActual,'V',mp.volumen,'T',DECODE(mp.mercado_id,sysc.value,mp.volumen,mprod.volumencontrato),'TA', DECODE(mp.mercado_id,sysc.value,mp.volumen,mprod.volumencontrato),'TC',DECODE(mp.mercado_id,sysc.value, mp.volumen,mprod.volumencontrato),mprod.volumencontrato) cantidad, ")
                    .append("cc.nrocuenta nrocuenta, ").append("mr.liquida_a_id, ").append("mr.es_liquidado_por_id, ")
                    .append("mr.accion, ").append("NVL(ws.fechamodif,ws.fechaalta) FECHAACTUALIZADO, ")
                    .append("DECODE(wd.estadodestino,'T',c.numerocontrato,'TC',c.numerocontrato,'TA',c.numerocontrato,null) CONTRATOZENI ")
                    // .append("gar.garantia, ")
                    // .append("ajuste.precioajuste valorajuste ")
                    // .append(" 0 garantia, ")
                    // .append(" 0 valorajuste ")
                    .append(" FROM mat_operacion mp ").append("INNER JOIN mat_registro mr ")
                    .append("ON (mr.matoperacion_id=mp.id) ").append("INNER JOIN mat_producto mprod ")
                    .append("ON (mprod.id=mp.matproducto_id) ").append(" INNER JOIN unidad_de_medida udm ")
                    .append(" ON (udm.id=mprod.unidaddemedida_id) ").append("INNER JOIN mercado merc ")
                    .append("ON (merc.id=mp.mercado_id) ").append("INNER JOIN cuenta_cliente cc ")
                    .append("ON (cc.id=mp.cuentacliente_id)").append("INNER JOIN workflow_state ws ")
                    .append("ON(ws.id = mr.state_id) ").append("INNER JOIN workflow_definition wd ")
                    .append("ON (wd.id = ws.workflowdefinition_id) ").append("LEFT OUTER JOIN mat_contrato mc ")
                    .append("ON (mc.id=mp.matcontrato_id) ").append("LEFT OUTER JOIN contrato c ")
                    .append("ON (c.id=mc.contrato_id) ").append("INNER JOIN sys_config sysc ")
                    .append("ON (sysc.id='id_mercado_matba') ")
                    .append("INNER JOIN workflow_state ws on (ws.id=mr.state_id)")
                    .append("INNER JOIN workflow_definition wd on (wd.id=ws.workflowdefinition_id and wd.entidad   ='MatRegistro')")
                    // .append("LEFT OUTER JOIN (select
                    // mdyg.matregistro_id,max(mdyg.garantia) garantia ")
                    // .append(" from zeniapp.mat_dif_y_gtia mdyg ")
                    // .append(" where mdyg.fechaalta = (select
                    // max(xx2.fechaalta) ")
                    // .append(" from zeniapp.mat_dif_y_gtia xx2 ")
                    // .append(" where xx2.matregistro_id=mdyg.matregistro_id)
                    // ")
                    // .append(" group by mdyg.matregistro_id ")
                    // .append(" ) gar on (gar.matregistro_id=mr.id) ")
                    // .append("LEFT OUTER JOIN (select
                    // aj.posicion,aj.matproducto_id,max(aj.precioajuste)
                    // precioajuste
                    // ")
                    // .append(" from mat_precio_ajuste aj ")
                    // .append(" where aj.fechaalta = (select max(xx1.fechaalta)
                    // ")
                    // .append(" from mat_precio_ajuste xx1 ")
                    // .append(" where xx1.posicion=aj.posicion ")
                    // .append(" and xx1.matproducto_id=aj.matproducto_id) ")
                    // .append(" group by aj.posicion,aj.matproducto_id) ajuste
                    // on (ajuste.posicion=mp.posicion and
                    // ajuste.matproducto_id=mp.matproducto_id) ")
                    .append("WHERE mp.fechabaja              IS NULL ")
                    .append("AND mr.fechabaja                IS NULL ").append("AND mp.cuentacliente_id          in  ")
                    .append(cuentas_cuentas).append(" ").append("AND merc.tipomercado            = 'G' ")
                    .append(filtroAppend.toString()).append(") OP ");
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            OperacionMercadoATerminoType _returnMercadoAterminoVal1 = new OperacionMercadoATerminoType();
                            ZeniContextServer.getInstance().printErrorLog("EJECUTANDO: " + rset.getInt("ID"));
                            _returnMercadoAterminoVal1.setCANTIDAD(rset.getFloat("CANTIDAD"));
                            _returnMercadoAterminoVal1.setFECHA(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("FECHA"))));
                            _returnMercadoAterminoVal1.setIDMERCADO(rset.getInt("MERCADO"));

                            // #1563
                            _returnMercadoAterminoVal1.setIDMONEDA(rset.getInt("MONEDA"));

                            _returnMercadoAterminoVal1.setIDESTADO(rset.getInt("ESTADO"));
                            _returnMercadoAterminoVal1.setPOSICION(rset.getString("POSICION"));
                            _returnMercadoAterminoVal1.setPRECIOAPERTURA(rset.getFloat("PRECIOAPERTURA"));
                            _returnMercadoAterminoVal1.setPRIMA(rset.getFloat("PRIMA"));
                            _returnMercadoAterminoVal1.setIDPRODUCTO(rset.getInt("PRODUCTO"));
                            _returnMercadoAterminoVal1.setIDPUERTO(rset.getInt("PUERTO"));
                            _returnMercadoAterminoVal1.setREGISTRO(rset.getInt("REGISTRO"));
                            _returnMercadoAterminoVal1.setTIPOOPERACION(rset.getString("TIPO_OPERACION"));
                            _returnMercadoAterminoVal1.setTONELADAS(rset.getFloat("TONELADAS"));
                            _returnMercadoAterminoVal1.setNROSUBCTA(rset.getInt("NROSUBCTA"));
                            _returnMercadoAterminoVal1.setIDREGISTRO(rset.getInt("ID"));
                            _returnMercadoAterminoVal1.setNROCUENTA(rset.getString("NROCUENTA"));
                            _returnMercadoAterminoVal1.setLIQUIDA_A_ID(rset.getInt("LIQUIDA_A_ID"));
                            _returnMercadoAterminoVal1.setES_LIQUIDADO_POR_ID(rset.getInt("ES_LIQUIDADO_POR_ID"));
                            _returnMercadoAterminoVal1.setACCION(rset.getString("ACCION"));
                            _returnMercadoAterminoVal1.setFECHAACTUALIZADO(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.yyyyMMdd(rset.getString("FECHAACTUALIZADO"))));
                            _returnMercadoAterminoVal1.setCONTRATOZENI(rset.getString("CONTRATOZENI"));
                            _returnMercado.add(_returnMercadoAterminoVal1);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniContextServer.getInstance().printErrorLog("ANTES DE EJECUTAR: ");
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            ZeniContextServer.getInstance().printErrorLog("TERMINO: ");
            _return.getMercater().addAll(_returnMercado);
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
    public ArrayOfGarantiasMercadoATerminoType obtenerGarantiasMercadoATermino(AuthType auth, ArrayOfIdCuenta cuentas,
            FechaTimeType fecha) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerGarantiasMercadoATermino");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuentas);
        ZeniContextServer.getInstance().printVerboLog(fecha);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (cuentas == null || cuentas.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("la cuentas no pueden ser nulo o vacias", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (fecha == null || DateUtil.ToString.ddMMyyyy(fecha.getFecha()) == null) {
                throw new ZeniServerExeption("La fecha no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }

            String cuentas_cuentas = ZeniContextServer.VACIO;
            if (cuentas != null && cuentas.getCuentas() != null && cuentas.getCuentas().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < cuentas.getCuentas().size(); i++) {
                    sb.append(",").append(cuentas.getCuentas().get(i)).append("");
                }
                cuentas_cuentas = sb.deleteCharAt(0).insert(0, "(").append(") ").toString();
            }

            StringBuffer filtroAppend = new StringBuffer(" ");

            filtroAppend.append(" AND TRUNC(gtias.fecha)         = to_date('"
                    + DateUtil.ToString.yyyyMMdd(fecha.getFecha()) + "','yyyy-MM-dd') ");

            final ArrayOfGarantiasMercadoATerminoType _return = new ArrayOfGarantiasMercadoATerminoType();
            final List<GarantiasMercadoATerminoType> _returnGarantias = new ArrayList<GarantiasMercadoATerminoType>();
            final StringBuilder querySBuilder = new StringBuilder().append("SELECT ")
                    .append(" d.MATREGISTRO_ID REGISTRO, ").append(" d.GARANTIA GARANTIA, ")
                    .append(" gtias.FECHA FECHAPOSICION, ").append(" gtias.MONEDA_ID MONEDA_ID")
                    .append(" from ZENIAPP.MAT_DIF_O_GTIA_A_LIQUIDAR gtias ")
                    .append(" INNER JOIN ZENIAPP.MAT_DIF_Y_GTIA d ON (gtias.id = d.MATGARANTIAALIQUIDAR_ID) ")
                    .append("WHERE gtias.fechabaja  IS NULL ").append(" AND d.fechabaja is null ")
                    .append(" AND gtias.cuentacliente_id in  ").append(cuentas_cuentas).append(" ")
                    .append("AND gtias.TIPO  = 'G' ").append(filtroAppend.toString());
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            GarantiasMercadoATerminoType _returnGarantiasMercadoAterminoVal1 = new GarantiasMercadoATerminoType();
                            _returnGarantiasMercadoAterminoVal1.setIDREGISTRO(rset.getInt("REGISTRO"));
                            _returnGarantiasMercadoAterminoVal1.setFECHAPOSICION(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("FECHAPOSICION"))));
                            _returnGarantiasMercadoAterminoVal1.setIDMONEDA(rset.getInt("MONEDA_ID"));
                            _returnGarantiasMercadoAterminoVal1.setGARANTIA(rset.getFloat("GARANTIA"));
                            _returnGarantias.add(_returnGarantiasMercadoAterminoVal1);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniContextServer.getInstance().printErrorLog("ANTES DE EJECUTAR: ");
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            ZeniContextServer.getInstance().printErrorLog("TERMINO: ");
            _return.getGarantias().addAll(_returnGarantias);
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
    public ArrayOfResultadosMercadoATerminoType obtenerResultadosMercadoATermino(AuthType auth, ArrayOfIdCuenta cuentas,
            FechaTimeType fecha) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerResultadosMercadoATermino");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuentas);
        ZeniContextServer.getInstance().printVerboLog(fecha);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (cuentas == null || cuentas.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("la cuentas no pueden ser nulo o vacias", FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (fecha == null || DateUtil.ToString.ddMMyyyy(fecha.getFecha()) == null) {
                throw new ZeniServerExeption("La fecha no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
            }

            String cuentas_cuentas = ZeniContextServer.VACIO;
            if (cuentas != null && cuentas.getCuentas() != null && cuentas.getCuentas().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < cuentas.getCuentas().size(); i++) {
                    sb.append(",").append(cuentas.getCuentas().get(i)).append("");
                }
                cuentas_cuentas = sb.deleteCharAt(0).insert(0, "(").append(") ").toString();
            }

            StringBuffer filtroAppend = new StringBuffer(" ");

            filtroAppend.append(" AND TRUNC(gtias.fecha)         = to_date('"
                    + DateUtil.ToString.yyyyMMdd(fecha.getFecha()) + "','yyyy-MM-dd') ");

            final ArrayOfResultadosMercadoATerminoType _return = new ArrayOfResultadosMercadoATerminoType();
            final List<ResultadosMercadoATerminoType> _returnResultados = new ArrayList<ResultadosMercadoATerminoType>();
            final StringBuilder querySBuilder = new StringBuilder().append(" SELECT ")
                    .append(" d.MATREGISTRO_ID REGISTRO, ").append(" OP.PRECIO PRECIOAPERTURA, ")
                    .append(" OP.PRIMA PRIMA, ").append(" PA.PRECIOAJUSTE PRECIOAJUSTE, ")
                    .append(" d.DIFERENCIA RESULTADO, ").append(" gtias.FECHA FECHAPOSICION, ")
                    .append(" gtias.MONEDA_ID MONEDA_ID").append(" from ZENIAPP.MAT_DIF_O_GTIA_A_LIQUIDAR gtias ")
                    .append(" INNER JOIN ZENIAPP.MAT_DIF_Y_GTIA d ON (gtias.id = d.MATDIFERENCIAALIQUIDAR_ID) ")
                    .append(" INNER JOIN MAT_REGISTRO R ON (R.ID = d.MATREGISTRO_ID) ")
                    .append(" INNER JOIN MAT_OPERACION OP ON (OP.ID = R.MATOPERACION_ID) ")
                    .append(" INNER JOIN MAT_PRECIO_AJUSTE PA ON (PA.FECHA = gtias.FECHA AND PA.POSICION = OP.POSICION AND PA.MATPRODUCTO_ID = OP.MATPRODUCTO_ID AND PA.TIPO = OP.TIPO ) ")
                    .append("WHERE gtias.fechabaja  IS NULL ").append(" AND d.fechabaja is null ")
                    .append(" AND gtias.cuentacliente_id in  ").append(cuentas_cuentas).append(" ")
                    .append("AND gtias.TIPO  = 'D' ").append(filtroAppend.toString());
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            ResultadosMercadoATerminoType _returnResultadosMercadoAterminoVal1 = new ResultadosMercadoATerminoType();
                            _returnResultadosMercadoAterminoVal1.setIDREGISTRO(rset.getInt("REGISTRO"));
                            _returnResultadosMercadoAterminoVal1.setFECHAPOSICION(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("FECHAPOSICION"))));
                            // #1567
                            // _returnResultadosMercadoAterminoVal1
                            // .setIDMONEDA(rset.getInt("MONEDA_ID"));

                            _returnResultadosMercadoAterminoVal1.setRESULTADO(rset.getFloat("RESULTADO"));

                            // #1567
                            // _returnResultadosMercadoAterminoVal1
                            // .setPRECIOAPERTURA(rset
                            // .getFloat("PRECIOAPERTURA"));
                            // _returnResultadosMercadoAterminoVal1.setPRIMA(rset
                            // .getFloat("PRIMA"));
                            _returnResultadosMercadoAterminoVal1.setVALORAJUSTE(rset.getFloat("PRECIOAJUSTE"));
                            _returnResultados.add(_returnResultadosMercadoAterminoVal1);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        }
                    }
                }
            };
            ZeniContextServer.getInstance().printErrorLog("ANTES DE EJECUTAR: ");
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            ZeniContextServer.getInstance().printErrorLog("TERMINO: ");
            _return.getResultados().addAll(_returnResultados);
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

    // #1501
    @Override
    public ArrayOfFacturaType obtenerFacturas(AuthType auth, ArrayOfIdCuenta cuentas, RangoFechaType RangoDeFechas)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerFacturas");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(cuentas);
        ZeniContextServer.getInstance().printVerboLog(RangoDeFechas);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (cuentas == null || cuentas.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("la cuentas no pueden ser nulo o vacias", FAULTCONSTANTS.ERROR_ERRONEO);
            }

            if (RangoDeFechas == null || DateUtil.ToString.ddMMyyyy(RangoDeFechas.getDesde()) == null) {
                throw new ZeniServerExeption("La fecha rangoFecha desde no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            if (RangoDeFechas == null || DateUtil.ToString.ddMMyyyy(RangoDeFechas.getHasta()) == null) {
                throw new ZeniServerExeption("La fecha rangoFecha hasta no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }
            String cuentas_cuentas = ZeniContextServer.VACIO;
            if (cuentas != null && cuentas.getCuentas() != null && cuentas.getCuentas().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < cuentas.getCuentas().size(); i++) {
                    sb.append(",").append(cuentas.getCuentas().get(i)).append("");
                }
                cuentas_cuentas = sb.deleteCharAt(0).insert(0, "(").append(") ").toString();
            }

            StringBuffer filtroAppend = new StringBuffer(" ");

            // #1654
            filtroAppend.append(" AND TRUNC(fp.fechafactura)         >= to_date('"
                    + DateUtil.ToString.yyyyMMdd(RangoDeFechas.getDesde()) + "','yyyy-MM-dd') ");

            filtroAppend.append(" AND TRUNC(fp.fechafactura)         <= to_date('"
                    + DateUtil.ToString.yyyyMMdd(RangoDeFechas.getHasta()) + "','yyyy-MM-dd') ");

            final ArrayOfFacturaType _return = new ArrayOfFacturaType();
            final List<FacturaType> _returnFacturas = new ArrayList<FacturaType>();

            final StringBuilder querySBuilder = new StringBuilder().append(" select  ")
                    // #1654
                    .append(" fp.tipocomprobante_id tipo_cpte, ").append(" fp.fechafactura fecha_cpte, ")
                    .append(" fp.fechavto fecha_acred_ctacte, ")
                    .append(" fp.letra letra_cpte, ").append(" fp.nrofacturasucursal sucursal_cpte, ")
                    .append(" fp.nrofacturanumero numero_cpte, ")
                    .append(" decode(sign(fp.importeiva),-1,'-','+') signo_iva_base, ")
                    .append(" abs(fp.baseimponible) iva_base, ").append(" ' ' signo_iva_no_gravado, ")
                    .append(" 0 iva_no_gravado, ").append(" decode(sign(fp.importeiva),-1,'-','+') signo_iva_total, ")
                    .append(" abs(fp.importeiva) importeiva, ")
                    // #1654
                    .append("decode(nvl(fp.totaldebe,0),0,decode(sign(fp.totalhaber),-1,'-','+'),decode(sign(fp.totaldebe),-1,'-','+')) signo_importe, ")
                    .append("decode(nvl(fp.totaldebe,0),0,abs(fp.totalhaber),abs(fp.totaldebe)) importe, ")
                    // .append(" decode(sign(mov.importe),-1,'-','+')
                    // signo_importe, ")
                    // .append(" abs(mov.importe) importe, ")

                    .append(" fp.monedafactura_id moneda, ")
                    .append(" decode(fp.cuentacliente_id,c.vendedor_id,'VE',c.comprador_id,'CO',' ') condicion_cliente, ")
                    .append(" decode(fp.cuentacliente_id,c.vendedor_id,c.nrocontratovendedor,c.comprador_id,c.nrocontratocomprador,' ') cto_cliente, ")
                    .append(" decode(sign(fp.cantidadfacturada),-1,'-','+') signo_kgs_fact, ")
                    .append(" abs(fp.cantidadfacturada) kgs_facturados, ").append(" c.preciomercaderia precio_cto, ")
                    .append(" '30-51282309-9' cuit_corredor, ")
                    .append(" decode(fp.cuentacliente_id,c.vendedor_id,ctecomp.cuit,c.comprador_id,ctevend.cuit,' ') cuit_contra, ")
                    .append(" decode(fp.estadocobranza,'P','Pendiente','C','Cobrado','A','Anulado',' ') estado, ")
                    .append(" fp.fechavto fecha_vto_cpte, ")
                    .append(" decode(sign(fp.importeacuenta),-1,'-','+') signo_importe_a_cta, ")
                    .append(" abs(fp.importeacuenta) importe_a_cta, ").append(" fp.nrocai cai, ")
                    .append(" fp.fechavencimientocai fecha_vto_cai, ").append(" fp.cae cae, ")
                    .append(" fp.fechavtocae fecha_vto_cae, ").append(" fp.coefacturapapel coefacturapapel, ")
                    // #1589
                    .append(" fp.id factura_id, ").append(" cotiza.cotizacion cotizacion, ")
                    .append(" fp.facturador facturador, ").append(" fp.leyendamat leyenda ")
                    // #1654
                    .append(" from factura_producto fp ")
                    // .append(" from ctacte_mov mov ")
                    // .append(" inner join factura_producto fp on (fp.id =
                    // mov.ref_id) ")
                    .append(" left outer join contrato c on (c.id = fp.contrato_id) ")
                    .append(" left outer join cuenta_cliente vend on (vend.id = c.vendedor_id) ")
                    .append(" left outer join cuenta_cliente comp on (comp.id = c.comprador_id) ")
                    .append(" left outer join cliente ctevend on (ctevend.id = vend.cliente_id) ")
                    .append(" left outer join cliente ctecomp on (ctecomp.id = comp.cliente_id) ")
                    // #1589
                    .append(" left outer join (").append("		select factura_id, ")
                    .append("		to_number(replace(substr(leyenda,instr(leyenda,'$',-1,1)+1),',','.')) cotizacion ")
                    .append("		from detalle_factura  ")
                    .append("		where leyenda like 'T.C. BANCO DE LA NACION ARGENTINA%' or ")
                    .append("		leyenda LIKE 'T.C. MERCADO A TERMINO DE BS.AS.%' or ")
                    .append("       leyenda LIKE 'T.C. MERCADO A TERMINO DE ROSARIO%' or ")
                    .append("	    leyenda LIKE 'T.C. BANCO CENTRAL DE LA REPUBLICA ARGENTINA' ")
                    .append(" ) cotiza on (cotiza.factura_id = fp.id) ")
                    // #1654
                    .append(" where fp.fechabaja is null ")
                    // .append(" where mov.ref_entity = 'FacturaProducto' ")
                    // .append(" and mov.fechabaja is null ")

                    .append(" and fp.facturador in ").append(" ( 'Factura de Tercero', ")
                    .append(" 'FacturacionRegistros-C', ").append(" 'FacturacionParcialesMAT- Comision Comprador', ")
                    .append(" 'AnuladorFacturaFinalMAT', ").append(" 'MercadoParcial', ")
                    .append(" 'FacturacionRegistros-OA', ").append(" 'FacturacionParcialesMAT', ")
                    .append(" 'FacturacionRegistros-F', ").append(" 'FacturacionRegistros-V', ")
                    .append(" 'AnuladorFacturaComision', ").append(" 'FacturacionRegistros-T', ")
                    .append(" 'AnuladorFacturaRegistros', ").append(" 'AnuladorDiferenciasGarantias', ")
                    .append(" 'MAT Ingreso Manual', ").append(" 'FacturacionRegistros-TC', ").append(" 'FinalMAT', ")
                    .append(" 'FacturacionRegistros-CE', ").append(" 'FacturacionRegistros-AJ', ")
                    .append(" 'MercadoFinal', ").append(" 'FacturacionParcialesMAT - Comision Vendedor', ")
                    .append(" 'DiferenciasGarantías' ) ").append(" AND fp.cuentacliente_id in ")
                    .append(cuentas_cuentas).append(filtroAppend);

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            FacturaType _returnFacturaVal1 = new FacturaType();

                            // #1589
                            _returnFacturaVal1.setID(rset.getInt("factura_id"));
                            _returnFacturaVal1.setCOTIZACION(rset.getFloat("cotizacion"));

                            _returnFacturaVal1.setTIPO_CPTE(rset.getInt("TIPO_CPTE"));
                            _returnFacturaVal1.setFECHA_CPTE(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("FECHA_CPTE"))));
                            _returnFacturaVal1.setFECHA_ACRED_CTACTE(DateUtil.Converters.DateToFechaTimeType(
                                    DateUtil.Parsers.yyyyMMdd(rset.getString("FECHA_ACRED_CTACTE"))));
                            _returnFacturaVal1.setLETRA_CPTE(rset.getString("LETRA_CPTE"));
                            _returnFacturaVal1.setSUCURSAL_CPTE(rset.getString("SUCURSAL_CPTE"));
                            _returnFacturaVal1.setNUMERO_CPTE(rset.getString("NUMERO_CPTE"));
                            _returnFacturaVal1.setSIGNO_IVA_BASE(rset.getString("SIGNO_IVA_BASE"));
                            _returnFacturaVal1.setIVA_BASE(rset.getFloat("IVA_BASE"));
                            _returnFacturaVal1.setSIGNO_IVA_NO_GRAVADO(rset.getString("SIGNO_IVA_NO_GRAVADO"));
                            _returnFacturaVal1.setIVA_NO_GRAVADO(rset.getFloat("IVA_NO_GRAVADO"));
                            _returnFacturaVal1.setSIGNO_IVA_TOTAL(rset.getString("SIGNO_IVA_TOTAL"));
                            _returnFacturaVal1.setIMPORTEIVA(rset.getFloat("IMPORTEIVA"));
                            _returnFacturaVal1.setSIGNO_IMPORTE(rset.getString("SIGNO_IMPORTE"));
                            _returnFacturaVal1.setIMPORTE(rset.getFloat("IMPORTE"));
                            _returnFacturaVal1.setMONEDA(rset.getInt("MONEDA"));
                            _returnFacturaVal1.setCONDICION_CLIENTE(rset.getString("CONDICION_CLIENTE"));
                            _returnFacturaVal1.setCTO_CLIENTE(rset.getString("CTO_CLIENTE"));
                            _returnFacturaVal1.setSIGNO_KGS_FACT(rset.getString("SIGNO_KGS_FACT"));
                            _returnFacturaVal1.setKGS_FACTURADOS(rset.getFloat("KGS_FACTURADOS"));
                            _returnFacturaVal1.setPRECIO_CTO(rset.getFloat("PRECIO_CTO"));
                            _returnFacturaVal1.setCUIT_CORREDOR(rset.getString("CUIT_CORREDOR"));
                            _returnFacturaVal1.setCUIT_CONTRA(rset.getString("CUIT_CONTRA"));
                            _returnFacturaVal1.setESTADO(rset.getString("ESTADO"));
                            _returnFacturaVal1.setFECHA_VTO_CPTE(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("FECHA_VTO_CPTE"))));
                            _returnFacturaVal1.setSIGNO_IMPORTE_A_CTA(rset.getString("SIGNO_IMPORTE_A_CTA"));
                            _returnFacturaVal1.setIMPORTE_A_CTA(rset.getFloat("IMPORTE_A_CTA"));
                            _returnFacturaVal1.setCAI(rset.getString("CAI"));
                            _returnFacturaVal1.setFECHA_VTO_CAI(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("FECHA_VTO_CAI"))));
                            _returnFacturaVal1.setCAE(rset.getString("CAE"));
                            _returnFacturaVal1.setFECHA_VTO_CAE(DateUtil.Converters
                                    .DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("FECHA_VTO_CAE"))));
                            _returnFacturaVal1.setCOEFACTURAPAPEL(rset.getString("COEFACTURAPAPEL"));

                            String facturador = rset.getString("FACTURADOR");
                            String leyenda = rset.getString("LEYENDA");

                            boolean esDolar = (_returnFacturaVal1.getMONEDA() == 1717) ? false : true;

                            ServiceFacturaDetalle fd = new ServiceFacturaDetalle();
                            ArrayOfFacturaDetalleType fdetalle = fd.obtenerFacturaDetalle(_returnFacturaVal1.getID(),
                                    facturador, leyenda, esDolar, _returnFacturaVal1.getCOTIZACION());

                            ServiceFacturaRegistro fr = new ServiceFacturaRegistro();
                            ArrayOfFacturaRegistroType fregistro = fr.obtenerFacturaRegistro(_returnFacturaVal1.getID(),
                                    facturador, leyenda);

                            _returnFacturaVal1.setDETALLE(fdetalle);
                            _returnFacturaVal1.setREGISTRO(fregistro);

                            _returnFacturas.add(_returnFacturaVal1);
                        } catch (ParseException e) {
                            throw new SQLException("No se puede determinar la fecha en el query.", e);
                        } catch (ZeniDBExeption ex) {
                            throw new SQLException(ex.getMessage(), ex);
                        }
                    }
                }
            };
            ZeniContextServer.getInstance().printErrorLog("ANTES DE EJECUTAR: ");
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            ZeniContextServer.getInstance().printErrorLog("TERMINO: ");
            _return.getFacturas().addAll(_returnFacturas);
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

    // #1506
    @Override
    public ArrayOfDetalleFacturaType obtenerDetalleFacturas(AuthType auth, ArrayOfId facturas)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerDetalleFacturas");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(facturas);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (facturas == null || facturas.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("los id de facturas no pueden ser nulo o vacias",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            String filtrofacturas = ZeniContextServer.VACIO;
            if (facturas != null && facturas.getIds().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < facturas.getIds().size(); i++) {
                    sb.append(",").append(facturas.getIds().get(i)).append("");
                }
                filtrofacturas = sb.deleteCharAt(0).insert(0, "(").append(") ").toString();
            }

            final ArrayOfDetalleFacturaType _return = new ArrayOfDetalleFacturaType();
            final List<DetalleFacturaType> _returnDetalleFacturas = new ArrayList<DetalleFacturaType>();
            final StringBuilder querySBuilder = new StringBuilder().append(" SELECT ").append(" ID, ")
                    .append(" ORDEN, ").append(" LEYENDA, ")
                    .append(" DECODE(SIGN(IMPORTECOMPRADOR),-1,'-','+') SIGNO_IMPORTE_COMPRADOR, ")
                    .append(" ABS(IMPORTECOMPRADOR) IMPORTE_COMPRADOR, ")
                    .append(" DECODE(SIGN(IMPORTEVENDEDOR),-1,'-','+') SIGNO_IMPORTE_VENDEDOR, ")
                    .append(" ABS(IMPORTEVENDEDOR) IMPORTE_VENDEDOR, ").append(" FACTURA_ID ")
                    .append(" FROM DETALLE_FACTURA ").append(" WHERE FACTURA_ID IN ").append(filtrofacturas)
                    .append(" AND FECHABAJA IS NULL ");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            DetalleFacturaType _returnDetalleFacturasVal1 = new DetalleFacturaType();
                            _returnDetalleFacturasVal1.setID(rset.getInt("ID"));
                            _returnDetalleFacturasVal1.setORDEN(rset.getInt("ORDEN"));
                            _returnDetalleFacturasVal1.setLEYENDA(rset.getString("LEYENDA"));
                            _returnDetalleFacturasVal1
                                    .setSIGNO_IMPORTE_COMPRADOR(rset.getString("SIGNO_IMPORTE_COMPRADOR"));
                            _returnDetalleFacturasVal1.setIMPORTE_COMPRADOR(rset.getFloat("IMPORTE_COMPRADOR"));
                            _returnDetalleFacturasVal1
                                    .setSIGNO_IMPORTE_VENDEDOR(rset.getString("SIGNO_IMPORTE_VENDEDOR"));
                            _returnDetalleFacturasVal1.setIMPORTE_VENDEDOR(rset.getFloat("IMPORTE_VENDEDOR"));
                            _returnDetalleFacturasVal1.setFACTURA_ID(rset.getInt("FACTURA_ID"));
                            _returnDetalleFacturas.add(_returnDetalleFacturasVal1);
                        } catch (Exception e) {
                            throw new SQLException("Error al ejecutar el query.", e);
                        }
                    }
                }
            };
            ZeniContextServer.getInstance().printErrorLog("ANTES DE EJECUTAR: ");
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
            ZeniContextServer.getInstance().printErrorLog("TERMINO: ");
            _return.getDetalleFacturas().addAll(_returnDetalleFacturas);
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
    public SINOType actualizarNroReferenciaOperacionTermino(AuthType auth, ArrayOfReferenciaType listaReferencia)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation actualizarNroReferenciaOperacionTermino");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(listaReferencia);
        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
            if (listaReferencia == null) {
                throw new ZeniServerExeption("La Lista de referencia no puede ser nulo o vacio",
                        FAULTCONSTANTS.ERROR_ERRONEO);
            }

            SINOType _return = SINOType.NO;

            for (ReferenciaType ref : listaReferencia.getMercater()) {
                StringBuilder update = new StringBuilder().append("UPDATE MAT_REGISTRO ");
                update.append(" SET NROSUBCTAREG = ");
                update.append(ref.getNroReferencia());
                update.append(" WHERE ID = ");
                update.append(ref.getIdOperacion());

                ZeniQueryExcecutor.excecuteUpdate(update.toString());
                _return = SINOType.SI;
            }

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
    public FileType descargaExportacionACamaradeBuenosAires(AuthType auth, CuentaType cuenta, RangoFechaType fechas)
            throws FaultType_Exception, ZeniServerExeption {
        ZeniContextServer.getInstance().printInfoLog("Executing operation descargaExportacionACamaradeBuenosAires");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(fechas);

        if (fechas == null || DateUtil.ToString.ddMMyyyy(fechas.getDesde()) == null) {
            throw new ZeniServerExeption("La fecha rangoFecha desde no puede ser nulo o vacio",
                    FAULTCONSTANTS.ERROR_ERRONEO);
        }
        if (fechas == null || DateUtil.ToString.ddMMyyyy(fechas.getHasta()) == null) {
            throw new ZeniServerExeption("La fecha rangoFecha hasta no puede ser nulo o vacio",
                    FAULTCONSTANTS.ERROR_ERRONEO);
        }

        try {
            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            final StringBuffer queryExport = new StringBuffer("SELECT ARCH.ID, ARCH.ARCHIVO FROM ( ");

            queryExport.append(" select A.ID, RPAD('CABC0',47,' ')||  ");
            queryExport.append(" LPAD(NVL(A.NROCERTIFICADOANALISIS,0),6,'0')|| ");
            queryExport.append(" LPAD(TO_CHAR(A.FECHAALTA,'DDMMYY'),6,'0')|| ");
            queryExport.append(" LPAD(TO_CHAR(A.FECHAANALISIS,'DDMMYY'),6,'0')|| ");
            queryExport.append(" LPAD(TO_CHAR(TRUNC(E.CANTIDADENTREGA*100)),7,'0')|| ");
            queryExport.append(" LPAD(TO_CHAR(TRUNC(A.HONORARIOS*100)),10,'0')|| ");
            queryExport.append(" DECODE(NVL(A.CERTIFICADOORIGINAL_ID,0),0,0,1)||'00' ARCHIVO ");
            queryExport.append(" from ENTREGA E ");
            queryExport.append(" INNER JOIN ENTREGA_PARTIDA EP ON (EP.ENTREGA_ID=E.ID) ");
            queryExport.append(" INNER JOIN CONTRATO C ON (C.ID=EP.CONTRATO_ID) ");
            queryExport.append(" INNER JOIN ANALISIS_MUESTRA A ON (A.ID=E.ANALISISMUESTRA_ID) ");
            queryExport.append(" UNION ");
            queryExport.append(
                    " SELECT A.ID, RPAD('CABC2',41,' ') || LPAD(R.CODIGO,3,'0')|| LPAD(TRUNC(A.RESULTADOANALISIS*100),6,'0') ARCHIVO ");
            queryExport.append(" FROM ANALISIS_MUESTRA A ");
            queryExport.append(" INNER JOIN RUBRO_CALIDAD_EN_CAMARA R ON (A.PRODUCTO_ID=R.PRODUCTO_ID) ");
            queryExport.append(" UNION ");
            queryExport.append(" SELECT A.ID, RPAD('CABC4',71, ' ')|| LPAD(E.NROCARTADEPORTE,15,'0') ");
            queryExport.append(" FROM ENTREGA E ");
            queryExport.append(" INNER JOIN ANALISIS_MUESTRA A ON (A.ID=E.ANALISISMUESTRA_ID)) ARCH ");
            queryExport.append(" WHERE ARCH.ID  IN ( ");
            queryExport.append(" SELECT A.ID ");
            queryExport.append(" from ENTREGA E ");
            queryExport.append(" INNER JOIN ENTREGA_PARTIDA EP ON (EP.ENTREGA_ID=E.ID) ");
            queryExport.append(" INNER JOIN CONTRATO C ON (C.ID=EP.CONTRATO_ID) ");
            queryExport.append(" INNER JOIN ANALISIS_MUESTRA A ON (A.ID=E.ANALISISMUESTRA_ID) ");
            queryExport.append(
                    " where (C.VENDEDOR_ID = " + cuenta.getId() + " or C.COMPRADOR_ID = " + cuenta.getId() + ") ");
            queryExport.append(" and TRUNC(a.fechaalta) >= TO_DATE('" + DateUtil.ToString.yyyyMMdd(fechas.getDesde())
                    + "','yyyy-MM-dd') ");
            queryExport.append(" and TRUNC(a.fechahasta) >= TO_DATE('" + DateUtil.ToString.yyyyMMdd(fechas.getHasta())
                    + "','yyyy-MM-dd') ");
            queryExport.append(" ) ORDER BY ARCH.ID,ARCH.ARCHIVO ");

            final StringBuilder lineasArchivo = new StringBuilder();
            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        lineasArchivo.append(rset.getString("linea"));
                        lineasArchivo.append("\r\n");
                    }
                }
            };
            ZeniQueryExcecutor.excecuteSelect(queryExport.toString(), resb);
            final FileType _return = new FileType();
            final String date = DateUtil.ToString.yyyyMMddSimple(new Date());
            final String name = "exportacionCamaraBuenosAires_" + date;
            final String ext = "txt";
            _return.setNombreConExtension(name + "." + ext);
            _return.setNombre(name);
            _return.setExtension(ext);
            _return.setData(lineasArchivo.toString().getBytes());
            _return.setTamanio(lineasArchivo.toString().length());
            _return.setId(date);
            _return.setMessage(name);
            _return.setDescription(name);
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

    // #1666
    @Override
    public ArrayOfAdministracionGarantiaType obtenerAdministracionGarantia(AuthType auth, ArrayOfIdCuenta Cuentas,
            ArrayOfMercadoType Mercados, ArrayOfProductoMATType Productos, MonedaType Moneda)
            throws FaultType_Exception {

        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerAdministracionGarantia");
        ZeniContextServer.getInstance().printVerboLog(auth);
        ZeniContextServer.getInstance().printVerboLog(Cuentas);
        ZeniContextServer.getInstance().printVerboLog(Mercados);
        ZeniContextServer.getInstance().printVerboLog(Productos);
        ZeniContextServer.getInstance().printVerboLog(Moneda);

        try {

            final MessageContext mc = wsContext.getMessageContext();
            final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
            AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

            if (Cuentas == null || Cuentas.equals(ZeniContextServer.VACIO)) {
                throw new ZeniServerExeption("la cuentas no pueden ser nulo o vacias", FAULTCONSTANTS.ERROR_ERRONEO);
            }

            // Cuentas
            String cuentas_cuentas = ZeniContextServer.VACIO;
            if (Cuentas != null && Cuentas.getCuentas() != null && Cuentas.getCuentas().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < Cuentas.getCuentas().size(); i++) {
                    sb.append(",").append(Cuentas.getCuentas().get(i)).append("");
                }
                cuentas_cuentas = sb.deleteCharAt(0).insert(0, "(").append(") ").toString();
            }

            // Mercados
            String listaDeMercad = ZeniContextServer.VACIO;
            if (Mercados != null && Mercados.getMercados() != null && Mercados.getMercados().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < Mercados.getMercados().size(); i++) {
                    sb.append(",'").append(Mercados.getMercados().get(i).getId()).append("'");
                }
                listaDeMercad = sb.deleteCharAt(0).insert(0, " and mg.id in (").append(") ").toString();
            }

            // Productos
            String listaDeProductos = ZeniContextServer.VACIO;
            if (Productos != null && Productos.getProductos() != null && Productos.getProductos().size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < Productos.getProductos().size(); i++) {
                    sb.append(",'").append(Productos.getProductos().get(i).getId()).append("'");
                }
                listaDeProductos = sb.deleteCharAt(0).insert(0, " and mp.id in (").append(") ").toString();
            }

            // Moneda
            String listaDeMoneda = ZeniContextServer.VACIO;
            if (Moneda != null && Moneda.getId() != null) {
                listaDeMoneda = " and mong.id ='" + Moneda.getId() + "' ";
            }

            final ArrayOfAdministracionGarantiaType _return = new ArrayOfAdministracionGarantiaType();
            final List<AdministracionGarantiaType> _administra = new ArrayList<AdministracionGarantiaType>();
            final StringBuilder querySBuilder = new StringBuilder().append("SELECT ")
                    .append(" ma.id,cc.id cuentaclienteid, cc.nrocuenta nrocuenta, cc.denominacioncuenta denominacioncuenta, ")
                    .append(" mp.nombre producto, ma.cantidad,ma.precio,ma.aforo, mg.NOMBRE mercado, mong.NOMBRE moneda,mong.simbolo simbolo, mong.id monedaID,")
                    .append(" ma.cantidad*ma.precio*ma.aforo valor, ")
                    .append(" cc.requisitogarantiasrd, mg.id mercadoID ").append(" FROM mat_aval ma ")
                    .append(" inner join cuenta_cliente cc on (cc.id = ma.cuentacliente_id) ")
                    .append(" left outer join mat_producto mp on (mp.id = ma.matproducto_id) ")
                    .append(" left outer join mercado mg on (mg.id = ma.mercadoengarantia_id) ")
                    .append(" left outer join moneda mong on (mong.id = ma.monedadegarantia_id) ")
                    .append(" left outer join moneda mon on (mon.id = ma.moneda_id) ")
                    .append(" where ma.fechabaja is null ").append(" and cc.id in ").append(cuentas_cuentas).append(" ")
                    .append(listaDeMercad).append(listaDeProductos).append(listaDeMoneda);

            // #1666
            final StringBuilder queryUltimaLiq = new StringBuilder()
                    .append(" SELECT coalesce(m.acumuladomataval,0) ultima FROM Mat_Dif_O_Gtia_A_Liquidar m ")
                    .append(" INNER JOIN workflow_state ws ON(ws.id = m.state_id)  ")
                    .append(" INNER JOIN workflow_definition wd ON (wd.id = ws.workflowdefinition_id and wd.estadoActual = 'FA' ) ")
                    .append(" WHERE m.fecha = (SELECT max(mm.fecha) FROM Mat_Dif_O_Gtia_A_Liquidar mm ")
                    .append(" INNER JOIN workflow_state ws1 ON(ws1.id = mm.state_id)  ")
                    .append(" INNER JOIN workflow_definition wd1 ON (wd1.id = ws1.workflowdefinition_id and wd1.estadoActual = 'FA' ) ")
                    .append(" WHERE mm.cuentaCliente_id = m.cuentaCliente_id ")
                    .append(" AND mm.mercado_id = m.mercado_id ").append(" AND mm.tipo = m.tipo ")
                    .append(" AND mm.moneda_id = m.moneda_id ").append(" AND mm.monedaOrigen = m.monedaOrigen ")
                    .append(" AND mm.fechaBaja is NULL) ").append(" AND m.fechaBaja is NULL ")
                    .append(" AND m.tipo = 'G'  ").append(" and m.cuentacliente_id = " + Cuentas.getCuentas().get(0))
                    .append(" ORDER BY m.fecha desc ");

            final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {
                    while (rset.next()) {
                        try {
                            AdministracionGarantiaType _returnAGVal1 = new AdministracionGarantiaType();
                            ZeniContextServer.getInstance().printErrorLog("EJECUTANDO: " + rset.getInt("id"));
                            _returnAGVal1.setId(rset.getInt("id"));
                            _returnAGVal1.setCuentaclienteid(rset.getInt("cuentaclienteid"));
                            _returnAGVal1.setNrocuenta(rset.getString("nrocuenta"));
                            _returnAGVal1.setDenominacioncuenta(rset.getString("denominacioncuenta"));
                            _returnAGVal1.setProducto(rset.getString("producto"));
                            _returnAGVal1.setCantidad(rset.getInt("cantidad"));
                            _returnAGVal1.setPrecio(rset.getFloat("precio"));
                            _returnAGVal1.setAforo(rset.getFloat("aforo"));
                            _returnAGVal1.setMercado(rset.getString("mercado"));
                            _returnAGVal1.setMoneda(rset.getString("moneda"));
                            _returnAGVal1.setSimbolo(rset.getString("simbolo"));
                            _returnAGVal1.setValor(rset.getFloat("valor"));
                            _returnAGVal1.setRequisitogarantia(
                                    ZeniHelper.obtenerRequisitoGtias("" + _returnAGVal1.getCuentaclienteid(),
                                            rset.getString("mercadoID"), rset.getString("monedaID")));

                            _administra.add(_returnAGVal1);
                        } catch (Exception e) {
                            throw new SQLException(e.getMessage(), e);
                        }
                    }
                }
            };

            final List<Float> ult = new ArrayList<Float>();

            final ResulsetObjectBuilder resultimaliq = new ResulsetObjectBuilder() {
                @Override
                public void thisIsTheResulset(ResultSet rset) throws SQLException {

                    if (rset.next()) {
                        ult.add(rset.getFloat("ultima"));
                    } else {
                        ult.add(new Float(0));
                    }
                }
            };

            ZeniContextServer.getInstance().printErrorLog("ANTES DE EJECUTAR: ");
            ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);

            ZeniQueryExcecutor.excecuteSelect(queryUltimaLiq.toString(), resultimaliq);

            for (AdministracionGarantiaType t : _administra) {
                t.setUltimaliq(ult.get(0));
            }

            ZeniContextServer.getInstance().printErrorLog("TERMINO: ");
            _return.getAdministracionGarantia().addAll(_administra);
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
     * #1808
     */
    @Override
    public ArrayOfCalidadEntregas obtenerCalidadEntregas(AuthType auth, String cuenta, RangoFechaType rangoFecha,
            String producto) throws FaultType_Exception {
        return ZeniHelper.obtenerCalidadEntregas(auth, wsContext, cuenta, rangoFecha, producto);
    }

    @Override
    public int addSolicitudDeInformacion(AuthType auth, SolicitudInformacionReg solicitud)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation addSolicitudDeInformacion");
        return ZeniHelper.addSolicitudDeInformacion(auth, wsContext, solicitud);
    }

    @Override
    public ArrayOfSolicitudInformacion obtenerSolicitudInformacion(AuthType auth, String cuentaId) throws FaultType_Exception {
        return ZeniHelper.obtenerSolicitudInformacion(auth, cuentaId, wsContext);

    }

    @Override
    public int editSolicitudDeInformacion(AuthType auth, SolicitudInformacionReg solicitud)
            throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation editSolicitudDeInformacion");
        ZeniContextServer.getInstance().printInfoLog("SolicitudInformacionReg["
                + (solicitud != null ? solicitud.toString() : "null") + "]  ");
        return ZeniHelper.editSolicitudDeInformacion(auth, wsContext, solicitud);
    }

    @Override
    public int deleteSolicitudDeInformacion(AuthType auth, String id) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation deleteSolicitudDeInformacion");
        ZeniContextServer.getInstance().printInfoLog("id[" + id + "]");
        return ZeniHelper.deleteSolicitudDeInformacion(auth, wsContext, id);
    }

    @Override
    public int solicitudZeniApi(AuthType auth, String email, String cuenta, String nombre) throws FaultType_Exception {

        ZeniContextServer.getInstance().printInfoLog("Executing operation solicitudDeInformacion");
        ZeniContextServer.getInstance()
                .printInfoLog(" email[" + email + "] cuenta[" + cuenta + "] nomnre[" + nombre + "]");

        return ZeniHelper.solicitudZeniApi(auth, wsContext, email, nombre, cuenta);
    }

    @Override
    public int solicitudDeInformacion(AuthType auth, String email, String bodytitle, String body, ArrayOfString table,
            String cuenta) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation solicitudDeInformacion");
        ZeniContextServer.getInstance()
                .printInfoLog(" email[" + email + "] bodytitle[" + bodytitle + "] body[" + body + "] table[" + (table != null ? table.getIds() : null) + "]");

        return ZeniHelper.solicitudDeInformacion(auth, wsContext, email, body, cuenta, (table != null ? table.getIds() : null), bodytitle);
    }

    @Override
    public int recuperaPassword(String email, String usuario, String nombre) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation recuperaPassword");
        ZeniContextServer.getInstance()
                .printInfoLog(" email[" + email + "] usuario[" + usuario + "] nombre[" + nombre + "]");

        return ZeniHelper.recuperaPassword(wsContext, email, usuario, nombre);
    }

    @Override
    public ArrayOfSectorReclamos obtenerSectorReclamos(AuthType auth) throws FaultType_Exception {
        return ZeniHelper.obtenerSectorReclamos(wsContext, auth);
    }

    @Override
    public int solicitarReclamo(AuthType auth, String email, String asunto, String descripcion, String sector)
            throws FaultType_Exception {
        return ZeniHelper.solicitarReclamo(wsContext, auth, email, asunto, descripcion, sector);
    }

    @Override
    public String obtenerContratoId(AuthType auth, String numeroContrato) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerContratoId numeroContrato[" + numeroContrato + "]");
        return ZeniHelper.obtenerContratoId(wsContext, auth, numeroContrato);
    }

    @Override
    public ArrayOfCartaPorteReg obtenerCartaPorteById(AuthType auth, String cartaPorte) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCartaPorteById cartaPorte[" + cartaPorte + "]");
        return ZeniHelper.obtenerCartaPorteId(wsContext, auth, cartaPorte);
    }   
    
    
    @Override
    public ArrayOfCartaPorteReg obtenerCTGById(AuthType auth, String cartaPorte) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCTGById cartaPorte[" + cartaPorte + "]");
        return ZeniHelper.obtenerCTGById(wsContext, auth, cartaPorte);
    }
    

    @Override
    public String validarCartaPorte(AuthType auth, String cartaPorte) throws FaultType_Exception {
        ZeniContextServer.getInstance().printInfoLog("Executing operation validarCartaPorte cartaPorte[" + cartaPorte + "]");
        return ZeniHelper.validarCartaPorte(wsContext, auth, cartaPorte);
    }

    @Override
    public ArrayOfBoletosCartaOferta obtenerBoletosCartaOferta(AuthType auth, String cuentaId, String nroContrato,
            RangoFechaType rangoDeFechas) throws FaultType_Exception {
        return ZeniHelper.obtenerBoletosCartaOferta(wsContext, auth, cuentaId, nroContrato, rangoDeFechas);
    }

    @Override
    public FileType descargarBoletoCartaOferta(AuthType auth, String comprobante, EnumBoletoCartaPorte tipo)
            throws FaultType_Exception {
        return ZeniHelper.descargarBoletoCartaOferta(wsContext, auth, comprobante, tipo);
    }

    @Override
    public ArrayOfContratoType obtenerContratos(AuthType at, ArrayOfIdCuenta aoic, String string, String string1, String string2, EnumIncluirType eit, String string3, EnumOrdenamientoContratosType eoct, Object o, ArrayOfProductoType aopt, String string4, String string5, String string6, String string7, String string8, String string9, EnumCompradorVendedorType ecvt, String string10, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
