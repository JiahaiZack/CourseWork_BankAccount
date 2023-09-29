package HW8;

public class PostDatedCheckException extends Exception {

	private String errMsg;
	
	public PostDatedCheckException() {
		super("!Error! Check is a Post-Dated Check");
		errMsg = super.getMessage();
	}
	
	public PostDatedCheckException(String err) {
		super(err);
		errMsg = err;
	}
	
	public String getMessage() {
		return errMsg;
	}
	
}
