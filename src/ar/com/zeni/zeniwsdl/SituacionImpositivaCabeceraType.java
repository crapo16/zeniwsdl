
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SituacionImpositivaCabeceraType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SituacionImpositivaCabeceraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="cuit" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="cbu" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="razonSocial" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="actividad" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="domicilio" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="codPostal" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="localidad" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="provincia" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="tipoSociedad" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="nroSAGPYA" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="fechaAlta" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="categIngresosBrutos" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="categoriaGanancias" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="ivaresgeneral1394" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="resolucion" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="percepcioniva" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="categoriaiva" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SituacionImpositivaCabeceraType", propOrder = {
    "cuenta",
    "cuit",
    "cbu",
    "razonSocial",
    "actividad",
    "domicilio",
    "codPostal",
    "localidad",
    "provincia",
    "tipoSociedad",
    "nroSAGPYA",
    "fechaAlta",
    "categIngresosBrutos",
    "categoriaGanancias",
    "ivaresgeneral1394",
    "resolucion",
    "percepcioniva",
    "categoriaiva"
})
public class SituacionImpositivaCabeceraType {

    @XmlElement(required = true)
    protected String cuenta;
    @XmlElement(required = true)
    protected String cuit;
    @XmlElement(required = true)
    protected String cbu;
    @XmlElement(required = true)
    protected String razonSocial;
    @XmlElement(required = true)
    protected String actividad;
    @XmlElement(required = true)
    protected String domicilio;
    @XmlElement(required = true)
    protected String codPostal;
    @XmlElement(required = true)
    protected String localidad;
    @XmlElement(required = true)
    protected String provincia;
    @XmlElement(required = true)
    protected String tipoSociedad;
    @XmlElement(required = true)
    protected String nroSAGPYA;
    @XmlElement(required = true)
    protected FechaTimeType fechaAlta;
    @XmlElement(required = true)
    protected String categIngresosBrutos;
    @XmlElement(required = true)
    protected String categoriaGanancias;
    @XmlElement(required = true)
    protected String ivaresgeneral1394;
    @XmlElement(required = true)
    protected String resolucion;
    @XmlElement(required = true)
    protected String percepcioniva;
    @XmlElement(required = true)
    protected String categoriaiva;

    /**
     * Gets the value of the cuenta property.
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
     * Sets the value of the cuenta property.
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
     * Gets the value of the cuit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuit() {
        return cuit;
    }

    /**
     * Sets the value of the cuit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuit(String value) {
        this.cuit = value;
    }

    /**
     * Gets the value of the cbu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCbu() {
        return cbu;
    }

    /**
     * Sets the value of the cbu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCbu(String value) {
        this.cbu = value;
    }

    /**
     * Gets the value of the razonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Sets the value of the razonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazonSocial(String value) {
        this.razonSocial = value;
    }

    /**
     * Gets the value of the actividad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividad() {
        return actividad;
    }

    /**
     * Sets the value of the actividad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividad(String value) {
        this.actividad = value;
    }

    /**
     * Gets the value of the domicilio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Sets the value of the domicilio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomicilio(String value) {
        this.domicilio = value;
    }

    /**
     * Gets the value of the codPostal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodPostal() {
        return codPostal;
    }

    /**
     * Sets the value of the codPostal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodPostal(String value) {
        this.codPostal = value;
    }

    /**
     * Gets the value of the localidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Sets the value of the localidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalidad(String value) {
        this.localidad = value;
    }

    /**
     * Gets the value of the provincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the value of the provincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincia(String value) {
        this.provincia = value;
    }

    /**
     * Gets the value of the tipoSociedad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSociedad() {
        return tipoSociedad;
    }

    /**
     * Sets the value of the tipoSociedad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSociedad(String value) {
        this.tipoSociedad = value;
    }

    /**
     * Gets the value of the nroSAGPYA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroSAGPYA() {
        return nroSAGPYA;
    }

    /**
     * Sets the value of the nroSAGPYA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroSAGPYA(String value) {
        this.nroSAGPYA = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaAlta(FechaTimeType value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the categIngresosBrutos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategIngresosBrutos() {
        return categIngresosBrutos;
    }

    /**
     * Sets the value of the categIngresosBrutos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategIngresosBrutos(String value) {
        this.categIngresosBrutos = value;
    }

    /**
     * Gets the value of the categoriaGanancias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoriaGanancias() {
        return categoriaGanancias;
    }

    /**
     * Sets the value of the categoriaGanancias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoriaGanancias(String value) {
        this.categoriaGanancias = value;
    }

    /**
     * Gets the value of the ivaresgeneral1394 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIvaresgeneral1394() {
        return ivaresgeneral1394;
    }

    /**
     * Sets the value of the ivaresgeneral1394 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIvaresgeneral1394(String value) {
        this.ivaresgeneral1394 = value;
    }

    /**
     * Gets the value of the resolucion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolucion() {
        return resolucion;
    }

    /**
     * Sets the value of the resolucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolucion(String value) {
        this.resolucion = value;
    }

    /**
     * Gets the value of the percepcioniva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercepcioniva() {
        return percepcioniva;
    }

    /**
     * Sets the value of the percepcioniva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercepcioniva(String value) {
        this.percepcioniva = value;
    }

    /**
     * Gets the value of the categoriaiva property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoriaiva() {
        return categoriaiva;
    }

    /**
     * Sets the value of the categoriaiva property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoriaiva(String value) {
        this.categoriaiva = value;
    }

}
