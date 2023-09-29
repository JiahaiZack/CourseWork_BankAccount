package HW8;

public class Name extends genName{
	
	private String firstName;
	private String lastName;
	
	//no-arg constructor
	public Name() {
		firstName = "";
		lastName = "";
	}
	
	//parameterized Constructors
	public Name(String last, String first) {
		setLast(last);
		setFirst(first);
	}
	
	//copy constructor
	public Name(Name myName) {
		lastName = myName.lastName;
		firstName = myName.firstName;
	}
	
	//setters
	protected void setLast(String last) {
		lastName = last;
	}
	
	protected void setFirst(String first) {
		firstName = first;
	}
	
	//getters
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	//toString method
	public String toString() {
		String str = String.format("%9s %15s", lastName, firstName);
		return str;
	}
	
	public boolean equals(Name myName) {
		
		if(lastName.equals(myName.lastName) && firstName.equals(myName.firstName))
			return true;
		else
			return false;
	}
	
}
