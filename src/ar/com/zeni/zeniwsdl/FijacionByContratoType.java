
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de fijacion.
 *
 *
 * <p>Clase Java para FijacionByContratoType complex type.
 *
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 *
 * <pre>
 * &lt;complexType name="FijacionByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nro" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecpizarra" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="kgfijados" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="pizarra" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="preciofij" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="vtofijaciones" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="sit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoComp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="letra" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="suc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cantidadAFijar" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="simbolo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FijacionByContratoType", propOrder = {
    "nro",
    "fecpizarra",
    "kgfijados",
    "pizarra",
    "preciofij",
    "vtofijaciones",
    "sit",
    "tipoComp",
    "letra",
    "suc",
    "numero",
    "cantidadAFijar",
    "simbolo"
})
public class FijacionByContratoType {

    @XmlElement(required = true)
    protected String nro;
    @XmlElement(required = true)
    protected FechaTimeType fecpizarra;
    protected float kgfijados;
    @XmlElement(required = true)
    protected String pizarra;
    protected float preciofij;
    @XmlElement(required = true)
    protected FechaTimeType vtofijaciones;
    @XmlElement(required = true)
    protected String sit;
    @XmlElement(required = true)
    protected String tipoComp;
    @XmlElement(required = true)
    protected String letra;
    @XmlElement(required = true)
    protected String suc;
    @XmlElement(required = true)
    protected String numero;
    protected float cantidadAFijar;
    @XmlElement(required = true)
    protected String simbolo;

    /**
     * Obtiene el valor de la propiedad nro.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNro() {
        return nro;
    }

    /**
     * Define el valor de la propiedad nro.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNro(String value) {
        this.nro = value;
    }

    /**
     * Obtiene el valor de la propiedad fecpizarra.
     *
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *
     */
    public FechaTimeType getFecpizarra() {
        return fecpizarra;
    }

    /**
     * Define el valor de la propiedad fecpizarra.
     *
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *
     */
    public void setFecpizarra(FechaTimeType value) {
        this.fecpizarra = value;
    }

    /**
     * Obtiene el valor de la propiedad kgfijados.
     *
     */
    public float getKgfijados() {
        return kgfijados;
    }

    /**
     * Define el valor de la propiedad kgfijados.
     *
     */
    public void setKgfijados(float value) {
        this.kgfijados = value;
    }

    /**
     * Obtiene el valor de la propiedad pizarra.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPizarra() {
        return pizarra;
    }

    /**
     * Define el valor de la propiedad pizarra.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPizarra(String value) {
        this.pizarra = value;
    }

    /**
     * Obtiene el valor de la propiedad preciofij.
     *
     */
    public float getPreciofij() {
        return preciofij;
    }

    /**
     * Define el valor de la propiedad preciofij.
     *
     */
    public void setPreciofij(float value) {
        this.preciofij = value;
    }

    /**
     * Obtiene el valor de la propiedad vtofijaciones.
     *
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *
     */
    public FechaTimeType getVtofijaciones() {
        return vtofijaciones;
    }

    /**
     * Define el valor de la propiedad vtofijaciones.
     *
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *
     */
    public void setVtofijaciones(FechaTimeType value) {
        this.vtofijaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad sit.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSit() {
        return sit;
    }

    /**
     * Define el valor de la propiedad sit.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSit(String value) {
        this.sit = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoComp.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTipoComp() {
        return tipoComp;
    }

    /**
     * Define el valor de la propiedad tipoComp.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTipoComp(String value) {
        this.tipoComp = value;
    }

    /**
     * Obtiene el valor de la propiedad letra.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLetra() {
        return letra;
    }

    /**
     * Define el valor de la propiedad letra.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLetra(String value) {
        this.letra = value;
    }

    /**
     * Obtiene el valor de la propiedad suc.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSuc() {
        return suc;
    }

    /**
     * Define el valor de la propiedad suc.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSuc(String value) {
        this.suc = value;
    }

    /**
     * Obtiene el valor de la propiedad numero.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Define el valor de la propiedad numero.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNumero(String value) {
        this.numero = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadAFijar.
     *
     */
    public float getCantidadAFijar() {
        return cantidadAFijar;
    }

    /**
     * Define el valor de la propiedad cantidadAFijar.
     *
     */
    public void setCantidadAFijar(float value) {
        this.cantidadAFijar = value;
    }

    /**
     * Obtiene el valor de la propiedad simbolo.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     * Define el valor de la propiedad simbolo.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSimbolo(String value) {
        this.simbolo = value;
    }

}
