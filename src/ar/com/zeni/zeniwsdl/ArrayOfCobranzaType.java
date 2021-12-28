
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de cobranzas
 * 
 * <p>Java class for ArrayOfCobranzaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCobranzaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cobranzas" type="{http://www.zeni.com.ar/ZeniWSDL/}CobranzaType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCobranzaType", propOrder = {
    "cobranzas"
})
public class ArrayOfCobranzaType {

    protected List<CobranzaType> cobranzas;

    /**
     * Gets the value of the cobranzas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cobranzas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCobranzas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CobranzaType }
     * 
     * 
     */
    public List<CobranzaType> getCobranzas() {
        if (cobranzas == null) {
            cobranzas = new ArrayList<CobranzaType>();
        }
        return this.cobranzas;
    }

}
