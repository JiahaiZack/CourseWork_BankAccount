package HW8;

public abstract class genDepositor {

	protected String SSN;
	protected Name name;
	
	public genDepositor() {
		
	}
	
	public genDepositor(String ssn, Name name) {
		SSN = ssn;
		this.name = name;
	}
	
	public genDepositor(genDepositor deposit) {
		SSN = deposit.SSN;
		name = deposit.name;
	}

	//abstract setters
	protected abstract void setSSN(String ssn);
	
	protected abstract void setName(Name fullName);
	
	//abstract getters
	protected abstract String getSSN();
	
	protected abstract Name getName();
	
}
