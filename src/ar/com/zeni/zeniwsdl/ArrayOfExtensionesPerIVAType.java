
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfExtensionesPerIVAType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfExtensionesPerIVAType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="extes" type="{http://www.zeni.com.ar/ZeniWSDL/}ExtensionesPerIVAType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfExtensionesPerIVAType", propOrder = {
    "extes"
})
public class ArrayOfExtensionesPerIVAType {

    protected List<ExtensionesPerIVAType> extes;

    /**
     * Gets the value of the extes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtensionesPerIVAType }
     * 
     * 
     */
    public List<ExtensionesPerIVAType> getExtes() {
        if (extes == null) {
            extes = new ArrayList<ExtensionesPerIVAType>();
        }
        return this.extes;
    }

}
