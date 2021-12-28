
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ComentarioComentarioFijacionByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComentarioComentarioFijacionByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GrupoFijacionesId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="Comentario" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComentarioComentarioFijacionByContratoType", propOrder = {
    "grupoFijacionesId",
    "comentario"
})
public class ComentarioComentarioFijacionByContratoType {

    @XmlElement(name = "GrupoFijacionesId", required = true)
    protected String grupoFijacionesId;
    @XmlElement(name = "Comentario", required = true)
    protected String comentario;

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
     * Gets the value of the comentario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * Sets the value of the comentario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComentario(String value) {
        this.comentario = value;
    }

}
