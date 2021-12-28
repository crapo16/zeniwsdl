package ar.com.zeni.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import ar.com.zeni.zeniwsdl.FechaTimeType;

/**
 * Utilidades para fechas.
 *
 * @see Converters
 * @see Parsers
 * @see DateMetrics
 * @see Formats
 * @see ToString
 * @author rodrigo
 *
 */
public class DateUtil {
	/**
	 * Formatos varios
	 *
	 * @author rodrigo
	 *
	 */
	public interface Formats {
		/**
		 * Formato "yyyyddMMSSSssHHmm"
		 */
		static public final String	yyyyddMMSSSssHHmm	= "yyyyddMMSSSssHHmm";
		/**
		 * Formato "dd-MM-yyyy"
		 */
		static public final String	ddMMyyyy			= "dd-MM-yyyy";
		
		
		/**
		 * Formato "yyyy-MM-dd HH:mm:ss"
		 */
		static public final String	yyyyMMddHHmmss		= "yyyy-MM-dd HH:mm:ss";
		/**
		 * Formato "yyyyMMddHHmmssSSS"
		 */
		static public final String	yyyyMMddHHmmssSSS	= "yyyyMMddHHmmssSSS";
		/**
		 * Formato "yyyy-MM-dd"
		 */
		static public final String	yyyyMMdd			= "yyyy-MM-dd";

		/**
		 * Formato "yyyyMMdd"
		 */
		static public final String	yyyyMMddSimple		= "yyyyMMdd";

		/**
		 * Formato "fecha - hora-mm-ss"
		 */
		static public final String fechahorammss		= "yyyy-MM-dd - HH-mm-ss";
	}

	/**
	 * Unidades de fhechas
	 *
	 * @author rodrigo
	 *
	 */
	public interface DateMetrics {
		public static long	segundoenmillis		= 1000;
		public static long	minutoenmillis		= 60 * segundoenmillis;
		public static long	horaenmillis		= 60 * minutoenmillis;
		public static long	diaenmillis			= 24 * horaenmillis;
		public static long	semanaenmillis		= 7 * diaenmillis;
		public static long	segundoensegundos	= 1;
		public static long	minutoensegundos	= 60 * segundoensegundos;
		public static long	horaensegundos		= 60 * minutoensegundos;
		public static long	diaensegundos		= 24 * horaensegundos;
		public static long	semanaensegundos	= 7 * diaensegundos;
	}

	static private final SimpleDateFormat	yyyyddMMSSSssHHmmDateFormatter	= new SimpleDateFormat(Formats.yyyyddMMSSSssHHmm);
	static private final SimpleDateFormat	ddMMyyyyDateFormatter			= new SimpleDateFormat(Formats.ddMMyyyy);
	static private final SimpleDateFormat	yyyyMMddHHmmssDateFormatter		= new SimpleDateFormat(Formats.yyyyMMddHHmmss);
	static private final SimpleDateFormat	yyyyMMddHHmmssSSSDateFormatter	= new SimpleDateFormat(Formats.yyyyMMddHHmmssSSS);
	static private final DateFormat			yyyyMMddDateFormatter			= new SimpleDateFormat(Formats.yyyyMMdd);
	static private final SimpleDateFormat	yyyyMMddSimpleDateFormatter		= new SimpleDateFormat(Formats.yyyyMMddSimple);
	static private final SimpleDateFormat	fechahorammssFormatter			= new SimpleDateFormat(Formats.fechahorammss);

	/**
	 * Parsers de fechas
	 *
	 * @author rodrigo
	 *
	 */
	public static class Parsers {
		/**
		 * {@link DateUtil.Formats.ddMMyyyy}
		 *
		 * @param ftt
		 * @return
		 */
		public static Date ddmmyyyy(String string) throws ParseException {
			if (string == null) {
				return null;
			}
			return ddMMyyyyDateFormatter.parse(string);
		}

		/**
		 * {@link DateUtil.Formats.yyyyddMMSSSssHHmm}
		 *
		 * @param ftt
		 * @return
		 */
		public static Date yyyyddMMSSSssHHmm(String string) throws ParseException {
			if (string == null) {
				return null;
			}
			return yyyyddMMSSSssHHmmDateFormatter.parse(string);
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMddHHmmsss}
		 *
		 * @param ftt
		 * @return
		 */
		public static Date yyyyMMddHHmmsss(String string) throws ParseException {
			if (string == null) {
				return null;
			}
			return yyyyMMddHHmmssDateFormatter.parse(string);
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMddHHmmssSSS}
		 *
		 * @param ftt
		 * @return
		 */
		public static Date yyyyMMddHHmmssSSS(String string) throws ParseException {
			if (string == null) {
				return null;
			}
			return yyyyMMddHHmmssSSSDateFormatter.parse(string);
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMdd}
		 *
		 * @param ftt
		 * @return
		 */
		public static Date yyyyMMdd(String string) throws ParseException {
			if (string == null || "".equals(string.trim())) {
				return null;
			}
			return yyyyMMddDateFormatter.parse(string);
		}

