
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo Referencia
 * 					
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenciaType", propOrder = {
    "idOperacion",
    "nroReferencia"
})
public class ReferenciaType {

    @XmlElement(required = true)
    protected String idOperacion;
    @XmlElement(required = true)
    protected String nroReferencia;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOperacion() {
        return idOperacion;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOperacion(String value) {
        this.idOperacion = value;
    }

    /**
     * Gets the value of the descripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroReferencia() {
        return nroReferencia;
    }

    /**
     * Sets the value of the descripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroReferencia(String value) {
        this.nroReferencia = value;
    }

}
