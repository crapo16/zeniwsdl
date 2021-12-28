
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfComentarioComentarioFijacionByContrato complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfComentarioComentarioFijacionByContrato">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comentarios" type="{http://www.zeni.com.ar/ZeniWSDL/}ComentarioComentarioFijacionByContratoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfComentarioComentarioFijacionByContrato", propOrder = {
    "comentarios"
})
public class ArrayOfComentarioComentarioFijacionByContrato {

    protected List<ComentarioComentarioFijacionByContratoType> comentarios;

    /**
     * Gets the value of the comentarios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comentarios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComentarios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComentarioComentarioFijacionByContratoType }
     * 
     * 
     */
    public List<ComentarioComentarioFijacionByContratoType> getComentarios() {
        if (comentarios == null) {
            comentarios = new ArrayList<ComentarioComentarioFijacionByContratoType>();
        }
        return this.comentarios;
    }

}
