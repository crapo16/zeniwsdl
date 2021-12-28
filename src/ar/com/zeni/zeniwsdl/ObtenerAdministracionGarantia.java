package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

// #1666

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "auth", "Cuentas", "Mercados", "Productos",
		"Moneda" })
@XmlRootElement(name = "obtenerAdministracionGarantia")
public class ObtenerAdministracionGarantia {

	@XmlElement(required = true)
	protected AuthType auth;
	@XmlElement(required = true)
	protected ArrayOfIdCuenta Cuentas;
	protected ArrayOfMercadoType Mercados;
	protected ArrayOfProductoMATType Productos;
	protected MonedaType Moneda;

	public AuthType getAuth() {
		return auth;
	}

	public void setAuth(AuthType auth) {
		this.auth = auth;
	}

	public ArrayOfIdCuenta getCuentas() {
		return Cuentas;
	}

	public void setCuentas(ArrayOfIdCuenta cuentas) {
		Cuentas = cuentas;
	}

	public ArrayOfMercadoType getMercados() {
		return Mercados;
	}

	public void setMercados(ArrayOfMercadoType mercados) {
		Mercados = mercados;
	}

	public ArrayOfProductoMATType getProductos() {
		return Productos;
	}

	public void setProductos(ArrayOfProductoMATType productos) {
		Productos = productos;
	}

	public MonedaType getMoneda() {
		return Moneda;
	}

	public void setMoneda(MonedaType moneda) {
		Moneda = moneda;
	}
}
