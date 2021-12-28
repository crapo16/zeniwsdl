package ar.com.zeni.common.tables;

import ar.com.zeni.common.ZeniContextServer;


public interface AyudasConstanst {
	public interface AyudaTable {
		static final String HELPTABLE = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_HELP";
		static final String FIELD_ID = "ID";
		static final String FILED_QUESTION = "QUESTION";
		static final String FIELD_ANSWER = "ANSWER";
		static final String FIELD_ORDER = "ORDERD";
	}
}
