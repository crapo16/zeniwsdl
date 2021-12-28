
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MatComisionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MatComisionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuenta" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="mercado" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="producto" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="tipooperacion" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="condicion" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="tipocomision" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="tipotasa" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="valor" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatComisionType", propOrder = {
    "cuenta",
    "mercado",
    "producto",
    "tipooperacion",
    "condicion",
    "tipocomision",
    "tipotasa",
    "valor"
})
public class MatComisionType {

    @XmlElement(required = true)
    protected String cuenta;
    @XmlElement(required = true)
    protected String mercado;
    @XmlElement(required = true)
    protected String producto;
    @XmlElement(required = true)
    protected String tipooperacion;
    @XmlElement(required = true)
    protected String condicion;
    @XmlElement(required = true)
    protected String tipocomision;
    @XmlElement(required = true)
    protected String tipotasa;
    protected float valor;

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
     * Gets the value of the producto property.
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
     * Sets the value of the producto property.
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
     * Gets the value of the tipooperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipooperacion() {
        return tipooperacion;
    }

    /**
     * Sets the value of the tipooperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipooperacion(String value) {
        this.tipooperacion = value;
    }

    /**
     * Gets the value of the condicion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * Sets the value of the condicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondicion(String value) {
        this.condicion = value;
    }

    /**
     * Gets the value of the tipocomision property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipocomision() {
        return tipocomision;
    }

    /**
     * Sets the value of the tipocomision property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipocomision(String value) {
        this.tipocomision = value;
    }

    /**
     * Gets the value of the tipotasa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipotasa() {
        return tipotasa;
    }

    /**
     * Sets the value of the tipotasa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipotasa(String value) {
        this.tipotasa = value;
    }

    /**
     * Gets the value of the valor property.
     * 
     */
    public float getValor() {
        return valor;
    }

    /**
     * Sets the value of the valor property.
     * 
     */
    public void setValor(float value) {
        this.valor = value;
    }

}
