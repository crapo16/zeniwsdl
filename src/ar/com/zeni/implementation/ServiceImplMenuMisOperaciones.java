package ar.com.zeni.implementation;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;

import ar.com.zeni.common.Condicion;
import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.DateUtil.Formats;
import ar.com.zeni.common.FAULTCONSTANTS;
import ar.com.zeni.common.ZeniConstants;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.ZeniHelper;
import ar.com.zeni.common.exceptions.ZeniAuthTypeInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.common.exceptions.ZeniHashInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniIPDifiereExeption;
import ar.com.zeni.common.exceptions.ZeniServerExeption;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.security.AuthNHashUtil;
import ar.com.zeni.zeniwsdl.ArrayOfCobranzaType;
import ar.com.zeni.zeniwsdl.ArrayOfComprobanteCuentaCorrienteType;
import ar.com.zeni.zeniwsdl.ArrayOfContratoType;
import ar.com.zeni.zeniwsdl.ArrayOfDestinoMATType;
import ar.com.zeni.zeniwsdl.ArrayOfIdCuenta;
import ar.com.zeni.zeniwsdl.ArrayOfMercadoType;
import ar.com.zeni.zeniwsdl.ArrayOfNovedades;
import ar.com.zeni.zeniwsdl.ArrayOfOMatComisionType;
import ar.com.zeni.zeniwsdl.ArrayOfOperacionFinancieraType;
import ar.com.zeni.zeniwsdl.ArrayOfProductoMATType;
import ar.com.zeni.zeniwsdl.ArrayOfProductoType;
import ar.com.zeni.zeniwsdl.ArrayOfResumenCuentaCorrienteType;
import ar.com.zeni.zeniwsdl.ArrayOfTenenciaValorizada;
import ar.com.zeni.zeniwsdl.ArrayOfTipoComisionType;
import ar.com.zeni.zeniwsdl.AuthType;
import ar.com.zeni.zeniwsdl.ClienteType;
import ar.com.zeni.zeniwsdl.CobranzaType;
import ar.com.zeni.zeniwsdl.ComprobanteCuentaCorrienteType;
import ar.com.zeni.zeniwsdl.ContratoType;
import ar.com.zeni.zeniwsdl.CuentaType;
import ar.com.zeni.zeniwsdl.EnumCompraVentaType;
import ar.com.zeni.zeniwsdl.EnumCompradorVendedorType;
import ar.com.zeni.zeniwsdl.EnumDeMovimientoType;
import ar.com.zeni.zeniwsdl.EnumEstadoType;
import ar.com.zeni.zeniwsdl.EnumIncluirType;
import ar.com.zeni.zeniwsdl.EnumOperacionesType;
import ar.com.zeni.zeniwsdl.EnumSucursalType;
import ar.com.zeni.zeniwsdl.EnumTipoConsultaType;
import ar.com.zeni.zeniwsdl.EnumTipoOperacionMatType;
import ar.com.zeni.zeniwsdl.EnumTipoOperacionType;
import ar.com.zeni.zeniwsdl.FaultType;
import ar.com.zeni.zeniwsdl.FaultType_Exception;
import ar.com.zeni.zeniwsdl.FechaTimeType;
import ar.com.zeni.zeniwsdl.FileType;
import ar.com.zeni.zeniwsdl.InformacionCuentaCorrienteType;
import ar.com.zeni.zeniwsdl.MatComisionType;
import ar.com.zeni.zeniwsdl.MonedaType;
import ar.com.zeni.zeniwsdl.NovedadType;
import ar.com.zeni.zeniwsdl.OperacionFinancieraType;
import ar.com.zeni.zeniwsdl.RangoFechaType;
import ar.com.zeni.zeniwsdl.ResumenCuentaCorrienteType;
import ar.com.zeni.zeniwsdl.SINOType;
import ar.com.zeni.zeniwsdl.TenenciaVal;

public abstract class ServiceImplMenuMisOperaciones extends ServiceImplMenuInformacion {

	/*
	 * (non-Javadoc)
	 *
	 * @see ZeniWSDL#obtenerContratos(AuthType auth ,)java.lang.String nroZeni
	 * ,)java.lang.String nroContratoComprador ,)java.lang.String
	 * nroContratoVendedor ,)EnumIncluirType incluir ,)java.lang.String precio
	 * ,)EnumOrdenamientoContratosType ordenamiento ,)RangoFechaType rangoFechas
	 * ,)ArrayOfProductoType productos ,)java.lang.String vendedorId
	 * ,)java.lang.String compradorId ,)java.lang.String calidadId
	 * ,)java.lang.String operatoriaId ,)java.lang.String campaniaId
	 * ,)java.lang.String condDePagoId )*
	 */
	public ArrayOfContratoType obtenerContratos(AuthType auth, ArrayOfIdCuenta cuentas, String nroZeni,
			String nroContratoComprador, String nroContratoVendedor, EnumIncluirType incluir, java.lang.String precio,
			ar.com.zeni.zeniwsdl.EnumOrdenamientoContratosType ordenamiento, RangoFechaType rangoFechas,
			ArrayOfProductoType productos, java.lang.String vendedorId, java.lang.String compradorId,
			java.lang.String calidadId, java.lang.String operatoriaId, java.lang.String campaniaId,
			java.lang.String condDePagoId, EnumCompradorVendedorType compraVende, java.lang.String cartaDePorte,
			RangoFechaType rangoFechasEntrega) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerContratos");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(nroZeni);
		ZeniContextServer.getInstance().printVerboLog(nroContratoComprador);
		ZeniContextServer.getInstance().printVerboLog(nroContratoVendedor);
		ZeniContextServer.getInstance().printVerboLog(incluir);
		ZeniContextServer.getInstance().printVerboLog(precio);
		ZeniContextServer.getInstance().printVerboLog(ordenamiento);
		ZeniContextServer.getInstance().printVerboLog(rangoFechas);
		ZeniContextServer.getInstance().printVerboLog(productos);
		ZeniContextServer.getInstance().printVerboLog(vendedorId);
		ZeniContextServer.getInstance().printVerboLog(compradorId);
		ZeniContextServer.getInstance().printVerboLog(calidadId);
		ZeniContextServer.getInstance().printVerboLog(operatoriaId);
		ZeniContextServer.getInstance().printVerboLog(campaniaId);
		ZeniContextServer.getInstance().printVerboLog(condDePagoId);
		ZeniContextServer.getInstance().printVerboLog(compraVende);
		ZeniContextServer.getInstance().printVerboLog(cartaDePorte);
		ZeniContextServer.getInstance().printVerboLog(rangoFechasEntrega);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			String listaDeProductos = ZeniContextServer.VACIO;


			if (cuentas == null || cuentas.getCuentas() == null || cuentas.getCuentas().size() <= 0 ) {
				throw new ZeniServerExeption("Por favor seleccione alguna cuenta. ", FAULTCONSTANTS.ERROR_ERRONEO);
			}

