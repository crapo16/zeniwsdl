
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de calidades
 * 
 * <p>Java class for ArrayOfCalidadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCalidadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calidades" type="{http://www.zeni.com.ar/ZeniWSDL/}CalidadType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCalidadType", propOrder = {
    "calidades"
})
public class ArrayOfCalidadType {

    protected List<CalidadType> calidades;

    /**
     * Gets the value of the calidades property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the calidades property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCalidades().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CalidadType }
     * 
     * 
     */
    public List<CalidadType> getCalidades() {
        if (calidades == null) {
            calidades = new ArrayList<CalidadType>();
        }
        return this.calidades;
    }

}
