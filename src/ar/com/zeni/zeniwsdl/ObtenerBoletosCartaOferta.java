
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Parametros de Busqueda
 * 						
 * 
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auth" type="{http://www.zeni.com.ar/ZeniWSDL/}AuthType"/>
 *         &lt;element name="cuentaId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="nroContrato" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType" minOccurs="0"/>
 *         &lt;element name="rangoDeFechas" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType"/>
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
    "cuentaId",
    "nroContrato",
    "rangoDeFechas"
})
@XmlRootElement(name = "obtenerBoletosCartaOferta")
public class ObtenerBoletosCartaOferta {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected String cuentaId;
    protected String nroContrato;
    @XmlElement(required = true)
    protected RangoFechaType rangoDeFechas;

    /**
     * Obtiene el valor de la propiedad auth.
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
     * Define el valor de la propiedad auth.
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
     * Obtiene el valor de la propiedad cuentaId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaId() {
        return cuentaId;
    }

    /**
     * Define el valor de la propiedad cuentaId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaId(String value) {
        this.cuentaId = value;
    }

    /**
     * Obtiene el valor de la propiedad nroContrato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroContrato() {
        return nroContrato;
    }

    /**
     * Define el valor de la propiedad nroContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroContrato(String value) {
        this.nroContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad rangoDeFechas.
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
     * Define el valor de la propiedad rangoDeFechas.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setRangoDeFechas(RangoFechaType value) {
        this.rangoDeFechas = value;
    }

}
