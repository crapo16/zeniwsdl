
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de entrega por contrato.
 * 					
 * 
 * <p>Clase Java para EntregaByContratoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EntregaByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idEntrega" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="fechaDescargaMercaderia" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="kilos" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ctg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kgfijar" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="cport" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="CpteNro" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="producto" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="proc" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="conf" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="comentarios" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="porcentajezarandeo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="cantmermaporzarandeo" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="cantmermaporsecado" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="cantotrasmermas" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="porcentajehumedad" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="cantidadneta" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="unidaddemedida" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="destino" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="nrorecibo" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="nroremito" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="observaciones" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntregaByContratoType", propOrder = {
    "idEntrega",
    "fechaDescargaMercaderia",
    "kilos",
    "ctg",
    "kgfijar",
    "cport",
    "cpteNro",
    "producto",
    "proc",
    "conf",
    "comentarios",
    "porcentajezarandeo",
    "cantmermaporzarandeo",
    "cantmermaporsecado",
    "cantotrasmermas",
    "porcentajehumedad",
    "cantidadneta",
    "unidaddemedida",
    "destino",
    "nrorecibo",
    "nroremito",
    "observaciones"
})
public class EntregaByContratoType {

    @XmlElement(required = true)
    protected String idEntrega;
    @XmlElement(required = true)
    protected FechaTimeType fechaDescargaMercaderia;
    protected float kilos;
    protected String ctg;
    protected float kgfijar;
    @XmlElement(required = true)
    protected String cport;
    @XmlElement(name = "CpteNro", required = true)
    protected String cpteNro;
    @XmlElement(required = true)
    protected String producto;
    @XmlElement(required = true)
    protected String proc;
    @XmlElement(required = true)
    protected String conf;
    @XmlElement(required = true)
    protected String comentarios;
    protected float porcentajezarandeo;
    protected float cantmermaporzarandeo;
    protected float cantmermaporsecado;
    protected float cantotrasmermas;
    protected float porcentajehumedad;
    protected float cantidadneta;
    @XmlElement(required = true)
    protected String unidaddemedida;
    @XmlElement(required = true)
    protected String destino;
    @XmlElement(required = true)
    protected String nrorecibo;
    @XmlElement(required = true)
    protected String nroremito;
    protected String observaciones;

    /**
     * Obtiene el valor de la propiedad idEntrega.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEntrega() {
        return idEntrega;
    }

    /**
     * Define el valor de la propiedad idEntrega.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEntrega(String value) {
        this.idEntrega = value;
    }

    public String getCtg() {
        return ctg;
    }

    public void setCtg(String ctg) {
        this.ctg = ctg;
    }

    /**
     * Obtiene el valor de la propiedad fechaDescargaMercaderia.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaDescargaMercaderia() {
        return fechaDescargaMercaderia;
    }

    /**
     * Define el valor de la propiedad fechaDescargaMercaderia.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaDescargaMercaderia(FechaTimeType value) {
        this.fechaDescargaMercaderia = value;
    }

    /**
     * Obtiene el valor de la propiedad kilos.
     * 
     */
    public float getKilos() {
        return kilos;
    }

    /**
     * Define el valor de la propiedad kilos.
     * 
     */
    public void setKilos(float value) {
        this.kilos = value;
    }

    /**
     * Obtiene el valor de la propiedad kgfijar.
     * 
     */
    public float getKgfijar() {
        return kgfijar;
    }

    /**
     * Define el valor de la propiedad kgfijar.
     * 
     */
    public void setKgfijar(float value) {
        this.kgfijar = value;
    }

    /**
     * Obtiene el valor de la propiedad cport.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCport() {
        return cport;
    }

    /**
     * Define el valor de la propiedad cport.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCport(String value) {
        this.cport = value;
    }

    /**
     * Obtiene el valor de la propiedad cpteNro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpteNro() {
        return cpteNro;
    }

    /**
     * Define el valor de la propiedad cpteNro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpteNro(String value) {
        this.cpteNro = value;
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
     * Obtiene el valor de la propiedad proc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProc() {
        return proc;
    }

    /**
     * Define el valor de la propiedad proc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProc(String value) {
        this.proc = value;
    }

    /**
     * Obtiene el valor de la propiedad conf.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConf() {
        return conf;
    }

    /**
     * Define el valor de la propiedad conf.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConf(String value) {
        this.conf = value;
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
     * Obtiene el valor de la propiedad porcentajezarandeo.
     * 
     */
    public float getPorcentajezarandeo() {
        return porcentajezarandeo;
    }

    /**
     * Define el valor de la propiedad porcentajezarandeo.
     * 
     */
    public void setPorcentajezarandeo(float value) {
        this.porcentajezarandeo = value;
    }

    /**
     * Obtiene el valor de la propiedad cantmermaporzarandeo.
     * 
     */
    public float getCantmermaporzarandeo() {
        return cantmermaporzarandeo;
    }

    /**
     * Define el valor de la propiedad cantmermaporzarandeo.
     * 
     */
    public void setCantmermaporzarandeo(float value) {
        this.cantmermaporzarandeo = value;
    }

    /**
     * Obtiene el valor de la propiedad cantmermaporsecado.
     * 
     */
    public float getCantmermaporsecado() {
        return cantmermaporsecado;
    }

    /**
     * Define el valor de la propiedad cantmermaporsecado.
     * 
     */
    public void setCantmermaporsecado(float value) {
        this.cantmermaporsecado = value;
    }

    /**
     * Obtiene el valor de la propiedad cantotrasmermas.
     * 
     */
    public float getCantotrasmermas() {
        return cantotrasmermas;
    }

    /**
     * Define el valor de la propiedad cantotrasmermas.
     * 
     */
    public void setCantotrasmermas(float value) {
        this.cantotrasmermas = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentajehumedad.
     * 
     */
    public float getPorcentajehumedad() {
        return porcentajehumedad;
    }

    /**
     * Define el valor de la propiedad porcentajehumedad.
     * 
     */
    public void setPorcentajehumedad(float value) {
        this.porcentajehumedad = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadneta.
     * 
     */
    public float getCantidadneta() {
        return cantidadneta;
    }

    /**
     * Define el valor de la propiedad cantidadneta.
     * 
     */
    public void setCantidadneta(float value) {
        this.cantidadneta = value;
    }

    /**
     * Obtiene el valor de la propiedad unidaddemedida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidaddemedida() {
        return unidaddemedida;
    }

    /**
     * Define el valor de la propiedad unidaddemedida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidaddemedida(String value) {
        this.unidaddemedida = value;
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
     * Obtiene el valor de la propiedad nrorecibo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNrorecibo() {
        return nrorecibo;
    }

    /**
     * Define el valor de la propiedad nrorecibo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNrorecibo(String value) {
        this.nrorecibo = value;
    }

    /**
     * Obtiene el valor de la propiedad nroremito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroremito() {
        return nroremito;
    }

    /**
     * Define el valor de la propiedad nroremito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroremito(String value) {
        this.nroremito = value;
    }

    /**
     * Obtiene el valor de la propiedad observaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Define el valor de la propiedad observaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservaciones(String value) {
        this.observaciones = value;
    }

}
