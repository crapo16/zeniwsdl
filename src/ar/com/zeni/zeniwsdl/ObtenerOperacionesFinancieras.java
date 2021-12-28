
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
 *         &lt;element name="tipoConsulta" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumTipoConsultaType"/>
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
    "tipoOperacion",
    "tipoConsulta"
})
@XmlRootElement(name = "obtenerOperacionesFinancieras")
public class ObtenerOperacionesFinancieras {

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
    @XmlElement(required = true)
    protected EnumTipoConsultaType tipoConsulta;

    /**
     * Obtiene el valor de la propiedad auth.
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
     * Define el valor de la propiedad auth.
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
     * Obtiene el valor de la propiedad cuenta.
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
     * Define el valor de la propiedad cuenta.
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
     * Obtiene el valor de la propiedad condicion.
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
     * Define el valor de la propiedad condicion.
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
     * Obtiene el valor de la propiedad estado.
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
     * Define el valor de la propiedad estado.
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
     * Obtiene el valor de la propiedad sucursal.
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
     * Define el valor de la propiedad sucursal.
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
     * Obtiene el valor de la propiedad mercados.
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
     * Define el valor de la propiedad mercados.
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
     * Obtiene el valor de la propiedad rangoFecha.
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
     * Define el valor de la propiedad rangoFecha.
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
     * Obtiene el valor de la propiedad tipo.
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
     * Define el valor de la propiedad tipo.
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
     * Obtiene el valor de la propiedad productos.
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
     * Define el valor de la propiedad productos.
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
     * Obtiene el valor de la propiedad destinos.
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
     * Define el valor de la propiedad destinos.
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
     * Obtiene el valor de la propiedad precio.
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
     * Define el valor de la propiedad precio.
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
     * Obtiene el valor de la propiedad prima.
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
     * Define el valor de la propiedad prima.
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
     * Obtiene el valor de la propiedad posicion.
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
     * Define el valor de la propiedad posicion.
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
     * Obtiene el valor de la propiedad tipoOperacion.
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
     * Define el valor de la propiedad tipoOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoOperacionType }
     *     
     */
    public void setTipoOperacion(EnumTipoOperacionType value) {
        this.tipoOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoConsulta.
     * 
     * @return
     *     possible object is
     *     {@link EnumTipoConsultaType }
     *     
     */
    public EnumTipoConsultaType getTipoConsulta() {
        return tipoConsulta;
    }

    /**
     * Define el valor de la propiedad tipoConsulta.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumTipoConsultaType }
     *     
     */
    public void setTipoConsulta(EnumTipoConsultaType value) {
        this.tipoConsulta = value;
    }

}
