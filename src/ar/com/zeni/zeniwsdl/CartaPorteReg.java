
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Data Carta Porte.
 * 					
 * 
 * <p>Clase Java para CartaPorteReg complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CartaPorteReg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fechaDescarga" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType" minOccurs="0"/>
 *         &lt;element name="nroCartaPorte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroContrato" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cosecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ctaVend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="razonSocialVend" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ctaComp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="razonSocialComp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contratoComprador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="producto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cantidadAplicar" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="nroRecibo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="procedencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estadoEntrega" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaAplicacion" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType" minOccurs="0"/>
 *         &lt;element name="usuarioActualizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaBaja" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CartaPorteReg", propOrder = {
    "fechaDescarga",
    "nroCartaPorte",
    "numeroContrato",
    "cosecha",
    "ctaVend",
    "razonSocialVend",
    "ctaComp",
    "razonSocialComp",
    "contratoComprador",
    "producto",
    "cantidadAplicar",
    "cantidad",
    "nroRecibo",
    "estado",
    "procedencia",
    "destino",
    "estadoEntrega",
    "fechaAplicacion",
    "usuarioActualizacion",
    "fechaBaja"
})
public class CartaPorteReg {

    protected FechaTimeType fechaDescarga;
    protected String nroCartaPorte;
    protected String numeroContrato;
    protected String cosecha;
    protected String ctaVend;
    protected String razonSocialVend;
    protected String ctaComp;
    protected String razonSocialComp;
    protected String contratoComprador;
    protected String producto;
    protected Float cantidadAplicar;
    protected Float cantidad;
    protected String nroRecibo;
    protected String estado;
    protected String procedencia;
    protected String destino;
    protected String estadoEntrega;
    protected FechaTimeType fechaAplicacion;
    protected String usuarioActualizacion;
    protected FechaTimeType fechaBaja;

    /**
     * Obtiene el valor de la propiedad fechaDescarga.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaDescarga() {
        return fechaDescarga;
    }

    /**
     * Define el valor de la propiedad fechaDescarga.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaDescarga(FechaTimeType value) {
        this.fechaDescarga = value;
    }

    /**
     * Obtiene el valor de la propiedad nroCartaPorte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroCartaPorte() {
        return nroCartaPorte;
    }

    /**
     * Define el valor de la propiedad nroCartaPorte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroCartaPorte(String value) {
        this.nroCartaPorte = value;
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
     * Obtiene el valor de la propiedad ctaVend.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCtaVend() {
        return ctaVend;
    }

    /**
     * Define el valor de la propiedad ctaVend.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCtaVend(String value) {
        this.ctaVend = value;
    }

    /**
     * Obtiene el valor de la propiedad razonSocialVend.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocialVend() {
        return razonSocialVend;
    }

    /**
     * Define el valor de la propiedad razonSocialVend.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocialVend(String value) {
        this.razonSocialVend = value;
    }

    /**
     * Obtiene el valor de la propiedad ctaComp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCtaComp() {
        return ctaComp;
    }

    /**
     * Define el valor de la propiedad ctaComp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCtaComp(String value) {
        this.ctaComp = value;
    }

    /**
     * Obtiene el valor de la propiedad razonSocialComp.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocialComp() {
        return razonSocialComp;
    }

    /**
     * Define el valor de la propiedad razonSocialComp.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocialComp(String value) {
        this.razonSocialComp = value;
    }

    /**
     * Obtiene el valor de la propiedad contratoComprador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratoComprador() {
        return contratoComprador;
    }

    /**
     * Define el valor de la propiedad contratoComprador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratoComprador(String value) {
        this.contratoComprador = value;
    }

    /**
     * Obtiene el valor de la propiedad producto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducto() {
        return producto;
    }

    /**
     * Define el valor de la propiedad producto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducto(String value) {
        this.producto = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadAplicar.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getCantidadAplicar() {
        return cantidadAplicar;
    }

    /**
     * Define el valor de la propiedad cantidadAplicar.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setCantidadAplicar(Float value) {
        this.cantidadAplicar = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidad.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setCantidad(Float value) {
        this.cantidad = value;
    }

    /**
     * Obtiene el valor de la propiedad nroRecibo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroRecibo() {
        return nroRecibo;
    }

    /**
     * Define el valor de la propiedad nroRecibo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroRecibo(String value) {
        this.nroRecibo = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
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
     * Obtiene el valor de la propiedad estadoEntrega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    /**
     * Define el valor de la propiedad estadoEntrega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoEntrega(String value) {
        this.estadoEntrega = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAplicacion.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaAplicacion() {
        return fechaAplicacion;
    }

    /**
     * Define el valor de la propiedad fechaAplicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaAplicacion(FechaTimeType value) {
        this.fechaAplicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad usuarioActualizacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuarioActualizacion() {
        return usuarioActualizacion;
    }

    /**
     * Define el valor de la propiedad usuarioActualizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuarioActualizacion(String value) {
        this.usuarioActualizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaBaja.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Define el valor de la propiedad fechaBaja.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaBaja(FechaTimeType value) {
        this.fechaBaja = value;
    }

}
