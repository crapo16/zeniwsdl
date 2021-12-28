
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SituacionImpositivaInformacionImpositivaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SituacionImpositivaInformacionImpositivaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="iva" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="categoria" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="percepcionIVA" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="arrayExtensionesIIBB" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfExtensionesPerIVAType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SituacionImpositivaInformacionImpositivaType", propOrder = {
    "iva",
    "categoria",
    "percepcionIVA",
    "arrayExtensionesIIBB"
})
public class SituacionImpositivaInformacionImpositivaType {

    @XmlElement(required = true)
    protected String iva;
    @XmlElement(required = true)
    protected String categoria;
    @XmlElement(required = true)
    protected String percepcionIVA;
    @XmlElement(required = true)
    protected ArrayOfExtensionesPerIVAType arrayExtensionesIIBB;

    /**
     * Gets the value of the iva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIva() {
        return iva;
    }

    /**
     * Sets the value of the iva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIva(String value) {
        this.iva = value;
    }

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
     * Gets the value of the percepcionIVA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercepcionIVA() {
        return percepcionIVA;
    }

    /**
     * Sets the value of the percepcionIVA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercepcionIVA(String value) {
        this.percepcionIVA = value;
    }

    /**
     * Gets the value of the arrayExtensionesIIBB property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfExtensionesPerIVAType }
     *     
     */
    public ArrayOfExtensionesPerIVAType getArrayExtensionesIIBB() {
        return arrayExtensionesIIBB;
    }

    /**
     * Sets the value of the arrayExtensionesIIBB property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfExtensionesPerIVAType }
     *     
     */
    public void setArrayExtensionesIIBB(ArrayOfExtensionesPerIVAType value) {
        this.arrayExtensionesIIBB = value;
    }

}
