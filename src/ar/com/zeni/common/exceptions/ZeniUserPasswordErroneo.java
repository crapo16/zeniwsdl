package ar.com.zeni.common.exceptions;

import ar.com.zeni.common.FAULTCONSTANTS;

public class ZeniUserPasswordErroneo extends Exception implements ZeniBaseExceptionInterface{
	private static final long	serialVersionUID	= 1L;

	@Override
	public String getMessage() {
		return FAULTCONSTANTS.PASSWORD_ERRONEO_MSG;
	}

	@Override
	public Throwable getEx() {
		return this;
	}

	@Override
	public String getCode() {
		return FAULTCONSTANTS.PASSWORD_ERRONEO;
	}
}
