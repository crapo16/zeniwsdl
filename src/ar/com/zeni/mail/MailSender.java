package ar.com.zeni.mail;

import java.util.ArrayList;

import ar.com.zeni.common.exceptions.ZeniMailServerExeption;
import ar.com.zeni.mail.implementation.MailImplementation;
import ar.com.zeni.mail.implementation.ServerMailCore;

/**
 * Clase para enviar mails.
 * @author rodrigo
 * @see MailSender#sendMail(String, ArrayList, String, String)
 * @see MailSender#sendMailInAFuture(String, ArrayList, String, String)
 *
 */
public class MailSender {
	static MailServer instance;
	static Object synch = new Object();
	private static MailServer getInstance() {
		synchronized (synch) {
			if ( instance == null){
				instance = new ServerMailCore();
			}
		}
		return instance;
	}
	/**
	 * Envia texto desde from hasta to del message
	 * @param from
	 * @param to
	 * @param subject
	 * @param message
	 * @throws ZeniMailServerExeption 
	 */
	public static void sendMail(final String from, final ArrayList<String> to, final String subject, final String message) throws ZeniMailServerExeption {
		MailServer ms = getInstance();
		Mail mail = new MailImplementation(from, to, subject, message);
		ms.sendMail(mail);
	}

	/**
	 * Envia texto desde from hasta to del message
	 * @param from
	 * @param to
	 * @param subject
	 * @param message
	 * @throws ZeniMailServerExeption
	 */
	public static void sendMailInAFuture(final String from, final ArrayList<String> to, final String subject, final String message) {
		Thread t = new Thread( new Runnable() {
			@Override
			public void run() {
				MailServer ms = getInstance();
				Mail mail = new MailImplementation(from, to, subject, message);
				try {
					ms.sendMail(mail);
				} catch (ZeniMailServerExeption e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
	}
}
