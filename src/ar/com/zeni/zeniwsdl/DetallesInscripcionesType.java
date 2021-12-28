
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DetallesInscripcionesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetallesInscripcionesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="provincia" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="actividad" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="inscripto" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *         &lt;element name="convMultilateral" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DetallesInscripcionesType", propOrder = {
    "provincia",
    "actividad",
    "inscripto",
    "convMultilateral"
})
public class DetallesInscripcionesType {

    @XmlElement(required = true)
    protected String provincia;
    @XmlElement(required = true)
    protected String actividad;
    @XmlElement(required = true)
    protected SINOType inscripto;
    @XmlElement(required = true)
    protected SINOType convMultilateral;

    /**
     * Gets the value of the provincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the value of the provincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Gets the value of the actividad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividad() {
        return actividad;
    }

    /**
     * Sets the value of the actividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividad(String value) {
        this.actividad = value;
    }

    /**
     * Gets the value of the inscripto property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getInscripto() {
        return inscripto;
    }

    /**
     * Sets the value of the inscripto property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setInscripto(SINOType value) {
        this.inscripto = value;
    }

    /**
     * Gets the value of the convMultilateral property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getConvMultilateral() {
        return convMultilateral;
    }

    /**
     * Sets the value of the convMultilateral property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setConvMultilateral(SINOType value) {
        this.convMultilateral = value;
    }

}
