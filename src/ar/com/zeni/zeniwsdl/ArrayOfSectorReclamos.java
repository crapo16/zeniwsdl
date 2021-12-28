
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfSectorReclamos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSectorReclamos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sectorReclamos" type="{http://www.zeni.com.ar/ZeniWSDL/}SectorReclamo" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSectorReclamos", propOrder = {
    "sectorReclamos"
})
public class ArrayOfSectorReclamos {

    protected List<SectorReclamo> sectorReclamos;

    /**
     * Gets the value of the sectorReclamos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sectorReclamos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSectorReclamos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SectorReclamo }
     * 
     * 
     */
    public List<SectorReclamo> getSectorReclamos() {
        if (sectorReclamos == null) {
            sectorReclamos = new ArrayList<SectorReclamo>();
        }
        return this.sectorReclamos;
    }

}
