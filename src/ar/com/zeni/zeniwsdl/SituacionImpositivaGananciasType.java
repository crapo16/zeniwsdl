
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SituacionImpositivaGananciasType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SituacionImpositivaGananciasType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoria" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="arrayExtensionesIIBB" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfExtensionesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SituacionImpositivaGananciasType", propOrder = {
    "categoria",
    "arrayExtensionesIIBB"
})
public class SituacionImpositivaGananciasType {

    @XmlElement(required = true)
    protected String categoria;
    @XmlElement(required = true)
    protected ArrayOfExtensionesType arrayExtensionesIIBB;

    /**
     * Gets the value of the categoria property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Sets the value of the categoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoria(String value) {
        this.categoria = value;
    }

    /**
     * Gets the value of the arrayExtensionesIIBB property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfExtensionesType }
     *     
     */
    public ArrayOfExtensionesType getArrayExtensionesIIBB() {
        return arrayExtensionesIIBB;
    }

    /**
     * Sets the value of the arrayExtensionesIIBB property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfExtensionesType }
     *     
     */
    public void setArrayExtensionesIIBB(ArrayOfExtensionesType value) {
        this.arrayExtensionesIIBB = value;
    }

}
