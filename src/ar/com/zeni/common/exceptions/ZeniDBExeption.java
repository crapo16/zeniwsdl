package ar.com.zeni.common.exceptions;

import ar.com.zeni.common.FAULTCONSTANTS;

public class ZeniDBExeption extends ZeniBaseExeption {
	private static final long	serialVersionUID	= 1L;
	public ZeniDBExeption(String query, Throwable e) {
		super(FAULTCONSTANTS.DB_ERROR_MSG + query, FAULTCONSTANTS.DB_ERROR, e);
	}
	public ZeniDBExeption(String errorMsg, String errorCode, Exception ex) {
		super(FAULTCONSTANTS.DB_ERROR_MSG, errorCode, ex);
	}
}
