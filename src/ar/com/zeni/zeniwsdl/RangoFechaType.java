
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo que contiene un rango de fechas.
 *
 *
 * <p>Java class for RangoFechaType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RangoFechaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="desde" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="hasta" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RangoFechaType", propOrder = {
    "desde",
    "hasta"
})
public class RangoFechaType {

    @XmlElement(required = true)
    protected FechaTimeType desde;
    @XmlElement(required = true)
    protected FechaTimeType hasta;

    /**
     * Gets the value of the desde property.
     *
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *
     */
    public FechaTimeType getDesde() {
        return desde;
    }

    /**
     * Sets the value of the desde property.
     *
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *
     */
    public void setDesde(FechaTimeType value) {
        this.desde = value;
    }

    /**
     * Gets the value of the hasta property.
     *
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *
     */
    public FechaTimeType getHasta() {
        return hasta;
    }

    /**
     * Sets the value of the hasta property.
     *
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *
     */
    public void setHasta(FechaTimeType value) {
        this.hasta = value;
    }


}
