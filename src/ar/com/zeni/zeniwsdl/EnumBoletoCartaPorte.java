
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EnumBoletoCartaPorte.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumBoletoCartaPorte">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Boleto"/>
 *     &lt;enumeration value="CartaOferta"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumBoletoCartaPorte")
@XmlEnum
public enum EnumBoletoCartaPorte {

    @XmlEnumValue("Boleto")
    BOLETO("Boleto"),
    @XmlEnumValue("CartaOferta")
    CARTA_OFERTA("CartaOferta");
    private final String value;

    EnumBoletoCartaPorte(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnumBoletoCartaPorte fromValue(String v) {
        for (EnumBoletoCartaPorte c: EnumBoletoCartaPorte.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
