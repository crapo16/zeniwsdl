
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Canje por contrato.
 * 					
 * 
 * <p>Clase Java para CanjeByContratoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CanjeByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nroCuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vendedorCompra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contrapartida" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CanjeByContratoType", propOrder = {
    "nroCuenta",
    "vendedorCompra",
    "contrapartida"
})
public class CanjeByContratoType {

    protected String nroCuenta;
    protected String vendedorCompra;
    protected String contrapartida;

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
     * Obtiene el valor de la propiedad vendedorCompra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendedorCompra() {
        return vendedorCompra;
    }

    /**
     * Define el valor de la propiedad vendedorCompra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendedorCompra(String value) {
        this.vendedorCompra = value;
    }

    /**
     * Obtiene el valor de la propiedad contrapartida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrapartida() {
        return contrapartida;
    }

    /**
     * Define el valor de la propiedad contrapartida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrapartida(String value) {
        this.contrapartida = value;
    }

}
