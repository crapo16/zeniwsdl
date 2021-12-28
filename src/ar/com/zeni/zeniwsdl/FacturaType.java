package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


// #1501
// #1589

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FacturaType", propOrder = {"id","cotizacion","tipo_cpte","fecha_cpte","fecha_acred_ctacte","letra_cpte",
		"sucursal_cpte","numero_cpte","signo_iva_base","iva_base","signo_iva_no_gravado","iva_no_gravado",
		"signo_iva_total","importeiva","signo_importe","importe","moneda","condicion_cliente","cto_cliente",
		"signo_kgs_fact","kgs_facturados","precio_cto","cuit_corredor","cuit_contra","estado","fecha_vto_cpte",
		"signo_importe_a_cta","importe_a_cta","cai","fecha_vto_cai","cae","fecha_vto_cae","coefacturapapel", 
		"detalle", "registro" })

public class FacturaType {

	// #1589
	@XmlElement(name = "ID")
	protected int id;
	
	@XmlElement(name = "COTIZACION")
	protected float cotizacion;
	
	@XmlElement(name = "TIPO_CPTE")
	protected int tipo_cpte;
	
	@XmlElement(name = "FECHA_CPTE")
	protected FechaTimeType fecha_cpte;
	
	@XmlElement(name = "FECHA_ACRED_CTACTE")
	protected FechaTimeType fecha_acred_ctacte;

	@XmlElement(name = "LETRA_CPTE")
	protected String letra_cpte;

	@XmlElement(name = "SUCURSAL_CPTE")
	protected String sucursal_cpte;
	
	@XmlElement(name = "NUMERO_CPTE")
	protected String numero_cpte;
	
	@XmlElement(name = "SIGNO_IVA_BASE")
	protected String signo_iva_base;
	
	@XmlElement(name = "IVA_BASE")
	protected float iva_base;
	
	@XmlElement(name = "SIGNO_IVA_NO_GRAVADO")
	protected String signo_iva_no_gravado;
	
	@XmlElement(name = "IVA_NO_GRAVADO")
	protected float iva_no_gravado;
	
	@XmlElement(name = "SIGNO_IVA_TOTAL")
	protected String signo_iva_total;

	@XmlElement(name = "IMPORTEIVA")
	protected float importeiva;

	@XmlElement(name = "SIGNO_IMPORTE")
	protected String signo_importe;
	
	@XmlElement(name = "IMPORTE")
	protected float importe;

	@XmlElement(name = "MONEDA")
	protected int moneda;

	@XmlElement(name = "CONDICION_CLIENTE")
	protected String condicion_cliente;

	@XmlElement(name = "CTO_CLIENTE")
	protected String cto_cliente;

	@XmlElement(name = "SIGNO_KGS_FACT")
	protected String signo_kgs_fact;

	@XmlElement(name = "KGS_FACTURADOS")
	protected float kgs_facturados;

	@XmlElement(name = "PRECIO_CTO")
	protected float precio_cto;
	
	@XmlElement(name = "CUIT_CORREDOR")
	protected String cuit_corredor;
	
	@XmlElement(name = "CUIT_CONTRA")
	protected String cuit_contra;

	@XmlElement(name = "ESTADO")
	protected String estado;
	
	@XmlElement(name = "FECHA_VTO_CPTE")
	protected FechaTimeType fecha_vto_cpte;
	
	@XmlElement(name = "SIGNO_IMPORTE_A_CTA")
	protected String signo_importe_a_cta;
	
	@XmlElement(name = "IMPORTE_A_CTA")
	protected float importe_a_cta;

	@XmlElement(name = "CAI")
	protected String cai;

	@XmlElement(name = "FECHA_VTO_CAI")
	protected FechaTimeType fecha_vto_cai;
	
	@XmlElement(name = "CAE")
	protected String cae;
	
	@XmlElement(name = "FECHA_VTO_CAE")
	protected FechaTimeType fecha_vto_cae;

	@XmlElement(name = "COEFACTURAPAPEL")
	protected String coefacturapapel;
	
	@XmlElement(name="DETALLE")
	protected ArrayOfFacturaDetalleType detalle;
	
	@XmlElement(name="REGISTRO")
	protected ArrayOfFacturaRegistroType registro;
	
	// #1589
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public float getCOTIZACION() {
		return cotizacion;
	}
	
	public void setCOTIZACION(float cotiz) {
		this.cotizacion = cotiz;
	}
	
	public void setDETALLE(ArrayOfFacturaDetalleType det) {
		detalle = det;
	}	
	
	public void setREGISTRO(ArrayOfFacturaRegistroType reg) {
		registro = reg;
	}
	
	public ArrayOfFacturaDetalleType getDETALLE() {
		return detalle;
	}
	
	public ArrayOfFacturaRegistroType getREGISTRO() {
		return registro;
	}
	
	public int getTIPO_CPTE() {
		return tipo_cpte;
	}

	public void setTIPO_CPTE(int tipo_cpte) {
		this.tipo_cpte = tipo_cpte;
	}

	public FechaTimeType getFECHA_CPTE() {
		return fecha_cpte;
	}

	public void setFECHA_CPTE(FechaTimeType fecha_cpte) {
		this.fecha_cpte = fecha_cpte;
	}

	public FechaTimeType getFECHA_ACRED_CTACTE() {
		return fecha_acred_ctacte;
	}

	public void setFECHA_ACRED_CTACTE(FechaTimeType fecha_acred_ctacte) {
		this.fecha_acred_ctacte = fecha_acred_ctacte;
	}

