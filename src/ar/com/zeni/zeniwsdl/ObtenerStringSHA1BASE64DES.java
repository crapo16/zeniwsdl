
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
 *         &lt;element name="stringAEncodear" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stringSeedEnBase64" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "stringAEncodear",
    "stringSeedEnBase64"
})
@XmlRootElement(name = "obtenerStringSHA1BASE64DES")
public class ObtenerStringSHA1BASE64DES {

    @XmlElement(required = true)
    protected String stringAEncodear;
    @XmlElement(required = true)
    protected String stringSeedEnBase64;

    /**
     * Gets the value of the stringAEncodear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringAEncodear() {
        return stringAEncodear;
    }

    /**
     * Sets the value of the stringAEncodear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringAEncodear(String value) {
        this.stringAEncodear = value;
    }

    /**
     * Gets the value of the stringSeedEnBase64 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringSeedEnBase64() {
        return stringSeedEnBase64;
    }

    /**
     * Sets the value of the stringSeedEnBase64 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringSeedEnBase64(String value) {
        this.stringSeedEnBase64 = value;
    }

}
