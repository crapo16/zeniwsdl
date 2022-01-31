package ar.com.zeni.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.db.ConnectionImplementation;
import ar.com.zeni.db.wP.ConnectionImplementationWordPress;

public class ZeniContextServer {
	public final static String VACIO = "";
//	private static final Logger	LOG	= Logger.getLogger("ar.com.zeni.implementation.ZeniContextServer");
	private static final Logger	LOG	= Logger.getLogger("org.apache.cxf.services.ZeniWSDL.ZeniWSDLSOAP.ZeniWSDL");

    private static ConnectionImplementation _dataSource;
    private static ConnectionImplementationWordPress _dataSourceWP;
	private static Properties	prop;
	private static Properties	propWP;
	private static String	db_consulta_owner;
	private static String	db_web_owner;
	private static ZeniContextServer instance;
	public static synchronized  ZeniContextServer getInstance() {
		if (instance==null){
			instance = new ZeniContextServer();
		}
		return instance;
	}

	private ZeniContextServer() {
		isLogMode();
		if (qufolo>ll){
			LOG.setFilter(new Filter(){
				@Override
				public boolean isLoggable(LogRecord record) {
					if (record.getSourceClassName().contains("ZeniWSDLSOAP")){
						return false;
					}
					return true;
				}
			});
		}
	}

	private static final Object syncWP = new Object();
	final public synchronized ConnectionImplementationWordPress getConnectionWP() throws ZeniDBExeption {
		try {
			synchronized (syncWP) {
				if (_dataSourceWP==null){
					_dataSourceWP = new ConnectionImplementationWordPress();
				}
			}
		} catch (Exception ex) {
			throw new ZeniDBExeption(FAULTCONSTANTS.CONTEXT_ERROR_MSG, FAULTCONSTANTS.CONTEXT_ERROR, ex);
		}
		return _dataSourceWP;
	}