		/**
		 * {@link DateUtil.Formats.fechahorammss}
		 *
		 * @param ftt
		 * @return
		 */
		public static Date fechahorammss(String string) throws ParseException {
			if (string == null) {
				return null;
			}
			return fechahorammssFormatter.parse(string);
		}

	}

	/**
	 * De formatos de fecha a string
	 *
	 * @author rodrigo
	 *
	 */
	public static class ToString {
		/**
		 * {@link DateUtil.Formats.ddMMyyyy}
		 *
		 * @param ftt
		 * @return
		 */
		public static String ddMMyyyy(FechaTimeType ftt) {
			if (ftt != null)
				return ddMMyyyy(ftt.getFecha());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.ddMMyyyy}
		 *
		 * @param ftt
		 * @return
		 */
		public static String ddMMyyyy(XMLGregorianCalendar xgc) {
			if (xgc != null)
				return ddMMyyyy(xgc.toGregorianCalendar().getTime());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.ddMMyyyy}
		 *
		 * @param ftt
		 * @return
		 */
		public static String dateFormat(XMLGregorianCalendar xgc, String format) {
			if (xgc != null) {
				try {
				SimpleDateFormat dFormat= new SimpleDateFormat(format);
				return dFormat.format(xgc.toGregorianCalendar().getTime());
				}catch (Exception e) {
					return null;
				}
			}
			return null;
		}

		/**
		 * {@link DateUtil.Formats.ddMMyyyy}
		 *
		 * @param ftt
		 * @return
		 */
		public static String ddMMyyyy(Date date) {
			if (date != null)
				return ddMMyyyyDateFormatter.format(date);
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyddMMSSSssHHmm}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyddMMSSSssHHmm(FechaTimeType ftt) {
			if (ftt != null)
				return yyyyddMMSSSssHHmm(ftt.getFecha());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyddMMSSSssHHmm}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyddMMSSSssHHmm(XMLGregorianCalendar xgc) {
			if (xgc != null)
				return yyyyddMMSSSssHHmm(xgc.toGregorianCalendar().getTime());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyddMMSSSssHHmm}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyddMMSSSssHHmm(Date date) {
			if (date != null)
				return yyyyddMMSSSssHHmmDateFormatter.format(date);
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMddHHmmss}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyMMddHHmmss(FechaTimeType ftt) {
			if (ftt != null)
				return yyyyMMddHHmmss(ftt.getFecha());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMddHHmmss}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyMMddHHmmss(XMLGregorianCalendar xgc) {
			if (xgc != null)
				return yyyyMMddHHmmss(xgc.toGregorianCalendar().getTime());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMddHHmmss}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyMMddHHmmss(Date date) {
			if (date != null)
				return yyyyMMddHHmmssDateFormatter.format(date);
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMddHHmmssSSS}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyMMddHHmmssSSS(FechaTimeType ftt) {
			if (ftt != null)
				return yyyyMMddHHmmssSSS(ftt.getFecha());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMddHHmmssSSS}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyMMddHHmmssSSS(XMLGregorianCalendar xgc) {
			if (xgc != null)
				return yyyyMMddHHmmssSSS(xgc.toGregorianCalendar().getTime());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMddHHmmssSSS}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyMMddHHmmssSSS(Date date) {
			if (date != null)
				return yyyyMMddHHmmssSSSDateFormatter.format(date);
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMdd}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyMMdd(Date date) {
			if (date != null)
				return yyyyMMddDateFormatter.format(date);
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMdd}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyMMdd(FechaTimeType ftt) {
			if (ftt != null)
				return yyyyMMdd(ftt.getFecha());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMdd}
		 *
		 * @param ftt
		 * @return
		 */
		public static String yyyyMMdd(XMLGregorianCalendar xgc) {
			if (xgc != null)
				return yyyyMMdd(xgc.toGregorianCalendar().getTime());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.fechahorammss}
		 *
		 * @param ftt
		 * @return
		 */
		public static String fechahorammss(Date date) {
			if (date != null)
				return fechahorammssFormatter.format(date);
			return null;
		}

		/**
		 * {@link DateUtil.Formats.fechahorammss}
		 *
		 * @param ftt
		 * @return
		 */
		public static String fechahorammss(FechaTimeType ftt) {
			if (ftt != null)
				return fechahorammss(ftt.getFecha());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.fechahorammss}
		 *
		 * @param ftt
		 * @return
		 */
		public static String fechahorammss(XMLGregorianCalendar xgc) {
			if (xgc != null)
				return fechahorammss(xgc.toGregorianCalendar().getTime());
			return null;
		}

