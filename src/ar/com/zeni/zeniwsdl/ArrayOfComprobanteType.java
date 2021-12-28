
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de comprobantes.
 * 
 * <p>Java class for ArrayOfComprobanteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfComprobanteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comprobantes" type="{http://www.zeni.com.ar/ZeniWSDL/}ComprobanteType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfComprobanteType", propOrder = {
    "comprobantes"
})
public class ArrayOfComprobanteType {

    protected List<ComprobanteType> comprobantes;

    /**
     * Gets the value of the comprobantes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comprobantes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComprobantes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComprobanteType }
     * 
     * 
     */
    public List<ComprobanteType> getComprobantes() {
        if (comprobantes == null) {
            comprobantes = new ArrayList<ComprobanteType>();
        }
        return this.comprobantes;
    }

}
