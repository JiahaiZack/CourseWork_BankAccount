package HW8;

import java.util.Calendar;

public abstract class genTransactionTicket {
	
	private int AccountNumber;
	private Calendar dateOfTransaction;
	private String typeOfTransaction;
	private double amountOfTransaction;
	private int termOfCD;
	
	private int historyIndex;
	
	//no-arg constructor
	public genTransactionTicket() {
		
	}
	//parameterized Constructor
	public genTransactionTicket(int Account, Calendar transactionDate, String transactionType, double transactionAmount, int CDterm) {
		AccountNumber = Account;
		dateOfTransaction = transactionDate;
		typeOfTransaction = transactionType;
		amountOfTransaction = transactionAmount;
		termOfCD = CDterm;
	}
	
	public genTransactionTicket(genTransactionTicket ticket) {
		AccountNumber = ticket.AccountNumber;
		dateOfTransaction = ticket.dateOfTransaction;
		typeOfTransaction = ticket.typeOfTransaction;
		amountOfTransaction = ticket.amountOfTransaction;
		termOfCD = ticket.termOfCD;
	}
	
	public genTransactionTicket(int index) {
		historyIndex = index;
	}
	
	//abstract setters
	protected abstract void setAccountNumber(int Account);
	
	protected abstract void setDateOfTransaction(Calendar transactionDate);
	
	protected abstract void setTypeOfTransaction(String transactionType);
	
	protected abstract void setAmountOfTransaction(double transactionAmount);
	
	protected abstract void setTermOfCD(int CDterm);
	
	protected abstract void setHistoryIndex(int index);

	//abstract getters
	protected abstract int getAccountNumber();
	
	protected abstract Calendar getDateOfTransaction();
	
	protected abstract String getTypeOfTransaction();
	
	protected abstract double getAmountOfTransaction();
	
	protected abstract int getTermOfCD();
	
	protected abstract int getHistoryIndex();
	
}
