
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// #1589

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFacturaRegistroType", propOrder = {
    "facturaregistro"
})
public class ArrayOfFacturaRegistroType {

    protected List<FacturaRegistroType> facturaregistro;

    public List<FacturaRegistroType> getFacturaRegistro() {
        if (facturaregistro == null) {
        	facturaregistro = new ArrayList<FacturaRegistroType>();
        }
        return this.facturaregistro;
    }
}
