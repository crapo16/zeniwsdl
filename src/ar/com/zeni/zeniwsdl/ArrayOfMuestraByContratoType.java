
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Listado de muestras por contrato, tiene un
 * 						contrato asociado a una lista de muestras.
 * 					
 * 
 * <p>Java class for ArrayOfMuestraByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfMuestraByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="muestrasByContrato" type="{http://www.zeni.com.ar/ZeniWSDL/}MuestraByContratoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfMuestraByContratoType", propOrder = {
    "muestrasByContrato"
})
public class ArrayOfMuestraByContratoType {

    protected List<MuestraByContratoType> muestrasByContrato;

    /**
     * Gets the value of the muestrasByContrato property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the muestrasByContrato property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMuestrasByContrato().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MuestraByContratoType }
     * 
     * 
     */
    public List<MuestraByContratoType> getMuestrasByContrato() {
        if (muestrasByContrato == null) {
            muestrasByContrato = new ArrayList<MuestraByContratoType>();
        }
        return this.muestrasByContrato;
    }

}
