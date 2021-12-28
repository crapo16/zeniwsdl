
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Contiene totalizadores y la lista de cupos.
 * 					
 * 
 * <p>Java class for CuposResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CuposResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuposList" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfCupoType"/>
 *         &lt;element name="totalCantidad" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="totalKilosFacturados" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CuposResponseType", propOrder = {
    "cuposList",
    "totalCantidad",
    "totalKilosFacturados"
})
public class CuposResponseType {

    @XmlElement(required = true)
    protected ArrayOfCupoType cuposList;
    protected float totalCantidad;
    protected float totalKilosFacturados;

    /**
     * Gets the value of the cuposList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCupoType }
     *     
     */
    public ArrayOfCupoType getCuposList() {
        return cuposList;
    }

    /**
     * Sets the value of the cuposList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCupoType }
     *     
     */
    public void setCuposList(ArrayOfCupoType value) {
        this.cuposList = value;
    }

    /**
     * Gets the value of the totalCantidad property.
     * 
     */
    public float getTotalCantidad() {
        return totalCantidad;
    }

    /**
     * Sets the value of the totalCantidad property.
     * 
     */
    public void setTotalCantidad(float value) {
        this.totalCantidad = value;
    }

    /**
     * Gets the value of the totalKilosFacturados property.
     * 
     */
    public float getTotalKilosFacturados() {
        return totalKilosFacturados;
    }

    /**
     * Sets the value of the totalKilosFacturados property.
     * 
     */
    public void setTotalKilosFacturados(float value) {
        this.totalKilosFacturados = value;
    }

}
