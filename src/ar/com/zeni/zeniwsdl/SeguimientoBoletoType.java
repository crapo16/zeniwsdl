
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

// #1610

/**
 * <p>Java class for SeguimientoBoletoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CartaDePorteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numeroContrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeguimientoBoletoType", propOrder = {
    "fecha",
    "estado",
    "usuario",
    "observacion"
})
public class SeguimientoBoletoType {

    @XmlElement(required = true)
    protected FechaTimeType fecha;
    @XmlElement(required = true)
    protected String estado;
    @XmlElement(required = true)
    protected String usuario;
    @XmlElement(required = true)
    protected String observacion;
    
	public FechaTimeType getFecha() {
		return fecha;
	}
	public void setFecha(FechaTimeType fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

    

}
