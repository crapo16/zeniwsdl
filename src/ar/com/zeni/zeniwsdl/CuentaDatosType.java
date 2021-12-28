
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Datos de la cuenta del tipo CuentaType
 * 					
 * 
 * <p>Java class for CuentaDatosType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CuentaDatosType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="moneda" type="{http://www.zeni.com.ar/ZeniWSDL/}MonedaType"/>
 *         &lt;element name="tipoCuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}CharType"/>
 *         &lt;element name="saldo" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="saldoAnterior" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="saldoUS" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="fechaCreacion" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="fechaSaldoAnterior" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CuentaDatosType", propOrder = {
    "idCuenta",
    "moneda",
    "tipoCuenta",
    "saldo",
    "saldoAnterior",
    "saldoUS",
    "fechaCreacion",
    "fechaSaldoAnterior"
})
public class CuentaDatosType {

    @XmlElement(required = true)
    protected String idCuenta;
    @XmlElement(required = true)
    protected MonedaType moneda;
    @XmlElement(required = true)
    protected String tipoCuenta;
    protected double saldo;
    protected double saldoAnterior;
    protected double saldoUS;
    @XmlElement(required = true)
    protected FechaTimeType fechaCreacion;
    @XmlElement(required = true)
    protected FechaTimeType fechaSaldoAnterior;

    /**
     * Gets the value of the idCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCuenta() {
        return idCuenta;
    }

    /**
     * Sets the value of the idCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCuenta(String value) {
        this.idCuenta = value;
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
     * Gets the value of the tipoCuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Sets the value of the tipoCuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCuenta(String value) {
        this.tipoCuenta = value;
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
     * Gets the value of the saldoAnterior property.
     * 
     */
    public double getSaldoAnterior() {
        return saldoAnterior;
    }

    /**
     * Sets the value of the saldoAnterior property.
     * 
     */
    public void setSaldoAnterior(double value) {
        this.saldoAnterior = value;
    }

    /**
     * Gets the value of the saldoUS property.
     * 
     */
    public double getSaldoUS() {
        return saldoUS;
    }

    /**
     * Sets the value of the saldoUS property.
     * 
     */
    public void setSaldoUS(double value) {
        this.saldoUS = value;
    }

    /**
     * Gets the value of the fechaCreacion property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Sets the value of the fechaCreacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaCreacion(FechaTimeType value) {
        this.fechaCreacion = value;
    }

    /**
     * Gets the value of the fechaSaldoAnterior property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaSaldoAnterior() {
        return fechaSaldoAnterior;
    }

    /**
     * Sets the value of the fechaSaldoAnterior property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaSaldoAnterior(FechaTimeType value) {
        this.fechaSaldoAnterior = value;
    }

}
