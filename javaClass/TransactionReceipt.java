package HW8;

import java.util.*;

public class TransactionReceipt extends genTransactionReceipt {

	private int accountNum;
	private int termOfCD;
	private boolean successIndicatorFlag;
	private String transactionFailure;
	private String accountType;
	private double preTransaction;
	private double postTransaction;
	private Calendar TransactionMaturityDate;
	private TransactionTicket ticket;
	
	private Calendar TransactionDate;
	private String TransactionType;
	private double Amount;
	private String TransactionStatus;
	private String Reason;
	
	//parameterized Constructors
	public TransactionReceipt(int num, int CD, boolean successIndicator, String failure, String type, double preTran, double postTran, Calendar transactionDate, TransactionTicket tick, Calendar date, String TransactType, double promptAmount, String transactStatus, String ReasonForFailure) {
		setAccountNumber(num);
		setTermOfCD(CD);
		setTransactionSuccessIndicatorFlag(successIndicator);
		setTransactionFailureReason(failure);
		setAccountType(type);
		setPreTransactionBalance(preTran);
		setPostTransactionBalance(postTran);
		setPostTransactionMaturityDate(transactionDate);
		setTransactionType(TransactType);
		setTransactionTicket(tick);
		setCalendarDate(date);
		setPromptAmount(promptAmount);
		setTransactionStatus(transactStatus);
		setReasonForFailure(ReasonForFailure);
	}
	//copy constructor
	public TransactionReceipt(TransactionReceipt receipt) {
		accountNum = receipt.accountNum;
		termOfCD = receipt.termOfCD;
		successIndicatorFlag = receipt.successIndicatorFlag;
		transactionFailure = receipt.transactionFailure;
		accountType = receipt.accountType;
		preTransaction = receipt.preTransaction;
		postTransaction = receipt.postTransaction;
		TransactionMaturityDate = receipt.TransactionMaturityDate;
		ticket = new TransactionTicket(receipt.ticket);
		TransactionDate = receipt.TransactionDate;
		TransactionType = receipt.TransactionType;
		Amount = receipt.Amount;
		TransactionStatus = receipt.TransactionStatus;
		Reason = receipt.Reason;
	}
	
	//setters
	protected void setAccountNumber(int num) {
		accountNum = num;
	}
	protected void setTermOfCD(int CD) {
		termOfCD = CD;
	}
	protected void setTransactionSuccessIndicatorFlag(boolean successIndicator) {
		successIndicatorFlag = successIndicator;
	}
	protected void setTransactionFailureReason(String failure) {
		transactionFailure = failure;
	}
	protected void setAccountType(String type) {
		accountType = type;
	}
	protected void setPreTransactionBalance(double preTran) {
		preTransaction = preTran;
	}
	protected void setPostTransactionBalance(double postTran) {
		postTransaction = postTran;
	}
	protected void setPostTransactionMaturityDate(Calendar transactionDate) {
		TransactionMaturityDate = transactionDate;
	}
	protected void setTransactionTicket(TransactionTicket tick) {
		ticket = tick;
	}
	protected void setCalendarDate(Calendar date) {
		TransactionDate = date;
	}
	protected void setTransactionType(String TransactType) {
		TransactionType = TransactType;
	}
	protected void setPromptAmount(double promptAmount) {
		Amount = promptAmount;
	}
	protected void setTransactionStatus(String transactStatus) {
		TransactionStatus = transactStatus;
	}
	protected void setReasonForFailure(String ReasonForFailure) {
		Reason = ReasonForFailure;
	}
	
	
	
	
	//getters
	public int getAccountNumber() {
		return accountNum;
	}
	public int getTermOfCD() {
		return termOfCD;
	}
	public boolean getTransactionSuccessIndicatorFlag() {
		return successIndicatorFlag;
	}
	public String getTransactionFailureReason() {
		return transactionFailure;
	}
	public String getAccountType() {
		return accountType;
	}
	public double getPreTransactionBalance() {
		return preTransaction;
	}
	public double getPostTransactionBalance() {
		return postTransaction;
	}
	public Calendar getPostTransactionMaturityDate() {
		return TransactionMaturityDate;
	}
	public TransactionTicket getTransactionTicket() {
		return ticket;
	}
	public Calendar getCalendarDate() {
		return TransactionDate;
	}
	public String getTransactionType() {
		return TransactionType;
	}
	public double getPromptAmount() {
		return Amount;
	}
	public String getTransactionStatus() {
		return TransactionStatus;
	}
	public String getReasonForFailure() {
		return Reason;
	}
	