	private static final Object sync = new Object();
	final public synchronized ConnectionImplementation getConnectionGetter() throws ZeniDBExeption {
		try {
			synchronized (sync) {
				if (_dataSource==null){
					_dataSource = new ConnectionImplementation();
				}
			}
		} catch (Exception ex) {
			throw new ZeniDBExeption(FAULTCONSTANTS.CONTEXT_ERROR_MSG, FAULTCONSTANTS.CONTEXT_ERROR, ex);
		}
		return _dataSource;
	}
	final public synchronized String getDBWebOwner() {
		synchronized (sync) {
			try {
				if (db_web_owner==null){
					db_web_owner = getProperty(APPL_PROPERTIES.DB_WEB_OWNER).concat(".");
				}
			} catch (Exception ex) {
				db_web_owner=ZeniContextServer.VACIO;
			}
		}
		return db_web_owner;
	}
	final public synchronized String getDBConsultaOwner() {
		synchronized (sync) {
			try {
				if (db_consulta_owner==null){
					db_consulta_owner = getProperty(APPL_PROPERTIES.DB_CONSULTA_OWNER).concat(".");
				}
			} catch (Exception ex) {
				db_consulta_owner=ZeniContextServer.VACIO;
			}
		}
		return db_consulta_owner;
	}
	final private Properties readApplProperties() {
		synchronized (sync) {
			if (prop==null) {
				prop = new Properties();
				try {
					prop.load(new FileInputStream(APPL_PROPERTIES.APP_PROPERTIES_FILE_NAME));
				} catch (Exception e) {
					// i cant read property file, so I cannot continue with execution.
					InputStream in = ConnectionImplementation.class.getClassLoader().getResourceAsStream(APPL_PROPERTIES.APP_PROPERTIES_FILE_NAME);
					if (in == null) {
						RuntimeException ex = new RuntimeException("No se puede leer properties: " + APPL_PROPERTIES.APP_PROPERTIES_FILE_NAME,e);
						throw ex;
					}
					try {
						prop.load(in);
					} catch (IOException e1) {
						RuntimeException ex = new RuntimeException("No se puede leer properties: " + APPL_PROPERTIES.APP_PROPERTIES_FILE_NAME,e1);
						throw ex;
					}
					try {in.close();} catch (IOException e1) {}
				}
			}
		}
		return prop;
	}
	private static final Object sync2 = new Object();
	final private Properties readWPProperties() {
		synchronized (sync2) {
			if (propWP==null) {
				propWP = new Properties();
				try {
					File f = new File(APPL_PROPERTIES.WP_PROPERTIES_FILE_NAME);
					ZeniContextServer.getInstance().printInfoLog("File : {"+f+"}");
					ZeniContextServer.getInstance().printInfoLog("File : {"+f.getAbsolutePath()+"}");
					propWP.load(new FileInputStream(APPL_PROPERTIES.WP_PROPERTIES_FILE_NAME));
				} catch (Exception e) {
					InputStream in = ConnectionImplementation.class.getClassLoader().getResourceAsStream(APPL_PROPERTIES.WP_PROPERTIES_FILE_NAME);
					if (in == null) {
						RuntimeException ex = new RuntimeException("No se puede leer properties: " + APPL_PROPERTIES.WP_PROPERTIES_FILE_NAME,e);
						throw ex;
					}
					try {
						propWP.load(in);
					} catch (IOException e1) {
						RuntimeException ex = new RuntimeException("No se puede leer properties: " + APPL_PROPERTIES.WP_PROPERTIES_FILE_NAME,e1);
						throw ex;
					}
					try {in.close();} catch (IOException e1) {}
				}
			}
		}
		return propWP;
	}
	public void printQuery(String string) {
		if (ll>=qufolo){
			doLog("QUERY: " + string);
		}
	}
	public void printInfoLog(String string){
		if (ll>=infolo){
			doLog(string);
		}
	}
	public void printVerboLog(Object str){
		printVerboLog(str!=null?str.toString():"null");
	}
	public void printVerboLog(String str){
		if (ll>=verblo){
			doLog(str);
		}
	}
	public void printErrorLog(String string) {
		if (ll>=errolo){
			doLog(string);
		}
	}
	public void printErrorLog(Exception ex) {
		if (ex!=null){
			if (ll>=errolo){
				printException(ex);
			}
		} else {
			printErrorLog("null");
		}
	}
	private void doLog(String str){
		LOG.info(str!=null?str:"null");
	}
	private static final String ENDLN = "\n";
	private static final String AT    = "at ";
	private static final String DOT   = ".";
	private static final String BRAC  = "(";
	private static final String ARAC  = ")";
	private static final String DDOT  = ":";
	private static final String SPACE = " ";
	private static final String TAB = "\t";
	private void printException(final Throwable t){
		innerPrintException(t);
	}
	private void innerPrintException(final Throwable t){
		final StringBuilder sb = new StringBuilder();
		innerFillUpException(t, sb);
		printErrorLog(sb.toString());
	}
	private void innerFillUpException(final Throwable t, final StringBuilder sb){
		final StackTraceElement[] ste = t.getStackTrace();
		sb.append(t.getClass());
		sb.append(DDOT);
		sb.append(SPACE);
		sb.append(t.getMessage());
		sb.append(ENDLN);
		for (int i=0; i<ste.length;i++){
			sb.append(TAB);
			sb.append(AT);
			sb.append(ste[i].getClassName());
			sb.append(DOT);
			sb.append(ste[i].getMethodName());
			sb.append(BRAC);
			sb.append(ste[i].getFileName());
			sb.append(DDOT);
			sb.append(ste[i].getLineNumber());
			sb.append(ARAC);
			sb.append(ENDLN);
		}
		if (t.getCause()!=null){
			innerFillUpException(t.getCause(), sb);
		}
	}
	private int	ll	= -1;
	private int	errolo	= 1;
	private int	qufolo	= 2;
	private int	infolo	= 3;
	private int	verblo	= 4;
	private boolean isLogMode() {
		if (ll<0) {
			if (isDebugMode()){
				setProperty(APPL_PROPERTIES.APPL_LOG,APPL_PROPERTIES.VERB);
			}
			ll = getProperty(APPL_PROPERTIES.APPL_LOG)!=null ? (getProperty(APPL_PROPERTIES.APPL_LOG).equals(APPL_PROPERTIES.VERB) ? verblo : getProperty(APPL_PROPERTIES.APPL_LOG).equals(APPL_PROPERTIES.INFO) ? infolo : getProperty(APPL_PROPERTIES.APPL_LOG).equals(APPL_PROPERTIES.QUER) ? qufolo : getProperty(APPL_PROPERTIES.APPL_LOG).equals(APPL_PROPERTIES.ERRO) ? errolo : 0) : 0;

			if (qufolo>ll){
				LOG.setFilter(new Filter(){
					@Override
					public boolean isLoggable(LogRecord record) {
						if (record.getSourceClassName().contains("ZeniWSDLSOAP")){
							return false;
						}
						return true;
					}
				});
			}
		}
		return ll > errolo;
	}
	/**
	 *
	 * APPL_PROPERTIES.<b>VERB</b><br/>
	 * APPL_PROPERTIES.<b>INFO</b><br/>
	 * APPL_PROPERTIES.<b>ERRO</b><br/>
	 * @param loglvl
	 */
	public void setLogLevel(final String loglvl){
		ll=-1;
		setProperty(APPL_PROPERTIES.APPL_LOG,loglvl);
		isLogMode();
	}
	public boolean isDebugMode(){
		return readApplProperties().getProperty(APPL_PROPERTIES.APPL_MODE).equals(APPL_PROPERTIES.DEBUG_MODE);
	}
	public boolean isAuthEnabled(){
		return isDebugMode()?readApplProperties().getProperty(APPL_PROPERTIES.APPL_AUTH).equals(APPL_PROPERTIES.TRUE):true;
	}
	public String getProperty(final String propName){
		return readApplProperties().getProperty(propName);
	}
	public String getPropertyWP(final String propName){
		return readWPProperties().getProperty(propName);
	}
	private void setProperty(final String key, final String value){
		readApplProperties().setProperty(key, value);
	}

