
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Listado de operatorias.
 * 
 * <p>Java class for ArrayOfOperatoriaDListadoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfOperatoriaDListadoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operatorias" type="{http://www.zeni.com.ar/ZeniWSDL/}OperatoriaDListadoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfOperatoriaDListadoType", propOrder = {
    "operatorias"
})
public class ArrayOfOperatoriaDListadoType {

    protected List<OperatoriaDListadoType> operatorias;

    /**
     * Gets the value of the operatorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operatorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperatorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OperatoriaDListadoType }
     * 
     * 
     */
    public List<OperatoriaDListadoType> getOperatorias() {
        if (operatorias == null) {
            operatorias = new ArrayList<OperatoriaDListadoType>();
        }
        return this.operatorias;
    }

}
