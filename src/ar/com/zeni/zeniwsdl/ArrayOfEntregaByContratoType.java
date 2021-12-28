
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de entregas por contrato.
 * 					
 * 
 * <p>Java class for ArrayOfEntregaByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfEntregaByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entre" type="{http://www.zeni.com.ar/ZeniWSDL/}EntregaByContratoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="kilos" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ctg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kgfijar" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfEntregaByContratoType", propOrder = {
    "entre",
    "kilos",
    "kgfijar",
    "ctg"
})
public class ArrayOfEntregaByContratoType {

    protected List<EntregaByContratoType> entre;
    protected float kilos;
    protected String ctg;

    public String getCtg() {
        return ctg;
    }

    public void setCtg(String ctg) {
        this.ctg = ctg;
    }
    protected float kgfijar;

    /**
     * Gets the value of the entre property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entre property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntre().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntregaByContratoType }
     * 
     * 
     */
    public List<EntregaByContratoType> getEntre() {
        if (entre == null) {
            entre = new ArrayList<EntregaByContratoType>();
        }
        return this.entre;
    }

    /**
     * Gets the value of the kilos property.
     * 
     */
    public float getKilos() {
        return kilos;
    }

    /**
     * Sets the value of the kilos property.
     * 
     */
    public void setKilos(float value) {
        this.kilos = value;
    }

    /**
     * Gets the value of the kgfijar property.
     * 
     */
    public float getKgfijar() {
        return kgfijar;
    }

    /**
     * Sets the value of the kgfijar property.
     * 
     */
    public void setKgfijar(float value) {
        this.kgfijar = value;
    }

}