	//toString method
	public String toString() {
		String str;
		Calendar date;
		String strDate;
		
		if(TransactionType.equals("Balance Inquiry")) {
			if(accountType.equals("CD")) {
				strDate = String.format("%02d/%02d/%4d", TransactionMaturityDate.get(Calendar.MONTH) + 1, TransactionMaturityDate.get(Calendar.DAY_OF_MONTH), TransactionMaturityDate.get(Calendar.YEAR));
				str = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%s\n\n", " Transaction Type - Balance",
															  	 	 			 "--------------------------",
															  	 	 			 "Account Number: ", accountNum,
															  	 	 			 "Account Type: ", accountType,
															  	 	 			 "Balance: ", preTransaction,
															  	 	 			 "Maturity Date: ", strDate);
			}
			else 
				str = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n\n", " Transaction Type - Balance",
																			"--------------------------",
																			"Account Number: ", accountNum,
																			"Account Type: ", accountType,
																			"Balance: ", preTransaction);
		}
		else if(TransactionType.equals("Deposit")) {
			if(accountType.equals("CD")) {
				strDate = String.format("%02d/%02d/%4d", TransactionMaturityDate.get(Calendar.MONTH) + 1, TransactionMaturityDate.get(Calendar.DAY_OF_MONTH), TransactionMaturityDate.get(Calendar.YEAR));
				str = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%s\n %s%.2f\n %s%.2f\n %s%d\n %s%s\n\n", " Transaction Type - Deposit",
															  	 	 			 								 "--------------------------",
															  	 	 			 								 "Account Number: ", accountNum,
															  	 	 			 								 "Account Type: ", accountType,
															  	 	 			 								 "Balance: ", preTransaction,
															  	 	 			 								 "Maturity Date: ", strDate,
															  	 	 			 								 "Deposit Amount: ", Amount,
															  	 	 			 								 "New Balance: ", postTransaction,
															  	 	 			 								 "CD Term Entered(Months): ", termOfCD,
															  	 	 			 								 "CD New Maturity Date: ", strDate);
			}
			else
				str = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s%.2f\n\n", " Transaction Type - Deposit",
																			"--------------------------",
																			"Account Number: ", accountNum,
																			"Account Type: ", accountType,
																			"Balance: ", preTransaction,
																			"Deposit Amount: ", Amount,
																			"New Balance: ", postTransaction);
		}
		else if(TransactionType.equals("Withdrawal")) {
			if(accountType.equals("CD")) {
				strDate = String.format("%02d/%02d/%4d", TransactionMaturityDate.get(Calendar.MONTH) + 1, TransactionMaturityDate.get(Calendar.DAY_OF_MONTH), TransactionMaturityDate.get(Calendar.YEAR));
				str = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%s\n %s%.2f\n %s%.2f\n %s%d\n %s%s\n\n", " Transaction Type - Withdrawal",
															  	 	 			 								 "-----------------------------",
															  	 	 			 								 "Account Number: ", accountNum,
															  	 	 			 								 "Account Type: ", accountType,
															  	 	 			 								 "Balance: ", preTransaction,
															  	 	 			 								 "Maturity Date: ", strDate,
															  	 	 			 								 "Withdraw Amount: ", Amount,
															  	 	 			 								 "New Balance: ", postTransaction,
															  	 	 			 								 "CD Term Entered(Months): ", termOfCD,
															  	 	 			 								 "CD New Maturity Date: ", strDate);
			}
			else
				str = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s%.2f\n\n", " Transaction Type - Withdrawal",
																							"-----------------------------",
																							"Account Number: ", accountNum,
																							"Account Type: ", accountType,
																							"Balance: ", preTransaction,
																							"Withdraw Amount: ", Amount,
																							"New Balance: ", postTransaction);
		}
		else if(TransactionType.equals("Clear Check")) {
			strDate = String.format("%02d/%02d/%4d", ticket.getDateOfTransaction().get(Calendar.MONTH) + 1, ticket.getDateOfTransaction().get(Calendar.DAY_OF_MONTH), ticket.getDateOfTransaction().get(Calendar.YEAR));
			str = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s%s\n %s%.2f\n\n", " Transaction Type - Clear Check",
																							   "------------------------------",
																							   "Account Number: ", accountNum,
																							   "Account Type: ", accountType,
																							   "Balance: ", preTransaction,
																							   "Check Amount: ", Amount,
																							   "Check Date: ", strDate,
																							   "New Balance: ", postTransaction);
		}
		else if(TransactionType.equals("New Account")) {
			if(accountType.equals("CD")) {
				strDate = String.format("%02d/%02d/%4d", TransactionMaturityDate.get(Calendar.MONTH) + 1, TransactionMaturityDate.get(Calendar.DAY_OF_MONTH), TransactionMaturityDate.get(Calendar.YEAR));
				str = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%d\n %s%s\n %s%d%s\n\n", " Transaction Type - New Account",
																								 "------------------------------",
																								 "Account Number: ", accountNum,
																								 "Account Type: ", accountType,
																								 "Balance: ", preTransaction,
																								 "CD Term: ", termOfCD,
																								 "CD Maturity Date: ", strDate,
																								 "Success! Account ", accountNum, " is Created");
			}
			else
				str = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%d%s\n\n", " Transaction Type - New Account",
																				   "------------------------------",
																				   "Account Number: ", accountNum,
																				   "Account Type: ", accountType,
																				   "Balance: ", preTransaction,
																				   "Success! Account ", accountNum, " is Created");
		}
		else if(TransactionType.equals("Close Account")) {
			str = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Close Account",
					 									   "--------------------------------",
					 									   "Account Number: ", accountNum,
					 									   "Account is Closed As Requested");
		}
		else if(TransactionType.equals("Open Account")) {
			str = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Reopen Account",
					 									   "---------------------------------",
					 									   "Account Number: ", accountNum,
					 									   "Account is Reopened As Requested");
		}
		else if(TransactionType.equals("Delete Account")) {
			str = String.format("%s\n %s\n %s%d\n %s%d%s\n\n", " Transaction Type - Delete Account",
														   	   "---------------------------------",
														   	   "Account Number: ", accountNum,
														   	   "Account ", accountNum, " is Deleted from the Database");
		}
		//for Account Info History
		else {
			date = Calendar.getInstance();
			strDate = String.format("%02d/%02d/%4d", date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.YEAR));
			str = String.format("%12s %16s %11.2f %11s %11.2f %5s %-20s\n", strDate, accountType, Amount, TransactionStatus, preTransaction, " ", Reason);
		}
		return str;
	}
	
}
