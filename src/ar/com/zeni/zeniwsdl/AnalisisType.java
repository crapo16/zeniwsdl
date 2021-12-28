
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo para detallas analisis.
 * 					
 * 
 * <p>Java class for AnalisisType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AnalisisType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="analisis" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="nroAnalisis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalisisType", propOrder = {
    "analisis",
    "nroAnalisis",
    "result"
})
public class AnalisisType {

    @XmlElement(required = true)
    protected String analisis;
    @XmlElement(required = true)
    protected String nroAnalisis;
    protected float result;

    /**
     * Gets the value of the analisis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnalisis() {
        return analisis;
    }

    /**
     * Sets the value of the analisis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnalisis(String value) {
        this.analisis = value;
    }

    /**
     * Gets the value of the nroAnalisis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroAnalisis() {
        return nroAnalisis;
    }

    /**
     * Sets the value of the nroAnalisis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroAnalisis(String value) {
        this.nroAnalisis = value;
    }

    /**
     * Gets the value of the result property.
     * 
     */
    public float getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     */
    public void setResult(float value) {
        this.result = value;
    }

}
