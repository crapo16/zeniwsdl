
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Listado de condiciones de pago.
 * 					
 * 
 * <p>Java class for ArrayOfCondicionDePagoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCondicionDePagoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="condiciones" type="{http://www.zeni.com.ar/ZeniWSDL/}CondicionDePagoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCondicionDePagoType", propOrder = {
    "condiciones"
})
public class ArrayOfCondicionDePagoType {

    protected List<CondicionDePagoType> condiciones;

    /**
     * Gets the value of the condiciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the condiciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCondiciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CondicionDePagoType }
     * 
     * 
     */
    public List<CondicionDePagoType> getCondiciones() {
        if (condiciones == null) {
            condiciones = new ArrayList<CondicionDePagoType>();
        }
        return this.condiciones;
    }

}
