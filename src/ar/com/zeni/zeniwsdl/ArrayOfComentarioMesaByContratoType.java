
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de comentarios de mesa
 * 
 * <p>Java class for ArrayOfComentarioMesaByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfComentarioMesaByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comen" type="{http://www.zeni.com.ar/ZeniWSDL/}ComentarioMesaByContratoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfComentarioMesaByContratoType", propOrder = {
    "comen"
})
public class ArrayOfComentarioMesaByContratoType {

    protected List<ComentarioMesaByContratoType> comen;

    /**
     * Gets the value of the comen property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comen property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComen().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComentarioMesaByContratoType }
     * 
     * 
     */
    public List<ComentarioMesaByContratoType> getComen() {
        if (comen == null) {
            comen = new ArrayList<ComentarioMesaByContratoType>();
        }
        return this.comen;
    }

}