	public interface APPL_PROPERTIES {
		public final String APP_PROPERTIES_FILE_NAME = "APP_PROPERTIES.PROPERTIES";
		public final String WP_PROPERTIES_FILE_NAME = "WP_PROPERTIES.PROPERTIES";
		public final String APPL_SECURITY_LEVEL = "APPL_SECURITY_LEVEL";
		public final String APPL_MODE = "APPL_MODE";
		public final String APPL_AUTH = "APPL_AUTH";
		public final String APPL_LOG = "APPL_LOG";
		public final String VERB = "VERB";
		public final String ERRO = "ERRO";
		public final String QUER = "QUER";
		public final String INFO = "INFO";
		public final String TRUE = "true";
		public final String FALSE = "false";
		public final String STRICT = "strict";
		public final String SIMPLE	= "simple";
		public final String LEASE = "lease";
		public final String UNACTIVE = "unactive";
		public final String DEBUG_MODE = "debug";
		public final String PRODUCTION_MODE = "production";
		public final String DB_WEB_OWNER = "DB_WEB_OWNER";
		public final String DB_CONSULTA_OWNER = "DB_CONSULTA_OWNER";
		public final String DOWNLOAD_URL_BASE = "DOWNLOAD_URL_BASE";
		public final String DOWNLOAD_PARAM_1 = "DOWNLOAD_PARAM_1";
		public final String DOWNLOAD_PARAM_2 = "DOWNLOAD_PARAM_2";
		public final String DOWNLOAD_PARAM_3 = "DOWNLOAD_PARAM_3";

		/*OGAGLIANO TENENCIA VALORIZADA*/
		public final String DOWNLOAD_URL_TENENCIA = "DOWNLOAD_URL_TENENCIA";
		public final String DOWNLOAD_PARAM_1_TENENCIA = "DOWNLOAD_PARAM_1_TENENCIA";
		public final String DOWNLOAD_PARAM_2_TENENCIA = "DOWNLOAD_PARAM_2_TENENCIA";
		
		
		/*OGAGLIANO BoletoCartaOferta*/
		public final String DOWNLOAD_URL_BOLETOCARTA = "DOWNLOAD_URL_BOLETOCARTA";
		public final String DOWNLOAD_PARAM_1_BOLETOCARTA = "DOWNLOAD_PARAM_1_BOLETOCARTA";
		public final String DOWNLOAD_PARAM_2_BOLETOCARTA = "DOWNLOAD_PARAM_2_BOLETOCARTA";



	}

	public interface APPL_SECURITY_PROPERTIES {
		public final String APPL_SECURITY_LEVEL = APPL_PROPERTIES.APPL_SECURITY_LEVEL;
		public final String APPL_AUTH = APPL_PROPERTIES.APPL_AUTH;
		public final String TRUE = APPL_PROPERTIES.TRUE;
		public final String FALSE = APPL_PROPERTIES.FALSE;
		public final String LEVEL_STRICT = APPL_PROPERTIES.STRICT;
		public final String LEVEL_LEASE = APPL_PROPERTIES.LEASE;
		public final String LEVEL_SIMPLE= APPL_PROPERTIES.SIMPLE;
		public final String LEVEL_UNACTIVE = APPL_PROPERTIES.UNACTIVE;
	}
}
