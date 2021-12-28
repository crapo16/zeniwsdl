package ar.com.zeni.mail.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;

import ar.com.zeni.common.DateUtil;
import ar.com.zeni.common.ZeniContextServer;
import ar.com.zeni.common.exceptions.ZeniDBExeption;
import ar.com.zeni.common.exceptions.ZeniMailServerExeption;
import ar.com.zeni.common.tables.MailAudTable;
import ar.com.zeni.common.tables.MailTable;
import ar.com.zeni.common.tables.ServerTable;
import ar.com.zeni.db.ZeniInsertBuilder;
import ar.com.zeni.db.ZeniQueryBuilder;
import ar.com.zeni.db.ZeniQueryBuilder.Operator;
import ar.com.zeni.db.ZeniQueryExcecutor;
import ar.com.zeni.db.ZeniQueryExcecutor.ResulsetObjectBuilder;
import ar.com.zeni.db.ZeniUpdateBuilder;
import ar.com.zeni.mail.Mail;
import ar.com.zeni.mail.MailServer;
import ar.com.zeni.mail.MailServerConstants;

public class ServerMailCore implements MailServer {
	
	/**
	 * sends the mails contained in <b>SMailToSendTable.TABLE_NAME</b>.
	 * The mails are filtered by <b>SMailToSendTable.ID_MAIL_SERVER_FIELD</b> and <b>SMailToSendTable.ID_ENV</b>
	 */
	public void sendMail() {
		try {
			try {
				Connection con = ZeniContextServer.getInstance().getConnectionGetter().getConnection();
				try {
					con.setAutoCommit(false);
					ArrayList<Properties> serversProp = getMailServerProperties();
					for ( int i = 0; i < serversProp.size(); i++ ) {
						String status = MailServerConstants.ERROR_STATUS; // optimista ;)
						ServerMailDispatcher md = new ServerMailDispatcher();
						ArrayList<String> mails = readMailIds(serversProp.get(i).getProperty(MailServerConstants.PROP_SERVER_ID));
						MailFactory mf = new MailFactory(con);
						for ( int j = 0; j<mails.size(); j++){
							String statusCause = MailServerConstants.IN_PROCESS;
							updateMailStatus(MailServerConstants.IN_PROCESS_STATUS, mails.get(j), serversProp.get(i));
							con.commit();
							statusCause = MailServerConstants.OK;
							try {
								try {
									Mail mts = mf.createMailFromDb(mails.get(j));
									md.sendMail(mts, serversProp.get(i));
									status = MailServerConstants.SENDED_STATUS;
								} catch (MessagingException e) {
//									 error enviando mail
//									e.printStackTrace();
									ZeniContextServer.getInstance().printInfoLog( "Mail ID: " + mails.get(j) + " : Exception is: \n" + e.getMessage());
									for (int k = 0; k<e.getStackTrace().length ; k++){
										ZeniContextServer.getInstance().printInfoLog( "    " + e.getStackTrace()[k]);
									}
									statusCause = e.getMessage().replaceAll("'", "''").substring(0, 49 < e.getMessage().length() ? 49 : e.getMessage().length() ); // .replaceAll(":", "':")
								} catch (Exception e){
									ZeniContextServer.getInstance().printInfoLog( "Mail ID: " + mails.get(j) + " : Exception is: \n" + e.getMessage());
									for (int k = 0; k<e.getStackTrace().length ; k++){
										ZeniContextServer.getInstance().printInfoLog( "    " + e.getStackTrace()[k]);
									}
									statusCause = e.getMessage().replaceAll("'", "''").substring(0, 49 < e.getMessage().length() ? 49 : e.getMessage().length() ); // .replaceAll(":", "':")
								}								
								updateMailStatus(status, mails.get(j), serversProp.get(i));
							}
							catch (ZeniDBExeption e) {
								con.rollback();
								status = MailServerConstants.ERROR_STATUS;
								statusCause = e.getMessage().replaceAll("'", "''").substring(0, 49); // .replaceAll(":", "':")
							} finally {
								performAudit(status, statusCause, mails.get(j), serversProp.get(i));
								con.commit();							
							}
						}
					}
				} finally {
					try { con.commit(); con.close(); } catch (Exception e2){}
				}
			} catch (SQLException e) {
				ZeniContextServer.getInstance().printInfoLog("Can't create connection to database: " + e.getMessage());
			} catch (Exception e) {
				ZeniContextServer.getInstance().printInfoLog("Unespected error: " + e.getMessage());
			}
		} catch (Exception e1) {
			System.out.println("An error ocurred while triying to create a log");
			e1.printStackTrace();
		}
	}

