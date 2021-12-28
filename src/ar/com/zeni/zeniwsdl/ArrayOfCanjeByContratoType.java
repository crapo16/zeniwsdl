
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de Canjes segun Contrato
 * 
 * <p>Clase Java para ArrayOfCanjeByContratoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCanjeByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="canje" type="{http://www.zeni.com.ar/ZeniWSDL/}CanjeByContratoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCanjeByContratoType", propOrder = {
    "canje"
})
public class ArrayOfCanjeByContratoType {

    protected List<CanjeByContratoType> canje;

    /**
     * Gets the value of the canje property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the canje property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCanje().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CanjeByContratoType }
     * 
     * 
     */
    public List<CanjeByContratoType> getCanje() {
        if (canje == null) {
            canje = new ArrayList<CanjeByContratoType>();
        }
        return this.canje;
    }

}
