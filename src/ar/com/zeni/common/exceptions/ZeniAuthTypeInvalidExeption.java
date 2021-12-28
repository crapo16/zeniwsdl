package ar.com.zeni.common.exceptions;

import ar.com.zeni.common.FAULTCONSTANTS;

public class ZeniAuthTypeInvalidExeption extends ZeniBaseExeption {
	private static final long	serialVersionUID	= 1L;

	public ZeniAuthTypeInvalidExeption(Throwable e) {
		super(FAULTCONSTANTS.CONTEXT_AUTH_NO_VALIDO_MSG, FAULTCONSTANTS.CONTEXT_AUTH_NO_VALIDO, e);
	}

	public ZeniAuthTypeInvalidExeption() {
		super(FAULTCONSTANTS.CONTEXT_AUTH_NO_VALIDO_MSG, FAULTCONSTANTS.CONTEXT_AUTH_NO_VALIDO);
	}
}
