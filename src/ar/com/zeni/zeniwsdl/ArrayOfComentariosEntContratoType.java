
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * lista de Comentarios Entrega segun Contrato
 * 
 * <p>Clase Java para ArrayOfComentariosEntContratoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfComentariosEntContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="canje" type="{http://www.zeni.com.ar/ZeniWSDL/}ComentarioEntregaByContratoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfComentariosEntContratoType", propOrder = {
    "canje"
})
public class ArrayOfComentariosEntContratoType {

    protected List<ComentarioEntregaByContratoType> canje;

    /**
     * Gets the value of the canje property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the canje property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCanje().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComentarioEntregaByContratoType }
     * 
     * 
     */
    public List<ComentarioEntregaByContratoType> getCanje() {
        if (canje == null) {
            canje = new ArrayList<ComentarioEntregaByContratoType>();
        }
        return this.canje;
    }

}
