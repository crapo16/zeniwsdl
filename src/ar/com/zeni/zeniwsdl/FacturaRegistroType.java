package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

// #1589

/*
 * 					<xsd:element minOccurs="1" name="idregistro" type="tns:IdType"/>
					<xsd:element minOccurs="1" name="concepto" type="tns:DescripcionType"/>
					<xsd:element minOccurs="1" name="fecha" type="tns:FechaTimeType"/>
					<xsd:element minOccurs="1" name="registro" type="tns:DescripcionType"/>
					<xsd:element minOccurs="1" name="porcentaje" type="xsd:float"/>
					<xsd:element minOccurs="1" name="preciooprima" type="xsd:float"/>
					<xsd:element minOccurs="1" name="caratula" type="tns:DescripcionType"/>
					<xsd:element minOccurs="1" name="contrato" type="tns:DescripcionType"/>
					<xsd:element minOccurs="1" name="monto" type="xsd:float"/>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FacturaRegistroType", propOrder = {"idregistro","concepto","fecha","registro","porcentaje",
		"preciooprima","caratula","contrato","monto" })

public class FacturaRegistroType {

	@XmlElement(name = "idregistro")
	protected int idregistro;
	
	@XmlElement(name = "concepto")
	protected String concepto;
	
	@XmlElement(name = "fecha")
	protected FechaTimeType fecha;
	
	@XmlElement(name = "registro")
	protected String registro;

	@XmlElement(name = "porcentaje")
	protected float porcentaje;

	@XmlElement(name = "preciooprima")
	protected float preciooprima;
	
	@XmlElement(name = "caratula")
	protected String caratula;
	
	@XmlElement(name = "contrato")
	protected String contrato;
	
	@XmlElement(name = "monto")
	protected float monto;
	
	public int getIDREGISTRO() {
		return idregistro;
	}

	public void setIDREGISTRO(int id) {
		this.idregistro = id;
	}

	public String getCONCEPTO() {
		return concepto;
	}

	public void setCONCEPTO(String c) {
		this.concepto = c;
	}
	
	public FechaTimeType getFECHA() {
		return fecha;
	}

	public void setFECHA(FechaTimeType f) {
		this.fecha = f;
	}
	
	public String getREGISTRO() {
		return registro;
	}

	public void setREGISTRO(String r) {
		this.registro = r;
	}

	public float getPORCENTAJE() {
		return porcentaje;
	}

	public void setPORCENTAJE(float p) {
		this.porcentaje = p;
	}

	public float getPRECIOOPRIMA() {
		return preciooprima;
	}

	public void setPRECIOOPRIMA(float preciooprima) {
		this.preciooprima = preciooprima;
	}

	public String getCARATULA() {
		return caratula;
	}

	public void setCARATULA(String c) {
		this.caratula = c;
	}

	public String getCONTRATO() {
		return contrato;
	}

	public void setCONTRATO(String c) {
		this.contrato = c;
	}

	public float getMONTO() {
		return monto;
	}

	public void setMONTO(float m) {
		this.monto = m;
	}
}
