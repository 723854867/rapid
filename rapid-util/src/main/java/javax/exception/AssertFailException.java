package javax.exception;

/**
 * 断言异常
 */
public class AssertFailException extends RuntimeException {

	private static final long serialVersionUID = -2905784962503753982L;
	
	private Object expect;
	private Object target;

	public AssertFailException(String message) {
		super(message);
	}
	
	public AssertFailException(String message, Object expect, Object target) {
		super(message);
		this.expect = expect;
		this.target = target;
	}
	
	public Object getExpect() {
		return expect;
	}
	
	public void setExpect(Object expect) {
		this.expect = expect;
	}
	
	public Object getTarget() {
		return target;
	}
	
	public void setTarget(Object target) {
		this.target = target;
	}
}
