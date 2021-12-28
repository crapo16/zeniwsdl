
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDetallesInscripcionesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDetallesInscripcionesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="detas" type="{http://www.zeni.com.ar/ZeniWSDL/}DetallesInscripcionesType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDetallesInscripcionesType", propOrder = {
    "detas"
})
public class ArrayOfDetallesInscripcionesType {

    protected List<DetallesInscripcionesType> detas;

    /**
     * Gets the value of the detas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the detas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDetas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DetallesInscripcionesType }
     * 
     * 
     */
    public List<DetallesInscripcionesType> getDetas() {
        if (detas == null) {
            detas = new ArrayList<DetallesInscripcionesType>();
        }
        return this.detas;
    }

}
