
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo para comprobantes.
 * 
 * <p>Clase Java para ComprobanteCuentaCorrienteType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ComprobanteCuentaCorrienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuentaid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="denominacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comprobante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comprobanteId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="contrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contratId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="fecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="fechaValor" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="detalle" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="movimiento" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="saldo" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComprobanteCuentaCorrienteType", propOrder = {
    "cuentaid",
    "denominacion",
    "nroCuenta",
    "comprobante",
    "tipo",
    "comprobanteId",
    "contrato",
    "contratId",
    "fecha",
    "fechaValor",
    "detalle",
    "movimiento",
    "saldo"
})
public class ComprobanteCuentaCorrienteType {

    protected String cuentaid;
    protected String denominacion;
    protected String nroCuenta;
    @XmlElement(required = true)
    protected String comprobante;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String comprobanteId;
    @XmlElement(required = true)
    protected String contrato;
    @XmlElement(required = true)
    protected String contratId;
    @XmlElement(required = true)
    protected FechaTimeType fecha;
    @XmlElement(required = true)
    protected FechaTimeType fechaValor;
    @XmlElement(required = true)
    protected String detalle;
    protected double movimiento;
    protected double saldo;

    /**
     * Obtiene el valor de la propiedad cuentaid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaid() {
        return cuentaid;
    }

    /**
     * Define el valor de la propiedad cuentaid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaid(String value) {
        this.cuentaid = value;
    }

    /**
     * Obtiene el valor de la propiedad denominacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominacion() {
        return denominacion;
    }

    /**
     * Define el valor de la propiedad denominacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominacion(String value) {
        this.denominacion = value;
    }

    /**
     * Obtiene el valor de la propiedad nroCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroCuenta() {
        return nroCuenta;
    }

    /**
     * Define el valor de la propiedad nroCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroCuenta(String value) {
        this.nroCuenta = value;
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
     * Obtiene el valor de la propiedad fecha.
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
     * Define el valor de la propiedad fecha.
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
     * Obtiene el valor de la propiedad fechaValor.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaValor() {
        return fechaValor;
    }

    /**
     * Define el valor de la propiedad fechaValor.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaValor(FechaTimeType value) {
        this.fechaValor = value;
    }

    /**
     * Obtiene el valor de la propiedad detalle.
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
     * Define el valor de la propiedad detalle.
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
     * Obtiene el valor de la propiedad movimiento.
     * 
     */
    public double getMovimiento() {
        return movimiento;
    }

    /**
     * Define el valor de la propiedad movimiento.
     * 
     */
    public void setMovimiento(double value) {
        this.movimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad saldo.
     * 
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Define el valor de la propiedad saldo.
     * 
     */
    public void setSaldo(double value) {
        this.saldo = value;
    }

}
