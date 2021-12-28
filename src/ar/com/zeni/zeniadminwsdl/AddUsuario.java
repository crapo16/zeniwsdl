
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
 *         &lt;element name="clienteId" type="{http://www.zeni.com.ar/ZeniAdminWSDL/}IdType"/>
 *         &lt;element name="username" type="{http://www.zeni.com.ar/ZeniAdminWSDL/}DescripcionType"/>
 *         &lt;element name="name" type="{http://www.zeni.com.ar/ZeniAdminWSDL/}DescripcionType"/>
 *         &lt;element name="lastName" type="{http://www.zeni.com.ar/ZeniAdminWSDL/}DescripcionType"/>
 *         &lt;element name="email" type="{http://www.zeni.com.ar/ZeniAdminWSDL/}DescripcionType"/>
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
    "clienteId",
    "username",
    "name",
    "lastName",
    "email"
})
@XmlRootElement(name = "addUsuario")
@Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
public class AddUsuario {

    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    protected String clienteId;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    protected String username;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    protected String name;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    protected String lastName;
    @XmlElement(required = true)
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    protected String email;

    /**
     * Gets the value of the clienteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized String getClienteId() {
        return clienteId;
    }

    /**
     * Sets the value of the clienteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized void setClienteId(String value) {
        this.clienteId = value;
    }

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized void setEmail(String value) {
        this.email = value;
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
