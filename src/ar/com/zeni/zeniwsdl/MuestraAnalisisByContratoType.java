
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Analisis de contrato.
 * 
 * <p>Java class for MuestraAnalisisByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MuestraAnalisisByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descripcion" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="bonreb" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sinnombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MuestraAnalisisByContratoType", propOrder = {
    "descripcion",
    "cantidad",
    "bonreb",
    "sinnombre"
})
public class MuestraAnalisisByContratoType {

    @XmlElement(required = true)
    protected String descripcion;
    protected float cantidad;
    @XmlElement(required = true)
    protected String bonreb;
    @XmlElement(required = true)
    protected String sinnombre;

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
     * Gets the value of the cantidad property.
     * 
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     */
    public void setCantidad(float value) {
        this.cantidad = value;
    }

    /**
     * Gets the value of the bonreb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBonreb() {
        return bonreb;
    }

    /**
     * Sets the value of the bonreb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBonreb(String value) {
        this.bonreb = value;
    }

    /**
     * Gets the value of the sinnombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSinnombre() {
        return sinnombre;
    }

    /**
     * Sets the value of the sinnombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSinnombre(String value) {
        this.sinnombre = value;
    }

}