			if (productos != null && productos.getProductos() != null && productos.getProductos().size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < productos.getProductos().size(); i++) {
					sb.append(",'").append(productos.getProductos().get(i).getId()).append("'");
				}
				listaDeProductos = sb.deleteCharAt(0).insert(0, " and c.producto_id in (").append(") ").toString();
			}
			String vendeCompra = ZeniContextServer.VACIO;
			if (vendedorId != null && !vendedorId.equals(ZeniContextServer.VACIO)
					|| compradorId != null && !compradorId.equals(ZeniContextServer.VACIO)) {
				if (vendedorId != null && !vendedorId.equals(ZeniContextServer.VACIO) && compradorId != null
						&& !compradorId.equals(ZeniContextServer.VACIO)) {
					vendeCompra = " and ( c.vendedor_id=" + vendedorId + " or c.comprador_id=" + compradorId + " ) ";
				} else if (compradorId != null && !compradorId.equals(ZeniContextServer.VACIO)) {
					vendeCompra = " and c.comprador_id=" + compradorId + " ";
				} else {
					vendeCompra = " and c.vendedor_id=" + vendedorId + " ";
				}
			}



			String cuentas_cuentas = ZeniContextServer.VACIO;
			if (cuentas != null && cuentas.getCuentas() != null && cuentas.getCuentas().size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < cuentas.getCuentas().size(); i++) {
					sb.append(",").append(cuentas.getCuentas().get(i)).append("");
				}
				cuentas_cuentas = sb.deleteCharAt(0).insert(0, "(").append(") ").toString();
			}
			String opcionesFiltro = listaDeProductos + vendeCompra
					+ (nroZeni != null && !nroZeni.equals(ZeniContextServer.VACIO)
							? " AND c.numerocontrato ='" + nroZeni + "'"
							: ZeniContextServer.VACIO)
					+ (nroContratoComprador != null && !nroContratoComprador.equals(ZeniContextServer.VACIO)
							? " AND c.nrocontratocomprador ='" + nroContratoComprador + "'"
							: ZeniContextServer.VACIO)
					+ (nroContratoVendedor != null && !nroContratoVendedor.equals(ZeniContextServer.VACIO)
							? " AND c.nrocontratovendedor ='" + nroContratoVendedor + "'"
							: ZeniContextServer.VACIO)
					+ (campaniaId != null && !campaniaId.equals(ZeniContextServer.VACIO)
							? " AND c.aniocosecha ='" + campaniaId + "'"
							: ZeniContextServer.VACIO)
					+ (operatoriaId != null && !operatoriaId.equals(ZeniContextServer.VACIO)
							? " AND c.operatoria ='" + operatoriaId + "'"
							: ZeniContextServer.VACIO)
					+ (calidadId != null && !calidadId.equals(ZeniContextServer.VACIO)
							? " AND c.calidad_id ='" + calidadId + "'"
							: ZeniContextServer.VACIO)
					+ ((rangoFechas != null && DateUtil.ToString.ddMMyyyy(rangoFechas.getDesde()) != null
							&& DateUtil.ToString.ddMMyyyy(rangoFechas.getHasta()) != null)
									? " AND c.FECHAOPERACION <= to_date('"
											+ DateUtil.ToString.ddMMyyyy(rangoFechas.getHasta()) + "','"
											+ Formats.ddMMyyyy + "') AND c.FECHAOPERACION >= to_date('"
											+ DateUtil.ToString.ddMMyyyy(rangoFechas.getDesde()) + "','"
											+ Formats.ddMMyyyy + "') "
									: ZeniContextServer.VACIO)
					// #1458
					+ ((incluir != null && (incluir.equals(EnumIncluirType.F_ENTREGAR) || incluir.equals("F_Entregar")
							|| incluir.toString().equals("FEntregar")))
									? " AND c.cumplido=0 "
									: (incluir.equals(EnumIncluirType.SL_FINAL) || incluir.equals("SL_Final")
											|| incluir.toString().equals("SLFinal")) ? " AND c.ultimafinal=0 "
													: ZeniContextServer.VACIO)
					+ ((cartaDePorte != null && !(cartaDePorte.equals(ZeniContextServer.VACIO)))
							? " and c.id in (select ep.contrato_id from entrega_partida ep inner join entrega e on (e.id=ep.entrega_id) and ep.fechabaja is null and e.fechabaja is null and e.nrocartadeporte='"
									+ cartaDePorte + "') "
							: ZeniContextServer.VACIO)
					// #1820
					+ ((rangoFechasEntrega != null && DateUtil.ToString.ddMMyyyy(rangoFechasEntrega.getDesde()) != null
							&& DateUtil.ToString.ddMMyyyy(rangoFechasEntrega.getHasta()) != null)
									? " AND (c.fechaentregahasta IS NULL OR (c.fechaentregahasta <= to_date('"
											+ DateUtil.ToString.ddMMyyyy(rangoFechasEntrega.getHasta()) + "','"
											+ Formats.ddMMyyyy
											+ "'))) AND ( c.fechaentregadesde IS NULL OR  ( c.fechaentregadesde >= to_date('"
											+ DateUtil.ToString.ddMMyyyy(rangoFechasEntrega.getDesde()) + "','"
											+ Formats.ddMMyyyy + "'))) "
									: ZeniContextServer.VACIO);

			if (precio != null && !precio.equals(ZeniContextServer.VACIO)) {
				try {
					if (Float.parseFloat(precio) > 0.0 || Float.parseFloat(precio) < 0.0) {
						opcionesFiltro += " AND c.preciomercaderia = " + precio + " ";
					}
				} catch (Exception e) {
				}
			}
			EnumCompradorVendedorType ecompraven = compraVende == null ? EnumCompradorVendedorType.AMBOS : compraVende;
			final StringBuilder querySBuilder = new StringBuilder().append("SELECT  ").append("filamento.cuenta, ")
					.append("filamento.id_cuenta, ").append("filamento.contrato_id, ")
					.append("filamento.numerocontrato, ").append("filamento.nrozeni, ")
					.append("filamento.contraparte, ").append("filamento.comprobante, ").append("filamento.cantidad, ")
					.append("filamento.precio, ").append("filamento.destino, ").append("filamento.kilosfacturados, ")
					.append("filamento.procedencia, ").append("filamento.catcod, ").append("filamento.catdesc, ")
					.append("filamento.opcod, ").append("filamento.opdesc, ").append("filamento.operatoria, ")
					.append("filamento.pac, ").append("filamento.entr, ").append("filamento.cumplido, ")
					.append("filamento.tte, ").append("filamento.fact, ").append("filamento.fij, ")
					.append("filamento.saldo, ").append("filamento.pdte, ")
					// #1531
					.append("DECODE(filamento.cobrodemercaderia,'F',filamento.afijar,0) afijar, ")
					.append("TO_CHAR(filamento.periododesde, 'yyyy-MM-dd') periododesde, ")
					.append("TO_CHAR(filamento.periodohasta, 'yyyy-MM-dd') periodohasta, ")
					.append("TO_CHAR(filamento.fechaentregadesde, 'yyyy-MM-dd') fechaentregadesde, ")
					.append("TO_CHAR(filamento.fechaentregahasta, 'yyyy-MM-dd') fechaentregahasta, ")
					.append("TO_CHAR(filamento.fechafijaciondesde, 'yyyy-MM-dd') fechafijaciondesde, ")
					.append("TO_CHAR(filamento.fechafijacionhasta, 'yyyy-MM-dd') fechafijacionhasta, ")
					.append("TO_CHAR(filamento.fa, 'yyyy-MM-dd') fa, ").append("filamento.moneda_id, ")
					.append("filamento.producto_id, ").append("filamento.productocodigo, ")
					.append("filamento.producto, ").append("filamento.nroCuenta, ").append("filamento.cosecha ")
					.append("FROM ( ");


			if (ecompraven.equals(EnumCompradorVendedorType.VENDEDOR)
					|| ecompraven.equals(EnumCompradorVendedorType.AMBOS)) {

				querySBuilder.append("SELECT ").append("cuenta.denominacioncuenta cuenta, ")
						.append("cuenta.id id_cuenta, ").append("c.id contrato_id, ").append("c.numerocontrato, ")
						.append("c.numerocontrato nrozeni, ").append("cc.denominacioncuenta contraparte, ")
						.append("c.nrocontratocomprador comprobante, ").append("c.cantidadpactada cantidad, ")
						.append("c.preciomercaderia precio, ")
						.append("c.cantidadpactada -NVL(ent.cantidadentregada,0) saldo, ")
						.append("c.fechaentregadesde, ").append("c.fechaentregahasta, ")
						.append("proc.nombre procedencia, ").append("dest.nombre destino, ")
						.append("NVL(cf.cantidadfacturada,0) kilosfacturados, ").append("cal.codigo catcod, ")
						.append("cal.nombre catdesc, ").append("c.fechaoperacion fa, ")
						// #1516
						.append("DECODE(c.cobrodemercaderia,'F','A Fijar','A Precio') opcod, ")
						// #1531
						.append(" c.cobrodemercaderia cobrodemercaderia, ")
						.append("NVL(c.monedaiva_id, NVL(c.MONEDAFACTURACIONMERCADERIA_ID, NVL(c.MONEDAPRECIOOLEICO_ID, '1717'))) moneda_id, ")
						.append("( select de.descripcion from " + ZeniContextServer.getInstance().getDBWebOwner()
								+ "NOESTA_OPERATORIACBRMER de where de.id = c.cobrodemercaderia) opdesc, ")
						.append("( select dd.descripcion from " + ZeniContextServer.getInstance().getDBWebOwner()
								+ "NOESTA_OPERATORIA dd where dd.id = c.operatoria) operatoria, ")
						.append("c.cantidadpactada pac, ").append("ent.cantidadentregada entr, ").append("c.cumplido, ")
						.append("c.fechaentregadesde periododesde, ").append("c.fechaentregahasta periodohasta, ")
						.append("tt.nombre tte, ").append("c.cantidadpactada -NVL(ent.cantidadentregada,0) pdte, ")
						.append("NVL(cf.cantidadfacturada,0) fact, ").append("NVL(fija.cantidad,0) fij, ")
						.append("NVL(ent.cantidadentregada,0) -NVL(fija.cantidad,0) afijar, ")
						.append("fija.fechadesde fechafijaciondesde, ").append("fija.fechahasta fechafijacionhasta, ")
						.append("p.id producto_id, ").append("p.nombre producto, ").append("p.codigo productocodigo, ")
						.append("c.aniocosecha cosecha, cuenta.nroCuenta ").append("FROM contrato c ")
						.append("INNER JOIN producto p ").append("ON (p.id=c.producto_id) ")
						.append("INNER JOIN cuenta_cliente cc ").append("ON (cc.id =c.comprador_id ")
						.append("AND cc.fechabaja IS NULL) ").append("INNER JOIN cuenta_cliente cuenta ")
						.append("ON (cuenta.id =c.vendedor_id ").append("AND cuenta.fechabaja IS NULL) ")
						.append("INNER JOIN ciudad PROC ").append("ON (proc.id=c.procedencia_id) ")
						.append("INNER JOIN ciudad dest ").append("ON (dest.id=c.destino_id) ")
						.append("LEFT OUTER JOIN calidad cal ").append("ON (cal.id=c.calidad_id) ")
						.append("LEFT OUTER JOIN tipo_transporte tt ").append("ON (tt.id=c.tipotransporte_id) ")
						.append("INNER JOIN producto p ").append("ON (p.id=c.producto_id) ").append("LEFT OUTER JOIN ")
						.append("(SELECT ep.contrato_id, ").append("SUM(ep.cantidad) cantidadentregada ")
						// #2063
						.append("FROM entrega_partida ep ")
						.append("INNER JOIN WORKFLOW_STATE ws ON (ws.id = ep.state_id) ")
						.append("INNER JOIN WORKFLOW_DEFINITION wd ON (wd.id = ws.workflowDefinition_id) ")
						.append("WHERE ep.fechabaja IS NULL AND wd.estadoActual NOT IN ('N') ")
						// #2063
						.append("GROUP BY contrato_id ").append(") ent ").append("ON (ent.contrato_id=c.id) ")
						.append("LEFT OUTER JOIN ").append("(SELECT fp.contrato_id, ")
						.append("SUM(fp.cantidadfacturada) cantidadfacturada ").append("FROM factura_producto fp ")
						.append("INNER JOIN tipo_comprobante tc ").append("ON (tc.id=fp.tipocomprobante_id) ")
						.append("WHERE upper(fp.facturador) NOT LIKE '%FINAL%' ")
						.append("AND tc.abreviatura NOT IN ('FA','ND','NC','LF','BF') ")
						.append("AND (tc.abreviatura <> 'FC' ").append("OR fp.descontarCantidadFacturada = 1) ")
						.append("AND fp.anulador_id IS NULL ").append("AND fp.anulada_id IS NULL ")
						.append("AND fp.fechabaja IS NULL ").append("GROUP BY fp.contrato_id ")
						.append(") cf ON (cf.contrato_id=c.id) ").append("LEFT OUTER JOIN ")
						.append("(SELECT gf.contrato_id, ").append("SUM(f.cantidadafijar) cantidad, ")
						.append("MAX(f.fechafijacion) fechahasta, ").append("MIN(f.fechafijacion) fechadesde ")
						.append("FROM grupo_fijaciones gf ").append("INNER JOIN fijacion f ")
						.append("ON (f.grupofijaciones_id = gf.id ").append("AND f.fechabaja IS NULL) ")

						.append("INNER JOIN WORKFLOW_STATE WSF ").append("ON (WSF.ID = F.STATE_ID) ")
						.append("INNER JOIN WORKFLOW_DEFINITION WDF ").append("ON (WSF.WORKFLOWDEFINITION_ID = WDF.ID ")
						.append("AND WDF.ESTADOACTUAL <> 'A') ")

						.append("WHERE gf.fechabaja IS NULL ").append("GROUP BY gf.contrato_id ")
						.append(") fija ON (fija.contrato_id=c.id) ").append("WHERE c.fechabaja IS NULL ")
						.append("AND c.vendedor_id            in ").append(cuentas_cuentas).append(opcionesFiltro);

//						if (ecompraven.equals(EnumCompradorVendedorType.AMBOS))
//							querySBuilder.append("AND c.vendedor_id            in ").append(cuentas_cuentas).append(opcionesFiltro);
//
//						if (ecompraven.equals(EnumCompradorVendedorType.VENDEDOR))
//							querySBuilder.append("AND c.comprador_id            in ").append(cuentas_cuentas).append(opcionesFiltro);


			}

			if (ecompraven.equals(EnumCompradorVendedorType.AMBOS)) {
				querySBuilder.append(" UNION ");
			}



			if (ecompraven.equals(EnumCompradorVendedorType.COMPRADOR)
					|| ecompraven.equals(EnumCompradorVendedorType.AMBOS)) {

				querySBuilder.append("SELECT ").append("cuenta.denominacioncuenta cuenta, ")
						.append("cuenta.id id_cuenta , ").append("c.id contrato_id, ").append("c.numerocontrato, ")
						.append("c.numerocontrato nrozeni, ").append("cc.denominacioncuenta contraparte, ")
						.append("c.nrocontratocomprador comprobante, ").append("c.cantidadpactada cantidad, ")
						.append("c.preciomercaderia precio, ")
						.append("c.cantidadpactada -NVL(ent.cantidadentregada,0) saldo, ")
						.append("c.fechaentregadesde, ").append("c.fechaentregahasta, ")
						.append("proc.nombre procedencia, ").append("dest.nombre destino, ")
						.append("NVL(cf.cantidadfacturada,0) kilosfacturados, ").append("cal.codigo catcod, ")
						.append("cal.nombre catdesc, ").append("c.fechaoperacion fa, ")
						// #1516
						.append("DECODE(c.cobrodemercaderia,'F','A Fijar','A Precio') opcod, ")
						// #1531
						.append(" c.cobrodemercaderia cobrodemercaderia, ")
						.append("NVL(c.monedaiva_id, NVL(c.MONEDAFACTURACIONMERCADERIA_ID, NVL(c.MONEDAPRECIOOLEICO_ID, '1717'))) moneda_id, ")
						.append("( select de.descripcion from " + ZeniContextServer.getInstance().getDBWebOwner()
								+ "NOESTA_OPERATORIACBRMER de where de.id = c.cobrodemercaderia) opdesc, ")
						.append("( select dd.descripcion from " + ZeniContextServer.getInstance().getDBWebOwner()
								+ "NOESTA_OPERATORIA dd where dd.id = c.operatoria) operatoria, ")
						.append("c.cantidadpactada pac, ").append("ent.cantidadentregada entr, ").append("c.cumplido, ")
						.append("c.fechaentregadesde periododesde, ").append("c.fechaentregahasta periodohasta, ")
						.append("tt.nombre tte, ").append("c.cantidadpactada -NVL(ent.cantidadentregada,0) pdte, ")
						.append("NVL(cf.cantidadfacturada,0) fact, ").append("NVL(fija.cantidad,0) fij, ")
						.append("NVL(ent.cantidadentregada,0) -NVL(fija.cantidad,0) afijar, ")
						.append("fija.fechadesde fechafijaciondesde, ").append("fija.fechahasta fechafijacionhasta, ")
						.append("p.id producto_id, ").append("p.nombre producto, ").append("p.codigo productocodigo, ")
						.append("c.aniocosecha cosecha, cuenta.nroCuenta ").append("FROM contrato c ")
						.append("INNER JOIN producto p ").append("ON (p.id=c.producto_id) ")
						.append("INNER JOIN cuenta_cliente cc ").append("ON (cc.id =c.vendedor_id ")
						.append("AND cc.fechabaja IS NULL) ").append("INNER JOIN cuenta_cliente cuenta ")
						.append("ON (cuenta.id =c.comprador_id ").append("AND cuenta.fechabaja IS NULL) ")
						.append("INNER JOIN ciudad PROC ").append("ON (proc.id=c.procedencia_id) ")
						.append("INNER JOIN ciudad dest ").append("ON (dest.id=c.destino_id) ")
						.append("LEFT OUTER JOIN calidad cal ").append("ON (cal.id=c.calidad_id) ")
						.append("LEFT OUTER JOIN tipo_transporte tt ").append("ON (tt.id=c.tipotransporte_id) ")
						.append("LEFT OUTER JOIN ").append("(SELECT ep.contrato_id, ")
						.append("SUM(ep.cantidad) cantidadentregada ")
						// #2063
						.append("FROM entrega_partida ep ")
						.append("INNER JOIN WORKFLOW_STATE ws ON (ws.id = ep.state_id) ")
						.append("INNER JOIN WORKFLOW_DEFINITION wd ON (wd.id = ws.workflowDefinition_id) ")
						.append("WHERE ep.fechabaja IS NULL AND wd.estadoActual NOT IN ('N') ")
						// #2063
						.append("GROUP BY contrato_id ").append(") ent ").append("ON (ent.contrato_id=c.id) ")
						.append("LEFT OUTER JOIN ").append("(SELECT fp.contrato_id, ")
						.append("SUM(fp.cantidadfacturada) cantidadfacturada ").append("FROM factura_producto fp ")
						.append("INNER JOIN tipo_comprobante tc ").append("ON (tc.id=fp.tipocomprobante_id) ")
						.append("WHERE upper(fp.facturador) NOT LIKE '%FINAL%' ")
						.append("AND tc.abreviatura NOT IN ('FA','ND','NC','LF','BF') ")
						.append("AND (tc.abreviatura <> 'FC' ").append("OR fp.descontarCantidadFacturada = 1) ")
						.append("AND fp.anulador_id IS NULL ").append("AND fp.anulada_id IS NULL ")
						.append("AND fp.fechabaja IS NULL ").append("GROUP BY fp.contrato_id ")
						.append(") cf ON (cf.contrato_id=c.id) ").append("LEFT OUTER JOIN ")
						.append("(SELECT gf.contrato_id, ").append("SUM(f.cantidadafijar) cantidad, ")
						.append("MAX(f.fechafijacion) fechahasta, ").append("MIN(f.fechafijacion) fechadesde ")
						.append("FROM grupo_fijaciones gf ").append("INNER JOIN fijacion f ")
						.append("ON (f.grupofijaciones_id = gf.id ").append("AND f.fechabaja IS NULL) ")

						.append("INNER JOIN WORKFLOW_STATE WSF ").append("ON (WSF.ID = F.STATE_ID) ")
						.append("INNER JOIN WORKFLOW_DEFINITION WDF ")
						.append("ON ( WSF.WORKFLOWDEFINITION_ID = WDF.ID ").append("AND WDF.ESTADOACTUAL <> 'A' )")

						.append("WHERE gf.fechabaja IS NULL ").append("GROUP BY gf.contrato_id ")
						.append(") fija ON (fija.contrato_id=c.id) ").append("WHERE c.fechabaja IS NULL ")
						.append("AND c.comprador_id           IN ").append(cuentas_cuentas).append(opcionesFiltro);

//				if (ecompraven.equals(EnumCompradorVendedorType.AMBOS))
//					querySBuilder.append("AND c.comprador_id  in ").append(cuentas_cuentas).append(opcionesFiltro);
//
//				if (ecompraven.equals(EnumCompradorVendedorType.COMPRADOR))
//					querySBuilder.append("AND c.vendedor_id    in ").append(cuentas_cuentas).append(opcionesFiltro);



			}
			querySBuilder.append(" ) filamento ");
			final ArrayOfContratoType _return = new ArrayOfContratoType();
			final List<ContratoType> _returnContratos = new ArrayList<ContratoType>();
			final HashMap<String, MonedaType> monedaMap = innerObtenerMoneda();
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						try {
							final ContratoType _returnContratosVal1 = new ContratoType();
							final RangoFechaType _returnContratosVal1EntRango = new RangoFechaType();
							final RangoFechaType _returnContratosVal1Periodo = new RangoFechaType();
							final RangoFechaType _returnContratosVal1FijacionRango = new RangoFechaType();
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
							_returnContratosVal1.setFA(DateUtil.Converters
									.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fa"))));
							_returnContratosVal1.setContratId(rset.getString("contrato_id"));
							_returnContratosVal1.setContratoId(rset.getString("contrato_id"));
							_returnContratosVal1.setNumeroZeni(rset.getString("nrozeni"));
							_returnContratosVal1.setNumeroContrato(rset.getString("numerocontrato"));
							_returnContratosVal1.setNumeroComprobante(rset.getString("comprobante"));
							_returnContratosVal1.setContraparte(rset.getString("contraparte"));
							_returnContratosVal1.setCantidad(rset.getFloat("cantidad"));
							_returnContratosVal1.setSaldo(rset.getFloat("saldo"));
							_returnContratosVal1.setPrecio(rset.getFloat("precio"));
							_returnContratosVal1.setKgFacturado(rset.getFloat("kilosfacturados"));
							_returnContratosVal1.setProductoDescripcion(rset.getString("producto"));
							_returnContratosVal1.setProductoCodigo(rset.getString("productocodigo"));
							_returnContratosVal1.setProductoId(rset.getString("producto_id"));
							_returnContratosVal1.setProcedencia(rset.getString("procedencia"));// ESTE
							_returnContratosVal1.setDestino(rset.getString("destino"));// ESTE
							_returnContratosVal1.setCatCod(rset.getString("catcod"));
							_returnContratosVal1.setCatDesc(rset.getString("catdesc"));
							_returnContratosVal1.setOpCod(rset.getString("opcod"));
							_returnContratosVal1.setOpDesc(rset.getString("opdesc"));
							_returnContratosVal1.setPac(rset.getFloat("pac"));
							_returnContratosVal1.setEntr(rset.getFloat("entr"));
							_returnContratosVal1.setCumplido(rset.getInt("cumplido") > 0 ? SINOType.SI : SINOType.NO);
							_returnContratosVal1.setTte(rset.getString("tte"));
							_returnContratosVal1.setPdte(rset.getFloat("pdte"));
							_returnContratosVal1.setMoneda(monedaMap.get(rset.getString("moneda_id")));
							_returnContratosVal1.setFact(rset.getFloat("fact"));
							_returnContratosVal1.setFij(rset.getFloat("fij"));
							_returnContratosVal1.setAfijar(rset.getFloat("afijar"));
							_returnContratosVal1.setFijacionRango(_returnContratosVal1FijacionRango);
							_returnContratosVal1.setPeriodo(_returnContratosVal1Periodo);
							_returnContratosVal1.setEntRango(_returnContratosVal1EntRango);
							_returnContratosVal1.setCuenta(rset.getString("cuenta"));
							_returnContratosVal1.setIdCuenta(rset.getString("id_cuenta"));
							_returnContratosVal1.setCosecha(rset.getString("cosecha"));
							_returnContratosVal1.setNroCuenta(rset.getString("nroCuenta"));
							_returnContratos.add(_returnContratosVal1);
						} catch (ParseException e) {
							throw new SQLException("No se puede determinar la fecha en el query.", e);
						}
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);

			// #1516
			StringBuffer sqldetalle = new StringBuffer()
					.append(" select 'Multa por incumplimiento: ' || con.multaporincumplimiento  ")
					.append(" || ' - Fijaciones: ' || grpfijadesc.detalle_fijacion detalle_fijacion ")
					.append(" from contrato con  ").append(" inner join ( ").append(" select ")
					.append(" ' Cantidad Minima Diaria: ' || grp_fija.CANTIDADMINIMADIARIA || ' Canitdad Maxima Diaria: ' ||grp_fija.CANTIDADMAXIMADIARIA || ")
					.append(" ' Cantidad Minima Semanal: ' || grp_fija.CANTIDADMINIMASEMANAL || ' Cantidad Maxima Semanal: ' || grp_fija.CANTIDADMAXIMASEMANAL ||   ")
					.append(" ' Cantidad Minima Mensual: ' || grp_fija.CANTIDADMINIMAMENSUAL || ' Cantidad Maxima Mensual: ' || grp_fija.CANTIDADMAXIMAMENSUAL || ")
					.append(" ' Fecha Desde: ' || to_char(grp_fija.FECHAFIJACIONDESDE,'DD/MM/YYYY') || ' Fecha Hasta: ' || to_char(grp_fija.FECHAFIJACIONHASTA,'DD/MM/YYYY') || ")
					.append(" ' Horario Fijacion: ' || grp_fija.HORARIOFIJACION || ' Moneda: ' || moneda.nombre || ' Unidad de Medida: ' || udmedida.nombre || ")
					.append(" ' Pizarra: ' || mercado.nombre  detalle_fijacion, contrato_id ")
					.append(" from grupo_fijaciones grp_fija ")
					.append(" inner join moneda moneda on (moneda.id=grp_fija.moneda_id) ")
					.append(" inner join unidad_de_medida udmedida on (udmedida.id=grp_fija.unidaddemedida_id) ")
					.append(" inner join ponderado_producto_mercado ppm on (ppm.grupofijaciones_id=grp_fija.id) ")
					.append(" inner join mercado mercado on (mercado.id = ppm.mercado_id) ")
					.append(" where grp_fija.fechabaja is null ").append(" and ppm.fechabaja is null ")
					.append(" ) grpfijadesc on (grpfijadesc.contrato_id=con.id) ");

			// #1548
			StringBuffer sqlajustes = new StringBuffer().append(
					" select ' Mercado: ' || mercado.nombre  || ' Tipo: ' || taf.nombre ||  ' Moneda: ' || moneda.simbolo ")
					.append(" || ' Porcentaje: ' || to_char(vaf.PORCENTAJEAJUSTEFIJACION)  ")
					.append(" || ' Importe: ' || to_char(vaf.IMPORTEAJUSTEFIJACION) descripcion ")
					.append(" from valor_ajuste_fijacion vaf ")
					.append(" inner join grupo_fijaciones grp_fija on (grp_fija.id = vaf.grupofijaciones_id) ")
					.append(" inner join moneda moneda on (moneda.id=grp_fija.moneda_id) ")
					.append(" inner join unidad_de_medida udmedida on (udmedida.id=grp_fija.unidaddemedida_id) ")
					.append(" inner join ponderado_producto_mercado ppm on (ppm.grupofijaciones_id=grp_fija.id) ")
					.append(" inner join mercado mercado on (mercado.id = ppm.mercado_id)  ")
					.append(" inner join TIPO_AJUSTE_FIJACION taf on (taf.id = vaf.tipoajustefijacion_id) ")
					.append(" where grp_fija.fechabaja is null  ").append(" and ppm.fechabaja is null  ")
					.append(" and vaf.fechabaja is null ");

			// #1556
			StringBuffer sqlcomentario = new StringBuffer(" select ")
					.append(" comentario from comentario_grupo_fijaciones cgf ")
					.append(" inner join grupo_fijaciones grp_fija on (grp_fija.id = cgf.grupofijaciones_id) ")
					.append(" where cgf.fechabaja is null ").append(" and grp_fija.fechabaja is null ");

			for (final ContratoType contrato : _returnContratos) {
				String sql = sqldetalle.toString() + " where con.id = " + contrato.getContratoId();
				// #1548
				String sqlaj = sqlajustes.toString() + " and grp_fija.contrato_id = " + contrato.getContratoId();

				// #1556
				String sqlcom = sqlcomentario.toString() + " and grp_fija.contrato_id = " + contrato.getContratoId();

				final ResulsetObjectBuilder resdetalle = new ResulsetObjectBuilder() {
					@Override
					public void thisIsTheResulset(ResultSet resdetalle) throws SQLException {
						StringBuffer detalle = new StringBuffer();
						while (resdetalle.next()) {
							try {
								detalle.append(resdetalle.getString("detalle_fijacion"));
								detalle.append("\n");
							} catch (Exception e) {
								throw new SQLException("Error en la ejecucion del query detalle contrato", e);
							}
						}
						contrato.setCondicionAFijar(detalle.toString());
					}
				};

				// #1548
				final ResulsetObjectBuilder ajdetalle = new ResulsetObjectBuilder() {
					@Override
					public void thisIsTheResulset(ResultSet ajdetalle) throws SQLException {
						StringBuffer detalle = new StringBuffer();
						while (ajdetalle.next()) {
							try {
								detalle.append(ajdetalle.getString("descripcion"));
								detalle.append("\n");
							} catch (Exception e) {
								throw new SQLException("Error en la ejecucion del query ajustes de contrato", e);
							}
						}
						contrato.setAjustes(detalle.toString());
					}
				};

				// #1556
				final ResulsetObjectBuilder comentarios = new ResulsetObjectBuilder() {
					@Override
					public void thisIsTheResulset(ResultSet comentario) throws SQLException {
						StringBuffer com = new StringBuffer();
						while (comentario.next()) {
							try {
								com.append(comentario.getString("comentario"));
								com.append("\n");
							} catch (Exception e) {
								throw new SQLException("Error en la ejecucion del query comentarios de contrato", e);
							}
						}
						contrato.setComentarios(com.toString());
					}
				};

				ZeniQueryExcecutor.excecuteSelect(sql, resdetalle);

				// #1548
				ZeniQueryExcecutor.excecuteSelect(sqlaj, ajdetalle);

				// #1556
				ZeniQueryExcecutor.excecuteSelect(sqlcom, comentarios);

			}

			_return.getContratos().addAll(_returnContratos);
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
	public ArrayOfCobranzaType obtenerCobranzas(AuthType auth, ArrayOfIdCuenta acuenta, FechaTimeType fechaTope,
			EnumOperacionesType filtroOperaciones) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCobranzas");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(acuenta);
		ZeniContextServer.getInstance().printVerboLog(fechaTope);
		ZeniContextServer.getInstance().printVerboLog(filtroOperaciones);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			if (fechaTope == null || DateUtil.ToString.ddMMyyyy(fechaTope) == null) {
				throw new ZeniServerExeption("La fecha fechaTope no puede ser nulo o vacio",
						FAULTCONSTANTS.ERROR_ERRONEO);
			}
			if (acuenta == null || acuenta.getCuentas() == null || acuenta.getCuentas().size() <= 0) {
				throw new ZeniServerExeption("Los id de la cuenta no puede ser nulo o vacio",
						FAULTCONSTANTS.ERROR_ERRONEO);
			}
			String cuentas = ZeniContextServer.VACIO;
			if (acuenta != null && acuenta.getCuentas() != null && acuenta.getCuentas().size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < acuenta.getCuentas().size(); i++) {
					sb.append(",").append(acuenta.getCuentas().get(i)).append("");
				}
				cuentas = sb.deleteCharAt(0).insert(0, "(").append(") ").toString();
			}

			final ArrayOfCobranzaType _return = new ArrayOfCobranzaType();
			final List<CobranzaType> _returnCobranzas = new ArrayList<CobranzaType>();
			final String parciales;

			boolean comisiones = false;

			if (filtroOperaciones.equals(EnumOperacionesType.TODAS)) {
				parciales = " AND (f.grupocobranza IN ('P','F') OR f.grupocobranza IS NULL ) ";
				comisiones = true;
			} else if (filtroOperaciones.equals(EnumOperacionesType.FINALES)) {
				parciales = " AND f.grupocobranza IN ('F') ";
			} else if (filtroOperaciones.equals(EnumOperacionesType.PARCIALES)) {
				parciales = " AND f.grupocobranza IN ('P') ";
			} else {
				// NO debe traer ningun grupocobranza
				parciales = " AND f.grupocobranza <> f.grupocobranza ";
				comisiones = true;
			}

			StringBuffer sqlcomisiones = new StringBuffer(" ");

			if (comisiones) {
				sqlcomisiones.append(" UNION SELECT cta.denominacioncuenta cuenta, ").append("c.id contratid, ")
						.append("c.numerocontrato , ").append("tc.abreviatura tipoComprobante, ")
						.append("f.observacionescobranza, ").append("f.id comprobanteId, ").append("fechafactura, ")
						.append("fechavto fecha_vencimiento, ").append("tc.abreviatura ").append("||' ' ")
						.append("|| f.letra ").append("||' ' ").append("||f.nrofacturasucursal ").append("||' ' ")
						.append("||f.nrofacturanumero comprobante, ")
						.append("ROUND(DECODE(monedafactura_id,1717, (importesaldo ) *-1,0),2) saldo_a_cobrar, ")
						.append("ROUND(DECODE(monedafactura_id,1718, (importesaldo ) *-1,0),2) saldo_dolar, ")
						.append("ROUND(NVL(sac.saldo_a_confirmar,0)                  *-1,2) saldo_a_confirmar, ")
						.append("ROUND(ABS(NVL(f.totaldebe,f.totalhaber))            *(SIGN(DECODE( importesaldo,0,1,f.importesaldo)) *-1),2) importe_comprobante, ")
						.append("ROUND(ABS(f.importeiva)                             *(SIGN(DECODE(f.importesaldo,0,NVL(f.totaldebe, f.totalhaber),f.importesaldo))*-1),2) importe_iva, ")
						.append("f.monedafactura_id, ").append("m.nombre, ").append("m.simbolo, ")
						.append("DECODE(f.facturador,'FacturacionParcialesMAT',ccMat.denominacioncuenta,cc.denominacioncuenta) contraparte, ")
						.append("'C' cv, ")
						.append("decode(c.realizacobranza,'A','Aplicacion Cuenta','C','Cesion Pago','P','Pago Directo','T','Tercero','Z','Valor Zeni',' ') realizacobranza, ")
						.append("DECODE(f.pesificado, 1, '$' , DECODE(monedafactura_id,1718 ,'U$S', '$')) moneda_original, f.pesificado, ")
						.append("ROUND(importesaldo,2) importesaldo  ")
						.append("FROM factura_producto f ").append("INNER JOIN contrato c ")
						.append("ON (c.id         =f.contrato_id ").append("AND c.fechabaja IS NULL ) ")
						.append("INNER JOIN cuenta_cliente cc ").append("ON ( cc.id        =f.cuentacliente_id ")
						.append("AND cc.fechabaja IS NULL ) ").append("LEFT OUTER JOIN cuenta_cliente ccMat ")
						.append("ON ( ccMat.id        =f.cuentacliente_id ").append("AND ccMat.fechabaja IS NULL ) ")
						.append("INNER JOIN cuenta_cliente cta ").append("ON ( cta.id        =f.cuentacliente_id ")
						.append("AND cta.fechabaja IS NULL ) ").append("INNER JOIN moneda m ")
						.append("ON ( m.id=f.monedafactura_id ) ").append("INNER JOIN tipo_comprobante tc ")
						.append("ON ( tc.id =f.tipocomprobante_id ) ").append("LEFT OUTER JOIN ")
						.append("(SELECT rf.factura_id factura_id, ").append("SUM(rf.importe) saldo_a_confirmar ")
						.append("FROM recibo_factura rf ").append("INNER JOIN recibo r ")
						.append("ON ( r.id            = rf.recibo_id ) ").append("WHERE rf.fechaBaja  IS NULL ")
						.append("AND r.confirmado     = 0 ").append("AND rf.aplicarCtaCte = 0 ")
						.append("GROUP BY rf.factura_id ").append(") sac ON ( sac.factura_id=f.id ) ")
						.append("WHERE ( f.estadocobranza   ='P' ").append("OR sac.saldo_a_confirmar  <>0 ) ")
						.append(" AND f.grupocobranza IN ('C') ").append("AND TRUNC(f.fechavto) <= to_date('")
						.append(DateUtil.ToString.yyyyMMdd(fechaTope)).append("','yyyy-MM-dd') ")
						.append("AND f.cuentacliente_id        IN ").append(cuentas)
						.append("AND f.fechabaja           IS NULL ");
			}

			final StringBuilder querySBuilder = new StringBuilder().append("").append("SELECT * ").append("FROM ")
					.append("(SELECT cta.denominacioncuenta cuenta, ").append("c.id contratid, ")
					.append("c.numerocontrato , ").append("tc.abreviatura tipoComprobante, ")
					.append("f.observacionescobranza, ").append("f.id comprobanteId, ").append("fechafactura, ")
					.append("fechavto fecha_vencimiento, ").append("tc.abreviatura ").append("||' ' ")
					.append("|| f.letra ").append("||' ' ").append("||f.nrofacturasucursal ").append("||' ' ")
					.append("||f.nrofacturanumero comprobante, ")
					.append("ROUND(DECODE(monedafactura_id,1717, importesaldo ,0),2) saldo_a_cobrar, ")
					.append("ROUND(DECODE(monedafactura_id,1718, importesaldo ,0),2) saldo_dolar, ")
					.append("ROUND(NVL(sac.saldo_a_confirmar,0),2) saldo_a_confirmar, ")
					.append("DECODE(f.importesaldo,0,ROUND(ABS(f.totalhaber),2),ROUND(ABS(f.totalhaber) * SIGN(f.importesaldo),2)) importe_comprobante, ")
					.append("ROUND(ABS(f.importeiva)                                                    *SIGN(DECODE(f.importesaldo,0,ABS(f.totalhaber), f.importesaldo)),2) importe_iva, ")
					.append("f.monedafactura_id, ").append("m.nombre, ").append("m.simbolo, ")
					.append("DECODE(f.facturador,'FacturacionParcialesMAT',ccMat.denominacioncuenta,cc.denominacioncuenta) contraparte, ")
					.append("'V' cv, ")
					.append("decode(c.realizacobranza,'A','Aplicacion Cuenta','C','Cesion Pago','P','Pago Directo','T','Tercero','Z','Valor Zeni',' ') realizacobranza,  ")
					.append("DECODE(f.pesificado, 1, '$' , DECODE(monedafactura_id,1718 ,'U$S', '$')) moneda_original, f.pesificado, ")
					.append("ROUND(importesaldo,2) importesaldo  ")
					.append("FROM factura_producto f ").append("INNER JOIN contrato c ")
					.append("ON ( c.id        =f.contrato_id ").append("AND c.fechabaja IS NULL ) ")
					.append("INNER JOIN cuenta_cliente cc ").append("ON ( cc.id        =c.comprador_id ")
					.append("AND cc.fechabaja IS NULL ) ").append("LEFT OUTER JOIN cuenta_cliente ccMat ")
					.append("ON ( ccMat.id        =f.comprador_id ").append("AND ccMat.fechabaja IS NULL ) ")
					.append("INNER JOIN cuenta_cliente cta ").append("ON ( cta.id        =c.vendedor_id ")
					.append("AND cta.fechabaja IS NULL ) ").append("INNER JOIN moneda m ")
					.append("ON ( m.id=f.monedafactura_id ) ").append("INNER JOIN tipo_comprobante tc ")
					.append("ON ( tc.id =f.tipocomprobante_id ) ").append("LEFT OUTER JOIN ")
					.append("(SELECT rf.factura_id factura_id, ").append("SUM(rf.importe) saldo_a_confirmar ")
					.append("FROM recibo_factura rf ").append("INNER JOIN recibo r ")
					.append("ON ( r.id            = rf.recibo_id ) ").append("WHERE rf.fechaBaja  IS NULL ")
					.append("AND r.confirmado     = 0 ").append("AND rf.aplicarCtaCte = 0 ")
					.append("GROUP BY rf.factura_id ").append(") sac ON ( sac.factura_id=f.id ) ")
					.append("WHERE ( f.estadocobranza   ='P' ").append("OR sac.saldo_a_confirmar  <>0 ) ")
					.append(parciales).append("AND TRUNC(f.fechavto) <= to_date('")
					.append(DateUtil.ToString.yyyyMMdd(fechaTope)).append("','yyyy-MM-dd') ")
					.append("AND c.vendedor_id         IN ").append(cuentas).append(" ")
					.append("AND (f.comprador_id        =c.vendedor_id ")
					.append("OR f.vendedor_id           =c.vendedor_id) ").append("AND f.fechabaja           IS NULL ")
					.append("UNION ").append("SELECT cta.denominacioncuenta cuenta, ").append("c.id contratid, ")
					.append("c.numerocontrato , ").append("tc.abreviatura tipoComprobante, ")
					.append("f.observacionescobranza, ").append("f.id comprobanteId, ").append("fechafactura, ")
					.append("fechavto fecha_vencimiento, ").append("tc.abreviatura ").append("||' ' ")
					.append("|| f.letra ").append("||' ' ").append("||f.nrofacturasucursal ").append("||' ' ")
					.append("||f.nrofacturanumero comprobante, ")
					.append("ROUND(DECODE(monedafactura_id,1717, (importesaldo ) *-1,0),2) saldo_a_cobrar, ")
					.append("ROUND(DECODE(monedafactura_id,1718, (importesaldo ) *-1,0),2) saldo_dolar, ")
					.append("ROUND(NVL(sac.saldo_a_confirmar,0)                  *-1,2) saldo_a_confirmar, ")
					.append("ROUND(ABS(NVL(f.totaldebe,f.totalhaber))            *(SIGN(DECODE( importesaldo,0,1,f.importesaldo)) *-1),2) importe_comprobante, ")
					.append("ROUND(ABS(f.importeiva)                             *(SIGN(DECODE(f.importesaldo,0,NVL(f.totaldebe, f.totalhaber),f.importesaldo))*-1),2) importe_iva, ")
					.append("f.monedafactura_id, ").append("m.nombre, ").append("m.simbolo, ")
					.append("DECODE(f.facturador,'FacturacionParcialesMAT',ccMat.denominacioncuenta,cc.denominacioncuenta) contraparte, ")
					.append("'C' cv, ")
					.append("decode(c.realizacobranza,'A','Aplicacion Cuenta','C','Cesion Pago','P','Pago Directo','T','Tercero','Z','Valor Zeni',' ') realizacobranza , ")
					.append("DECODE(f.pesificado, 1, '$' , DECODE(monedafactura_id,1718 ,'U$S', '$')) moneda_original, f.pesificado, ")
					.append("ROUND(importesaldo,2) importesaldo  ")
					.append("FROM factura_producto f ").append("INNER JOIN contrato c ")
					.append("ON (c.id         =f.contrato_id ").append("AND c.fechabaja IS NULL ) ")
					.append("INNER JOIN cuenta_cliente cc ").append("ON ( cc.id        =c.vendedor_id ")
					.append("AND cc.fechabaja IS NULL ) ").append("LEFT OUTER JOIN cuenta_cliente ccMat ")
					.append("ON ( ccMat.id        =f.comprador_id ").append("AND ccMat.fechabaja IS NULL ) ")
					.append("INNER JOIN cuenta_cliente cta ").append("ON ( cta.id        =c.comprador_id ")
					.append("AND cta.fechabaja IS NULL ) ").append("INNER JOIN moneda m ")
					.append("ON ( m.id=f.monedafactura_id ) ").append("INNER JOIN tipo_comprobante tc ")
					.append("ON ( tc.id =f.tipocomprobante_id ) ").append("LEFT OUTER JOIN ")
					.append("(SELECT rf.factura_id factura_id, ").append("SUM(rf.importe) saldo_a_confirmar ")
					.append("FROM recibo_factura rf ").append("INNER JOIN recibo r ")
					.append("ON ( r.id            = rf.recibo_id ) ").append("WHERE rf.fechaBaja  IS NULL ")
					.append("AND r.confirmado     = 0 ").append("AND rf.aplicarCtaCte = 0 ")
					.append("GROUP BY rf.factura_id ").append(") sac ON ( sac.factura_id=f.id ) ")
					.append("WHERE ( f.estadocobranza   ='P' ").append("OR sac.saldo_a_confirmar  <>0 ) ")
					.append(parciales).append("AND TRUNC(f.fechavto) <= to_date('")
					.append(DateUtil.ToString.yyyyMMdd(fechaTope)).append("','yyyy-MM-dd') ")
					.append("AND c.comprador_id        IN ").append(cuentas).append(" ")
					.append("AND (f.comprador_id        =c.comprador_id ")
					.append("OR f.vendedor_id           =c.comprador_id ) ")
					.append("AND f.fechabaja           IS NULL ").append(sqlcomisiones).append(") a ")
					.append("ORDER BY a.cuenta, ").append("a.fechafactura ");
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						final CobranzaType _returnCobranzasVal1 = new CobranzaType();
						try {
							_returnCobranzasVal1.setVencimiento(DateUtil.Converters.DateToFechaTimeType(
									DateUtil.Parsers.yyyyMMdd(rset.getString("fecha_vencimiento"))));
							if (rset.getString("cv").equals("C") || rset.getString("cv").equals("c")) {
								_returnCobranzasVal1.setCompraVenta(EnumCompraVentaType.COMPRA);
							} else {
								_returnCobranzasVal1.setCompraVenta(EnumCompraVentaType.VENTA);
							}
							_returnCobranzasVal1.setComprobante(rset.getString("comprobante"));
							_returnCobranzasVal1.setComprobanteId(rset.getString("comprobanteId"));
							_returnCobranzasVal1.setTipo(rset.getString("tipoComprobante"));
							_returnCobranzasVal1.setDatosComprobante(rset.getString("comprobante"));
							_returnCobranzasVal1.setContraparte(rset.getString("contraparte"));
							_returnCobranzasVal1.setContrato(rset.getString("numerocontrato"));
							_returnCobranzasVal1.setContratId(rset.getString("contratid"));
							_returnCobranzasVal1.setImporte(rset.getDouble("importe_comprobante"));
							_returnCobranzasVal1.setImporteIVA(rset.getDouble("importe_iva"));
							_returnCobranzasVal1.setSaldoACobrar(rset.getDouble("saldo_a_cobrar"));
							_returnCobranzasVal1.setSaldoAConfirmar(rset.getDouble("saldo_a_confirmar"));
							_returnCobranzasVal1.setSaldoUSS(rset.getDouble("saldo_dolar"));
							_returnCobranzasVal1.setObservaciones(rset.getString("observacionescobranza"));
							_returnCobranzasVal1.setCuenta(rset.getString("cuenta"));
							_returnCobranzasVal1.setRealizacobranza(rset.getString("realizacobranza"));
							try {
								_returnCobranzasVal1.setCoe(getCOEByTipoComprobante(
										_returnCobranzasVal1.getComprobanteId(), _returnCobranzasVal1.getTipo()));
							} catch (ZeniDBExeption e) {
								throw new SQLException("Error al busca el COE", e);
							}
							_returnCobranzasVal1.setMonedaOriginal(rset.getString("moneda_original"));
							_returnCobranzasVal1.setImporteSaldo(rset.getDouble("importesaldo"));
							_returnCobranzas.add(_returnCobranzasVal1);

						} catch (ParseException ex) {
							throw new SQLException("No se puede determinar la fecha en el query.", ex);
						}
					}
				}

			};
			ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);

			_return.getCobranzas().addAll(_returnCobranzas);
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
	 * Obtiene el numero COE, segun el comprobante y su tipo
	 *
	 * Mejora #1821
	 *
	 * @throws ZeniDBExeption
	 */
	private static String getCOEByTipoComprobante(String comprobanteId, String tipo) throws ZeniDBExeption {

		final ArrayList<String> coes = new ArrayList<String>();
		String coe = null;
		StringBuffer sql = new StringBuffer();

		if (tipo == null || (!tipo.equalsIgnoreCase(ZeniConstants.COMPROBANTE_CV)
				&& !tipo.equalsIgnoreCase(ZeniConstants.COMPROBANTE_DV)
				&& !tipo.equalsIgnoreCase(ZeniConstants.COMPROBANTE_LF)
				&& !tipo.equalsIgnoreCase(ZeniConstants.COMPROBANTE_LP)))
			return null;

		if (tipo.equalsIgnoreCase(ZeniConstants.COMPROBANTE_LP))
			sql.append(
					"SELECT COE FROM WS_LPG_AUTORIZACION AUTO  WHERE AUTO.ID IN (SELECT WSLPGAUTORIZACION_ID FROM FACTURA_PRODUCTO WHERE ID = '"
							+ comprobanteId + "')");
		else
			sql.append(
					"SELECT COE FROM WS_LPG_AJUSTE AJUSTE  WHERE AJUSTE.ID IN (SELECT WSLPGAJUSTE_ID FROM FACTURA_PRODUCTO WHERE ID = '"
							+ comprobanteId + "')");

		// #1821
		final ResulsetObjectBuilder ajdetalle = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet ajdetalle) throws SQLException {
				if (ajdetalle.next()) {
					try {
						String data = ajdetalle.getString("COE");
						coes.add(data);
					} catch (Exception e) {
						throw new SQLException("Error en la ejecucion del query COE de cobranzas", e);
					}
				}
			}
		};

		ZeniQueryExcecutor.excecuteSelect(sql.toString(), ajdetalle);

		if (coes.size() > 0)
			coe = coes.get(0);

		return coe;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see ZeniWSDL#obtenerInformacionCuentaCorriente(AuthType auth ,)CuentaType
	 * cuenta ,)RangoFechaType rangoDeFechas ,)MonedaType filtroDeMoneda
	 * ,)EnumDeMovimientoType filtroDeMovimiento ,)java.lang.String comprobante
	 * ,)java.lang.String contrato )*
	 */
	public InformacionCuentaCorrienteType obtenerInformacionCuentaCorriente(AuthType auth, ArrayOfIdCuenta cuentas,
			RangoFechaType rangoDeFechas, MonedaType filtroDeMoneda, EnumDeMovimientoType filtroDeMovimiento,
			String comprobante, String contrato) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerInformacionCuentaCorriente");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(cuentas);
		ZeniContextServer.getInstance().printVerboLog(rangoDeFechas);
		ZeniContextServer.getInstance().printVerboLog(filtroDeMoneda);
		ZeniContextServer.getInstance().printVerboLog(filtroDeMovimiento);
		ZeniContextServer.getInstance().printVerboLog(comprobante);
		ZeniContextServer.getInstance().printVerboLog(contrato);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			if (rangoDeFechas == null || DateUtil.ToString.ddMMyyyy(rangoDeFechas.getDesde()) == null) {
				throw new ZeniServerExeption("La fecha rangoDeFechas desde no puede ser nulo o vacio",
						FAULTCONSTANTS.ERROR_ERRONEO);
			}
			if (rangoDeFechas == null || DateUtil.ToString.ddMMyyyy(rangoDeFechas.getHasta()) == null) {
				throw new ZeniServerExeption("La fecha rangoDeFechas hasta no puede ser nulo o vacio",
						FAULTCONSTANTS.ERROR_ERRONEO);
			}
			// #1698
			// String listaCuentas = ZeniContextServer.VACIO;
			// if (cuentas != null && cuentas.getCuentas() != null
			// && cuentas.getCuentas().size() > 0) {
			// StringBuilder sb = new StringBuilder();
			// for (int i = 0; i < cuentas.getCuentas().size(); i++) {
			// sb.append(",").append(cuentas.getCuentas().get(i))
			// .append("");
			// }
			// listaCuentas = sb.deleteCharAt(0).insert(0, "(").append(") ")
			// .toString();
			// }
			final InformacionCuentaCorrienteType _return = new InformacionCuentaCorrienteType();
			final ArrayOfComprobanteCuentaCorrienteType _returnComprobantes = new ArrayOfComprobanteCuentaCorrienteType();
			final List<ComprobanteCuentaCorrienteType> _returnComprobantesComprobantes = new ArrayList<ComprobanteCuentaCorrienteType>();
			final ArrayOfResumenCuentaCorrienteType _returnResumenCtaCtes = new ArrayOfResumenCuentaCorrienteType();
			final List<ResumenCuentaCorrienteType> _returnResumenCtaCtesResumenCtaCtes = new ArrayList<ResumenCuentaCorrienteType>();
			final ClienteType client = obtenerCliente(auth);
			StringBuilder querySBuilder = new StringBuilder().append("SELECT com.idcuenta idcuenta, ")
					.append("com.denominacion denominacion, ").append("com.nroCuenta nroCuenta, ")
					.append("com.idcomp, ").append("com.contratid, ").append("TO_CHAR(com.fecha, 'yyyy-MM-dd') fecha, ")
					.append("com.ridx, ").append("com.tipocom, ")
					.append("TO_CHAR(com.fechavalor, 'yyyy-MM-dd') fechavalor, ").append("com.tipocom, ")
					.append("com.comprobante, ").append("com.numerocontrato numerocontrato, ")
					.append("com.descripcion, ").append("com.importe, ").append("com.saldo saldoSinSuma, ")
					.append("saldoinicialcalculo.saldoinicial saldoinicialSinSuma, ")
					.append("TO_CHAR(to_number(TO_CHAR(com.saldo)) + to_number(TO_CHAR(saldoinicialcalculo.saldoinicial))) saldoSuma2 ")
					.append("FROM ").append("(SELECT cte.id idcuenta, ").append("cte.denominacioncuenta denominacion, ")
					.append("cte.nrocuenta nrocuenta, ").append("cm.fecha, ").append("cm.fechavalor, ")
					.append("DECODE(tc.abreviatura,'RT',cm.id,cm.ref_id) idcomp, ").append("tc.abreviatura tipocom, ")
					.append("tc.abreviatura ").append("||' ' ").append("||cm.letra ").append("||' ' ")
					.append("||cm.pvta ").append("||' ' ").append("||cm.nro comprobante, ").append("c.numerocontrato, ")
					.append("c.id contratid, ").append("cm.descripcion, ").append("DECODE('")
					.append(filtroDeMovimiento.equals(EnumDeMovimientoType.PENDIENTE) ? "SALDOPENDIENTE" : "IMPORTE")
					.append("','IMPORTE', cm.importe, cm.saldopendiente) importe, ").append("SUM(DECODE('")
					.append(filtroDeMovimiento.equals(EnumDeMovimientoType.PENDIENTE) ? "SALDOPENDIENTE" : "IMPORTE")
					.append("','IMPORTE', cm.importe, cm.saldopendiente)) over (order by cm.fecha ASC, cm.id ASC) saldo, ")
					.append("SUM(1) over (order by cm.fecha ASC, cm.id ASC) ridx ").append("FROM ctacte_mov cm ")
					.append("INNER JOIN tipo_comprobante tc ").append("ON (tc.id=cm.tipocomprobante_id) ")
					.append("INNER JOIN ctacte_cuenta cc ").append("ON (cc.id=cm.cuenta_id) ")
					.append("INNER JOIN cuenta_cliente cte  ").append("ON (cte.id=cc.cuentacliente_id) ")
					.append("LEFT OUTER JOIN contrato c ").append("ON (c.id               =cm.contrato_id) ")
					.append("WHERE cm.fechabaja    IS NULL ").append("AND cm.moneda_id       = ")
					.append(filtroDeMoneda.getId()).append(" ").append("AND cc.cuentacliente_id in ")

					// #1698
					// .append(listaCuentas)
					.append(" (CUENTAIDXX) ")

					.append(" ").append("AND TRUNC(fecha)      >= to_date('")
					.append(DateUtil.ToString.yyyyMMdd(rangoDeFechas.getDesde())).append("','yyyy-MM-dd') ")
					.append("AND TRUNC(fecha)      <= to_date('")
					.append(DateUtil.ToString.yyyyMMdd(rangoDeFechas.getHasta())).append("','yyyy-MM-dd')  ")
					.append(filtroDeMovimiento.equals(EnumDeMovimientoType.PENDIENTE) ? "AND cm.saldopendiente <>0 "
							: " ORDER BY cm.fecha ASC, cm.id ASC ")
					.append(") com, ").append("(SELECT NVL(SUM(NVL(DECODE('")
					.append(filtroDeMovimiento.equals(EnumDeMovimientoType.PENDIENTE) ? "SALDOPENDIENTE" : "IMPORTE")
					.append("','IMPORTE', NVL(cm2.importe, 0), NVL(cm2.saldopendiente, 0)), 0)), 0) saldoInicial ")
					.append("FROM ctacte_mov cm2 ").append("INNER JOIN ctacte_cuenta cc2 ")
					.append("ON (cc2.id              =cm2.cuenta_id ").append("AND cc2.fechabaja      IS NULL) ")
					.append("WHERE cm2.fechabaja    IS NULL ").append("AND cm2.fecha           <to_date('")
					.append(DateUtil.ToString.yyyyMMdd(rangoDeFechas.getDesde())).append("','yyyy-MM-dd') ")
					.append("AND cc2.cuentacliente_id in ")
					// #1698
					// .append(listaCuentas)
					.append(" (CUENTAIDXX) ")

					.append(" ").append("AND cm2.moneda_id       = ").append(filtroDeMoneda.getId()).append(" ")
					.append(") saldoinicialcalculo ORDER BY com.denominacion,com.ridx ASC  ");
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						try {
							final ComprobanteCuentaCorrienteType _returnComprobantesComprobantesVal1 = new ComprobanteCuentaCorrienteType();
							_returnComprobantesComprobantesVal1.setCuentaid(rset.getString("idcuenta"));
							_returnComprobantesComprobantesVal1.setDenominacion(rset.getString("denominacion"));
							_returnComprobantesComprobantesVal1.setNroCuenta(rset.getString("nroCuenta"));
							_returnComprobantesComprobantesVal1.setComprobante(rset.getString("comprobante"));
							_returnComprobantesComprobantesVal1.setTipo(rset.getString("tipocom"));
							_returnComprobantesComprobantesVal1.setComprobanteId(rset.getString("idcomp"));
							_returnComprobantesComprobantesVal1.setContrato(rset.getString("numerocontrato"));
							_returnComprobantesComprobantesVal1.setContratId(rset.getString("contratId"));
							_returnComprobantesComprobantesVal1.setFecha(DateUtil.Converters
									.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecha"))));
							_returnComprobantesComprobantesVal1.setFechaValor(DateUtil.Converters
									.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechavalor"))));
							_returnComprobantesComprobantesVal1.setDetalle(rset.getString("descripcion"));
							_returnComprobantesComprobantesVal1.setMovimiento(rset.getDouble("importe"));
							_returnComprobantesComprobantesVal1.setSaldo(rset.getDouble("saldoSuma2"));
							_returnComprobantesComprobantes.add(_returnComprobantesComprobantesVal1);
						} catch (ParseException ex) {
							throw new SQLException("No se puede determinar la fecha en el query.", ex);
						}
					}
				}
			};

			// #1698
			for (int i = 0; i < cuentas.getCuentas().size(); i++) {
				String idcuenta = cuentas.getCuentas().get(i);
				String sql = querySBuilder.toString().replaceAll("CUENTAIDXX", idcuenta);

				ZeniQueryExcecutor.excecuteSelect(sql, resb);
			}

			_returnComprobantes.getComprobantesCC().addAll(_returnComprobantesComprobantes);
			for (int i = 0; i < cuentas.getCuentas().size(); i++) {
				String cuenta = cuentas.getCuentas().get(i);
				if (cuenta == null || cuenta.equals(ZeniContextServer.VACIO)) {
					throw new ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
							FAULTCONSTANTS.ERROR_ERRONEO);
				}
				for (int f = 0; f < client.getCuentas().getCuentas().size(); f++) {
					if (client.getCuentas().getCuentas().get(f).getId().equals(cuenta)) {
						final CuentaType cuent = client.getCuentas().getCuentas().get(f);
						final ResumenCuentaCorrienteType _returnResumenCtaCte = new ResumenCuentaCorrienteType();
						StringBuilder querySBuilder2 = new StringBuilder().append(
								"SELECT max(cc.id) cuenta_id, max(cc.tipocuenta) tipocuenta, max(cc.moneda_id) moneda_id, max(cc.saldo) saldo, nvl(sum(cm.importe),0) saldoAnterior, max(cc.saldous) saldous, ")
								.append("to_char(max(cc.fechacreacion), 'yyyy-MM-dd') fechacreacion, '2012-04-01' fechasaldoanterior ")
								.append("FROM ctacte_mov cm ").append("INNER JOIN ctacte_cuenta cc ")
								.append("ON (cc.id              =cm.cuenta_id ")
								.append("AND cc.fechabaja      IS NULL) ").append("WHERE cm.fechabaja    IS NULL ")
								.append("AND cm.fecha      <to_date('")
								.append(DateUtil.ToString.yyyyMMdd(rangoDeFechas.getDesde())).append("','yyyy-MM-dd') ")
								.append("AND cc.cuentacliente_id= ").append(cuenta).append(" ")
								.append("AND cm.moneda_id       = ").append(filtroDeMoneda.getId()).append(" ");
						final ResulsetObjectBuilder resb2 = new ResulsetObjectBuilder() {
							@Override
							public void thisIsTheResulset(ResultSet rset) throws SQLException {
								while (rset.next()) {
									_returnResumenCtaCte.setCuenta(cuent);
									_returnResumenCtaCte.setSaldo(rset.getDouble("saldo"));
									_returnResumenCtaCte.setSaldoAnterior(rset.getDouble("saldoAnterior"));
									_returnResumenCtaCte.setSaldouss(rset.getDouble("saldous"));
								}
							}
						};
						ZeniQueryExcecutor.excecuteSelect(querySBuilder2.toString(), resb2);
						_returnResumenCtaCtesResumenCtaCtes.add(_returnResumenCtaCte);
					}
				}
			}
			_returnResumenCtaCtes.getResumenCtaCtes().addAll(_returnResumenCtaCtesResumenCtaCtes);
			_return.setEmision(DateUtil.Converters.DateToFechaTimeType(new Date()));
			_return.setPeriodo(rangoDeFechas);
			_return.setResumenCtaCtes(_returnResumenCtaCtes);
			_return.setComprobantes(_returnComprobantes);
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
	public ArrayOfOperacionFinancieraType obtenerOperacionesFinancieras(AuthType auth, final CuentaType cuenta,
			EnumCompraVentaType condicion, EnumEstadoType estado, EnumSucursalType sucursal,
			ArrayOfMercadoType mercados, RangoFechaType rangoFecha, EnumTipoOperacionMatType tipo,
			ArrayOfProductoMATType productos, ArrayOfDestinoMATType destinos, String precio, String prima,
			String posicion, EnumTipoOperacionType tipoOperacion, final EnumTipoConsultaType tipoConsulta)
			throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerOperacionesFinancieras");
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
			ArrayOfOperacionFinancieraType _return = new ArrayOfOperacionFinancieraType();
			final List<OperacionFinancieraType> _returnOperaciones = new ArrayList<OperacionFinancieraType>();

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
			if (tipoOperacion == null || tipoOperacion.equals(ZeniContextServer.VACIO)) {
				throw new ZeniServerExeption("El tipo de operacion no puede ser nulo o vacio",
						FAULTCONSTANTS.ERROR_ERRONEO);
			}
			String listaDeMercad = ZeniContextServer.VACIO;
			if (mercados != null && mercados.getMercados() != null && mercados.getMercados().size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mercados.getMercados().size(); i++) {
					sb.append(",'").append(mercados.getMercados().get(i).getId()).append("'");
				}
				listaDeMercad = sb.deleteCharAt(0).insert(0, " and mp.mercado_id in (").append(") ").toString();
			}
			StringBuilder querySBuilder = new StringBuilder().append("");

			// Por Operacion.
			if (tipoConsulta.equals(EnumTipoConsultaType.OPERACION)) {

				ZeniContextServer.getInstance()
						.printInfoLog("Executing obtenerOperacionesFinancieras tipoConsultaOperacion{"
								+ EnumTipoConsultaType.OPERACION.value() + "} tipoOperacion {" + tipoOperacion + "} ");

				if (tipoOperacion.equals(EnumTipoOperacionType.ABIERTAS)) {

					querySBuilder = obtenerOperacionesAbiertas(rangoFecha, cuenta, listaDeMercad);

				} else if (tipoOperacion.equals(EnumTipoOperacionType.ESTADISTICO)) {

					querySBuilder = obtenerOperacionesEstadisticas(rangoFecha, cuenta, listaDeMercad);
				}

			} else {
				ZeniContextServer.getInstance()
						.printInfoLog("Executing obtenerOperacionesFinancieras tipoConsultaOperacion{"
								+ EnumTipoConsultaType.RESUMEN.value() + "} tipoOperacion {" + tipoOperacion + "} ");

				if (tipoOperacion.equals(EnumTipoOperacionType.ABIERTAS)) {

					querySBuilder = obtenerResumenAbiertas(rangoFecha, cuenta, listaDeMercad);

				} else if (tipoOperacion.equals(EnumTipoOperacionType.ESTADISTICO)) {

					querySBuilder = obtenerResumenEstadisticas(rangoFecha, cuenta, listaDeMercad);
				}

			}

			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {

					while (rset.next()) {
						try {

							OperacionFinancieraType _returnOperacionesVal1 = new OperacionFinancieraType();

							String tipo = rset.getString("tipo_sigla");
							String estadoActual = rset.getString("estadoActual");

							if (tipoConsulta.equals(EnumTipoConsultaType.OPERACION)) {
								_returnOperacionesVal1
										.setCondicion(Condicion.getDescripcionByValor(rset.getString("condicion")));
								_returnOperacionesVal1.setOperador(rset.getString("operador"));
								_returnOperacionesVal1.setPosicion(rset.getString("posicion"));

								String condicion = rset.getString("condicion");

								if (tipo != null && tipo.equalsIgnoreCase("N") && condicion != null
										&& condicion.equalsIgnoreCase("S")) {

									if (rset.getBigDecimal("cantidadctapartes") != null) {

										_returnOperacionesVal1
												.setVolumen(rset.getBigDecimal("cantidadctapartes"));

										if (rset.getBigDecimal("cantidadctapartes")
												.compareTo(BigDecimal.valueOf(1)) == 0) {
											_returnOperacionesVal1.setMontoSuscripto(rset.getBigDecimal("preciooprima"));
											_returnOperacionesVal1.setSuscripto("S");
											_returnOperacionesVal1.setPreciooprima(BigDecimal.valueOf(0).floatValue());
										} else {
											_returnOperacionesVal1
													.setMontoSuscripto(BigDecimal.valueOf(0));
											_returnOperacionesVal1.setPreciooprima(rset.getFloat("preciooprima"));
										}
									} else {
										_returnOperacionesVal1.setVolumen(BigDecimal.valueOf(0));
										_returnOperacionesVal1.setMontoSuscripto(BigDecimal.valueOf(0));
										_returnOperacionesVal1.setPreciooprima(rset.getFloat("preciooprima"));

									}
								} else {
									_returnOperacionesVal1.setVolumen(rset.getBigDecimal("volumen"));
									_returnOperacionesVal1.setPreciooprima(rset.getFloat("preciooprima"));
									_returnOperacionesVal1.setMontoSuscripto(BigDecimal.valueOf(0));
								}

								_returnOperacionesVal1.setDestino(rset.getString("destino"));
								_returnOperacionesVal1.setRegistro(rset.getString("registro"));
								_returnOperacionesVal1.setFechaAlta(DateUtil.Converters
										.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fecha_alta"))));
								_returnOperacionesVal1.setEnContratos(rset.getFloat("en_contratos"));
								_returnOperacionesVal1.setRegCancela(rset.getString("Reg_Cancela"));
								_returnOperacionesVal1.setMoneda(rset.getString("moneda"));
								_returnOperacionesVal1.setFechaInicial(DateUtil.Converters.DateToFechaTimeType(
										DateUtil.Parsers.yyyyMMdd(rset.getString("Fecha_Inicial"))));
								_returnOperacionesVal1.setPrecioInicial(rset.getFloat("Precio_Inicial"));

								_returnOperacionesVal1.setFechaOperacion(DateUtil.Converters.DateToFechaTimeType(
										DateUtil.Parsers.yyyyMMdd(rset.getString("fecha_operacion"))));

							} else {
								_returnOperacionesVal1.setVolumen(rset.getBigDecimal("volumen"));
								_returnOperacionesVal1.setPreciooprima(rset.getFloat("preciooprima"));
								_returnOperacionesVal1.setMontoSuscripto(BigDecimal.valueOf(0));

							}

							_returnOperacionesVal1.setMercado(rset.getString("mercado"));
							_returnOperacionesVal1.setTipo(rset.getString("tipo"));
							_returnOperacionesVal1.setProducto(rset.getString("producto"));
							_returnOperacionesVal1.setSuscripto("N");
							_returnOperacionesVal1.setPrecioEjercicio(rset.getFloat("precio_ejercicio"));
							_returnOperacionesVal1.setTipoOperacion(rset.getString("tipo_operacion"));
							_returnOperacionesVal1.setEstado(rset.getString("estado"));
							_returnOperacionesVal1.setCodigoInterno(rset.getString("codigointerno"));

							_returnOperacionesVal1.setFechaVto(DateUtil.Converters
									.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechaVto"))));

							// Fix Norberto.

							if (tipo != null && tipo.equalsIgnoreCase("N")) {
								_returnOperacionesVal1.setMontoSuscripto(
										_returnOperacionesVal1.getVolumen().multiply( new BigDecimal(_returnOperacionesVal1.getPreciooprima())));
							} else {
								_returnOperacionesVal1.setMontoSuscripto(BigDecimal.valueOf(0));
							}

							if (((estadoActual.equalsIgnoreCase("F") || estadoActual.equalsIgnoreCase("EG")
									|| estadoActual.equalsIgnoreCase("CC")) && !tipo.equals("N"))
									|| (estadoActual.equalsIgnoreCase("F") || estadoActual.equalsIgnoreCase("EG")
											|| estadoActual.equalsIgnoreCase("CC"))

											&& (tipo.equals("N") && (_returnOperacionesVal1.getVolumen().floatValue() > 1
													|| _returnOperacionesVal1.getVolumen().floatValue() < 1))) {
								try {

									List<Float> precios = getPrecioActual(rset.getString("matProductoID"));

									if (precios != null && precios.size() > 0)
										_returnOperacionesVal1.setPrecioActual(precios.get(0));
									else
										_returnOperacionesVal1.setPrecioActual(BigDecimal.valueOf(0).floatValue());

								} catch (Exception e) {
									ZeniContextServer.getInstance().printErrorLog(e);
									ZeniContextServer.getInstance()
											.printErrorLog("Ocurrio un error al obtener el precioactual {"
													+ rset.getString("matProductoID") + "}");
									_returnOperacionesVal1.setPrecioActual(BigDecimal.valueOf(0).floatValue());
								}

							}

							//Recalculo de precioPrima
							ZeniContextServer.getInstance()
							.printInfoLog("TipoConsulta {"+tipoConsulta+"} estadoActual{"+estadoActual+"}  ");

							if (tipoConsulta.equals(EnumTipoConsultaType.RESUMEN) && (estadoActual.equalsIgnoreCase("F") || estadoActual.equalsIgnoreCase("CC"))) {
								_returnOperacionesVal1.setPreciooprima(calcularPrecioPrima(cuenta.getId(), rset.getLong("matProductoID"), rset.getDouble("preciooprima")).floatValue());
							}


							//Se Agrega total Valorizado
							if (_returnOperacionesVal1.getVolumen() !=null) {
								_returnOperacionesVal1.setTotalValorizado( _returnOperacionesVal1.getVolumen().setScale(7,2).multiply(new BigDecimal(_returnOperacionesVal1.getPrecioActual()).setScale(7,2)));
							}

							_returnOperaciones.add(_returnOperacionesVal1);

						} catch (ParseException e) {
							throw new SQLException("No se puede determinar la fecha en el query.", e);
						}
						catch (Exception e) {
							ZeniContextServer.getInstance().printErrorLog(e);
							throw new SQLException("Error al procesar el SQL", e);

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




	/**
	 *
	 * @param rangoFecha
	 * @param cuenta
	 * @param listaDeMercad
	 * @return
	 * @throws Exception
	 */
	private StringBuilder obtenerResumenEstadisticas(RangoFechaType rangoFecha, CuentaType cuenta, String listaDeMercad)
			throws Exception {
		StringBuilder querySBuilder = new StringBuilder();

		querySBuilder.append("	select   merc.nombre mercado, mp.tipo tipo_sigla, ");
		querySBuilder.append("DECODE( mp.tipo, ");
		querySBuilder.append("      'F','Futuro', ");
		querySBuilder.append("       'T','Futuro sobre base', ");
		querySBuilder.append("'I','Inmediato', ");
		querySBuilder.append("'C','Call', ");
		querySBuilder.append("'P','Put', ");
		querySBuilder.append("'A','Acciones', ");
		querySBuilder.append("'N','Fci', ");
		querySBuilder.append("'L','Titulos', ");
		querySBuilder.append("'Otros') tipo_operacion, ");
		querySBuilder.append(" DECODE( ");
		querySBuilder.append(" mp.tipo, ");
		querySBuilder.append(" 'F','Futuros', ");
		querySBuilder.append(" 'T','Futuros', ");
		querySBuilder.append(" 'C','Opciones', ");
		querySBuilder.append(" 'P','Opciones', ");
		querySBuilder.append(" 'A','Acciones', ");
		querySBuilder.append("    'L','Titulos', ");
		querySBuilder.append("'Otros') tipo, ");
		querySBuilder.append(" mprod.nombre producto, ");
		querySBuilder.append("mprod.codigointerno, ");
		querySBuilder.append("(CASE WHEN mprod.fechaVto IS NOT NULL THEN to_char(mprod.fechavto, 'yyyy-mm-dd') ");
		querySBuilder.append("ELSE ' ' END) as fechaVto, ");
		querySBuilder
				.append(" SUM (CASE WHEN (mp.tipo = 'N') THEN (CASE WHEN (mp.condicion = 'R' OR mp.condicion = 'A') ");
		querySBuilder.append("THEN -COALESCE(mp.cantidadCtaPartes,0) ELSE COALESCE(mp.cantidadCtaPartes,0) END) ");
		querySBuilder
				.append(" ELSE (CASE WHEN (mp.condicion = 'V' OR mp.condicion = 'A') THEN -COALESCE(mp.volumen,0) ");
		querySBuilder.append("    ELSE COALESCE(mp.volumen,0) END) END) as volumen, ");
		querySBuilder.append("ROUND(SUM(CASE WHEN (mp.tipo = 'N') THEN (mp.precioOPrima * ");
		querySBuilder.append("(CASE WHEN mp.moneda_id = 1718 THEN (select cm.promedio from moneda_cotizacion cm  ");
		querySBuilder.append("where cm.fecha = mp.fecha  and cm.moneda_id = 1718 and cm.cotizada_id = 1717  ");
		querySBuilder.append(" and cm.banco_id = 19558201  ");
		querySBuilder.append(" and cm.fechaBaja is null and rownum=1) ELSE 1 END) * mp.cantidadCtaPartes) ");
		querySBuilder.append("ELSE (mp.precioOPrima *  ");
		querySBuilder.append(" (CASE WHEN mp.moneda_id = 1718 THEN (select cm.promedio from moneda_cotizacion cm ");
		querySBuilder.append("  where cm.fecha = mp.fecha  and cm.moneda_id = 1718 and cm.cotizada_id = 1717 ");
		querySBuilder.append("and cm.banco_id = 19558201  ");
		querySBuilder.append(" and cm.fechaBaja is null and rownum=1) ELSE 1 END) * mp.volumen) END) / ");
		querySBuilder.append(" SUM(CASE WHEN (mp.tipo = 'N') THEN mp.cantidadCtaPartes ELSE mp.volumen END),7) as preciooprima, ");
		querySBuilder.append("DECODE(mp.tipo, ");
		querySBuilder.append("'C',mp.precio, ");
		querySBuilder.append("'P',mp.precio, ");
		querySBuilder.append("			        null) precio_ejercicio, ");
		querySBuilder.append("SUM(mp.cantidadctapartes) cantidadctapartes, ");
		querySBuilder.append("SUM(mp.cantidadctapartes) MontoSuscripto, ");
		querySBuilder.append("  DECODE( ");
		querySBuilder.append("wd.estadoActual, ");
		querySBuilder.append("'A','Anulada', ");
		querySBuilder.append("'AJ','Candelado por Ajuste', ");
		querySBuilder.append("'C','Cancelada', ");
		querySBuilder.append("'E','Expirado', ");
		querySBuilder.append("'F','Abierta', ");
		querySBuilder.append("'I','Ingresada', ");
		querySBuilder.append("'J','Ejercicio', ");
		querySBuilder.append("'M','Ingresada Manual', ");
		querySBuilder.append("'O','Oferta Entrega', ");
		querySBuilder.append("'OA','Oferta Entrega Agrupada', ");
		querySBuilder.append("'P','Pedido Ejercicio', ");
		querySBuilder.append("'T','Caratulada', ");
		querySBuilder.append("    'TA','Caratulada Ajustada', ");
		querySBuilder.append("'TC','Caratulada Cerrada', ");
		querySBuilder.append("'V','Voceo', 'EG','En Garantia', 'CC','Caucionada', ");
		querySBuilder.append("'Sin Codificar') estado,  mprod.id matProductoID, wd.estadoActual,mp.POSICION ");
		querySBuilder.append("from ");
		querySBuilder.append("mat_operacion mp ");
		querySBuilder.append("inner join mat_registro mr on (mr.matoperacion_id = mp.id) ");
		querySBuilder.append("inner join mat_producto mprod on (mprod.id = mp.matproducto_id) ");
		querySBuilder.append("inner join workflow_state ws on (ws.id = mr.state_id) ");
		querySBuilder.append("inner join workflow_definition wd on (wd.id = ws.workflowdefinition_id) ");
		querySBuilder.append("inner join mercado merc on (merc.id = mp.mercado_id) ");
		querySBuilder.append("inner join sys_config sysc on (sysc.id = 'id_mercado_matba') ");
		querySBuilder.append("inner join cuenta_cliente ctacte on (ctacte.id = mp.cuentacliente_id) ");
		querySBuilder.append("left outer join operador_de_mesa op on (op.id = ctacte.operadordemesa_id) ");
		querySBuilder.append("left outer join mat_base destino on (destino.id = mp.destino_id) ");
		querySBuilder.append("left join mat_registro regCancelado on (mr.liquida_a_id = regCancelado.id) ");
		querySBuilder.append("left join mat_operacion opCancelada on (regCancelado.matoperacion_id = opCancelada.id) ");
		querySBuilder.append("left outer join moneda mon on (mon.id = mp.moneda_id) ");
		querySBuilder.append("where    mp.fechabaja is null ");
		querySBuilder.append("and mr.fechabaja is null ");
		querySBuilder.append("and mp.cuentacliente_id =  ");
		querySBuilder.append(cuenta.getId());
		querySBuilder.append(" ");
		querySBuilder.append("and merc.tipomercado = 'F' ");
		querySBuilder.append(listaDeMercad);
		// Se Agrega Filtro Fecha
		querySBuilder.append("and trunc(mp.fecha) >= to_date('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getDesde())
				+ "','yyyy-MM-dd') ");
		querySBuilder.append("and trunc(mp.fecha) <= to_date('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getHasta())
				+ "','yyyy-MM-dd') ");
		// Se Agrega Filtro Fecha
		querySBuilder.append("and mr.state_id =( ");
		querySBuilder.append("select id from workflow_state ws ");
		querySBuilder.append("where ws.fechabaja is null ");
		querySBuilder.append("and ws.id = mr.state_id ");
		querySBuilder.append("and ws.workflowdefinition_id in( ");
		querySBuilder.append("select wd.id from workflow_definition wd ");
		querySBuilder.append("where wd.entidad = 'MatRegistro' ");
		querySBuilder.append("AND (wd.estadoactual IN ('F', 'C', 'AJ', 'J', 'E','O', 'OA', 'EG','CC') ");
		querySBuilder.append("OR (wd.estadoactual   = 'I' ").append("AND TRUNC(mp.fecha)  >= TRUNC(sysdate-1) ");
		querySBuilder.append("AND TRUNC(mp.fecha)  <= TRUNC(sysdate) )) ");
		querySBuilder.append("and wd.fechabaja is null)) ");
		querySBuilder.append("Group By   merc.nombre,  mp.tipo, ");
		querySBuilder.append("DECODE( ");
		querySBuilder.append("mp.tipo, ");
		querySBuilder.append("'F','Futuros', ");
		querySBuilder.append("'T','Futuros', ");
		querySBuilder.append("'C','Opciones', ");
		querySBuilder.append("'P','Opciones', ");
		querySBuilder.append("'A','Acciones', ");
		querySBuilder.append("'L','Titulos', ");
		querySBuilder.append("'Otros'), mprod.nombre, ");
		querySBuilder.append("DECODE(mp.tipo,'C',mp.precio,'P',mp.precio,null), ");
		querySBuilder.append("DECODE(mp.tipo,'F','Futuro','T','Futuro sobre base','I','Inmediato', ");
		querySBuilder.append(" 'C','Call','P','Put','A','Acciones','N','Fci','L','Titulos', 'Otros'), ");
		querySBuilder.append("DECODE(wd.estadoActual,'A','Anulada','AJ','Candelado por Ajuste', ");
		querySBuilder.append("'C','Cancelada','E','Expirado','F','Abierta','I','Ingresada', ");
		querySBuilder.append("'J','Ejercicio','M','Ingresada Manual','O','Oferta Entrega', ");
		querySBuilder.append("'OA','Oferta Entrega Agrupada','P','Pedido Ejercicio', ");
		querySBuilder.append("'T','Caratulada','TA','Caratulada Ajustada', ");
		querySBuilder.append(
				"'TC','Caratulada Cerrada','V','Voceo','EG','En Garantia','CC','Caucionada','Sin Codificar'), mprod.codigointerno, ");
		querySBuilder.append(" (CASE WHEN mprod.fechaVto IS NOT NULL THEN to_char(mprod.fechavto, 'yyyy-mm-dd') ");
		querySBuilder.append(" ELSE ' ' END) ,mprod.id,wd.estadoActual, mp.POSICION ");
		querySBuilder.append(
				" Having SUM(CASE WHEN (mp.tipo = 'N') THEN (CASE WHEN (mp.condicion = 'R' OR mp.condicion = 'A') ");
		querySBuilder.append(" THEN -COALESCE(mp.cantidadCtaPartes,0) ELSE COALESCE(mp.cantidadCtaPartes,0) END) ");
		querySBuilder.append(
				" ELSE (CASE WHEN (mp.condicion = 'V' OR mp.condicion = 'A') THEN -COALESCE(mp.volumen,0) ELSE COALESCE(mp.volumen,0) END) END) <> 0 ");
		return querySBuilder;
	}

	/**
	 *
	 * @param rangoFecha
	 * @param cuenta
	 * @param listaDeMercad
	 * @return
	 * @throws Exception
	 */
	private StringBuilder obtenerResumenAbiertas(RangoFechaType rangoFecha, CuentaType cuenta, String listaDeMercad)
			throws Exception {

		StringBuilder querySBuilder = new StringBuilder();

		querySBuilder.append("	select   merc.nombre mercado, mp.tipo tipo_sigla, ");
		querySBuilder.append("DECODE( mp.tipo, ");
		querySBuilder.append("      'F','Futuro', ");
		querySBuilder.append("       'T','Futuro sobre base', ");
		querySBuilder.append("'I','Inmediato', ");
		querySBuilder.append("'C','Call', ");
		querySBuilder.append("'P','Put', ");
		querySBuilder.append("'A','Acciones', ");
		querySBuilder.append("'N','Fci', ");
		querySBuilder.append("'L','Titulos', ");
		querySBuilder.append("'Otros') tipo_operacion, ");
		querySBuilder.append(" DECODE( ");
		querySBuilder.append(" mp.tipo, ");
		querySBuilder.append(" 'F','Futuros', ");
		querySBuilder.append(" 'T','Futuros', ");
		querySBuilder.append(" 'C','Opciones', ");
		querySBuilder.append(" 'P','Opciones', ");
		querySBuilder.append(" 'A','Acciones', ");
		querySBuilder.append("    'L','Titulos', ");
		querySBuilder.append("'Otros') tipo, ");
		querySBuilder.append(" mprod.nombre producto, ");
		querySBuilder.append("mprod.codigointerno, ");
		querySBuilder.append("(CASE WHEN mprod.fechaVto IS NOT NULL THEN to_char(mprod.fechavto, 'yyyy-mm-dd') ");
		querySBuilder.append("ELSE ' ' END) as fechaVto, ");

		//volumen
		querySBuilder
				.append("  SUM(CASE WHEN (mp.tipo = 'N') THEN (CASE WHEN (mp.condicion = 'R' OR mp.condicion = 'A') ");
		querySBuilder.append(" THEN -COALESCE(mp.cantidadCtaPartes,0) ELSE COALESCE(mp.cantidadCtaPartes,0) END) ");
		querySBuilder
				.append("  ELSE (CASE WHEN (mp.condicion = 'V' OR mp.condicion = 'A') THEN -COALESCE(mp.volumen,0) ");
		querySBuilder.append("  ELSE COALESCE(mp.volumen,0) END) END) as volumen, ");

		querySBuilder.append("DECODE(mp.tipo, ");
		querySBuilder.append("'C',mp.precio, ");
		querySBuilder.append("'P',mp.precio, ");
		querySBuilder.append(" null) precio_ejercicio, ");
		querySBuilder.append("SUM(mp.cantidadctapartes) cantidadctapartes,  ");

		querySBuilder.append("ROUND(SUM(CASE WHEN (mp.tipo = 'N') THEN (mp.precioOPrima * ");
		querySBuilder.append("(CASE WHEN mp.moneda_id = 1718 THEN (select cm.promedio from moneda_cotizacion cm  ");
		querySBuilder.append("where cm.fecha = mp.fecha  and cm.moneda_id = 1718 and cm.cotizada_id = 1717  ");
		querySBuilder.append(" and cm.banco_id = 19558201  ");
		querySBuilder.append(" and cm.fechaBaja is null and rownum=1) ELSE 1 END) * mp.cantidadCtaPartes) ");
		querySBuilder.append("ELSE (mp.precioOPrima *  ");
		querySBuilder.append(" (CASE WHEN mp.moneda_id = 1718 THEN (select cm.promedio from moneda_cotizacion cm ");
		querySBuilder.append("  where cm.fecha = mp.fecha  and cm.moneda_id = 1718 and cm.cotizada_id = 1717 ");
		querySBuilder.append("and cm.banco_id = 19558201  ");
		querySBuilder.append(" and cm.fechaBaja is null and rownum=1) ELSE 1 END) * mp.volumen) END) / ");
		querySBuilder.append(" SUM(CASE WHEN (mp.tipo = 'N') THEN mp.cantidadCtaPartes ELSE mp.volumen END),7) as preciooprima, ");


		querySBuilder.append("SUM(mp.cantidadctapartes) MontoSuscripto, ");
		querySBuilder.append("  DECODE( ");
		querySBuilder.append("wd.estadoActual, ");
		querySBuilder.append("'A','Anulada', ");
		querySBuilder.append("'AJ','Candelado por Ajuste', ");
		querySBuilder.append("'C','Cancelada', ");
		querySBuilder.append("'E','Expirado', ");
		querySBuilder.append("'F','Abierta', ");
		querySBuilder.append("'I','Ingresada', ");
		querySBuilder.append("'J','Ejercicio', ");
		querySBuilder.append("'M','Ingresada Manual', ");
		querySBuilder.append("'O','Oferta Entrega', ");
		querySBuilder.append("'OA','Oferta Entrega Agrupada', ");
		querySBuilder.append("'P','Pedido Ejercicio', ");
		querySBuilder.append("'T','Caratulada', ");
		querySBuilder.append("'TA','Caratulada Ajustada', ");
		querySBuilder.append("'TC','Caratulada Cerrada', ");
		querySBuilder.append("'EG', 'En Garantia', 'CC', 'Caucionada', ");
		querySBuilder.append("'V','Voceo', ");
		querySBuilder.append("'Sin Codificar') estado,  mprod.id matProductoID, wd.estadoActual, mp.POSICION ");
		querySBuilder.append("from ");
		querySBuilder.append("mat_operacion mp ");
		querySBuilder.append("inner join mat_registro mr on (mr.matoperacion_id = mp.id) ");
		querySBuilder.append("inner join mat_producto mprod on (mprod.id = mp.matproducto_id) ");
		querySBuilder.append("inner join workflow_state ws on (ws.id = mr.state_id) ");
		querySBuilder.append("inner join workflow_definition wd on (wd.id = ws.workflowdefinition_id) ");
		querySBuilder.append("inner join mercado merc on (merc.id = mp.mercado_id) ");
		querySBuilder.append("inner join sys_config sysc on (sysc.id = 'id_mercado_matba') ");
		querySBuilder.append("inner join cuenta_cliente ctacte on (ctacte.id = mp.cuentacliente_id) ");
		querySBuilder.append("left outer join operador_de_mesa op on (op.id = ctacte.operadordemesa_id) ");
		querySBuilder.append("left outer join mat_base destino on (destino.id = mp.destino_id) ");
		querySBuilder.append("left join mat_registro regCancelado on (mr.liquida_a_id = regCancelado.id) ");
		querySBuilder.append("left join mat_operacion opCancelada on (regCancelado.matoperacion_id = opCancelada.id) ");
		querySBuilder.append("left outer join moneda mon on (mon.id = mp.moneda_id) ");
		querySBuilder.append("where    mp.fechabaja is null ");
		querySBuilder.append("and mr.fechabaja is null ");

		// Se Agrega Filtro Fecha
		querySBuilder.append("and trunc(mp.fecha) >= to_date('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getDesde())
				+ "','yyyy-MM-dd') ");
		querySBuilder.append("and trunc(mp.fecha) <= to_date('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getHasta())
				+ "','yyyy-MM-dd') ");
		// Se Agrega Filtro Fecha

		querySBuilder.append("and mp.cuentacliente_id =  ");
		querySBuilder.append(cuenta.getId());
		querySBuilder.append(" ");
		querySBuilder.append("and merc.tipomercado = 'F' ");
		querySBuilder.append(listaDeMercad);
		querySBuilder.append("and mr.state_id =( ");
		querySBuilder.append("select id from workflow_state ws ");
		querySBuilder.append("where ws.fechabaja is null ");
		querySBuilder.append("and ws.id = mr.state_id ");
		querySBuilder.append("and ws.workflowdefinition_id in( ");
		querySBuilder.append("select wd.id from workflow_definition wd ");
		querySBuilder.append("where wd.entidad = 'MatRegistro' ");
		querySBuilder.append("and wd.estadoactual = 'F' ");
		querySBuilder.append("and wd.fechabaja is null)) ");



		querySBuilder.append("	GROUP BY merc.nombre, mp.tipo, ");
		querySBuilder.append(" DECODE( mp.tipo, 'F', 'Futuros', 'T', 'Futuros', 'C', 'Opciones', 'P', 'Opciones', 'A', 'Acciones', 'L', 'Titulos', 'Otros'), ");
		querySBuilder.append(" mprod.nombre, ");
		querySBuilder.append(" DECODE(mp.tipo, 'C', mp.precio, 'P', mp.precio, NULL), ");
		querySBuilder.append(" DECODE(mp.tipo, 'F', 'Futuro', 'T', 'Futuro sobre base', 'I', 'Inmediato', 'C', 'Call', 'P', 'Put', 'A', 'Acciones', 'N', 'Fci', 'L', ");
		querySBuilder.append(" 'Titulos', 'Otros'), ");
		querySBuilder.append(" DECODE(wd.estadoActual, 'A', 'Anulada', 'AJ', 'Candelado por Ajuste', 'C', 'Cancelada', 'E', 'Expirado', 'F', 'Abierta', 'I', ");
		querySBuilder.append("'Ingresada', 'J', 'Ejercicio', 'M', 'Ingresada Manual', 'O', 'Oferta Entrega', 'OA', 'Oferta Entrega Agrupada', 'P', ");
		querySBuilder.append("'Pedido Ejercicio', 'T', 'Caratulada', 'TA', 'Caratulada Ajustada', 'TC', 'Caratulada Cerrada', 'EG', 'En Garantia', 'CC', ");
		querySBuilder.append("'Caucionada', 'V', 'Voceo', 'Sin Codificar' ), ");
		querySBuilder.append("mprod.codigointerno, ");
		querySBuilder.append(" (CASE WHEN mprod.fechaVto IS NOT NULL THEN TO_CHAR(mprod.fechavto, 'yyyy-mm-dd') ");
		querySBuilder.append("	ELSE ' 'END), mprod.id, wd.estadoActual, mp.POSICION ");
		querySBuilder.append("	HAVING ");
		querySBuilder.append(" SUM(CASE WHEN (mp.tipo = 'N') THEN (CASE WHEN (mp.condicion = 'R' OR mp.condicion = 'A') THEN -COALESCE(mp.cantidadCtaPartes, 0) ");
		querySBuilder.append("	ELSE COALESCE(mp.cantidadCtaPartes, 0) END) ELSE (CASE WHEN (mp.condicion = 'V' OR mp.condicion = 'A') THEN -COALESCE(mp.volumen, 0) ");
		querySBuilder.append("	ELSE COALESCE(mp.volumen, 0) END) END) <> 0 ");

		return querySBuilder;
	}

	/**
	 *
	 * @param rangoFecha
	 * @param cuenta
	 * @param listaDeMercad
	 * @return
	 */
	private StringBuilder obtenerOperacionesEstadisticas(RangoFechaType rangoFecha, CuentaType cuenta,
			String listaDeMercad) {

		StringBuilder querySBuilder = new StringBuilder();

		querySBuilder.append("SELECT mp.condicion condicion, ")
				.append("merc.nombre mercado, op.nombre operador, ")
				.append("mp.tipo tipo_sigla, mp.cantidadctapartes, ")
				.append("DECODE(mp.tipo, 'F','Futuros', 'T','Futuros', 'C','Opciones', 'P','Opciones', ")
				.append("'A','Acciones', 'L','Titulos', 'Otros') tipo, ")
				.append("mprod.nombre producto, ")
				.append("(CASE WHEN mp.mercado_id IN('83095463','-11','-13') ")
				.append("THEN ' ' ELSE SUBSTR(mp.posicion,1,2)|| '/' || SUBSTR(mp.posicion,3,4) END) as posicion, ")
				.append("DECODE(mp.tipo, 'C',mp.precio, 'P',mp.precio, null) precio_ejercicio, ")
				.append("mp.preciooprima preciooprima, mp.preciooprima monto_suscripto, destino.nombre destino, ")
				.append("mr.nroregistro registro, mp.fechaalta fecha_alta, ")
				.append("DECODE( mp.matcontrato_id, null,0, mp.volumen / mprod.volumencontrato) en_contratos, ")
				.append(" (CASE WHEN (mp.tipo = 'N') THEN (CASE WHEN (mp.condicion = 'R' OR mp.condicion = 'A') ")
				.append(" THEN -COALESCE(mp.cantidadCtaPartes,0) ELSE COALESCE(mp.cantidadCtaPartes,0) END) ")
				.append("  ELSE (CASE WHEN (mp.condicion = 'V' OR mp.condicion = 'A') THEN -COALESCE(mp.volumen,0) ")
				.append("  ELSE COALESCE(mp.volumen,0) END) END) as volumen, ")
 				.append("DECODE(mp.tipo, 'F','Futuro', 'T','Futuro sobre base', 'I','Inmediato', ")
				.append("'C','Call', 'P','Put', 'A','Acciones', 'N','Fci', 'L','Titulos','Otros') tipo_operacion, ")
				.append("DECODE(wd.estadoActual,'A','Anulada','AJ','Candelado por Ajuste', 'C','Cancelada','E','Expirado', ")
				.append("'F','Abierta', 'I','Ingresada', 'J','Ejercicio', 'M','Ingresada Manual', 'O','Oferta Entrega', ")
				.append("'OA','Oferta Entrega Agrupada','P','Pedido Ejercicio', 'T','Caratulada', 'TA','Caratulada Ajustada', ")
				.append("'TC','Caratulada Cerrada','V','Voceo', 'EG','En Garantia', 'CC','Caucionada', 'Sin Codificar') estado, ")
				.append("regCancelado.nroregistro as Reg_Cancela, opCancelada.fecha as Fecha_Inicial, ")
				.append("opCancelada.precioOPrima as Precio_Inicial, mon.simbolo moneda, ")
				.append("mprod.codigointerno, ")
				.append("(CASE WHEN(mprod.producto_id IN(91736654,419254) AND mprod.mercado_id IN('83095463','-11','-13')) ")
				.append("THEN mprod.fechavto END) as fechaVto, mprod.id matProductoID, wd.estadoActual, mp.fecha as fecha_operacion ")
				.append("from mat_operacion mp ")
				.append("inner join mat_registro mr on (mr.matoperacion_id = mp.id) ")
				.append("inner join mat_producto mprod on (mprod.id = mp.matproducto_id) ")
				.append("inner join workflow_state ws on (ws.id = mr.state_id) ")
				.append("inner join workflow_definition wd on (wd.id = ws.workflowdefinition_id) ")
				.append("inner join mercado merc on (merc.id = mp.mercado_id) ")
				.append("inner join sys_config sysc on (sysc.id = 'id_mercado_matba') ")
				.append("inner join cuenta_cliente ctacte on (ctacte.id = mp.cuentacliente_id) ")
				.append("left outer join operador_de_mesa op on (op.id = ctacte.operadordemesa_id) ")
				.append("left outer join mat_base destino on (destino.id = mp.destino_id) ")
				.append("left join mat_registro regCancelado on (mr.liquida_a_id = regCancelado.id) ")
				.append("left join mat_operacion opCancelada on (regCancelado.matoperacion_id = opCancelada.id) ")
				.append("left outer join moneda mon on (mon.id = mp.moneda_id) ")
				.append("WHERE mp.fechabaja              IS NULL ")
				.append("AND mr.fechabaja                IS NULL ")
				.append("and trunc(mp.fechaalta) >= to_date('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getDesde())
						+ "','yyyy-MM-dd') ")
				.append("and trunc(mp.fechaalta) <= to_date('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getHasta())
						+ "','yyyy-MM-dd') ")
				.append("AND mp.cuentacliente_id          =")
				.append(cuenta.getId())
				.append(" ")
				.append("AND merc.tipomercado             = 'F' ")
				.append(listaDeMercad)
				.append("AND mr.state_id                  = ")
				.append("(SELECT id ")
				.append("FROM workflow_state ws ")
				.append("WHERE ws.fechabaja           IS NULL ")
				.append("AND ws.id                     = mr.state_id ")
				.append("AND ws.workflowdefinition_id IN ")
				.append("(SELECT wd.id ")
				.append("FROM workflow_definition wd ")
				.append("WHERE wd.entidad      ='MatRegistro' ")
				.append("AND (wd.estadoactual IN ('F', 'C', 'AJ', 'J', 'E','O', 'OA', 'EG','CC') ")
				.append("OR (wd.estadoactual   = 'I' ")
				.append("AND TRUNC(mp.fecha)  >= TRUNC(sysdate-1) ")
				.append("AND TRUNC(mp.fecha)  <= TRUNC(sysdate) )) ")
				.append("AND wd.fechabaja     IS NULL ")
				.append(") ").append(") ");

		return querySBuilder;
	}

	/**
	 *
	 * @param rangoFecha
	 * @param cuenta
	 * @param listaDeMercad
	 * @return
	 */
	private StringBuilder obtenerOperacionesAbiertas(RangoFechaType rangoFecha, CuentaType cuenta,
			String listaDeMercad) {

		StringBuilder querySBuilder = new StringBuilder();

		querySBuilder.append("SELECT mp.condicion condicion, ").append("merc.nombre mercado, op.nombre operador, ")
				.append("mp.tipo tipo_sigla, mp.cantidadctapartes, ")

				.append("DECODE(mp.tipo, 'F','Futuros', 'T','Futuros', 'C','Opciones', 'P','Opciones', ")
				.append("'A','Acciones', 'L','Titulos', 'Otros') tipo, ").append("mprod.nombre producto, ")

				.append("(CASE WHEN mp.mercado_id IN('83095463','-11','-13') ")
				.append("THEN ' ' ELSE SUBSTR(mp.posicion,1,2)|| '/' || SUBSTR(mp.posicion,3,4) END) as posicion, ")
				.append("DECODE(mp.tipo, 'C',mp.precio, 'P',mp.precio, null) precio_ejercicio, ")

				.append(" mp.preciooprima monto_suscripto, destino.nombre destino, ")
				.append("mr.nroregistro registro, mp.fechaalta fecha_alta, ")
				.append("DECODE( mp.matcontrato_id, null,0, mp.volumen / mprod.volumencontrato) en_contratos, ")

				.append(" (CASE WHEN (mp.tipo = 'N') THEN (CASE WHEN (mp.condicion = 'R' OR mp.condicion = 'A') ")
				.append(" THEN -COALESCE(mp.cantidadCtaPartes,0) ELSE COALESCE(mp.cantidadCtaPartes,0) END) ")
				.append("  ELSE (CASE WHEN (mp.condicion = 'V' OR mp.condicion = 'A') THEN -COALESCE(mp.volumen,0) ")
				.append("  ELSE COALESCE(mp.volumen,0) END) END) as volumen, ")

				.append("DECODE(mp.tipo, 'F','Futuro', 'T','Futuro sobre base', 'I','Inmediato', ")
				.append("'C','Call', 'P','Put', 'A','Acciones', 'N','Fci', 'L','Titulos','Otros') tipo_operacion, ")

				.append("DECODE(wd.estadoActual,'A','Anulada','AJ','Candelado por Ajuste', 'C','Cancelada','E','Expirado', ")
				.append("'F','Abierta', 'I','Ingresada', 'J','Ejercicio', 'M','Ingresada Manual', 'O','Oferta Entrega', ")
				.append("'OA','Oferta Entrega Agrupada','P','Pedido Ejercicio', 'T','Caratulada', 'TA','Caratulada Ajustada', ")
				.append("'EG', 'En Garantia', 'CC', 'Caucionada', ")
				.append("'TC','Caratulada Cerrada','V','Voceo', 'Sin Codificar') estado, ")

				.append("regCancelado.nroregistro as Reg_Cancela, opCancelada.fecha as Fecha_Inicial, ")
				.append("opCancelada.precioOPrima as Precio_Inicial, mon.simbolo moneda, ")
				.append("mprod.codigointerno, ")
				.append("(CASE WHEN(mprod.producto_id IN(91736654,419254) AND mprod.mercado_id IN('83095463','-11','-13')) ")
				.append("THEN mprod.fechavto END) as fechaVto, mprod.id matProductoID, wd.estadoActual,  ")
				.append("mprod.codigointerno, mp.PRECIOOPRIMA as preciooprima, mp.fecha as fecha_operacion ") // preciooprima

//				.append("ROUND((CASE WHEN (mp.tipo = 'N') THEN (mp.precioOPrima * ")
//				.append("(CASE WHEN mp.moneda_id = 1718 THEN (select cm.promedio from moneda_cotizacion cm  ")
//				.append("where cm.fecha = mp.fecha  and cm.moneda_id = 1718 and cm.cotizada_id = 1717  ")
//				.append(" and cm.banco_id = 19558201  ")
//				.append(" and cm.fechaBaja is null and rownum=1) ELSE 1 END) * mp.cantidadCtaPartes) ")
//				.append("ELSE (mp.precioOPrima *  ")
//				.append(" (CASE WHEN mp.moneda_id = 1718 THEN (select cm.promedio from moneda_cotizacion cm ")
//				.append("  where cm.fecha = mp.fecha  and cm.moneda_id = 1718 and cm.cotizada_id = 1717 ")
//				.append("and cm.banco_id = 19558201  ")
//				.append(" and cm.fechaBaja is null and rownum=1) ELSE 1 END) * mp.volumen) END) / ")
//				.append(" (CASE WHEN (mp.tipo = 'N') THEN mp.cantidadCtaPartes ELSE mp.volumen END),7) as preciooprima ")

				.append("from mat_operacion mp ")
				.append("inner join mat_registro mr on (mr.matoperacion_id = mp.id) ")
				.append("inner join mat_producto mprod on (mprod.id = mp.matproducto_id) ")
				.append("inner join workflow_state ws on (ws.id = mr.state_id) ")
				.append("inner join workflow_definition wd on (wd.id = ws.workflowdefinition_id) ")
				.append("inner join mercado merc on (merc.id = mp.mercado_id) ")
				.append("inner join sys_config sysc on (sysc.id = 'id_mercado_matba') ")
				.append("inner join cuenta_cliente ctacte on (ctacte.id = mp.cuentacliente_id) ")
				.append("left outer join operador_de_mesa op on (op.id = ctacte.operadordemesa_id) ")
				.append("left outer join mat_base destino on (destino.id = mp.destino_id) ")
				.append("left join mat_registro regCancelado on (mr.liquida_a_id = regCancelado.id) ")
				.append("left join mat_operacion opCancelada on (regCancelado.matoperacion_id = opCancelada.id) ")
				.append("left outer join moneda mon on (mon.id = mp.moneda_id) ")
				.append("where ")
				.append("mp.fechabaja is null and mr.fechabaja is null ")
				.append("and trunc(mp.fechaalta) >= to_date('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getDesde())
						+ "','yyyy-MM-dd') ")
				.append("and trunc(mp.fechaalta) <= to_date('" + DateUtil.ToString.yyyyMMdd(rangoFecha.getHasta())
						+ "','yyyy-MM-dd') ")
				.append("AND mp.cuentacliente_id          =")
				.append(cuenta.getId())
				.append(" ")
				.append("and merc.tipomercado = 'F' ")
				.append(listaDeMercad)
				.append("and mr.state_id =( ")
				.append("select id from workflow_state ws ")
				.append("where ws.fechabaja is null ")
				.append("and ws.id = mr.state_id ")
				.append("and ws.workflowdefinition_id in( ")
				.append("select wd.id from workflow_definition wd ")
				.append("where wd.entidad = 'MatRegistro' ")
				.append("and wd.estadoactual = 'F' ")
				.append("and wd.fechabaja is null ")
				.append(") ")
				.append(") ");

		return querySBuilder;
	}


	/**
	 *
	 * @param clienteId
	 * @param ProductoId
	 * @param precioPrimaNuevo
	 */
	protected Double calcularPrecioPrima(String clienteId, Long ProductoId, Double precioPrimaNuevo) throws Exception {

		Double precioPrimaProm=0D;
		try {

		String qryOperacionesDesagrupadas = "SELECT op.id as ope, " +
				"CASE WHEN (pr.subFamilia = 'O') THEN op.condicion ELSE 'S' END AS condicion, op.tipo, " +
                "CASE WHEN (op.tipo = 'N') THEN (op.precioOPrima*(CASE WHEN op.moneda_id=1718 THEN (select c.promedio from moneda_cotizacion c where " +
                "c.fecha = op.fecha and c.moneda_id = 1718 and c.cotizada_id = 1717 and c.banco_id = 19558201 and c.fechaBaja is null and rownum=1) ELSE 1 END)*op.cantidadCtaPartes)/op.cantidadCtaPartes " +
                "ELSE (op.precioOPrima*(CASE WHEN op.moneda_id=1718 THEN (select c.promedio from moneda_cotizacion c where " +
                "c.fecha = op.fecha and c.moneda_id = 1718 and c.cotizada_id = 1717 and c.banco_id = 19558201 and c.fechaBaja is null and rownum=1) ELSE 1 END)*op.volumen)/NULLIF(op.volumen,0) END AS precioOPrima, "+
        		"CASE WHEN (op.condicion = 'V' or op.condicion = 'A') THEN -COALESCE(op.volumen,0) ELSE COALESCE(op.volumen,0) END AS volumen, " +
        		"op.fecha as fecha, "+
        		"op.cantidadctapartes "+
                "FROM mat_operacion op "+
                "JOIN mat_producto pr ON op.matproducto_id = pr.id "+
                "JOIN mat_registro re ON re.matoperacion_id = op.id "+
                "JOIN cuenta_cliente cc ON cc.id = op.cuentaCliente_id "+
                "JOIN workflow_state ws ON re.state_id = ws.id "+
                "JOIN workflow_definition wd ON ws.workflowdefinition_id = wd.id "+
                "WHERE op.mercado_id=83095463 AND re.accion = 'A' " +
                "AND (wd.estadoActual = 'F' or wd.estadoActual = 'CC') "+
                "AND op.regristraZeni = 1 "+
                "AND op.cuentaCliente_id = " + clienteId + " " +
                "AND pr.id = " + ProductoId + " " +
                "and sysdate > TRUNC(op.fechaliq) "+
                "and pr.codigoInterno <> 'FCI' "+
                "and pr.tipoProducto NOT IN ('H','Q') "+
                //agrupa operaciones FCI cuyo volumen sea igual a 1
                "union all "+
                "SELECT op.id as ope, "+
                "CASE WHEN (pr.subFamilia = 'O') THEN op.condicion ELSE 'S' END, op.tipo, " +
                "CASE WHEN (op.tipo = 'N') THEN (op.precioOPrima * op.cantidadCtaPartes) ELSE (op.precioOPrima * op.volumen) END / CASE WHEN (op.tipo = 'N') THEN op.cantidadCtaPartes ELSE op.volumen END AS precioOPrima, "+
                "CASE WHEN (op.condicion = 'R' OR op.condicion = 'A') THEN -COALESCE(op.volumen,0) ELSE COALESCE(op.volumen,0) END AS volumen, "+
                "op.fecha as fecha, "+
                "CASE WHEN (op.condicion = 'R') THEN -op.cantidadctapartes ELSE op.cantidadctapartes END "+
                "FROM mat_operacion op "+
                "JOIN moneda mon ON op.moneda_id = mon.id "+
                "JOIN mat_producto pr ON op.matproducto_id = pr.id "+
                "JOIN mat_registro re ON re.matoperacion_id = op.id "+
                "JOIN cuenta_cliente cc ON cc.id = op.cuentaCliente_id "+
                "JOIN workflow_state ws ON re.state_id = ws.id "+
                "JOIN workflow_definition wd ON ws.workflowdefinition_id = wd.id "+
                "WHERE op.mercado_id=83095463 AND re.accion = 'A' " +
                "AND (wd.estadoActual = 'F' or wd.estadoActual = 'CC') "+
                "AND op.regristraZeni = 1 "+
                "AND op.cuentaCliente_id = " +  clienteId + " " +
                "AND pr.id = " + ProductoId + " " +
                "and (op.VOLUMEN = 1) "+
                "and pr.codigoInterno = 'FCI' "+
                //agrupa operaciones FCI cuyo volumen sea distinto de 1
                "union all "+
                "SELECT op.id as ope, " +
                "CASE WHEN (pr.subFamilia = 'O') THEN op.condicion ELSE 'S' END, op.tipo, " +
                "CASE WHEN (op.tipo = 'N') THEN (op.precioOPrima * op.cantidadCtaPartes) ELSE (op.precioOPrima * op.volumen) END / CASE WHEN (op.tipo = 'N') THEN op.cantidadCtaPartes ELSE op.volumen END AS precioOPrima, "+
                "CASE WHEN (op.condicion = 'R' OR op.condicion = 'A') THEN -COALESCE(op.volumen,0) ELSE COALESCE(op.volumen,0) END AS volumen, "+
                "op.fecha as fecha, "+
                "CASE WHEN (op.condicion = 'R') THEN -op.cantidadctapartes ELSE op.cantidadctapartes END "+
                "FROM mat_operacion op "+
                "JOIN moneda mon ON op.moneda_id = mon.id "+
                "JOIN mat_producto pr ON op.matproducto_id = pr.id "+
                "JOIN mat_registro re ON re.matoperacion_id = op.id "+
                "JOIN cuenta_cliente cc ON cc.id = op.cuentaCliente_id "+
                "JOIN workflow_state ws ON re.state_id = ws.id "+
                "JOIN workflow_definition wd ON ws.workflowdefinition_id = wd.id "+
                "WHERE op.mercado_id=83095463 AND re.accion = 'A' " +
				"AND (wd.estadoActual = 'F' or wd.estadoActual = 'CC') "+
                "AND op.regristraZeni = 1 "+
                "AND op.cuentaCliente_id = " + clienteId + " " +
                "AND pr.id = " + ProductoId + " " +
                "and (op.VOLUMEN <> 1) "+
                "and pr.codigoInterno = 'FCI' "+
                " ORDER BY fecha,ope,volumen";



		final List<Object[]> operacionesRDA = new ArrayList<Object[]>();

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {


				while (rset.next()) {
					Object[] data  = new Object[7];

					data [0] =rset.getObject("ope");
					data [1] =rset.getObject("condicion");
					data [2] =rset.getObject("tipo");
					data [3] =rset.getObject("precioOPrima");
					data [4] =rset.getObject("volumen");
					data [5] =rset.getObject("fecha");
					data [6] =rset.getObject("cantidadctapartes");
					operacionesRDA.add(data);
				}
			}
		};

		ZeniQueryExcecutor.excecuteSelect(qryOperacionesDesagrupadas, resb);

		ArrayList<Object[]> copy = new ArrayList<Object[]>(operacionesRDA);
		Double totalVentas = 0D;
		Double primaXVolumen = 0D;
		Double sumaVolumen = 0D;
		Boolean bandera = false;

		if(!operacionesRDA.isEmpty()){
			bandera = true;
			for (Object[] obj : operacionesRDA) {
				Double volumen = getVolumen(obj[1], obj[2], obj[4], obj[6]);
				if(volumen!=null && (volumen<0D)){
					totalVentas+= (volumen);
					copy.remove(obj);
				}
			}
		}
		if(totalVentas<0){
			Boolean primerPositivo = false;
			for (Object[] obj : copy) {
				Double volumen = getVolumen(obj[1], obj[2], obj[4], obj[6]);
				Double prima = (obj[3]!=null)?Double.parseDouble(obj[3].toString()):1D;
				totalVentas += volumen;
				if(totalVentas>0){
					if(!primerPositivo){
						primerPositivo=true;
						sumaVolumen+=totalVentas;
						primaXVolumen+=(prima*totalVentas);
					}else{
						Double v2 = getVolumen(obj[1], obj[2], obj[4], obj[6]);
						sumaVolumen+=v2;
						primaXVolumen+=(prima*v2);
					}
				}
			}
			precioPrimaProm = primaXVolumen / ((sumaVolumen!=0)?sumaVolumen:1D);
		}else{
			for (Object[] obj : copy) {
				Double prima = (obj[3]!=null)?Double.parseDouble(obj[3].toString()):1D;
				Double volumen = getVolumen(obj[1], obj[2], obj[4], obj[6]);
				sumaVolumen+=volumen;
				primaXVolumen+=(prima*volumen);
			}
			precioPrimaProm = primaXVolumen / ((sumaVolumen!=0)?sumaVolumen:1D);
		}
		if(!bandera)
			precioPrimaProm = precioPrimaNuevo;
		}catch (Exception e) {
			ZeniContextServer.getInstance().printErrorLog(e);
			throw e;
		}

		return precioPrimaProm;

	}

	/**
	 *
	 * @param condicion
	 * @param tipo
	 * @param volumen
	 * @param cuotaPartes
	 * @return
	 */
	private Double getVolumen(Object condicion, Object tipo, Object volumen, Object cuotaPartes) {

		if (condicion.toString().equals("S") && tipo.toString().equals("N"))
			return (cuotaPartes == null) ? 0D : Double.parseDouble(cuotaPartes.toString());
		else
			return (volumen == null) ? 0D : Double.parseDouble(volumen.toString());

	}
	/**
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	private List<Float> getPrecioActual(String id) throws Exception {
		final List<Float> _returnOperaciones = new ArrayList<Float>();

		StringBuffer querySBuilder = new StringBuffer();

		querySBuilder.append(" select mpa.precioAjuste from mat_precio_ajuste mpa ");
		querySBuilder.append(" where mpa.matProducto_id = '" + id + "' ");
		querySBuilder.append("and mpa.fechaAlta =(select max(mpa2.fechaAlta) ");
		querySBuilder.append("		 			from mat_precio_ajuste mpa2 ");
		querySBuilder.append("		 			where mpa2.matProducto_id = '" + id + "'");
		querySBuilder.append("		 			and mpa2.fechaBaja is null) ");
		querySBuilder.append(" and mpa.fechaBaja is null ");

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {

				if (rset.next()) {
					_returnOperaciones.add(rset.getFloat("precioAjuste"));
				}
			}
		};
		ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
		return _returnOperaciones;
	}

	@Override
	public ArrayOfOMatComisionType obtenerMatComision(AuthType auth, ArrayOfProductoMATType productos,
			ArrayOfMercadoType mercados, String tipodeoperacion, EnumCompradorVendedorType condicion,
			ArrayOfTipoComisionType tipodecomision, ArrayOfIdCuenta cuentas) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerMatComision");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(productos);
		ZeniContextServer.getInstance().printVerboLog(mercados);
		ZeniContextServer.getInstance().printVerboLog(tipodeoperacion);
		ZeniContextServer.getInstance().printVerboLog(condicion);
		ZeniContextServer.getInstance().printVerboLog(tipodecomision);
		ZeniContextServer.getInstance().printVerboLog(cuentas);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			if (cuentas == null || cuentas.getCuentas() == null || cuentas.getCuentas().size() <= 0) {
				throw new ZeniServerExeption("Los id de la cuenta no puede ser nulo o vacio",
						FAULTCONSTANTS.ERROR_ERRONEO);
			}
			if (mercados == null) {
				throw new ZeniServerExeption("Los mercados no pueden ser nulo", FAULTCONSTANTS.ERROR_ERRONEO);
			}
			if (condicion == null) {
				throw new ZeniServerExeption("La condicion no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
			}
			if (productos == null) {
				throw new ZeniServerExeption("Los productos no pueden ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
			}
			if (tipodeoperacion != null && (tipodeoperacion.length() != 1)) {
				throw new ZeniServerExeption("El tipodeoperacion tiene que tener solo 1 caracter",
						FAULTCONSTANTS.ERROR_ERRONEO);
			}
			String filtroProductos = ZeniContextServer.VACIO;
			if (productos != null && productos.getProductos() != null && productos.getProductos().size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < productos.getProductos().size(); i++) {
					sb.append(",").append(productos.getProductos().get(i).getMatId());
				}
				filtroProductos = sb.deleteCharAt(0).insert(0, " AND MC.MATPRODUCTO_ID IN (").append(") ").toString();
			}
			String filtroMercados = ZeniContextServer.VACIO;
			if (mercados != null && mercados.getMercados() != null && mercados.getMercados().size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mercados.getMercados().size(); i++) {
					sb.append(",").append(mercados.getMercados().get(i).getId());
				}
				filtroMercados = sb.deleteCharAt(0).insert(0, " AND MC.MERCADO_ID IN (").append(") ").toString();
			}
			String filtroComision = ZeniContextServer.VACIO;
			if (tipodecomision != null && tipodecomision.getComisiones() != null
					&& tipodecomision.getComisiones().size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < tipodecomision.getComisiones().size(); i++) {
					sb.append(",'").append(tipodecomision.getComisiones().get(i).getTipo()).append("'");
				}
				filtroComision = sb.deleteCharAt(0).insert(0, " AND MC.TIPO_COMISION IN (").append(") ").toString();
			}
			String filtroCondicion = ZeniContextServer.VACIO;
			if (condicion.compareTo(EnumCompradorVendedorType.AMBOS) == 0) {
			} else if (condicion.compareTo(EnumCompradorVendedorType.COMPRADOR) == 0) {
				filtroCondicion = " AND MC.CONDICION = 'C' ";
			} else {
				filtroCondicion = " AND MC.CONDICION = 'V' ";
			}
			String filtroCuentas = ZeniContextServer.VACIO;
			if (cuentas != null && cuentas.getCuentas() != null && cuentas.getCuentas().size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < cuentas.getCuentas().size(); i++) {
					sb.append(",").append(cuentas.getCuentas().get(i));
				}
				filtroCuentas = sb.deleteCharAt(0).insert(0, " AND MC.CUENTACLIENTE_ID IN (").append(") ").toString();
			}
			String filtroOperacion = ZeniContextServer.VACIO;
			if (tipodeoperacion != null) {
				filtroOperacion = " AND MC.TIPO_OPERACION = '" + tipodeoperacion + "' ";
			}
			final ArrayOfOMatComisionType _return = new ArrayOfOMatComisionType();
			final ArrayList<MatComisionType> _returnMatComisionType = new ArrayList<MatComisionType>();
			final StringBuilder querySBuilder = new StringBuilder().append("SELECT ")
					.append("CC.DENOMINACIONCUENTA CUENTA, ").append("MERC.NOMBRE MERCADO, ")
					.append("MP.NOMBRE PRODUCTO, ")
					.append("DECODE(MC.TIPO_OPERACION,'F','Futuro','O','Opcion','T','Futuro Sobre Base','Otros') TIPO_OPERACION, ")
					.append("DECODE(MC.CONDICION,'C','Compra','V','Venta','Otros') CONDICION, ")
					.append("DECODE(MC.TIPO_COMISION,'A','Comision Apertura de Operaciones','N','Comision Cancelacion de Operaciones','D','Comision Apertura Day Trade', ")
					.append("'T','Comision Cancelacion Day Trade', ")
					.append("'V','Comision Operaciones de Voceo','C','Comisin Caratula','Otros') TIPO_COMISION, ")
					.append("DECODE(MC.TIPO_TASA,'P','Porcentaje','I','Importe','Otros') TIPO_TASA, ")
					.append("MC.IMPORTE_TASA VALOR ").append("FROM MAT_COMISION MC ")
					.append("INNER JOIN MAT_PRODUCTO MP ON (MC.MATPRODUCTO_ID=MP.ID) ")
					.append("INNER JOIN MERCADO MERC ON (MERC.ID = MC.MERCADO_ID) ")
					.append("INNER JOIN CUENTA_CLIENTE CC ON (CC.ID=MC.CUENTACLIENTE_ID) ")
					.append("WHERE MC.FECHABAJA IS NULL ").append("AND MC.IMPORTE_TASA <> 0 ").append(filtroProductos)
					.append(filtroComision).append(filtroMercados).append(filtroCondicion).append(filtroCuentas)
					.append(filtroOperacion).append("ORDER BY MERC.NOMBRE, MP.NOMBRE, MC.CONDICION");
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						MatComisionType c = new MatComisionType();
						c.setCondicion(rset.getString("CONDICION"));
						c.setCuenta(rset.getString("CUENTA"));
						c.setMercado(rset.getString("MERCADO"));
						c.setProducto(rset.getString("PRODUCTO"));
						c.setTipocomision(rset.getString("TIPO_COMISION"));
						c.setTipooperacion(rset.getString("TIPO_OPERACION"));
						c.setTipotasa(rset.getString("TIPO_TASA"));
						c.setValor(rset.getFloat("VALOR"));
						_returnMatComisionType.add(c);
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
			_return.getOperaciones().addAll(_returnMatComisionType);
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
	public FileType descargarTenencia(AuthType auth, String nombreArchivo) throws FaultType_Exception {

		ZeniContextServer.getInstance().printInfoLog("Executing operation descargarTenencia");
		ZeniContextServer.getInstance().printInfoLog("nombreArchivo {" + nombreArchivo + "}");

		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

			if (nombreArchivo == null || nombreArchivo.equals(ZeniContextServer.VACIO)) {
				throw new ZeniServerExeption("El nombre del archivo no puede ser nulo o vacio",
						FAULTCONSTANTS.ERROR_ERRONEO);
			}

			if (nombreArchivo.indexOf('-') <= 0) {
				throw new ZeniServerExeption("El nombre del archivo es incorrecto", FAULTCONSTANTS.ERROR_ERRONEO);
			}

			String fecha = nombreArchivo.substring(0, nombreArchivo.indexOf('-'));
			String cuenta = nombreArchivo.substring(nombreArchivo.indexOf('-') + 1, nombreArchivo.length());

			final URL fileremote = new URL(
					ZeniContextServer.getInstance().getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_URL_TENENCIA)
							+ ZeniContextServer.getInstance()
									.getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_PARAM_1_TENENCIA)
							+ fecha + ZeniContextServer.getInstance()
									.getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_PARAM_2_TENENCIA)
							+ cuenta);

			ZeniContextServer.getInstance()
					.printInfoLog("fileRemote: " + (fileremote != null ? fileremote.toString() : "null"));

			URLConnection connection = fileremote.openConnection();

			connection.setConnectTimeout(15 * 1000);
			String fileName = ZeniHelper.getFileName(connection.getHeaderFields());

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
				if (icon != null)
					icon.close();
				if (bifin != null)
					bifin.close();
				if (bais != null)
					bais.flush();
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

				throw new ZeniServerExeption("No se pudo descargar el archivo{" + nombreArchivo + "}",
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
	 */
	public ArrayOfTenenciaValorizada obtenerTenenciasValorizadas(AuthType auth, String cuentaId, FechaTimeType fecha)
			throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerTenenciasValorizadas");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printInfoLog(cuentaId);
		ZeniContextServer.getInstance().printInfoLog(fecha != null ? "" + fecha.getFecha() : null);

		ArrayOfTenenciaValorizada list = new ArrayOfTenenciaValorizada();

		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

			if (cuentaId == null || cuentaId.equals(ZeniContextServer.VACIO)) {
				throw new ZeniServerExeption("El id de la cuenta no puede ser nulo o vacio",
						FAULTCONSTANTS.ERROR_ERRONEO);
			}
			if (fecha == null || fecha.getFecha() == null) {
				throw new ZeniServerExeption("La fecha no puede ser  nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
			}

			String fechaTenencia = DateUtil.ToString.dateFormat(fecha.getFecha(), "dd/MM/yyyy");

			final URL fileremote = new URL(
					ZeniContextServer.getInstance().getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_URL_TENENCIA)
							+ ZeniContextServer.getInstance()
									.getProperty(ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_PARAM_1_TENENCIA)
							+ fechaTenencia + ZeniContextServer.getInstance().getProperty(
									ZeniContextServer.APPL_PROPERTIES.DOWNLOAD_PARAM_2_TENENCIA)
							+ cuentaId);

			ZeniContextServer.getInstance()
					.printInfoLog("fileRemote: " + (fileremote != null ? fileremote.toString() : "null"));

			URLConnection connection = fileremote.openConnection();
			connection.setConnectTimeout(15 * 1000);

			String fileName = ZeniHelper.getFileName(connection.getHeaderFields());

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

			// Existe el archivo
			if (_returnData != null && _returnData.length > 0 && fileName != null) {

				TenenciaVal val = new TenenciaVal();

				val.setFecha(fechaTenencia);
				val.setCuenta(cuentaId);

				if (fileName != null) {
					val.setNombreArchivo(fileName);

					int desde = fileName.indexOf('_') + 1;
					int hasta = fileName.lastIndexOf('.');

					if (desde > 1 && hasta > 0 && hasta > desde) {
						val.setCuit(fileName.substring(desde, hasta));
					}

				}

				list.getTenenciaValorizada().add(val);

			} else {
				ZeniContextServer.getInstance().printInfoLog("No se encuentra el archivo");
			}

			return list;

			// return _return;

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
	 */
	public ArrayOfNovedades obtenerNovedades(AuthType auth, FechaTimeType fechaDesde, FechaTimeType fechaHasta)
			throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerNovedades");
		ZeniContextServer.getInstance()
				.printVerboLog(auth + " FechaDesde {" + (fechaDesde != null ? "" + fechaDesde.getFecha() : null)
						+ "}  FechaHasta{" + (fechaHasta != null ? "" + fechaHasta.getFecha() : null) + "}");

		final ArrayOfNovedades _return = new ArrayOfNovedades();
		final ArrayList<NovedadType> _returnNovedadesType = new ArrayList<NovedadType>();

		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());

			final StringBuilder querySBuilder = new StringBuilder();
			querySBuilder.append("SELECT * FROM ");
			querySBuilder.append("WEB_NOVEDAD WHERE 1=1 ");

			if (fechaHasta != null && fechaHasta.getFecha() != null) {
				querySBuilder.append(" AND FECHA <= '" + DateUtil.ToString.yyyyMMdd(fechaHasta.getFecha()) + "'");
			}

			if (fechaDesde != null && fechaDesde.getFecha() != null) {
				querySBuilder.append("AND FECHA >='" + DateUtil.ToString.yyyyMMdd(fechaDesde.getFecha()) + "'");
			}

			querySBuilder.append(" ORDER BY FECHA ASC ");

			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						NovedadType n = new NovedadType();
						try {
							n.setDate(DateUtil.Converters
									.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("FECHA"))));

							n.setDescription(rset.getString("DESCRIPTION"));
							n.setId(rset.getString("ID"));
							n.setTitle(rset.getString("TITLE"));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						_returnNovedadesType.add(n);
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
			_return.getNovedades().addAll(_returnNovedadesType);
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
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);

		}
	}

}
