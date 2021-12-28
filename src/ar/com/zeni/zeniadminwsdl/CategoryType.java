
package ar.com.zeni.zeniadminwsdl;

import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>Java class for CategoryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CategoryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.zeni.com.ar/ZeniAdminWSDL/}IdType"/>
 *         &lt;element name="desc" type="{http://www.zeni.com.ar/ZeniAdminWSDL/}DescripcionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CategoryType", propOrder = {
    "id",
    "desc"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
public class CategoryType {

    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    protected String id;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    protected String desc;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized String getId() {
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
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized void setDesc(String value) {
        this.desc = value;
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
