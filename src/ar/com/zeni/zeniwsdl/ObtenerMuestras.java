
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 							parametros para la obtencion de los
 * 							MuestrasByContrato.
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
 *         &lt;element name="rangoFechasAlta" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType"/>
 *         &lt;element name="campaniaId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType" minOccurs="0"/>
 *         &lt;element name="contrato" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType" minOccurs="0"/>
 *         &lt;element name="productos" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfProductoType" minOccurs="0"/>
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
    "rangoFechasAlta",
    "campaniaId",
    "contrato",
    "productos"
})
@XmlRootElement(name = "obtenerMuestras")
public class ObtenerMuestras {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected CuentaType cuenta;
    @XmlElement(required = true)
    protected RangoFechaType rangoFechasAlta;
    protected String campaniaId;
    protected String contrato;
    protected ArrayOfProductoType productos;

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
     * Gets the value of the rangoFechasAlta property.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public RangoFechaType getRangoFechasAlta() {
        return rangoFechasAlta;
    }

    /**
     * Sets the value of the rangoFechasAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setRangoFechasAlta(RangoFechaType value) {
        this.rangoFechasAlta = value;
    }

    /**
     * Gets the value of the campaniaId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampaniaId() {
        return campaniaId;
    }

    /**
     * Sets the value of the campaniaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampaniaId(String value) {
        this.campaniaId = value;
    }

    /**
     * Gets the value of the contrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrato() {
        return contrato;
    }

    /**
     * Sets the value of the contrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrato(String value) {
        this.contrato = value;
    }

    /**
     * Gets the value of the productos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfProductoType }
     *     
     */
    public ArrayOfProductoType getProductos() {
        return productos;
    }

    /**
     * Sets the value of the productos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfProductoType }
     *     
     */
    public void setProductos(ArrayOfProductoType value) {
        this.productos = value;
    }

}
