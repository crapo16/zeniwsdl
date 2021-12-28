
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
 *         &lt;element name="cuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}CuentaType"/>
 *         &lt;element name="condicion" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumCompraVentaType"/>
 *         &lt;element name="estado" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumEstadoType"/>
 *         &lt;element name="sucursal" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumSucursalType"/>
 *         &lt;element name="mercados" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfMercadoType" minOccurs="0"/>
 *         &lt;element name="rangoFecha" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumTipoOperacionMatType"/>
 *         &lt;element name="productos" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfProductoMATType" minOccurs="0"/>
 *         &lt;element name="destinos" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfDestinoMATType" minOccurs="0"/>
 *         &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prima" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="posicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipoOperacion" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumTipoOperacionType"/>
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
    "cuenta",
    "condicion",
    "estado",
    "sucursal",
    "mercados",
    "rangoFecha",
    "tipo",
    "productos",
    "destinos",
    "precio",
    "prima",
    "posicion",
    "tipoOperacion"
})
@XmlRootElement(name = "obtenerOperacionesMAT")
public class ObtenerOperacionesMAT {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected CuentaType cuenta;
    @XmlElement(required = true)
    protected EnumCompraVentaType condicion;
    @XmlElement(required = true)
    protected EnumEstadoType estado;
    @XmlElement(required = true)
    protected EnumSucursalType sucursal;
    protected ArrayOfMercadoType mercados;
    protected RangoFechaType rangoFecha;
    @XmlElement(required = true)
    protected EnumTipoOperacionMatType tipo;
    protected ArrayOfProductoMATType productos;
    protected ArrayOfDestinoMATType destinos;
    protected String precio;
    protected String prima;
    protected String posicion;
    @XmlElement(required = true)
    protected EnumTipoOperacionType tipoOperacion;

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
     * Gets the value of the condicion property.
     * 
     * @return
     *     possible object is
     *     {@link EnumCompraVentaType }
     *     
     */
    public EnumCompraVentaType getCondicion() {
        return condicion;
    }

    /**
     * Sets the value of the condicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumCompraVentaType }
     *     
     */
    public void setCondicion(EnumCompraVentaType value) {
        this.condicion = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link EnumEstadoType }
     *     
     */
    public EnumEstadoType getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumEstadoType }
     *     
     */
    public void setEstado(EnumEstadoType value) {
        this.estado = value;
    }

    /**
     * Gets the value of the sucursal property.
     * 
     * @return
     *     possible object is
     *     {@link EnumSucursalType }
     *     
     */
    public EnumSucursalType getSucursal() {
        return sucursal;
    }

    /**
     * Sets the value of the sucursal property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumSucursalType }
     *     
     */
    public void setSucursal(EnumSucursalType value) {
        this.sucursal = value;
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
     * Gets the value of the rangoFecha property.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public RangoFechaType getRangoFecha() {
        return rangoFecha;
    }

    /**
     * Sets the value of the rangoFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setRangoFecha(RangoFechaType value) {
        this.rangoFecha = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoOperacionMatType }
     *     
     */
    public EnumTipoOperacionMatType getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoOperacionMatType }
     *     
     */
    public void setTipo(EnumTipoOperacionMatType value) {
        this.tipo = value;
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
     * Gets the value of the destinos property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDestinoMATType }
     *     
     */
    public ArrayOfDestinoMATType getDestinos() {
        return destinos;
    }

    /**
     * Sets the value of the destinos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDestinoMATType }
     *     
     */
    public void setDestinos(ArrayOfDestinoMATType value) {
        this.destinos = value;
    }

    /**
     * Gets the value of the precio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * Sets the value of the precio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrecio(String value) {
        this.precio = value;
    }

    /**
     * Gets the value of the prima property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrima() {
        return prima;
    }

    /**
     * Sets the value of the prima property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrima(String value) {
        this.prima = value;
    }

    /**
     * Gets the value of the posicion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosicion() {
        return posicion;
    }

    /**
     * Sets the value of the posicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosicion(String value) {
        this.posicion = value;
    }

    /**
     * Gets the value of the tipoOperacion property.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoOperacionType }
     *     
     */
    public EnumTipoOperacionType getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Sets the value of the tipoOperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoOperacionType }
     *     
     */
    public void setTipoOperacion(EnumTipoOperacionType value) {
        this.tipoOperacion = value;
    }

}
