package ar.com.zeni.common;

/**
 *
 * @author ogagliano
 *
 */
public enum Condicion {

	VENTA("V", "Venta"), COMPRA("C", "Compra"), SUSCRIPCION("S", "Suscripcion"), RESCATE("R", "Rescate"),LICITACION("L", "Licitacion"),OTROS("O", "Otros");

	String valor;
	String descripcion;

	/**
	 *
	 * @param _valor
	 * @return
	 */
	public static String getDescripcionByValor(String _valor) {
		String _data = null;

		if (_valor != null) {
			if (VENTA.valor.equalsIgnoreCase(_valor.trim()))
				_data = VENTA.descripcion;
			else if (COMPRA.valor.equalsIgnoreCase(_valor.trim()))
				_data = COMPRA.descripcion;
			else if (SUSCRIPCION.valor.equalsIgnoreCase(_valor.trim()))
				_data = SUSCRIPCION.descripcion;
			else if (RESCATE.valor.equalsIgnoreCase(_valor.trim()))
				_data = RESCATE.descripcion;
			else if (LICITACION.valor.equalsIgnoreCase(_valor.trim()))
				_data = LICITACION.descripcion;
			else
				_data = OTROS.descripcion;
		}

		return _data;

	}

	/**
	 *
	 * @return
	 */
	public String getValor() {
		return valor;
	}

	/**
	 *
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 *
	 * @param valor
	 * @param descripcion
	 */
	Condicion(String valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

}
