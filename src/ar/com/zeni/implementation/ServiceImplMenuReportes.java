package ar.com.zeni.implementation;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.handler.MessageContext;

import ar.com.zeni.admin.app.server.FileAdministrationConstanst;
import ar.com.zeni.admin.app.server.FileAdministrationConstanst.FileTable;
import ar.com.zeni.admin.app.server.NormasComerciaConstanst.NormasComerciaTable;
import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.FAULTCONSTANTS;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniAuthTypeInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.common.exceptions.ZeniHashInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniIPDifiereExeption;
import ar.com.zeni.common.exceptions.ZeniServerExeption;
import ar.com.zeni.common.tables.AyudasConstanst.AyudaTable;
import ar.com.zeni.common.tables.FileAdministrationConstanst.FileCategoriesTable;
import ar.com.zeni.common.tables.NewsConstanst.NewsTable;
import ar.com.zeni.db.ZeniQueryBuilder;
import ar.com.zeni.db.ZeniQueryBuilder.Operator;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.security.AuthNHashUtil;
import ar.com.zeni.zeniwsdl.ArrayOfAyudaType;
import ar.com.zeni.zeniwsdl.ArrayOfCategoryType;
import ar.com.zeni.zeniwsdl.ArrayOfDetallesInscripcionesType;
import ar.com.zeni.zeniwsdl.ArrayOfExtensionesIIBBType;
import ar.com.zeni.zeniwsdl.ArrayOfExtensionesType;
import ar.com.zeni.zeniwsdl.ArrayOfFilesType;
import ar.com.zeni.zeniwsdl.ArrayOfNewsType;
import ar.com.zeni.zeniwsdl.ArrayOfNormCromType;
import ar.com.zeni.zeniwsdl.AuthType;
import ar.com.zeni.zeniwsdl.AyudaType;
import ar.com.zeni.zeniwsdl.CategoryType;
import ar.com.zeni.zeniwsdl.DetallesInscripcionesType;
import ar.com.zeni.zeniwsdl.ExtensionesIIBBType;
import ar.com.zeni.zeniwsdl.ExtensionesType;
import ar.com.zeni.zeniwsdl.FaultType;
import ar.com.zeni.zeniwsdl.FaultType_Exception;
import ar.com.zeni.zeniwsdl.FileType;
import ar.com.zeni.zeniwsdl.FilesType;
import ar.com.zeni.zeniwsdl.NewsType;
import ar.com.zeni.zeniwsdl.NormCromType;
import ar.com.zeni.zeniwsdl.SINOType;
import ar.com.zeni.zeniwsdl.SituacionImpositivaGananciasType;
import ar.com.zeni.zeniwsdl.SituacionImpositivaIngBrutosType;

public abstract class ServiceImplMenuReportes extends ServiceImplPopContr {
	public ArrayOfAyudaType obtenerAyuda(AuthType auth, String ayudaId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerAyuda");
		ZeniContextServer.getInstance().printVerboLog(ayudaId);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			ArrayOfAyudaType _returnAyudas = new ArrayOfAyudaType();
			final java.util.List<AyudaType> _returnAyudasAyus = new ArrayList<AyudaType>();
			String querySelect = new ZeniQueryBuilder().select(AyudaTable.FIELD_ID, AyudaTable.FILED_QUESTION, AyudaTable.FIELD_ANSWER, AyudaTable.FIELD_ORDER)
				.from(AyudaTable.HELPTABLE)
				.getQuey();
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						AyudaType _returnAyudasAyusVal1 = new AyudaType();
						_returnAyudasAyusVal1.setId(rset.getString(AyudaTable.FIELD_ID));
						_returnAyudasAyusVal1.setPregunta(rset.getString(AyudaTable.FILED_QUESTION));
						_returnAyudasAyusVal1.setRespuesta(rset.getString(AyudaTable.FIELD_ANSWER));
						_returnAyudasAyusVal1.setOrden(rset.getInt(AyudaTable.FIELD_ORDER));
						_returnAyudasAyus.add(_returnAyudasAyusVal1);
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
			_returnAyudas.getAyus().addAll(_returnAyudasAyus);
			return _returnAyudas;
		} catch (ZeniIPDifiereExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniHashInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniAuthTypeInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}

