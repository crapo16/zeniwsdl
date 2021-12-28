
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Listado de muestras.
 * 
 * <p>Java class for ArrayOfMuestraType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMuestraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="muestras" type="{http://www.zeni.com.ar/ZeniWSDL/}MuestraType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMuestraType", propOrder = {
    "muestras"
})
public class ArrayOfMuestraType {

    protected List<MuestraType> muestras;

    /**
     * Gets the value of the muestras property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the muestras property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMuestras().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MuestraType }
     * 
     * 
     */
    public List<MuestraType> getMuestras() {
        if (muestras == null) {
            muestras = new ArrayList<MuestraType>();
        }
        return this.muestras;
    }

}
