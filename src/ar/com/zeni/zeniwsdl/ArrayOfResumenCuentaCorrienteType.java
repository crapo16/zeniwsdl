
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfResumenCuentaCorrienteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfResumenCuentaCorrienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resumenCtaCtes" type="{http://www.zeni.com.ar/ZeniWSDL/}ResumenCuentaCorrienteType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfResumenCuentaCorrienteType", propOrder = {
    "resumenCtaCtes"
})
public class ArrayOfResumenCuentaCorrienteType {

    protected List<ResumenCuentaCorrienteType> resumenCtaCtes;

    /**
     * Gets the value of the resumenCtaCtes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resumenCtaCtes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResumenCtaCtes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResumenCuentaCorrienteType }
     * 
     * 
     */
    public List<ResumenCuentaCorrienteType> getResumenCtaCtes() {
        if (resumenCtaCtes == null) {
            resumenCtaCtes = new ArrayList<ResumenCuentaCorrienteType>();
        }
        return this.resumenCtaCtes;
    }

}
