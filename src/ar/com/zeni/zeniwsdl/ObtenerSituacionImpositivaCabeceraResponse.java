
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="out" type="{http://www.zeni.com.ar/ZeniWSDL/}SituacionImpositivaCabeceraType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "out"
})
@XmlRootElement(name = "obtenerSituacionImpositivaCabeceraResponse")
public class ObtenerSituacionImpositivaCabeceraResponse {

    @XmlElement(required = true)
    protected SituacionImpositivaCabeceraType out;

    /**
     * Gets the value of the out property.
     * 
     * @return
     *     possible object is
     *     {@link SituacionImpositivaCabeceraType }
     *     
     */
    public SituacionImpositivaCabeceraType getOut() {
        return out;
    }

    /**
     * Sets the value of the out property.
     * 
     * @param value
     *     allowed object is
     *     {@link SituacionImpositivaCabeceraType }
     *     
     */
    public void setOut(SituacionImpositivaCabeceraType value) {
        this.out = value;
    }

}
