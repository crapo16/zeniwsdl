
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumTipoComprobanteType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumTipoComprobanteType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Completo"/>
 *     &lt;enumeration value="LFyLP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumTipoComprobanteType")
@XmlEnum
public enum EnumTipoComprobanteType {

    @XmlEnumValue("Completo")
    COMPLETO("Completo"),
    @XmlEnumValue("LFyLP")
    L_FY_LP("LFyLP");
    private final String value;

    EnumTipoComprobanteType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoComprobanteType fromValue(String v) {
        for (EnumTipoComprobanteType c: EnumTipoComprobanteType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
