
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de contrato.
 * 					
 * 
 * <p>Clase Java para ContratoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contratId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="contratoId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id_cuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cosecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroZeni" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroContrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroComprobante" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contraparte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vendRazonSocial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="compRazonSocial" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vendCuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="vendActividad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="saldo" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="precio" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="kgFacturado" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="productoDescripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productoCodigo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productoId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="cp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="procedencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="entRango" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType"/>
 *         &lt;element name="periodo" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType"/>
 *         &lt;element name="fijacionRango" type="{http://www.zeni.com.ar/ZeniWSDL/}RangoFechaType"/>
 *         &lt;element name="FA" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="catCod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="catDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="opCod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="opDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pac" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="entr" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="cumplido" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *         &lt;element name="tte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pdte" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="cpagoCod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cpagoDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cpagoProciento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cesionderecho" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vto" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="vtoDias" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fact" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="fij" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="afijar" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="moneda" type="{http://www.zeni.com.ar/ZeniWSDL/}MonedaType"/>
 *         &lt;element name="vencimiento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tipoDias" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="condicionAFijar" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ajustes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comentarios" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nroCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContratoType", propOrder = {
    "contratId",
    "contratoId",
    "cuenta",
    "idCuenta",
    "cosecha",
    "numeroZeni",
    "numeroContrato",
    "numeroComprobante",
    "contraparte",
    "vendRazonSocial",
    "compRazonSocial",
    "vendCuenta",
    "vendActividad",
    "cantidad",
    "saldo",
    "precio",
    "kgFacturado",
    "productoDescripcion",
    "productoCodigo",
    "productoId",
    "cp",
    "procedencia",
    "destino",
    "entRango",
    "periodo",
    "fijacionRango",
    "fa",
    "catCod",
    "catDesc",
    "opCod",
    "opDesc",
    "pac",
    "entr",
    "cumplido",
    "tte",
    "pdte",
    "cpagoCod",
    "cpagoDesc",
    "cpagoProciento",
    "cesionderecho",
    "vto",
    "vtoDias",
    "fact",
    "fij",
    "afijar",
    "moneda",
    "vencimiento",
    "tipoDias",
    "condicionAFijar",
    "ajustes",
    "comentarios",
    "nroCuenta"
})
public class ContratoType {

    @XmlElement(required = true)
    protected String contratId;
    @XmlElement(required = true)
    protected String contratoId;
    protected String cuenta;
    @XmlElement(name = "id_cuenta")
    protected String idCuenta;
    protected String cosecha;
    @XmlElement(required = true)
    protected String numeroZeni;
    @XmlElement(required = true)
    protected String numeroContrato;
    @XmlElement(required = true)
    protected String numeroComprobante;
    @XmlElement(required = true)
    protected String contraparte;
    @XmlElement(required = true)
    protected String vendRazonSocial;
    @XmlElement(required = true)
    protected String compRazonSocial;
    @XmlElement(required = true)
    protected String vendCuenta;
    @XmlElement(required = true)
    protected String vendActividad;
    protected float cantidad;
    protected double saldo;
    protected double precio;
    protected float kgFacturado;
    @XmlElement(required = true)
    protected String productoDescripcion;
    @XmlElement(required = true)
    protected String productoCodigo;
    @XmlElement(required = true)
    protected String productoId;
    @XmlElement(required = true)
    protected String cp;
    @XmlElement(required = true)
    protected String procedencia;
    @XmlElement(required = true)
    protected String destino;
    @XmlElement(required = true)
    protected RangoFechaType entRango;
    @XmlElement(required = true)
    protected RangoFechaType periodo;
    @XmlElement(required = true)
    protected RangoFechaType fijacionRango;
    @XmlElement(name = "FA", required = true)
    protected FechaTimeType fa;
    @XmlElement(required = true)
    protected String catCod;
    @XmlElement(required = true)
    protected String catDesc;
    @XmlElement(required = true)
    protected String opCod;
    @XmlElement(required = true)
    protected String opDesc;
    protected float pac;
    protected float entr;
    @XmlElement(required = true)
    protected SINOType cumplido;
    @XmlElement(required = true)
    protected String tte;
    protected float pdte;
    @XmlElement(required = true)
    protected String cpagoCod;
    @XmlElement(required = true)
    protected String cpagoDesc;
    @XmlElement(required = true)
    protected String cpagoProciento;
    @XmlElement(required = true)
    protected String cesionderecho;
    @XmlElement(required = true)
    protected FechaTimeType vto;
    protected int vtoDias;
    protected float fact;
    protected float fij;
    protected float afijar;
    @XmlElement(required = true)
    protected MonedaType moneda;
    protected int vencimiento;
    @XmlElement(required = true)
    protected String tipoDias;
    @XmlElement(required = true)
    protected String condicionAFijar;
    @XmlElement(required = true)
    protected String ajustes;
    @XmlElement(required = true)
    protected String comentarios;
    @XmlElement(required = true)
    protected String nroCuenta;

