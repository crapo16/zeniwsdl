package ar.com.zeni.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.xml.datatype.XMLGregorianCalendar;

import ar.com.zeni.codecs.GenericCodec;
import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniAuthTypeInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniBaseExeption;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.common.exceptions.ZeniHashInvalidExeption;
import ar.com.zeni.common.exceptions.ZeniIPDifiereExeption;
import ar.com.zeni.db.ZeniInsertBuilder;
import ar.com.zeni.db.ZeniQueryBuilder;
import ar.com.zeni.db.ZeniQueryBuilder.Operator;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.db.ZeniUpdateBuilder;
import ar.com.zeni.zeniwsdl.AuthType;
import ar.com.zeni.zeniwsdl.FechaTimeType;
import ar.com.zeni.zeniwsdl.HashNSeedType;

/**
 * Maneja los hash tanto como seed validos o no.
 * @author rodrigo
 *
 */
public class AuthNHashUtil {
	public static AuthNHashUtil getInstance(){
		synchronized (updataSync) {
			if (instance==null){
				instance = new AuthNHashUtil();
			}
		}
		return instance;
	}
//	private long aleatorio = (long) (Math.cos(Math.random())*100000l);
	private Random random = new Random();
	public HashNSeedType getNewHashNSeed(String ip) throws ZeniDBExeption {
		removeOldHashes();
		final Calendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(calendar.getTimeInMillis() + DateUtil.DateMetrics.diaenmillis + DateUtil.DateMetrics.horaenmillis);
		final XMLGregorianCalendar gc = DateUtil.Converters.DateToXMLGregorianCalendar(calendar.getTime());
		final FechaTimeType fecha = new FechaTimeType();
		fecha.setFecha(gc);
		final HashNSeedInternal hashNseed = new HashNSeedInternal();
		hashNseed.setValidezHasta(fecha);
		hashNseed.setIp(ip);
		synchronized (updataSync) {
			long oo =  fecha.getFecha().getMillisecond() * 100000l +
			fecha.getFecha().getSecond() * 100000000l +
			fecha.getFecha().getMinute() * 10000000000l +
			fecha.getFecha().getHour()   * 10000000000000l + 
			fecha.getFecha().getDay()    * 10000000000000000l +
			random.nextInt(99999)        * 10000000000000000l;
//			aleatorio=(long) (Math.cos(Math.random())*100000l);
			oo += random.nextInt(99999);
//			final String toHash = fecha.toString()+aleatorio;
			final String toHash = Long.toString(oo);
			try {
				hashNseed.setHash(GenericCodec.stringToBase64(GenericCodec.digestSHA1(toHash)));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				hashNseed.setHash(GenericCodec.stringToBase64(Integer.toString(toHash.hashCode())));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				hashNseed.setHash(GenericCodec.stringToBase64(Integer.toString(toHash.hashCode())));
			}
			hashNseed.setSeed(GenericCodec.stringToBase64(toHash));
		}
		addHashNewHash(hashNseed);
		return hashNseed;
	}
	public boolean isAuthValid(AuthType auth, String ip) throws ZeniIPDifiereExeption, ZeniAuthTypeInvalidExeption, ZeniHashInvalidExeption {
		long currentDate = new Date().getTime() - DateUtil.DateMetrics.horaenmillis;
		if (!ZeniContextServer.getInstance().isAuthEnabled()) {
			return true;
		}
		if (auth!=null&&auth.getHash()!=null) {
			HashNSeedInternal hns = getHashFromStore(auth.getHash());
			if (hns != null && currentDate < hns.getValidezHasta().getFecha().toGregorianCalendar().getTimeInMillis() && hns.isValid()){
				if (ip.equals(hns.getIp())){
					return true;
				} else {
					throw new ZeniIPDifiereExeption();
				}
			} else {
				throw new ZeniHashInvalidExeption();
			}
		} else {
			throw new ZeniAuthTypeInvalidExeption();
		}
	}
	public AuthType generateAuth(String hash, String usuario) throws ZeniAuthTypeInvalidExeption, ZeniDBExeption, ZeniHashInvalidExeption {
		HashNSeedInternal hns = getHashFromStore(hash);
		setHashAsValid(hash, usuario);
		AuthType a = new AuthType();
		a.setHash(hns.getHash());
		a.setUser(usuario);
		a.setValidezHasta(hns.getValidezHasta());
		return a;
	}
	public HashNSeedInternal getHashNSeed(String hash) throws ZeniHashInvalidExeption {
		synchronized (readSync) {
			HashNSeedInternal hnse = getHashFromStore(hash);
			if (hnse!=null){
				return hnse;
			}
		}
		throw new ZeniHashInvalidExeption();
	}
	private AuthNHashUtil() {
		init();
	}
	private void init() {
		synchronized (updataStoreSync) {
			synchronized (readStoreSync) {
				lastRemoveDate = new Date().getTime();
				hashMap = new HashMap<String, HashNSeedInternal>();
				try {
					initializeValues();
				} catch (ZeniBaseExeption e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void setHashAsValid(String hash, String usuario) throws ZeniAuthTypeInvalidExeption, ZeniDBExeption, ZeniHashInvalidExeption {
		HashNSeedInternal hnse = getHashFromStore(hash);
		if (hnse!=null && !hnse.isValid()){
			hnse.setValid(true);
			hnse.setUser(usuario);
			updateHashNSeed(hnse);
		} else {
			throw new ZeniHashInvalidExeption();
		}
	}
	private HashNSeedInternal getHashFromStore(String hash) {
		synchronized (readStoreSync) {
			return hashMap.get(hash);
		}
	}
	private HashMap<String, HashNSeedInternal> getHashMap() {
		synchronized (readStoreSync) {
			return hashMap;
		}
	}
	private void addHashToStore( HashNSeedInternal toAdd){
		synchronized (updataStoreSync) {
			synchronized (readStoreSync) {
				getHashMap().put(toAdd.getHash(), toAdd);
			}
		}
	}
	private void removeHashFromStore( HashNSeedInternal toRemove) {
		synchronized (updataStoreSync) {
			synchronized (readStoreSync) {
				getHashMap().remove(toRemove.getHash());
			}
		}
	}
	private void removeOldHashes() {
		long currentDate = new Date().getTime() - DateUtil.DateMetrics.horaenmillis;
		synchronized (updataSync) {
			if ( (currentDate - lastRemoveDate) > DateUtil.DateMetrics.horaenmillis) {
				synchronized (updataStoreSync) {
					synchronized (readStoreSync) {
						List<HashNSeedInternal> listHash = new ArrayList<HashNSeedInternal>(getHashMap().values());
						for (HashNSeedInternal e : listHash){
							if ( currentDate > e.getValidezHasta().getFecha().toGregorianCalendar().getTimeInMillis() ){
								removeHashFromStore(e);
							}
						}
					}
				}
				lastRemoveDate = currentDate;
			}
		}
	}
	private static AuthNHashUtil instance;
	private static Object readSync = new Object();
	private static Object readStoreSync = new Object();
	private static Object updataSync = new Object();
	private static Object updataStoreSync = new Object();
	private HashMap<String, HashNSeedInternal>	hashMap;
	private long	lastRemoveDate;
	public interface HashAuthTable {
		public static String	TABLE_NAME		= ZeniContextServer.getInstance().getDBWebOwner() + "WEB_HASH_TABLE";
		public static String	HASH_FIELD		= "HASH";
		public static String	SEED_FIELD		= "SEED";
		public static String	IP_FIELD		= "IP";
		public static String	VALID_FIELD		= "IS_VALID";
		public static String	EXP_DATE_FIELD	= "EXP_DATE";
		public static String	TRUE			= "T";
		public static String	FALSE			= "F";
		public static String	USER_FIELD		= "USER_NAME";
	}
	private void initializeValues() throws ZeniDBExeption {
		borrarViejos();
		String querySelect = new ZeniQueryBuilder()
		.select(HashAuthTable.HASH_FIELD,HashAuthTable.SEED_FIELD, HashAuthTable.IP_FIELD, HashAuthTable.USER_FIELD, HashAuthTable.VALID_FIELD ,HashAuthTable.EXP_DATE_FIELD)
		.from(HashAuthTable.TABLE_NAME)
		.getQuey();
		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				while (rset.next()){
					try {
						final HashNSeedInternal hns = new HashNSeedInternal();
						hns.setHash(rset.getString(HashAuthTable.HASH_FIELD));
						hns.setSeed(rset.getString(HashAuthTable.SEED_FIELD));
						hns.setValidezHasta(DateUtil.Converters.DateToFechaTimeType(DateUtil.Parsers.yyyyMMddHHmmssSSS(rset.getString(HashAuthTable.EXP_DATE_FIELD))));
						hns.setUser(rset.getString(HashAuthTable.USER_FIELD));
						hns.setValid(rset.getString(HashAuthTable.VALID_FIELD).equals(HashAuthTable.TRUE));
						hns.setIp(rset.getString(HashAuthTable.IP_FIELD));
						addHashToStore(hns);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		};
		ZeniQueryExcecutor.excecuteSelect(querySelect, resb);
	}
	private void borrarViejos() throws ZeniDBExeption {
		ZeniQueryExcecutor.excecuteUpdate("DELETE FROM " + HashAuthTable.TABLE_NAME + " WHERE " + HashAuthTable.EXP_DATE_FIELD + " < '" + DateUtil.ToString.yyyyMMddHHmmssSSS(new Date()) + "'");	
	}
	private void addHashNSeed(HashNSeedInternal hashNseed) throws ZeniDBExeption {
		String insertQuery = new ZeniInsertBuilder()
				.insertinsertinto(HashAuthTable.TABLE_NAME)
				.fields(HashAuthTable.HASH_FIELD,
						HashAuthTable.SEED_FIELD,
						HashAuthTable.IP_FIELD,
						HashAuthTable.EXP_DATE_FIELD,
						HashAuthTable.VALID_FIELD,
						HashAuthTable.USER_FIELD)
				.values(hashNseed.getHash(),
						hashNseed.getSeed(),
						hashNseed.getIp(),
						DateUtil.ToString.yyyyMMddHHmmssSSS(hashNseed.getValidezHasta()),
						hashNseed.isValid() ? HashAuthTable.TRUE : HashAuthTable.FALSE,
						hashNseed.getUser())
				.getInsertQuery();
		ZeniQueryExcecutor.excecuteUpdate(insertQuery);
	}
	private void addHashNewHash(HashNSeedInternal hashNseed) throws ZeniDBExeption {
		addHashNSeed(hashNseed);
		addHashToStore(hashNseed);
	}
	private void updateHashNSeed(HashNSeedInternal hashNseed) throws ZeniDBExeption {
		borrarViejos();
		String updateQuery = new ZeniUpdateBuilder().update(HashAuthTable.TABLE_NAME)
				.set(HashAuthTable.VALID_FIELD, hashNseed.isValid() ? HashAuthTable.TRUE : HashAuthTable.FALSE)
				.coma(HashAuthTable.USER_FIELD, hashNseed.getUser())
				.coma(HashAuthTable.IP_FIELD, hashNseed.getIp())
				.where(HashAuthTable.HASH_FIELD, Operator.EQUALS, hashNseed.getHash())
				.getupdateQuery();
		ZeniQueryExcecutor.excecuteUpdate(updateQuery);
	}
	public class HashNSeedInternal extends HashNSeedType {
		private String ip;
		private String user;
		private boolean valid;
		public boolean isValid() {
			return valid;
		}
		public void setValid(boolean valid) {
			this.valid = valid;
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
	}
}
