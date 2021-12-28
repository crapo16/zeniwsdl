
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AjusteComentarioFijacionByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AjusteComentarioFijacionByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GrupoFijacionesId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="TipoAjuste" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="Moneda" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="MonedaSimbolo" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="ImporteAjuste" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="PorcentajeAjuste" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AjusteComentarioFijacionByContratoType", propOrder = {
    "grupoFijacionesId",
    "tipoAjuste",
    "moneda",
    "monedaSimbolo",
    "importeAjuste",
    "porcentajeAjuste"
})
public class AjusteComentarioFijacionByContratoType {

    @XmlElement(name = "GrupoFijacionesId", required = true)
    protected String grupoFijacionesId;
    @XmlElement(name = "TipoAjuste", required = true)
    protected String tipoAjuste;
    @XmlElement(name = "Moneda", required = true)
    protected String moneda;
    @XmlElement(name = "MonedaSimbolo", required = true)
    protected String monedaSimbolo;
    @XmlElement(name = "ImporteAjuste")
    protected float importeAjuste;
    @XmlElement(name = "PorcentajeAjuste")
    protected float porcentajeAjuste;

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

    /**
     * Gets the value of the tipoAjuste property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoAjuste() {
        return tipoAjuste;
    }

    /**
     * Sets the value of the tipoAjuste property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoAjuste(String value) {
        this.tipoAjuste = value;
    }

    /**
     * Gets the value of the moneda property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Sets the value of the moneda property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
    }

    /**
     * Gets the value of the monedaSimbolo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonedaSimbolo() {
        return monedaSimbolo;
    }

    /**
     * Sets the value of the monedaSimbolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonedaSimbolo(String value) {
        this.monedaSimbolo = value;
    }

    /**
     * Gets the value of the importeAjuste property.
     * 
     */
    public float getImporteAjuste() {
        return importeAjuste;
    }

    /**
     * Sets the value of the importeAjuste property.
     * 
     */
    public void setImporteAjuste(float value) {
        this.importeAjuste = value;
    }

    /**
     * Gets the value of the porcentajeAjuste property.
     * 
     */
    public float getPorcentajeAjuste() {
        return porcentajeAjuste;
    }

    /**
     * Sets the value of the porcentajeAjuste property.
     * 
     */
    public void setPorcentajeAjuste(float value) {
        this.porcentajeAjuste = value;
    }

}
