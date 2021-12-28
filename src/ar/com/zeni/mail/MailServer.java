package ar.com.zeni.mail;

import ar.com.zeni.common.exceptions.ZeniMailServerExeption;

public interface MailServer {
	   /**
	    * this method generates a mail to queue the mail
	 * @throws ZeniDBExeption 
	    */
		public void storeMail( Mail mail) throws ZeniMailServerExeption ;

	   /**
	    * this method generates a mail to send instantly
	 * @throws ZeniDBExeption 
	    */
		public void sendMail( Mail param) throws ZeniMailServerExeption ;
}
