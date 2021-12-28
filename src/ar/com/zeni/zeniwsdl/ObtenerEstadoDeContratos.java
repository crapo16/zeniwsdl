
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 							parametros para la obtencion de los estados de
 * 							los contratos.
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
 *         &lt;element name="fechaTope" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="tipoCompradorVendedor" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumCompradorVendedorType" minOccurs="0"/>
 *         &lt;element name="actividadId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType" minOccurs="0"/>
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
    "fechaTope",
    "tipoCompradorVendedor",
    "actividadId"
})
@XmlRootElement(name = "obtenerEstadoDeContratos")
public class ObtenerEstadoDeContratos {

    @XmlElement(required = true)
    protected AuthType auth;
    
    // #1535
    @XmlElement(required = true)
    protected ArrayOfIdCuenta cuentas;
    
    @XmlElement(required = true)
    protected FechaTimeType fechaTope;
    protected EnumCompradorVendedorType tipoCompradorVendedor;
    protected String actividadId;

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
    // # 1535
    public ArrayOfIdCuenta getCuentas() {
        return cuentas;
    }

    /**
     * Sets the value of the cuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link CuentaType }
     *     
     */
    // # 1535
    public void setCuentas(ArrayOfIdCuenta value) {
        this.cuentas = value;
    }

    /**
     * Gets the value of the fechaTope property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaTope() {
        return fechaTope;
    }

    /**
     * Sets the value of the fechaTope property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaTope(FechaTimeType value) {
        this.fechaTope = value;
    }

    /**
     * Gets the value of the tipoCompradorVendedor property.
     * 
     * @return
     *     possible object is
     *     {@link EnumCompradorVendedorType }
     *     
     */
    public EnumCompradorVendedorType getTipoCompradorVendedor() {
        return tipoCompradorVendedor;
    }

    /**
     * Sets the value of the tipoCompradorVendedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumCompradorVendedorType }
     *     
     */
    public void setTipoCompradorVendedor(EnumCompradorVendedorType value) {
        this.tipoCompradorVendedor = value;
    }

    /**
     * Gets the value of the actividadId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividadId() {
        return actividadId;
    }

    /**
     * Sets the value of the actividadId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividadId(String value) {
        this.actividadId = value;
    }

}
