
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MuestraType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MuestraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nroMuestra" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="muestraId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="kilos" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="cPorte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nroCertificado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechaCertificado" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="factor" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="contrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totKilos" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="promPond" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="analisis" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="nroAnalisis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MuestraType", propOrder = {
    "nroMuestra",
    "muestraId",
    "kilos",
    "cPorte",
    "nroCertificado",
    "fechaCertificado",
    "factor",
    "contrato",
    "totKilos",
    "promPond",
    "analisis",
    "nroAnalisis",
    "result"
})
public class MuestraType {

    @XmlElement(required = true)
    protected String nroMuestra;
    @XmlElement(required = true)
    protected String muestraId;
    protected float kilos;
    @XmlElement(required = true)
    protected String cPorte;
    @XmlElement(required = true)
    protected String nroCertificado;
    @XmlElement(required = true)
    protected FechaTimeType fechaCertificado;
    protected float factor;
    @XmlElement(required = true)
    protected String contrato;
    protected float totKilos;
    protected float promPond;
    @XmlElement(required = true)
    protected String analisis;
    @XmlElement(required = true)
    protected String nroAnalisis;
    protected float result;

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
     * Gets the value of the muestraId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMuestraId() {
        return muestraId;
    }

    /**
     * Sets the value of the muestraId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMuestraId(String value) {
        this.muestraId = value;
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
     * Gets the value of the cPorte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPorte() {
        return cPorte;
    }

    /**
     * Sets the value of the cPorte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPorte(String value) {
        this.cPorte = value;
    }

    /**
     * Gets the value of the nroCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroCertificado() {
        return nroCertificado;
    }

    /**
     * Sets the value of the nroCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroCertificado(String value) {
        this.nroCertificado = value;
    }

    /**
     * Gets the value of the fechaCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaCertificado() {
        return fechaCertificado;
    }

    /**
     * Sets the value of the fechaCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaCertificado(FechaTimeType value) {
        this.fechaCertificado = value;
    }

    /**
     * Gets the value of the factor property.
     * 
     */
    public float getFactor() {
        return factor;
    }

    /**
     * Sets the value of the factor property.
     * 
     */
    public void setFactor(float value) {
        this.factor = value;
    }

    /**
     * Gets the value of the contrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrato() {
        return contrato;
    }

    /**
     * Sets the value of the contrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrato(String value) {
        this.contrato = value;
    }

    /**
     * Gets the value of the totKilos property.
     * 
     */
    public float getTotKilos() {
        return totKilos;
    }

    /**
     * Sets the value of the totKilos property.
     * 
     */
    public void setTotKilos(float value) {
        this.totKilos = value;
    }

    /**
     * Gets the value of the promPond property.
     * 
     */
    public float getPromPond() {
        return promPond;
    }

    /**
     * Sets the value of the promPond property.
     * 
     */
    public void setPromPond(float value) {
        this.promPond = value;
    }

    /**
     * Gets the value of the analisis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnalisis() {
        return analisis;
    }

    /**
     * Sets the value of the analisis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnalisis(String value) {
        this.analisis = value;
    }

    /**
     * Gets the value of the nroAnalisis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroAnalisis() {
        return nroAnalisis;
    }

    /**
     * Sets the value of the nroAnalisis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroAnalisis(String value) {
        this.nroAnalisis = value;
    }

    /**
     * Gets the value of the result property.
     * 
     */
    public float getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     */
    public void setResult(float value) {
        this.result = value;
    }

}
