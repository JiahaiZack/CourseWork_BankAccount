package HW8;

import java.util.Calendar;

public abstract class genTransactionReceipt {

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

	public genTransactionReceipt() {

	}

	public genTransactionReceipt(int num, int CD, boolean successIndicator, String failure, String type, double preTran, double postTran, Calendar transactionDate, TransactionTicket tick, Calendar date, String TransactType, double promptAmount, String transactStatus, String ReasonForFailure) {
		accountNum = num;
		termOfCD = CD;
		successIndicatorFlag = successIndicator;
		transactionFailure = failure;
		accountType = type;
		preTransaction = preTran;
		postTransaction = postTran;
		TransactionMaturityDate = transactionDate;
		ticket = tick;

		TransactionDate = date;
		TransactionType = TransactType;
		Amount = promptAmount;
		TransactionStatus = transactStatus;
		Reason = ReasonForFailure;
	}

	public genTransactionReceipt(genTransactionReceipt receipt) {
		accountNum = receipt.accountNum;
		termOfCD = receipt.termOfCD;
		successIndicatorFlag = receipt.successIndicatorFlag;
		transactionFailure = receipt.transactionFailure;
		accountType = receipt.accountType;
		preTransaction = receipt.preTransaction;
		postTransaction = receipt.postTransaction;
		TransactionMaturityDate = receipt.TransactionMaturityDate;
		ticket = receipt.ticket;

		TransactionDate = receipt.TransactionDate;
		TransactionType = receipt.TransactionType;
		Amount = receipt.Amount;
		TransactionStatus = receipt.TransactionStatus;
		Reason = receipt.Reason;
	}
	//abstract setters
	protected abstract void setAccountNumber(int num);
	
	protected abstract void setTermOfCD(int CD);
	
	protected abstract void setTransactionSuccessIndicatorFlag(boolean successIndicator);
	
	protected abstract void setTransactionFailureReason(String failure);
	
	protected abstract void setAccountType(String type);
	
	protected abstract void setPreTransactionBalance(double preTran);
	
	protected abstract void setPostTransactionBalance(double postTran);
	
	protected abstract void setPostTransactionMaturityDate(Calendar transactionDate);
	
	protected abstract void setTransactionTicket(TransactionTicket tick);

	protected abstract void setCalendarDate(Calendar date);
	
	protected abstract void setTransactionType(String TransactType);
	
	protected abstract void setPromptAmount(double promptAmount);
	
	protected abstract void setTransactionStatus(String transactStatus);
	
	protected abstract void setReasonForFailure(String ReasonForFailure);
	
	//abstract getters
	protected abstract int getAccountNumber();
	
	protected abstract int getTermOfCD();
	
	protected abstract boolean getTransactionSuccessIndicatorFlag();
	
	protected abstract String getTransactionFailureReason();
	
	protected abstract String getAccountType();
	
	protected abstract double getPreTransactionBalance();
	
	protected abstract double getPostTransactionBalance();
	
	protected abstract Calendar getPostTransactionMaturityDate();
	
	protected abstract TransactionTicket getTransactionTicket();

	protected abstract Calendar getCalendarDate();
	
	protected abstract String getTransactionType();
	
	protected abstract double getPromptAmount();
	
	protected abstract String getTransactionStatus();
	
	protected abstract String getReasonForFailure();

}
