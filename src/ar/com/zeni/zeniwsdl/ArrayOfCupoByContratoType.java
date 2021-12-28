
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de cupos por un contrato
 * 					
 * 
 * <p>Java class for ArrayOfCupoByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCupoByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cup" type="{http://www.zeni.com.ar/ZeniWSDL/}CupoByContratoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCupoByContratoType", propOrder = {
    "cup"
})
public class ArrayOfCupoByContratoType {

    protected List<CupoByContratoType> cup;

    /**
     * Gets the value of the cup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CupoByContratoType }
     * 
     * 
     */
    public List<CupoByContratoType> getCup() {
        if (cup == null) {
            cup = new ArrayList<CupoByContratoType>();
        }
        return this.cup;
    }

}
