
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Tipo de opratoria.
 * 
 * <p>Java class for OperatoriaDListadoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperatoriaDListadoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contrato" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contratoId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="contratId" type="{http://www.zeni.com.ar/ZeniWSDL/}IdType"/>
 *         &lt;element name="fechaAlta" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="contraparte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productoDescripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cPago" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="moneda" type="{http://www.zeni.com.ar/ZeniWSDL/}MonedaType"/>
 *         &lt;element name="precio" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="plazoFijacionDesde" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="plazoFijacionHasta" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="periodoEntreDesde" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="periodoEntreHasta" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="cantidadPactada" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="entregado" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="pendientaEntrega" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="kgFijados" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="pendienteFijacion" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="kgFacturados" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="importeFacturado" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="importeCobradoPesos" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="pendienteDeCobroEnPesos" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="kgPendienteDeFacturar" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="importeEstimadoPesosPendienteFacturar" type="{http://www.zeni.com.ar/ZeniWSDL/}ImporteType"/>
 *         &lt;element name="finalesFacturadasCobradas" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="finalesFacturadasPendienteCobro" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="kgPendienteFacturarPorFinal" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperatoriaDListadoType", propOrder = {
    "contrato",
    "contratoId",
    "contratId",
    "fechaAlta",
    "contraparte",
    "productoDescripcion",
    "cPago",
    "moneda",
    "precio",
    "plazoFijacionDesde",
    "plazoFijacionHasta",
    "periodoEntreDesde",
    "periodoEntreHasta",
    "cantidadPactada",
    "entregado",
    "pendientaEntrega",
    "kgFijados",
    "pendienteFijacion",
    "kgFacturados",
    "importeFacturado",
    "importeCobradoPesos",
    "pendienteDeCobroEnPesos",
    "kgPendienteDeFacturar",
    "importeEstimadoPesosPendienteFacturar",
    "finalesFacturadasCobradas",
    "finalesFacturadasPendienteCobro",
    "kgPendienteFacturarPorFinal"
})
public class OperatoriaDListadoType {

    @XmlElement(required = true)
    protected String contrato;
    @XmlElement(required = true)
    protected String contratoId;
    @XmlElement(required = true)
    protected String contratId;
    @XmlElement(required = true)
    protected FechaTimeType fechaAlta;
    @XmlElement(required = true)
    protected String contraparte;
    @XmlElement(required = true)
    protected String productoDescripcion;
    @XmlElement(required = true)
    protected String cPago;
    @XmlElement(required = true)
    protected MonedaType moneda;
    protected double precio;
    @XmlElement(required = true)
    protected FechaTimeType plazoFijacionDesde;
    @XmlElement(required = true)
    protected FechaTimeType plazoFijacionHasta;
    @XmlElement(required = true)
    protected FechaTimeType periodoEntreDesde;
    @XmlElement(required = true)
    protected FechaTimeType periodoEntreHasta;
    protected float cantidadPactada;
    protected float entregado;
    protected float pendientaEntrega;
    protected float kgFijados;
    protected float pendienteFijacion;
    protected float kgFacturados;
    protected double importeFacturado;
    protected double importeCobradoPesos;
    protected double pendienteDeCobroEnPesos;
    protected float kgPendienteDeFacturar;
    protected double importeEstimadoPesosPendienteFacturar;
    protected float finalesFacturadasCobradas;
    protected float finalesFacturadasPendienteCobro;
    protected float kgPendienteFacturarPorFinal;

    /**
     * Gets the value of the contrato property.
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
     * Sets the value of the contrato property.
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
     * Gets the value of the contratoId property.
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
     * Sets the value of the contratoId property.
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
     * Gets the value of the contratId property.
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
     * Sets the value of the contratId property.
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
     * Gets the value of the contraparte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContraparte() {
        return contraparte;
    }

    /**
     * Sets the value of the contraparte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContraparte(String value) {
        this.contraparte = value;
    }

    /**
     * Gets the value of the productoDescripcion property.
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
     * Sets the value of the productoDescripcion property.
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
     * Gets the value of the cPago property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCPago() {
        return cPago;
    }

    /**
     * Sets the value of the cPago property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCPago(String value) {
        this.cPago = value;
    }

    /**
     * Gets the value of the moneda property.
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
     * Sets the value of the moneda property.
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
     * Gets the value of the precio property.
     * 
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Sets the value of the precio property.
     * 
     */
    public void setPrecio(double value) {
        this.precio = value;
    }

    /**
     * Gets the value of the plazoFijacionDesde property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getPlazoFijacionDesde() {
        return plazoFijacionDesde;
    }

    /**
     * Sets the value of the plazoFijacionDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setPlazoFijacionDesde(FechaTimeType value) {
        this.plazoFijacionDesde = value;
    }

    /**
     * Gets the value of the plazoFijacionHasta property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getPlazoFijacionHasta() {
        return plazoFijacionHasta;
    }

    /**
     * Sets the value of the plazoFijacionHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setPlazoFijacionHasta(FechaTimeType value) {
        this.plazoFijacionHasta = value;
    }

    /**
     * Gets the value of the periodoEntreDesde property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getPeriodoEntreDesde() {
        return periodoEntreDesde;
    }

    /**
     * Sets the value of the periodoEntreDesde property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setPeriodoEntreDesde(FechaTimeType value) {
        this.periodoEntreDesde = value;
    }

    /**
     * Gets the value of the periodoEntreHasta property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getPeriodoEntreHasta() {
        return periodoEntreHasta;
    }

    /**
     * Sets the value of the periodoEntreHasta property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setPeriodoEntreHasta(FechaTimeType value) {
        this.periodoEntreHasta = value;
    }

    /**
     * Gets the value of the cantidadPactada property.
     * 
     */
    public float getCantidadPactada() {
        return cantidadPactada;
    }

