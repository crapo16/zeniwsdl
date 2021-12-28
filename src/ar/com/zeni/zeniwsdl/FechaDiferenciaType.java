
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FechaDiferenciaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FechaDiferenciaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaOriginal" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="fechaServer" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="diferencia" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FechaDiferenciaType", propOrder = {
    "fechaOriginal",
    "fechaServer",
    "diferencia"
})
public class FechaDiferenciaType {

    @XmlElement(required = true)
    protected FechaTimeType fechaOriginal;
    @XmlElement(required = true)
    protected FechaTimeType fechaServer;
    protected int diferencia;

    /**
     * Gets the value of the fechaOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaOriginal() {
        return fechaOriginal;
    }

    /**
     * Sets the value of the fechaOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaOriginal(FechaTimeType value) {
        this.fechaOriginal = value;
    }

    /**
     * Gets the value of the fechaServer property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaServer() {
        return fechaServer;
    }

    /**
     * Sets the value of the fechaServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaServer(FechaTimeType value) {
        this.fechaServer = value;
    }

    /**
     * Gets the value of the diferencia property.
     * 
     */
    public int getDiferencia() {
        return diferencia;
    }

    /**
     * Sets the value of the diferencia property.
     * 
     */
    public void setDiferencia(int value) {
        this.diferencia = value;
    }

}
