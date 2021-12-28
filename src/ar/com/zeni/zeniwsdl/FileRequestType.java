
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo para pedir archivos
 * 					
 * 
 * <p>Java class for FileRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="categoria" type="{http://www.zeni.com.ar/ZeniWSDL/}EnumCategoriaType"/>
 *         &lt;element name="codigoArchivo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileRequestType", propOrder = {
    "id",
    "categoria",
    "codigoArchivo"
})
public class FileRequestType {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected EnumCategoriaType categoria;
    @XmlElement(required = true)
    protected String codigoArchivo;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the categoria property.
     * 
     * @return
     *     possible object is
     *     {@link EnumCategoriaType }
     *     
     */
    public EnumCategoriaType getCategoria() {
        return categoria;
    }

    /**
     * Sets the value of the categoria property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumCategoriaType }
     *     
     */
    public void setCategoria(EnumCategoriaType value) {
        this.categoria = value;
    }

    /**
     * Gets the value of the codigoArchivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoArchivo() {
        return codigoArchivo;
    }

    /**
     * Sets the value of the codigoArchivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoArchivo(String value) {
        this.codigoArchivo = value;
    }

}
