
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EnumTipoConsultaType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumTipoConsultaType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Resumen"/>
 *     &lt;enumeration value="Operacion"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumTipoConsultaType")
@XmlEnum
public enum EnumTipoConsultaType {

    @XmlEnumValue("Resumen")
    RESUMEN("Resumen"),
    @XmlEnumValue("Operacion")
    OPERACION("Operacion");
    private final String value;

    EnumTipoConsultaType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumTipoConsultaType fromValue(String v) {
        for (EnumTipoConsultaType c: EnumTipoConsultaType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
