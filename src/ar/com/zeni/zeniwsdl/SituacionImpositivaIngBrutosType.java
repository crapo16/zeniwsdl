
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SituacionImpositivaIngBrutosType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SituacionImpositivaIngBrutosType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroInscripcion" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="arrayDetalleInscripciones" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfDetallesInscripcionesType"/>
 *         &lt;element name="arrayExtensionesIIBB" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfExtensionesIIBBType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SituacionImpositivaIngBrutosType", propOrder = {
    "numeroInscripcion",
    "arrayDetalleInscripciones",
    "arrayExtensionesIIBB"
})
public class SituacionImpositivaIngBrutosType {

    @XmlElement(required = true)
    protected String numeroInscripcion;
    @XmlElement(required = true)
    protected ArrayOfDetallesInscripcionesType arrayDetalleInscripciones;
    @XmlElement(required = true)
    protected ArrayOfExtensionesIIBBType arrayExtensionesIIBB;

    /**
     * Gets the value of the numeroInscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroInscripcion() {
        return numeroInscripcion;
    }

    /**
     * Sets the value of the numeroInscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroInscripcion(String value) {
        this.numeroInscripcion = value;
    }

    /**
     * Gets the value of the arrayDetalleInscripciones property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDetallesInscripcionesType }
     *     
     */
    public ArrayOfDetallesInscripcionesType getArrayDetalleInscripciones() {
        return arrayDetalleInscripciones;
    }

    /**
     * Sets the value of the arrayDetalleInscripciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDetallesInscripcionesType }
     *     
     */
    public void setArrayDetalleInscripciones(ArrayOfDetallesInscripcionesType value) {
        this.arrayDetalleInscripciones = value;
    }

    /**
     * Gets the value of the arrayExtensionesIIBB property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfExtensionesIIBBType }
     *     
     */
    public ArrayOfExtensionesIIBBType getArrayExtensionesIIBB() {
        return arrayExtensionesIIBB;
    }

    /**
     * Sets the value of the arrayExtensionesIIBB property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfExtensionesIIBBType }
     *     
     */
    public void setArrayExtensionesIIBB(ArrayOfExtensionesIIBBType value) {
        this.arrayExtensionesIIBB = value;
    }

}
