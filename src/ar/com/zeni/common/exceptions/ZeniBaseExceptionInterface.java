package ar.com.zeni.common.exceptions;

public interface ZeniBaseExceptionInterface {
	public Throwable getEx();
	public String getMessage();
	public String getCode();
}
