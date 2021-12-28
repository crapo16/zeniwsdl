
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 						Tipo de cuenta.
 * 					
 * 
 * <p>Clase Java para CuentaType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CuentaType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.zeni.com.ar/ZeniWSDL/}IdDescripcionType">
 *       &lt;sequence>
 *         &lt;element name="numeroDeCuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}NumeroDeCuentaType"/>
 *         &lt;element name="denominacionCuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="cuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CuentaType", propOrder = {
    "numeroDeCuenta",
    "denominacionCuenta",
    "cuit"
})
public class CuentaType
    extends IdDescripcionType
{

    @XmlElement(required = true)
    protected String numeroDeCuenta;
    @XmlElement(required = true)
    protected String denominacionCuenta;
    protected String cuit;

    /**
     * Obtiene el valor de la propiedad numeroDeCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    /**
     * Define el valor de la propiedad numeroDeCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDeCuenta(String value) {
        this.numeroDeCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad denominacionCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominacionCuenta() {
        return denominacionCuenta;
    }

    /**
     * Define el valor de la propiedad denominacionCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominacionCuenta(String value) {
        this.denominacionCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad cuit.
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
     * Define el valor de la propiedad cuit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuit(String value) {
        this.cuit = value;
    }

}
