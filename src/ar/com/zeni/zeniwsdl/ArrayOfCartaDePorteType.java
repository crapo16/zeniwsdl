
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Listado de cartas de porte.
 * 
 * <p>Java class for ArrayOfCartaDePorteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCartaDePorteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entregas" type="{http://www.zeni.com.ar/ZeniWSDL/}CartaDePorteType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCartaDePorteType", propOrder = {
    "entregas"
})
public class ArrayOfCartaDePorteType {

    protected List<CartaDePorteType> entregas;

    /**
     * Gets the value of the entregas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entregas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntregas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CartaDePorteType }
     * 
     * 
     */
    public List<CartaDePorteType> getEntregas() {
        if (entregas == null) {
            entregas = new ArrayList<CartaDePorteType>();
        }
        return this.entregas;
    }

}
