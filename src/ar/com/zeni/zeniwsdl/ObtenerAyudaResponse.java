
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
 *         &lt;element name="ayudas" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfAyudaType"/>
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
    "ayudas"
})
@XmlRootElement(name = "obtenerAyudaResponse")
public class ObtenerAyudaResponse {

    @XmlElement(required = true)
    protected ArrayOfAyudaType ayudas;

    /**
     * Gets the value of the ayudas property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAyudaType }
     *     
     */
    public ArrayOfAyudaType getAyudas() {
        return ayudas;
    }

    /**
     * Sets the value of the ayudas property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAyudaType }
     *     
     */
    public void setAyudas(ArrayOfAyudaType value) {
        this.ayudas = value;
    }

}
