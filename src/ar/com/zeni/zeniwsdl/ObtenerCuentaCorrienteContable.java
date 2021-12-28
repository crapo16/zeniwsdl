
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 							parametros para la obtencion de los
 * 							CuentaCorrienteContable para la cuenta dentro del rango de
 * 							fechas.
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
 *         &lt;element name="cuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}CuentaType"/>
 *         &lt;element name="rangoFechas" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType"/>
 *         &lt;element name="filtroDeMoneda" type="{http://www.zeni.com.ar/ZeniWSDL/}MonedaType" minOccurs="0"/>
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
    "cuenta",
    "rangoFechas",
    "filtroDeMoneda"
})
@XmlRootElement(name = "obtenerCuentaCorrienteContable")
public class ObtenerCuentaCorrienteContable {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected CuentaType cuenta;
    @XmlElement(required = true)
    protected RangoFechaType rangoFechas;
    protected MonedaType filtroDeMoneda;

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
     *     {@link CuentaType }
     *     
     */
    public CuentaType getCuenta() {
        return cuenta;
    }

    /**
     * Sets the value of the cuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link CuentaType }
     *     
     */
    public void setCuenta(CuentaType value) {
        this.cuenta = value;
    }

    /**
     * Gets the value of the rangoFechas property.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public RangoFechaType getRangoFechas() {
        return rangoFechas;
    }

    /**
     * Sets the value of the rangoFechas property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setRangoFechas(RangoFechaType value) {
        this.rangoFechas = value;
    }

    /**
     * Gets the value of the filtroDeMoneda property.
     * 
     * @return
     *     possible object is
     *     {@link MonedaType }
     *     
     */
    public MonedaType getFiltroDeMoneda() {
        return filtroDeMoneda;
    }

    /**
     * Sets the value of the filtroDeMoneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonedaType }
     *     
     */
    public void setFiltroDeMoneda(MonedaType value) {
        this.filtroDeMoneda = value;
    }

}
