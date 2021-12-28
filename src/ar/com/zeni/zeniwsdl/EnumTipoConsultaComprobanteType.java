
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumTipoConsultaComprobanteType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumTipoConsultaComprobanteType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DIA"/>
 *     &lt;enumeration value="MES"/>
 *     &lt;enumeration value="EMITIDO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumTipoConsultaComprobanteType")
@XmlEnum
public enum EnumTipoConsultaComprobanteType {

    DIA,
    MES,
    EMITIDO;

    public String value() {
        return name();
    }

    public static EnumTipoConsultaComprobanteType fromValue(String v) {
        return valueOf(v);
    }

}
