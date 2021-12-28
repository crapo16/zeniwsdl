package ar.com.zeni.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.zeniwsdl.ArrayOfFacturaDetalleType;
import ar.com.zeni.zeniwsdl.FacturaDetalleType;

//#1589

public class ServiceFacturaDetalle {

	public ArrayOfFacturaDetalleType obtenerFacturaDetalle(int facturaid,
			String facturador, String leyenda, boolean esDolar, float cotizacion) throws ZeniDBExeption {
		ArrayOfFacturaDetalleType ret = new ArrayOfFacturaDetalleType();
		List<FacturaDetalleType> reg = new ArrayList<FacturaDetalleType>();

//		DiferenciasGarantÃ­as
		if (facturador.equals("DiferenciasGarantÃ­as")) {

			if (leyenda.startsWith("LIQUIDACIÃ“N DE DIFERENCIAS DE MERCADO A TERMINO BA")) {
				reg = getDetalleDiferenciasGarantias(facturaid, esDolar, cotizacion, true, true);
			}

			if (leyenda.startsWith("LIQUIDACIÃ“N DE GARANTÃ�AS DE MERCADO A TERMINO BA")) {
				reg = getDetalleDiferenciasGarantias(facturaid, esDolar, cotizacion, false, true);
			}

			if (leyenda.startsWith("LIQUIDACIÃ“N DE DIFERENCIAS DE ROFEX")) {
				reg = getDetalleDiferenciasGarantias(facturaid, esDolar, cotizacion, true, false);
			}

			if (leyenda.startsWith("LIQUIDACIÃ“N DE GARANTÃ�AS DE ROFEX")) {
				reg = getDetalleDiferenciasGarantias(facturaid, esDolar, cotizacion, false, false);
			}
		}

//		FacturacionRegistros-C
		if (facturador.equals("FacturacionRegistros-C")) {
			if (leyenda.equals("MERCADO A TERMINO BA Futuro - Registro CancelaciÃ³n")) {
				reg = getDetalleFacturacionC(facturaid, esDolar, cotizacion, true);
			}
			if (leyenda.equals("MERCADO A TERMINO BA OpciÃ³n - Registro CancelaciÃ³n")) {
				reg = getDetalleFacturacionC(facturaid, esDolar, cotizacion, true);
			}
			if (leyenda.equals("ROFEX Futuro - Registro CancelaciÃ³n")) {
				reg = getDetalleFacturacionC(facturaid, esDolar, cotizacion, false);
			}
		}

//		FacturacionRegistros-CE
		if (facturador.equals("FacturacionRegistros-CE")) {
			if (leyenda.equals("ROFEX Futuro - Registro Cancelada Para Entrega")) {
				reg = getDetalleFacturacionCE(facturaid, esDolar, cotizacion);
			}

		}

//		FacturacionRegistros-F
		if (facturador.equals("FacturacionRegistros-F")) {
			if (leyenda.equals("MERCADO A TERMINO BA Futuro - Registro")) {
				reg = getDetalleFacturacionF(facturaid, esDolar, cotizacion);
			}
			if (leyenda.equals("MERCADO A TERMINO BA OpciÃ³n - Registro")) {
				reg = getDetalleFacturacionF(facturaid, esDolar, cotizacion);
			}
			if (leyenda.equals("ROFEX Futuro - Registro")) {
				reg = getDetalleFacturacionF(facturaid, esDolar, cotizacion);
			}
		}

//		FacturacionRegistros-OA
		if (facturador.equals("FacturacionRegistros-OA")) {
			if (leyenda.equals("MERCADO A TERMINO BA Futuro - Oferta de Entrega Agrupada")) {
				reg = getDetalleFacturacionOA(facturaid, esDolar, cotizacion);
			}
		}

//		FacturacionRegistros-T
		if (facturador.equals("FacturacionRegistros-T")) {
			if (leyenda.equals("MERCADO A TERMINO BA Futuro - Registro CarÃ¡tulas")) {
				reg = getDetalleFacturacionT(facturaid, esDolar, cotizacion, true);
			}
			if (leyenda.equals("ROFEX Futuro - Registro CarÃ¡tulas")) {
				reg = getDetalleFacturacionT(facturaid, esDolar, cotizacion, false);
			}
		}

//		FacturacionRegistros-TC
		if (facturador.equals("FacturacionRegistros-TC")) {
			if (leyenda.equals("MERCADO A TERMINO BA Futuro - Cierre CarÃ¡tula")) {
				reg = getDetalleFacturacionTC(facturaid, esDolar, cotizacion, true);
			}
			if (leyenda.equals("ROFEX Futuro - Cierre CarÃ¡tula")) {
				reg = getDetalleFacturacionTC(facturaid, esDolar, cotizacion, false);
			}
		}
		if (ret != null) {
			ret.getFacturaDetalle().addAll(reg);
		}

		return ret;
	}