    /**
     * Obtiene el valor de la propiedad contratId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratId() {
        return contratId;
    }

    /**
     * Define el valor de la propiedad contratId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratId(String value) {
        this.contratId = value;
    }

    /**
     * Obtiene el valor de la propiedad contratoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratoId() {
        return contratoId;
    }

    /**
     * Define el valor de la propiedad contratoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratoId(String value) {
        this.contratoId = value;
    }

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
     * Obtiene el valor de la propiedad idCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCuenta() {
        return idCuenta;
    }

    /**
     * Define el valor de la propiedad idCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCuenta(String value) {
        this.idCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad cosecha.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCosecha() {
        return cosecha;
    }

    /**
     * Define el valor de la propiedad cosecha.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCosecha(String value) {
        this.cosecha = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroZeni.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroZeni() {
        return numeroZeni;
    }

    /**
     * Define el valor de la propiedad numeroZeni.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroZeni(String value) {
        this.numeroZeni = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroContrato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroContrato() {
        return numeroContrato;
    }

    /**
     * Define el valor de la propiedad numeroContrato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroContrato(String value) {
        this.numeroContrato = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroComprobante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * Define el valor de la propiedad numeroComprobante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroComprobante(String value) {
        this.numeroComprobante = value;
    }

    /**
     * Obtiene el valor de la propiedad contraparte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContraparte() {
        return contraparte;
    }

    /**
     * Define el valor de la propiedad contraparte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContraparte(String value) {
        this.contraparte = value;
    }

    /**
     * Obtiene el valor de la propiedad vendRazonSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendRazonSocial() {
        return vendRazonSocial;
    }

    /**
     * Define el valor de la propiedad vendRazonSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendRazonSocial(String value) {
        this.vendRazonSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad compRazonSocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompRazonSocial() {
        return compRazonSocial;
    }

    /**
     * Define el valor de la propiedad compRazonSocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompRazonSocial(String value) {
        this.compRazonSocial = value;
    }

    /**
     * Obtiene el valor de la propiedad vendCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendCuenta() {
        return vendCuenta;
    }

    /**
     * Define el valor de la propiedad vendCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendCuenta(String value) {
        this.vendCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad vendActividad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendActividad() {
        return vendActividad;
    }

    /**
     * Define el valor de la propiedad vendActividad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendActividad(String value) {
        this.vendActividad = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidad.
     * 
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     */
    public void setCantidad(float value) {
        this.cantidad = value;
    }

    /**
     * Obtiene el valor de la propiedad saldo.
     * 
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Define el valor de la propiedad saldo.
     * 
     */
    public void setSaldo(double value) {
        this.saldo = value;
    }

    /**
     * Obtiene el valor de la propiedad precio.
     * 
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Define el valor de la propiedad precio.
     * 
     */
    public void setPrecio(double value) {
        this.precio = value;
    }

    /**
     * Obtiene el valor de la propiedad kgFacturado.
     * 
     */
    public float getKgFacturado() {
        return kgFacturado;
    }

