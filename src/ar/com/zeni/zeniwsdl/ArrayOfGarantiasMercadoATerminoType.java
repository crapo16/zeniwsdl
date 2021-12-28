
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfOperacionMercadoATerminoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfOperacionMercadoATerminoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mercater" type="{http://www.zeni.com.ar/ZeniWSDL/}OperacionMercadoATerminoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfGarantiasMercadoATerminoType", propOrder = {
    "garantias"
})
public class ArrayOfGarantiasMercadoATerminoType {

    protected List<GarantiasMercadoATerminoType> garantias;

    /**
     * Gets the value of the mercater property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mercater property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMercater().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OperacionMercadoATerminoType }
     * 
     * 
     */
    public List<GarantiasMercadoATerminoType> getGarantias() {
        if (garantias == null) {
        	garantias = new ArrayList<GarantiasMercadoATerminoType>();
        }
        return this.garantias;
    }

}
