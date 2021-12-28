package ar.com.zeni.admin.app.server;

import ar.com.zeni.common.ZeniContextServer;

/**
 *
 * @author  ogagliano
 *
 */
public interface NovedadConstanst {
	public interface NovedadTable {
		static final String TABLE = ZeniContextServer.getInstance().getDBWebOwner() + "WEB_NOVEDAD";
		static final String FIELD_ID = "ID";
		static final String FIELD_TITLE = "TITLE";
		static final String FIELD_DESCRIPTION = "DESCRIPTION";
		static final String FIELD_FECHA = "FECHA";
	}
}