    /**
     * Define el valor de la propiedad kgFacturado.
     * 
     */
    public void setKgFacturado(float value) {
        this.kgFacturado = value;
    }

    /**
     * Obtiene el valor de la propiedad productoDescripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductoDescripcion() {
        return productoDescripcion;
    }

    /**
     * Define el valor de la propiedad productoDescripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductoDescripcion(String value) {
        this.productoDescripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad productoCodigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductoCodigo() {
        return productoCodigo;
    }

    /**
     * Define el valor de la propiedad productoCodigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductoCodigo(String value) {
        this.productoCodigo = value;
    }

    /**
     * Obtiene el valor de la propiedad productoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductoId() {
        return productoId;
    }

    /**
     * Define el valor de la propiedad productoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductoId(String value) {
        this.productoId = value;
    }

    /**
     * Obtiene el valor de la propiedad cp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCp() {
        return cp;
    }

    /**
     * Define el valor de la propiedad cp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCp(String value) {
        this.cp = value;
    }

    /**
     * Obtiene el valor de la propiedad procedencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcedencia() {
        return procedencia;
    }

    /**
     * Define el valor de la propiedad procedencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcedencia(String value) {
        this.procedencia = value;
    }

    /**
     * Obtiene el valor de la propiedad destino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Define el valor de la propiedad destino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestino(String value) {
        this.destino = value;
    }

    /**
     * Obtiene el valor de la propiedad entRango.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public RangoFechaType getEntRango() {
        return entRango;
    }

    /**
     * Define el valor de la propiedad entRango.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setEntRango(RangoFechaType value) {
        this.entRango = value;
    }

    /**
     * Obtiene el valor de la propiedad periodo.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public RangoFechaType getPeriodo() {
        return periodo;
    }

    /**
     * Define el valor de la propiedad periodo.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setPeriodo(RangoFechaType value) {
        this.periodo = value;
    }

    /**
     * Obtiene el valor de la propiedad fijacionRango.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public RangoFechaType getFijacionRango() {
        return fijacionRango;
    }

    /**
     * Define el valor de la propiedad fijacionRango.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setFijacionRango(RangoFechaType value) {
        this.fijacionRango = value;
    }

    /**
     * Obtiene el valor de la propiedad fa.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFA() {
        return fa;
    }

    /**
     * Define el valor de la propiedad fa.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFA(FechaTimeType value) {
        this.fa = value;
    }

    /**
     * Obtiene el valor de la propiedad catCod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatCod() {
        return catCod;
    }

    /**
     * Define el valor de la propiedad catCod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatCod(String value) {
        this.catCod = value;
    }

    /**
     * Obtiene el valor de la propiedad catDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatDesc() {
        return catDesc;
    }

    /**
     * Define el valor de la propiedad catDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatDesc(String value) {
        this.catDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad opCod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpCod() {
        return opCod;
    }

    /**
     * Define el valor de la propiedad opCod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpCod(String value) {
        this.opCod = value;
    }

    /**
     * Obtiene el valor de la propiedad opDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpDesc() {
        return opDesc;
    }

    /**
     * Define el valor de la propiedad opDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpDesc(String value) {
        this.opDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad pac.
     * 
     */
    public float getPac() {
        return pac;
    }

    /**
     * Define el valor de la propiedad pac.
     * 
     */
    public void setPac(float value) {
        this.pac = value;
    }

    /**
     * Obtiene el valor de la propiedad entr.
     * 
     */
    public float getEntr() {
        return entr;
    }

    /**
     * Define el valor de la propiedad entr.
     * 
     */
    public void setEntr(float value) {
        this.entr = value;
    }

    /**
     * Obtiene el valor de la propiedad cumplido.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getCumplido() {
        return cumplido;
    }

    /**
     * Define el valor de la propiedad cumplido.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setCumplido(SINOType value) {
        this.cumplido = value;
    }

    /**
     * Obtiene el valor de la propiedad tte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTte() {
        return tte;
    }

    /**
     * Define el valor de la propiedad tte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTte(String value) {
        this.tte = value;
    }

    /**
     * Obtiene el valor de la propiedad pdte.
     * 
     */
    public float getPdte() {
        return pdte;
    }

