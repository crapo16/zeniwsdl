
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfMermasByEntregaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMermasByEntregaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mermas" type="{http://www.zeni.com.ar/ZeniWSDL/}MermaByEntregaType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMermasByEntregaType", propOrder = {
    "mermas"
})
public class ArrayOfMermasByEntregaType {

    protected List<MermaByEntregaType> mermas;

    /**
     * Gets the value of the mermas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mermas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMermas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MermaByEntregaType }
     * 
     * 
     */
    public List<MermaByEntregaType> getMermas() {
        if (mermas == null) {
            mermas = new ArrayList<MermaByEntregaType>();
        }
        return this.mermas;
    }

}
