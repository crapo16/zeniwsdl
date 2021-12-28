
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfTenenciaValorizada complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTenenciaValorizada">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tenenciaValorizada" type="{http://www.zeni.com.ar/ZeniWSDL/}TenenciaVal" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTenenciaValorizada", propOrder = {
    "tenenciaValorizada"
})
public class ArrayOfTenenciaValorizada {

    protected List<TenenciaVal> tenenciaValorizada;

    /**
     * Gets the value of the tenenciaValorizada property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tenenciaValorizada property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTenenciaValorizada().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TenenciaVal }
     * 
     * 
     */
    public List<TenenciaVal> getTenenciaValorizada() {
        if (tenenciaValorizada == null) {
            tenenciaValorizada = new ArrayList<TenenciaVal>();
        }
        return this.tenenciaValorizada;
    }

}
