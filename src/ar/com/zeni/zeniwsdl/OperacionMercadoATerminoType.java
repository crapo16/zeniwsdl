
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperacionMercadoATerminoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperacionMercadoATerminoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MERCADO" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="PRODUCTO" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="ESTADO" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="PUERTO" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="POSICION" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="REGISTRO" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FECHA" type="{http://www.zeni.com.ar/ZeniWSDL/}FechaTimeType"/>
 *         &lt;element name="TIPO_OPERACION" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
 *         &lt;element name="PRIMA" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="PRECIOAPERTURA" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="TONELADAS" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="CANTIDAD" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         					<xsd:element maxOccurs="1" minOccurs="1" name="NROCUENTA" type="tns:DescripcionType"/>
					<xsd:element maxOccurs="1" minOccurs="1" name="LIQUIDA_A_ID" type="xsd:int"/>
					<xsd:element maxOccurs="1" minOccurs="1" name="ES_LIQUIDADO_POR_ID" type="xsd:int"/>
					<xsd:element maxOccurs="1" minOccurs="1" name="ACCION" type="tns:DescripcionType"/>
					<xsd:element maxOccurs="1" minOccurs="1" name="FECHAACTUALIZADO" type="tns:FechaTimeType" />
					<xsd:element maxOccurs="1" minOccurs="1" name="CONTRATOZENI" type="tns:DescripcionType" />

 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperacionMercadoATerminoType", propOrder = {
	"idregistro",	
    "idmercado",
    // #1563
    "idmoneda",
    "idproducto",
    "idestado",
    "idpuerto",
    "posicion",
    "registro",
    "fecha",
    "tipooperacion",
    "prima",
    "precioapertura",
    "toneladas",
    "cantidad",
    "nrosubcta",
    "nrocuenta",
    "liquidaaid",
    "esliquidadoporid",
    "accion",
    "fechaactualizado",
    "contratozeni"    
})
public class OperacionMercadoATerminoType {

    @XmlElement(name = "IDREGISTRO")
    protected int idregistro;
    @XmlElement(name = "IDMERCADO", required = true)
    protected int idmercado;
    
    // #1563
    @XmlElement(name = "IDMONEDA", required = true)
    protected int idmoneda;
    
    @XmlElement(name = "IDPRODUCTO", required = true)
    protected int idproducto;
    @XmlElement(name = "IDESTADO", required = true)
    protected int idestado;
    @XmlElement(name = "IDPUERTO", required = true)
    protected int idpuerto;
    @XmlElement(name = "POSICION", required = true)
    protected String posicion;
    @XmlElement(name = "REGISTRO")
    protected int registro;
    @XmlElement(name = "FECHA", required = true)
    protected FechaTimeType fecha;
    @XmlElement(name = "TIPO_OPERACION", required = true)
    protected String tipooperacion;
    @XmlElement(name = "PRIMA")
    protected float prima;
    @XmlElement(name = "PRECIOAPERTURA")
    protected float precioapertura;
    @XmlElement(name = "TONELADAS")
    protected float toneladas;
    @XmlElement(name = "CANTIDAD")
    protected float cantidad;
    @XmlElement(name = "NROSUBCTA")
    protected int nrosubcta;
    @XmlElement(name = "NROCUENTA")
    protected String nrocuenta;
    @XmlElement(name = "LIQUIDA_A_ID")
    protected int liquidaaid;
    @XmlElement(name = "ES_LIQUIDADO_POR_ID")
    protected int esliquidadoporid;
    @XmlElement(name = "ACCION")
    protected String accion;
    @XmlElement(name = "FECHAACTUALIZADO")
    protected FechaTimeType fechaactualizado;
    @XmlElement(name = "CONTRATOZENI")
    protected String contratozeni;

