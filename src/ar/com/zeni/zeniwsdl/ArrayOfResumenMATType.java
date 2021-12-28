
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfResumenMATType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfResumenMATType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resumenes" type="{http://www.zeni.com.ar/ZeniWSDL/}ResumenMATType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfResumenMATType", propOrder = {
    "resumenes"
})
public class ArrayOfResumenMATType {

    protected List<ResumenMATType> resumenes;

    /**
     * Gets the value of the resumenes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resumenes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResumenes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResumenMATType }
     * 
     * 
     */
    public List<ResumenMATType> getResumenes() {
        if (resumenes == null) {
            resumenes = new ArrayList<ResumenMATType>();
        }
        return this.resumenes;
    }

}
