package ar.com.zeni.common.tables;


public interface AttachmentTable {
//	final String TABLE_NAME          = ZeniContextServer.getInstance().getDBOwner() + "WEB_EM_ATTACHMENT";
	final String ID_FIELD            = "ID_ATTACHMENT";
	final String ID_MAIL_FIELD       = "ID_MAIL";
	final String FILE_NAME_FIELD     = "FILE_NAME";
	final String FILE_PATH_FIELD     = "FILE_PATH";
	final String FILE_DATA_FIELD     = "FILE_DATA";
	final String INSERT_DATE_FIELD   = "AUD_DATE_INSERT";
	final String INSERT_USER_FIELD   = "AUD_USER_INSERT";
	final String UPDATE_DATE_FIELD   = "AUD_DATE_UPDATE";
	final String UPDATE_USER_FIELD   = "AUD_USER_UPDATE";
	final String ID_ENV_FIELD        = "ID_ENV";
	final String TABLE_ALIAS         = "A";
//	final String TABLE_NAME_W_TABLE_ALIAS       = TABLE_NAME  +" "+ TABLE_ALIAS;
	final String ID_FIELD_W_TABLE_ALIAS         = TABLE_ALIAS +"."+ ID_FIELD;
	final String ID_MAIL_FIELD_W_TABLE_ALIAS         = TABLE_ALIAS +"."+ ID_MAIL_FIELD;
	final String FILE_NAME_FIELD_W_TABLE_ALIAS  = TABLE_ALIAS +"."+ FILE_NAME_FIELD;
	final String FILE_PATH_FIELD_W_TABLE_ALIAS  = TABLE_ALIAS +"."+ FILE_PATH_FIELD;
	final String FILE_DATA_FIELD_W_TABLE_ALIAS  = TABLE_ALIAS +"."+ FILE_DATA_FIELD;
	final String INSERT_DATE_FIELD_W_TABLE_ALIAS= TABLE_ALIAS +"."+ INSERT_DATE_FIELD;
	final String INSERT_USER_FIELD_W_TABLE_ALIAS= TABLE_ALIAS +"."+ INSERT_USER_FIELD;
	final String UPDATE_DATE_FIELD_W_TABLE_ALIAS= TABLE_ALIAS +"."+ UPDATE_DATE_FIELD;
	final String UPDATE_USER_FIELD_W_TABLE_ALIAS= TABLE_ALIAS +"."+ UPDATE_USER_FIELD;
	final String ID_ENV_FIELD_W_TABLE_ALIAS     = TABLE_ALIAS +"."+ ID_ENV_FIELD;


}
