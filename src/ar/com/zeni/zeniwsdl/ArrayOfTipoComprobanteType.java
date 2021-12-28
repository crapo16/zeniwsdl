
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// #1510

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTipoComprobanteType", propOrder = {
    "tiposcomprobante"
})
public class ArrayOfTipoComprobanteType {

    protected List<TipoComprobanteType> tiposcomprobante;

    public List<TipoComprobanteType> getTiposcomprobante() {
        if (tiposcomprobante == null) {
        	tiposcomprobante = new ArrayList<TipoComprobanteType>();
        }
        return this.tiposcomprobante;
    }

}
