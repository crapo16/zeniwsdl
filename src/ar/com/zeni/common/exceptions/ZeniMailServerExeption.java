package ar.com.zeni.common.exceptions;

import ar.com.zeni.common.FAULTCONSTANTS;

public class ZeniMailServerExeption extends ZeniBaseExeption {
	private static final long	serialVersionUID	= 1L;

	public ZeniMailServerExeption(Throwable e) {
		super(FAULTCONSTANTS.MAIL_ERROR_MSG, FAULTCONSTANTS.MAIL_ERROR, e);
	}

}
