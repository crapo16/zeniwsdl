
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo utilizado par representar un row de un
 * 						resumen de cuenta corriente.
 * 					
 * 
 * <p>Java class for ResumenCuentaCorrienteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResumenCuentaCorrienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}CuentaType"/>
 *         &lt;element name="saldo" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="saldouss" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="saldoAnterior" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResumenCuentaCorrienteType", propOrder = {
    "cuenta",
    "saldo",
    "saldouss",
    "saldoAnterior"
})
public class ResumenCuentaCorrienteType {

    @XmlElement(required = true)
    protected CuentaType cuenta;
    protected double saldo;
    protected double saldouss;
    protected double saldoAnterior;

    /**
     * Gets the value of the cuenta property.
     * 
     * @return
     *     possible object is
     *     {@link CuentaType }
     *     
     */
    public CuentaType getCuenta() {
        return cuenta;
    }

    /**
     * Sets the value of the cuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link CuentaType }
     *     
     */
    public void setCuenta(CuentaType value) {
        this.cuenta = value;
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
     * Gets the value of the saldouss property.
     * 
     */
    public double getSaldouss() {
        return saldouss;
    }

    /**
     * Sets the value of the saldouss property.
     * 
     */
    public void setSaldouss(double value) {
        this.saldouss = value;
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

}
