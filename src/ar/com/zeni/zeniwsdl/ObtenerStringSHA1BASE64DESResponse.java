
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
 *         &lt;element name="out" type="{http://www.zeni.com.ar/ZeniWSDL/}StringSHA1BASE64DESType"/>
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
@XmlRootElement(name = "obtenerStringSHA1BASE64DESResponse")
public class ObtenerStringSHA1BASE64DESResponse {

    @XmlElement(required = true)
    protected StringSHA1BASE64DESType out;

    /**
     * Gets the value of the out property.
     * 
     * @return
     *     possible object is
     *     {@link StringSHA1BASE64DESType }
     *     
     */
    public StringSHA1BASE64DESType getOut() {
        return out;
    }

    /**
     * Sets the value of the out property.
     * 
     * @param value
     *     allowed object is
     *     {@link StringSHA1BASE64DESType }
     *     
     */
    public void setOut(StringSHA1BASE64DESType value) {
        this.out = value;
    }

}
