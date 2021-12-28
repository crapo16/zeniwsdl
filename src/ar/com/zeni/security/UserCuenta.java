package ar.com.zeni.security;

/**
 * 
 */
public class UserCuenta {

	private boolean habilitado;
	private String opcc;
	private String opc;

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getOpcc() {
		return opcc;
	}

	public void setOpcc(String opcc) {
		this.opcc = opcc;
	}

	public String getOpc() {
		return opc;
	}

	public void setOpc(String opc) {
		this.opc = opc;
	}
}
