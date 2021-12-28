
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumIncluirType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumIncluirType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FEntregar"/>
 *     &lt;enumeration value="SLFinal"/>
 *     &lt;enumeration value="Estadistico"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumIncluirType")
@XmlEnum
public enum EnumIncluirType {

    @XmlEnumValue("FEntregar")
    F_ENTREGAR("FEntregar"),
    @XmlEnumValue("SLFinal")
    SL_FINAL("SLFinal"),
    @XmlEnumValue("Estadistico")
    ESTADISTICO("Estadistico");
    private final String value;

    EnumIncluirType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumIncluirType fromValue(String v) {
        for (EnumIncluirType c: EnumIncluirType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
