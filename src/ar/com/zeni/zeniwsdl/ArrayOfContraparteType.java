
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de cobranzas
 * 
 * <p>Java class for ArrayOfContraparteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfContraparteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contrapartes" type="{http://www.zeni.com.ar/ZeniWSDL/}ContraparteType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfContraparteType", propOrder = {
    "contrapartes"
})
public class ArrayOfContraparteType {

    protected List<ContraparteType> contrapartes;

    /**
     * Gets the value of the contrapartes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contrapartes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContrapartes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContraparteType }
     * 
     * 
     */
    public List<ContraparteType> getContrapartes() {
        if (contrapartes == null) {
            contrapartes = new ArrayList<ContraparteType>();
        }
        return this.contrapartes;
    }

}
