
package ar.com.zeni.zeniwsdl;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para OperacionFinancieraType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OperacionFinancieraType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="condicion" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="mercado" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="operador" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="tipo" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="producto" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="posicion" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="volumen" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="precio_ejercicio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="preciooprima" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="destino" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="registro" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="fecha_alta" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="en_contratos" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="tipo_operacion" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="estado" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="Reg_Cancela" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="Fecha_Inicial" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="Precio_Inicial" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Precio_Actual" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="Monto_Suscripto" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="suscripto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="moneda" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="fecha_vto" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="codigo_interno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="total_valorizado" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="fecha_operacion" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperacionFinancieraType", propOrder = {
    "condicion",
    "mercado",
    "operador",
    "tipo",
    "producto",
    "posicion",
    "volumen",
    "precioEjercicio",
    "preciooprima",
    "destino",
    "registro",
    "fechaAlta",
    "enContratos",
    "tipoOperacion",
    "estado",
    "regCancela",
    "fechaInicial",
    "precioInicial",
    "precioActual",
    "montoSuscripto",
    "suscripto",
    "moneda",
    "fechaVto",
    "codigoInterno",
    "totalValorizado",
    "fechaOperacion"
})
public class OperacionFinancieraType {

    @XmlElement(required = true)
    protected String condicion;
    @XmlElement(required = true)
    protected String mercado;
    @XmlElement(required = true)
    protected String operador;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    protected String producto;
    @XmlElement(required = true)
    protected String posicion;
    @XmlElement(required = true)
    protected BigDecimal volumen;
    @XmlElement(name = "precio_ejercicio")
    protected float precioEjercicio;
    protected float preciooprima;
    @XmlElement(required = true)
    protected String destino;
    @XmlElement(required = true)
    protected String registro;
    @XmlElement(name = "fecha_alta", required = true)
    protected FechaTimeType fechaAlta;
    @XmlElement(name = "en_contratos")
    protected float enContratos;
    @XmlElement(name = "tipo_operacion", required = true)
    protected String tipoOperacion;
    @XmlElement(required = true)
    protected String estado;
    @XmlElement(name = "Reg_Cancela", required = true)
    protected String regCancela;
    @XmlElement(name = "Fecha_Inicial", required = true)
    protected FechaTimeType fechaInicial;
    @XmlElement(name = "Precio_Inicial")
    protected float precioInicial;
    @XmlElement(name = "Precio_Actual")
    protected float precioActual;
    @XmlElement(name = "Monto_Suscripto", required = true)
    protected BigDecimal montoSuscripto;
    @XmlElement(required = true)
    protected String suscripto;
    @XmlElement(required = true)
    protected String moneda;
    @XmlElement(name = "fecha_vto", required = true)
    protected FechaTimeType fechaVto;
    @XmlElement(name = "codigo_interno", required = true)
    protected String codigoInterno;
    @XmlElement(name = "total_valorizado", required = true)
    protected BigDecimal totalValorizado;
    @XmlElement(name = "fecha_operacion", required = true)
    protected FechaTimeType fechaOperacion;

    /**
     * Obtiene el valor de la propiedad condicion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondicion() {
        return condicion;
    }

    /**
     * Define el valor de la propiedad condicion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondicion(String value) {
        this.condicion = value;
    }

    /**
     * Obtiene el valor de la propiedad mercado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMercado() {
        return mercado;
    }

    /**
     * Define el valor de la propiedad mercado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMercado(String value) {
        this.mercado = value;
    }

    /**
     * Obtiene el valor de la propiedad operador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperador() {
        return operador;
    }

    /**
     * Define el valor de la propiedad operador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperador(String value) {
        this.operador = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Obtiene el valor de la propiedad producto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducto() {
        return producto;
    }

    /**
     * Define el valor de la propiedad producto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducto(String value) {
        this.producto = value;
    }

    /**
     * Obtiene el valor de la propiedad posicion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosicion() {
        return posicion;
    }

    /**
     * Define el valor de la propiedad posicion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosicion(String value) {
        this.posicion = value;
    }

    /**
     * Obtiene el valor de la propiedad volumen.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVolumen() {
        return volumen;
    }

    /**
     * Define el valor de la propiedad volumen.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVolumen(BigDecimal value) {
        this.volumen = value;
    }

    /**
     * Obtiene el valor de la propiedad precioEjercicio.
     * 
     */
    public float getPrecioEjercicio() {
        return precioEjercicio;
    }

