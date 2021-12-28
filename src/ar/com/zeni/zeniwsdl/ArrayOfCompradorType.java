
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de Compradores
 * 
 * <p>Java class for ArrayOfCompradorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCompradorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="compradores" type="{http://www.zeni.com.ar/ZeniWSDL/}CompradorType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCompradorType", propOrder = {
    "compradores"
})
public class ArrayOfCompradorType {

    protected List<CompradorType> compradores;

    /**
     * Gets the value of the compradores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compradores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompradores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CompradorType }
     * 
     * 
     */
    public List<CompradorType> getCompradores() {
        if (compradores == null) {
            compradores = new ArrayList<CompradorType>();
        }
        return this.compradores;
    }

}
