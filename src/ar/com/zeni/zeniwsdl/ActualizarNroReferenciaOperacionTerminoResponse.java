
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "out"
})
@XmlRootElement(name = "actualizarNroReferenciaOperacionTerminoResponse")
public class ActualizarNroReferenciaOperacionTerminoResponse {

    @XmlElement(required = true)
    protected SINOType out;

    public SINOType getOut() {
        return out;
    }

    /**
     */
    public void setOut(SINOType value) {
        this.out = value;
    }

}
