package HW8;

public class Depositor extends genDepositor{

	private String SSN;
	private Name name;
	
	//no-arg constructor
	public Depositor() {
		SSN = "";
		name = new Name();
	}
	
	//parameterized Constructors
	public Depositor(String ssn, Name fullName) {
		setSSN(ssn);
		setName(fullName);
	}
	
	//copy constructor
	public Depositor(Depositor myDepositor) {
		SSN = myDepositor.SSN;
		name = new Name(myDepositor.name);
	}
	
	//setters
	protected void setSSN(String ssn) {
		SSN = ssn;
	}
	
	protected void setName(Name fullName) {
		name = fullName;
	}
	
	//getters
	public String getSSN() {
		return SSN;
	}
	
	public Name getName() {
		return new Name(name);
	}
	
	public String toString() {
		String str = String.format("%14s", SSN);
		return str;
	}
	
	public boolean equals(Depositor myDepositor) {
		
		if(SSN.equals(myDepositor.SSN) && name.equals(myDepositor.name))
			return true;
		else
			return false;
		
	}
	
}
