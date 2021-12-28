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
@XmlAccessorType(XmlAccessType.FIELD)

// #1567
//@XmlType(name = "ResultadosMercadoATerminoType", propOrder = { "idregistro",
//		"prima", "precioapertura", "valorajuste", "resultado", "fechaposicion",
//		"idmoneda" })

@XmlType(name = "ResultadosMercadoATerminoType", propOrder = { "idregistro",
"valorajuste", "resultado", "fechaposicion" })


public class ResultadosMercadoATerminoType {

	@XmlElement(name = "IDREGISTRO")
	protected int idregistro;
	
	// #1567
	// @XmlElement(name = "PRIMA")
	// protected float prima;
	// @XmlElement(name = "PRECIOAPERTURA")
	// protected float precioapertura;
	
	@XmlElement(name = "VALORAJUSTE")
	protected float valorajuste;
	@XmlElement(name = "RESULTADO")
	protected float resultado;
	@XmlElement(name = "FECHAPOSICION", required = true)
	protected FechaTimeType fechaposicion;
	
	// #1567
	// @XmlElement(name = "MONEDA", required = true)
	// protected int idmoneda;

	/**
	 * Gets the value of the fechaposicion property.
	 * 
	 * @return possible object is {@link FechaTimeType }
	 * 
	 */
	public FechaTimeType getFECHAPOSICION() {
		return fechaposicion;
	}

	/**
	 * Sets the value of the fechaposicion property.
	 * 
	 * @param value
	 *            allowed object is {@link FechaTimeType }
	 * 
	 */
	public void setFECHAPOSICION(FechaTimeType value) {
		this.fechaposicion = value;
	}

	// #1567
	// public float getPRIMA() {
	//  	return prima;
	// }

	/**
	 * Sets the value of the prima property.
	 * 
	 */
	// public void setPRIMA(float value) {
	//	this.prima = value;
	// }

	/**
	 * Gets the value of the precioapertura property.
	 * 
	 */
	// public float getPRECIOAPERTURA() {
	//	return precioapertura;
	//}

	/**
	 * Sets the value of the precioapertura property.
	 * 
	 */
	// public void setPRECIOAPERTURA(float value) {
	//	this.precioapertura = value;
	// }

	public void setIDREGISTRO(int value) {
		this.idregistro = value;
	}

	public int getID() {
		return this.idregistro;
	}

	public void setVALORAJUSTE(float value) {
		this.valorajuste = value;
	}

	public float getVALORAJUSTE() {
		return this.valorajuste;
	}

	public void setRESULTADO(float value) {
		this.resultado = value;
	}

	public float getRESULTADO() {
		return this.resultado;
	}

	// #1567
	// public void setIDMONEDA(int value) {
	// 	this.idmoneda = value;
	// }

	// public int getIDMONEDA() {
	//	return this.idmoneda;
	// }
}
