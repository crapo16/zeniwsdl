
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 							parametros para la marcar un comprobante como
 * 							descargado o no.
 * 						
 * 
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auth" type="{http://www.zeni.com.ar/ZeniWSDL/}AuthType"/>
 *         &lt;element name="comprobanteId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="descargado" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
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
    "auth",
    "comprobanteId",
    "descargado"
})
@XmlRootElement(name = "marcarComprobanteDescargado")
public class MarcarComprobanteDescargado {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected String comprobanteId;
    @XmlElement(required = true)
    protected SINOType descargado;

    /**
     * Gets the value of the auth property.
     * 
     * @return
     *     possible object is
     *     {@link AuthType }
     *     
     */
    public AuthType getAuth() {
        return auth;
    }

    /**
     * Sets the value of the auth property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthType }
     *     
     */
    public void setAuth(AuthType value) {
        this.auth = value;
    }

    /**
     * Gets the value of the comprobanteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComprobanteId() {
        return comprobanteId;
    }

    /**
     * Sets the value of the comprobanteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComprobanteId(String value) {
        this.comprobanteId = value;
    }

    /**
     * Gets the value of the descargado property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getDescargado() {
        return descargado;
    }

    /**
     * Sets the value of the descargado property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setDescargado(SINOType value) {
        this.descargado = value;
    }

}
