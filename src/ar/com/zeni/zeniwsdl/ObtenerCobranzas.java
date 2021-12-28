
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Parametros para la obtencion de las cobranzas.
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
 *         &lt;element name="acuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfIdCuenta"/>
 *         &lt;element name="fechaTope" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="filtroOperaciones" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumOperacionesType"/>
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
    "acuenta",
    "fechaTope",
    "filtroOperaciones"
})
@XmlRootElement(name = "obtenerCobranzas")
public class ObtenerCobranzas {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected ArrayOfIdCuenta acuenta;
    @XmlElement(required = true)
    protected FechaTimeType fechaTope;
    @XmlElement(required = true)
    protected EnumOperacionesType filtroOperaciones;

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
     * Gets the value of the acuenta property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfIdCuenta }
     *     
     */
    public ArrayOfIdCuenta getAcuenta() {
        return acuenta;
    }

    /**
     * Sets the value of the acuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfIdCuenta }
     *     
     */
    public void setAcuenta(ArrayOfIdCuenta value) {
        this.acuenta = value;
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
     * Gets the value of the filtroOperaciones property.
     * 
     * @return
     *     possible object is
     *     {@link EnumOperacionesType }
     *     
     */
    public EnumOperacionesType getFiltroOperaciones() {
        return filtroOperaciones;
    }

    /**
     * Sets the value of the filtroOperaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumOperacionesType }
     *     
     */
    public void setFiltroOperaciones(EnumOperacionesType value) {
        this.filtroOperaciones = value;
    }

}
