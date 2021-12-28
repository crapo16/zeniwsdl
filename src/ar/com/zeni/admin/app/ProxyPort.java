package ar.com.zeni.admin.app;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.zeniadminwsdl.ArrayOfAdminClienteType;
import ar.com.zeni.zeniadminwsdl.ArrayOfAdminCuentaType;
import ar.com.zeni.zeniadminwsdl.ArrayOfAyudaType;
import ar.com.zeni.zeniadminwsdl.ArrayOfCategoryType;
import ar.com.zeni.zeniadminwsdl.ArrayOfFilesType;
import ar.com.zeni.zeniadminwsdl.ArrayOfNewsType;
import ar.com.zeni.zeniadminwsdl.ArrayOfNormCromType;
import ar.com.zeni.zeniadminwsdl.ArrayOfNovedadType;
import ar.com.zeni.zeniadminwsdl.AyudaType;
import ar.com.zeni.zeniadminwsdl.ClienteCompletoType;
import ar.com.zeni.zeniadminwsdl.FaultType_Exception;
import ar.com.zeni.zeniadminwsdl.FileType;
import ar.com.zeni.zeniadminwsdl.FilesType;
import ar.com.zeni.zeniadminwsdl.NewsType;
import ar.com.zeni.zeniadminwsdl.NormCromType;
import ar.com.zeni.zeniadminwsdl.NovedadType;
import ar.com.zeni.zeniadminwsdl.ZeniAdminWSDL;
import ar.com.zeni.zeniadminwsdl.ZeniAdminWSDL_Service;

public class ProxyPort implements ZeniAdminWSDL {
	private static final QName SERVICE_NAME = new QName("http://www.zeni.com.ar/ZeniAdminWSDL/", "ZeniAdminWSDL");
	private static ZeniAdminWSDL	port;
	private static ProxyPort	proxy;
	public String modNormCrom(NormCromType norm) throws FaultType_Exception {
		return port.modNormCrom(norm);
	}
	public String delNormCrom(String norm) throws FaultType_Exception {
		return port.delNormCrom(norm);
	}
	public ArrayOfNormCromType obtNormCrom(String norm) throws FaultType_Exception {
		return port.obtNormCrom(norm);
	}
	public String addNormCrom(NormCromType norm) throws FaultType_Exception {
		return port.addNormCrom(norm);
	}
	public String modFiles(FilesType file) throws FaultType_Exception {
		return port.modFiles(file);
	}
	public String delFile(String fileId) throws FaultType_Exception {
		return port.delFile(fileId);
	}
	public ArrayOfFilesType obtFiles(String fileId) throws FaultType_Exception {
		return port.obtFiles(fileId);
	}
	public ArrayOfAdminCuentaType obtCuentas(String clienteId) throws FaultType_Exception {
		return port.obtCuentas(clienteId);
	}
	public String modCliente(String clienteId, String username, String name, String lastName, String email, ArrayOfAdminCuentaType cuentas) throws FaultType_Exception {
		return port.modCliente(clienteId, username, name, lastName, email, cuentas);
	}
	public String cloneUsuario(String clienteId) throws FaultType_Exception {
		return port.cloneUsuario(clienteId);
	}
	public String addUsuario(String clienteId, String username, String name, String lastName, String email) throws FaultType_Exception {
		return port.addUsuario(clienteId, username, name, lastName, email);
	}
	public ArrayOfAdminClienteType obtClientes(String clienteId) throws FaultType_Exception {
		return port.obtClientes(clienteId);
	}
	public String resetPassCliente(String clienteId) throws FaultType_Exception {
		return port.resetPassCliente(clienteId);
	}
	public String delCliente(String clienteId) throws FaultType_Exception {
		return port.delCliente(clienteId);
	}
	public ClienteCompletoType obtClienteCompleto(String clienteId) throws FaultType_Exception {
		return port.obtClienteCompleto(clienteId);
	}
	public ArrayOfCategoryType obtCategories(String clienteId) throws FaultType_Exception {
		return port.obtCategories(clienteId);
	}
	public String uploadFile(FileType file, String category) throws FaultType_Exception {
		return port.uploadFile(file, category);
	}
	public FileType downloadFile(String fileId, String category) throws FaultType_Exception {
		return port.downloadFile(fileId, category);
	}
	public String modAyuda(AyudaType ayuda) throws FaultType_Exception {
		return port.modAyuda(ayuda);
	}
	public String delAyuda(String ayudaId) throws FaultType_Exception {
		return port.delAyuda(ayudaId);
	}
	public String addAyuda(AyudaType ayuda) throws FaultType_Exception {
		return port.addAyuda(ayuda);
	}
	public ArrayOfAyudaType obtAyuda(String ayudaId) throws FaultType_Exception {
		return port.obtAyuda(ayudaId);
	}
	public ArrayOfNewsType obtNews(String newId) throws FaultType_Exception {
		return port.obtNews(newId);
	}
	public String modNews(NewsType news) throws FaultType_Exception {
		return port.modNews(news);
	}
	public String delNews(String newsId) throws FaultType_Exception {
		return port.delNews(newsId);
	}
	public String addNews(NewsType news) throws FaultType_Exception {
		return port.addNews(news);
	}
	public String delNovedad(String idNovedad)  throws FaultType_Exception {
		return port.delNovedad(idNovedad);
	}
	public String addNovedad(NovedadType in) throws FaultType_Exception {
		return port.addNovedad(in);
	}
	public ArrayOfNovedadType obtNovedad(String novedadId) throws FaultType_Exception {
		return port.obtNovedad(novedadId);
	}
	public String modNovedad(NovedadType in) throws FaultType_Exception {
		return port.modNovedad(in);
	}

	/**
	 *
	 * @param args
	 */
	private ProxyPort(String args[]) {
        URL wsdlURL = null;
        try {
        	System.out.println("Buscando el servicio: " + ZeniContextServer.getInstance().getProperty("WSDL_LOCATION_CLIENT"));
            wsdlURL = new URL( ZeniContextServer.getInstance().getProperty("WSDL_LOCATION_CLIENT"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
		if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        ZeniAdminWSDL_Service ss = new ZeniAdminWSDL_Service(wsdlURL, SERVICE_NAME);
        port = ss.getZeniAdminWSDLSOAP();
	}
	public ProxyPort() {
		this(new String[0]);
	}
	public static ProxyPort getInstance(){
		if (port==null) {
			proxy = new ProxyPort();
		}
		return proxy;
	}


}
