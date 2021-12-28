
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de Fijaciones
 * 
 * <p>Java class for ArrayOfFijacionByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfFijacionByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fija" type="{http://www.zeni.com.ar/ZeniWSDL/}FijacionByContratoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFijacionByContratoType", propOrder = {
    "fija"
})
public class ArrayOfFijacionByContratoType {

    protected List<FijacionByContratoType> fija;

    /**
     * Gets the value of the fija property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fija property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFija().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FijacionByContratoType }
     * 
     * 
     */
    public List<FijacionByContratoType> getFija() {
        if (fija == null) {
            fija = new ArrayList<FijacionByContratoType>();
        }
        return this.fija;
    }

}
