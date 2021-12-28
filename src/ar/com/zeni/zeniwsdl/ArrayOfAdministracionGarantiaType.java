
package ar.com.zeni.zeniwsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

// #1666

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAdministracionGarantiaType", propOrder = {
    "administraciongarantia"
})
public class ArrayOfAdministracionGarantiaType {

    protected List<AdministracionGarantiaType> administraciongarantia;
    public List<AdministracionGarantiaType> getAdministracionGarantia() {
        if (administraciongarantia == null) {
        	administraciongarantia = new ArrayList<AdministracionGarantiaType>();
        }
        return this.administraciongarantia;
    }

}