    /**
     * Sets the value of the cantidadPactada property.
     * 
     */
    public void setCantidadPactada(float value) {
        this.cantidadPactada = value;
    }

    /**
     * Gets the value of the entregado property.
     * 
     */
    public float getEntregado() {
        return entregado;
    }

    /**
     * Sets the value of the entregado property.
     * 
     */
    public void setEntregado(float value) {
        this.entregado = value;
    }

    /**
     * Gets the value of the pendientaEntrega property.
     * 
     */
    public float getPendientaEntrega() {
        return pendientaEntrega;
    }

    /**
     * Sets the value of the pendientaEntrega property.
     * 
     */
    public void setPendientaEntrega(float value) {
        this.pendientaEntrega = value;
    }

    /**
     * Gets the value of the kgFijados property.
     * 
     */
    public float getKgFijados() {
        return kgFijados;
    }

    /**
     * Sets the value of the kgFijados property.
     * 
     */
    public void setKgFijados(float value) {
        this.kgFijados = value;
    }

    /**
     * Gets the value of the pendienteFijacion property.
     * 
     */
    public float getPendienteFijacion() {
        return pendienteFijacion;
    }

    /**
     * Sets the value of the pendienteFijacion property.
     * 
     */
    public void setPendienteFijacion(float value) {
        this.pendienteFijacion = value;
    }

    /**
     * Gets the value of the kgFacturados property.
     * 
     */
    public float getKgFacturados() {
        return kgFacturados;
    }

    /**
     * Sets the value of the kgFacturados property.
     * 
     */
    public void setKgFacturados(float value) {
        this.kgFacturados = value;
    }

    /**
     * Gets the value of the importeFacturado property.
     * 
     */
    public double getImporteFacturado() {
        return importeFacturado;
    }

    /**
     * Sets the value of the importeFacturado property.
     * 
     */
    public void setImporteFacturado(double value) {
        this.importeFacturado = value;
    }

    /**
     * Gets the value of the importeCobradoPesos property.
     * 
     */
    public double getImporteCobradoPesos() {
        return importeCobradoPesos;
    }

    /**
     * Sets the value of the importeCobradoPesos property.
     * 
     */
    public void setImporteCobradoPesos(double value) {
        this.importeCobradoPesos = value;
    }

    /**
     * Gets the value of the pendienteDeCobroEnPesos property.
     * 
     */
    public double getPendienteDeCobroEnPesos() {
        return pendienteDeCobroEnPesos;
    }

    /**
     * Sets the value of the pendienteDeCobroEnPesos property.
     * 
     */
    public void setPendienteDeCobroEnPesos(double value) {
        this.pendienteDeCobroEnPesos = value;
    }

    /**
     * Gets the value of the kgPendienteDeFacturar property.
     * 
     */
    public float getKgPendienteDeFacturar() {
        return kgPendienteDeFacturar;
    }

    /**
     * Sets the value of the kgPendienteDeFacturar property.
     * 
     */
    public void setKgPendienteDeFacturar(float value) {
        this.kgPendienteDeFacturar = value;
    }

    /**
     * Gets the value of the importeEstimadoPesosPendienteFacturar property.
     * 
     */
    public double getImporteEstimadoPesosPendienteFacturar() {
        return importeEstimadoPesosPendienteFacturar;
    }

    /**
     * Sets the value of the importeEstimadoPesosPendienteFacturar property.
     * 
     */
    public void setImporteEstimadoPesosPendienteFacturar(double value) {
        this.importeEstimadoPesosPendienteFacturar = value;
    }

    /**
     * Gets the value of the finalesFacturadasCobradas property.
     * 
     */
    public float getFinalesFacturadasCobradas() {
        return finalesFacturadasCobradas;
    }

    /**
     * Sets the value of the finalesFacturadasCobradas property.
     * 
     */
    public void setFinalesFacturadasCobradas(float value) {
        this.finalesFacturadasCobradas = value;
    }

    /**
     * Gets the value of the finalesFacturadasPendienteCobro property.
     * 
     */
    public float getFinalesFacturadasPendienteCobro() {
        return finalesFacturadasPendienteCobro;
    }

    /**
     * Sets the value of the finalesFacturadasPendienteCobro property.
     * 
     */
    public void setFinalesFacturadasPendienteCobro(float value) {
        this.finalesFacturadasPendienteCobro = value;
    }

    /**
     * Gets the value of the kgPendienteFacturarPorFinal property.
     * 
     */
    public float getKgPendienteFacturarPorFinal() {
        return kgPendienteFacturarPorFinal;
    }

    /**
     * Sets the value of the kgPendienteFacturarPorFinal property.
     * 
     */
    public void setKgPendienteFacturarPorFinal(float value) {
        this.kgPendienteFacturarPorFinal = value;
    }

}
