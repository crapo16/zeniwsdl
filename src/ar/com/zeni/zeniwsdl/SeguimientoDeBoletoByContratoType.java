
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de liquidacion por contrato.
 * 					
 * 
 * <p>Java class for SeguimientoDeBoletoByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SeguimientoDeBoletoByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="envioVendedorFecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="regresoVendedorFecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="envioABolsaFecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="regresoBolsaFecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="envioACompradorFecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="envioASellarFecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="archivadoFecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="zeniciaFecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="abcdFecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="envioVendedor" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *         &lt;element name="regresoVendedor" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *         &lt;element name="envioABolsa" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *         &lt;element name="regresoBolsa" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *         &lt;element name="envioAComprador" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *         &lt;element name="envioASellar" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *         &lt;element name="archivado" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *         &lt;element name="zenicia" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *         &lt;element name="abcd" type="{http://www.zeni.com.ar/ZeniWSDL/}SINOType"/>
 *         &lt;element name="comentarios" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeguimientoDeBoletoByContratoType", propOrder = {
    "envioVendedorFecha",
    "regresoVendedorFecha",
    "envioABolsaFecha",
    "regresoBolsaFecha",
    "envioACompradorFecha",
    "envioASellarFecha",
    "archivadoFecha",
    "zeniciaFecha",
    "abcdFecha",
    "envioVendedor",
    "regresoVendedor",
    "envioABolsa",
    "regresoBolsa",
    "envioAComprador",
    "envioASellar",
    "archivado",
    "zenicia",
    "abcd",
    "comentarios",
    // #1610
    "seguimientoboleto"
})
public class SeguimientoDeBoletoByContratoType {

    @XmlElement(required = true)
    protected FechaTimeType envioVendedorFecha;
    @XmlElement(required = true)
    protected FechaTimeType regresoVendedorFecha;
    @XmlElement(required = true)
    protected FechaTimeType envioABolsaFecha;
    @XmlElement(required = true)
    protected FechaTimeType regresoBolsaFecha;
    @XmlElement(required = true)
    protected FechaTimeType envioACompradorFecha;
    @XmlElement(required = true)
    protected FechaTimeType envioASellarFecha;
    @XmlElement(required = true)
    protected FechaTimeType archivadoFecha;
    @XmlElement(required = true)
    protected FechaTimeType zeniciaFecha;
    @XmlElement(required = true)
    protected FechaTimeType abcdFecha;
    @XmlElement(required = true)
    protected SINOType envioVendedor;
    @XmlElement(required = true)
    protected SINOType regresoVendedor;
    @XmlElement(required = true)
    protected SINOType envioABolsa;
    @XmlElement(required = true)
    protected SINOType regresoBolsa;
    @XmlElement(required = true)
    protected SINOType envioAComprador;
    @XmlElement(required = true)
    protected SINOType envioASellar;
    @XmlElement(required = true)
    protected SINOType archivado;
    @XmlElement(required = true)
    protected SINOType zenicia;
    @XmlElement(required = true)
    protected SINOType abcd;
    @XmlElement(required = true)
    protected String comentarios;
    
    // #1610
    @XmlElement(required = true)
    protected ArrayOfSeguimientoBoletoType seguimientoboleto;

    public ArrayOfSeguimientoBoletoType getSeguimientoboleto() {
		return seguimientoboleto;
	}

	public void setSeguimientoboleto(ArrayOfSeguimientoBoletoType seguimientoboleto) {
		this.seguimientoboleto = seguimientoboleto;
	}