		/**
		 * {@link DateUtil.Formats.yyyyMMddSimple}
		 *
		 * @param date
		 * @return String yyyyMMddSimple
		 */
		public static String yyyyMMddSimple(Date date) {
			if (date != null)
				return yyyyMMddSimpleDateFormatter.format(date);
			return null;
		}
	}

	/**
	 * Ofrece metodos para convertir de un tipo de fecha a otro
	 *
	 * @author rodrigo
	 *
	 */
	public static class Converters {
		/**
		 * Needed to create XMLGregorianCalendar instances
		 */
		private static DatatypeFactory	df	= null;
		static {
			try {
				df = DatatypeFactory.newInstance();
			} catch (DatatypeConfigurationException dce) {
				throw new IllegalStateException("Exception while obtaining DatatypeFactory instance", dce);
			}
		}

		/**
		 * Converts a java.util.Date into an instance of XMLGregorianCalendar
		 *
		 * @param date
		 *            Instance of java.util.Date or a null reference
		 * @return XMLGregorianCalendar instance whose value is based upon the
		 *         value in the date parameter. If the date parameter is null
		 *         then this method will simply return null.
		 */
		public static XMLGregorianCalendar DateToXMLGregorianCalendar(java.util.Date date) {
			if (date == null) {
				return null;
			} else {
				final GregorianCalendar gc = new GregorianCalendar();
				gc.setTimeInMillis(date.getTime());
				return df.newXMLGregorianCalendar(gc);
			}
		}

		/**
		 * Converts a java.util.Date into an instance of FechaTimeType
		 *
		 * @param date
		 *            Instance of java.util.Date or a null reference
		 * @return FechaTimeType instance whose value is based upon the value in
		 *         the date parameter. If the date parameter is null then this
		 *         method will simply return null.
		 */
		public static FechaTimeType DateToFechaTimeType(java.util.Date date) {
			if (date == null) {
				return null;
			} else {
				final FechaTimeType f = new FechaTimeType();
				f.setFecha(DateToXMLGregorianCalendar(date));
				return f;
			}
		}

		/**
		 * Converts an XMLGregorianCalendar to an instance of java.util.Date
		 *
		 * @param xgc
		 *            Instance of XMLGregorianCalendar or a null reference
		 * @return java.util.Date instance whose value is based upon the value
		 *         in the xgc parameter. If the xgc parameter is null then this
		 *         method will simply return null.
		 */
		public static java.util.Date XMLGregorianCalendarToDate(XMLGregorianCalendar xgc) {
			if (xgc == null) {
				return null;
			} else {
				return xgc.toGregorianCalendar().getTime();
			}
		}

		/**
		 * Converts an FechaTimeType to an instance of java.util.Date
		 *
		 * @param ftt
		 *            Instance of FechaTimeType or a null reference
		 * @return java.util.Date instance whose value is based upon the value
		 *         in the ftt parameter. If the ftt parameter is null then this
		 *         method will simply return null.
		 */
		public static java.util.Date FechaTimeTypeToDate(FechaTimeType ftt) {
			if (ftt == null) {
				return null;
			} else {
				return XMLGregorianCalendarToDate(ftt.getFecha());
			}
		}

		/**
		 * Converts a FechaTimeType into an instance of XMLGregorianCalendar
		 *
		 * @param date
		 *            Instance of FechaTimeType or a null reference
		 * @return XMLGregorianCalendar instance whose value is based upon the
		 *         value in the date parameter. If the date parameter is null
		 *         then this method will simply return null.
		 */
		public static XMLGregorianCalendar DateToXMLGregorianCalendar(FechaTimeType ftt) {
			if (ftt == null) {
				return null;
			} else {
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(XMLGregorianCalendarToDate(ftt.getFecha()));
				return df.newXMLGregorianCalendar(gc);
			}
		}

		/**
		 * Converts a XMLGregorianCalendar into an instance of FechaTimeType
		 *
		 * @param date
		 *            Instance of XMLGregorianCalendar or a null reference
		 * @return FechaTimeType instance whose value is based upon the value in
		 *         the date parameter. If the date parameter is null then this
		 *         method will simply return null.
		 */
		public static FechaTimeType XMLGregorianCalendarToFechaTimeType(XMLGregorianCalendar xgc) {
			if (xgc == null) {
				return null;
			} else {
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(XMLGregorianCalendarToDate(xgc));
				XMLGregorianCalendar ss = df.newXMLGregorianCalendar(gc);
				final FechaTimeType f = new FechaTimeType();
				f.setFecha(ss);
				return f;
			}
		}
	}
}
