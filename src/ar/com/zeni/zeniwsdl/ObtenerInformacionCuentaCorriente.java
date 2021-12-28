
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 							parametros para la obtencion de la informacion
 * 							de la cuenta corriente del
 * 							Cliente.
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
 *         &lt;element name="cuentas" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfIdCuenta"/>
 *         &lt;element name="rangoDeFechas" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType"/>
 *         &lt;element name="filtroDeMoneda" type="{http://www.zeni.com.ar/ZeniWSDL/}MonedaType"/>
 *         &lt;element name="filtroDeMovimiento" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumDeMovimientoType"/>
 *         &lt;element name="comprobanteNro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contratoNro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "rangoDeFechas",
    "filtroDeMoneda",
    "filtroDeMovimiento",
    "comprobanteNro",
    "contratoNro"
})
@XmlRootElement(name = "obtenerInformacionCuentaCorriente")
public class ObtenerInformacionCuentaCorriente {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected ArrayOfIdCuenta cuentas;
    @XmlElement(required = true)
    protected RangoFechaType rangoDeFechas;
    @XmlElement(required = true)
    protected MonedaType filtroDeMoneda;
    @XmlElement(required = true)
    protected EnumDeMovimientoType filtroDeMovimiento;
    protected String comprobanteNro;
    protected String contratoNro;

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
     * Gets the value of the cuentas property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfIdCuenta }
     *     
     */
    public ArrayOfIdCuenta getCuentas() {
        return cuentas;
    }

    /**
     * Sets the value of the cuentas property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfIdCuenta }
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
    public RangoFechaType getRangoDeFechas() {
        return rangoDeFechas;
    }

    /**
     * Sets the value of the rangoDeFechas property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setRangoDeFechas(RangoFechaType value) {
        this.rangoDeFechas = value;
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

    /**
     * Gets the value of the filtroDeMovimiento property.
     * 
     * @return
     *     possible object is
     *     {@link EnumDeMovimientoType }
     *     
     */
    public EnumDeMovimientoType getFiltroDeMovimiento() {
        return filtroDeMovimiento;
    }

    /**
     * Sets the value of the filtroDeMovimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumDeMovimientoType }
     *     
     */
    public void setFiltroDeMovimiento(EnumDeMovimientoType value) {
        this.filtroDeMovimiento = value;
    }

    /**
     * Gets the value of the comprobanteNro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComprobanteNro() {
        return comprobanteNro;
    }

    /**
     * Sets the value of the comprobanteNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComprobanteNro(String value) {
        this.comprobanteNro = value;
    }

    /**
     * Gets the value of the contratoNro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratoNro() {
        return contratoNro;
    }

    /**
     * Sets the value of the contratoNro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratoNro(String value) {
        this.contratoNro = value;
    }

}
