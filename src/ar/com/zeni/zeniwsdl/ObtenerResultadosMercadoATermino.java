
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
 *         &lt;element name="Cuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="RangoDeFechas" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType"/>
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
    "cuentas",
    "Fecha"
})
@XmlRootElement(name = "obtenerResultadosMercadoATermino")
public class ObtenerResultadosMercadoATermino {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(name = "Cuentas", required = true)
    protected ArrayOfIdCuenta cuentas;
    @XmlElement(name = "Fecha", required = true)
    protected FechaTimeType Fecha;

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
     * Gets the value of the cuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public ArrayOfIdCuenta getCuentas() {
        return cuentas;
    }

    /**
     * Sets the value of the cuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentas(ArrayOfIdCuenta value) {
        this.cuentas = value;
    }

    /**
     * Gets the value of the rangoDeFechas property.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public FechaTimeType getFecha() {
        return Fecha;
    }

    /**
     * Sets the value of the rangoDeFechas property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setFecha(FechaTimeType value) {
        this.Fecha = value;
    }
}
