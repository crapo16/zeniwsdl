package ar.com.zeni.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.zeniwsdl.ArrayOfFacturaRegistroType;
import ar.com.zeni.zeniwsdl.FacturaRegistroType;

// #1589

public class ServiceFacturaRegistro {

	public ArrayOfFacturaRegistroType obtenerFacturaRegistro(int facturaid,
			String facturador, String leyenda) throws ZeniDBExeption {
		ArrayOfFacturaRegistroType ret = new ArrayOfFacturaRegistroType();
		List<FacturaRegistroType> reg = new ArrayList<FacturaRegistroType>();

//		DiferenciasGarantÃ­as
		if (facturador.equals("DiferenciasGarantÃ­as")) {
			if (leyenda
					.startsWith("LIQUIDACIÃ“N DE DIFERENCIAS DE MERCADO A TERMINO BA")) {
				reg = getRegistroDiferenciasGarantias(facturaid, true, true);
			}

			if (leyenda
					.startsWith("LIQUIDACIÃ“N DE GARANTÃ�AS DE MERCADO A TERMINO BA")) {
				reg = getRegistroDiferenciasGarantias(facturaid, false, true);
			}

			if (leyenda.startsWith("LIQUIDACIÃ“N DE DIFERENCIAS DE ROFEX")) {
				reg = getRegistroDiferenciasGarantias(facturaid, true, false);
			}
//
			if (leyenda.startsWith("LIQUIDACIÃ“N DE GARANTÃ�AS DE ROFEX")) {
				reg = getRegistroDiferenciasGarantias(facturaid, false, false);
			}
		}

//		FacturacionRegistros-C
		if (facturador.equals("FacturacionRegistros-C")) {
			if (leyenda.startsWith("MERCADO A TERMINO BA Futuro - Registro CancelaciÃ³n")) {
				reg = getRegistroC(facturaid);
			}
			if (leyenda.startsWith("MERCADO A TERMINO BA OpciÃ³n - Registro CancelaciÃ³n")) {
				reg = getRegistroC(facturaid);
			}
			if (leyenda.startsWith("ROFEX Futuro - Registro CancelaciÃ³n")) {
				reg = getRegistroC(facturaid);
			}
		}

//		FacturacionRegistros-F
		if (facturador.equals("FacturacionRegistros-F")) {
			if (leyenda.startsWith("MERCADO A TERMINO BA Futuro - Registro")) {
				reg = getRegistroF(facturaid, false, false);
			}
			if (leyenda.startsWith("MERCADO A TERMINO BA OpciÃ³n - Registro")) {
				reg = getRegistroF(facturaid, true, false);
			}
			if (leyenda.startsWith("ROFEX Futuro - Registro")) {
				reg = getRegistroF(facturaid, false, true);
			}
		}

//		FacturacionRegistros-CE
		if (facturador.equals("FacturacionRegistros-CE")) {
			reg = getRegistroCE(facturaid);
		}

//		FacturacionRegistros-OA
		if (facturador.equals("FacturacionRegistros-OA")) {
			reg = getRegistroOA(facturaid);
		}

//		FacturacionRegistros-T
		if (facturador.equals("FacturacionRegistros-T")) {
			if (leyenda.startsWith("MERCADO A TERMINO BA Futuro - Registro CarÃ¡tulas")) {
				reg = getRegistroT(facturaid, true);
			} else {
				reg = getRegistroT(facturaid, false);
			}
		}

//		FacturacionRegistros-TC
		if (facturador.equals("FacturacionRegistros-TC")) {
			reg = getRegistroTC(facturaid);
		}

		if (ret != null) {
			ret.getFacturaRegistro().addAll(reg);
		}

		return ret;
	}

