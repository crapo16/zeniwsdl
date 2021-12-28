
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para CalidadEntrega complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="CalidadEntrega">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="nroComOrig" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="turno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kgsOrigen" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="kgsDestino" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="kgsDiferencia" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="mermaHumedad" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="mermaCalidad" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="mermaVolatil" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="kgsNetos" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="bonifRebaja" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="humedad" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="cposExtranos" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="tierra" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="partidos" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="daniados" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="granosVerdes" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="calidadCalculada" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="producto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CalidadEntrega", propOrder = {
    "fecha",
    "nroComOrig",
    "turno",
    "kgsOrigen",
    "kgsDestino",
    "kgsDiferencia",
    "mermaHumedad",
    "mermaCalidad",
    "mermaVolatil",
    "kgsNetos",
    "bonifRebaja",
    "humedad",
    "cposExtranos",
    "tierra",
    "partidos",
    "daniados",
    "granosVerdes",
    "calidadCalculada",
    "producto"
})
public class CalidadEntrega {

    @XmlElement(required = true)
    protected FechaTimeType fecha;
    @XmlElement(required = true)
    protected String nroComOrig;
    @XmlElement(required = true)
    protected String turno;
    protected float kgsOrigen;
    protected float kgsDestino;
    protected float kgsDiferencia;
    protected float mermaHumedad;
    protected float mermaCalidad;
    protected float mermaVolatil;
    protected float kgsNetos;
    protected float bonifRebaja;
    protected float humedad;
    protected float cposExtranos;
    protected float tierra;
    protected float partidos;
    protected float daniados;
    protected float granosVerdes;
    protected float calidadCalculada;
    @XmlElement(required = true)
    protected String producto;

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
     * Obtiene el valor de la propiedad nroComOrig.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroComOrig() {
        return nroComOrig;
    }

    /**
     * Define el valor de la propiedad nroComOrig.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroComOrig(String value) {
        this.nroComOrig = value;
    }

    /**
     * Obtiene el valor de la propiedad turno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTurno() {
        return turno;
    }

    /**
     * Define el valor de la propiedad turno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTurno(String value) {
        this.turno = value;
    }

    /**
     * Obtiene el valor de la propiedad kgsOrigen.
     * 
     */
    public float getKgsOrigen() {
        return kgsOrigen;
    }

    /**
     * Define el valor de la propiedad kgsOrigen.
     * 
     */
    public void setKgsOrigen(float value) {
        this.kgsOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad kgsDestino.
     * 
     */
    public float getKgsDestino() {
        return kgsDestino;
    }

    /**
     * Define el valor de la propiedad kgsDestino.
     * 
     */
    public void setKgsDestino(float value) {
        this.kgsDestino = value;
    }

    /**
     * Obtiene el valor de la propiedad kgsDiferencia.
     * 
     */
    public float getKgsDiferencia() {
        return kgsDiferencia;
    }

    /**
     * Define el valor de la propiedad kgsDiferencia.
     * 
     */
    public void setKgsDiferencia(float value) {
        this.kgsDiferencia = value;
    }

    /**
     * Obtiene el valor de la propiedad mermaHumedad.
     * 
     */
    public float getMermaHumedad() {
        return mermaHumedad;
    }

    /**
     * Define el valor de la propiedad mermaHumedad.
     * 
     */
    public void setMermaHumedad(float value) {
        this.mermaHumedad = value;
    }

    /**
     * Obtiene el valor de la propiedad mermaCalidad.
     * 
     */
    public float getMermaCalidad() {
        return mermaCalidad;
    }

    /**
     * Define el valor de la propiedad mermaCalidad.
     * 
     */
    public void setMermaCalidad(float value) {
        this.mermaCalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad mermaVolatil.
     * 
     */
    public float getMermaVolatil() {
        return mermaVolatil;
    }

    /**
     * Define el valor de la propiedad mermaVolatil.
     * 
     */
    public void setMermaVolatil(float value) {
        this.mermaVolatil = value;
    }

    /**
     * Obtiene el valor de la propiedad kgsNetos.
     * 
     */
    public float getKgsNetos() {
        return kgsNetos;
    }

    /**
     * Define el valor de la propiedad kgsNetos.
     * 
     */
    public void setKgsNetos(float value) {
        this.kgsNetos = value;
    }

    /**
     * Obtiene el valor de la propiedad bonifRebaja.
     * 
     */
    public float getBonifRebaja() {
        return bonifRebaja;
    }

    /**
     * Define el valor de la propiedad bonifRebaja.
     * 
     */
    public void setBonifRebaja(float value) {
        this.bonifRebaja = value;
    }

    /**
     * Obtiene el valor de la propiedad humedad.
     * 
     */
    public float getHumedad() {
        return humedad;
    }

    /**
     * Define el valor de la propiedad humedad.
     * 
     */
    public void setHumedad(float value) {
        this.humedad = value;
    }

    /**
     * Obtiene el valor de la propiedad cposExtranos.
     * 
     */
    public float getCposExtranos() {
        return cposExtranos;
    }

    /**
     * Define el valor de la propiedad cposExtranos.
     * 
     */
    public void setCposExtranos(float value) {
        this.cposExtranos = value;
    }

    /**
     * Obtiene el valor de la propiedad tierra.
     * 
     */
    public float getTierra() {
        return tierra;
    }

    /**
     * Define el valor de la propiedad tierra.
     * 
     */
    public void setTierra(float value) {
        this.tierra = value;
    }

    /**
     * Obtiene el valor de la propiedad partidos.
     * 
     */
    public float getPartidos() {
        return partidos;
    }

    /**
     * Define el valor de la propiedad partidos.
     * 
     */
    public void setPartidos(float value) {
        this.partidos = value;
    }

    /**
     * Obtiene el valor de la propiedad daniados.
     * 
     */
    public float getDaniados() {
        return daniados;
    }

    /**
     * Define el valor de la propiedad daniados.
     * 
     */
    public void setDaniados(float value) {
        this.daniados = value;
    }

    /**
     * Obtiene el valor de la propiedad granosVerdes.
     * 
     */
    public float getGranosVerdes() {
        return granosVerdes;
    }

    /**
     * Define el valor de la propiedad granosVerdes.
     * 
     */
    public void setGranosVerdes(float value) {
        this.granosVerdes = value;
    }

    /**
     * Obtiene el valor de la propiedad calidadCalculada.
     * 
     */
    public float getCalidadCalculada() {
        return calidadCalculada;
    }

    /**
     * Define el valor de la propiedad calidadCalculada.
     * 
     */
    public void setCalidadCalculada(float value) {
        this.calidadCalculada = value;
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

}
