
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de liquidacion por contrato.
 * 					
 * 
 * <p>Java class for LiquidacionByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LiquidacionByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="cpte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comprobanteId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kgsfact" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="impvend" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="anticip" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="mon" type="{http://www.zeni.com.ar/ZeniWSDL/}MonedaType"/>
 *         &lt;element name="usu" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="compfinal" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="anuladopor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LiquidacionByContratoType", propOrder = {
    "fecha",
    "cpte",
    "comprobanteId",
    "tipo",
    "kgsfact",
    "impvend",
    "anticip",
    "mon",
    "usu",
    "compfinal",
    "anuladopor"
})
public class LiquidacionByContratoType {

    @XmlElement(required = true)
    protected FechaTimeType fecha;
    @XmlElement(required = true)
    protected String cpte;
    @XmlElement(required = true)
    protected String comprobanteId;
    @XmlElement(required = true)
    protected String tipo;
    protected float kgsfact;
    protected float impvend;
    protected float anticip;
    @XmlElement(required = true)
    protected MonedaType mon;
    @XmlElement(required = true)
    protected String usu;
    protected float compfinal;
    @XmlElement(required = true)
    protected String anuladopor;

    /**
     * Gets the value of the fecha property.
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
     * Sets the value of the fecha property.
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
     * Gets the value of the cpte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpte() {
        return cpte;
    }

    /**
     * Sets the value of the cpte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpte(String value) {
        this.cpte = value;
    }

    /**
     * Gets the value of the comprobanteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComprobanteId() {
        return comprobanteId;
    }

    /**
     * Sets the value of the comprobanteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComprobanteId(String value) {
        this.comprobanteId = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the kgsfact property.
     * 
     */
    public float getKgsfact() {
        return kgsfact;
    }

    /**
     * Sets the value of the kgsfact property.
     * 
     */
    public void setKgsfact(float value) {
        this.kgsfact = value;
    }

    /**
     * Gets the value of the impvend property.
     * 
     */
    public float getImpvend() {
        return impvend;
    }

    /**
     * Sets the value of the impvend property.
     * 
     */
    public void setImpvend(float value) {
        this.impvend = value;
    }

    /**
     * Gets the value of the anticip property.
     * 
     */
    public float getAnticip() {
        return anticip;
    }

    /**
     * Sets the value of the anticip property.
     * 
     */
    public void setAnticip(float value) {
        this.anticip = value;
    }

    /**
     * Gets the value of the mon property.
     * 
     * @return
     *     possible object is
     *     {@link MonedaType }
     *     
     */
    public MonedaType getMon() {
        return mon;
    }

    /**
     * Sets the value of the mon property.
     * 
     * @param value
     *     allowed object is
     *     {@link MonedaType }
     *     
     */
    public void setMon(MonedaType value) {
        this.mon = value;
    }

    /**
     * Gets the value of the usu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsu() {
        return usu;
    }

    /**
     * Sets the value of the usu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsu(String value) {
        this.usu = value;
    }

    /**
     * Gets the value of the compfinal property.
     * 
     */
    public float getCompfinal() {
        return compfinal;
    }

    /**
     * Sets the value of the compfinal property.
     * 
     */
    public void setCompfinal(float value) {
        this.compfinal = value;
    }

    /**
     * Gets the value of the anuladopor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnuladopor() {
        return anuladopor;
    }

    /**
     * Sets the value of the anuladopor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnuladopor(String value) {
        this.anuladopor = value;
    }

}
