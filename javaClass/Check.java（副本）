package HW8;

import java.util.*;

public class Check {
	
	private int acct;
	private double bal;
	private Calendar time;
	
	//parameterized Constructors
	public Check(int account, double amount, Calendar date) {
		setAccount(account);
		setAmount(amount);
		timeOfTheCheck(date);
	}
	
	//copy constructor
	public Check(Check myCheck) {
		acct = myCheck.acct;
		bal = myCheck.bal;
		time = myCheck.time;
	}
	
	//setters
	private void setAccount(int account) {
		acct = account;
	}
	
	private void setAmount(double amount) {
		bal = amount;
	}
	
	private void timeOfTheCheck(Calendar checkTime) {
		time = checkTime;
	}
	
	//getters
	public int getAccount() {
		return acct;
	}
	
	public double getBalance() {
		return bal;
	}
	
	public Calendar getTime() {
		return time;
	}
	
	public String toString() {
		String strDate = String.format("%02d/%02d/%4d", time.get(Calendar.MONTH) + 1, time.get(Calendar.DAY_OF_MONTH), time.get(Calendar.YEAR));
		String str = String.format("%s%.2f\n %s%s","Check Amount: ", bal,
												 	 "Check Date: ", strDate);
		return str;
	}
	
}
