
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfAjusteComentarioFijacionByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAjusteComentarioFijacionByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ajustes" type="{http://www.zeni.com.ar/ZeniWSDL/}AjusteComentarioFijacionByContratoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAjusteComentarioFijacionByContratoType", propOrder = {
    "ajustes"
})
public class ArrayOfAjusteComentarioFijacionByContratoType {

    protected List<AjusteComentarioFijacionByContratoType> ajustes;

    /**
     * Gets the value of the ajustes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ajustes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAjustes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AjusteComentarioFijacionByContratoType }
     * 
     * 
     */
    public List<AjusteComentarioFijacionByContratoType> getAjustes() {
        if (ajustes == null) {
            ajustes = new ArrayList<AjusteComentarioFijacionByContratoType>();
        }
        return this.ajustes;
    }

}
