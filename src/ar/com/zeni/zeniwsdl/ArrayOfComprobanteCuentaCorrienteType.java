
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de comprobantes
 * 
 * <p>Java class for ArrayOfComprobanteCuentaCorrienteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfComprobanteCuentaCorrienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comprobantesCC" type="{http://www.zeni.com.ar/ZeniWSDL/}ComprobanteCuentaCorrienteType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfComprobanteCuentaCorrienteType", propOrder = {
    "comprobantesCC"
})
public class ArrayOfComprobanteCuentaCorrienteType {

    protected List<ComprobanteCuentaCorrienteType> comprobantesCC;

    /**
     * Gets the value of the comprobantesCC property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comprobantesCC property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComprobantesCC().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComprobanteCuentaCorrienteType }
     * 
     * 
     */
    public List<ComprobanteCuentaCorrienteType> getComprobantesCC() {
        if (comprobantesCC == null) {
            comprobantesCC = new ArrayList<ComprobanteCuentaCorrienteType>();
        }
        return this.comprobantesCC;
    }

}
