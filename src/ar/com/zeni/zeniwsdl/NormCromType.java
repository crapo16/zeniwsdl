
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NormCromType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NormCromType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="prod" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="normvig" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="fileNorma" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="fileTMPS" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="fileNormaId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="fileTMPSId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NormCromType", propOrder = {
    "id",
    "prod",
    "normvig",
    "fileNorma",
    "fileTMPS",
    "fileNormaId",
    "fileTMPSId"
})
public class NormCromType {

    @XmlElement(required = true)
    protected String id;
    @XmlElement(required = true)
    protected String prod;
    @XmlElement(required = true)
    protected String normvig;
    @XmlElement(required = true)
    protected String fileNorma;
    @XmlElement(required = true)
    protected String fileTMPS;
    @XmlElement(required = true)
    protected String fileNormaId;
    @XmlElement(required = true)
    protected String fileTMPSId;

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
     * Gets the value of the prod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProd() {
        return prod;
    }

    /**
     * Sets the value of the prod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProd(String value) {
        this.prod = value;
    }

    /**
     * Gets the value of the normvig property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNormvig() {
        return normvig;
    }

    /**
     * Sets the value of the normvig property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNormvig(String value) {
        this.normvig = value;
    }

    /**
     * Gets the value of the fileNorma property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileNorma() {
        return fileNorma;
    }

    /**
     * Sets the value of the fileNorma property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileNorma(String value) {
        this.fileNorma = value;
    }

    /**
     * Gets the value of the fileTMPS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileTMPS() {
        return fileTMPS;
    }

    /**
     * Sets the value of the fileTMPS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileTMPS(String value) {
        this.fileTMPS = value;
    }

    /**
     * Gets the value of the fileNormaId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileNormaId() {
        return fileNormaId;
    }

    /**
     * Sets the value of the fileNormaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileNormaId(String value) {
        this.fileNormaId = value;
    }

    /**
     * Gets the value of the fileTMPSId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileTMPSId() {
        return fileTMPSId;
    }

    /**
     * Sets the value of the fileTMPSId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileTMPSId(String value) {
        this.fileTMPSId = value;
    }

}
