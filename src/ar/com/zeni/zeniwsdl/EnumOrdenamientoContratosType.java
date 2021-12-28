
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumOrdenamientoContratosType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumOrdenamientoContratosType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Fecha"/>
 *     &lt;enumeration value="Numero"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumOrdenamientoContratosType")
@XmlEnum
public enum EnumOrdenamientoContratosType {

    @XmlEnumValue("Fecha")
    FECHA("Fecha"),
    @XmlEnumValue("Numero")
    NUMERO("Numero");
    private final String value;

    EnumOrdenamientoContratosType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumOrdenamientoContratosType fromValue(String v) {
        for (EnumOrdenamientoContratosType c: EnumOrdenamientoContratosType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
