
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumTipoOperacionMercadoATerminoType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumTipoOperacionMercadoATerminoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RangoFechaApertura"/>
 *     &lt;enumeration value="RangoFechaActualizacion"/>
 *     &lt;enumeration value="Abiertas"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumTipoOperacionMercadoATerminoType")
@XmlEnum
public enum EnumTipoOperacionMercadoATerminoType {

    RANGOFECHAAPERTURA,
    RANGOFECHAACTUALIZACION,
    ABIERTAS;

    public String value() {
        return name();
    }

    public static EnumTipoOperacionMercadoATerminoType fromValue(String v) {
        return valueOf(v);
    }
}
