
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="out" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfCanjeByContratoType"/>
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
    "out"
})
@XmlRootElement(name = "obtenerCanjeByContratoResponse")
public class ObtenerCanjeByContratoResponse {

    @XmlElement(required = true)
    protected ArrayOfCanjeByContratoType out;

    /**
     * Obtiene el valor de la propiedad out.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCanjeByContratoType }
     *     
     */
    public ArrayOfCanjeByContratoType getOut() {
        return out;
    }

    /**
     * Define el valor de la propiedad out.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCanjeByContratoType }
     *     
     */
    public void setOut(ArrayOfCanjeByContratoType value) {
        this.out = value;
    }

}
