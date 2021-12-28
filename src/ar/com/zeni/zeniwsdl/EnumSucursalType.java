
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumSucursalType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumSucursalType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BuenosAires"/>
 *     &lt;enumeration value="Rosario"/>
 *     &lt;enumeration value="Ambos"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumSucursalType")
@XmlEnum
public enum EnumSucursalType {

    @XmlEnumValue("BuenosAires")
    BUENOS_AIRES("BuenosAires"),
    @XmlEnumValue("Rosario")
    ROSARIO("Rosario"),
    @XmlEnumValue("Ambos")
    AMBOS("Ambos");
    private final String value;

    EnumSucursalType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumSucursalType fromValue(String v) {
        for (EnumSucursalType c: EnumSucursalType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
