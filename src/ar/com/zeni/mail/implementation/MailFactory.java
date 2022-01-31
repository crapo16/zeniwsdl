package ar.com.zeni.mail.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.tables.MailTable;
import ar.com.zeni.mail.Mail;

public class MailFactory {
	private final Connection con;
	
	public MailFactory(Connection connection){
		con = connection;
	}
	public Mail createMailFromDb( String id_mail_to_send) {
		return new SMailFromDb(id_mail_to_send);
	}
	private class SMailFromDb implements Mail {
		public SMailFromDb(String id_mail_to_send) {
			String sqlMailSelect = prepareStmtMailSelect(id_mail_to_send);
			PreparedStatement stmt = null;
			ResultSet rset = null;
			try {
				try {
					stmt = con.prepareStatement(sqlMailSelect);
				} catch (SQLException e) {
//					try {stmt.close();} catch (SQLException e1) {}
					ZeniContextServer.getInstance().printInfoLog("Can't create stmt for mail:" + e.getMessage());
					throw new RuntimeException(e.getMessage());
				}
				try {
					rset = stmt.executeQuery();
				} catch (SQLException e){
//					try {rset.close();} catch (SQLException e1) {}
					ZeniContextServer.getInstance().printInfoLog("Can't execute stmt for mail:" + e.getMessage());
					throw new RuntimeException(e.getMessage());
				}
				try { // reading mail from dbase
					if (rset.next()) {
						
						from = rset.getString(MailTable.FROM_FIELD);
						to = new ArrayList<String>();
						String tostr = rset.getString(MailTable.TO_FIELD);
						StringTokenizer tkzr = new StringTokenizer(tostr, ";");
						while ( tkzr.hasMoreTokens() ){ to.add(tkzr.nextToken()); }
						subject = rset.getString(MailTable.SUBJECT_FIELD);
						body = rset.getString(MailTable.BODY_FIELD);
					}
				} catch (SQLException e) {
					throw e;
				} finally { // closing
					try {stmt.close();} catch (SQLException e1) {}
					try {rset.close();} catch (SQLException e1) {}
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		private String prepareStmtMailSelect(String id_mail_to_send) {
			return "SELECT " +
					MailTable.FROM_FIELD_W_TABLE_ALIAS + ", " +
					MailTable.TO_FIELD_W_TABLE_ALIAS + ", " +
					MailTable.SUBJECT_FIELD_W_TABLE_ALIAS + ", " +
					MailTable.BODY_FIELD_W_TABLE_ALIAS +
				" FROM " +
					MailTable.TABLE_NAME_W_TABLE_ALIAS +
				" WHERE " +
//					MailTable.ID_FIELD_W_TABLE_ALIAS + " = " + AttachmentTable.ID_MAIL_FIELD_W_TABLE_ALIAS + " (+) " +
//				" AND " + 
					MailTable.ID_FIELD_W_TABLE_ALIAS + " = '" + id_mail_to_send + "'";
		}
		private ArrayList<String> to;
		private String from;
		private String subject;
		private String body;
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
}
