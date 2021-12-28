package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

// #1589

/*
					<xsd:element minOccurs="1" name="orden" type="xsd:int"/>
					<xsd:element minOccurs="1" name="concepto" type="tns:DescripcionType"/>
					<xsd:element minOccurs="1" name="descripcion" type="tns:DescripcionType"/>
					<xsd:element minOccurs="1" name="base" type="xsd:float"/>
					<xsd:element minOccurs="1" name="porcentaje" type="xsd:float"/>
					<xsd:element minOccurs="1" name="importe" type="xsd:float"/>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FacturaDetalleType", propOrder = {"orden","concepto","descripcion","base",
		"porcentaje","importe" })

public class FacturaDetalleType {

	@XmlElement(name = "ORDEN")
	protected int orden;
	
	@XmlElement(name = "CONCEPTO")
	protected String concepto;
	
	@XmlElement(name = "DESCRIPCION")
	protected String descripcion;

	@XmlElement(name = "BASE")
	protected float base;

	@XmlElement(name = "PORCENTAJE")
	protected float porcentaje;

	@XmlElement(name = "IMPORTE")
	protected float importe;
	
	public int getORDEN() {
		return orden;
	}

	public void setORDEN(int o) {
		this.orden = o;
	}

	public String getCONCEPTO() {
		return concepto;
	}

	public void setCONCEPTO(String c) {
		this.concepto = c;
	}

	public String getDESCRIPCION() {
		return descripcion;
	}

	public void setDESCRIPCION(String r) {
		this.descripcion = r;
	}

	public float getPORCENTAJE() {
		return porcentaje;
	}

	public void setPORCENTAJE(float p) {
		this.porcentaje = p;
	}

	public float getBASE() {
		return base;
	}

	public void setBASE(float base) {
		this.base = base;
	}

	public float getIMPORTE() {
		return importe;
	}

	public void setIMPORTE(float m) {
		this.importe = m;
	}
}
