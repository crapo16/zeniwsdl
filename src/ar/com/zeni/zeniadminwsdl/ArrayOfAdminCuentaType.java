
package ar.com.zeni.zeniadminwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.xjc.runtime.JAXBToStringStyle;


/**
 * <p>Java class for ArrayOfAdminCuentaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAdminCuentaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuentas" type="{http://www.zeni.com.ar/ZeniAdminWSDL/}AdminCuentaType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAdminCuentaType", propOrder = {
    "cuentas"
})
@Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
public class ArrayOfAdminCuentaType {

    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    protected List<AdminCuentaType> cuentas;

    /**
     * Gets the value of the cuentas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cuentas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCuentas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdminCuentaType }
     * 
     * 
     */
    @Generated(value = "com.sun.tools.xjc.Driver", date = "2012-11-23T03:17:13-03:00", comments = "JAXB RI v2.2.5-2")
    public synchronized List<AdminCuentaType> getCuentas() {
        if (cuentas == null) {
            cuentas = new ArrayList<AdminCuentaType>();
        }
        return this.cuentas;
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
