
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerCuentaCorrienteContableType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerCuentaCorrienteContableType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comprobantes" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfCuentaCorrienteContableType"/>
 *         &lt;element name="saldo" type="{http://www.zeni.com.ar/ZeniWSDL/}SaldoType"/>
 *         &lt;element name="saldoAFecha" type="{http://www.zeni.com.ar/ZeniWSDL/}SaldoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerCuentaCorrienteContableType", propOrder = {
    "comprobantes",
    "saldo",
    "saldoAFecha"
})
public class ObtenerCuentaCorrienteContableType {

    @XmlElement(required = true)
    protected ArrayOfCuentaCorrienteContableType comprobantes;
    @XmlElement(required = true)
    protected SaldoType saldo;
    @XmlElement(required = true)
    protected SaldoType saldoAFecha;

    /**
     * Gets the value of the comprobantes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCuentaCorrienteContableType }
     *     
     */
    public ArrayOfCuentaCorrienteContableType getComprobantes() {
        return comprobantes;
    }

    /**
     * Sets the value of the comprobantes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCuentaCorrienteContableType }
     *     
     */
    public void setComprobantes(ArrayOfCuentaCorrienteContableType value) {
        this.comprobantes = value;
    }

    /**
     * Gets the value of the saldo property.
     * 
     * @return
     *     possible object is
     *     {@link SaldoType }
     *     
     */
    public SaldoType getSaldo() {
        return saldo;
    }

    /**
     * Sets the value of the saldo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SaldoType }
     *     
     */
    public void setSaldo(SaldoType value) {
        this.saldo = value;
    }

    /**
     * Gets the value of the saldoAFecha property.
     * 
     * @return
     *     possible object is
     *     {@link SaldoType }
     *     
     */
    public SaldoType getSaldoAFecha() {
        return saldoAFecha;
    }

    /**
     * Sets the value of the saldoAFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link SaldoType }
     *     
     */
    public void setSaldoAFecha(SaldoType value) {
        this.saldoAFecha = value;
    }

}
