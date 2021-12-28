
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

// #1510

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipoComprobanteType", propOrder = {
	"id",	
    "nombre",
    "abreviatura"
})

public class TipoComprobanteType {

    @XmlElement(name = "ID")
    protected int id;
    @XmlElement(name = "NOMBRE")
    protected String nombre;
    @XmlElement(name = "ABREVIATURA")
    protected String abreviatura;
    
    public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getNOMBRE() {
		return nombre;
	}
	public void setNOMBRE(String nombre) {
		this.nombre = nombre;
	}
	public String getABREVIATURA() {
		return abreviatura;
	}
	public void setABREVIATURA(String abreviatura) {
		this.abreviatura = abreviatura;
	}

}
