
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de las muestras agrupadas por contratos.
 * 					
 * 
 * <p>Java class for ArrayOfMuestraWrapperType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMuestraWrapperType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="musetra" type="{http://www.zeni.com.ar/ZeniWSDL/}MuestraWrapperType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMuestraWrapperType", propOrder = {
    "musetra"
})
public class ArrayOfMuestraWrapperType {

    protected List<MuestraWrapperType> musetra;

    /**
     * Gets the value of the musetra property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the musetra property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMusetra().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MuestraWrapperType }
     * 
     * 
     */
    public List<MuestraWrapperType> getMusetra() {
        if (musetra == null) {
            musetra = new ArrayList<MuestraWrapperType>();
        }
        return this.musetra;
    }

}
