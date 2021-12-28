
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Cuenta corriente del cliente.
 * 					
 * 
 * <p>Java class for CuentaCorrienteContableType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CuentaCorrienteContableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comprobanteId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="contratId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="letra" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="suc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroComprobante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="interno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detalle" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="contrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importePesos" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="importeDolares" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="importeEquivalentePesos" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="saldo" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="moneda" type="{http://www.zeni.com.ar/ZeniWSDL/}MonedaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CuentaCorrienteContableType", propOrder = {
    "fecha",
    "tipo",
    "comprobanteId",
    "contratId",
    "letra",
    "suc",
    "numeroComprobante",
    "interno",
    "detalle",
    "contrato",
    "importePesos",
    "importeDolares",
    "importeEquivalentePesos",
    "saldo",
    "moneda"
})
public class CuentaCorrienteContableType {

    @XmlElement(required = true)
    protected FechaTimeType fecha;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String comprobanteId;
    @XmlElement(required = true)
    protected String contratId;
    @XmlElement(required = true)
    protected String letra;
    @XmlElement(required = true)
    protected String suc;
    @XmlElement(required = true)
    protected String numeroComprobante;
    @XmlElement(required = true)
    protected String interno;
    @XmlElement(required = true)
    protected String detalle;
    @XmlElement(required = true)
    protected String contrato;
    protected double importePesos;
    protected double importeDolares;
    protected double importeEquivalentePesos;
    protected double saldo;
    @XmlElement(required = true)
    protected MonedaType moneda;

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
     * Gets the value of the contratId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratId() {
        return contratId;
    }

    /**
     * Sets the value of the contratId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratId(String value) {
        this.contratId = value;
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
     * Gets the value of the numeroComprobante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * Sets the value of the numeroComprobante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroComprobante(String value) {
        this.numeroComprobante = value;
    }

    /**
     * Gets the value of the interno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterno() {
        return interno;
    }

    /**
     * Sets the value of the interno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterno(String value) {
        this.interno = value;
    }

    /**
     * Gets the value of the detalle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * Sets the value of the detalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetalle(String value) {
        this.detalle = value;
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
     * Gets the value of the importePesos property.
     * 
     */
    public double getImportePesos() {
        return importePesos;
    }

    /**
     * Sets the value of the importePesos property.
     * 
     */
    public void setImportePesos(double value) {
        this.importePesos = value;
    }

    /**
     * Gets the value of the importeDolares property.
     * 
     */
    public double getImporteDolares() {
        return importeDolares;
    }

    /**
     * Sets the value of the importeDolares property.
     * 
     */
    public void setImporteDolares(double value) {
        this.importeDolares = value;
    }

    /**
     * Gets the value of the importeEquivalentePesos property.
     * 
     */
    public double getImporteEquivalentePesos() {
        return importeEquivalentePesos;
    }

    /**
     * Sets the value of the importeEquivalentePesos property.
     * 
     */
    public void setImporteEquivalentePesos(double value) {
        this.importeEquivalentePesos = value;
    }

    /**
     * Gets the value of the saldo property.
     * 
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Sets the value of the saldo property.
     * 
     */
    public void setSaldo(double value) {
        this.saldo = value;
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

}