	/**
	 * This method updates the state of a mail from <b>SMailTable.TABLE_NAME</b> with the recived parameters
	 * @param status
	 * @param cause
	 * @param idmail
	 * @param idserver
	 * @throws ZeniDBExeption 
	 */
	private void updateMailStatus(String status, String idmail, Properties serverProp) throws ZeniDBExeption {
		String sqlMailUpdate = new ZeniUpdateBuilder()
								.update(MailTable.TABLE_NAME)
								.set(MailTable.UPDATE_DATE_FIELD,getSysDate())
								.coma(MailTable.STATUS_DATE_FIELD,getSysDate())
								.coma(MailTable.STATUS_FIELD,status)
								.coma(MailTable.ID_MAIL_SERVER_FIELD,serverProp.getProperty(MailServerConstants.PROP_SERVER_ID))
								.where(MailTable.ID_FIELD, Operator.EQUALS, idmail).getupdateQuery();
		ZeniQueryExcecutor.excecuteUpdate(sqlMailUpdate);
	}
	/**
	 * performs audits for the mail event and the actual configuration for the mailserver in the table <b>SMailAudTable.TABLE_NAME</b>
	 * @param status
	 * @param cause
	 * @param idmail
	 * @param idserver
	 * @throws ZeniDBExeption
	 */
	private void performAudit(String status, String cause, String idmail, Properties serverProp) throws ZeniDBExeption {
		String sqlMailInsert = new ZeniInsertBuilder()
								.insertinsertinto(MailAudTable.TABLE_NAME)
								.fields(MailAudTable.ID_FIELD, 
										MailAudTable.ID_SERVER_FIELD, 
										MailAudTable.SERVER_HOST_FIELD, 
										MailAudTable.SERVER_PORT_FIELD, 
										MailAudTable.SERVER_USER_NAME_FIELD, 
										MailAudTable.SERVER_PASS_FIELD, 
										MailAudTable.SERVER_PROTOCOL_FIELD, 
										MailAudTable.ID_MAIL_FIELD, 
										MailAudTable.INSERT_DATE_FIELD, 
										MailAudTable.UPDATE_DATE_FIELD, 
										MailAudTable.STATUS_DATE_FIELD, 
										MailAudTable.STATUS_FIELD,
										MailAudTable.STATUS_CAUSE_FIELD)
								.values(genId(),
										serverProp.getProperty(MailServerConstants.PROP_SERVER_ID), 
										serverProp.getProperty(MailServerConstants.PROP_HOST), 
										serverProp.getProperty(MailServerConstants.PROP_PORT), 
										serverProp.getProperty(MailServerConstants.PROP_USER), 
										serverProp.getProperty(MailServerConstants.PROP_PASS), 
										serverProp.getProperty(MailServerConstants.PROP_PROTOCOL), 
										idmail, 
										getSysDate(),
										getSysDate(),
										getSysDate(),
										status,
										cause).getInsertQuery();
		ZeniQueryExcecutor.excecuteUpdate(sqlMailInsert);
	}
	private String genId() {
		Random random = new Random();
	    Date today = new Date();
	    return DateUtil.ToString.yyyyMMddHHmmssSSS(today) + Integer.toString(random.nextInt(899)+100);
	}
	/**
	 * Reads the table <b>SMailTable.TABLE_NAME</b> and collects the mail for later send.
	 * The mails are filtered by <b>SMailTable.ID_MAIL_SERVER_FIELD</b> and <b>SMailTable.ID_ENV</b>
	 * @throws ZeniDBExeption 
	 * @returns an arraylist with the id's for the mails to send by the server par ID_SERVER - ID_ENV
	 * 
	 * @date 29-11-2010 idServer is assigned by the appl, 'll not use for filtering
	 */
	private ArrayList<String> readMailIds( String idServer ) throws ZeniDBExeption {
			final ArrayList<String> rtn = new ArrayList<String>();
		String sqlMailSelect = new ZeniQueryBuilder().select(MailTable.ID_FIELD_W_TABLE_ALIAS)
													.from(MailTable.TABLE_NAME_W_TABLE_ALIAS)
													.where(MailTable.STATUS_FIELD, Operator.EQUALS, MailServerConstants.PENDING_STATUS)
//													.and("ROWNUM", Operator.LESS, "2")
													.getQuey();
		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				if (rset.next()) {
					rtn.add(rset.getString(MailTable.ID_FIELD));
				}
			}
		};
		ZeniQueryExcecutor.excecuteSelect(sqlMailSelect, resb);
		return rtn;
	}

	/**
	 * it gets the server properties from property file (default) or data base
	 * @return the preperties for server configuration, WARNING it can be empty!
	 * @throws ZeniDBExeption 
	 */
	private ArrayList<Properties> getMailServerProperties() throws ZeniDBExeption {
		final Properties props = new Properties();
		final ArrayList<Properties> rtn = new ArrayList<Properties>();
		final ClassLoader cl = this.getClass().getClassLoader();
		final InputStream strProp;
		try { // Reading defaults values
			strProp  = cl.getResource(MailServerConstants.PROP_FILE_NAME).openStream();
			props.load(strProp);
		} catch (IOException e) {
		} catch (NullPointerException e) {
		}
		String sqlMailSelect = new ZeniQueryBuilder()
						.select(ServerTable.ID_FIELD, ServerTable.HOST_FIELD, ServerTable.PORT_FIELD, ServerTable.PROTOCOL, ServerTable.USER_FIELD, ServerTable.PASS_FIELD)
						.from(ServerTable.TABLE_NAME).getQuey();
		final ResulsetObjectBuilder resb = new ResulsetObjectBuilder() {
			@Override
			public void thisIsTheResulset(ResultSet rset) throws SQLException {
				while (rset.next()) {
					String host          = rset.getString(ServerTable.HOST_FIELD) != null ? rset.getString(ServerTable.HOST_FIELD) : ZeniContextServer.VACIO;
					String port          = rset.getString(ServerTable.PORT_FIELD) != null ? rset.getString(ServerTable.PORT_FIELD) : ZeniContextServer.VACIO;
					boolean ssl_enabled = rset.getString(ServerTable.PROTOCOL)   != null ? rset.getString(ServerTable.PROTOCOL).equals(MailServerConstants.SSL_PROTOCOL_CONSTANT) : false;
					String username      = rset.getString(ServerTable.USER_FIELD) != null ? rset.getString(ServerTable.USER_FIELD) : ZeniContextServer.VACIO;
					String password      = rset.getString(ServerTable.PASS_FIELD) != null ? rset.getString(ServerTable.PASS_FIELD) : ZeniContextServer.VACIO;
					String serverId      = rset.getString(ServerTable.ID_FIELD)   != null ? rset.getString(ServerTable.ID_FIELD)   : ZeniContextServer.VACIO;
					// last_date = rset.getString(SMailServerTable.LAST_CHECKED_DATE_FIELD);

					//validate configuration
					if ( host.equals(ZeniContextServer.VACIO) || port.equals(ZeniContextServer.VACIO) ){
						host = props.getProperty(MailServerConstants.PROP_FILE_HOST, ZeniContextServer.VACIO);
						port = props.getProperty(MailServerConstants.PROP_FILE_PORT, ZeniContextServer.VACIO);
						ssl_enabled = props.getProperty(MailServerConstants.PROP_FILE_PROTOCOL) != null && props.getProperty(MailServerConstants.PROP_FILE_PROTOCOL).equals(MailServerConstants.SSL_PROTOCOL_CONSTANT);
						username = props.getProperty(MailServerConstants.PROP_FILE_USER, ZeniContextServer.VACIO);
						password = props.getProperty(MailServerConstants.PROP_FILE_PASS, ZeniContextServer.VACIO);
						ZeniContextServer.getInstance().printInfoLog("Loading default values for Server Id: " + serverId);
					}
					Properties curr_prop = new Properties();
					curr_prop.put(MailServerConstants.PROP_USER, username);
					curr_prop.put(MailServerConstants.PROP_PASS, password);
					curr_prop.put(MailServerConstants.PROP_PROTOCOL, rset.getString(ServerTable.PROTOCOL));
					curr_prop.put(MailServerConstants.PROP_AUTH_ENABLE, username.equals(ZeniContextServer.VACIO) ? MailServerConstants.FALSE_STRING_VALUE : MailServerConstants.TRUE_STRING_VALUE );
					curr_prop.put(MailServerConstants.PROP_HOST, host);
					curr_prop.put(MailServerConstants.PROP_PORT, port);
					if (ssl_enabled) {
						if ( username.equals(ZeniContextServer.VACIO) || password.equals(ZeniContextServer.VACIO) ){
							throw new RuntimeException("Must be especified User and Password values for SSL connection socket");
						}
						curr_prop.put(MailServerConstants.PROP_SSL_ENABLE, MailServerConstants.TRUE_STRING_VALUE);
						curr_prop.put(MailServerConstants.PROP_AUTH_ENABLE, MailServerConstants.TRUE_STRING_VALUE);
						curr_prop.put(MailServerConstants.PROP_SOKECT_FACTORY_PORT, port);
						curr_prop.put(MailServerConstants.PROP_SOKECT_FACTORY_CLASS, MailServerConstants.JAVA_SSL_SOCKETFACTORY_CLASS);
						curr_prop.put(MailServerConstants.PROP_SOKECT_FACTORY_FALLBACK, MailServerConstants.FALSE_STRING_VALUE);
					} else {
						curr_prop.put(MailServerConstants.PROP_SSL_ENABLE, MailServerConstants.FALSE_STRING_VALUE);
					}
					// validaciones
					if (( curr_prop.get(MailServerConstants.PROP_AUTH_ENABLE).equals(MailServerConstants.TRUE_STRING_VALUE) && (curr_prop.get(MailServerConstants.PROP_USER).equals(ZeniContextServer.VACIO) || curr_prop.get(MailServerConstants.PROP_PASS).equals(ZeniContextServer.VACIO))) || ( curr_prop.get(MailServerConstants.PROP_HOST).equals(ZeniContextServer.VACIO) ||  curr_prop.get(MailServerConstants.PROP_PORT).equals(ZeniContextServer.VACIO) )){
						throw new RuntimeException("Check server properties on DB " + ServerTable.TABLE_NAME + " and/or property file " + MailServerConstants.PROP_FILE_NAME);
					}
					curr_prop.put(MailServerConstants.PROP_SERVER_ID, serverId);
					rtn.add(curr_prop);
				}
			}
		};
		ZeniQueryExcecutor.excecuteSelect(sqlMailSelect, resb);
		if (rtn.size()<1){//default configuracion en el archivo.
			rtn.add(props);
		}
		return rtn;
	}
	private String getSysDate() {
	    Date today = new Date();
	    return DateUtil.ToString.yyyyMMddHHmmss(today);
	}
