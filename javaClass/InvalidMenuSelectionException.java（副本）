package HW8;

public class InvalidMenuSelectionException extends Exception {

	private String errMsg;
	
	public InvalidMenuSelectionException() {
		super("!Error! Invalid Menu Selection");
		errMsg = super.getMessage();
	}
	
	public InvalidMenuSelectionException(char choice) {
		super("!Error! \'" + choice + "\' is an Invalid Selection\n");
		errMsg = super.getMessage();
	}
	
	public String getMessage() {
		return errMsg;
	}
	
}
