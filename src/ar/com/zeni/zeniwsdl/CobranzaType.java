
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de cobranza. Utilizado en el metodo
 * 						obtenerCobranzas.
 * 					
 * 
 * <p>Clase Java para CobranzaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CobranzaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vencimiento" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="compraVenta" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumCompraVentaType"/>
 *         &lt;element name="importe" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="comprobante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comprobanteId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="importeIVA" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="saldoACobrar" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="saldoAConfirmar" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="saldoUSS" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="datosComprobante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contratId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="contraparte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="observaciones" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="cuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="realizacobranza" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="coe" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="monedaOriginal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importeSaldo" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CobranzaType", propOrder = {
    "vencimiento",
    "compraVenta",
    "importe",
    "comprobante",
    "tipo",
    "comprobanteId",
    "importeIVA",
    "saldoACobrar",
    "saldoAConfirmar",
    "saldoUSS",
    "datosComprobante",
    "contrato",
    "contratId",
    "contraparte",
    "observaciones",
    "cuenta",
    "realizacobranza",
    "coe",
    "monedaOriginal",
    "importeSaldo"
})
public class CobranzaType {

    @XmlElement(required = true)
    protected FechaTimeType vencimiento;
    @XmlElement(required = true)
    protected EnumCompraVentaType compraVenta;
    protected double importe;
    @XmlElement(required = true)
    protected String comprobante;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String comprobanteId;
    protected double importeIVA;
    protected double saldoACobrar;
    protected double saldoAConfirmar;
    protected double saldoUSS;
    @XmlElement(required = true)
    protected String datosComprobante;
    @XmlElement(required = true)
    protected String contrato;
    @XmlElement(required = true)
    protected String contratId;
    @XmlElement(required = true)
    protected String contraparte;
    @XmlElement(required = true)
    protected String observaciones;
    @XmlElement(required = true)
    protected String cuenta;
    @XmlElement(required = true)
    protected String realizacobranza;
    @XmlElement(required = true)
    protected String coe;
    @XmlElement(required = true)
    protected String monedaOriginal;
    protected double importeSaldo;

    /**
     * Obtiene el valor de la propiedad vencimiento.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getVencimiento() {
        return vencimiento;
    }

    /**
     * Define el valor de la propiedad vencimiento.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setVencimiento(FechaTimeType value) {
        this.vencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad compraVenta.
     * 
     * @return
     *     possible object is
     *     {@link EnumCompraVentaType }
     *     
     */
    public EnumCompraVentaType getCompraVenta() {
        return compraVenta;
    }

    /**
     * Define el valor de la propiedad compraVenta.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumCompraVentaType }
     *     
     */
    public void setCompraVenta(EnumCompraVentaType value) {
        this.compraVenta = value;
    }

    /**
     * Obtiene el valor de la propiedad importe.
     * 
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Define el valor de la propiedad importe.
     * 
     */
    public void setImporte(double value) {
        this.importe = value;
    }

    /**
     * Obtiene el valor de la propiedad comprobante.
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
     * Define el valor de la propiedad comprobante.
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
     * Obtiene el valor de la propiedad tipo.
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
     * Define el valor de la propiedad tipo.
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
     * Obtiene el valor de la propiedad comprobanteId.
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
     * Define el valor de la propiedad comprobanteId.
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
     * Obtiene el valor de la propiedad importeIVA.
     * 
     */
    public double getImporteIVA() {
        return importeIVA;
    }

    /**
     * Define el valor de la propiedad importeIVA.
     * 
     */
    public void setImporteIVA(double value) {
        this.importeIVA = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoACobrar.
     * 
     */
    public double getSaldoACobrar() {
        return saldoACobrar;
    }

    /**
     * Define el valor de la propiedad saldoACobrar.
     * 
     */
    public void setSaldoACobrar(double value) {
        this.saldoACobrar = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoAConfirmar.
     * 
     */
    public double getSaldoAConfirmar() {
        return saldoAConfirmar;
    }

    /**
     * Define el valor de la propiedad saldoAConfirmar.
     * 
     */
    public void setSaldoAConfirmar(double value) {
        this.saldoAConfirmar = value;
    }

    /**
     * Obtiene el valor de la propiedad saldoUSS.
     * 
     */
    public double getSaldoUSS() {
        return saldoUSS;
    }

    /**
     * Define el valor de la propiedad saldoUSS.
     * 
     */
    public void setSaldoUSS(double value) {
        this.saldoUSS = value;
    }

    /**
     * Obtiene el valor de la propiedad datosComprobante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatosComprobante() {
        return datosComprobante;
    }

    /**
     * Define el valor de la propiedad datosComprobante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatosComprobante(String value) {
        this.datosComprobante = value;
    }

    /**
     * Obtiene el valor de la propiedad contrato.
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
     * Define el valor de la propiedad contrato.
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
     * Obtiene el valor de la propiedad contratId.
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
     * Define el valor de la propiedad contratId.
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
     * Obtiene el valor de la propiedad contraparte.
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
     * Define el valor de la propiedad contraparte.
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
     * Obtiene el valor de la propiedad observaciones.
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
     * Define el valor de la propiedad observaciones.
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
     * Obtiene el valor de la propiedad cuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * Define el valor de la propiedad cuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuenta(String value) {
        this.cuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad realizacobranza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealizacobranza() {
        return realizacobranza;
    }

    /**
     * Define el valor de la propiedad realizacobranza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealizacobranza(String value) {
        this.realizacobranza = value;
    }

    /**
     * Obtiene el valor de la propiedad coe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoe() {
        return coe;
    }

    /**
     * Define el valor de la propiedad coe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoe(String value) {
        this.coe = value;
    }

    /**
     * Obtiene el valor de la propiedad monedaOriginal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaOriginal() {
        return monedaOriginal;
    }

    /**
     * Define el valor de la propiedad monedaOriginal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaOriginal(String value) {
        this.monedaOriginal = value;
    }

    /**
     * Obtiene el valor de la propiedad importeSaldo.
     * 
     */
    public double getImporteSaldo() {
        return importeSaldo;
    }

    /**
     * Define el valor de la propiedad importeSaldo.
     * 
     */
    public void setImporteSaldo(double value) {
        this.importeSaldo = value;
    }

}