//	private String betweenQuotes(String str) {
//		return "'" + str + "'";
//	}

	public void sendMail(Mail mts) throws ZeniMailServerExeption {
		storeMail(mts);
		sendMail();
	}

	@Override
	public void storeMail(Mail mail) throws ZeniMailServerExeption {
		try {
			ArrayList<Properties> serversProp = getMailServerProperties();
			for ( int i = 0; i < serversProp.size(); i++ ) {
				storeMail(mail, serversProp.get(i).getProperty(MailServerConstants.PROP_SERVER_ID));
			}
		} catch (ZeniDBExeption e) {
			throw new ZeniMailServerExeption(e);
		}
	}
	public void storeMail(Mail mail, String serverid) throws ZeniDBExeption {
		String toAddresses = ZeniContextServer.VACIO;
		if ( mail.getTo().size() >= 1 ){ // this happens when you are not able to think, maybe with a while and iterators this become a happy code
			toAddresses = (String) mail.getTo().get(0) ;
			for ( int i = 1 ; i < mail.getTo().size(); i++ ){
				toAddresses +=  " ; " + (String) mail.getTo().get(i) ;
			}
		}
		String idMail = genId();
		String sqlInsertMail = makeStmtInsertMail( idMail, mail.getFrom(), toAddresses, mail.getSubject(), mail.getBody(), serverid);
		executeInsert(sqlInsertMail);
	}

	private String makeStmtInsertMail( String idMail, String from, String to, String subject, String body, String serverid) {
		return new ZeniInsertBuilder()
				.insertinsertinto(MailTable.TABLE_NAME)
				.fields(
						MailTable.ID_FIELD,
						MailTable.ID_MAIL_SERVER_FIELD, 
						MailTable.FROM_FIELD, 
						MailTable.TO_FIELD, 
						MailTable.SUBJECT_FIELD, 
						MailTable.BODY_FIELD,
						MailTable.STATUS_FIELD,
						MailTable.STATUS_DATE_FIELD,
						MailTable.CREATION_DATE_FIELD,
						MailTable.INSERT_DATE_FIELD,
						MailTable.UPDATE_DATE_FIELD)
				.values(			
						idMail,
						serverid,
						from,
						to,
						subject,
						body,
						MailServerConstants.PENDING_STATUS,
						getSysDate(),
						getSysDate(),
						getSysDate(),
						getSysDate()).getInsertQuery();
	}
	private void executeInsert( String insertStmt) throws ZeniDBExeption {
		ZeniQueryExcecutor.excecuteUpdate(insertStmt);
	}
}
