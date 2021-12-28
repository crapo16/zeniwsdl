
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
 *         &lt;element name="out" type="{http://www.zeni.com.ar/ZeniWSDL/}obtenerCuentaCorrienteContableType"/>
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
@XmlRootElement(name = "obtenerCuentaCorrienteContableResponse")
public class ObtenerCuentaCorrienteContableResponse {

    @XmlElement(required = true)
    protected ObtenerCuentaCorrienteContableType out;

    /**
     * Gets the value of the out property.
     * 
     * @return
     *     possible object is
     *     {@link ObtenerCuentaCorrienteContableType }
     *     
     */
    public ObtenerCuentaCorrienteContableType getOut() {
        return out;
    }

    /**
     * Sets the value of the out property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObtenerCuentaCorrienteContableType }
     *     
     */
    public void setOut(ObtenerCuentaCorrienteContableType value) {
        this.out = value;
    }

}
