
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// #1666

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "out"
})
@XmlRootElement(name = "obtenerAdministracionGarantiaResponse")
public class ObtenerAdministracionGarantiaResponse {

    @XmlElement(required = true)
    protected ArrayOfAdministracionGarantiaType out;

    public ArrayOfAdministracionGarantiaType getOut() {
        return out;
    }

    public void setOut(ArrayOfAdministracionGarantiaType value) {
        this.out = value;
    }

}