	// DiferenciasGarantÃ­as
	private List<FacturaRegistroType> getRegistroDiferenciasGarantias(int id,
			final boolean diferencia, final boolean matba)
			throws ZeniDBExeption {
		final List<FacturaRegistroType> registros = new ArrayList<FacturaRegistroType>();

		final String concepto = (diferencia ? "Diferencias" : "Garantias")
				+ (matba ? "Matba" : "Rofex");

		StringBuilder querySBuilder = new StringBuilder();

		if (diferencia) {
			querySBuilder.append(" select mr.id idregistro, mr.fechaalta,mr.nroregistro,mo.preciooprima,mdg.diferencia monto");
			querySBuilder.append("  FROM mat_dif_y_gtia mdg ");
			querySBuilder.append("	inner join mat_dif_o_gtia_a_liquidar mdgl ON  mdg.MATDIFERENCIAALIQUIDAR_ID= mdgl.id ");
			querySBuilder.append(" 	inner join factura_producto fp                      ON  fp.id= mdgl.factura_id ");
			querySBuilder.append("  inner join mat_registro     mr                        ON  mr.id= mdg.MATREGISTRO_ID ");
			querySBuilder.append("	inner join mat_operacion mo                        ON  mo.id= mr.matoperacion_id ");
			querySBuilder.append(" where fp.id = " + id + " ");
			querySBuilder.append(" order by mr.NROREGISTRO ");
		} else {
			querySBuilder.append(" select mr.id idregistro, mr.fechaalta,mr.nroregistro,mo.preciooprima,mdg.garantia monto");
			querySBuilder.append("  FROM mat_dif_y_gtia mdg ");
			querySBuilder.append("	inner join mat_dif_o_gtia_a_liquidar mdgl ON  mdg. MATGARANTIAALIQUIDAR_ID= mdgl.id ");
			querySBuilder.append(" 	inner join factura_producto fp                      ON  fp.id= mdgl.factura_id ");
			querySBuilder.append("  inner join mat_registro     mr                        ON  mr.id= mdg.MATREGISTRO_ID ");
			querySBuilder.append("   inner join mat_operacion mo                        ON  mo.id= mr.matoperacion_id ");
			querySBuilder.append(" where fp.id = " + id + " ");
			querySBuilder.append(" order by mr.NROREGISTRO ");
		}

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				while (rset.next()) {
					try {
						FacturaRegistroType facturareg = new FacturaRegistroType();
						facturareg.setIDREGISTRO(rset.getInt("idregistro"));
						facturareg.setCONCEPTO(concepto);
						facturareg.setFECHA(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers
										.yyyyMMdd(rset.getString("fechaalta"))));
						facturareg.setREGISTRO(rset.getString("nroregistro"));
						facturareg.setPRECIOOPRIMA(rset.getFloat("preciooprima"));
						facturareg.setMONTO(rset.getFloat("monto"));
						registros.add(facturareg);
					} catch (ParseException e) {
						throw new SQLException(
								"Error Query DiferenciasGarantias Registros.",
								e);
					}
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getRegistroDiferenciasGarantias ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
		ZeniContextServer.getInstance().printErrorLog("getRegistroDiferenciasGarantias TERMINO: ");
		return registros;
	}

