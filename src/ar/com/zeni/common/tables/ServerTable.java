package ar.com.zeni.common.tables;

import ar.com.zeni.common.ZeniContextServer;


public interface ServerTable {
	final String TABLE_NAME              = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_MAIL_SERVER";
	final String ID_FIELD                = "ID_MAIL_SERVER";
	final String HOST_FIELD              = "SERVER";
	final String PORT_FIELD              = "PORT";
	final String USER_FIELD              = "USER_NAME";
	final String PASS_FIELD              = "PASS";
	final String PROTOCOL                = "PROTOCOL";
	final String TABLE_ALIAS             = "MS";
//	final String ID_ENV_FIELD            = "ID_ENV";
//	final String LAST_CHECKED_DATE_FIELD = "LAST_CHECKED_DATE";
	final String TABLE_NAME_W_TABLE_ALIAS              = TABLE_NAME +" "+ TABLE_ALIAS;
	final String ID_FIELD_W_TABLE_ALIAS                = TABLE_ALIAS +"."+ ID_FIELD;
	final String HOST_FIELD_W_TABLE_ALIAS              = TABLE_ALIAS +"."+ HOST_FIELD;
	final String PORT_FIELD_W_TABLE_ALIAS              = TABLE_ALIAS +"."+ PORT_FIELD;
	final String USER_FIELD_W_TABLE_ALIAS              = TABLE_ALIAS +"."+ USER_FIELD;
	final String PASS_FIELD_W_TABLE_ALIAS              = TABLE_ALIAS +"."+ PASS_FIELD;
	final String PROTOCOL_W_TABLE_ALIAS                = TABLE_ALIAS +"."+ PROTOCOL;
//	final String ID_ENV_FIELD_W_TABLE_ALIAS            = TABLE_ALIAS +"."+ ID_ENV_FIELD;
//	final String LAST_CHECKED_DATE_FIELD_ALAIS = "MS.LAST_CHECKED_DATE";
	
}
