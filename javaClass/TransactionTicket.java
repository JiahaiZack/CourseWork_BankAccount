package HW8;

import java.util.*;

public class TransactionTicket extends genTransactionTicket{

	private int AccountNumber;
	private Calendar dateOfTransaction;
	private String typeOfTransaction;
	private double amountOfTransaction;
	private int termOfCD;
	
	private int historyIndex;
	
	//no-arg constructor
	public TransactionTicket() {
		AccountNumber = 0;
		dateOfTransaction = null;
		typeOfTransaction = "";
		amountOfTransaction = 0;
		termOfCD = 0;
	}
	
	//parameterized Constructors
	public TransactionTicket(int Account, Calendar transactionDate, String transactionType, double transactionAmount, int CDterm) {
		setAccountNumber(Account);
		setDateOfTransaction(transactionDate);
		setTypeOfTransaction(transactionType);
		setAmountOfTransaction(transactionAmount);
		setTermOfCD(CDterm);
	}
	
	//copy constructor
	public TransactionTicket(TransactionTicket ticket) {
		AccountNumber = ticket.AccountNumber;
		dateOfTransaction = ticket.dateOfTransaction;
		typeOfTransaction = ticket.typeOfTransaction;
		amountOfTransaction = ticket.amountOfTransaction;
		termOfCD = ticket.termOfCD;
	}
	
	public TransactionTicket(int index) {
		setHistoryIndex(index);
	}
	
	//setter
	protected void setAccountNumber(int Account) {
		AccountNumber = Account;
	}
	
	protected void setDateOfTransaction(Calendar transactionDate) {
		dateOfTransaction = transactionDate;
	}
	
	protected void setTypeOfTransaction(String transactionType) {
		typeOfTransaction = transactionType;
	}
	
	protected void setAmountOfTransaction(double transactionAmount) {
		amountOfTransaction = transactionAmount;
	}
	
	protected void setTermOfCD(int CDterm) {
		termOfCD = CDterm;
	}
	
	protected void setHistoryIndex(int index) {
		historyIndex = index;
	}
	
	//getter
	public int getAccountNumber() {
		return AccountNumber;
	}
	
	public Calendar getDateOfTransaction() {
		return dateOfTransaction;
	}
	
	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}
	
	public double getAmountOfTransaction() {
		return amountOfTransaction;
	}
	
	public int getTermOfCD() {
		return termOfCD;
	}
	
	public int getHistoryIndex() {
		return historyIndex;
	}
	
	public String toString() {
		
		String str;
		
		if(typeOfTransaction.equals("CD")) {
			String strDate = String.format("%02d/%02d/%4d", dateOfTransaction.get(Calendar.MONTH) - 1, dateOfTransaction.get(Calendar.DAY_OF_MONTH), dateOfTransaction.get(Calendar.YEAR));
			str = String.format("%s%d\n %s%s\n %s%.2f\n %s%d\n %s%s"," Account Number: ", AccountNumber,
										  								"Account Type: ", typeOfTransaction,
										  								"Balance: ", amountOfTransaction,
										  								"CD Term: ", termOfCD,
										  								"CD Maturity Date: ", strDate);
		}
		else
			str = String.format("%s%d\n %s%s\n %s%.2f"," Account Number: ", AccountNumber,
															"Account Type: ", typeOfTransaction,
															"Balance: ", amountOfTransaction);
		
		return str;
		
	}
	
}
	
	
