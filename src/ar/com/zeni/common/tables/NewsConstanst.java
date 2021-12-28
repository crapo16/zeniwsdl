package ar.com.zeni.common.tables;

import ar.com.zeni.common.ZeniContextServer;


public interface NewsConstanst {
	public interface NewsTable {
		static final String NEWSTABLE = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_NEWS";
		static final String FIELD_ID = "ID";
		static final String FIELD_NEWS = "NEWS";
		static final String FIELD_DATE = "FECHA";
	}
}
