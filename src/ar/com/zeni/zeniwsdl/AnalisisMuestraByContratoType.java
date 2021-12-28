
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Analisis de contrato.
 * 
 * <p>Java class for AnalisisMuestraByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AnalisisMuestraByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="analisis" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfMuestraAnalisisByContratoType"/>
 *         &lt;element name="nrocertificado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaemision" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="camara" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="honorarios" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="sobrekilos" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="observaciones" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="factor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sinnombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnalisisMuestraByContratoType", propOrder = {
    "analisis",
    "nrocertificado",
    "fechaemision",
    "camara",
    "honorarios",
    "sobrekilos",
    "observaciones",
    "factor",
    "sinnombre"
})
public class AnalisisMuestraByContratoType {

    @XmlElement(required = true)
    protected ArrayOfMuestraAnalisisByContratoType analisis;
    @XmlElement(required = true)
    protected String nrocertificado;
    @XmlElement(required = true)
    protected FechaTimeType fechaemision;
    @XmlElement(required = true)
    protected String camara;
    protected float honorarios;
    @XmlElement(required = true)
    protected String sobrekilos;
    @XmlElement(required = true)
    protected String observaciones;
    @XmlElement(required = true)
    protected String factor;
    @XmlElement(required = true)
    protected String sinnombre;

    /**
     * Gets the value of the analisis property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMuestraAnalisisByContratoType }
     *     
     */
    public ArrayOfMuestraAnalisisByContratoType getAnalisis() {
        return analisis;
    }

    /**
     * Sets the value of the analisis property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMuestraAnalisisByContratoType }
     *     
     */
    public void setAnalisis(ArrayOfMuestraAnalisisByContratoType value) {
        this.analisis = value;
    }

    /**
     * Gets the value of the nrocertificado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrocertificado() {
        return nrocertificado;
    }

    /**
     * Sets the value of the nrocertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrocertificado(String value) {
        this.nrocertificado = value;
    }

    /**
     * Gets the value of the fechaemision property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaemision() {
        return fechaemision;
    }

    /**
     * Sets the value of the fechaemision property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaemision(FechaTimeType value) {
        this.fechaemision = value;
    }

    /**
     * Gets the value of the camara property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCamara() {
        return camara;
    }

    /**
     * Sets the value of the camara property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCamara(String value) {
        this.camara = value;
    }

    /**
     * Gets the value of the honorarios property.
     * 
     */
    public float getHonorarios() {
        return honorarios;
    }

    /**
     * Sets the value of the honorarios property.
     * 
     */
    public void setHonorarios(float value) {
        this.honorarios = value;
    }

    /**
     * Gets the value of the sobrekilos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSobrekilos() {
        return sobrekilos;
    }

    /**
     * Sets the value of the sobrekilos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSobrekilos(String value) {
        this.sobrekilos = value;
    }

    /**
     * Gets the value of the observaciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Sets the value of the observaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservaciones(String value) {
        this.observaciones = value;
    }

    /**
     * Gets the value of the factor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFactor() {
        return factor;
    }

    /**
     * Sets the value of the factor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFactor(String value) {
        this.factor = value;
    }

    /**
     * Gets the value of the sinnombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSinnombre() {
        return sinnombre;
    }

    /**
     * Sets the value of the sinnombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSinnombre(String value) {
        this.sinnombre = value;
    }

}
