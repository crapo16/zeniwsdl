
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumEstadoType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumEstadoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Confirmada"/>
 *     &lt;enumeration value="Oferta"/>
 *     &lt;enumeration value="Caratula"/>
 *     &lt;enumeration value="Todos"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumEstadoType")
@XmlEnum
public enum EnumEstadoType {

    @XmlEnumValue("Confirmada")
    CONFIRMADA("Confirmada"),
    @XmlEnumValue("Oferta")
    OFERTA("Oferta"),
    @XmlEnumValue("Caratula")
    CARATULA("Caratula"),
    @XmlEnumValue("Todos")
    TODOS("Todos");
    private final String value;

    EnumEstadoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumEstadoType fromValue(String v) {
        for (EnumEstadoType c: EnumEstadoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
