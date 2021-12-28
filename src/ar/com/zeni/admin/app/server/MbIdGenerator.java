package ar.com.zeni.admin.app.server;

import java.util.Date;
import java.util.Random;


/**
 * <p>Title: Moear.com.apertura.moebius.serveranejo de IDs</p>
 * <p>Copyright: Apertura Software S.A. -  2004</p>
 * <p>Company: Apertura Software S.A.</p>
 * @author Juan Antonio Quiroga
 * @version 2.2.1.1
 */

final public class MbIdGenerator {
	private static MbIdGenerator sidpk;
	private static long cnt;
	private MbIdGenerator() {
		Random generator = new Random();
		cnt = generator.nextInt(65536);
	}
	public static MbIdGenerator getInstance() {
		if (sidpk==null){
			sidpk = new MbIdGenerator();
		}
		return sidpk;
	}

	public String nextId(){
		return new String(padZeroes(Long.toString((new Date().getTime()*10000)+getCnt(), 10), 20, '0'));
	}

	private synchronized long getCnt() {
		return cnt = cnt >= 9999 ? 0 : ++cnt;
//		return cnt;
	}

	private char[] padZeroes(String str, final int size, final char padChar) {
		final int len = size - str.length();
		final char[] temp = new char[size];
		int i = 0;
		while (i < len) {
			temp[i] = padChar;
			i++;
		}
		int j = 0;
		while (i < size) {
			temp[i] = str.charAt(j);
			i++;j++;
		}
		return temp;
	}
}
