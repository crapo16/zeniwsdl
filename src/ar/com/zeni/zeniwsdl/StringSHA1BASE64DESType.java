
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StringSHA1BASE64DESType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StringSHA1BASE64DESType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="stringSHA1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stringSHA1BASE64" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stringSHA1BASE64DESBASE64" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estoFuncionaSoloParaTestYNoEnProduccionString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StringSHA1BASE64DESType", propOrder = {
    "stringSHA1",
    "stringSHA1BASE64",
    "stringSHA1BASE64DESBASE64",
    "estoFuncionaSoloParaTestYNoEnProduccionString"
})
public class StringSHA1BASE64DESType {

    @XmlElement(required = true)
    protected String stringSHA1;
    @XmlElement(required = true)
    protected String stringSHA1BASE64;
    @XmlElement(required = true)
    protected String stringSHA1BASE64DESBASE64;
    @XmlElement(required = true)
    protected String estoFuncionaSoloParaTestYNoEnProduccionString;

    /**
     * Gets the value of the stringSHA1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringSHA1() {
        return stringSHA1;
    }

    /**
     * Sets the value of the stringSHA1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringSHA1(String value) {
        this.stringSHA1 = value;
    }

    /**
     * Gets the value of the stringSHA1BASE64 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringSHA1BASE64() {
        return stringSHA1BASE64;
    }

    /**
     * Sets the value of the stringSHA1BASE64 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringSHA1BASE64(String value) {
        this.stringSHA1BASE64 = value;
    }

    /**
     * Gets the value of the stringSHA1BASE64DESBASE64 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStringSHA1BASE64DESBASE64() {
        return stringSHA1BASE64DESBASE64;
    }

    /**
     * Sets the value of the stringSHA1BASE64DESBASE64 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStringSHA1BASE64DESBASE64(String value) {
        this.stringSHA1BASE64DESBASE64 = value;
    }

    /**
     * Gets the value of the estoFuncionaSoloParaTestYNoEnProduccionString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstoFuncionaSoloParaTestYNoEnProduccionString() {
        return estoFuncionaSoloParaTestYNoEnProduccionString;
    }

    /**
     * Sets the value of the estoFuncionaSoloParaTestYNoEnProduccionString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstoFuncionaSoloParaTestYNoEnProduccionString(String value) {
        this.estoFuncionaSoloParaTestYNoEnProduccionString = value;
    }

}
