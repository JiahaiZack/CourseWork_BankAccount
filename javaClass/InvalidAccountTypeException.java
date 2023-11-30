package HW8;

public class InvalidAccountTypeException extends Exception{
	
	private String errMsg;
	
	public InvalidAccountTypeException() {
		super("!Error! Wrong Account Type");
		errMsg = "!Error! Wrong Account Type";
	}
	
	public InvalidAccountTypeException(String err) {
		super(err);
		errMsg = err;
	}
	
	public String getMessage() {
		return errMsg;
	}

}
