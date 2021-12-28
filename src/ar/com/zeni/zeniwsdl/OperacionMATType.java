
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperacionMATType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperacionMATType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="condicion" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="mercado" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="operador" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="tipo" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="producto" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="posicion" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="volumen" type="{http://www.w3.org/2001/XMLSchema}float"/>
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
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperacionMATType", propOrder = {
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
    "precioInicial"
})
public class OperacionMATType {

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
    protected float volumen;
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

    /**
     * Gets the value of the condicion property.
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
     * Sets the value of the condicion property.
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
     * Gets the value of the mercado property.
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
     * Sets the value of the mercado property.
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
     * Gets the value of the operador property.
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
     * Sets the value of the operador property.
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
     * Gets the value of the tipo property.
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
     * Sets the value of the tipo property.
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
     * Gets the value of the producto property.
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
     * Sets the value of the producto property.
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
     * Gets the value of the posicion property.
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
     * Sets the value of the posicion property.
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
     * Gets the value of the volumen property.
     * 
     */
    public float getVolumen() {
        return volumen;
    }

    /**
     * Sets the value of the volumen property.
     * 
     */
    public void setVolumen(float value) {
        this.volumen = value;
    }

    /**
     * Gets the value of the precioEjercicio property.
     * 
     */
    public float getPrecioEjercicio() {
        return precioEjercicio;
    }

    /**
     * Sets the value of the precioEjercicio property.
     * 
     */
    public void setPrecioEjercicio(float value) {
        this.precioEjercicio = value;
    }

    /**
     * Gets the value of the preciooprima property.
     * 
     */
    public float getPreciooprima() {
        return preciooprima;
    }

    /**
     * Sets the value of the preciooprima property.
     * 
     */
    public void setPreciooprima(float value) {
        this.preciooprima = value;
    }

    /**
     * Gets the value of the destino property.
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
     * Sets the value of the destino property.
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
     * Gets the value of the registro property.
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
     * Sets the value of the registro property.
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
     * Gets the value of the fechaAlta property.
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
     * Sets the value of the fechaAlta property.
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
     * Gets the value of the enContratos property.
     * 
     */
    public float getEnContratos() {
        return enContratos;
    }

    /**
     * Sets the value of the enContratos property.
     * 
     */
    public void setEnContratos(float value) {
        this.enContratos = value;
    }

    /**
     * Gets the value of the tipoOperacion property.
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
     * Sets the value of the tipoOperacion property.
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
     * Gets the value of the estado property.
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
     * Sets the value of the estado property.
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
     * Gets the value of the regCancela property.
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
     * Sets the value of the regCancela property.
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
     * Gets the value of the fechaInicial property.
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
     * Sets the value of the fechaInicial property.
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
     * Gets the value of the precioInicial property.
     * 
     */
    public float getPrecioInicial() {
        return precioInicial;
    }

    /**
     * Sets the value of the precioInicial property.
     * 
     */
    public void setPrecioInicial(float value) {
        this.precioInicial = value;
    }

}
