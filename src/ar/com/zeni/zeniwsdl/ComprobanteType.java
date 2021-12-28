
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de comprobante utilizado en obtencion de
 * 						comprobantes.
 * 					
 * 
 * <p>Java class for ComprobanteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComprobanteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comprobanteId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="comprobante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contratoId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="fechaVto" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="fecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="letra" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="suc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nroComprobante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="moneda" type="{http://www.zeni.com.ar/ZeniWSDL/}MonedaType"/>
 *         &lt;element name="parteTipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parteId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="contraparteId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="contraparte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cuit" type="{http://www.zeni.com.ar/ZeniWSDL/}CuitType"/>
 *         &lt;element name="importeIVA" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="total" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="descargado" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComprobanteType", propOrder = {
    "comprobanteId",
    "comprobante",
    "contrato",
    "contratoId",
    "fechaVto",
    "fecha",
    "tipo",
    "letra",
    "suc",
    "nroComprobante",
    "moneda",
    "parteTipo",
    "parteId",
    "contraparteId",
    "contraparte",
    "cuit",
    "importeIVA",
    "total",
    "descargado",
    "esfinal"
    
})
public class ComprobanteType {

    @XmlElement(required = true)
    protected String comprobanteId;
    @XmlElement(required = true)
    protected String comprobante;
    @XmlElement(required = true)
    protected String contrato;
    @XmlElement(required = true)
    protected String contratoId;
    @XmlElement(required = true)
    protected FechaTimeType fechaVto;
    @XmlElement(required = true)
    protected FechaTimeType fecha;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String letra;
    @XmlElement(required = true)
    protected String suc;
    @XmlElement(required = true)
    protected String nroComprobante;
    @XmlElement(required = true)
    protected MonedaType moneda;
    @XmlElement(required = true)
    protected String parteTipo;
    @XmlElement(required = true)
    protected String parteId;
    @XmlElement(required = true)
    protected String contraparteId;
    @XmlElement(required = true)
    protected String contraparte;
    @XmlElement(required = true)
    protected String cuit;
    protected double importeIVA;
    protected double total;
    @XmlElement(required = true)
    protected SINOType descargado;
    
    // #1633
    @XmlElement()
    protected SINOType esfinal;
    
    /**
     * Gets the value of the comprobanteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComprobanteId() {
        return comprobanteId;
    }

    /**
     * Sets the value of the comprobanteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComprobanteId(String value) {
        this.comprobanteId = value;
    }

    /**
     * Gets the value of the comprobante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * Sets the value of the comprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComprobante(String value) {
        this.comprobante = value;
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
     * Gets the value of the contratoId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratoId() {
        return contratoId;
    }

    /**
     * Sets the value of the contratoId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratoId(String value) {
        this.contratoId = value;
    }

    /**
     * Gets the value of the fechaVto property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaVto() {
        return fechaVto;
    }

    /**
     * Sets the value of the fechaVto property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaVto(FechaTimeType value) {
        this.fechaVto = value;
    }

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
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the letra property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLetra() {
        return letra;
    }

    /**
     * Sets the value of the letra property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLetra(String value) {
        this.letra = value;
    }

    /**
     * Gets the value of the suc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuc() {
        return suc;
    }

    /**
     * Sets the value of the suc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuc(String value) {
        this.suc = value;
    }

    /**
     * Gets the value of the nroComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroComprobante() {
        return nroComprobante;
    }

    /**
     * Sets the value of the nroComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroComprobante(String value) {
        this.nroComprobante = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link MonedaType }
     *     
     */
    public MonedaType getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonedaType }
     *     
     */
    public void setMoneda(MonedaType value) {
        this.moneda = value;
    }

    /**
     * Gets the value of the parteTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteTipo() {
        return parteTipo;
    }

    /**
     * Sets the value of the parteTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteTipo(String value) {
        this.parteTipo = value;
    }

    /**
     * Gets the value of the parteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteId() {
        return parteId;
    }

    /**
     * Sets the value of the parteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteId(String value) {
        this.parteId = value;
    }

    /**
     * Gets the value of the contraparteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContraparteId() {
        return contraparteId;
    }

    /**
     * Sets the value of the contraparteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContraparteId(String value) {
        this.contraparteId = value;
    }

    /**
     * Gets the value of the contraparte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContraparte() {
        return contraparte;
    }

    /**
     * Sets the value of the contraparte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContraparte(String value) {
        this.contraparte = value;
    }

    /**
     * Gets the value of the cuit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuit() {
        return cuit;
    }

    /**
     * Sets the value of the cuit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuit(String value) {
        this.cuit = value;
    }

    /**
     * Gets the value of the importeIVA property.
     * 
     */
    public double getImporteIVA() {
        return importeIVA;
    }

    /**
     * Sets the value of the importeIVA property.
     * 
     */
    public void setImporteIVA(double value) {
        this.importeIVA = value;
    }

    /**
     * Gets the value of the total property.
     * 
     */
    public double getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(double value) {
        this.total = value;
    }

    /**
     * Gets the value of the descargado property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getDescargado() {
        return descargado;
    }

    /**
     * Sets the value of the descargado property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setDescargado(SINOType value) {
        this.descargado = value;
    }

    // #1633
    
    public SINOType getEsFinal() {
        return esfinal;
    }

    public void setEsFinal(SINOType sino) {
    	esfinal = sino;
    }
}
