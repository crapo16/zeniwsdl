package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for OperacionMercadoATerminoType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <xsd:complexType name="ResultadosMercadoATerminoType"> <xsd:sequence>
 * <xsd:element maxOccurs="1" minOccurs="1" name="IDREGISTRO" type="xsd:int" />
 * <xsd:element maxOccurs="1" minOccurs="1" name="PRECIOAPERTURA"
 * type="xsd:float" /> <xsd:element maxOccurs="1" minOccurs="1" name="PRIMA"
 * type="xsd:float" /> <xsd:element maxOccurs="1" minOccurs="1"
 * name="VALORAJUSTE" type="xsd:float" /> <xsd:element maxOccurs="1"
 * minOccurs="1" name="RESULTADO" type="xsd:float" /> <xsd:element maxOccurs="1"
 * minOccurs="1" name="FECHAPOSICION" type="tns:FechaTimeType" /> <xsd:element
 * maxOccurs="1" minOccurs="1" name="MONEDA" type="tns:MonedaType" />
 * </xsd:sequence> </xsd:complexType> </pre>
 * 
 * 
 */

// #1506

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetalleFacturaType", propOrder = {"id","orden","leyenda","signo_importe_comprador",
		"importe_comprador","signo_importe_vendedor","importe_vendedor","factura_id" })

public class DetalleFacturaType {

	@XmlElement(name = "ID")
	protected int id;
	
	@XmlElement(name = "ORDEN")
	protected int orden;
	
	@XmlElement(name = "LEYENDA")
	protected String leyenda;

	@XmlElement(name = "SIGNO_IMPORTE_COMPRADOR")
	protected String signo_importe_comprador;

	@XmlElement(name = "IMPORTE_COMPRADOR")
	protected float importe_comprador;
	
	@XmlElement(name = "SIGNO_IMPORTE_VENDEDOR")
	protected String signo_importe_vendedor;
	
	@XmlElement(name = "IMPORTE_VENDEDOR")
	protected float importe_vendedor;
	
	@XmlElement(name = "FACTURA_ID")
	protected int factura_id;
	
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getORDEN() {
		return orden;
	}

	public void setORDEN(int orden) {
		this.orden = orden;
	}

	public String getLEYENDA() {
		return leyenda;
	}

	public void setLEYENDA(String leyenda) {
		this.leyenda = leyenda;
	}

	public String getSIGNO_IMPORTE_COMPRADOR() {
		return signo_importe_comprador;
	}

	public void setSIGNO_IMPORTE_COMPRADOR(String signo_importe_comprador) {
		this.signo_importe_comprador = signo_importe_comprador;
	}

	public float getIMPORTE_COMPRADOR() {
		return importe_comprador;
	}

	public void setIMPORTE_COMPRADOR(float importe_comprador) {
		this.importe_comprador = importe_comprador;
	}

	public String getSIGNO_IMPORTE_VENDEDOR() {
		return signo_importe_vendedor;
	}

	public void setSIGNO_IMPORTE_VENDEDOR(String signo_importe_vendedor) {
		this.signo_importe_vendedor = signo_importe_vendedor;
	}

	public float getIMPORTE_VENDEDOR() {
		return importe_vendedor;
	}

	public void setIMPORTE_VENDEDOR(float importe_vendedor) {
		this.importe_vendedor = importe_vendedor;
	}

	public int getFACTURA_ID() {
		return factura_id;
	}

	public void setFACTURA_ID(int factura_id) {
		this.factura_id = factura_id;
	}
}
