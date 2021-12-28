package ar.com.zeni.mail.implementation;

import java.io.Serializable;
import java.util.ArrayList;

import ar.com.zeni.mail.Mail;


/**
 * This <b>class</b> implements an interface to send an email, you can set all
 * the attributes from a mail <b>from</b> address <b>to</b> addresses
 * <b>subjetc</b> <b>body</b>
 * 
 * @author rmolina
 * 
 */
public class MailImplementation implements Mail, Serializable {
	private static final long	serialVersionUID	= 1L;

	public MailImplementation(String from, ArrayList<String> to, String subject, String body) {
		super();
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.body = body;
	}

	/**
	 * Sets the addresses to send this email
	 * 
	 * @param to
	 *            an arraylist with addresses to send this email
	 */
	public void setTo(ArrayList<String> to) {
		this.to = to;
	}

	/**
	 * Sets the address from send this email
	 * 
	 * @param from
	 *            an address to send this email
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Sets the subject for this mail
	 * 
	 * @param subject
	 *            for this mail
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Sets the body for the mail
	 * 
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	private ArrayList<String>	to;
	private String				from;
	private String				subject;
	private String				body;

	public ArrayList<String> getTo() {
		return to;
	}

	public String getFrom() {
		return from;
	}

	public String getSubject() {
		return subject;
	}

	public String getBody() {
		return body;
	}
}
