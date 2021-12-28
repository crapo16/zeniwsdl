
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de entrega por contrato.
 * 					
 * 
 * <p>Java class for EntregaCubiertaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntregaCubiertaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idEntrega" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="entregaPartidaId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="cubiertaPorEntregaPartidaId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="contrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contratoId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntregaCubiertaType", propOrder = {
    "idEntrega",
    "entregaPartidaId",
    "cubiertaPorEntregaPartidaId",
    "contrato",
    "contratoId",
    "fecha"
})
public class EntregaCubiertaType {

    @XmlElement(required = true)
    protected String idEntrega;
    @XmlElement(required = true)
    protected String entregaPartidaId;
    @XmlElement(required = true)
    protected String cubiertaPorEntregaPartidaId;
    @XmlElement(required = true)
    protected String contrato;
    @XmlElement(required = true)
    protected String contratoId;
    @XmlElement(required = true)
    protected FechaTimeType fecha;

    /**
     * Gets the value of the idEntrega property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEntrega() {
        return idEntrega;
    }

    /**
     * Sets the value of the idEntrega property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEntrega(String value) {
        this.idEntrega = value;
    }

    /**
     * Gets the value of the entregaPartidaId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntregaPartidaId() {
        return entregaPartidaId;
    }

    /**
     * Sets the value of the entregaPartidaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntregaPartidaId(String value) {
        this.entregaPartidaId = value;
    }

    /**
     * Gets the value of the cubiertaPorEntregaPartidaId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCubiertaPorEntregaPartidaId() {
        return cubiertaPorEntregaPartidaId;
    }

    /**
     * Sets the value of the cubiertaPorEntregaPartidaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCubiertaPorEntregaPartidaId(String value) {
        this.cubiertaPorEntregaPartidaId = value;
    }

    /**
     * Gets the value of the contrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrato() {
        return contrato;
    }

    /**
     * Sets the value of the contrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrato(String value) {
        this.contrato = value;
    }

    /**
     * Gets the value of the contratoId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratoId() {
        return contratoId;
    }

    /**
     * Sets the value of the contratoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratoId(String value) {
        this.contratoId = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFecha(FechaTimeType value) {
        this.fecha = value;
    }

}
