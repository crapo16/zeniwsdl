
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfOperacionMATType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfOperacionMATType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operaciones" type="{http://www.zeni.com.ar/ZeniWSDL/}OperacionMATType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfOperacionMATType", propOrder = {
    "operaciones"
})
public class ArrayOfOperacionMATType {

    protected List<OperacionMATType> operaciones;

    /**
     * Gets the value of the operaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OperacionMATType }
     * 
     * 
     */
    public List<OperacionMATType> getOperaciones() {
        if (operaciones == null) {
            operaciones = new ArrayList<OperacionMATType>();
        }
        return this.operaciones;
    }

}
