
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Muestras de contrato.
 * 
 * <p>Java class for MuestraByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MuestraByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nroMuestra" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecentr" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="kilos" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="feccarta" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="nrosolic" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nrocertif" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="analisisId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="cporte" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "MuestraByContratoType", propOrder = {
    "nroMuestra",
    "fecentr",
    "kilos",
    "feccarta",
    "nrosolic",
    "nrocertif",
    "analisisId",
    "cporte",
    "factor",
    "sinnombre"
})
public class MuestraByContratoType {

    @XmlElement(required = true)
    protected String nroMuestra;
    @XmlElement(required = true)
    protected FechaTimeType fecentr;
    protected float kilos;
    @XmlElement(required = true)
    protected FechaTimeType feccarta;
    @XmlElement(required = true)
    protected String nrosolic;
    @XmlElement(required = true)
    protected String nrocertif;
    @XmlElement(required = true)
    protected String analisisId;
    @XmlElement(required = true)
    protected String cporte;
    @XmlElement(required = true)
    protected String factor;
    @XmlElement(required = true)
    protected String sinnombre;

    /**
     * Gets the value of the nroMuestra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroMuestra() {
        return nroMuestra;
    }

    /**
     * Sets the value of the nroMuestra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroMuestra(String value) {
        this.nroMuestra = value;
    }

    /**
     * Gets the value of the fecentr property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFecentr() {
        return fecentr;
    }

    /**
     * Sets the value of the fecentr property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFecentr(FechaTimeType value) {
        this.fecentr = value;
    }

    /**
     * Gets the value of the kilos property.
     * 
     */
    public float getKilos() {
        return kilos;
    }

    /**
     * Sets the value of the kilos property.
     * 
     */
    public void setKilos(float value) {
        this.kilos = value;
    }

    /**
     * Gets the value of the feccarta property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFeccarta() {
        return feccarta;
    }

    /**
     * Sets the value of the feccarta property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFeccarta(FechaTimeType value) {
        this.feccarta = value;
    }

    /**
     * Gets the value of the nrosolic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrosolic() {
        return nrosolic;
    }

    /**
     * Sets the value of the nrosolic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrosolic(String value) {
        this.nrosolic = value;
    }

    /**
     * Gets the value of the nrocertif property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrocertif() {
        return nrocertif;
    }

    /**
     * Sets the value of the nrocertif property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrocertif(String value) {
        this.nrocertif = value;
    }

    /**
     * Gets the value of the analisisId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnalisisId() {
        return analisisId;
    }

    /**
     * Sets the value of the analisisId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnalisisId(String value) {
        this.analisisId = value;
    }

    /**
     * Gets the value of the cporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCporte() {
        return cporte;
    }

    /**
     * Sets the value of the cporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCporte(String value) {
        this.cporte = value;
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
