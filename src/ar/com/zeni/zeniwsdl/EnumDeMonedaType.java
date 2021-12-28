
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumDeMonedaType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumDeMonedaType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Pesos"/>
 *     &lt;enumeration value="Dolares"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumDeMonedaType")
@XmlEnum
public enum EnumDeMonedaType {

    @XmlEnumValue("Pesos")
    PESOS("Pesos"),
    @XmlEnumValue("Dolares")
    DOLARES("Dolares");
    private final String value;

    EnumDeMonedaType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumDeMonedaType fromValue(String v) {
        for (EnumDeMonedaType c: EnumDeMonedaType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
