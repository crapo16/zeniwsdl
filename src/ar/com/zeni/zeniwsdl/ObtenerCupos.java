
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 							parametros para la obtencion de los cupos.
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
 *         &lt;element name="rangoFechas" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType"/>
 *         &lt;element name="clienteId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
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
    "rangoFechas",
    "clienteId",
    "contratoNro"
})
@XmlRootElement(name = "obtenerCupos")
public class ObtenerCupos {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected RangoFechaType rangoFechas;
    @XmlElement(required = true)
    protected String clienteId;
    protected String contratoNro;

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
     * Obtiene el valor de la propiedad rangoFechas.
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
     * Define el valor de la propiedad rangoFechas.
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
     * Obtiene el valor de la propiedad clienteId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClienteId() {
        return clienteId;
    }

    /**
     * Define el valor de la propiedad clienteId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClienteId(String value) {
        this.clienteId = value;
    }

    /**
     * Obtiene el valor de la propiedad contratoNro.
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
     * Define el valor de la propiedad contratoNro.
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
