
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo que contiene las descripciones de las
 * 						cosas.
 * 					
 * 
 * <p>Java class for MuestraWrapperType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MuestraWrapperType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="muesdelcont" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfMuestraType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MuestraWrapperType", propOrder = {
    "muesdelcont"
})
public class MuestraWrapperType {

    @XmlElement(required = true)
    protected ArrayOfMuestraType muesdelcont;

    /**
     * Gets the value of the muesdelcont property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMuestraType }
     *     
     */
    public ArrayOfMuestraType getMuesdelcont() {
        return muesdelcont;
    }

    /**
     * Sets the value of the muesdelcont property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMuestraType }
     *     
     */
    public void setMuesdelcont(ArrayOfMuestraType value) {
        this.muesdelcont = value;
    }

}
