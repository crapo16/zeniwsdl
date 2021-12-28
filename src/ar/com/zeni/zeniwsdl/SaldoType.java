
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SaldoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SaldoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="pesos" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="dolares" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SaldoType", propOrder = {
    "fecha",
    "pesos",
    "dolares"
})
public class SaldoType {

    @XmlElement(required = true)
    protected FechaTimeType fecha;
    protected double pesos;
    protected double dolares;

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFecha(FechaTimeType value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the pesos property.
     * 
     */
    public double getPesos() {
        return pesos;
    }

    /**
     * Sets the value of the pesos property.
     * 
     */
    public void setPesos(double value) {
        this.pesos = value;
    }

    /**
     * Gets the value of the dolares property.
     * 
     */
    public double getDolares() {
        return dolares;
    }

    /**
     * Sets the value of the dolares property.
     * 
     */
    public void setDolares(double value) {
        this.dolares = value;
    }

}
