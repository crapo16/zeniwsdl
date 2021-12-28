
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
 *         &lt;element name="UNIDAD_MEDIDA" type="{http://www.zeni.com.ar/ZeniWSDL/}DescripcionType"/>
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
@XmlType(name = "GarantiasMercadoATerminoType", propOrder = {
	"idregistro",	
    "garantia",
    "fechaposicion",
    "idmoneda"    
})

public class GarantiasMercadoATerminoType {

    @XmlElement(name = "IDREGISTRO")
    protected int idregistro;
    @XmlElement(name = "GARANTIA", required = true)
    protected float garantia;
    @XmlElement(name = "FECHAPOSICION", required = true)
    protected FechaTimeType fechaposicion;
    @XmlElement(name = "IDMONEDA", required = true)
    protected int idmoneda;

    public FechaTimeType getFECHAPOSICION() {
        return fechaposicion;
    }

    /**
     * Sets the value of the fechaposicion property.
     * 
     * @param value
     *     allowed object is
     *     {@link FechaTimeType }
     *     
     */
    public void setFECHAPOSICION(FechaTimeType value) {
        this.fechaposicion = value;
    }

    public void setIDREGISTRO(int value) {
    	this.idregistro = value;
    }
    
    public int getIDREGISTRO() {
    	return this.idregistro;
    }

    public void setGARANTIA(float value) {
    	this.garantia = value;
    }
    
    public float getGARANTIA() {
    	return this.garantia;
    }
    
    public void setIDMONEDA(int value) {
    	this.idmoneda = value;
    }
    
    public int getIDMONEDA() {
    	return this.idmoneda;
    }
}
