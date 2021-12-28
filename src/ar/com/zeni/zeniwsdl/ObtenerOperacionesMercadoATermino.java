
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "auth",
    "Cuentas",
    "TipoFiltro",
    "RangoDeFechas",
    "IdOperacion"
})
@XmlRootElement(name = "obtenerOperacionesMercadoATermino")
public class ObtenerOperacionesMercadoATermino {

    @XmlElement(required = true)
    protected AuthType auth;
    protected ArrayOfIdCuenta Cuentas;
    protected EnumTipoOperacionMercadoATerminoType TipoFiltro;
    protected RangoFechaType RangoDeFechas;
    protected String IdOperacion;


    public String getIdOperacion() {
		return IdOperacion;
	}

	public void setIdOperacion(String idOperacion) {
		this.IdOperacion = idOperacion;
	}

	public EnumTipoOperacionMercadoATerminoType getTipoFiltro() {
		return TipoFiltro;
	}

	public void setTipoFiltro(EnumTipoOperacionMercadoATerminoType tipoFiltro) {
		this.TipoFiltro = tipoFiltro;
	}

	/**
     * Gets the value of the auth property.
     * 
     * @return
     *     possible object is
     *     {@link AuthType }
     *     
     */
    public AuthType getAuth() {
        return auth;
    }

    /**
     * Sets the value of the auth property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthType }
     *     
     */
    public void setAuth(AuthType value) {
        this.auth = value;
    }

    /**
     * Gets the value of the cuenta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public ArrayOfIdCuenta getCuentas() {
        return Cuentas;
    }

    /**
     * Sets the value of the cuenta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentas(ArrayOfIdCuenta value) {
        this.Cuentas = value;
    }

    /**
     * Gets the value of the rangoDeFechas property.
     * 
     * @return
     *     possible object is
     *     {@link RangoFechaType }
     *     
     */
    public RangoFechaType getRangoDeFechas() {
        return RangoDeFechas;
    }

    /**
     * Sets the value of the rangoDeFechas property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangoFechaType }
     *     
     */
    public void setRangoDeFechas(RangoFechaType value) {
        this.RangoDeFechas = value;
    }

}
