
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumCompradorVendedorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumCompradorVendedorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Comprador"/>
 *     &lt;enumeration value="Vendedor"/>
 *     &lt;enumeration value="Ambos"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumCompradorVendedorType")
@XmlEnum
public enum EnumCompradorVendedorType {

    @XmlEnumValue("Comprador")
    COMPRADOR("Comprador"),
    @XmlEnumValue("Vendedor")
    VENDEDOR("Vendedor"),
    @XmlEnumValue("Ambos")
    AMBOS("Ambos");
    private final String value;

    EnumCompradorVendedorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumCompradorVendedorType fromValue(String v) {
        for (EnumCompradorVendedorType c: EnumCompradorVendedorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
