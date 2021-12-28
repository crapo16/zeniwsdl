package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

// #1666

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdministracionGarantiaType")
public class AdministracionGarantiaType {
	@XmlElement(name = "id")
	protected int id;
	@XmlElement(name = "cuentaclienteid")
	protected int cuentaclienteid;
	@XmlElement(name = "nrocuenta")
	protected String nrocuenta;
	@XmlElement(name = "denominacioncuenta")
	protected String denominacioncuenta;
	@XmlElement(name = "producto")
	protected String producto;
	@XmlElement(name = "cantidad")
	protected int cantidad;
	@XmlElement(name = "precio")
	protected float precio;
	@XmlElement(name = "aforo")
	protected float aforo;
	@XmlElement(name = "valor")
	protected float valor;
	@XmlElement(name = "mercado")
	protected String mercado;
	@XmlElement(name = "moneda")
	protected String moneda;
	@XmlElement(name = "simbolo")
	protected String simbolo;
	@XmlElement(name = "ultimaliq")
	protected float ultimaliq;
	@XmlElement(name = "requisitogarantia")
	protected float requisitogarantia;
	

	public float getRequisitogarantia() {
		return requisitogarantia;
	}

	public void setRequisitogarantia(float requisitogarantia) {
		this.requisitogarantia = requisitogarantia;
	}

	public float getUltimaliq() {
		return ultimaliq;
	}

	public void setUltimaliq(float ultimaliq) {
		this.ultimaliq = ultimaliq;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCuentaclienteid() {
		return cuentaclienteid;
	}

	public void setCuentaclienteid(int cuentaclienteid) {
		this.cuentaclienteid = cuentaclienteid;
	}

	public String getNrocuenta() {
		return nrocuenta;
	}

	public void setNrocuenta(String nrocuenta) {
		this.nrocuenta = nrocuenta;
	}

	public String getDenominacioncuenta() {
		return denominacioncuenta;
	}

	public void setDenominacioncuenta(String denominacioncuenta) {
		this.denominacioncuenta = denominacioncuenta;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getAforo() {
		return aforo;
	}

	public void setAforo(float aforo) {
		this.aforo = aforo;
	}

	public String getMercado() {
		return mercado;
	}

	public void setMercado(String mercado) {
		this.mercado = mercado;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public void setValor(float v) {
		this.valor = v;
	}
	
	public float getValor() {
		return this.valor;
	}
	
}
