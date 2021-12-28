package ar.com.zeni.common.exceptions;

import ar.com.zeni.common.FAULTCONSTANTS;

public class ZeniUsuarioExistenteException extends ZeniBaseExeption {
	private static final long	serialVersionUID	= 1L;
	public ZeniUsuarioExistenteException(Throwable e) {
		super(FAULTCONSTANTS.USUARIO_EXISTENTE_MSG, FAULTCONSTANTS.USUARIO_EXISTENTE, e);
	}
}
