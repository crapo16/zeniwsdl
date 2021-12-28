
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * tipo de cupo
 * 
 * <p>Clase Java para CupoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CupoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="numeroContrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comprador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vendedor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="producto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroCupo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destinatario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lugarDescarga" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="otorgados" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destinatariocuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corredorcuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intermediariocuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cargadorcuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remitentecomercialcuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corredorvendcuit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinatariorazonsocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corredorrazonsocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intermediariorazonsocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cargadorrazonsocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remitentecomercialrazonsocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="corredorvendrazonsocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direcciondestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localidaddestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provinciadestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cupoid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuitdestino" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CupoType", propOrder = {
    "fecha",
    "numeroContrato",
    "comprador",
    "vendedor",
    "producto",
    "numeroCupo",
    "destino",
    "destinatario",
    "lugarDescarga",
    "otorgados",
    "destinatariocuit",
    "corredorcuit",
    "intermediariocuit",
    "cargadorcuit",
    "remitentecomercialcuit",
    "corredorvendcuit",
    "destinatariorazonsocial",
    "corredorrazonsocial",
    "intermediariorazonsocial",
    "cargadorrazonsocial",
    "remitentecomercialrazonsocial",
    "corredorvendrazonsocial",
    "direcciondestino",
    "localidaddestino",
    "provinciadestino",
    "cupoid",
    "cuitdestino"
})
public class CupoType {

    @XmlElement(required = true)
    protected FechaTimeType fecha;
    @XmlElement(required = true)
    protected String numeroContrato;
    @XmlElement(required = true)
    protected String comprador;
    @XmlElement(required = true)
    protected String vendedor;
    @XmlElement(required = true)
    protected String producto;
    @XmlElement(required = true)
    protected String numeroCupo;
    @XmlElement(required = true)
    protected String destino;
    @XmlElement(required = true)
    protected String destinatario;
    @XmlElement(required = true)
    protected String lugarDescarga;
    @XmlElement(required = true)
    protected String otorgados;
    protected String destinatariocuit;
    protected String corredorcuit;
    protected String intermediariocuit;
    protected String cargadorcuit;
    protected String remitentecomercialcuit;
    protected String corredorvendcuit;
    protected String destinatariorazonsocial;
    protected String corredorrazonsocial;
    protected String intermediariorazonsocial;
    protected String cargadorrazonsocial;
    protected String remitentecomercialrazonsocial;
    protected String corredorvendrazonsocial;
    protected String direcciondestino;
    protected String localidaddestino;
    protected String provinciadestino;
    protected String cupoid;
    protected String cuitdestino;