    /**
     * Define el valor de la propiedad pdte.
     * 
     */
    public void setPdte(float value) {
        this.pdte = value;
    }

    /**
     * Obtiene el valor de la propiedad cpagoCod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpagoCod() {
        return cpagoCod;
    }

    /**
     * Define el valor de la propiedad cpagoCod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpagoCod(String value) {
        this.cpagoCod = value;
    }

    /**
     * Obtiene el valor de la propiedad cpagoDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpagoDesc() {
        return cpagoDesc;
    }

    /**
     * Define el valor de la propiedad cpagoDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpagoDesc(String value) {
        this.cpagoDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad cpagoProciento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpagoProciento() {
        return cpagoProciento;
    }

    /**
     * Define el valor de la propiedad cpagoProciento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpagoProciento(String value) {
        this.cpagoProciento = value;
    }

    /**
     * Obtiene el valor de la propiedad cesionderecho.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCesionderecho() {
        return cesionderecho;
    }

    /**
     * Define el valor de la propiedad cesionderecho.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCesionderecho(String value) {
        this.cesionderecho = value;
    }

    /**
     * Obtiene el valor de la propiedad vto.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getVto() {
        return vto;
    }

    /**
     * Define el valor de la propiedad vto.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setVto(FechaTimeType value) {
        this.vto = value;
    }

    /**
     * Obtiene el valor de la propiedad vtoDias.
     * 
     */
    public int getVtoDias() {
        return vtoDias;
    }

    /**
     * Define el valor de la propiedad vtoDias.
     * 
     */
    public void setVtoDias(int value) {
        this.vtoDias = value;
    }

    /**
     * Obtiene el valor de la propiedad fact.
     * 
     */
    public float getFact() {
        return fact;
    }

    /**
     * Define el valor de la propiedad fact.
     * 
     */
    public void setFact(float value) {
        this.fact = value;
    }

    /**
     * Obtiene el valor de la propiedad fij.
     * 
     */
    public float getFij() {
        return fij;
    }

    /**
     * Define el valor de la propiedad fij.
     * 
     */
    public void setFij(float value) {
        this.fij = value;
    }

    /**
     * Obtiene el valor de la propiedad afijar.
     * 
     */
    public float getAfijar() {
        return afijar;
    }

    /**
     * Define el valor de la propiedad afijar.
     * 
     */
    public void setAfijar(float value) {
        this.afijar = value;
    }

    /**
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link MonedaType }
     *     
     */
    public MonedaType getMoneda() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link MonedaType }
     *     
     */
    public void setMoneda(MonedaType value) {
        this.moneda = value;
    }

    /**
     * Obtiene el valor de la propiedad vencimiento.
     * 
     */
    public int getVencimiento() {
        return vencimiento;
    }

    /**
     * Define el valor de la propiedad vencimiento.
     * 
     */
    public void setVencimiento(int value) {
        this.vencimiento = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDias.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDias() {
        return tipoDias;
    }

    /**
     * Define el valor de la propiedad tipoDias.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDias(String value) {
        this.tipoDias = value;
    }

    /**
     * Obtiene el valor de la propiedad condicionAFijar.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondicionAFijar() {
        return condicionAFijar;
    }

    /**
     * Define el valor de la propiedad condicionAFijar.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondicionAFijar(String value) {
        this.condicionAFijar = value;
    }

    /**
     * Obtiene el valor de la propiedad ajustes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAjustes() {
        return ajustes;
    }

    /**
     * Define el valor de la propiedad ajustes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAjustes(String value) {
        this.ajustes = value;
    }

    /**
     * Obtiene el valor de la propiedad comentarios.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * Define el valor de la propiedad comentarios.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentarios(String value) {
        this.comentarios = value;
    }

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

}