	// Registros OA
	private List<FacturaRegistroType> getRegistroOA(int id)
			throws ZeniDBExeption {
		final List<FacturaRegistroType> registros = new ArrayList<FacturaRegistroType>();

		String querySBuilder = " select mo.precio, mo.preciooprima, mo.caratula,c.numerocontrato,  wd.estadoDestino, mo.volumen, mr.id idregistro,mr.fechaalta,mr.nroregistro   from mat_registro mr  inner join mat_operacion mo                  ON mo.id= mr.matoperacion_id  inner join rel_matoperacion_factura rof ON mo.id= rof.matoperacion_id  inner join factura_producto fp                 ON fp.id= rof.factura_id  inner join workflow_state ws                  ON ws.id = mr.state_id   inner join workflow_definition wd         ON wd.id = ws.workflowdefinition_id  left JOIN MAT_CONTRATO  mc         ON  mc.id = mo.MATCONTRATO_ID  left join contrato c                                   ON c.id = mc.CONTRATO_ID " + (" where fp.id = " + id + " ") + " order by mr.NROREGISTRO ";

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				while (rset.next()) {
					try {
						FacturaRegistroType facturareg = new FacturaRegistroType();
						facturareg.setIDREGISTRO(rset.getInt("idregistro"));
						facturareg.setCONCEPTO("OfertaEntrega");
						facturareg
								.setFECHA(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers
										.yyyyMMdd(rset.getString("fechaalta"))));
						facturareg.setREGISTRO(rset.getString("nroregistro"));
						facturareg.setPRECIOOPRIMA(rset
								.getFloat("preciooprima"));
						registros.add(facturareg);
					} catch (ParseException e) {
						throw new SQLException("Error Query Registros OA.", e);
					}
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder, resb);
		ZeniContextServer.getInstance().printErrorLog("TERMINO: ");
		return registros;
	}

	// Registros CE
	private List<FacturaRegistroType> getRegistroCE(int id)
			throws ZeniDBExeption {
		final List<FacturaRegistroType> registros = new ArrayList<FacturaRegistroType>();
		final List<Integer> ids = new ArrayList<Integer>();

		String querySBuilder = " select mo.precio, mo.preciooprima, mo.caratula,c.numerocontrato, nvl(mr.liquida_a_id,0) liquida_a_id,  wd.estadoDestino, mo.volumen, mr.id idregistro,mr.fechaalta,mr.nroregistro  from mat_registro mr  inner join mat_operacion mo                  ON mo.id= mr.matoperacion_id  inner join rel_matoperacion_factura rof ON mo.id= rof.matoperacion_id  inner join factura_producto fp                 ON fp.id= rof.factura_id  inner join workflow_state ws                  ON ws.id = mr.state_id   inner join workflow_definition wd         ON wd.id = ws.workflowdefinition_id  left JOIN MAT_CONTRATO  mc         ON  mc.id = mo.MATCONTRATO_ID  left join contrato c                                   ON c.id = mc.CONTRATO_ID " + (" where fp.id = " + id + " ") + " order by mr.NROREGISTRO ";

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				while (rset.next()) {
					try {
						FacturaRegistroType facturareg = new FacturaRegistroType();
						facturareg.setIDREGISTRO(rset.getInt("idregistro"));
						facturareg.setCONCEPTO("CancelaPentrega");
						facturareg
								.setFECHA(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers
										.yyyyMMdd(rset.getString("fechaalta"))));
						facturareg.setREGISTRO(rset.getString("nroregistro"));
						facturareg.setPRECIOOPRIMA(rset
								.getFloat("preciooprima"));
						ids.add(rset.getInt("liquida_a_id"));
						registros.add(facturareg);
					} catch (ParseException e) {
						throw new SQLException("Error Query Registros CE.", e);
					}
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getRegistroCE ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder, resb);
		ZeniContextServer.getInstance().printErrorLog("getRegistroCE TERMINO: ");

		String liqsql = "   select mr1.id idregistro, mr1.fechaalta, mr1.nroregistro,mo1.preciooprima "
				+ " from mat_registro mr1 "
				+ " inner join mat_operacion mo1 ON mo1.id= mr1.matoperacion_id "
				+ " where mr1.id = iiiddd ";

		for (Integer liq : ids) {
			if (liq != 0) {
				String liqok = liqsql.replaceAll("iiiddd", liq.toString());
				final ResulsetObjectBuilder resliq = new ResulsetObjectBuilder() {
					@Override
					public void thisIsTheResulset(ResultSet rset)
							throws SQLException {
						while (rset.next()) {
							try {
								FacturaRegistroType facturareg = new FacturaRegistroType();
								facturareg.setIDREGISTRO(rset
										.getInt("idregistro"));
								facturareg.setCONCEPTO("CanceladaPentrega");
								facturareg
										.setFECHA(DateUtil.Converters
												.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset
														.getString("fechaalta"))));
								facturareg.setREGISTRO(rset
										.getString("nroregistro"));
								facturareg.setPRECIOOPRIMA(rset
										.getFloat("preciooprima"));
								registros.add(facturareg);
							} catch (ParseException e) {
								throw new SQLException(
										"Error Query Registros CE 2.", e);
							}
						}
					}
				};
				ZeniContextServer.getInstance().printErrorLog("getRegistroCE-2 ANTES DE EJECUTAR: ");
				ZeniQueryExcecutor.excecuteSelect(liqok, resliq);
				ZeniContextServer.getInstance().printErrorLog("getRegistroCE-2 TERMINO: ");
			}
		}

		return registros;
	}

	// Registros T
	private List<FacturaRegistroType> getRegistroT(int id, boolean matba)
			throws ZeniDBExeption {
		final List<FacturaRegistroType> registros = new ArrayList<FacturaRegistroType>();

		StringBuilder querySBuilder = new StringBuilder();
		querySBuilder.append(" select mo.precio, mo.preciooprima, mo.caratula,c.numerocontrato,  porc.porcentaje, ");
		querySBuilder.append(" wd.estadoDestino, mo.volumen, mr.id idregistro, mr.fechaalta, mr.nroregistro ");
		querySBuilder.append(" 	from mat_registro mr ");
		querySBuilder.append(" inner join mat_operacion mo                  ON mo.id= mr.matoperacion_id ");
		querySBuilder.append(" inner join rel_matoperacion_factura rof ON mo.id= rof.matoperacion_id ");
		querySBuilder.append(" inner join factura_producto fp                 ON fp.id= rof.factura_id ");
		querySBuilder.append(" inner join workflow_state ws                  ON ws.id = mr.state_id  ");
		querySBuilder.append(" inner join workflow_definition wd         ON wd.id = ws.workflowdefinition_id ");
		querySBuilder.append(" left outer join ( ");

		if (matba) {
			querySBuilder.append(" select factura_id,max(safe_to_number(nvl(replace(trim(substr(leyenda,30,(instr(leyenda,'% /OPERACION')-30))),'.'),'0'))) porcentaje ");
			querySBuilder.append(" from detalle_factura ");
			querySBuilder.append(" where leyenda like '%TASA REG. OFERTA ENTREGA%' ");
			querySBuilder.append(" and leyenda like '%%%' ");
			querySBuilder.append(" group by factura_id ");
		} else {
			querySBuilder.append(" select factura_id, max(safe_to_number(substr(leyenda,instr(leyenda,'x')+1,instr(leyenda,'%')-(instr(leyenda,'x')+1)))) porcentaje ");
			querySBuilder.append(" from detalle_factura ");
			querySBuilder.append(" where leyenda like '%BCR S/((%' ");
			querySBuilder.append(" and leyenda like '%%%' ");
			querySBuilder.append(" group by factura_id ");
		}

		querySBuilder.append(" ) porc on (porc.factura_id = fp.id) ");
		querySBuilder.append(" left JOIN MAT_CONTRATO  mc         ON  mc.id = mo.MATCONTRATO_ID ");
		querySBuilder.append(" left join contrato c                                   ON c.id = mc.CONTRATO_ID ");
		querySBuilder.append("  where fp.id = " + id + " ");
		querySBuilder.append(" order by mr.NROREGISTRO ");

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				while (rset.next()) {
					try {
						FacturaRegistroType facturareg = new FacturaRegistroType();
						facturareg.setIDREGISTRO(rset.getInt("idregistro"));
						facturareg.setCONCEPTO("Caratulada");
						facturareg
								.setFECHA(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers
										.yyyyMMdd(rset.getString("fechaalta"))));
						facturareg.setREGISTRO(rset.getString("nroregistro"));
						facturareg.setPRECIOOPRIMA(rset
								.getFloat("preciooprima"));
						facturareg.setCARATULA(rset.getString("caratula"));
						facturareg
								.setCONTRATO(rset.getString("numerocontrato"));
						facturareg.setPORCENTAJE(rset.getFloat("porcentaje"));

						float vol = rset.getFloat("volumen");
						float pp = rset.getFloat("preciooprima");
						float porc = rset.getFloat("porcentaje");
						float monto = (vol * pp * porc) / 100;

						facturareg.setMONTO(monto);

						// Calculo Porcentaje

						registros.add(facturareg);
					} catch (ParseException e) {
						throw new SQLException("Error Query Registros T.", e);
					}
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getRegistroT ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
		ZeniContextServer.getInstance().printErrorLog("getRegistroT TERMINO: ");
		return registros;
	}

	// Registros TC
	private List<FacturaRegistroType> getRegistroTC(int id)
			throws ZeniDBExeption {
		final List<FacturaRegistroType> registros = new ArrayList<FacturaRegistroType>();

		String querySBuilder = " select mo.precio, mo.preciooprima, mo.caratula,c.numerocontrato,  wd.estadoDestino, mo.volumen, mr.id idregistro,mr.fechaalta,mr.nroregistro   from mat_registro mr  inner join mat_operacion mo                  ON mo.id= mr.matoperacion_id  inner join rel_matoperacion_factura rof ON mo.id= rof.matoperacion_id  inner join factura_producto fp                 ON fp.id= rof.factura_id  inner join workflow_state ws                  ON ws.id = mr.state_id   inner join workflow_definition wd         ON wd.id = ws.workflowdefinition_id  left JOIN MAT_CONTRATO  mc         ON  mc.id = mo.MATCONTRATO_ID  left join contrato c                                   ON c.id = mc.CONTRATO_ID " + (" where fp.id = " + id + " ") + " order by mr.NROREGISTRO ";

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				while (rset.next()) {
					try {
						FacturaRegistroType facturareg = new FacturaRegistroType();
						facturareg.setIDREGISTRO(rset.getInt("idregistro"));
						facturareg.setCONCEPTO("OfertaEntrega");
						facturareg
								.setFECHA(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers
										.yyyyMMdd(rset.getString("fechaalta"))));
						facturareg.setREGISTRO(rset.getString("nroregistro"));
						facturareg.setPRECIOOPRIMA(rset
								.getFloat("preciooprima"));
						registros.add(facturareg);
					} catch (ParseException e) {
						throw new SQLException("Error Query Registros TC.", e);
					}
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getRegistroTC ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder, resb);
		ZeniContextServer.getInstance().printErrorLog("getRegistroTC TERMINO: ");
		return registros;
	}

	// Registros C
	private List<FacturaRegistroType> getRegistroC(int id) throws ZeniDBExeption {
		final List<FacturaRegistroType> registros = new ArrayList<FacturaRegistroType>();
		final List<Integer> ids = new ArrayList<Integer>();

		String querySBuilder = " select mo.precio, mo.preciooprima, mo.caratula,c.numerocontrato, nvl(mr.liquida_a_id,0) liquida_a_id,  wd.estadoDestino, mo.volumen, mr.id idregistro,mr.fechaalta,mr.nroregistro  from mat_registro mr  inner join mat_operacion mo                  ON mo.id= mr.matoperacion_id  inner join rel_matoperacion_factura rof ON mo.id= rof.matoperacion_id  inner join factura_producto fp                 ON fp.id= rof.factura_id  inner join workflow_state ws                  ON ws.id = mr.state_id   inner join workflow_definition wd         ON wd.id = ws.workflowdefinition_id  left JOIN MAT_CONTRATO  mc         ON  mc.id = mo.MATCONTRATO_ID  left join contrato c                                   ON c.id = mc.CONTRATO_ID " + (" where fp.id = " + id + " ") + " order by mr.NROREGISTRO ";

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				while (rset.next()) {
					try {
						FacturaRegistroType facturareg = new FacturaRegistroType();
						facturareg.setIDREGISTRO(rset.getInt("idregistro"));
						facturareg.setCONCEPTO("Cancela");
						facturareg.setFECHA(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechaalta"))));
						facturareg.setREGISTRO(rset.getString("nroregistro"));
						facturareg.setPRECIOOPRIMA(rset.getFloat("preciooprima"));
//						facturareg.setPORCENTAJE(null);
//						facturareg.setCARATULA(null);
//						facturareg.setCONTRATO(null);
//						facturareg.setMONTO(null);
						ids.add(rset.getInt("liquida_a_id"));
						registros.add(facturareg);
					} catch (ParseException e) {
						throw new SQLException("Error Query Registros C.", e);
					}
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getRegistroC ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder, resb);
		ZeniContextServer.getInstance().printErrorLog("getRegistroC TERMINO: ");

		String liqsql = "   select mr1.id idregistro, mr1.fechaalta, mr1.nroregistro,mo1.preciooprima "
				+ " from mat_registro mr1 "
				+ " inner join mat_operacion mo1 ON mo1.id= mr1.matoperacion_id "
				+ " where mr1.id = iiiddd ";

		for (Integer liq : ids) {
			if (liq != 0) {
				String liqok = liqsql.replaceAll("iiiddd", liq.toString());
				final ResulsetObjectBuilder resliq = new ResulsetObjectBuilder() {
					@Override
					public void thisIsTheResulset(ResultSet rset)
							throws SQLException {
						while (rset.next()) {
							try {
								FacturaRegistroType facturareg = new FacturaRegistroType();
								facturareg.setIDREGISTRO(rset.getInt("idregistro"));
								facturareg.setCONCEPTO("Cancelada");
								facturareg.setFECHA(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechaalta"))));
								facturareg.setREGISTRO(rset.getString("nroregistro"));
								facturareg.setPRECIOOPRIMA(rset.getFloat("preciooprima"));
								registros.add(facturareg);
							} catch (ParseException e) {
								throw new SQLException(
										"Error Query Registros C 2.", e);
							}
						}
					}
				};
				ZeniContextServer.getInstance().printErrorLog("getRegistroC-2 ANTES DE EJECUTAR: ");
				ZeniQueryExcecutor.excecuteSelect(liqok, resliq);
				ZeniContextServer.getInstance().printErrorLog("getRegistroC-2 TERMINO: ");
			}
		}

		return registros;
	}

	// Registros F
	private List<FacturaRegistroType> getRegistroF(int id, boolean opcion, boolean usd) throws ZeniDBExeption {
		final List<FacturaRegistroType> registros = new ArrayList<FacturaRegistroType>();
		final List<Integer> ids = new ArrayList<Integer>();
		final boolean opcion2 = opcion;

		String querySBuilder = " select mo.precio, mo.preciooprima, mo.caratula,c.numerocontrato, nvl(mr.liquida_a_id,0) liquida_a_id,  wd.estadoDestino, mo.volumen, mr.id idregistro,mr.fechaalta,mr.nroregistro  from mat_registro mr  inner join mat_operacion mo                  ON mo.id= mr.matoperacion_id  inner join rel_matoperacion_factura rof ON mo.id= rof.matoperacion_id  inner join factura_producto fp                 ON fp.id= rof.factura_id  inner join workflow_state ws                  ON ws.id = mr.state_id   inner join workflow_definition wd         ON wd.id = ws.workflowdefinition_id  left JOIN MAT_CONTRATO  mc         ON  mc.id = mo.MATCONTRATO_ID  left join contrato c                                   ON c.id = mc.CONTRATO_ID " + (" where fp.id = " + id + " ") + " order by mr.NROREGISTRO ";

		final float porc = getPorcentaje(id, usd);
		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				while (rset.next()) {
					try {
						FacturaRegistroType facturareg = new FacturaRegistroType();
						facturareg.setIDREGISTRO(rset.getInt("idregistro"));
						facturareg.setCONCEPTO("Abierta");
						facturareg.setFECHA(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechaalta"))));
						facturareg.setREGISTRO(rset.getString("nroregistro"));
						facturareg.setPRECIOOPRIMA(rset.getFloat("preciooprima"));
						facturareg.setPORCENTAJE(porc);
//						facturareg.setCARATULA(null);
//						facturareg.setCONTRATO(null);
						if (opcion2){//opcion
							facturareg.setMONTO((rset.getFloat("volumen") * rset.getFloat("preciooprima") * porc ) / 100);
						} else { //futuro
							facturareg.setMONTO((rset.getFloat("volumen") * porc ) / 100);
						}
						ids.add(rset.getInt("liquida_a_id"));
						registros.add(facturareg);
					} catch (ParseException e) {
						throw new SQLException("Error Query Registros C.", e);
					}
				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getRegistroC ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder, resb);
		ZeniContextServer.getInstance().printErrorLog("getRegistroC TERMINO: ");

		return registros;
	}

	private float getPorcentaje(int id, boolean usd) throws ZeniDBExeption {
		float ret = 0;
		final List<Float> porc = new ArrayList<Float>();
		final boolean usdF = usd;

		StringBuilder querySBuilder = new StringBuilder();
		querySBuilder.append(" select getDetalle_TIPO(df.leyenda) TIPO, GETDETALLE_TREGIS_TASA_PORC(df.leyenda) TREGIS_TASA_PORC, ");
		querySBuilder.append(" GETDETALLE_TREGIS_TASA_USD(df.leyenda) TREGIS_TASA_USD ");
		querySBuilder.append(" from detalle_factura df  ");
		querySBuilder.append(" where df.factura_id  = " + id + " ");
		querySBuilder.append(" and getDetalle_TIPO(df.leyenda) in ('TREGIS') ");
		querySBuilder.append(" order by df.orden ");

		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				if (rset.next()) {
					if (usdF){
						porc.add(rset.getFloat("TREGIS_TASA_USD"));
					} else {
						porc.add(rset.getFloat("TREGIS_TASA_PORC"));
					}

				}
			}
		};
		ZeniContextServer.getInstance().printErrorLog("getPorcentaje ANTES DE EJECUTAR: ");
		ZeniQueryExcecutor.excecuteSelect(querySBuilder.toString(), resb);
		ZeniContextServer.getInstance().printErrorLog("getPorcentaje TERMINO: ");

		if (porc.size()>0){
			ret = porc.get(0);
		}

		return ret;
	}
}
