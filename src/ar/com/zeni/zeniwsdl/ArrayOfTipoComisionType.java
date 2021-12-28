
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTipoComisionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTipoComisionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comisiones" type="{http://www.zeni.com.ar/ZeniWSDL/}ComisionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTipoComisionType", propOrder = {
    "comisiones"
})
public class ArrayOfTipoComisionType {

    protected List<ComisionType> comisiones;

    /**
     * Gets the value of the comisiones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comisiones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComisiones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComisionType }
     * 
     * 
     */
    public List<ComisionType> getComisiones() {
        if (comisiones == null) {
            comisiones = new ArrayList<ComisionType>();
        }
        return this.comisiones;
    }

}
