package ar.com.zeni.common.exceptions;

public class ZeniServerExeption extends ZeniBaseExeption {
	private static final long	serialVersionUID	= 1L;
	public ZeniServerExeption(String message, String code) {
		this(message, code, new Throwable());
	}
	public ZeniServerExeption(String message, String code, final Throwable e) {
		super(message, code, e);
	}
}