package HW8;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Account extends genAccount{
	
	private Depositor depositor;
	private int acctNum;
	private String acctType;
	private String accountStatus;
	private double balance;
	private String date;
	
	//Account Transaction History .dat files
	final private long ACCOUNT_SIZE = 292;
	
	//no-arg constructor
	public Account() {
		depositor = new Depositor();
		acctNum = 0;
		acctType = "";
		balance = 0;
		date = "";
		accountStatus = "";
	}
	
	//Parameterized Constructor
	public Account(int num, String type, String status, double bal, String mature, Depositor deposit) {
		setAcctNum(num);
		setAcctType(type);
		setAccountStatus(status);
		setBalance(bal);
		setTime(mature);
		setDepositor(deposit);
	}
	
	
	//copy constructor
	public Account(Account myAccount) {
		depositor = new Depositor(myAccount.depositor);
		acctNum = myAccount.acctNum;
		acctType = myAccount.acctType;
		accountStatus = myAccount.accountStatus;
		balance = myAccount.balance;
		date = myAccount.date;
	}
	
	public void writeTransactionHistoryData(TransactionReceipt receipt, int acct) {
		try {
			RandomAccessFile file = new RandomAccessFile("C:\\Users\\ispek\\eclipse-workspace\\CISC 3115 Zeigler\\src\\HW8\\Account_" + acct + ".dat", "rw");
			String data = getTransactionHistoryData(receipt, acct);
			file.setLength(file.length() + ACCOUNT_SIZE);
			file.seek(file.length() - ACCOUNT_SIZE);
			file.writeChars(data);
			file.close();
		} catch (IOException e) {
			System.out.println("!Error! Binary File DNE");
		}
		
	}
	
	public String getTransactionHistoryData(TransactionReceipt receipt, int acct) {
		
		try {
			RandomAccessFile file = new RandomAccessFile("C:\\Users\\ispek\\eclipse-workspace\\CISC 3115 Zeigler\\src\\HW8\\Account_" + acct + ".dat", "rw");
			Calendar date = Calendar.getInstance();
			String strDate = String.format("%02d/%02d/%4d", date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.YEAR));
			String data = String.format("%-20s %-20s %-20.2f %-20s %-20.2f %-40s", strDate, receipt.getAccountType(), receipt.getPromptAmount(), receipt.getTransactionStatus(), receipt.getPreTransactionBalance(), receipt.getReasonForFailure());
			file.close();
			return data;
		} catch(IOException e) {
			System.out.println("!Error! File Not Found");
			return "";
		}
		
	}
	
	public long getTransactionSize(int acct) {
		
		try {
			try (RandomAccessFile file = new RandomAccessFile("C:\\Users\\ispek\\eclipse-workspace\\CISC 3115 Zeigler\\src\\HW8\\Account_" + acct + ".dat", "rw")) {
				return (file.length()/ACCOUNT_SIZE);
			}
		} catch(IOException e) {
			System.out.println("!Error! File not Foundd");
			return 0;
		} 
		
	}
	
	//setters
	protected void setAcctNum(int num) {
		acctNum = num;
	}
	
	protected void setAcctType(String type) {
		acctType = type;
	}
	
	protected void setAccountStatus(String status) {
		accountStatus = status;
	}
	
	protected void setBalance(double bal) {
		balance = bal;
	}
	
	protected void setDepositor(Depositor deposit) {
		depositor = deposit;
	} 
	
	protected void setTime(String mature) {
		date = mature;
	}
	
	//getters
	public TransactionReceipt getBalance(TransactionTicket balanceTicket) throws AccountClosedException{
		
		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		boolean Found = true;
		
		Account account;
		
		if(accountStatus.equals("Closed")){
			Found = false;
			Calendar today = Calendar.getInstance();
			receipt = new TransactionReceipt(0, 0, Found, "", "Balance", getBalance(), getBalance(), null, balanceTicket, today, "", balanceTicket.getAmountOfTransaction(), "Failed", "Account is Closed");
			copyReceipt = new TransactionReceipt(receipt);
			writeTransactionHistoryData(copyReceipt, balanceTicket.getAccountNumber());
			String err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Balance",
						  										  "--------------------------",
						  										  "Account Number: ", balanceTicket.getAccountNumber(),
						  										  "!Error! Account is Closed");
			throw new AccountClosedException(err);
		}
		else if(acctType.equals("CD")) { 
			Found = true;
			account = new CDAccount(acctNum, acctType, accountStatus, balance, date, depositor);
			Calendar today = Calendar.getInstance();
			receipt = new TransactionReceipt(0, 0, Found, "", "Balance", account.getBalance(), account.getBalance(), null, balanceTicket, today, "", balanceTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
			writeTransactionHistoryData(copyReceipt, balanceTicket.getAccountNumber());
			receipt = new TransactionReceipt(balanceTicket.getAccountNumber(), balanceTicket.getTermOfCD(), Found, "", acctType, account.getBalance(), account.getBalance(), account.getMatureDate(), balanceTicket, today, "Balance Inquiry", balanceTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
		}
		else {
			Found = true;
			if(acctType.equals("Checking")) {
				account = new CheckingAccount(acctNum, acctType, accountStatus, balance, date, depositor);
				Calendar today = Calendar.getInstance();
				receipt = new TransactionReceipt(0, 0, Found, "", "Balance", account.getBalance(), account.getBalance(), null, balanceTicket, today, "", balanceTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
				writeTransactionHistoryData(copyReceipt, balanceTicket.getAccountNumber());
				receipt = new TransactionReceipt(balanceTicket.getAccountNumber(), balanceTicket.getTermOfCD(), Found, "", acctType, account.getBalance(), account.getBalance(), null, balanceTicket, today, "Balance Inquiry", balanceTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
			}
			else {
				account = new SavingsAccount(acctNum, acctType, accountStatus, balance, date, depositor);
				Calendar today = Calendar.getInstance();
				receipt = new TransactionReceipt(0, 0, Found, "", "Balance", account.getBalance(), account.getBalance(), null, balanceTicket, today, "", balanceTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
				writeTransactionHistoryData(copyReceipt, balanceTicket.getAccountNumber());
				receipt = new TransactionReceipt(balanceTicket.getAccountNumber(), balanceTicket.getTermOfCD(), Found, "", acctType, account.getBalance(), account.getBalance(), null, balanceTicket, today, "Balance Inquiry", balanceTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
			}
		}
		
		return copyReceipt;
		
	}
	
	public TransactionReceipt makeDeposit(TransactionTicket depositTicket) throws InvalidAmountException, AccountClosedException, CDMaturityDateException{
		TransactionReceipt receipt = null;
		return receipt;
	}
	
	public TransactionReceipt makeWithdrawal(TransactionTicket withdrawTicket) throws InvalidAmountException, AccountClosedException, CDMaturityDateException, InsufficientFundsException { 
		TransactionReceipt receipt = null;
		return receipt;
	}
	
	public TransactionReceipt clearCheck(Check check) throws InvalidAmountException, AccountClosedException, InsufficientFundsException, PostDatedCheckException, CheckTooOldException {
		TransactionReceipt receipt = null;
		return receipt;
	}
	
	public TransactionReceipt closeAcct(TransactionTicket closeTicket, String status) throws AccountClosedException{
		
		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		Boolean Found = false;
		String err;
		Account account;
		
		if(status.equals("Closed")) {
			Calendar today = Calendar.getInstance();
			err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Close Account",
					"-----------------------------",
					"Account Number: ", acctNum,
					"!Error! Account status: Closed");
			receipt = new TransactionReceipt(0, 0, Found, "", "Close Account", balance, balance, null, closeTicket, today, "", closeTicket.getAmountOfTransaction(), "Failed", "Account is Closed");
			copyReceipt = new TransactionReceipt(receipt);
			writeTransactionHistoryData(copyReceipt, closeTicket.getAccountNumber());
			throw new AccountClosedException(err);
			
		}
		else {
			Found = true;
			Calendar today = Calendar.getInstance();
			
			if(acctType.equals("CD")) {
				account = new CDAccount(acctNum, acctType, accountStatus, balance, date, depositor);
				receipt = new TransactionReceipt(0, 0, Found, "", "Close Account", account.getBalance(), account.getBalance(), null, closeTicket, today, "", closeTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
				writeTransactionHistoryData(copyReceipt, closeTicket.getAccountNumber());
				setAccountStatus("Closed");
				receipt = new TransactionReceipt(closeTicket.getAccountNumber(), 0, Found, "", acctType, account.getBalance(), account.getBalance(), null, closeTicket, today, "Close Account", closeTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
			}
			else if(acctType.equals("Checking")) {
				account = new CheckingAccount(acctNum, acctType, accountStatus, balance, date, depositor);
				receipt = new TransactionReceipt(0, 0, Found, "", "Close Account", account.getBalance(), account.getBalance(), null, closeTicket, today, "", closeTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
				writeTransactionHistoryData(copyReceipt, closeTicket.getAccountNumber());
				setAccountStatus("Closed");
				receipt = new TransactionReceipt(closeTicket.getAccountNumber(), 0, Found, "", acctType, account.getBalance(), account.getBalance(), null, closeTicket, today, "Close Account", closeTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
			}
			else {
				account = new SavingsAccount(acctNum, acctType, accountStatus, balance, date, depositor);
				receipt = new TransactionReceipt(0, 0, Found, "", "Close Account", account.getBalance(), account.getBalance(), null, closeTicket, today, "", closeTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
				writeTransactionHistoryData(copyReceipt, closeTicket.getAccountNumber());
				setAccountStatus("Closed");
				receipt = new TransactionReceipt(closeTicket.getAccountNumber(), 0, Found, "", acctType, account.getBalance(), account.getBalance(), null, closeTicket, today, "Close Account", closeTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
			}
			
		}
		
		return copyReceipt;
		
	}
	
	public TransactionReceipt reopenAcct(TransactionTicket reopenTicket, String status) throws AccountClosedException{
		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		
		Account account;
		String err;
		Boolean Found = false;
		
		if(status.equals("Open")) {
			Calendar today = Calendar.getInstance();
			err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Reopen Account",
					"-----------------------------",
					"Account Number: ", acctNum,
					"!Error! Account status: Open");
			receipt = new TransactionReceipt(0, 0, Found, "", "Reopen Account", balance, balance, null, reopenTicket, today, "", reopenTicket.getAmountOfTransaction(), "Failed", "Account is Already Opened");
			copyReceipt = new TransactionReceipt(receipt);
			writeTransactionHistoryData(copyReceipt, reopenTicket.getAccountNumber());
			throw new AccountClosedException(err);
		}
		else{	
			Found = true;
			Calendar today = Calendar.getInstance();
			
			if(acctType.equals("CD")) {
				account = new CDAccount(acctNum, acctType, accountStatus, balance, date, depositor);
				receipt = new TransactionReceipt(0, 0, Found, "", "Open Account", account.getBalance(), account.getBalance(), null, reopenTicket, today, "", reopenTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
				writeTransactionHistoryData(copyReceipt, reopenTicket.getAccountNumber());
				setAccountStatus("Open");
				receipt = new TransactionReceipt(reopenTicket.getAccountNumber(), 0, Found, "", acctType, account.getBalance(), account.getBalance(), null, reopenTicket, today, "Open Account", reopenTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
			}
			else if(acctType.equals("Checking")) {
				account = new CheckingAccount(acctNum, acctType, accountStatus, balance, date, depositor);
				receipt = new TransactionReceipt(0, 0, Found, "", "Open Account", account.getBalance(), account.getBalance(), null, reopenTicket, today, "", reopenTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
				writeTransactionHistoryData(copyReceipt, reopenTicket.getAccountNumber());
				setAccountStatus("Open");
				receipt = new TransactionReceipt(reopenTicket.getAccountNumber(), 0, Found, "", acctType, account.getBalance(), account.getBalance(), null, reopenTicket, today, "Open Account", reopenTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
			}
			else {
				account = new SavingsAccount(acctNum, acctType, accountStatus, balance, date, depositor);
				receipt = new TransactionReceipt(0, 0, Found, "", "Open Account", account.getBalance(), account.getBalance(), null, reopenTicket, today, "", reopenTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
				writeTransactionHistoryData(copyReceipt, reopenTicket.getAccountNumber());
				setAccountStatus("Open");
				receipt = new TransactionReceipt(reopenTicket.getAccountNumber(), 0, Found, "", acctType, account.getBalance(), account.getBalance(), null, reopenTicket, today, "Open Account", reopenTicket.getAmountOfTransaction(), "Processed", "");
				copyReceipt = new TransactionReceipt(receipt);
			}
			
		}
		
		return copyReceipt;
		
	}
	
	//methods that gets overridden
	public Calendar getMatureDate() {
		return null;
	}
	
	public int getAcctNumber() {
		return acctNum;
	}
	
	public String getAcctType() {
		return acctType;
	}
	
	public String getAccountStatus() {
		return accountStatus;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public String getDate() {
		return date;
	}
	
	public Depositor getDepositor() {
		return new Depositor(depositor);
	}
	
	public String toString() {
		
		String str = String.format("%s %s %18s %16s %15s %5s %9.2f", depositor.getName().toString(), depositor.toString(), acctNum, acctType, accountStatus, "$", balance);

		return str;
	
	}
	
	public Boolean equals(Account myAccount) {
		if(acctNum == myAccount.acctNum && depositor.equals(myAccount.depositor)) 
			return true;
		else
			return false;
	}
	
}
