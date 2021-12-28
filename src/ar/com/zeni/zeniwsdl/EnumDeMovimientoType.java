
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumDeMovimientoType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumDeMovimientoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Pendiente"/>
 *     &lt;enumeration value="Movimiento"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumDeMovimientoType")
@XmlEnum
public enum EnumDeMovimientoType {

    @XmlEnumValue("Pendiente")
    PENDIENTE("Pendiente"),
    @XmlEnumValue("Movimiento")
    MOVIMIENTO("Movimiento");
    private final String value;

    EnumDeMovimientoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumDeMovimientoType fromValue(String v) {
        for (EnumDeMovimientoType c: EnumDeMovimientoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
