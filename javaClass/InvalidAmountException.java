package HW8;

public class InvalidAmountException extends Exception {

	private String errMsg;
	
	public InvalidAmountException() {
		super("!Error! Invalid Amount");
		errMsg = super.getMessage();
	}
	
	public InvalidAmountException(String err) {
		super(err);
		errMsg = err;
	}
	
	public String getMessage() {
		return errMsg;
	}
	
	
}
