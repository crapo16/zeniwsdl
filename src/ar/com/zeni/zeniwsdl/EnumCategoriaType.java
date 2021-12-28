
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumCategoriaType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumCategoriaType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CONTRATO"/>
 *     &lt;enumeration value="REPORTE"/>
 *     &lt;enumeration value="FACTURA"/>
 *     &lt;enumeration value="COMPROBANTE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumCategoriaType")
@XmlEnum
public enum EnumCategoriaType {

    CONTRATO,
    REPORTE,
    FACTURA,
    COMPROBANTE;

    public String value() {
        return name();
    }

    public static EnumCategoriaType fromValue(String v) {
        return valueOf(v);
    }

}