	public String getLETRA_CPTE() {
		return letra_cpte;
	}

	public void setLETRA_CPTE(String letra_cpte) {
		this.letra_cpte = letra_cpte;
	}

	public String getSUCURSAL_CPTE() {
		return sucursal_cpte;
	}

	public void setSUCURSAL_CPTE(String sucursal_cpte) {
		this.sucursal_cpte = sucursal_cpte;
	}

	public String getNUMERO_CPTE() {
		return numero_cpte;
	}

	public void setNUMERO_CPTE(String numero_cpte) {
		this.numero_cpte = numero_cpte;
	}

	public String getSIGNO_IVA_BASE() {
		return signo_iva_base;
	}

	public void setSIGNO_IVA_BASE(String signo_iva_base) {
		this.signo_iva_base = signo_iva_base;
	}

	public float getIVA_BASE() {
		return iva_base;
	}

	public void setIVA_BASE(float iva_base) {
		this.iva_base = iva_base;
	}

	public String getSIGNO_IVA_NO_GRAVADO() {
		return signo_iva_no_gravado;
	}

	public void setSIGNO_IVA_NO_GRAVADO(String signo_iva_no_gravado) {
		this.signo_iva_no_gravado = signo_iva_no_gravado;
	}

	public float getIVA_NO_GRAVADO() {
		return iva_no_gravado;
	}

	public void setIVA_NO_GRAVADO(float iva_no_gravado) {
		this.iva_no_gravado = iva_no_gravado;
	}

	public String getSIGNO_IVA_TOTAL() {
		return signo_iva_total;
	}

	public void setSIGNO_IVA_TOTAL(String signo_iva_total) {
		this.signo_iva_total = signo_iva_total;
	}

	public float getIMPORTEIVA() {
		return importeiva;
	}

	public void setIMPORTEIVA(float importeiva) {
		this.importeiva = importeiva;
	}

	public String getSIGNO_IMPORTE() {
		return signo_importe;
	}

	public void setSIGNO_IMPORTE(String signo_importe) {
		this.signo_importe = signo_importe;
	}

	public float getIMPORTE() {
		return importe;
	}

	public void setIMPORTE(float importe) {
		this.importe = importe;
	}

	public int getMONEDA() {
		return moneda;
	}

	public void setMONEDA(int moneda) {
		this.moneda = moneda;
	}

	public String getCONDICION_CLIENTE() {
		return condicion_cliente;
	}

	public void setCONDICION_CLIENTE(String condicion_cliente) {
		this.condicion_cliente = condicion_cliente;
	}

	public String getCTO_CLIENTE() {
		return cto_cliente;
	}

	public void setCTO_CLIENTE(String cto_cliente) {
		this.cto_cliente = cto_cliente;
	}

	public String getSIGNO_KGS_FACT() {
		return signo_kgs_fact;
	}

	public void setSIGNO_KGS_FACT(String signo_kgs_fact) {
		this.signo_kgs_fact = signo_kgs_fact;
	}

	public float getKGS_FACTURADOS() {
		return kgs_facturados;
	}

	public void setKGS_FACTURADOS(float kgs_facturados) {
		this.kgs_facturados = kgs_facturados;
	}

	public float getPRECIO_CTO() {
		return precio_cto;
	}

	public void setPRECIO_CTO(float precio_cto) {
		this.precio_cto = precio_cto;
	}

	public String getCUIT_CORREDOR() {
		return cuit_corredor;
	}

	public void setCUIT_CORREDOR(String cuit_corredor) {
		this.cuit_corredor = cuit_corredor;
	}

	public String getCUIT_CONTRA() {
		return cuit_contra;
	}

	public void setCUIT_CONTRA(String cuit_contra) {
		this.cuit_contra = cuit_contra;
	}

	public String getESTADO() {
		return estado;
	}

	public void setESTADO(String estado) {
		this.estado = estado;
	}

	public FechaTimeType getFECHA_VTO_CPTE() {
		return fecha_vto_cpte;
	}

	public void setFECHA_VTO_CPTE(FechaTimeType fecha_vto_cpte) {
		this.fecha_vto_cpte = fecha_vto_cpte;
	}

	public String getSIGNO_IMPORTE_A_CTA() {
		return signo_importe_a_cta;
	}

	public void setSIGNO_IMPORTE_A_CTA(String signo_importe_a_cta) {
		this.signo_importe_a_cta = signo_importe_a_cta;
	}

	public float getIMPORTE_A_CTA() {
		return importe_a_cta;
	}

	public void setIMPORTE_A_CTA(float importe_a_cta) {
		this.importe_a_cta = importe_a_cta;
	}

	public String getCAI() {
		return cai;
	}

	public void setCAI(String cai) {
		this.cai = cai;
	}

	public FechaTimeType getFECHA_VTO_CAI() {
		return fecha_vto_cai;
	}

	public void setFECHA_VTO_CAI(FechaTimeType fecha_vto_cai) {
		this.fecha_vto_cai = fecha_vto_cai;
	}

	public String getCAE() {
		return cae;
	}

	public void setCAE(String cae) {
		this.cae = cae;
	}

	public FechaTimeType getFECHA_VTO_CAE() {
		return fecha_vto_cae;
	}

	public void setFECHA_VTO_CAE(FechaTimeType fecha_vto_cae) {
		this.fecha_vto_cae = fecha_vto_cae;
	}

	public String getCOEFACTURAPAPEL() {
		return coefacturapapel;
	}

	public void setCOEFACTURAPAPEL(String coefacturapapel) {
		this.coefacturapapel = coefacturapapel;
	}
}
