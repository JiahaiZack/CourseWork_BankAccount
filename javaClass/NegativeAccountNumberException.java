package HW8;

public class NegativeAccountNumberException extends Exception{

	private String errMsg;
	
	public NegativeAccountNumberException() {
		super("!Error! Account Number is Negative");
		errMsg = super.getMessage();
	}
	
	public NegativeAccountNumberException(String err) {
		super(err);
		errMsg = err;
	}
	
	public String getMessage() {
		return errMsg;
	}
	
}
