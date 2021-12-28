
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumTipoOperacionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumTipoOperacionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Abiertas"/>
 *     &lt;enumeration value="Estadistico"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumTipoOperacionType")
@XmlEnum
public enum EnumTipoOperacionType {

    @XmlEnumValue("Abiertas")
    ABIERTAS("Abiertas"),
    @XmlEnumValue("Estadistico")
    ESTADISTICO("Estadistico");
    private final String value;

    EnumTipoOperacionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoOperacionType fromValue(String v) {
        for (EnumTipoOperacionType c: EnumTipoOperacionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
