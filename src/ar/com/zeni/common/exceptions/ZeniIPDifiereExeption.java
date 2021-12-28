package ar.com.zeni.common.exceptions;

import ar.com.zeni.common.FAULTCONSTANTS;

public class ZeniIPDifiereExeption extends ZeniBaseExeption {
	private static final long	serialVersionUID	= 1L;

	public ZeniIPDifiereExeption(Throwable e) {
		super(FAULTCONSTANTS.IP_ACTUAL_DIFIERE_MSG, FAULTCONSTANTS.IP_ACTUAL_DIFIERE, e);
	}

	public ZeniIPDifiereExeption() {
		super(FAULTCONSTANTS.IP_ACTUAL_DIFIERE_MSG, FAULTCONSTANTS.IP_ACTUAL_DIFIERE);
	}
}
