package ar.com.zeni.mail.implementation;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.mail.Mail;
import ar.com.zeni.mail.MailServerConstants;

public class ServerMailDispatcher {
	private static ServerMailDispatcher	instance;

	public static ServerMailDispatcher getInstance() {
		if (instance != null) {
			instance = new ServerMailDispatcher();
		}
		return instance;
	}

	public synchronized void sendMailSynch(Mail mail2send, Properties prop) throws MessagingException {
		ZeniContextServer.getInstance().printInfoLog("Mail from " + mail2send.getFrom() + " subject " + mail2send.getSubject());
		if (prop.getProperty(MailServerConstants.PROP_SSL_ENABLE).equals(MailServerConstants.TRUE_STRING_VALUE)) {
			sendMailSSL(mail2send, prop);
		} else {
			sendMailNoSsl(mail2send, prop);
		}
	}

	public void sendMail(Mail mail2send, Properties prop) throws MessagingException {
		synchronized (this) {
			sendMailSynch(mail2send, prop);
		}
	}

	// Envio de mails
	private void sendMailNoSsl(Mail mail2send, Properties prop) throws javax.mail.MessagingException {
		Session session = null;
		if (prop.getProperty(MailServerConstants.PROP_AUTH_ENABLE).equals(MailServerConstants.TRUE_STRING_VALUE)) {
			session = Session.getDefaultInstance(
					prop,
					new SMTPAuthenticator(prop.getProperty(MailServerConstants.PROP_USER), prop
							.getProperty(MailServerConstants.PROP_PASS)));
		} else {
			session = Session.getDefaultInstance(prop, null);
		}
		MimeMessage m = composeMail(mail2send, session);
		Transport.send(m);
	}

	// Envio de mails
	private void sendMailSSL(Mail mail2send, Properties prop) throws javax.mail.MessagingException {
		if (mail2send==null) {
			sendMailGoogle(mail2send, prop);//es para cuando es casi ssl con puerto 5**
		}
		Authenticator auth = new SMTPAuthenticator(prop.getProperty(MailServerConstants.PROP_USER), prop.getProperty(MailServerConstants.PROP_PASS));
		Session session = Session.getDefaultInstance(prop, auth);
		Message msg = composeMail(mail2send, session);

		Transport t = session.getTransport("smtp"); // bueh, falto la constante.
		t.connect(prop.getProperty(MailServerConstants.PROP_HOST), prop.getProperty(MailServerConstants.PROP_USER),
				prop.getProperty(MailServerConstants.PROP_PASS));
		
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
	}

	private void sendMailGoogle(Mail mail2send, Properties prp) {
		try {
			// Propiedades de la conexión
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", prp.getProperty("mail.smtp.host"));
			props.setProperty("mail.smtp.starttls.enable", prp.getProperty("mail.smtp.starttls.enable"));
			props.setProperty("mail.smtp.port", prp.getProperty("mail.smtp.port"));
			props.setProperty("mail.smtp.user", prp.getProperty("mail.smtp.user"));
			props.setProperty("mail.smtp.auth", prp.getProperty("mail.smtp.auth"));
			// Preparamos la sesion
			Session session = Session.getDefaultInstance(props);
			// Construimos el mensaje
			Message message = composeMail(mail2send, session);
			// Lo enviamos.
			Transport t = session.getTransport("smtp");
			t.connect(prp.getProperty(MailServerConstants.PROP_HOST), prp.getProperty(MailServerConstants.PROP_USER),
					prp.getProperty(MailServerConstants.PROP_PASS));
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	private class SMTPAuthenticator extends javax.mail.Authenticator {
		String	d_user	= null;
		String	d_pass	= null;

		public SMTPAuthenticator(String user, String pass) {
			d_user = user;
			d_pass = pass;
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(d_user, d_pass);
		}
	}

	private MimeMessage composeMail(Mail mail2send, Session session) throws MessagingException {
		MimeMessage mail = new MimeMessage(session);
		Address from = new InternetAddress(mail2send.getFrom());
		mail.setFrom(from);
		mail.setReplyTo(new Address[] { from });
		InternetAddress[] to = new InternetAddress[mail2send.getTo().size()];
		for (int i = 0; i < mail2send.getTo().size(); i++) {
			to[i] = new InternetAddress(mail2send.getTo().get(i).toString());
		}
		mail.setRecipients(Message.RecipientType.TO, to);
		mail.setSubject(mail2send.getSubject());
		mail.setSentDate(new Date());
		mail.setContent(mail2send.getBody(), MailServerConstants.MIME_TYPE_PLAIN_TEXT);
//		MimeBodyPart mbp1 = new MimeBodyPart();
//		mbp1.setText(mail2send.getBody());
//
//		// create the Multipart and add its parts to it
//		Multipart mp = new MimeMultipart();
//		mp.addBodyPart(mbp1);
//
//		mail.setContent(mp);
		mail.setSentDate(new Date());
		return mail;
	};
}
