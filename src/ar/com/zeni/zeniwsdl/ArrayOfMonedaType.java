
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de monedas
 * 
 * <p>Java class for ArrayOfMonedaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMonedaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="monedas" type="{http://www.zeni.com.ar/ZeniWSDL/}MonedaType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMonedaType", propOrder = {
    "monedas"
})
public class ArrayOfMonedaType {

    protected List<MonedaType> monedas;

    /**
     * Gets the value of the monedas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the monedas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMonedas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MonedaType }
     * 
     * 
     */
    public List<MonedaType> getMonedas() {
        if (monedas == null) {
            monedas = new ArrayList<MonedaType>();
        }
        return this.monedas;
    }

}
