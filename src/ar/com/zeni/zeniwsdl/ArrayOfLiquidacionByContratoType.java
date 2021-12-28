
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de Liquidaciones
 * 
 * <p>Java class for ArrayOfLiquidacionByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfLiquidacionByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="liqui" type="{http://www.zeni.com.ar/ZeniWSDL/}LiquidacionByContratoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfLiquidacionByContratoType", propOrder = {
    "liqui"
})
public class ArrayOfLiquidacionByContratoType {

    protected List<LiquidacionByContratoType> liqui;

    /**
     * Gets the value of the liqui property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the liqui property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLiqui().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LiquidacionByContratoType }
     * 
     * 
     */
    public List<LiquidacionByContratoType> getLiqui() {
        if (liqui == null) {
            liqui = new ArrayList<LiquidacionByContratoType>();
        }
        return this.liqui;
    }

}
