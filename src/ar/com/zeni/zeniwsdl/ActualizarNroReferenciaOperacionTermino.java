
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "auth",
    "listaReferencia"
})
@XmlRootElement(name = "actualizarNroReferenciaOperacionTermino")
public class ActualizarNroReferenciaOperacionTermino {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(name = "listaReferencia", required = true)
    protected ArrayOfReferenciaType listaReferencia;

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
     * Gets the value of the rangoDeFechas property.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public ArrayOfReferenciaType getListaReferencia() {
        return listaReferencia;
    }

    /**
     * Sets the value of the rangoDeFechas property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setListaReferencia(ArrayOfReferenciaType value) {
        this.listaReferencia = value;
    }

}
