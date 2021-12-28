package ar.com.zeni.common.exceptions;

import ar.com.zeni.common.FAULTCONSTANTS;

public class ZeniHashInvalidExeption extends ZeniBaseExeption {
	private static final long	serialVersionUID	= 1L;

	public ZeniHashInvalidExeption(Throwable e) {
		super(FAULTCONSTANTS.CONTEXT_HASH_NO_VALIDO_MSG, FAULTCONSTANTS.CONTEXT_HASH_NO_VALIDO, e);
	}

	public ZeniHashInvalidExeption() {
		super(FAULTCONSTANTS.CONTEXT_HASH_NO_VALIDO_MSG, FAULTCONSTANTS.CONTEXT_HASH_NO_VALIDO);
	}
}
