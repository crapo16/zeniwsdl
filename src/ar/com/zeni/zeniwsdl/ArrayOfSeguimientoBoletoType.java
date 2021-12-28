
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// #1610

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSeguimientoBoletoType", propOrder = {
    "seguimientoboleto"
})
public class ArrayOfSeguimientoBoletoType {

    protected List<SeguimientoBoletoType> seguimientoboleto;

    public List<SeguimientoBoletoType> getSeguimientoBoleto() {
        if (seguimientoboleto == null) {
        	seguimientoboleto = new ArrayList<SeguimientoBoletoType>();
        }
        return this.seguimientoboleto;
    }

}