	// DiferenciasGarantÃ­as
	private List<FacturaDetalleType> getDetalleDiferenciasGarantias(int id, final boolean esDolar, float cotizacion,
			final boolean diferencia, final boolean matba)  throws ZeniDBExeption {
		final List<FacturaDetalleType> registros = new ArrayList<FacturaDetalleType>();

		final String concepto = (diferencia ? "DIF" : "GTIA") + (matba ? "MATBA" : "ROFEX");
		final String descripcion = (diferencia ? "DIFERENCIAS " : "GARANTIAS ") + (matba ? "MATBA" : "ROFEX");
		final float cotizaPor = (esDolar ? 1 : cotizacion);

		StringBuilder querySBuilder = new StringBuilder();
		querySBuilder.append(" select getDetalle_TIPO(df.leyenda) tipo, GETDETALLE_SALDOA_IMPORTE(df.leyenda) importe_SALDOA, ");
		querySBuilder.append(" GETDETALLE_SALDOD_IMPORTE(df.leyenda) importe_SALDOD ");
		querySBuilder.append(" from detalle_factura df ");
		querySBuilder.append(" where df.factura_id = " + id + " ");
		querySBuilder.append(" and getDetalle_TIPO(df.leyenda) in ('SALDOA','SALDOD') ");
		querySBuilder.append(" order by df.ORDEN ");

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				int o = 1;
				float importe;
				while (rset.next()) {
					FacturaDetalleType facturaDet = new FacturaDetalleType();
					facturaDet.setCONCEPTO(concepto);
					facturaDet.setDESCRIPCION(descripcion);
					if (rset.getString("tipo").equals("SALDOA")){
						importe = rset.getFloat("importe_SALDOA") * cotizaPor;
					} else {
						importe = rset.getFloat("importe_SALDOD") * cotizaPor;
					}
					facturaDet.setIMPORTE(importe);
					facturaDet.setORDEN(o);
					o++;
					// facturaDet.setBASE(null);
					// facturaDet.setPORCENTAJE(null);
					registros.add(facturaDet);
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getDetalleDiferenciasGarantias - ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
		ZeniContextServer.getInstance().printErrorLog("getDetalleDiferenciasGarantias - TERMINO: ");
		return registros;
	}


	// FacturacionRegistros-C
	private List<FacturaDetalleType> getDetalleFacturacionC(int id, final boolean esDolar, float cotizacion,
			final boolean matba)  throws ZeniDBExeption {
		final List<FacturaDetalleType> registros = new ArrayList<FacturaDetalleType>();

		final float cotizaPor = (esDolar ? 1 : cotizacion);

		StringBuilder querySBuilder = new StringBuilder();
		querySBuilder.append(" select getDetalle_TIPO(df.leyenda) TIPO, GETDETALLE_PRESAL_IMPORTE(df.leyenda) PRESAL_IMPORTE,");
		querySBuilder.append(" GETDETALLE_TREGIS_IMPORTE(df.leyenda) TREGIS_IMPORTE, GETDETALLE_COMISI_IMPORTE(df.leyenda) COMISI_IMPORTE, ");
		querySBuilder.append(" GETDETALLE_PRIMAO_IMPORTE(df.leyenda) PRIMAO_IMPORTE, GETDETALLE_IVA_TASA(df.leyenda) IVA_TASA,   ");
		querySBuilder.append(" GETDETALLE_IVA_IMPORTE(df.leyenda) IVA_IMPORTE, GETDETALLE_IIBB_TASA(df.leyenda) IIBB_TASA, ");
		querySBuilder.append(" df.importevendedor ");
		querySBuilder.append(" from detalle_factura df  ");
		querySBuilder.append(" where df.factura_id = " + id + " ");
		querySBuilder.append(" and getDetalle_TIPO(df.leyenda) in ('COMISI', 'PRESAL','TREGIS','PRIMAO','IVA---','P-IIBB') ");
		querySBuilder.append(" order by df.orden ");

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				int o = 1;
				String descripcion = "";
				float importe = 0;
				String concepto = "CANCELA";
				while (rset.next()) {
					FacturaDetalleType facturaDet = new FacturaDetalleType();

					concepto = "CANCELA";
					if (rset.getString("TIPO").equals("COMISI")){
						descripcion = "CANCELACION  COMISION";
						importe = rset.getFloat("COMISI_IMPORTE") * cotizaPor;
					}
					if (rset.getString("TIPO").equals("PRESAL")){
						descripcion = "CANCELACION  DIFERENCIA";
						importe = rset.getFloat("PRESAL_IMPORTE") * cotizaPor;
					}
					if (rset.getString("TIPO").equals("TREGIS")){
						descripcion = "CANCELACION  TASAREGISTRO";
						importe = rset.getFloat("TREGIS_IMPORTE") * cotizaPor;
					}
					if (rset.getString("TIPO").equals("PRIMAO")){
						descripcion = "CANCELACION DE REGISTRO";
						importe = rset.getFloat("PRIMAO_IMPORTE") * cotizaPor;
					}
					if (rset.getString("TIPO").equals("IVA---")){
						concepto = "IVA";
						descripcion = "IVA";
						facturaDet.setBASE(rset.getFloat("IVA_IMPORTE"));
						facturaDet.setPORCENTAJE(rset.getFloat("IVA_TASA"));
						importe = rset.getFloat("importevendedor");
					}
					if (rset.getString("TIPO").equals("P-IIBB")){
						concepto = "PERCIB";
						descripcion = "PERCEPCIONIIBB";
						facturaDet.setBASE(rset.getFloat("IIBB_TASA"));
						facturaDet.setPORCENTAJE(rset.getFloat("IIBB_TASA"));
						importe = rset.getFloat("importevendedor");
					}

					facturaDet.setCONCEPTO(concepto);
					facturaDet.setDESCRIPCION(descripcion);
					facturaDet.setIMPORTE(importe);
					facturaDet.setORDEN(o);
					o++;
					registros.add(facturaDet);
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionC - ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionC - TERMINO: ");
		return registros;
	}

//	FacturacionRegistros-CE
	private List<FacturaDetalleType> getDetalleFacturacionCE(int id, final boolean esDolar, float cotizacion)  throws ZeniDBExeption {
		final List<FacturaDetalleType> registros = new ArrayList<FacturaDetalleType>();

		final float cotizaPor = (esDolar ? 1 : cotizacion);

		StringBuilder querySBuilder = new StringBuilder();
		querySBuilder.append(" select getDetalle_TIPO(df.leyenda) TIPO, GETDETALLE_PRESAL_IMPORTE(df.leyenda) PRESAL_IMPORTE, ");
		querySBuilder.append(" df.importevendedor ");
		querySBuilder.append(" from detalle_factura df  ");
		querySBuilder.append(" where df.factura_id = " + id + " ");
		querySBuilder.append(" and getDetalle_TIPO(df.leyenda) in ('PRESAL') ");
		querySBuilder.append(" order by df.orden ");

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				int o = 1;
				String descripcion = "";
				float importe = 0;
				String concepto = "CANCELA";
				while (rset.next()) {
					FacturaDetalleType facturaDet = new FacturaDetalleType();
					if (rset.getString("TIPO").equals("PRESAL")){
						descripcion = "CANCELACION  PENTREGA";
						importe = rset.getFloat("PRESAL_IMPORTE") * cotizaPor;
					}
//					facturaDet.setBASE(null);
//					facturaDet.setPORCENTAJE(null);
					facturaDet.setCONCEPTO(concepto);
					facturaDet.setDESCRIPCION(descripcion);
					facturaDet.setIMPORTE(importe);
					facturaDet.setORDEN(o);
					o++;
					registros.add(facturaDet);
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionCE - ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionCE - TERMINO: ");
		return registros;
	}

//	FacturacionRegistros-F
	private List<FacturaDetalleType> getDetalleFacturacionF(int id, final boolean esDolar, float cotizacion)  throws ZeniDBExeption {
		final List<FacturaDetalleType> registros = new ArrayList<FacturaDetalleType>();

		final float cotizaPor = (esDolar ? 1 : cotizacion);

		StringBuilder querySBuilder = new StringBuilder();
		querySBuilder.append(" select getDetalle_TIPO(df.leyenda) TIPO,   ");
		querySBuilder.append(" GETDETALLE_IVA_TASA(df.leyenda) IVA_TASA, GETDETALLE_IVA_IMPORTE(df.leyenda) IVA_IMPORTE, ");
		querySBuilder.append(" df.importevendedor ");
		querySBuilder.append(" from detalle_factura df  ");
		querySBuilder.append(" where df.factura_id = " + id + " ");
		querySBuilder.append(" and getDetalle_TIPO(df.leyenda) in ('TASATC','IVA---') ");
		querySBuilder.append(" order by df.orden ");

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				int o = 1;
				String descripcion = "";
				float importe = 0;
				String concepto = "";
				while (rset.next()) {
					FacturaDetalleType facturaDet = new FacturaDetalleType();
					if (rset.getString("TIPO").equals("TASATC")){
						concepto = "REGISTRO";
						descripcion = "TASA REGISTRO";
						importe = rset.getFloat("importevendedor");
					}
					if (rset.getString("TIPO").equals("IVA---")){
						concepto = "IVA";
						descripcion = "IVA";
						facturaDet.setBASE(rset.getFloat("IVA_IMPORTE"));
						facturaDet.setPORCENTAJE(rset.getFloat("IVA_TASA"));
						importe = rset.getFloat("importevendedor");
					}
					facturaDet.setCONCEPTO(concepto);
					facturaDet.setDESCRIPCION(descripcion);
					facturaDet.setIMPORTE(importe);
					facturaDet.setORDEN(o);
					o++;
					registros.add(facturaDet);
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionF - ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionF - TERMINO: ");
		return registros;
	}

//	FacturacionRegistros-CE
	private List<FacturaDetalleType> getDetalleFacturacionOA(int id, final boolean esDolar, float cotizacion)  throws ZeniDBExeption {
		final List<FacturaDetalleType> registros = new ArrayList<FacturaDetalleType>();

		final float cotizaPor = (esDolar ? 1 : cotizacion);

		StringBuilder querySBuilder = new StringBuilder();
		querySBuilder.append(" select getDetalle_TIPO(df.leyenda) TIPO, GETDETALLE_PRESAL_IMPORTE(df.leyenda) PRESAL_IMPORTE, ");
		querySBuilder.append(" df.importevendedor ");
		querySBuilder.append(" from detalle_factura df  ");
		querySBuilder.append(" where df.factura_id = " + id + " ");
		querySBuilder.append(" and getDetalle_TIPO(df.leyenda) in ('PRESAL') ");
		querySBuilder.append(" order by df.orden ");

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				int o = 1;
				String descripcion = "";
				float importe = 0;
				String concepto = "ENTAGRUP";
				while (rset.next()) {
					FacturaDetalleType facturaDet = new FacturaDetalleType();
					if (rset.getString("TIPO").equals("PRESAL")){
						descripcion = "OFERTAENTREGAAGRUPADA";
						importe = rset.getFloat("PRESAL_IMPORTE") * cotizaPor;
					}
//					facturaDet.setBASE(null);
//					facturaDet.setPORCENTAJE(null);
					facturaDet.setCONCEPTO(concepto);
					facturaDet.setDESCRIPCION(descripcion);
					facturaDet.setIMPORTE(importe);
					facturaDet.setORDEN(o);
					o++;
					registros.add(facturaDet);
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionOA - ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionOA - TERMINO: ");
		return registros;
	}

	// FacturacionRegistros-T
	private List<FacturaDetalleType> getDetalleFacturacionT(int id, final boolean esDolar, float cotizacion,
			final boolean matba)  throws ZeniDBExeption {
		final List<FacturaDetalleType> registros = new ArrayList<FacturaDetalleType>();

		final float cotizaPor = (esDolar ? 1 : cotizacion);

		StringBuilder querySBuilder = new StringBuilder();
		querySBuilder.append(" select getDetalle_TIPO(df.leyenda) TIPO, ");
		querySBuilder.append(" GETDETALLE_IVA_IMPORTE(df.leyenda) IVA_IMPORTE, GETDETALLE_IVA_TASA(df.leyenda) IVA_TASA,  ");
		querySBuilder.append(" df.importevendedor ");
		querySBuilder.append(" from detalle_factura df  ");
		querySBuilder.append(" where df.factura_id = " + id + " ");
		querySBuilder.append(" and getDetalle_TIPO(df.leyenda) in ('TASATC','IVA---','TRCARA','RETSEL') ");
		querySBuilder.append(" order by df.orden ");

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				int o = 1;
				String descripcion = "";
				float importe = 0;
				String concepto = "";
				while (rset.next()) {
					FacturaDetalleType facturaDet = new FacturaDetalleType();
					if (rset.getString("TIPO").equals("TASATC")){
						concepto = "REGCARATULA";
						descripcion = "TASA REGISTRO CARATULA";
						importe = rset.getFloat("importevendedor");
					}
					if (rset.getString("TIPO").equals("TRCARA")){
						concepto = "REGCARATULA";
						descripcion = "TASA REGISTRO CARATULA";
						importe = rset.getFloat("importevendedor");
					}
					if (rset.getString("TIPO").equals("RETSEL")){
						concepto = "RETSELLADO";
						descripcion = "RETENCION SELLADO";
						importe = rset.getFloat("importevendedor");
					}
					if (rset.getString("TIPO").equals("IVA---")){
						concepto = "IVA";
						descripcion = "IVA";
						facturaDet.setBASE(rset.getFloat("IVA_IMPORTE"));
						facturaDet.setPORCENTAJE(rset.getFloat("IVA_TASA"));
						importe = rset.getFloat("importevendedor");
					}
					facturaDet.setCONCEPTO(concepto);
					facturaDet.setDESCRIPCION(descripcion);
					facturaDet.setIMPORTE(importe);
					facturaDet.setORDEN(o);
					o++;
					registros.add(facturaDet);
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionT - ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionT - TERMINO: ");
		return registros;
	}

	// FacturacionRegistros-TC
	private List<FacturaDetalleType> getDetalleFacturacionTC(int id, final boolean esDolar, float cotizacion,
			final boolean matba)  throws ZeniDBExeption {
		final List<FacturaDetalleType> registros = new ArrayList<FacturaDetalleType>();

		final float cotizaPor = (esDolar ? 1 : cotizacion);

		StringBuilder querySBuilder = new StringBuilder();
		querySBuilder.append(" select getDetalle_TIPO(df.leyenda) TIPO, GETDETALLE_CTAORD_IMPORTE(df.leyenda) CTAORD_IMPORTE, ");
		querySBuilder.append(" GETDETALLE_RETEVE_IMPORTE(df.leyenda) RETEVE_IMPORTE, GETDETALLE_TREGIS_IMPORTE(df.leyenda) TREGIS_IMPORTE,  ");
		querySBuilder.append(" GETDETALLE_IVA_TASA(df.leyenda) IVA_TASA, GETDETALLE_IVA_IMPORTE(df.leyenda) IVA_IMPORTE, ");
		querySBuilder.append(" df.importevendedor ");
		querySBuilder.append(" from detalle_factura df  ");
		querySBuilder.append(" where df.factura_id = " + id + " ");
		querySBuilder.append(" and getDetalle_TIPO(df.leyenda) in ('CTAORD','RETEVE','IVA---','TREGIS') ");
		querySBuilder.append(" order by df.orden ");


		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				int o = 1;
				String descripcion = "";
				float importe = 0;
				String concepto = "";
				while (rset.next()) {
					FacturaDetalleType facturaDet = new FacturaDetalleType();
					if (rset.getString("TIPO").equals("CTAORD")){
						concepto = "CIERRECAR";
						descripcion = "CIERRE CARATULA";
						importe = rset.getFloat("CTAORD_IMPORTE") * cotizaPor;
					}
					if (rset.getString("TIPO").equals("RETEVE")){
						concepto = "CIERRECAR";
						descripcion = "CIERRE CARATULA";
						importe = rset.getFloat("RETEVE_IMPORTE") * cotizaPor;
					}
					if (rset.getString("TIPO").equals("TREGIS")){
						concepto = "CIERRECAR";
						descripcion = "CIERRE CARATULA";
						importe = rset.getFloat("TREGIS_IMPORTE") * cotizaPor;
					}
					if (rset.getString("TIPO").equals("IVA---")){
						concepto = "IVA";
						descripcion = "IVA";
						facturaDet.setBASE(rset.getFloat("IVA_IMPORTE"));
						facturaDet.setPORCENTAJE(rset.getFloat("IVA_TASA"));
						importe = rset.getFloat("importevendedor");
					}
					facturaDet.setCONCEPTO(concepto);
					facturaDet.setDESCRIPCION(descripcion);
					facturaDet.setIMPORTE(importe);
					facturaDet.setORDEN(o);
					o++;
					registros.add(facturaDet);
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionTC - ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
		ZeniContextServer.getInstance().printErrorLog("getDetalleFacturacionTC - TERMINO: ");
		return registros;
	}


}
