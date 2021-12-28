
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
 *         &lt;element name="cuentas" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfIdCuenta"/>
 *         &lt;element name="nroZeni" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nroContratoComprador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nroContratoVendedor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incluir" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumIncluirType" minOccurs="0"/>
 *         &lt;element name="precio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ordenamiento" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumOrdenamientoContratosType" minOccurs="0"/>
 *         &lt;element name="rangoFechas" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType" minOccurs="0"/>
 *         &lt;element name="productos" type="{http://www.zeni.com.ar/ZeniWSDL/}ArrayOfProductoType" minOccurs="0"/>
 *         &lt;element name="vendedorId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType" minOccurs="0"/>
 *         &lt;element name="compradorId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType" minOccurs="0"/>
 *         &lt;element name="calidadId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType" minOccurs="0"/>
 *         &lt;element name="operatoriaId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType" minOccurs="0"/>
 *         &lt;element name="campaniaId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType" minOccurs="0"/>
 *         &lt;element name="condDePagoId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType" minOccurs="0"/>
 *         &lt;element name="compraVende" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumCompradorVendedorType" minOccurs="0"/>
 *         &lt;element name="cartaDePorte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rangoFechasEntrega" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType" minOccurs="0"/>
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
    "cuentas",
    "nroZeni",
    "nroContratoComprador",
    "nroContratoVendedor",
    "incluir",
    "precio",
    "ordenamiento",
    "rangoFechas",
    "productos",
    "vendedorId",
    "compradorId",
    "calidadId",
    "operatoriaId",
    "campaniaId",
    "condDePagoId",
    "compraVende",
    "cartaDePorte",
    "rangoFechasEntrega"
})
@XmlRootElement(name = "obtenerContratos")
public class ObtenerContratos {

    @XmlElement(required = true)
    protected AuthType auth;
    @XmlElement(required = true)
    protected ArrayOfIdCuenta cuentas;
    protected String nroZeni;
    protected String nroContratoComprador;
    protected String nroContratoVendedor;
    protected EnumIncluirType incluir;
    protected String precio;
    protected EnumOrdenamientoContratosType ordenamiento;
    protected RangoFechaType rangoFechas;
    protected ArrayOfProductoType productos;
    protected String vendedorId;
    protected String compradorId;
    protected String calidadId;
    protected String operatoriaId;
    protected String campaniaId;
    protected String condDePagoId;
    protected EnumCompradorVendedorType compraVende;
    protected String cartaDePorte;
    protected RangoFechaType rangoFechasEntrega;

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
     * Obtiene el valor de la propiedad cuentas.
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
     * Define el valor de la propiedad cuentas.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfIdCuenta }
     *     
     */
    public void setCuentas(ArrayOfIdCuenta value) {
        this.cuentas = value;
    }

    /**
     * Obtiene el valor de la propiedad nroZeni.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroZeni() {
        return nroZeni;
    }

    /**
     * Define el valor de la propiedad nroZeni.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroZeni(String value) {
        this.nroZeni = value;
    }

    /**
     * Obtiene el valor de la propiedad nroContratoComprador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroContratoComprador() {
        return nroContratoComprador;
    }

    /**
     * Define el valor de la propiedad nroContratoComprador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroContratoComprador(String value) {
        this.nroContratoComprador = value;
    }

    /**
     * Obtiene el valor de la propiedad nroContratoVendedor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroContratoVendedor() {
        return nroContratoVendedor;
    }

    /**
     * Define el valor de la propiedad nroContratoVendedor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroContratoVendedor(String value) {
        this.nroContratoVendedor = value;
    }

    /**
     * Obtiene el valor de la propiedad incluir.
     * 
     * @return
     *     possible object is
     *     {@link EnumIncluirType }
     *     
     */
    public EnumIncluirType getIncluir() {
        return incluir;
    }

    /**
     * Define el valor de la propiedad incluir.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumIncluirType }
     *     
     */
    public void setIncluir(EnumIncluirType value) {
        this.incluir = value;
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
     * Obtiene el valor de la propiedad ordenamiento.
     * 
     * @return
     *     possible object is
     *     {@link EnumOrdenamientoContratosType }
     *     
     */
    public EnumOrdenamientoContratosType getOrdenamiento() {
        return ordenamiento;
    }

    /**
     * Define el valor de la propiedad ordenamiento.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumOrdenamientoContratosType }
     *     
     */
    public void setOrdenamiento(EnumOrdenamientoContratosType value) {
        this.ordenamiento = value;
    }

    /**
     * Obtiene el valor de la propiedad rangoFechas.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public RangoFechaType getRangoFechas() {
        return rangoFechas;
    }

    /**
     * Define el valor de la propiedad rangoFechas.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setRangoFechas(RangoFechaType value) {
        this.rangoFechas = value;
    }

    /**
     * Obtiene el valor de la propiedad productos.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfProductoType }
     *     
     */
    public ArrayOfProductoType getProductos() {
        return productos;
    }

    /**
     * Define el valor de la propiedad productos.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfProductoType }
     *     
     */
    public void setProductos(ArrayOfProductoType value) {
        this.productos = value;
    }

    /**
     * Obtiene el valor de la propiedad vendedorId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendedorId() {
        return vendedorId;
    }

    /**
     * Define el valor de la propiedad vendedorId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendedorId(String value) {
        this.vendedorId = value;
    }

    /**
     * Obtiene el valor de la propiedad compradorId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompradorId() {
        return compradorId;
    }

    /**
     * Define el valor de la propiedad compradorId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompradorId(String value) {
        this.compradorId = value;
    }

    /**
     * Obtiene el valor de la propiedad calidadId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCalidadId() {
        return calidadId;
    }

    /**
     * Define el valor de la propiedad calidadId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCalidadId(String value) {
        this.calidadId = value;
    }

    /**
     * Obtiene el valor de la propiedad operatoriaId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatoriaId() {
        return operatoriaId;
    }

    /**
     * Define el valor de la propiedad operatoriaId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatoriaId(String value) {
        this.operatoriaId = value;
    }

    /**
     * Obtiene el valor de la propiedad campaniaId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCampaniaId() {
        return campaniaId;
    }

    /**
     * Define el valor de la propiedad campaniaId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCampaniaId(String value) {
        this.campaniaId = value;
    }

    /**
     * Obtiene el valor de la propiedad condDePagoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondDePagoId() {
        return condDePagoId;
    }

    /**
     * Define el valor de la propiedad condDePagoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondDePagoId(String value) {
        this.condDePagoId = value;
    }

    /**
     * Obtiene el valor de la propiedad compraVende.
     * 
     * @return
     *     possible object is
     *     {@link EnumCompradorVendedorType }
     *     
     */
    public EnumCompradorVendedorType getCompraVende() {
        return compraVende;
    }

    /**
     * Define el valor de la propiedad compraVende.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumCompradorVendedorType }
     *     
     */
    public void setCompraVende(EnumCompradorVendedorType value) {
        this.compraVende = value;
    }

    /**
     * Obtiene el valor de la propiedad cartaDePorte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCartaDePorte() {
        return cartaDePorte;
    }

    /**
     * Define el valor de la propiedad cartaDePorte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCartaDePorte(String value) {
        this.cartaDePorte = value;
    }

    /**
     * Obtiene el valor de la propiedad rangoFechasEntrega.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public RangoFechaType getRangoFechasEntrega() {
        return rangoFechasEntrega;
    }

    /**
     * Define el valor de la propiedad rangoFechasEntrega.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setRangoFechasEntrega(RangoFechaType value) {
        this.rangoFechasEntrega = value;
    }

}
