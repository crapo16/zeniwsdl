
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Listado de saldos de cuentas de cliente.
 * 					
 * 
 * <p>Java class for ArrayOfSaldoDeClienteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSaldoDeClienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="saldos" type="{http://www.zeni.com.ar/ZeniWSDL/}SaldoDeClienteType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSaldoDeClienteType", propOrder = {
    "saldos"
})
public class ArrayOfSaldoDeClienteType {

    protected List<SaldoDeClienteType> saldos;

    /**
     * Gets the value of the saldos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the saldos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSaldos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SaldoDeClienteType }
     * 
     * 
     */
    public List<SaldoDeClienteType> getSaldos() {
        if (saldos == null) {
            saldos = new ArrayList<SaldoDeClienteType>();
        }
        return this.saldos;
    }

}
