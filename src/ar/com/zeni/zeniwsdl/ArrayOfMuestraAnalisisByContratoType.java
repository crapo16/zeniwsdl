
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Listado de analisis.
 * 
 * <p>Java class for ArrayOfMuestraAnalisisByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMuestraAnalisisByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="analisis" type="{http://www.zeni.com.ar/ZeniWSDL/}MuestraAnalisisByContratoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMuestraAnalisisByContratoType", propOrder = {
    "analisis"
})
public class ArrayOfMuestraAnalisisByContratoType {

    protected List<MuestraAnalisisByContratoType> analisis;

    /**
     * Gets the value of the analisis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the analisis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnalisis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MuestraAnalisisByContratoType }
     * 
     * 
     */
    public List<MuestraAnalisisByContratoType> getAnalisis() {
        if (analisis == null) {
            analisis = new ArrayList<MuestraAnalisisByContratoType>();
        }
        return this.analisis;
    }

}
