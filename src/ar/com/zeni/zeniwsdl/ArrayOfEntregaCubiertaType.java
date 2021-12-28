
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de entregas cubiertas por entregas.
 * 					
 * 
 * <p>Java class for ArrayOfEntregaCubiertaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfEntregaCubiertaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entrecubi" type="{http://www.zeni.com.ar/ZeniWSDL/}EntregaCubiertaType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfEntregaCubiertaType", propOrder = {
    "entrecubi"
})
public class ArrayOfEntregaCubiertaType {

    protected List<EntregaCubiertaType> entrecubi;

    /**
     * Gets the value of the entrecubi property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entrecubi property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntrecubi().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntregaCubiertaType }
     * 
     * 
     */
    public List<EntregaCubiertaType> getEntrecubi() {
        if (entrecubi == null) {
            entrecubi = new ArrayList<EntregaCubiertaType>();
        }
        return this.entrecubi;
    }

}