    /**
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFecha(FechaTimeType value) {
        this.fecha = value;
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
     * Obtiene el valor de la propiedad comprador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComprador() {
        return comprador;
    }

    /**
     * Define el valor de la propiedad comprador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComprador(String value) {
        this.comprador = value;
    }

    /**
     * Obtiene el valor de la propiedad vendedor.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendedor() {
        return vendedor;
    }

    /**
     * Define el valor de la propiedad vendedor.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendedor(String value) {
        this.vendedor = value;
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
     * Obtiene el valor de la propiedad numeroCupo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCupo() {
        return numeroCupo;
    }

    /**
     * Define el valor de la propiedad numeroCupo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCupo(String value) {
        this.numeroCupo = value;
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
     * Obtiene el valor de la propiedad destinatario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * Define el valor de la propiedad destinatario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatario(String value) {
        this.destinatario = value;
    }

    /**
     * Obtiene el valor de la propiedad lugarDescarga.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugarDescarga() {
        return lugarDescarga;
    }

    /**
     * Define el valor de la propiedad lugarDescarga.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugarDescarga(String value) {
        this.lugarDescarga = value;
    }

    /**
     * Obtiene el valor de la propiedad otorgados.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtorgados() {
        return otorgados;
    }

    /**
     * Define el valor de la propiedad otorgados.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtorgados(String value) {
        this.otorgados = value;
    }

    /**
     * Obtiene el valor de la propiedad destinatariocuit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatariocuit() {
        return destinatariocuit;
    }

    /**
     * Define el valor de la propiedad destinatariocuit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatariocuit(String value) {
        this.destinatariocuit = value;
    }

    /**
     * Obtiene el valor de la propiedad corredorcuit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorredorcuit() {
        return corredorcuit;
    }

    /**
     * Define el valor de la propiedad corredorcuit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorredorcuit(String value) {
        this.corredorcuit = value;
    }

    /**
     * Obtiene el valor de la propiedad intermediariocuit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntermediariocuit() {
        return intermediariocuit;
    }

    /**
     * Define el valor de la propiedad intermediariocuit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntermediariocuit(String value) {
        this.intermediariocuit = value;
    }

    /**
     * Obtiene el valor de la propiedad cargadorcuit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCargadorcuit() {
        return cargadorcuit;
    }

    /**
     * Define el valor de la propiedad cargadorcuit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCargadorcuit(String value) {
        this.cargadorcuit = value;
    }

    /**
     * Obtiene el valor de la propiedad remitentecomercialcuit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemitentecomercialcuit() {
        return remitentecomercialcuit;
    }

    /**
     * Define el valor de la propiedad remitentecomercialcuit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemitentecomercialcuit(String value) {
        this.remitentecomercialcuit = value;
    }

    /**
     * Obtiene el valor de la propiedad corredorvendcuit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorredorvendcuit() {
        return corredorvendcuit;
    }

    /**
     * Define el valor de la propiedad corredorvendcuit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorredorvendcuit(String value) {
        this.corredorvendcuit = value;
    }

    /**
     * Obtiene el valor de la propiedad destinatariorazonsocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatariorazonsocial() {
        return destinatariorazonsocial;
    }

    /**
     * Define el valor de la propiedad destinatariorazonsocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatariorazonsocial(String value) {
        this.destinatariorazonsocial = value;
    }

    /**
     * Obtiene el valor de la propiedad corredorrazonsocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorredorrazonsocial() {
        return corredorrazonsocial;
    }

    /**
     * Define el valor de la propiedad corredorrazonsocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorredorrazonsocial(String value) {
        this.corredorrazonsocial = value;
    }

    /**
     * Obtiene el valor de la propiedad intermediariorazonsocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntermediariorazonsocial() {
        return intermediariorazonsocial;
    }

    /**
     * Define el valor de la propiedad intermediariorazonsocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntermediariorazonsocial(String value) {
        this.intermediariorazonsocial = value;
    }

    /**
     * Obtiene el valor de la propiedad cargadorrazonsocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCargadorrazonsocial() {
        return cargadorrazonsocial;
    }

    /**
     * Define el valor de la propiedad cargadorrazonsocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCargadorrazonsocial(String value) {
        this.cargadorrazonsocial = value;
    }

    /**
     * Obtiene el valor de la propiedad remitentecomercialrazonsocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemitentecomercialrazonsocial() {
        return remitentecomercialrazonsocial;
    }

    /**
     * Define el valor de la propiedad remitentecomercialrazonsocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemitentecomercialrazonsocial(String value) {
        this.remitentecomercialrazonsocial = value;
    }

    /**
     * Obtiene el valor de la propiedad corredorvendrazonsocial.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorredorvendrazonsocial() {
        return corredorvendrazonsocial;
    }

    /**
     * Define el valor de la propiedad corredorvendrazonsocial.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorredorvendrazonsocial(String value) {
        this.corredorvendrazonsocial = value;
    }

    /**
     * Obtiene el valor de la propiedad direcciondestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirecciondestino() {
        return direcciondestino;
    }

    /**
     * Define el valor de la propiedad direcciondestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirecciondestino(String value) {
        this.direcciondestino = value;
    }

    /**
     * Obtiene el valor de la propiedad localidaddestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalidaddestino() {
        return localidaddestino;
    }

    /**
     * Define el valor de la propiedad localidaddestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalidaddestino(String value) {
        this.localidaddestino = value;
    }

    /**
     * Obtiene el valor de la propiedad provinciadestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciadestino() {
        return provinciadestino;
    }

    /**
     * Define el valor de la propiedad provinciadestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciadestino(String value) {
        this.provinciadestino = value;
    }

    /**
     * Obtiene el valor de la propiedad cupoid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCupoid() {
        return cupoid;
    }

    /**
     * Define el valor de la propiedad cupoid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCupoid(String value) {
        this.cupoid = value;
    }

    /**
     * Obtiene el valor de la propiedad cuitdestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuitdestino() {
        return cuitdestino;
    }

    /**
     * Define el valor de la propiedad cuitdestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuitdestino(String value) {
        this.cuitdestino = value;
    }

}
