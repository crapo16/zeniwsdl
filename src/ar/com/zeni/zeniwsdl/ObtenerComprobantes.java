
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Parametros para la obtencion de las
 * 							comprobantes.
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
 *         &lt;element name="tipoConsultaComprobante" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumTipoConsultaComprobanteType"/>
 *         &lt;element name="rangoFechas" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType"/>
 *         &lt;element name="tipoComprobante" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumCompradorVendedorType"/>
 *         &lt;element name="contraparteId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType" minOccurs="0"/>
 *         &lt;element name="tipoDeComprobante" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumTipoComprobanteType"/>
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
    "cuenta",
    "tipoConsultaComprobante",
    "rangoFechas",
    "tipoComprobante",
    "contraparteId",
    "tipoDeComprobante",
    "descargado"
})
@XmlRootElement(name = "obtenerComprobantes")
public class ObtenerComprobantes {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected CuentaType cuenta;
    @XmlElement(required = true)
    protected EnumTipoConsultaComprobanteType tipoConsultaComprobante;
    @XmlElement(required = true)
    protected RangoFechaType rangoFechas;
    @XmlElement(required = true)
    protected EnumCompradorVendedorType tipoComprobante;
    protected String contraparteId;
    @XmlElement(required = true)
    protected EnumTipoComprobanteType tipoDeComprobante;
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
     * Gets the value of the tipoConsultaComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoConsultaComprobanteType }
     *     
     */
    public EnumTipoConsultaComprobanteType getTipoConsultaComprobante() {
        return tipoConsultaComprobante;
    }

    /**
     * Sets the value of the tipoConsultaComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoConsultaComprobanteType }
     *     
     */
    public void setTipoConsultaComprobante(EnumTipoConsultaComprobanteType value) {
        this.tipoConsultaComprobante = value;
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
     * Gets the value of the tipoComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link EnumCompradorVendedorType }
     *     
     */
    public EnumCompradorVendedorType getTipoComprobante() {
        return tipoComprobante;
    }

    /**
     * Sets the value of the tipoComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumCompradorVendedorType }
     *     
     */
    public void setTipoComprobante(EnumCompradorVendedorType value) {
        this.tipoComprobante = value;
    }

    /**
     * Gets the value of the contraparteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContraparteId() {
        return contraparteId;
    }

    /**
     * Sets the value of the contraparteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContraparteId(String value) {
        this.contraparteId = value;
    }

    /**
     * Gets the value of the tipoDeComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoComprobanteType }
     *     
     */
    public EnumTipoComprobanteType getTipoDeComprobante() {
        return tipoDeComprobante;
    }

    /**
     * Sets the value of the tipoDeComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoComprobanteType }
     *     
     */
    public void setTipoDeComprobante(EnumTipoComprobanteType value) {
        this.tipoDeComprobante = value;
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
