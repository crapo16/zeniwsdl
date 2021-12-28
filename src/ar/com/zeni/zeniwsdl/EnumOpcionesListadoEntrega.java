
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumOpcionesListadoEntrega.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumOpcionesListadoEntrega">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CONTRATO_A_FIJAR"/>
 *     &lt;enumeration value="SIN_CONTRATO_A_FIJAR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumOpcionesListadoEntrega")
@XmlEnum
public enum EnumOpcionesListadoEntrega {

    CONTRATO_A_FIJAR,
    SIN_CONTRATO_A_FIJAR;

    public String value() {
        return name();
    }

    public static EnumOpcionesListadoEntrega fromValue(String v) {
        return valueOf(v);
    }

}
