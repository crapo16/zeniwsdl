package ar.com.zeni.common.exceptions;

import ar.com.zeni.common.FAULTCONSTANTS;

public class ZeniCodigoDeAutorizacionEnviado extends ZeniBaseExeption {
	private static final long	serialVersionUID	= 1L;
	public ZeniCodigoDeAutorizacionEnviado(Throwable e) {
		super(FAULTCONSTANTS.CODIGO_DE_AUTORIZACION_REENVIADO_MSG, FAULTCONSTANTS.CODIGO_DE_AUTORIZACION_REENVIADO, e);
	}
//
//	public ZeniCodigoDeAutorizacionEnviado() {
//		super(FAULTCONSTANTS.CODIGO_DE_AUTORIZACION_REENVIADO_MSG, FAULTCONSTANTS.CODIGO_DE_AUTORIZACION_REENVIADO, null);
//	}
}
