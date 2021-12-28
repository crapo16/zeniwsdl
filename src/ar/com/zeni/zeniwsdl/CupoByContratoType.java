
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * tipo de cupo
 * 
 * <p>Java class for CupoByContratoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CupoByContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fecha" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="destinatario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lugar" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cuposOtorgados" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cuposAnulados" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="medioDeTransporte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="listaCupos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CupoByContratoType", propOrder = {
    "contrato",
    "fecha",
    "destinatario",
    "lugar",
    "cuposOtorgados",
    "cuposAnulados",
    "medioDeTransporte",
    "listaCupos"
})
public class CupoByContratoType {

    @XmlElement(required = true)
    protected String contrato;
    @XmlElement(required = true)
    protected FechaTimeType fecha;
    @XmlElement(required = true)
    protected String destinatario;
    @XmlElement(required = true)
    protected String lugar;
    protected int cuposOtorgados;
    protected int cuposAnulados;
    @XmlElement(required = true)
    protected String medioDeTransporte;
    protected List<String> listaCupos;

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
     * Gets the value of the destinatario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * Sets the value of the destinatario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatario(String value) {
        this.destinatario = value;
    }

    /**
     * Gets the value of the lugar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Sets the value of the lugar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLugar(String value) {
        this.lugar = value;
    }

    /**
     * Gets the value of the cuposOtorgados property.
     * 
     */
    public int getCuposOtorgados() {
        return cuposOtorgados;
    }

    /**
     * Sets the value of the cuposOtorgados property.
     * 
     */
    public void setCuposOtorgados(int value) {
        this.cuposOtorgados = value;
    }

    /**
     * Gets the value of the cuposAnulados property.
     * 
     */
    public int getCuposAnulados() {
        return cuposAnulados;
    }

    /**
     * Sets the value of the cuposAnulados property.
     * 
     */
    public void setCuposAnulados(int value) {
        this.cuposAnulados = value;
    }

    /**
     * Gets the value of the medioDeTransporte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedioDeTransporte() {
        return medioDeTransporte;
    }

    /**
     * Sets the value of the medioDeTransporte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedioDeTransporte(String value) {
        this.medioDeTransporte = value;
    }

    /**
     * Gets the value of the listaCupos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaCupos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaCupos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getListaCupos() {
        if (listaCupos == null) {
            listaCupos = new ArrayList<String>();
        }
        return this.listaCupos;
    }

}
