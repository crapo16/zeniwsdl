package ar.com.zeni.common.tables;

import ar.com.zeni.common.ZeniContextServer;


public interface MailAudTable {
	final String TABLE_NAME            = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_MAIL_AUD";
	final String ID_FIELD              = "ID_MAIL_AUD";
	final String ID_SERVER_FIELD       = "ID_MAIL_SERVER";
	final String ID_MAIL_FIELD         = "ID_MAIL";
	final String SERVER_HOST_FIELD     = "SERVER_HOST";
	final String SERVER_PORT_FIELD     = "SERVER_PORT";
	final String SERVER_USER_NAME_FIELD= "SERVER_USER_NAME";
	final String SERVER_PASS_FIELD     = "SERVER_PASS";
	final String SERVER_PROTOCOL_FIELD = "SERVER_PROTOCOL";
	final String STATUS_CAUSE_FIELD    = "STATUS_CAUSE";
	final String STATUS_FIELD          = "STATUS";
	final String STATUS_DATE_FIELD     = "STATUS_DATE";
//	final String ID_ENV_FIELD          = "ID_ENV";
//	final String UPDATE_USER_FIELD     = "AUD_USER_UPDATE";
	final String UPDATE_DATE_FIELD     = "UPDATE_DATE";
//	final String INSERT_USER_FIELD     = "AUD_USER_INSERT"; 
	final String INSERT_DATE_FIELD     = "INSERT_DATE";
	final String TABLE_ALIAS           = "MAT";
	final String TABLE_NAME_W_TABLE_ALIAS             = TABLE_NAME + " " + TABLE_ALIAS;
	final String ID_FIELD_W_TABLE_ALIAS               = TABLE_ALIAS +"."+ ID_FIELD;
	final String ID_SERVER_FIELD_W_TABLE_ALIAS        = TABLE_ALIAS +"."+ ID_SERVER_FIELD;
	final String SERVER_HOST_FIELD_FIELD_W_TABLE_ALIAS= TABLE_ALIAS +"."+ SERVER_HOST_FIELD;
	final String SERVER_PORT_FIELD_W_TABLE_ALIAS      = TABLE_ALIAS +"."+ SERVER_PORT_FIELD;
	final String SERVER_USER_NAME_FIELD_W_TABLE_ALIAS = TABLE_ALIAS +"."+ SERVER_USER_NAME_FIELD;
	final String SERVER_PROTOCOL_FIELD_W_TABLE_ALIAS  = TABLE_ALIAS +"."+ SERVER_PROTOCOL_FIELD;
	final String ID_MAIL_FIELD_W_TABLE_ALIAS          = TABLE_ALIAS +"."+ ID_MAIL_FIELD;
	final String STATUS_CAUSE_FIELD_W_TABLE_ALIAS     = TABLE_ALIAS +"."+ STATUS_CAUSE_FIELD;
	final String STATUS_FIELD_W_TABLE_ALIAS           = TABLE_ALIAS +"."+ STATUS_FIELD;
	final String STATUS_DATE_FIELD_W_TABLE_ALIAS      = TABLE_ALIAS +"."+ STATUS_DATE_FIELD;
//	final String ID_ENV_FIELD_W_TABLE_ALIAS           = TABLE_ALIAS +"."+ ID_ENV_FIELD;
//	final String UPDATE_USER_FIELD_W_TABLE_ALIAS      = TABLE_ALIAS +"."+ UPDATE_USER_FIELD;
	final String UPDATE_DATE_FIELD_W_TABLE_ALIAS      = TABLE_ALIAS +"."+ UPDATE_DATE_FIELD;
//	final String INSERT_USER_FIELD_W_TABLE_ALIAS      = TABLE_ALIAS +"."+ INSERT_USER_FIELD;
	final String INSERT_DATE_FIELD_W_TABLE_ALIAS      = TABLE_ALIAS +"."+ INSERT_DATE_FIELD;
}