    /**
     * Define el valor de la propiedad precioEjercicio.
     * 
     */
    public void setPrecioEjercicio(float value) {
        this.precioEjercicio = value;
    }

    /**
     * Obtiene el valor de la propiedad preciooprima.
     * 
     */
    public float getPreciooprima() {
        return preciooprima;
    }

    /**
     * Define el valor de la propiedad preciooprima.
     * 
     */
    public void setPreciooprima(float value) {
        this.preciooprima = value;
    }

    /**
     * Obtiene el valor de la propiedad destino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Define el valor de la propiedad destino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestino(String value) {
        this.destino = value;
    }

    /**
     * Obtiene el valor de la propiedad registro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistro() {
        return registro;
    }

    /**
     * Define el valor de la propiedad registro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistro(String value) {
        this.registro = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAlta.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaAlta(FechaTimeType value) {
        this.fechaAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad enContratos.
     * 
     */
    public float getEnContratos() {
        return enContratos;
    }

    /**
     * Define el valor de la propiedad enContratos.
     * 
     */
    public void setEnContratos(float value) {
        this.enContratos = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoOperacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoOperacion() {
        return tipoOperacion;
    }

    /**
     * Define el valor de la propiedad tipoOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoOperacion(String value) {
        this.tipoOperacion = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad regCancela.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegCancela() {
        return regCancela;
    }

    /**
     * Define el valor de la propiedad regCancela.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegCancela(String value) {
        this.regCancela = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicial.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaInicial() {
        return fechaInicial;
    }

    /**
     * Define el valor de la propiedad fechaInicial.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaInicial(FechaTimeType value) {
        this.fechaInicial = value;
    }

    /**
     * Obtiene el valor de la propiedad precioInicial.
     * 
     */
    public float getPrecioInicial() {
        return precioInicial;
    }

    /**
     * Define el valor de la propiedad precioInicial.
     * 
     */
    public void setPrecioInicial(float value) {
        this.precioInicial = value;
    }

    /**
     * Obtiene el valor de la propiedad precioActual.
     * 
     */
    public float getPrecioActual() {
        return precioActual;
    }

    /**
     * Define el valor de la propiedad precioActual.
     * 
     */
    public void setPrecioActual(float value) {
        this.precioActual = value;
    }

    /**
     * Obtiene el valor de la propiedad montoSuscripto.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMontoSuscripto() {
        return montoSuscripto;
    }

    /**
     * Define el valor de la propiedad montoSuscripto.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMontoSuscripto(BigDecimal value) {
        this.montoSuscripto = value;
    }

    /**
     * Obtiene el valor de la propiedad suscripto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuscripto() {
        return suscripto;
    }

    /**
     * Define el valor de la propiedad suscripto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuscripto(String value) {
        this.suscripto = value;
    }

    /**
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMoneda(String value) {
        this.moneda = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaVto.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaVto() {
        return fechaVto;
    }

    /**
     * Define el valor de la propiedad fechaVto.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaVto(FechaTimeType value) {
        this.fechaVto = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoInterno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoInterno() {
        return codigoInterno;
    }

    /**
     * Define el valor de la propiedad codigoInterno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoInterno(String value) {
        this.codigoInterno = value;
    }

    /**
     * Obtiene el valor de la propiedad totalValorizado.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalValorizado() {
        return totalValorizado;
    }

    /**
     * Define el valor de la propiedad totalValorizado.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalValorizado(BigDecimal value) {
        this.totalValorizado = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaOperacion.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFechaOperacion() {
        return fechaOperacion;
    }

    /**
     * Define el valor de la propiedad fechaOperacion.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFechaOperacion(FechaTimeType value) {
        this.fechaOperacion = value;
    }

}
