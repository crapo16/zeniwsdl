
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnumCompraVentaType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumCompraVentaType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Compra"/>
 *     &lt;enumeration value="Venta"/>
 *     &lt;enumeration value="Ambos"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumCompraVentaType")
@XmlEnum
public enum EnumCompraVentaType {

    @XmlEnumValue("Compra")
    COMPRA("Compra"),
    @XmlEnumValue("Venta")
    VENTA("Venta"),
    @XmlEnumValue("Ambos")
    AMBOS("Ambos");
    private final String value;

    EnumCompraVentaType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumCompraVentaType fromValue(String v) {
        for (EnumCompraVentaType c: EnumCompraVentaType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
