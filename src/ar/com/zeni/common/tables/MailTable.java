package ar.com.zeni.common.tables;

import ar.com.zeni.common.ZeniContextServer;


public interface MailTable {
	final String TABLE_NAME          = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_MAIL_TABLE";
	final String ID_FIELD            = "ID_MAIL";
	final String ID_MAIL_SERVER_FIELD= "ID_MAIL_SERVER";
	final String STATUS_FIELD        = "STATUS";
	final String STATUS_DATE_FIELD   = "STATUS_DATE";
	final String STATUS_CAUSE_FIELD  = "STATUS_CAUSE";
	final String CREATION_DATE_FIELD = "CREATION_DATE";
	final String TO_FIELD            = "MAIL_TO";
	final String FROM_FIELD          = "MAIL_FROM";
	final String SUBJECT_FIELD       = "MAIL_SUBJECT";
	final String BODY_FIELD          = "MAIL_BODY";
//	final String ID_ATTACHMENT_FIELD = "ID_ATTACHMENT";
//	final String HAS_ATTACHMENT_FIELD= "HAS_ATTACHMENT";
	final String INSERT_DATE_FIELD   = "INSERT_DATE";
//	final String INSERT_USER_FIELD   = "INSERT_USER";
//	final String UPDATE_USER_FIELD   = "UPDATE_USER";
	final String UPDATE_DATE_FIELD   = "UPDATE_DATE";
//	final String ID_ENV_FIELD        = "ID_ENV";
	final String TABLE_ALIAS         = "M";
	final String TABLE_NAME_W_TABLE_ALIAS          = TABLE_NAME  +" "+ TABLE_ALIAS;
	final String ID_FIELD_W_TABLE_ALIAS            = TABLE_ALIAS +"."+ ID_FIELD;
	final String ID_MAIL_SERVER_FIELD_W_TABLE_ALIAS= TABLE_ALIAS +"."+ ID_MAIL_SERVER_FIELD;
	final String STATUS_FIELD_W_TABLE_ALIAS        = TABLE_ALIAS +"."+ STATUS_FIELD;
	final String STATUS_DATE_FIELD_W_TABLE_ALIAS   = TABLE_ALIAS +"."+ STATUS_DATE_FIELD;
	final String CREATION_DATE_FIELD_W_TABLE_ALIAS = TABLE_ALIAS +"."+ CREATION_DATE_FIELD;
	final String TO_FIELD_W_TABLE_ALIAS            = TABLE_ALIAS +"."+ TO_FIELD;
	final String FROM_FIELD_W_TABLE_ALIAS          = TABLE_ALIAS +"."+ FROM_FIELD;
	final String SUBJECT_FIELD_W_TABLE_ALIAS       = TABLE_ALIAS +"."+ SUBJECT_FIELD;
	final String BODY_FIELD_W_TABLE_ALIAS          = TABLE_ALIAS +"."+ BODY_FIELD;
//	final String ID_ATTACHMENT_FIELD_W_TABLE_ALIAS = TABLE_ALIAS +"."+ ID_ATTACHMENT_FIELD;
//	final String HAS_ATTACHMENT_FIELD_W_TABLE_ALIAS= TABLE_ALIAS +"."+ HAS_ATTACHMENT_FIELD;
//	final String ID_ENV_FIELD_W_TABLE_ALIAS        = TABLE_ALIAS +"."+ ID_ENV_FIELD;
//	final String UPDATE_USER_FIELD_W_TABLE_ALIAS   = TABLE_ALIAS +"."+ UPDATE_USER_FIELD;
	final String UPDATE_DATE_FIELD_W_TABLE_ALIAS   = TABLE_ALIAS +"."+ UPDATE_DATE_FIELD;
	final String INSERT_USER_FIELD_W_TABLE_ALIAS   = TABLE_ALIAS +"."+ INSERT_DATE_FIELD;
//	final String INSERT_DATE_FIELD_W_TABLE_ALIAS   = TABLE_ALIAS +"."+ INSERT_USER_FIELD;
}