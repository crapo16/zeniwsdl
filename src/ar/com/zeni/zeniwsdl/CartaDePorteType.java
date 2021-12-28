
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CartaDePorteType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CartaDePorteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroContrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numeroCartaPorte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vagasig" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="procedencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fechadescargamercaderia" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="kilos" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="ctg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CartaDePorteType", propOrder = {
    "numeroContrato",
    "numeroCartaPorte",
    "vagasig",
    "destino",
    "procedencia",
    "fechadescargamercaderia",
    "kilos",
    "ctg"
})
public class CartaDePorteType {

    @XmlElement(required = true)
    protected String numeroContrato;
    @XmlElement(required = true)
    protected String numeroCartaPorte;
    @XmlElement(required = true)
    protected String vagasig;
    @XmlElement(required = true)
    protected String destino;
    @XmlElement(required = true)
    protected String procedencia;
    @XmlElement(required = true)
    protected FechaTimeType fechadescargamercaderia;
    protected float kilos;
    @XmlElement(required = true)
    protected String ctg;

    

    /**
     * Gets the value of the numeroContrato property.
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
     * Sets the value of the numeroContrato property.
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
     * Gets the value of the numeroCartaPorte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCartaPorte() {
        return numeroCartaPorte;
    }

    /**
     * Sets the value of the numeroCartaPorte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCartaPorte(String value) {
        this.numeroCartaPorte = value;
    }

    /**
     * Gets the value of the vagasig property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVagasig() {
        return vagasig;
    }

    /**
     * Sets the value of the vagasig property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVagasig(String value) {
        this.vagasig = value;
    }

    /**
     * Gets the value of the destino property.
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
     * Sets the value of the destino property.
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
     * Gets the value of the procedencia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcedencia() {
        return procedencia;
    }

    /**
     * Sets the value of the procedencia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcedencia(String value) {
        this.procedencia = value;
    }

    /**
     * Gets the value of the fechadescargamercaderia property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechadescargamercaderia() {
        return fechadescargamercaderia;
    }

    /**
     * Sets the value of the fechadescargamercaderia property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechadescargamercaderia(FechaTimeType value) {
        this.fechadescargamercaderia = value;
    }

    /**
     * Gets the value of the kilos property.
     * 
     */
    public float getKilos() {
        return kilos;
    }

    /**
     * Sets the value of the kilos property.
     * 
     */
    public void setKilos(float value) {
        this.kilos = value;
    }
    
    public String getCtg() {
        return ctg;
    }

    public void setCtg(String ctg) {
        this.ctg = ctg;
    }

}
