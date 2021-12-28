
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * tipo cliente.
 * 
 * <p>Java class for ClienteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClienteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="CUIT" type="{http://www.zeni.com.ar/ZeniWSDL/}CuitType"/>
 *         &lt;element name="razonSocial" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="cuentas" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfCuentaType"/>
 *         &lt;element name="cuentasOF" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfCuentaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClienteType", propOrder = {
    "id",
    "cuit",
    "razonSocial",
    "cuentas",
    "cuentasOF"
})
public class ClienteType {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(name = "CUIT", required = true)
    protected String cuit;
    @XmlElement(required = true)
    protected String razonSocial;
    @XmlElement(required = true)
    protected ArrayOfCuentaType cuentas;
    @XmlElement(required = true)
    protected ArrayOfCuentaType cuentasOF;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the cuit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCUIT() {
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
    public void setCUIT(String value) {
        this.cuit = value;
    }

    /**
     * Gets the value of the razonSocial property.
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
     * Sets the value of the razonSocial property.
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
     * Gets the value of the cuentas property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCuentaType }
     *     
     */
    public ArrayOfCuentaType getCuentas() {
        return cuentas;
    }

    /**
     * Sets the value of the cuentas property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCuentaType }
     *     
     */
    public void setCuentas(ArrayOfCuentaType value) {
        this.cuentas = value;
    }

    /**
     * Gets the value of the cuentasOF property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCuentaType }
     *     
     */
    public ArrayOfCuentaType getCuentasOF() {
        return cuentasOF;
    }

    /**
     * Sets the value of the cuentasOF property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCuentaType }
     *     
     */
    public void setCuentasOF(ArrayOfCuentaType value) {
        this.cuentasOF = value;
    }

}
