package ar.com.zeni.common.tables;

import ar.com.zeni.common.ZeniContextServer;

public interface NormasComerciaConstanst {
	public interface NormasComerciaTable {
		static final String HELPTABLE = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_NORMA_COMER";
		static final String FIELD_ID = "ID";
		static final String FIELD_PRODUCTO = "PRODUCTO";
		static final String FIELD_NORMAVIG = "NORMAVIG";
		static final String FIELD_NORMA_FILE_ID = "NORMA_FILE_ID";
		static final String FIELD_TMPD_FILE_ID = "TMPD_FILE_ID";
	}
}