	public ArrayOfNewsType obtenerNoticias(AuthType auth, String newId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerNoticias");
		ZeniContextServer.getInstance().printVerboLog(newId);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			final ArrayOfNewsType _return = new ArrayOfNewsType();
			final List<NewsType> _returnNews = new ArrayList<NewsType>();
			String querySelect = "SELECT ID, NEWS, FECHA FROM " + ZeniContextServer.getInstance().getDBWebOwner() + "WEB_NEWS ORDER BY FECHA DESC";
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						try {
							NewsType _returnNewsVal1 = new NewsType();
							_returnNewsVal1.setId(rset.getString(NewsTable.FIELD_ID));
							_returnNewsVal1.setDesc(rset.getString(NewsTable.FIELD_NEWS));
							_returnNewsVal1.setFecha(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString(NewsTable.FIELD_DATE))));
							_returnNews.add(_returnNewsVal1);
						} catch (ParseException e) {
							throw new SQLException("No se puede determinar la fecha en el query.", e);
						}
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
			_return.getNews().addAll(_returnNews);
			return _return;
		} catch (ZeniIPDifiereExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniHashInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniAuthTypeInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}

	public ArrayOfCategoryType obtenerCategories(AuthType auth, String catId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerCategories");
		ZeniContextServer.getInstance().printVerboLog(catId);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			String querySelect = new ZeniQueryBuilder().select(FileCategoriesTable.FIELD_ID, FileCategoriesTable.FILED_CATEGORY)
				.from(FileCategoriesTable.FILE_CATEGORIES_TABLE)
				.getQuey();
			final ArrayOfCategoryType ar = new ArrayOfCategoryType();
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						CategoryType ca = new CategoryType();
						ca.setId(rset.getString(FileCategoriesTable.FIELD_ID));
						ca.setDesc(rset.getString(FileCategoriesTable.FILED_CATEGORY));
						ar.getCategs().add(ca);
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
			return ar;
		} catch (ZeniIPDifiereExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniHashInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniAuthTypeInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}

	@Override
	public ArrayOfNormCromType obtenerNormasDeComercializacion(AuthType auth, String norm) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerNormasDeComercializacion");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(norm);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			ArrayOfNormCromType _return = new ArrayOfNormCromType();
			final List<NormCromType> _returnNormas = new ArrayList<NormCromType>();
			String querySelect = new ZeniQueryBuilder().select(NormasComerciaTable.FIELD_ID, NormasComerciaTable.FIELD_NORMAVIG, NormasComerciaTable.FIELD_PRODUCTO, NormasComerciaTable.FIELD_NORMA_FILE_ID, NormasComerciaTable.FIELD_TMPD_FILE_ID)
				.from(NormasComerciaTable.HELPTABLE)
				.getQuey();
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						final NormCromType _returnNormasVal1 = new NormCromType();
						_returnNormasVal1.setId(rset.getString(NormasComerciaTable.FIELD_ID));
						_returnNormasVal1.setProd(rset.getString(NormasComerciaTable.FIELD_PRODUCTO));
						_returnNormasVal1.setNormvig(rset.getString(NormasComerciaTable.FIELD_NORMAVIG));
						_returnNormasVal1.setFileNormaId(rset.getString(NormasComerciaTable.FIELD_NORMA_FILE_ID));
						_returnNormasVal1.setFileTMPSId(rset.getString(NormasComerciaTable.FIELD_TMPD_FILE_ID));
						_returnNormas.add(_returnNormasVal1);
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
			for (final NormCromType cor : _returnNormas) {
				String sel = "select " + FileTable.FIELD_DESCRIPTION + " from " + FileTable.FILETABLE + " where " + FileTable.FIELD_ID + " = '";
				String tmps = sel + cor.getFileNormaId() + "'";
				String norma = sel + cor.getFileTMPSId() + "'";
				final ResulsetObjectBuilder resb1 = new ResulsetObjectBuilder() {
					@Override
					public void thisIsTheResulset(ResultSet rset) throws SQLException {
						while (rset.next()) {
							cor.setFileNorma(rset.getString(FileTable.FIELD_DESCRIPTION));
						}
					}
				};
				final ResulsetObjectBuilder resb2 = new ResulsetObjectBuilder() {
					@Override
					public void thisIsTheResulset(ResultSet rset) throws SQLException {
						while (rset.next()) {
							cor.setFileTMPS(rset.getString(FileTable.FIELD_DESCRIPTION));
						}
					}
				};
				try {
					ZeniQueryExcecutor.excecuteSelect(norma, resb1);
					ZeniQueryExcecutor.excecuteSelect(tmps, resb2);
				} catch (ZeniDBExeption e) {
					e.printStackTrace();
				}
			}
			_return.getNormas().addAll(_returnNormas);
			return _return;
		} catch (ZeniIPDifiereExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniHashInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniAuthTypeInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}

	@Override
	public ArrayOfFilesType obtenerPDFaMostrarPantallas(AuthType auth, String categoryId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerPDFaMostrarPantallas");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(categoryId);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			if (categoryId == null || categoryId.equals(ZeniContextServer.VACIO)) {
				throw new ZeniServerExeption("la categoria no puede ser nula o vacia", FAULTCONSTANTS.ERROR_ERRONEO);
			}
			boolean multiple = false;
			boolean nsnc = false;
			if (categoryId.equals("CM") || categoryId.equals("RC") || categoryId.equals("CC") || categoryId.equals("RS") || categoryId.equals("CL") || categoryId.equals("MP") || categoryId.equals("CV")) {
				multiple = false;
			} else if (categoryId.equals("PI") || categoryId.equals("BI") || categoryId.equals("SF") || categoryId.equals("NI") || categoryId.equals("NC")) {
				multiple = true;
			} else {
				nsnc = true;
			}
			final boolean multipleFinal = multiple; //subo muchos o el ultimo.
			ArrayOfFilesType _return = new ArrayOfFilesType();
			if (nsnc) {
				return _return; //no es una opcion valida.
			}
			final List<FilesType> _returnFiles = new ArrayList<FilesType>();
			String querySelect = new ZeniQueryBuilder().select(FileTable.ALFIELD_ID, FileTable.ALFIELD_NAME, FileTable.ALFIELD_EXTENSION, FileTable.ALFIELD_DESCRIPTION, FileTable.ALFIELD_MESSAGE, FileTable.ALFIELD_MODIF_DATE, FileTable.ALFILED_CATEGORY, "SUBSTR("+FileTable.ALFIELD_MODIF_DATE+", 1, 4) as an", "SUBSTR("+FileTable.ALFIELD_MODIF_DATE+", 7, 2) as me", "SUBSTR("+FileTable.ALFIELD_MODIF_DATE+", 5, 2) as di")
					.from(FileTable.ALFILETABLE)
					.where(FileTable.ALFILED_CATEGORY, Operator.EQUALS, categoryId)
					.getQuey() + " Order by an desc, me desc, di desc, " + FileTable.ALFIELD_MODIF_DATE + " desc ";
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					boolean continueThru = true;
					while (rset.next() && continueThru) {
						try {
							FilesType _returnFilesVal1 = new FilesType();
							_returnFilesVal1.setId(rset.getString(FileTable.FIELD_ID));
							_returnFilesVal1.setExt(rset.getString(FileTable.FIELD_EXTENSION));
							_returnFilesVal1.setName(rset.getString(FileTable.FIELD_NAME));
							_returnFilesVal1.setNameExt(rset.getString(FileTable.FIELD_NAME) + "." + rset.getString(FileTable.FIELD_EXTENSION));
							_returnFilesVal1.setDesc(rset.getString(FileTable.FIELD_DESCRIPTION));
							_returnFilesVal1.setMess(rset.getString(FileTable.FIELD_MESSAGE));
							_returnFilesVal1.setCatId(rset.getString(FileTable.FILED_CATEGORY));
							_returnFilesVal1.setFecha(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyddMMSSSssHHmm(rset.getString(FileTable.FIELD_MODIF_DATE))));
							_returnFiles.add(_returnFilesVal1);
							continueThru = multipleFinal;
						} catch (ParseException e) {
							throw new SQLException("No se puede determinar la fecha en el query.", e);
						}
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
			_return.getFiles().addAll(_returnFiles);
			return _return;
		} catch (ZeniIPDifiereExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniHashInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniAuthTypeInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}

	@Override
	public FileType descargarPDFaMostrarPantallas(AuthType auth, String fileId, String categoryId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation descargarPDFaMostrarPantallas");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(fileId);
		ZeniContextServer.getInstance().printVerboLog(categoryId);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			if (fileId == null || fileId.equals(ZeniContextServer.VACIO)) {
				throw new ZeniServerExeption("El id del archivo no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
			}
			if (categoryId == null || categoryId.equals(ZeniContextServer.VACIO)) {
				throw new ZeniServerExeption("El id de la categoria no puede ser nulo o vacio", FAULTCONSTANTS.ERROR_ERRONEO);
			}
			String querySelect = new ZeniQueryBuilder().select(FileTable.FIELD_ID, FileTable.FIELD_NAME, FileTable.FIELD_EXTENSION, FileTable.FILED_CATEGORY, FileTable.FIELD_DESCRIPTION, FileTable.FIELD_MESSAGE, FileTable.FIELD_MODIF_DATE)
				.from(FileTable.FILETABLE)
				.where(FileTable.FIELD_ID, Operator.EQUALS, fileId)
				.and(FileTable.FILED_CATEGORY, Operator.EQUALS, categoryId)
				.getQuey();
			final FileType _return = new FileType();
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						_return.setNombre(rset.getString(FileTable.FIELD_NAME));
						_return.setExtension(rset.getString(FileTable.FIELD_EXTENSION));
						_return.setNombreConExtension(rset.getString(FileTable.FIELD_NAME) + "." + rset.getString(FileTable.FIELD_EXTENSION));
						_return.setMessage(rset.getString(FileTable.FIELD_MESSAGE));
						_return.setDescription(rset.getString(FileTable.FIELD_DESCRIPTION));
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
			File picturesDir = new File(ZeniContextServer.getInstance().getProperty(FileAdministrationConstanst.FILE_STORE_DIR) + fileId);
			final URL fileremote = picturesDir.toURI().toURL();
			URLConnection connection = fileremote.openConnection();
//    		BufferedInputStream bifin = new BufferedInputStream(fileremote.openStream());
			final InputStream icon = connection.getInputStream();
			final BufferedInputStream bifin = new BufferedInputStream(icon);
			final ByteArrayOutputStream bais = new ByteArrayOutputStream();
			final byte data[] = new byte[1024];
			int count;
			final byte[] _returnData;
			try {
				while ((count = bifin.read(data, 0, 1024)) != -1) {
					bais.write(data, 0, count);
				}
			} finally {
				bifin.close();
				bais.flush();
			}
			_returnData = bais.toByteArray();
			_return.setData(_returnData);
			_return.setTamanio(_returnData.length);
			bais.close();
			return _return;
		} catch (ZeniIPDifiereExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniHashInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniAuthTypeInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (IOException ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}

	public ar.com.zeni.zeniwsdl.SituacionImpositivaCabeceraType obtenerSituacionImpositivaCabecera(ar.com.zeni.zeniwsdl.AuthType auth, java.lang.String cuentaId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerSituacionImpositivaCabecera");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(cuentaId);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			final StringBuilder querySBuilderMain = new StringBuilder().append("select 'actividad' actividad, prov.nombre provincia, cc.nrocuenta, c.razonsocial, c.cuit, cc.codigosagpya, ts.nombre tiposociedad, ")
				.append("d.calle||' '||d.numero ||' '|| d.piso ||' '||d.nrooficinadpto domicilio, to_char(cc.fechaalta , 'yyyy-MM-dd') fechaalta, ")
				.append("cdad.nombre ciudad, cp.codigopostal, ")
				.append("tii.nombre categoriaiva,' ' percepcioniva, ' ' resolucion, ")
				.append("c.incluidoenrg23001394 ivaresgeneral1394,rg2300.cbu cbu, ")
				.append("tgan.nombre categoriaganancias, ")
				.append("c.ingresosbrutos nroingresosbrutos ")
				.append("from ")
				.append(owner)
				.append("cuenta_cliente cc ")
				.append("inner join ")
				.append(owner)
				.append("cliente c on (c.id=cc.cliente_id) ")
				.append("inner join ")
				.append(owner)
				.append("tipo_sociedad ts on (ts.id=c.tiposociedad_id) ")
				.append("left outer join ")
				.append(owner)
				.append("domicilio d on (d.cliente_id=cc.cliente_id and d.domicilionegocio=1) ")
				.append("left outer join ")
				.append(owner)
				.append("ciudad cdad on (cdad.id=d.ciudad_id) ")

				.append("left outer join ")
				.append(owner)
				.append("partido part on (part.id = cdad.partido_id) ")
				.append("left outer join ")
				.append(owner)
				.append("provincia prov on (prov.id = part.provincia_id) ")

				.append("left outer join ")
				.append(owner)
				.append("codigo_postal cp on (cp.id=d.codigopostal_id) ")
				.append("left outer join ")
				.append(owner)
				.append("tipo_inscripcion_impuesto tii on (tii.id=c.situacioniva_id) ")
				.append("left outer join ")
				.append(owner)
				.append("tipo_inscripcion_impuesto tgan on (tgan.id=c.tipoinscripcionganancias_id) ")
				.append("left outer join (select pr.cuit,pr.cbu from ")
				.append(owner)
				.append("padron_rg2300 pr ")
				.append("where pr.fechabaja is null ")
				.append("and pr.fechaalta = (select max(pr2.fechaalta) ")
				.append("from ")
				.append(owner)
				.append("padron_rg2300 pr2 ")
				.append("where pr2.cuit=pr.cuit ")
				.append("and pr2.fechabaja is null)) rg2300 ")
				.append("on (rg2300.cuit=c.cuit) ")
				.append("where cc.id = ")
				.append(cuentaId)
				.append(" and cc.fechabaja is null ");
			final ar.com.zeni.zeniwsdl.SituacionImpositivaCabeceraType _return = new ar.com.zeni.zeniwsdl.SituacionImpositivaCabeceraType();
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						try {
							_return.setCuenta(rset.getString("nrocuenta"));
							_return.setRazonSocial(rset.getString("razonsocial"));
							_return.setDomicilio(rset.getString("domicilio"));
							_return.setLocalidad(rset.getString("ciudad"));
							_return.setCuit(rset.getString("cuit"));
							_return.setTipoSociedad(rset.getString("tiposociedad"));
							_return.setCodPostal(rset.getString("codigopostal"));
							_return.setProvincia(rset.getString("provincia"));
							_return.setActividad(rset.getString("nrocuenta"));
							_return.setNroSAGPYA(rset.getString("codigosagpya"));
							_return.setCategoriaiva(rset.getString("categoriaiva"));
							_return.setPercepcioniva(rset.getString("percepcioniva"));
							_return.setResolucion(rset.getString("resolucion"));
							_return.setIvaresgeneral1394(rset.getString("ivaresgeneral1394"));
							_return.setCategIngresosBrutos(rset.getString("categoriaganancias"));
							_return.setCategoriaGanancias(rset.getString("nroingresosbrutos"));
							_return.setFechaAlta(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechaalta"))));
						} catch (ParseException e) {
							throw new SQLException("No se puede determinar la fecha en el query.", e);
						}
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySBuilderMain.toString(), resb);
			return _return;
		} catch (ZeniIPDifiereExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniHashInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniAuthTypeInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}

	public SituacionImpositivaIngBrutosType obtenerSituacionImpositivaIngBrutos(AuthType auth, String cuentaId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerSituacionImpositivaIngBrutos");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(cuentaId);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			SituacionImpositivaIngBrutosType _return = new SituacionImpositivaIngBrutosType();
			_return.setNumeroInscripcion("NumeroInscripcion792241595");
			ArrayOfDetallesInscripcionesType _returnArrayDetalleInscripciones = new ArrayOfDetallesInscripcionesType();
			final StringBuilder querySBuilderMain = new StringBuilder().append("select p.nombre provincia,decode(iibb.esagentepercepcion,1,'Si','No') esagentepercepcion, ")
				.append("decode(iibb.esagenteretencion,1,'Si','No') esagenteretencion, ")
				.append("tii.nombre tipoinscripcion, a.nombre actividad ")
				.append("from ")
				.append(owner)
				.append("cliente_provincia_iibb iibb ")
				.append("inner join ")
				.append(owner)
				.append("provincia p on (p.id=iibb.provincia_id) ")
				.append("inner join ")
				.append(owner)
				.append("cliente c on (c.id=iibb.cliente_id) ")
				.append("inner join ")
				.append(owner)
				.append("cuenta_cliente cc on (cc.cliente_id=c.id) ")
				.append("inner join ")
				.append(owner)
				.append("actividad a on (a.id=c.actividad_id) ")
				.append("left outer join ")
				.append(owner)
				.append("tipo_inscripcion_impuesto tii on (tii.id=c.tipoinscripcioniibb_id) ")
				.append("where iibb.fechabaja is null ")
				.append("and cc.id = ")
				.append(cuentaId);
			final java.util.List<DetallesInscripcionesType> _returnArrayDetalleInscripcionesDetas = new java.util.ArrayList<DetallesInscripcionesType>();
			final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						DetallesInscripcionesType _returnArrayDetalleInscripcionesDetasVal1 = new DetallesInscripcionesType();
						_returnArrayDetalleInscripcionesDetasVal1.setProvincia(rset.getString("provincia"));
						_returnArrayDetalleInscripcionesDetasVal1.setActividad(rset.getString("actividad"));
						_returnArrayDetalleInscripcionesDetasVal1.setInscripto(rset.getString("esagentepercepcion").equals("Si") ? SINOType.SI : SINOType.NO);
						_returnArrayDetalleInscripcionesDetasVal1.setConvMultilateral(rset.getString("esagenteretencion").equals("Si") ? SINOType.SI : SINOType.NO);
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySBuilderMain.toString(), resb);
			_returnArrayDetalleInscripciones.getDetas().addAll(_returnArrayDetalleInscripcionesDetas);
			_return.setArrayDetalleInscripciones(_returnArrayDetalleInscripciones);

			final StringBuilder querySBuilder2 = new StringBuilder().append("select p.nombre juridiccion, a.nombre actividad,pr.alicuotaretencion retencion, ")
				.append("pr.alicuotapercepcion percepcion,  to_char(pr.fechadesde , 'yyyy-MM-dd') fechadesde,to_char(pr.fechahasta , 'yyyy-MM-dd') fechahasta ")
				.append("from ")
				.append(owner)
				.append("percepcion_retencion pr ")
				.append("inner join ")
				.append(owner)
				.append("cliente_provincia_iibb iibb on (iibb.id=pr.clienteprovinciaiibb_id and iibb.fechabaja is null) ")
				.append("inner join ")
				.append(owner)
				.append("cliente c on (c.id=iibb.cliente_id) ")
				.append("inner join ")
				.append(owner)
				.append("cuenta_cliente cc on (cc.cliente_id=c.id) ")
				.append("inner join ")
				.append(owner)
				.append("actividad a on (a.id=c.actividad_id) ")
				.append("inner join ")
				.append(owner)
				.append("provincia p on (p.id=iibb.provincia_id) ")
				.append("where pr.fechabaja is null ")
				.append("and cc.id = ")
				.append(cuentaId);
			ArrayOfExtensionesIIBBType _returnArrayExtensionesIIBB = new ArrayOfExtensionesIIBBType();
			final java.util.List<ExtensionesIIBBType> _returnArrayExtensionesIIBBExtes = new java.util.ArrayList<ar.com.zeni.zeniwsdl.ExtensionesIIBBType>();
			final ResulsetObjectBuilder resb2 = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						try {
							ExtensionesIIBBType _returnArrayExtensionesIIBBExtesVal1 = new ExtensionesIIBBType();
							_returnArrayExtensionesIIBBExtesVal1.setJurisdiccion(rset.getString("juridiccion"));
							_returnArrayExtensionesIIBBExtesVal1.setActividad(rset.getString("actividad"));
							_returnArrayExtensionesIIBBExtesVal1.setNro(rset.getString("retencion"));
							_returnArrayExtensionesIIBBExtesVal1.setPorcentaje(rset.getString("percepcion"));
							_returnArrayExtensionesIIBBExtesVal1.setFechaalta(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechadesde"))));
							_returnArrayExtensionesIIBBExtesVal1.setFechavto(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechahasta"))));
							_returnArrayExtensionesIIBBExtes.add(_returnArrayExtensionesIIBBExtesVal1);
						} catch (ParseException e) {
							throw new SQLException("No se puede determinar la fecha en el query.", e);
						}
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySBuilder2.toString(), resb2);
			_returnArrayExtensionesIIBB.getExtes().addAll(_returnArrayExtensionesIIBBExtes);
			_return.setArrayExtensionesIIBB(_returnArrayExtensionesIIBB);
			return _return;
		} catch (ZeniIPDifiereExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniHashInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniAuthTypeInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}

	public SituacionImpositivaGananciasType obtenerSituacionImpositivaGanancias(AuthType auth, String cuentaId) throws FaultType_Exception {
		ZeniContextServer.getInstance().printInfoLog("Executing operation obtenerSituacionImpositivaGanancias");
		ZeniContextServer.getInstance().printVerboLog(auth);
		ZeniContextServer.getInstance().printVerboLog(cuentaId);
		try {
			final MessageContext mc = wsContext.getMessageContext();
			final HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
			AuthNHashUtil.getInstance().isAuthValid(auth, req.getRemoteAddr());
			final StringBuilder querySBuilder2 = new StringBuilder().append(" select porcexclusionrg830 porcentaje,to_char(fechahastarg830 , 'yyyy-MM-dd') fechavencimiento ")
				.append("from ")
				.append(owner)
				.append("cliente_situacion_ganancias csg ")
				.append("inner join ")
				.append(owner)
				.append("cuenta_cliente cc on (cc.cliente_id=csg.cliente_id) ")
				.append("where csg.fecha = (select max(cs.fecha) ")
				.append("from ")
				.append(owner)
				.append("cliente_situacion_ganancias cs ")
				.append("where cs.cliente_id = csg.cliente_id ")
				.append("and cs.fechabaja is null) ")
				.append("and csg.fechahastarg830 is not null ")
				.append("and cc.id = ")
				.append(cuentaId);
			SituacionImpositivaGananciasType _return = new SituacionImpositivaGananciasType();
			_return.setCategoria("INSCRIPTO");
			ArrayOfExtensionesType _returnArrayExtensionesIIBB = new ArrayOfExtensionesType();
			final java.util.List<ExtensionesType> _returnArrayExtensionesIIBBExtes = new java.util.ArrayList<ExtensionesType>();
			final ResulsetObjectBuilder resb2 = new ResulsetObjectBuilder() {
				@Override
				public void thisIsTheResulset(ResultSet rset) throws SQLException {
					while (rset.next()) {
						try {
							ExtensionesType _returnArrayExtensionesIIBBExtesVal1 = new ExtensionesType();
							_returnArrayExtensionesIIBBExtesVal1.setPorcentaje(rset.getString("porcentaje"));
							_returnArrayExtensionesIIBBExtesVal1.setFechaalta(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechavencimiento"))));
							_returnArrayExtensionesIIBBExtesVal1.setFechavto(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMdd(rset.getString("fechavencimiento"))));
							_returnArrayExtensionesIIBBExtesVal1.setNro("Nro-1793867924");
							_returnArrayExtensionesIIBBExtes.add(_returnArrayExtensionesIIBBExtesVal1);
						} catch (ParseException e) {
							throw new SQLException("No se puede determinar la fecha en el query.", e);
						}
					}
				}
			};
			ZeniQueryExcecutor.excecuteSelect(querySBuilder2.toString(), resb2);
			_returnArrayExtensionesIIBB.getExtes().addAll(_returnArrayExtensionesIIBBExtes);
			_return.setArrayExtensionesIIBB(_returnArrayExtensionesIIBB);
			return _return;
		} catch (ZeniIPDifiereExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniHashInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniAuthTypeInvalidExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (ZeniDBExeption ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(ex.getCode());
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		} catch (Exception ex) {
			FaultType f = new FaultType();
			f.setMensaje(ex.getMessage());
			f.setCodigoError(FAULTCONSTANTS.ERROR_ERRONEO);
			ZeniContextServer.getInstance().printErrorLog(ex);
			throw new FaultType_Exception(ex.getMessage(), f, ex);
		}
	}
}
