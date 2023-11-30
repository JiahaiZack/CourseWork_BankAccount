package HW8;

import java.util.*;

public abstract class genAccount {
	
	private Depositor depositor;
	private int acctNum;
	private String acctType;
	private String accountStatus;
	private ArrayList<TransactionReceipt> transactionHistory;
	private double balance;
	private String date;
	
	//no-arg Constructor
	public genAccount() {
		
	}
	
	//parameterized Constructor
	public genAccount(int num, String type, String status, double bal, String mature, Depositor deposit) {
		acctNum = num;
		acctType = type;
		accountStatus = status;
		balance = bal;
		date = mature;
		depositor = deposit;
		transactionHistory = new ArrayList<TransactionReceipt>();
	}
	
	//copy constructor
	public genAccount(genAccount account) {
		acctNum = account.acctNum;
		acctType = account.acctType;
		accountStatus = account.accountStatus;
		balance = account.balance;
		date = account.date;
		depositor = account.depositor;
		transactionHistory = account.transactionHistory;
	}
	//abstract setters
	protected abstract void setAcctNum(int num);
		
	protected abstract void setAcctType(String type);
	
	protected abstract void setAccountStatus(String status);
	
	protected abstract void setBalance(double bal);
	
	protected abstract void setTime(String date);
	
	protected abstract void setDepositor(Depositor deposit);
	
	//abstract getters
	protected abstract TransactionReceipt getBalance(TransactionTicket balanceTicket) throws AccountClosedException;
	
	protected abstract TransactionReceipt makeDeposit(TransactionTicket depositTicket) throws InvalidAmountException, AccountClosedException, CDMaturityDateException;
	
	protected abstract TransactionReceipt makeWithdrawal(TransactionTicket withdrawTicket) throws InvalidAmountException, AccountClosedException, CDMaturityDateException, InsufficientFundsException;
	
	protected abstract TransactionReceipt clearCheck(Check check) throws InvalidAmountException, AccountClosedException, InsufficientFundsException, PostDatedCheckException, CheckTooOldException;
	
	protected abstract TransactionReceipt closeAcct(TransactionTicket closeTicket, String status) throws AccountClosedException;
	
	protected abstract TransactionReceipt reopenAcct(TransactionTicket reopenTicket, String status) throws AccountClosedException;
	
	protected abstract int getAcctNumber();

	protected abstract String getAcctType();

	protected abstract String getAccountStatus();
	
	protected abstract double getBalance();
	
	protected abstract String getDate();
	
	protected abstract Depositor getDepositor();

}
