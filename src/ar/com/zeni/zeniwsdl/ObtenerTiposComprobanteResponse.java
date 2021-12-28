
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


// #1510

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "out"
})
@XmlRootElement(name = "obtenerTiposComprobanteResponse")
public class ObtenerTiposComprobanteResponse {

    @XmlElement(required = true)
    protected ArrayOfTipoComprobanteType out;

    public ArrayOfTipoComprobanteType getOut() {
        return out;
    }

    public void setOut(ArrayOfTipoComprobanteType value) {
        this.out = value;
    }

}
