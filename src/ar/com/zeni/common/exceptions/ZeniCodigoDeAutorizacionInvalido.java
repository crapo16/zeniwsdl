package ar.com.zeni.common.exceptions;

import ar.com.zeni.common.FAULTCONSTANTS;

public class ZeniCodigoDeAutorizacionInvalido extends ZeniBaseExeption {
	private static final long	serialVersionUID	= 1L;
	public ZeniCodigoDeAutorizacionInvalido(Throwable e) {
		super(FAULTCONSTANTS.CODIGO_DE_AUTORIZACION_INVALIDO_MSG, FAULTCONSTANTS.CODIGO_DE_AUTORIZACION_INVALIDO, e);
	}
//
//	public ZeniCodigoDeAutorizacionEnviado() {
//		super(FAULTCONSTANTS.CODIGO_DE_AUTORIZACION_REENVIADO_MSG, FAULTCONSTANTS.CODIGO_DE_AUTORIZACION_REENVIADO, null);
//	}
}