	/**
     * Gets the value of the envioVendedorFecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getEnvioVendedorFecha() {
        return envioVendedorFecha;
    }

    /**
     * Sets the value of the envioVendedorFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setEnvioVendedorFecha(FechaTimeType value) {
        this.envioVendedorFecha = value;
    }

    /**
     * Gets the value of the regresoVendedorFecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getRegresoVendedorFecha() {
        return regresoVendedorFecha;
    }

    /**
     * Sets the value of the regresoVendedorFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setRegresoVendedorFecha(FechaTimeType value) {
        this.regresoVendedorFecha = value;
    }

    /**
     * Gets the value of the envioABolsaFecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getEnvioABolsaFecha() {
        return envioABolsaFecha;
    }

    /**
     * Sets the value of the envioABolsaFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setEnvioABolsaFecha(FechaTimeType value) {
        this.envioABolsaFecha = value;
    }

    /**
     * Gets the value of the regresoBolsaFecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getRegresoBolsaFecha() {
        return regresoBolsaFecha;
    }

    /**
     * Sets the value of the regresoBolsaFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setRegresoBolsaFecha(FechaTimeType value) {
        this.regresoBolsaFecha = value;
    }

    /**
     * Gets the value of the envioACompradorFecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getEnvioACompradorFecha() {
        return envioACompradorFecha;
    }

    /**
     * Sets the value of the envioACompradorFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setEnvioACompradorFecha(FechaTimeType value) {
        this.envioACompradorFecha = value;
    }

    /**
     * Gets the value of the envioASellarFecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getEnvioASellarFecha() {
        return envioASellarFecha;
    }

    /**
     * Sets the value of the envioASellarFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setEnvioASellarFecha(FechaTimeType value) {
        this.envioASellarFecha = value;
    }

    /**
     * Gets the value of the archivadoFecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getArchivadoFecha() {
        return archivadoFecha;
    }

    /**
     * Sets the value of the archivadoFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setArchivadoFecha(FechaTimeType value) {
        this.archivadoFecha = value;
    }

    /**
     * Gets the value of the zeniciaFecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getZeniciaFecha() {
        return zeniciaFecha;
    }

    /**
     * Sets the value of the zeniciaFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setZeniciaFecha(FechaTimeType value) {
        this.zeniciaFecha = value;
    }

    /**
     * Gets the value of the abcdFecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getAbcdFecha() {
        return abcdFecha;
    }

    /**
     * Sets the value of the abcdFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setAbcdFecha(FechaTimeType value) {
        this.abcdFecha = value;
    }

    /**
     * Gets the value of the envioVendedor property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getEnvioVendedor() {
        return envioVendedor;
    }

    /**
     * Sets the value of the envioVendedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setEnvioVendedor(SINOType value) {
        this.envioVendedor = value;
    }

    /**
     * Gets the value of the regresoVendedor property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getRegresoVendedor() {
        return regresoVendedor;
    }

    /**
     * Sets the value of the regresoVendedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setRegresoVendedor(SINOType value) {
        this.regresoVendedor = value;
    }

    /**
     * Gets the value of the envioABolsa property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getEnvioABolsa() {
        return envioABolsa;
    }

    /**
     * Sets the value of the envioABolsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setEnvioABolsa(SINOType value) {
        this.envioABolsa = value;
    }

    /**
     * Gets the value of the regresoBolsa property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getRegresoBolsa() {
        return regresoBolsa;
    }

    /**
     * Sets the value of the regresoBolsa property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setRegresoBolsa(SINOType value) {
        this.regresoBolsa = value;
    }

    /**
     * Gets the value of the envioAComprador property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getEnvioAComprador() {
        return envioAComprador;
    }

    /**
     * Sets the value of the envioAComprador property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setEnvioAComprador(SINOType value) {
        this.envioAComprador = value;
    }

    /**
     * Gets the value of the envioASellar property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getEnvioASellar() {
        return envioASellar;
    }

    /**
     * Sets the value of the envioASellar property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setEnvioASellar(SINOType value) {
        this.envioASellar = value;
    }

    /**
     * Gets the value of the archivado property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getArchivado() {
        return archivado;
    }

    /**
     * Sets the value of the archivado property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setArchivado(SINOType value) {
        this.archivado = value;
    }

    /**
     * Gets the value of the zenicia property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getZenicia() {
        return zenicia;
    }

    /**
     * Sets the value of the zenicia property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setZenicia(SINOType value) {
        this.zenicia = value;
    }

    /**
     * Gets the value of the abcd property.
     * 
     * @return
     *     possible object is
     *     {@link SINOType }
     *     
     */
    public SINOType getAbcd() {
        return abcd;
    }

    /**
     * Sets the value of the abcd property.
     * 
     * @param value
     *     allowed object is
     *     {@link SINOType }
     *     
     */
    public void setAbcd(SINOType value) {
        this.abcd = value;
    }

    /**
     * Gets the value of the comentarios property.
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
     * Sets the value of the comentarios property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentarios(String value) {
        this.comentarios = value;
    }

}
