
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// #1589

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFacturaDetalleType", propOrder = {
    "facturadetalle"
})
public class ArrayOfFacturaDetalleType {

    protected List<FacturaDetalleType> facturadetalle;

    public List<FacturaDetalleType> getFacturaDetalle() {
        if (facturadetalle == null) {
        	facturadetalle = new ArrayList<FacturaDetalleType>();
        }
        return this.facturadetalle;
    }
}
