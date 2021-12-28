
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumTipoOperacionMatType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumTipoOperacionMatType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Futuro"/>
 *     &lt;enumeration value="Opciones"/>
 *     &lt;enumeration value="Ambos"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumTipoOperacionMatType")
@XmlEnum
public enum EnumTipoOperacionMatType {

    @XmlEnumValue("Futuro")
    FUTURO("Futuro"),
    @XmlEnumValue("Opciones")
    OPCIONES("Opciones"),
    @XmlEnumValue("Ambos")
    AMBOS("Ambos");
    private final String value;

    EnumTipoOperacionMatType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoOperacionMatType fromValue(String v) {
        for (EnumTipoOperacionMatType c: EnumTipoOperacionMatType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
