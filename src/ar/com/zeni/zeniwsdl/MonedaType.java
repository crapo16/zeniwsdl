
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de moneda.
 * 					
 * 
 * <p>Java class for MonedaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MonedaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="descripcion" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="simbolo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codigoOnca" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MonedaType", propOrder = {
    "id",
    "descripcion",
    "simbolo",
    "codigoOnca"
})
public class MonedaType {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String descripcion;
    @XmlElement(required = true)
    protected String simbolo;
    @XmlElement(required = true)
    protected String codigoOnca;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Gets the value of the simbolo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Sets the value of the simbolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimbolo(String value) {
        this.simbolo = value;
    }

    /**
     * Gets the value of the codigoOnca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoOnca() {
        return codigoOnca;
    }

    /**
     * Sets the value of the codigoOnca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoOnca(String value) {
        this.codigoOnca = value;
    }

}
