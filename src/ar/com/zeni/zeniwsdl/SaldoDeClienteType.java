
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SaldoDeClienteType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SaldoDeClienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="razonSocial" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="deudorPesos" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="acreedorPesos" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="deudorDolares" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="acreedorDolares" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="sucursal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="operador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SaldoDeClienteType", propOrder = {
    "cuenta",
    "razonSocial",
    "deudorPesos",
    "acreedorPesos",
    "deudorDolares",
    "acreedorDolares",
    "sucursal",
    "operador"
})
public class SaldoDeClienteType {

    @XmlElement(required = true)
    protected String cuenta;
    @XmlElement(required = true)
    protected String razonSocial;
    protected double deudorPesos;
    protected double acreedorPesos;
    protected double deudorDolares;
    protected double acreedorDolares;
    @XmlElement(required = true)
    protected String sucursal;
    @XmlElement(required = true)
    protected String operador;

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
     * Obtiene el valor de la propiedad razonSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Define el valor de la propiedad razonSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad deudorPesos.
     * 
     */
    public double getDeudorPesos() {
        return deudorPesos;
    }

    /**
     * Define el valor de la propiedad deudorPesos.
     * 
     */
    public void setDeudorPesos(double value) {
        this.deudorPesos = value;
    }

    /**
     * Obtiene el valor de la propiedad acreedorPesos.
     * 
     */
    public double getAcreedorPesos() {
        return acreedorPesos;
    }

    /**
     * Define el valor de la propiedad acreedorPesos.
     * 
     */
    public void setAcreedorPesos(double value) {
        this.acreedorPesos = value;
    }

    /**
     * Obtiene el valor de la propiedad deudorDolares.
     * 
     */
    public double getDeudorDolares() {
        return deudorDolares;
    }

    /**
     * Define el valor de la propiedad deudorDolares.
     * 
     */
    public void setDeudorDolares(double value) {
        this.deudorDolares = value;
    }

    /**
     * Obtiene el valor de la propiedad acreedorDolares.
     * 
     */
    public double getAcreedorDolares() {
        return acreedorDolares;
    }

    /**
     * Define el valor de la propiedad acreedorDolares.
     * 
     */
    public void setAcreedorDolares(double value) {
        this.acreedorDolares = value;
    }

    /**
     * Obtiene el valor de la propiedad sucursal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSucursal() {
        return sucursal;
    }

    /**
     * Define el valor de la propiedad sucursal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSucursal(String value) {
        this.sucursal = value;
    }

    /**
     * Obtiene el valor de la propiedad operador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperador() {
        return operador;
    }

    /**
     * Define el valor de la propiedad operador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperador(String value) {
        this.operador = value;
    }

}
