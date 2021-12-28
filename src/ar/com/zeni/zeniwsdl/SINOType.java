
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SINOType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SINOType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SI"/>
 *     &lt;enumeration value="NO"/>
 *     &lt;enumeration value="INDISTINTO"/>
 *     &lt;enumeration value="DEFAULT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SINOType")
@XmlEnum
public enum SINOType {

    SI,
    NO,
    INDISTINTO,
    DEFAULT;

    public String value() {
        return name();
    }

    public static SINOType fromValue(String v) {
        return valueOf(v);
    }

}
