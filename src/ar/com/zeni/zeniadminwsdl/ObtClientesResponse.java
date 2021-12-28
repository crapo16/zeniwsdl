
package ar.com.zeni.zeniadminwsdl;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pass" type="{http://www.zeni.com.ar/ZeniAdminWSDL/}ArrayOfAdminClienteType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pass"
})
@XmlRootElement(name = "obtClientesResponse")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
public class ObtClientesResponse {

    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    protected ArrayOfAdminClienteType pass;

    /**
     * Gets the value of the pass property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAdminClienteType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized ArrayOfAdminClienteType getPass() {
        return pass;
    }

    /**
     * Sets the value of the pass property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAdminClienteType }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized void setPass(ArrayOfAdminClienteType value) {
        this.pass = value;
    }

    /**
     * Generates a String representation of the contents of this type.
     * This is an extension method, produced by the 'ts' xjc plugin
     * 
     */
    @Override
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized String toString() {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.MULTI_LINE_STYLE);
    }

}
