
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de operatorias cobro de mercaderia.
 * 					
 * 
 * <p>Java class for ArrayOfOperatoriasCobroMercaderiaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfOperatoriasCobroMercaderiaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operatoriascobro" type="{http://www.zeni.com.ar/ZeniWSDL/}OperatoriasCobroMercaderiaType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfOperatoriasCobroMercaderiaType", propOrder = {
    "operatoriascobro"
})
public class ArrayOfOperatoriasCobroMercaderiaType {

    protected List<OperatoriasCobroMercaderiaType> operatoriascobro;

    /**
     * Gets the value of the operatoriascobro property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the operatoriascobro property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOperatoriascobro().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OperatoriasCobroMercaderiaType }
     * 
     * 
     */
    public List<OperatoriasCobroMercaderiaType> getOperatoriascobro() {
        if (operatoriascobro == null) {
            operatoriascobro = new ArrayList<OperatoriasCobroMercaderiaType>();
        }
        return this.operatoriascobro;
    }

}
