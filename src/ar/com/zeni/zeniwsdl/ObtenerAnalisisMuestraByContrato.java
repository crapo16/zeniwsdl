
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
 *         &lt;element name="auth" type="{http://www.zeni.com.ar/ZeniWSDL/}AuthType"/>
 *         &lt;element name="idContrato" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="idMuestra" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
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
    "idContrato",
    "idMuestra"
})
@XmlRootElement(name = "obtenerAnalisisMuestraByContrato")
public class ObtenerAnalisisMuestraByContrato {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected String idContrato;
    @XmlElement(required = true)
    protected String idMuestra;

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
     * Gets the value of the idContrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdContrato() {
        return idContrato;
    }

    /**
     * Sets the value of the idContrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdContrato(String value) {
        this.idContrato = value;
    }

    /**
     * Gets the value of the idMuestra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMuestra() {
        return idMuestra;
    }

    /**
     * Sets the value of the idMuestra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMuestra(String value) {
        this.idMuestra = value;
    }

}