    /**
     * Gets the value of the mercado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public int getIDMERCADO() {
        return idmercado;
    }

    /**
     * Sets the value of the mercado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDMERCADO(int value) {
        this.idmercado = value;
    }

    // #1563
    public int getIDMONEDA() {
        return idmoneda;
    }

    public void setIDMONEDA(int value) {
        this.idmoneda = value;
    }

    
    /**
     * Gets the value of the producto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public int getIDPRODUCTO() {
        return idproducto;
    }

    /**
     * Sets the value of the producto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPRODUCTO(int value) {
        this.idproducto = value;
    }

    
    public int getIDESTADO() {
    	return this.idestado;
    }
    
    public void setIDESTADO(int value) {
    	this.idestado = value;
    }
    
    /**
     * Gets the value of the puerto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public int getIDPUERTO() {
        return idpuerto;
    }

    /**
     * Sets the value of the puerto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPUERTO(int value) {
        this.idpuerto = value;
    }

    /**
     * Gets the value of the posicion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOSICION() {
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
    public void setPOSICION(String value) {
        this.posicion = value;
    }

    /**
     * Gets the value of the registro property.
     * 
     */
    public int getREGISTRO() {
        return registro;
    }

    /**
     * Sets the value of the registro property.
     * 
     */
    public void setREGISTRO(int value) {
        this.registro = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link FechaTimeType }
     *     
     */
    public FechaTimeType getFECHA() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFECHA(FechaTimeType value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the tipooperacion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIPOOPERACION() {
        return tipooperacion;
    }

    /**
     * Sets the value of the tipooperacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIPOOPERACION(String value) {
        this.tipooperacion = value;
    }

    /**
     * Gets the value of the prima property.
     * 
     */
    public float getPRIMA() {
        return prima;
    }

    /**
     * Sets the value of the prima property.
     * 
     */
    public void setPRIMA(float value) {
        this.prima = value;
    }

    /**
     * Gets the value of the precioapertura property.
     * 
     */
    public float getPRECIOAPERTURA() {
        return precioapertura;
    }

    /**
     * Sets the value of the precioapertura property.
     * 
     */
    public void setPRECIOAPERTURA(float value) {
        this.precioapertura = value;
    }

    /**
     * Gets the value of the volumen property.
     * 
     */
    public float getTONELADAS() {
        return toneladas;
    }

    /**
     * Sets the value of the volumen property.
     * 
     */
    public void setTONELADAS(float value) {
        this.toneladas = value;
    }

    /**
     * Gets the value of the cantidad property.
     * 
     */
    public float getCANTIDAD() {
        return cantidad;
    }

    /**
     * Sets the value of the cantidad property.
     * 
     */
    public void setCANTIDAD(float value) {
        this.cantidad = value;
    }

    public void setNROSUBCTA(int value) {
    	this.nrosubcta = value;
    }
    
    public int getNROSUBCTA() {
    	return this.nrosubcta;
    }
    
    public void setIDREGISTRO(int value) {
    	this.idregistro = value;
    }
    
    public int getID() {
    	return this.idregistro;
    }

    public void setNROCUENTA(String value) {
    	this.nrocuenta = value;
    }
    
    public String getNROCUENTA() {
    	return this.nrocuenta;
    }
    
    public void setLIQUIDA_A_ID(int value) {
    	this.liquidaaid = value;
    }
    
    public int getLIQUIDA_A_ID() {
    	return this.liquidaaid;
    }
    
    public void setES_LIQUIDADO_POR_ID(int value) {
    	this.esliquidadoporid = value;
    }
    
    public int getES_LIQUIDADO_POR_ID() {
    	return this.esliquidadoporid;
    }

    public void setACCION(String value) {
    	this.accion = value;
    }
    
    public String getACCION() {
    	return this.accion;
    }

    public void setFECHAACTUALIZADO(FechaTimeType value) {
    	this.fechaactualizado = value;
    }
    
    
    public FechaTimeType getFECHAACTUALIZADO() {
    	return this.fechaactualizado;
    }  

    public void setCONTRATOZENI(String value) {
    	this.contratozeni = value;
    }
    
    public String getCONTRATOZENI() {
    	return this.contratozeni;
    }
    
}
