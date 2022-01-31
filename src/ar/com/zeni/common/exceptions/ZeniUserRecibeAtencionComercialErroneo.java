package ar.com.zeni.common.exceptions;

import ar.com.zeni.common.FAULTCONSTANTS;

public class ZeniUserRecibeAtencionComercialErroneo extends Exception implements ZeniBaseExceptionInterface {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return FAULTCONSTANTS.CUENTA_DESABILITADA_ERRONEO_MSG;
    }

    @Override
    public Throwable getEx() {
        return this;
    }

    @Override
    public String getCode() {
        return FAULTCONSTANTS.CUENTA_DESABILITADA_ERRONEO_MSG;
    }
}
