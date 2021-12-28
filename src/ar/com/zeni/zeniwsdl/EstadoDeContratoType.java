
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo que contiene el estado de un contrato.
 * 					
 * 
 * <p>Clase Java para EstadoDeContratoType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EstadoDeContratoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cuentaId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="cuenta" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contratoId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="contratId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="productoDescripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actividad" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="moneda" type="{http://www.zeni.com.ar/ZeniWSDL/}MonedaType"/>
 *         &lt;element name="precio" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="pendienteEntrega" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="pendienteFijacion" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="vencimientoEntrega" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="vencimientoFijacion" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="condicionDePago" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="puestoEn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comprador" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estadoDestino" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EstadoDeContratoType", propOrder = {
    "cuentaId",
    "cuenta",
    "contrato",
    "contratoId",
    "contratId",
    "productoDescripcion",
    "actividad",
    "moneda",
    "precio",
    "cantidad",
    "pendienteEntrega",
    "pendienteFijacion",
    "vencimientoEntrega",
    "vencimientoFijacion",
    "condicionDePago",
    "puestoEn",
    "comprador",
    "estadoDestino"
})
public class EstadoDeContratoType {

    @XmlElement(required = true)
    protected String cuentaId;
    @XmlElement(required = true)
    protected String cuenta;
    @XmlElement(required = true)
    protected String contrato;
    @XmlElement(required = true)
    protected String contratoId;
    @XmlElement(required = true)
    protected String contratId;
    @XmlElement(required = true)
    protected String productoDescripcion;
    @XmlElement(required = true)
    protected String actividad;
    @XmlElement(required = true)
    protected MonedaType moneda;
    protected double precio;
    protected float cantidad;
    protected float pendienteEntrega;
    protected float pendienteFijacion;
    @XmlElement(required = true)
    protected FechaTimeType vencimientoEntrega;
    @XmlElement(required = true)
    protected FechaTimeType vencimientoFijacion;
    @XmlElement(required = true)
    protected String condicionDePago;
    @XmlElement(required = true)
    protected String puestoEn;
    @XmlElement(required = true)
    protected String comprador;
    @XmlElement(required = true)
    protected String estadoDestino;

    /**
     * Obtiene el valor de la propiedad cuentaId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaId() {
        return cuentaId;
    }

    /**
     * Define el valor de la propiedad cuentaId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaId(String value) {
        this.cuentaId = value;
    }

    /**
     * Obtiene el valor de la propiedad cuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * Define el valor de la propiedad cuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuenta(String value) {
        this.cuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad contrato.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrato() {
        return contrato;
    }

    /**
     * Define el valor de la propiedad contrato.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrato(String value) {
        this.contrato = value;
    }

    /**
     * Obtiene el valor de la propiedad contratoId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratoId() {
        return contratoId;
    }

    /**
     * Define el valor de la propiedad contratoId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratoId(String value) {
        this.contratoId = value;
    }

    /**
     * Obtiene el valor de la propiedad contratId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContratId() {
        return contratId;
    }

    /**
     * Define el valor de la propiedad contratId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContratId(String value) {
        this.contratId = value;
    }

    /**
     * Obtiene el valor de la propiedad productoDescripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductoDescripcion() {
        return productoDescripcion;
    }

    /**
     * Define el valor de la propiedad productoDescripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductoDescripcion(String value) {
        this.productoDescripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad actividad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActividad() {
        return actividad;
    }

    /**
     * Define el valor de la propiedad actividad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActividad(String value) {
        this.actividad = value;
    }

    /**
     * Obtiene el valor de la propiedad moneda.
     * 
     * @return
     *     possible object is
     *     {@link MonedaType }
     *     
     */
    public MonedaType getMoneda() {
        return moneda;
    }

    /**
     * Define el valor de la propiedad moneda.
     * 
     * @param value
     *     allowed object is
     *     {@link MonedaType }
     *     
     */
    public void setMoneda(MonedaType value) {
        this.moneda = value;
    }

    /**
     * Obtiene el valor de la propiedad precio.
     * 
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Define el valor de la propiedad precio.
     * 
     */
    public void setPrecio(double value) {
        this.precio = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidad.
     * 
     */
    public float getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     */
    public void setCantidad(float value) {
        this.cantidad = value;
    }

    /**
     * Obtiene el valor de la propiedad pendienteEntrega.
     * 
     */
    public float getPendienteEntrega() {
        return pendienteEntrega;
    }

    /**
     * Define el valor de la propiedad pendienteEntrega.
     * 
     */
    public void setPendienteEntrega(float value) {
        this.pendienteEntrega = value;
    }

    /**
     * Obtiene el valor de la propiedad pendienteFijacion.
     * 
     */
    public float getPendienteFijacion() {
        return pendienteFijacion;
    }

    /**
     * Define el valor de la propiedad pendienteFijacion.
     * 
     */
    public void setPendienteFijacion(float value) {
        this.pendienteFijacion = value;
    }

    /**
     * Obtiene el valor de la propiedad vencimientoEntrega.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getVencimientoEntrega() {
        return vencimientoEntrega;
    }

    /**
     * Define el valor de la propiedad vencimientoEntrega.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setVencimientoEntrega(FechaTimeType value) {
        this.vencimientoEntrega = value;
    }

    /**
     * Obtiene el valor de la propiedad vencimientoFijacion.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getVencimientoFijacion() {
        return vencimientoFijacion;
    }

    /**
     * Define el valor de la propiedad vencimientoFijacion.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setVencimientoFijacion(FechaTimeType value) {
        this.vencimientoFijacion = value;
    }

    /**
     * Obtiene el valor de la propiedad condicionDePago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCondicionDePago() {
        return condicionDePago;
    }

    /**
     * Define el valor de la propiedad condicionDePago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCondicionDePago(String value) {
        this.condicionDePago = value;
    }

    /**
     * Obtiene el valor de la propiedad puestoEn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPuestoEn() {
        return puestoEn;
    }

    /**
     * Define el valor de la propiedad puestoEn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPuestoEn(String value) {
        this.puestoEn = value;
    }

    /**
     * Obtiene el valor de la propiedad comprador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComprador() {
        return comprador;
    }

    /**
     * Define el valor de la propiedad comprador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComprador(String value) {
        this.comprador = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoDestino.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoDestino() {
        return estadoDestino;
    }

    /**
     * Define el valor de la propiedad estadoDestino.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoDestino(String value) {
        this.estadoDestino = value;
    }

}
