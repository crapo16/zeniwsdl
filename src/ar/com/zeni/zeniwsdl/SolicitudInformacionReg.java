
package ar.com.zeni.zeniwsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SolicitudInformacionReg complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SolicitudInformacionReg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mailTo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="avisoNegocioComprador" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="avisoNegocioVendedor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="boletosComprador" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="boletosVendedor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cuposComprador" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cuposVendedor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="docImpositivo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="infoDigitalComprador" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="infoDigitalVendedor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="retencionComprobantesComprador" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="retencionComprobantesVendedor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fijacionesComprador" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="fijacionesVendedor" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="habilitacionBolsa" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="informeDiario" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tenenciaValorizada" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mat" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="opFCI" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ordenCarga" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="idSolicitudInformacion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clienteWebId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cuentaID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SolicitudInformacionReg", propOrder = {
    "mailTo",
    "avisoNegocioComprador",
    "avisoNegocioVendedor",
    "boletosComprador",
    "boletosVendedor",
    "cuposComprador",
    "cuposVendedor",
    "docImpositivo",
    "infoDigitalComprador",
    "infoDigitalVendedor",
    "retencionComprobantesComprador",
    "retencionComprobantesVendedor",
    "fijacionesComprador",
    "fijacionesVendedor",
    "habilitacionBolsa",
    "informeDiario",
    "tenenciaValorizada",
    "mat",
    "opFCI",
    "ordenCarga",
    "idSolicitudInformacion",
    "clienteWebId",
    "usuario",
    "cuentaID"
})
public class SolicitudInformacionReg {

    @XmlElement(required = true)
    protected String mailTo;
    protected boolean avisoNegocioComprador;
    protected boolean avisoNegocioVendedor;
    protected boolean boletosComprador;
    protected boolean boletosVendedor;
    protected boolean cuposComprador;
    protected boolean cuposVendedor;
    protected boolean docImpositivo;
    protected boolean infoDigitalComprador;
    protected boolean infoDigitalVendedor;
    protected boolean retencionComprobantesComprador;
    protected boolean retencionComprobantesVendedor;
    protected boolean fijacionesComprador;
    protected boolean fijacionesVendedor;
    protected boolean habilitacionBolsa;
    protected boolean informeDiario;
    protected boolean tenenciaValorizada;
    protected boolean mat;
    protected boolean opFCI;
    protected boolean ordenCarga;
    @XmlElement(required = true)
    protected String idSolicitudInformacion;
    @XmlElement(required = true)
    protected String clienteWebId;
    @XmlElement(required = true)
    protected String usuario;
    @XmlElement(required = true)
    protected String cuentaID;

    /**
     * Obtiene el valor de la propiedad mailTo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailTo() {
        return mailTo;
    }

    /**
     * Define el valor de la propiedad mailTo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailTo(String value) {
        this.mailTo = value;
    }

    /**
     * Obtiene el valor de la propiedad avisoNegocioComprador.
     * 
     */
    public boolean isAvisoNegocioComprador() {
        return avisoNegocioComprador;
    }

    /**
     * Define el valor de la propiedad avisoNegocioComprador.
     * 
     */
    public void setAvisoNegocioComprador(boolean value) {
        this.avisoNegocioComprador = value;
    }

    /**
     * Obtiene el valor de la propiedad avisoNegocioVendedor.
     * 
     */
    public boolean isAvisoNegocioVendedor() {
        return avisoNegocioVendedor;
    }

    /**
     * Define el valor de la propiedad avisoNegocioVendedor.
     * 
     */
    public void setAvisoNegocioVendedor(boolean value) {
        this.avisoNegocioVendedor = value;
    }

    /**
     * Obtiene el valor de la propiedad boletosComprador.
     * 
     */
    public boolean isBoletosComprador() {
        return boletosComprador;
    }

    /**
     * Define el valor de la propiedad boletosComprador.
     * 
     */
    public void setBoletosComprador(boolean value) {
        this.boletosComprador = value;
    }

    /**
     * Obtiene el valor de la propiedad boletosVendedor.
     * 
     */
    public boolean isBoletosVendedor() {
        return boletosVendedor;
    }

    /**
     * Define el valor de la propiedad boletosVendedor.
     * 
     */
    public void setBoletosVendedor(boolean value) {
        this.boletosVendedor = value;
    }

    /**
     * Obtiene el valor de la propiedad cuposComprador.
     * 
     */
    public boolean isCuposComprador() {
        return cuposComprador;
    }

    /**
     * Define el valor de la propiedad cuposComprador.
     * 
     */
    public void setCuposComprador(boolean value) {
        this.cuposComprador = value;
    }

    /**
     * Obtiene el valor de la propiedad cuposVendedor.
     * 
     */
    public boolean isCuposVendedor() {
        return cuposVendedor;
    }

    /**
     * Define el valor de la propiedad cuposVendedor.
     * 
     */
    public void setCuposVendedor(boolean value) {
        this.cuposVendedor = value;
    }

    /**
     * Obtiene el valor de la propiedad docImpositivo.
     * 
     */
    public boolean isDocImpositivo() {
        return docImpositivo;
    }

