
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tiene la informacion de la cuenta corriente y
 * 						los comprobantes asociados.
 * 					
 * 
 * <p>Java class for InformacionCuentaCorrienteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InformacionCuentaCorrienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resumenCtaCtes" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfResumenCuentaCorrienteType"/>
 *         &lt;element name="periodo" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType"/>
 *         &lt;element name="emision" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="comprobantes" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfComprobanteCuentaCorrienteType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformacionCuentaCorrienteType", propOrder = {
    "resumenCtaCtes",
    "periodo",
    "emision",
    "comprobantes"
})
public class InformacionCuentaCorrienteType {

    @XmlElement(required = true)
    protected ArrayOfResumenCuentaCorrienteType resumenCtaCtes;
    @XmlElement(required = true)
    protected RangoFechaType periodo;
    @XmlElement(required = true)
    protected FechaTimeType emision;
    @XmlElement(required = true)
    protected ArrayOfComprobanteCuentaCorrienteType comprobantes;

    /**
     * Gets the value of the resumenCtaCtes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfResumenCuentaCorrienteType }
     *     
     */
    public ArrayOfResumenCuentaCorrienteType getResumenCtaCtes() {
        return resumenCtaCtes;
    }

    /**
     * Sets the value of the resumenCtaCtes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfResumenCuentaCorrienteType }
     *     
     */
    public void setResumenCtaCtes(ArrayOfResumenCuentaCorrienteType value) {
        this.resumenCtaCtes = value;
    }

    /**
     * Gets the value of the periodo property.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public RangoFechaType getPeriodo() {
        return periodo;
    }

    /**
     * Sets the value of the periodo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setPeriodo(RangoFechaType value) {
        this.periodo = value;
    }

    /**
     * Gets the value of the emision property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getEmision() {
        return emision;
    }

    /**
     * Sets the value of the emision property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setEmision(FechaTimeType value) {
        this.emision = value;
    }

    /**
     * Gets the value of the comprobantes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfComprobanteCuentaCorrienteType }
     *     
     */
    public ArrayOfComprobanteCuentaCorrienteType getComprobantes() {
        return comprobantes;
    }

    /**
     * Sets the value of the comprobantes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfComprobanteCuentaCorrienteType }
     *     
     */
    public void setComprobantes(ArrayOfComprobanteCuentaCorrienteType value) {
        this.comprobantes = value;
    }

}
