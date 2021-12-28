
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumOperacionesType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumOperacionesType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Parciales"/>
 *     &lt;enumeration value="Finales"/>
 *     &lt;enumeration value="Comisiones"/>
 *     &lt;enumeration value="Todas"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumOperacionesType")
@XmlEnum
public enum EnumOperacionesType {

    @XmlEnumValue("Parciales")
    PARCIALES("Parciales"),
    @XmlEnumValue("Finales")
    FINALES("Finales"),
    @XmlEnumValue("Comisiones")
    COMISIONES("Comisiones"),
    @XmlEnumValue("Todas")
    TODAS("Todas");
    private final String value;

    EnumOperacionesType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumOperacionesType fromValue(String v) {
        for (EnumOperacionesType c: EnumOperacionesType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
