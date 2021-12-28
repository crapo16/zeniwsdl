
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SituacionImpositivaRegistroFiscalType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SituacionImpositivaRegistroFiscalType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cbu" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="nroBoletin" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="fechaAlta" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="resGeneral" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfResolGeneralType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SituacionImpositivaRegistroFiscalType", propOrder = {
    "cbu",
    "nroBoletin",
    "fechaAlta",
    "resGeneral"
})
public class SituacionImpositivaRegistroFiscalType {

    @XmlElement(required = true)
    protected String cbu;
    @XmlElement(required = true)
    protected String nroBoletin;
    @XmlElement(required = true)
    protected FechaTimeType fechaAlta;
    @XmlElement(required = true)
    protected ArrayOfResolGeneralType resGeneral;

    /**
     * Gets the value of the cbu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbu() {
        return cbu;
    }

    /**
     * Sets the value of the cbu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbu(String value) {
        this.cbu = value;
    }

    /**
     * Gets the value of the nroBoletin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroBoletin() {
        return nroBoletin;
    }

    /**
     * Sets the value of the nroBoletin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroBoletin(String value) {
        this.nroBoletin = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaAlta(FechaTimeType value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the resGeneral property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfResolGeneralType }
     *     
     */
    public ArrayOfResolGeneralType getResGeneral() {
        return resGeneral;
    }

    /**
     * Sets the value of the resGeneral property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfResolGeneralType }
     *     
     */
    public void setResGeneral(ArrayOfResolGeneralType value) {
        this.resGeneral = value;
    }

}
