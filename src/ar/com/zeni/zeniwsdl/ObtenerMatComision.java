
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auth" type="{http://www.zeni.com.ar/ZeniWSDL/}AuthType"/>
 *         &lt;element name="productos" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfProductoMATType"/>
 *         &lt;element name="mercados" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfMercadoType"/>
 *         &lt;element name="tipodeoperacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="condicion" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumCompradorVendedorType"/>
 *         &lt;element name="tipodecomisiones" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfTipoComisionType" minOccurs="0"/>
 *         &lt;element name="cuentas" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfIdCuenta"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "auth",
    "productos",
    "mercados",
    "tipodeoperacion",
    "condicion",
    "tipodecomisiones",
    "cuentas"
})
@XmlRootElement(name = "obtenerMatComision")
public class ObtenerMatComision {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected ArrayOfProductoMATType productos;
    @XmlElement(required = true)
    protected ArrayOfMercadoType mercados;
    protected String tipodeoperacion;
    @XmlElement(required = true)
    protected EnumCompradorVendedorType condicion;
    protected ArrayOfTipoComisionType tipodecomisiones;
    @XmlElement(required = true)
    protected ArrayOfIdCuenta cuentas;

    /**
     * Gets the value of the auth property.
     * 
     * @return
     *     possible object is
     *     {@link AuthType }
     *     
     */
    public AuthType getAuth() {
        return auth;
    }

    /**
     * Sets the value of the auth property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthType }
     *     
     */
    public void setAuth(AuthType value) {
        this.auth = value;
    }

    /**
     * Gets the value of the productos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfProductoMATType }
     *     
     */
    public ArrayOfProductoMATType getProductos() {
        return productos;
    }

    /**
     * Sets the value of the productos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfProductoMATType }
     *     
     */
    public void setProductos(ArrayOfProductoMATType value) {
        this.productos = value;
    }

    /**
     * Gets the value of the mercados property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMercadoType }
     *     
     */
    public ArrayOfMercadoType getMercados() {
        return mercados;
    }

    /**
     * Sets the value of the mercados property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMercadoType }
     *     
     */
    public void setMercados(ArrayOfMercadoType value) {
        this.mercados = value;
    }

    /**
     * Gets the value of the tipodeoperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipodeoperacion() {
        return tipodeoperacion;
    }

    /**
     * Sets the value of the tipodeoperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipodeoperacion(String value) {
        this.tipodeoperacion = value;
    }

    /**
     * Gets the value of the condicion property.
     * 
     * @return
     *     possible object is
     *     {@link EnumCompradorVendedorType }
     *     
     */
    public EnumCompradorVendedorType getCondicion() {
        return condicion;
    }

    /**
     * Sets the value of the condicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumCompradorVendedorType }
     *     
     */
    public void setCondicion(EnumCompradorVendedorType value) {
        this.condicion = value;
    }

    /**
     * Gets the value of the tipodecomisiones property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTipoComisionType }
     *     
     */
    public ArrayOfTipoComisionType getTipodecomisiones() {
        return tipodecomisiones;
    }

    /**
     * Sets the value of the tipodecomisiones property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTipoComisionType }
     *     
     */
    public void setTipodecomisiones(ArrayOfTipoComisionType value) {
        this.tipodecomisiones = value;
    }

    /**
     * Gets the value of the cuentas property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfIdCuenta }
     *     
     */
    public ArrayOfIdCuenta getCuentas() {
        return cuentas;
    }

    /**
     * Sets the value of the cuentas property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfIdCuenta }
     *     
     */
    public void setCuentas(ArrayOfIdCuenta value) {
        this.cuentas = value;
    }

}
