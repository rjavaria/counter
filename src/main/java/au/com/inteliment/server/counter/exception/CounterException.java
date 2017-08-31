package au.com.inteliment.server.counter.exception;
/**
 * 
 * @author rjavaria
 *
 */
public class CounterException extends Exception {

	private static final long serialVersionUID = 397243640953744079L;

	private int errorCode;
	
	public CounterException()
    {
        super();
    }

    public CounterException(String s, int errorCode)
    {
        super(s);
        this.errorCode = errorCode;
    }

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