    /**
     * Define el valor de la propiedad docImpositivo.
     * 
     */
    public void setDocImpositivo(boolean value) {
        this.docImpositivo = value;
    }

    /**
     * Obtiene el valor de la propiedad infoDigitalComprador.
     * 
     */
    public boolean isInfoDigitalComprador() {
        return infoDigitalComprador;
    }

    /**
     * Define el valor de la propiedad infoDigitalComprador.
     * 
     */
    public void setInfoDigitalComprador(boolean value) {
        this.infoDigitalComprador = value;
    }

    /**
     * Obtiene el valor de la propiedad infoDigitalVendedor.
     * 
     */
    public boolean isInfoDigitalVendedor() {
        return infoDigitalVendedor;
    }

    /**
     * Define el valor de la propiedad infoDigitalVendedor.
     * 
     */
    public void setInfoDigitalVendedor(boolean value) {
        this.infoDigitalVendedor = value;
    }

    /**
     * Obtiene el valor de la propiedad retencionComprobantesComprador.
     * 
     */
    public boolean isRetencionComprobantesComprador() {
        return retencionComprobantesComprador;
    }

    /**
     * Define el valor de la propiedad retencionComprobantesComprador.
     * 
     */
    public void setRetencionComprobantesComprador(boolean value) {
        this.retencionComprobantesComprador = value;
    }

    /**
     * Obtiene el valor de la propiedad retencionComprobantesVendedor.
     * 
     */
    public boolean isRetencionComprobantesVendedor() {
        return retencionComprobantesVendedor;
    }

    /**
     * Define el valor de la propiedad retencionComprobantesVendedor.
     * 
     */
    public void setRetencionComprobantesVendedor(boolean value) {
        this.retencionComprobantesVendedor = value;
    }

    /**
     * Obtiene el valor de la propiedad fijacionesComprador.
     * 
     */
    public boolean isFijacionesComprador() {
        return fijacionesComprador;
    }

    /**
     * Define el valor de la propiedad fijacionesComprador.
     * 
     */
    public void setFijacionesComprador(boolean value) {
        this.fijacionesComprador = value;
    }

    /**
     * Obtiene el valor de la propiedad fijacionesVendedor.
     * 
     */
    public boolean isFijacionesVendedor() {
        return fijacionesVendedor;
    }

    /**
     * Define el valor de la propiedad fijacionesVendedor.
     * 
     */
    public void setFijacionesVendedor(boolean value) {
        this.fijacionesVendedor = value;
    }

    /**
     * Obtiene el valor de la propiedad habilitacionBolsa.
     * 
     */
    public boolean isHabilitacionBolsa() {
        return habilitacionBolsa;
    }

    /**
     * Define el valor de la propiedad habilitacionBolsa.
     * 
     */
    public void setHabilitacionBolsa(boolean value) {
        this.habilitacionBolsa = value;
    }

    /**
     * Obtiene el valor de la propiedad informeDiario.
     * 
     */
    public boolean isInformeDiario() {
        return informeDiario;
    }

    /**
     * Define el valor de la propiedad informeDiario.
     * 
     */
    public void setInformeDiario(boolean value) {
        this.informeDiario = value;
    }

    /**
     * Obtiene el valor de la propiedad tenenciaValorizada.
     * 
     */
    public boolean isTenenciaValorizada() {
        return tenenciaValorizada;
    }

    /**
     * Define el valor de la propiedad tenenciaValorizada.
     * 
     */
    public void setTenenciaValorizada(boolean value) {
        this.tenenciaValorizada = value;
    }

    /**
     * Obtiene el valor de la propiedad mat.
     * 
     */
    public boolean isMat() {
        return mat;
    }

    /**
     * Define el valor de la propiedad mat.
     * 
     */
    public void setMat(boolean value) {
        this.mat = value;
    }

    /**
     * Obtiene el valor de la propiedad opFCI.
     * 
     */
    public boolean isOpFCI() {
        return opFCI;
    }

    /**
     * Define el valor de la propiedad opFCI.
     * 
     */
    public void setOpFCI(boolean value) {
        this.opFCI = value;
    }

    /**
     * Obtiene el valor de la propiedad ordenCarga.
     * 
     */
    public boolean isOrdenCarga() {
        return ordenCarga;
    }

    /**
     * Define el valor de la propiedad ordenCarga.
     * 
     */
    public void setOrdenCarga(boolean value) {
        this.ordenCarga = value;
    }

    /**
     * Obtiene el valor de la propiedad idSolicitudInformacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSolicitudInformacion() {
        return idSolicitudInformacion;
    }

    /**
     * Define el valor de la propiedad idSolicitudInformacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSolicitudInformacion(String value) {
        this.idSolicitudInformacion = value;
    }

    /**
     * Obtiene el valor de la propiedad clienteWebId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClienteWebId() {
        return clienteWebId;
    }

    /**
     * Define el valor de la propiedad clienteWebId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClienteWebId(String value) {
        this.clienteWebId = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad cuentaID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuentaID() {
        return cuentaID;
    }

    /**
     * Define el valor de la propiedad cuentaID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuentaID(String value) {
        this.cuentaID = value;
    }

}
