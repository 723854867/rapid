package javax.exception;

public class HTTPException extends RuntimeException {

	private static final long serialVersionUID = -9152862490945653140L;
	
	private int status;
	
	public HTTPException(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
}
