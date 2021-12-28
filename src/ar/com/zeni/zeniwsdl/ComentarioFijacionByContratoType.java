
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComentarioFijacionByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComentarioFijacionByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Mercado" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="Pizarra" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="FechaDesde" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="FechaHasta" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="CantMinDia" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="CantMaxDia" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="CantMinSem" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="CantMaxSem" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="CantMinMes" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="CantMaxMes" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Unidad" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="GrupoFijacionesId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComentarioFijacionByContratoType", propOrder = {
    "mercado",
    "pizarra",
    "fechaDesde",
    "fechaHasta",
    "cantMinDia",
    "cantMaxDia",
    "cantMinSem",
    "cantMaxSem",
    "cantMinMes",
    "cantMaxMes",
    "unidad",
    "grupoFijacionesId"
})
public class ComentarioFijacionByContratoType {

    @XmlElement(name = "Mercado", required = true)
    protected String mercado;
    @XmlElement(name = "Pizarra", required = true)
    protected String pizarra;
    @XmlElement(name = "FechaDesde", required = true)
    protected FechaTimeType fechaDesde;
    @XmlElement(name = "FechaHasta", required = true)
    protected FechaTimeType fechaHasta;
    @XmlElement(name = "CantMinDia")
    protected float cantMinDia;
    @XmlElement(name = "CantMaxDia")
    protected float cantMaxDia;
    @XmlElement(name = "CantMinSem")
    protected float cantMinSem;
    @XmlElement(name = "CantMaxSem")
    protected float cantMaxSem;
    @XmlElement(name = "CantMinMes")
    protected float cantMinMes;
    @XmlElement(name = "CantMaxMes")
    protected float cantMaxMes;
    @XmlElement(name = "Unidad", required = true)
    protected String unidad;
    @XmlElement(name = "GrupoFijacionesId", required = true)
    protected String grupoFijacionesId;

    /**
     * Gets the value of the mercado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMercado() {
        return mercado;
    }

    /**
     * Sets the value of the mercado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMercado(String value) {
        this.mercado = value;
    }

    /**
     * Gets the value of the pizarra property.
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
     * Sets the value of the pizarra property.
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
     * Gets the value of the fechaDesde property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaDesde() {
        return fechaDesde;
    }

    /**
     * Sets the value of the fechaDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaDesde(FechaTimeType value) {
        this.fechaDesde = value;
    }

    /**
     * Gets the value of the fechaHasta property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaHasta() {
        return fechaHasta;
    }

    /**
     * Sets the value of the fechaHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaHasta(FechaTimeType value) {
        this.fechaHasta = value;
    }

    /**
     * Gets the value of the cantMinDia property.
     * 
     */
    public float getCantMinDia() {
        return cantMinDia;
    }

    /**
     * Sets the value of the cantMinDia property.
     * 
     */
    public void setCantMinDia(float value) {
        this.cantMinDia = value;
    }

    /**
     * Gets the value of the cantMaxDia property.
     * 
     */
    public float getCantMaxDia() {
        return cantMaxDia;
    }

    /**
     * Sets the value of the cantMaxDia property.
     * 
     */
    public void setCantMaxDia(float value) {
        this.cantMaxDia = value;
    }

    /**
     * Gets the value of the cantMinSem property.
     * 
     */
    public float getCantMinSem() {
        return cantMinSem;
    }

    /**
     * Sets the value of the cantMinSem property.
     * 
     */
    public void setCantMinSem(float value) {
        this.cantMinSem = value;
    }

    /**
     * Gets the value of the cantMaxSem property.
     * 
     */
    public float getCantMaxSem() {
        return cantMaxSem;
    }

    /**
     * Sets the value of the cantMaxSem property.
     * 
     */
    public void setCantMaxSem(float value) {
        this.cantMaxSem = value;
    }

    /**
     * Gets the value of the cantMinMes property.
     * 
     */
    public float getCantMinMes() {
        return cantMinMes;
    }

    /**
     * Sets the value of the cantMinMes property.
     * 
     */
    public void setCantMinMes(float value) {
        this.cantMinMes = value;
    }

    /**
     * Gets the value of the cantMaxMes property.
     * 
     */
    public float getCantMaxMes() {
        return cantMaxMes;
    }

    /**
     * Sets the value of the cantMaxMes property.
     * 
     */
    public void setCantMaxMes(float value) {
        this.cantMaxMes = value;
    }

    /**
     * Gets the value of the unidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * Sets the value of the unidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnidad(String value) {
        this.unidad = value;
    }

    /**
     * Gets the value of the grupoFijacionesId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrupoFijacionesId() {
        return grupoFijacionesId;
    }

    /**
     * Sets the value of the grupoFijacionesId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrupoFijacionesId(String value) {
        this.grupoFijacionesId = value;
    }

}
