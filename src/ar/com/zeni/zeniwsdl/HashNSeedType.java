
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 						El Hash es un hash que corresponde al seed
 * 						asociado redundantemente. El seed es una semilla
 * 						que se utilizara
 * 						para encriptar datos durante la
 * 						sesion.
 * 					
 * 
 * <p>Java class for HashNSeedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HashNSeedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seed" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="validezHasta" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HashNSeedType", propOrder = {
    "hash",
    "seed",
    "validezHasta"
})
public class HashNSeedType {

    @XmlElement(required = true)
    protected String hash;
    @XmlElement(required = true)
    protected String seed;
    @XmlElement(required = true)
    protected FechaTimeType validezHasta;

    /**
     * Gets the value of the hash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHash() {
        return hash;
    }

    /**
     * Sets the value of the hash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHash(String value) {
        this.hash = value;
    }

    /**
     * Gets the value of the seed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeed() {
        return seed;
    }

    /**
     * Sets the value of the seed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeed(String value) {
        this.seed = value;
    }

    /**
     * Gets the value of the validezHasta property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getValidezHasta() {
        return validezHasta;
    }

    /**
     * Sets the value of the validezHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setValidezHasta(FechaTimeType value) {
        this.validezHasta = value;
    }

}
