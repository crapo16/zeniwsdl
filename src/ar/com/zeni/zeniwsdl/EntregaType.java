
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de entrega.
 * 
 * <p>Java class for EntregaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntregaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="contrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contratId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="kgEntrega" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="contraparte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="procedencia" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productoDescripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nroCPorte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tipoCompradorVendedor" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumCompradorVendedorType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntregaType", propOrder = {
    "fecha",
    "contrato",
    "contratId",
    "kgEntrega",
    "contraparte",
    "procedencia",
    "productoDescripcion",
    "destino",
    "nroCPorte",
    "tipoCompradorVendedor"
})
public class EntregaType {

    @XmlElement(required = true)
    protected FechaTimeType fecha;
    @XmlElement(required = true)
    protected String contrato;
    @XmlElement(required = true)
    protected String contratId;
    protected float kgEntrega;
    @XmlElement(required = true)
    protected String contraparte;
    @XmlElement(required = true)
    protected String procedencia;
    @XmlElement(required = true)
    protected String productoDescripcion;
    @XmlElement(required = true)
    protected String destino;
    @XmlElement(required = true)
    protected String nroCPorte;
    @XmlElement(required = true)
    protected EnumCompradorVendedorType tipoCompradorVendedor;

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
     * Gets the value of the contrato property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrato() {
        return contrato;
    }

    /**
     * Sets the value of the contrato property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrato(String value) {
        this.contrato = value;
    }

    /**
     * Gets the value of the contratId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratId() {
        return contratId;
    }

    /**
     * Sets the value of the contratId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratId(String value) {
        this.contratId = value;
    }

    /**
     * Gets the value of the kgEntrega property.
     * 
     */
    public float getKgEntrega() {
        return kgEntrega;
    }

    /**
     * Sets the value of the kgEntrega property.
     * 
     */
    public void setKgEntrega(float value) {
        this.kgEntrega = value;
    }

    /**
     * Gets the value of the contraparte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContraparte() {
        return contraparte;
    }

    /**
     * Sets the value of the contraparte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContraparte(String value) {
        this.contraparte = value;
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
     * Gets the value of the productoDescripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductoDescripcion() {
        return productoDescripcion;
    }

    /**
     * Sets the value of the productoDescripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductoDescripcion(String value) {
        this.productoDescripcion = value;
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
     * Gets the value of the nroCPorte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNroCPorte() {
        return nroCPorte;
    }

    /**
     * Sets the value of the nroCPorte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNroCPorte(String value) {
        this.nroCPorte = value;
    }

    /**
     * Gets the value of the tipoCompradorVendedor property.
     * 
     * @return
     *     possible object is
     *     {@link EnumCompradorVendedorType }
     *     
     */
    public EnumCompradorVendedorType getTipoCompradorVendedor() {
        return tipoCompradorVendedor;
    }

    /**
     * Sets the value of the tipoCompradorVendedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumCompradorVendedorType }
     *     
     */
    public void setTipoCompradorVendedor(EnumCompradorVendedorType value) {
        this.tipoCompradorVendedor = value;
    }

}
