package ar.com.zeni.common.exceptions;

public class ZeniBaseExeption extends Exception implements ZeniBaseExceptionInterface{
	private static final long serialVersionUID = 1L;
	private Throwable ex;
	private final String	code;
	public ZeniBaseExeption(final String message, final String code) {
		super(message);
		ex = null;
		this.code = code;
	}
	public ZeniBaseExeption(final String message, final String code, final Throwable e) {
		this(message + " caused by: " + e.getMessage(), code);
		ex = e;
		updateSt(e);
	}
	private void updateSt(Throwable e) {
		StackTraceElement[] thisst = this.getStackTrace();
		StackTraceElement[] parast = e.getStackTrace();
		int canti = (parast!=null?parast.length:0) + (thisst!=null?thisst.length:0);
		StackTraceElement[] newst = new StackTraceElement[canti];
		int i = 0;
		for ( ;  thisst!=null && i < thisst.length; i++ ) {
			newst[i]=thisst[i];
		}
		for ( int j = 0; parast!=null && j < parast.length; j++, i++ ) {
			newst[i]=parast[j];
		}
		this.setStackTrace(newst);
	}
	@Override
	public void printStackTrace() {
		super.printStackTrace();
		if (getEx()!=null) {
			getEx().printStackTrace();
		}
	}
	@Override
	public Throwable getEx() {
		return ex;
	}
	@Override
	public String getCode() {
		return code;
	}
}