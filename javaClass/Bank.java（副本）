package HW8;

import java.io.*;
import java.util.*;

public class Bank {
	
	//BankAccount.dat
	final private long DATA_SIZE = 176;
	
	private RandomAccessFile file;
	
	//Account Transaction History .dat files
	final private long ACCOUNT_SIZE = 292;
	
	//static member variables
	private static double totalSavings;
	private static double totalChecking;
	private static double totalCD;
	private static double totalAll;
	
	//no-arg Constructor
	public Bank() throws IOException{
		file = new RandomAccessFile("C:\\Users\\ispek\\eclipse-workspace\\CISC 3115 Zeigler\\src\\HW8\\BankAccounts.dat", "rw");
	}
	
	public RandomAccessFile getRandomAccessFile() {
		return file;
	}
	
	public long getDATASIZE() {
		return DATA_SIZE;
	}
	
	public long getDataSize(long accountNum) {
		return DATA_SIZE * accountNum;
	}
	
	public String writeInitialAccountsToFile(Account account) {
		
		String dataLine = String.format("%-10s %-10s %-10s %-10d %-10s %-10s %-10.2f %-11s", account.getDepositor().getName().getLastName(),
																					 		 account.getDepositor().getName().getFirstName(),
																					 		 account.getDepositor().getSSN(),
																					 		 account.getAcctNumber(),
																					 		 account.getAcctType(),
																					 		 account.getAccountStatus(),
																					 		 account.getBalance(),
																					 		 account.getDate());	
		return dataLine;
		
	}
	
	public void appendingNewAccountFile(Account account) {
		
		try {
			getRandomAccessFile().setLength(getRandomAccessFile().length() + DATA_SIZE);
			getRandomAccessFile().seek(getRandomAccessFile().length() - DATA_SIZE);
			getRandomAccessFile().writeChars(appendingNewAccountData(account));
		} catch(IOException e) {
			System.out.println("!Error! Binary File not Found");
		}
		
	}
	
	public String appendingNewAccountData(Account account) {
		
		String dataLine = String.format("%-10s %-10s %-10s %-10d %-10s %-10s %-10.2f %-11s", account.getDepositor().getName().getLastName(),
																							 account.getDepositor().getName().getFirstName(),
																							 account.getDepositor().getSSN(),
																							 account.getAcctNumber(),
																							 account.getAcctType(),
																							 account.getAccountStatus(),
																							 account.getBalance(),
																							 account.getDate());
		
		return dataLine;
	}
	
	public void deletingAccountFile(int index) {
		try {
			RandomAccessFile file = new RandomAccessFile("C:\\Users\\ispek\\eclipse-workspace\\CISC 3115 Zeigler\\src\\HW8\\BankAccounts.dat", "rw");
			file.seek(getDataSize(index));
			file.writeChars(getFile(getNumAccts() - 1));
			file.setLength(getRandomAccessFile().length() - DATA_SIZE);	
			file.close();
		} catch(IOException e) {
			System.out.println("!Error! File not Found");
		} 
	}
	
	public String getFile(long num) {
		
		String dataLine = String.format("%-10s %-10s %-10s %-10d %-10s %-10s %-10.2f %-11s", readTypeOfAccount(num).getDepositor().getName().getLastName(),
																							 readTypeOfAccount(num).getDepositor().getName().getFirstName(),
																							 readTypeOfAccount(num).getDepositor().getSSN(),
																							 readTypeOfAccount(num).getAcctNumber(),
																							 readTypeOfAccount(num).getAcctType(),
																							 readTypeOfAccount(num).getAccountStatus(),
																							 readTypeOfAccount(num).getBalance(),
																							 readTypeOfAccount(num).getDate());	

		return dataLine;
	}
	
	public Account readTypeOfAccount(long num) {
		
		String data = convertBinToText(num);
		Account account;
		String last = data.substring(0, 10);
		String first = data.substring(10, 20);
		Name name = new Name(last.trim(), first.trim());
		String SSN = data.substring(20, 30);
		Depositor depositor = new Depositor(SSN.trim(), name);
		
		String accountNum = data.substring(30, 40);
		String accountType = data.substring(40, 50);
		String status = data.substring(50, 60);
		String balance = data.substring(60, 70);
		String date = data.substring(70, 80);
		
		if(accountType.trim().equals("CD")) {
			account = new CDAccount(Integer.parseInt(accountNum.trim()), accountType.trim(), status.trim(), Double.parseDouble(balance.trim()), date.trim(), depositor);
		}
		else if(accountType.trim().equals("Checking")) {
			account = new CheckingAccount(Integer.parseInt(accountNum.trim()), accountType.trim(), status.trim(), Double.parseDouble(balance.trim()), date.trim(), depositor);
		}
		else {
			account = new SavingsAccount(Integer.parseInt(accountNum.trim()), accountType.trim(), status.trim(), Double.parseDouble(balance.trim()), date.trim(), depositor);
		}
		return account;
	}
	
	public String convertBinToText(long num) {
		String stringOfData;
		String fullData = "";
		char[] array = new char[22];
		try {
			long pos = getDataSize(num);
			for(int i = 0; i < 8; i++) {
				getRandomAccessFile().seek(pos);
				for(int k = 0; k < 11; k++) {
					array[k] = getRandomAccessFile().readChar();
				}
				stringOfData = new String(array);
				stringOfData = String.format("%-10s", stringOfData.trim());
				fullData += stringOfData;
				pos += 22;
			}
		} catch(IOException e) {
			System.out.println("!Error! File DNE");
		}
		
		return fullData;
		
	}
	
	public void CreateTransactionHistoryFile(int acctNum) {
		try {
			RandomAccessFile file = new RandomAccessFile("C:\\Users\\ispek\\eclipse-workspace\\CISC 3115 Zeigler\\src\\HW8\\Account_" + acctNum + ".dat", "rw");
			file.close();
		} catch (IOException e) {
			System.out.println("!Error! Account Binary File Does Not Exist");
		}
	}
	
	public long getAccountTransactionSize(long transactionNum) {
		return ACCOUNT_SIZE * transactionNum;
	}
	
	public String convertAccountFileToText(int num, int acct) {
		String stringOfData;
		String fullData = "";
		
		char[] array = new char[42];
		char[] msgArray = new char[82];
		try {
			RandomAccessFile file = new RandomAccessFile("C:\\Users\\ispek\\eclipse-workspace\\CISC 3115 Zeigler\\src\\HW8\\Account_" + acct + ".dat", "rw");
			long pos = getAccountTransactionSize(num);
			for(int i = 0; i < 5; i++) {
				
				file.seek(pos);
				for(int k = 0; k < 21; k++) {
					array[k] = file.readChar();
				}
				stringOfData = new String(array);
				stringOfData = String.format("%-20s", stringOfData.trim());
				fullData += stringOfData;
				pos += 42;
			}
			for(int j = 0; j < 41; j++) {
				msgArray[j] = file.readChar();
			}
			stringOfData = new String(msgArray);
			stringOfData = String.format("%-40s", stringOfData.trim());
			fullData += stringOfData;
			file.close();
		} catch(IOException e) {
			System.out.println("!Error! Account File DNE");
		}
		
		return fullData;
	}
	
	public TransactionReceipt readAccountFile(int num, int acct) {

		TransactionTicket ticket = new TransactionTicket();
		
		String data = convertAccountFileToText(num, acct);
		
		String date = data.substring(0, 20);
		String transactionType = data.substring(20, 40);
		String amount = data.substring(40, 60);
		String status = data.substring(60, 80);
		String balance = data.substring(80, 100);
		String reason = data.substring(100, 140);
		
		Calendar calendar = Calendar.getInstance();
		calendar.clear();

		String[] strDate = date.split("/");
		calendar.set(Integer.parseInt(strDate[0]), Integer.parseInt(strDate[0]) - 1, Integer.parseInt(strDate[0]));
		
		TransactionReceipt receipt = new TransactionReceipt(0, 0, false, "", transactionType.trim(), Double.parseDouble(balance.trim()), Double.parseDouble(balance.trim()), null, ticket, calendar, "", Double.parseDouble(amount.trim()), status.trim(), reason.trim());
		TransactionReceipt copyReceipt = new TransactionReceipt(receipt);
		return copyReceipt;
		
	}
	
	//getters
	public TransactionReceipt openNewAcct(TransactionTicket newTicket, Account account) {
		
		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		
		boolean Found = false;
		try {
			findAcct(newTicket.getAccountNumber());
			String err = String.format("%s\n %s\n %s%d\n %s%d%s\n\n", " Transaction Type - New Account",
				  	  												  "--------------------------",
				 	  												  "Account Number: ", newTicket.getAccountNumber(),
				  	  												  "!Error! Account Number ", newTicket.getAccountNumber()," Exist in the Database");
			receipt = new TransactionReceipt(newTicket.getAccountNumber(), 0, Found, err, "", 0, 0, null, newTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		} catch (InvalidAccountException e) {
			try {
				if(newTicket.getAmountOfTransaction() < 0) 
					throw new InvalidAmountException();
				else if(account.getAcctNumber() < 0) {
					throw new NegativeAccountNumberException();
				}
				else {
					Found = true;
					Account acct;
					Calendar today = Calendar.getInstance();
					if(account.getAcctType().equals("CD")) {
						acct = new CDAccount(account.getAcctNumber(), account.getAcctType(), account.getAccountStatus(), account.getBalance(), account.getDate(), account.getDepositor());
						receipt = new TransactionReceipt(0, 0, Found, "", "New Account", acct.getBalance(), acct.getBalance(), null, newTicket, today, "", newTicket.getAmountOfTransaction(), "Processed", "");
						copyReceipt = new TransactionReceipt(receipt);
						appendingNewAccountFile(acct);
						acct.writeTransactionHistoryData(copyReceipt, newTicket.getAccountNumber());
						receipt = new TransactionReceipt(newTicket.getAccountNumber(), newTicket.getTermOfCD(), Found, "", acct.getAcctType(), acct.getBalance(), acct.getBalance(), acct.getMatureDate(), newTicket, today, "New Account", newTicket.getAmountOfTransaction(), "Processed", "");
						copyReceipt = new TransactionReceipt(receipt);
					}
					else if(account.getAcctType().equals("Checking")){
						acct = new CheckingAccount(account.getAcctNumber(), account.getAcctType(), account.getAccountStatus(), account.getBalance(), account.getDate(), account.getDepositor());
						receipt = new TransactionReceipt(0, 0, Found, "", "New Account", acct.getBalance(), acct.getBalance(), null, newTicket, today, "", newTicket.getAmountOfTransaction(), "Processed", "");
						copyReceipt = new TransactionReceipt(receipt);
						appendingNewAccountFile(acct);
						acct.writeTransactionHistoryData(copyReceipt, newTicket.getAccountNumber());
						receipt = new TransactionReceipt(newTicket.getAccountNumber(), newTicket.getTermOfCD(), Found, "", acct.getAcctType(), acct.getBalance(), acct.getBalance(), acct.getMatureDate(), newTicket, today, "New Account", newTicket.getAmountOfTransaction(), "Processed", "");
						copyReceipt = new TransactionReceipt(receipt);
					}
					else {
						acct = new SavingsAccount(account.getAcctNumber(), account.getAcctType(), account.getAccountStatus(), account.getBalance(), account.getDate(), account.getDepositor());
						receipt = new TransactionReceipt(0, 0, Found, "", "New Account", acct.getBalance(), acct.getBalance(), null, newTicket, today, "", newTicket.getAmountOfTransaction(), "Processed", "");
						copyReceipt = new TransactionReceipt(receipt);
						appendingNewAccountFile(acct);
						acct.writeTransactionHistoryData(copyReceipt, newTicket.getAccountNumber());
						receipt = new TransactionReceipt(newTicket.getAccountNumber(), newTicket.getTermOfCD(), Found, "", acct.getAcctType(), acct.getBalance(), acct.getBalance(), acct.getMatureDate(), newTicket, today, "New Account", newTicket.getAmountOfTransaction(), "Processed", "");
						copyReceipt = new TransactionReceipt(receipt);
					}
					
				}
			} catch(InvalidAmountException e1) {
				String err = String.format("%s\n %s\n %s%d\n %s%.2f\n %s\n\n", " Transaction Type - New Account",
							   "--------------------------",
							   "Account Number: ", newTicket.getAccountNumber(),
							   "Balance Amount: ", newTicket.getAmountOfTransaction(),
							   "!Error! Balance Amount Entered is Negative");

				e1 = new InvalidAmountException(err);
				receipt = new TransactionReceipt(newTicket.getAccountNumber(), 0, Found, e1.getMessage(), "", 0, 0, null, newTicket, null, null, 0, "", "");
				copyReceipt = new TransactionReceipt(receipt);
			} catch(NegativeAccountNumberException e1) {
				String err = String.format("%s\n %s\n %s%d\n %s%d%s\n\n", " Transaction Type - New Account",
							  "--------------------------",
							  "Account Number: ", newTicket.getAccountNumber(),
							  "!Error! Account Number ", newTicket.getAccountNumber()," is Negative");
				e1 = new NegativeAccountNumberException(err);
				receipt = new TransactionReceipt(newTicket.getAccountNumber(), 0, Found, e1.getMessage(), "", 0, 0, null, newTicket, null, null, 0, "", "");
				copyReceipt = new TransactionReceipt(receipt);
			} 
			
		}
		
		return copyReceipt;
				
	}
	
	public TransactionReceipt getBalance(TransactionTicket balanceTicket) {
		
		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		
		boolean Found = false;
		int found;
		try {
			found = findAcct(balanceTicket.getAccountNumber());
			receipt = readTypeOfAccount(found).getBalance(balanceTicket);
			copyReceipt = new TransactionReceipt(receipt);
		} catch (InvalidAccountException e) {
			String err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Balance",
															  	  "--------------------------",
															  	  "Account Number: ", balanceTicket.getAccountNumber(),
															  	  "!Error! Account Number Does not Exist");
			e = new InvalidAccountException(err);
			receipt = new TransactionReceipt(balanceTicket.getAccountNumber(), 0, Found, e.getMessage(), "", 0, 0, null, balanceTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		} catch (AccountClosedException e) {
			receipt = new TransactionReceipt(balanceTicket.getAccountNumber(), 0, Found, e.getMessage(), "", 0, 0, null, balanceTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		}
		
		return copyReceipt;
		
	}
	
	public TransactionReceipt makeDeposit(TransactionTicket depositTicket) {

		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		
		boolean Found = false;
		try {
			int found = findAcct(depositTicket.getAccountNumber());
			receipt = readTypeOfAccount(found).makeDeposit(depositTicket);
			copyReceipt = new TransactionReceipt(receipt);
		} catch (InvalidAccountException e) {
			String err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Deposit",
				  											   	  "--------------------------",
				  											   	  "Account Number: ", depositTicket.getAccountNumber(),
				  											   	  "!Error! Account Number Does not Exist");
			e = new InvalidAccountException(err);
			receipt = new TransactionReceipt(depositTicket.getAccountNumber(), 0, Found, e.getMessage(), "", 0, 0, null, depositTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		} catch(InvalidAmountException | AccountClosedException | CDMaturityDateException e) {
			receipt = new TransactionReceipt(depositTicket.getAccountNumber(), 0, Found, e.getMessage(), "", 0, 0, null, depositTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		} 
		
		return copyReceipt;
		
	}
	
	public TransactionReceipt makeWithdrawal(TransactionTicket withdrawTicket) {
		
		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		
		boolean Found = false;
		int found;
		try {
			found = findAcct(withdrawTicket.getAccountNumber());
			receipt = readTypeOfAccount(found).makeWithdrawal(withdrawTicket);
			copyReceipt = new TransactionReceipt(receipt);
		} catch (InvalidAccountException e) {
			String err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Withdrawal",
				  											   	  "--------------------------",
				  											   	  "Account Number: ", withdrawTicket.getAccountNumber(),
				  											   	  "!Error! Account Number Does not Exist");
			e = new InvalidAccountException(err);
			receipt = new TransactionReceipt(withdrawTicket.getAccountNumber(), 0, Found, e.getMessage(), "", 0, 0, null, withdrawTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		} catch(InvalidAmountException | AccountClosedException | CDMaturityDateException | InsufficientFundsException e) {
			receipt = new TransactionReceipt(withdrawTicket.getAccountNumber(), 0, Found, e.getMessage(), "", 0, 0, null, withdrawTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		}
		
		return copyReceipt;
		
	}
	
	public TransactionReceipt clearCheck(Check clear) {
		
		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		
		TransactionTicket ticket = new TransactionTicket(clear.getAccount(), clear.getTime(), "", clear.getBalance(), 0);
		TransactionTicket copyTicket = new TransactionTicket(ticket);
		
		boolean Found = false;
		try {
			int found = findAcct(clear.getAccount());
			if(!(readTypeOfAccount(found).getAcctType().equals("Checking"))) {
				throw new InvalidAccountTypeException();
			}
			else {
				Check copyCheck = new Check(clear);
				receipt = readTypeOfAccount(found).clearCheck(copyCheck);
				copyReceipt = new TransactionReceipt(receipt);
			}
		} catch (InvalidAccountException e) {
			String err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Clear Check",
				  											   	  "--------------------------",
				  											   	  "Account Number: ", clear.getAccount(),
				  											   	  "!Error! Account Number Does not Exist");
			e = new InvalidAccountException(err);
			receipt = new TransactionReceipt(clear.getAccount(), 0, Found, e.getMessage(), "", 0, 0, null, copyTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
			
		} catch(InvalidAccountTypeException e) {
			String err = String.format("%s\n %s\n %s%d\n %s%s\n %s\n\n", " Transaction Type - Clear Check",
				  											   	   		 "--------------------------",
				  											   	   		 "Account Number: ", clear.getAccount(),
				  											   	   		 "Account Type: ", e.getMessage(),
				  											   	   		 "!Error! Account is not a Checking Type");
			e = new InvalidAccountTypeException(err);
			receipt = new TransactionReceipt(clear.getAccount(), 0, Found, e.getMessage(), "", 0, 0, null, copyTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		} catch(AccountClosedException | InvalidAmountException | InsufficientFundsException | PostDatedCheckException | CheckTooOldException e) {
			receipt = new TransactionReceipt(clear.getAccount(), 0, Found, e.getMessage(), "", 0, 0, null, copyTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		} 
		
		return copyReceipt;
		
	}
	
	public TransactionReceipt closeAcct(TransactionTicket closeTicket) {
		
		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		
		boolean Found = false;
		int found;
		try {
			found = findAcct(closeTicket.getAccountNumber());
			receipt = readTypeOfAccount(found).closeAcct(closeTicket, readTypeOfAccount(found).getAccountStatus());
			copyReceipt = new TransactionReceipt(receipt);
		} catch (InvalidAccountException e) {
			String err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Close Account",
				  											   	  "--------------------------",
				  											   	  "Account Number: ", closeTicket.getAccountNumber(),
				  											   	  "!Error! Account Number Does not Exist");
			e = new InvalidAccountException(err);
			receipt = new TransactionReceipt(closeTicket.getAccountNumber(), 0, Found, e.getMessage(), "", 0, 0, null, closeTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		} catch(AccountClosedException e) {
			receipt = new TransactionReceipt(closeTicket.getAccountNumber(), 0, Found, e.getMessage(), "", 0, 0, null, closeTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		}
		
		return copyReceipt;
		
	}
	
	public TransactionReceipt reopenAcct(TransactionTicket reopenTicket) {
		
		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		
		boolean Found = false;
		int found;
		try {
			found = findAcct(reopenTicket.getAccountNumber());			
			receipt = readTypeOfAccount(found).reopenAcct(reopenTicket, readTypeOfAccount(found).getAccountStatus());
			copyReceipt = new TransactionReceipt(receipt);
		} catch (InvalidAccountException e) {
			String err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Reopen Account",
				  											   	   "--------------------------",
				  											   	   "Account Number: ", reopenTicket.getAccountNumber(),
				  											   	   "!Error! Account Number Does not Exist");
			e = new InvalidAccountException(err);
			receipt = new TransactionReceipt(reopenTicket.getAccountNumber(), 0, Found, e.getMessage(), "", 0, 0, null, reopenTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		} catch(AccountClosedException e) {
			receipt = new TransactionReceipt(reopenTicket.getAccountNumber(), 0, Found, e.getMessage(), "", 0, 0, null, reopenTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		}
		
		return copyReceipt;
		
	}
	
	public TransactionReceipt deleteAcct(TransactionTicket deleteTicket) {
		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		
		boolean Found = false;
		int found;
		try {
			found = findAcct(deleteTicket.getAccountNumber());
			if(readTypeOfAccount(found).getBalance() != 0) {
				throw new InvalidAmountException();
			}
			else {
				Found = true;
				deletingAccountFile(found);
				receipt = new TransactionReceipt(deleteTicket.getAccountNumber(), 0, Found, "", "", 0, 0, null, deleteTicket, null, "Delete Account", 0, "", "");
				copyReceipt = new TransactionReceipt(receipt);
			}
		} catch (InvalidAccountException e) {
			String err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Delete Account",
				   	   											  "--------------------------",
				   	   											  "Account Number: ", deleteTicket.getAccountNumber(),
				   	   											  "!Error! Account Number Does not Exist");
			e = new InvalidAccountException(err);
			receipt = new TransactionReceipt(deleteTicket.getAccountNumber(), 0, Found, e.getMessage(), "", 0, 0, null, deleteTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		} catch (InvalidAmountException ee) {
			String err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Delete Account",
						  										  	  "--------------------------",
						  										  	  "Account Number: ", deleteTicket.getAccountNumber(),
						  										  	  "!Error! Account Contains Balance");
			ee = new InvalidAmountException(err);
			receipt = new TransactionReceipt(deleteTicket.getAccountNumber(), 0, Found, ee.getMessage(), "", 0, 0, null, deleteTicket, null, null, 0, "", "");
			copyReceipt = new TransactionReceipt(receipt);
		}
		
		return copyReceipt;
		
	}
	
	//static methods
	public static void totalAmountInSavingsAccts(Bank bank) {
		
		double sum = 0;
		
		for(int i = 0; i < bank.getNumAccts(); i++) 
			if(bank.readTypeOfAccount(i).getAcctType().equals("Savings")) 
				sum += bank.readTypeOfAccount(i).getBalance();
		
		Bank.totalSavings = sum;
		
	}
	
	public double getAmountInSavingsAccts() {
		return totalSavings;
	}
	
	public static void totalAmountInCheckingAccts(Bank bank) {
		 
		double sum = 0;
		
		for(int i = 0; i < bank.getNumAccts(); i++)
			if(bank.readTypeOfAccount(i).getAcctType().equals("Checking"))
				sum += bank.readTypeOfAccount(i).getBalance();
	
		Bank.totalChecking = sum;
		
	}
	
	public double getAmountInCheckingAccts() {
		return Bank.totalChecking;
	}
	
	public static void totalAmountInCDAccts(Bank bank) {
		
		double sum = 0;
		
		for(int i = 0; i < bank.getNumAccts(); i++)
			if(bank.readTypeOfAccount(i).getAcctType().equals("CD"))
				sum += bank.readTypeOfAccount(i).getBalance();
		
		Bank.totalCD = sum;
		
	}
	
	public double getAmountInCDAccts() {
		return Bank.totalCD;
	}
	
	public static double totalAmountInAllAccts(Bank bank) {
		
		double sum = 0;
		
		for(int i = 0; i < bank.getNumAccts(); i++)
			sum += bank.readTypeOfAccount(i).getBalance();
		
		Bank.totalAll = sum;
		
		return Bank.totalAll;
		
	}
	
	public double getAmountInAllAccts() {
		return Bank.totalAll;
	}
	
	public static void addAmount(Bank addBank, TransactionTicket ticket) {
		
		for(int i = 0; i < addBank.getNumAccts(); i++) {
			if(addBank.readTypeOfAccount(i).getAcctType().equals("Savings") && addBank.readTypeOfAccount(i).getAcctNumber() == ticket.getAccountNumber())
				Bank.totalSavings += ticket.getAmountOfTransaction();
			else if(addBank.readTypeOfAccount(i).getAcctType().equals("Checking") && addBank.readTypeOfAccount(i).getAcctNumber() == ticket.getAccountNumber())
				Bank.totalChecking += ticket.getAmountOfTransaction();
			else if(addBank.readTypeOfAccount(i).getAcctType().equals("CD") && addBank.readTypeOfAccount(i).getAcctNumber() == ticket.getAccountNumber())
				Bank.totalCD += ticket.getAmountOfTransaction();
		}
			
		Bank.totalAll += ticket.getAmountOfTransaction();
		
	}
	
	public static void subAmount(Bank subBank, TransactionTicket ticket) {
		
		for(int i = 0; i < subBank.getNumAccts(); i++) {
			if(subBank.readTypeOfAccount(i).getAcctType().equals("Savings") && subBank.readTypeOfAccount(i).getAcctNumber() == ticket.getAccountNumber())
				Bank.totalSavings -= ticket.getAmountOfTransaction();
			else if(subBank.readTypeOfAccount(i).getAcctType().equals("Checking") && subBank.readTypeOfAccount(i).getAcctNumber() == ticket.getAccountNumber())
				Bank.totalChecking -= ticket.getAmountOfTransaction();
			else if(subBank.readTypeOfAccount(i).getAcctType().equals("CD") && subBank.readTypeOfAccount(i).getAcctNumber() == ticket.getAccountNumber())
				Bank.totalCD -= ticket.getAmountOfTransaction();
		}
		
		Bank.totalAll -= ticket.getAmountOfTransaction();
		
	}
	
	//overloading method; for checking only
	public static void subAmount(Bank subBank, Check check) {
		
		for(int i = 0; i < subBank.getNumAccts(); i++)
			if(subBank.readTypeOfAccount(i).getAcctType().equals("Checking") && subBank.readTypeOfAccount(i).getAcctNumber() == check.getAccount())
				Bank.totalChecking -= check.getBalance();
			
		Bank.totalAll -= check.getBalance();
	
	}
	
	//overloading method; bouncing a check
	public static void subAmount() {
			Bank.totalChecking -= 2.5;
			Bank.totalAll -= 2.5;
	}
	
	private int findAcct(int search) throws InvalidAccountException{
		
		for(int i = 0; i < getNumAccts(); i++)
			if(readTypeOfAccount(i).getAcctNumber() == search)
				return i;
		
		throw new InvalidAccountException();
		
	}
	
	public long getNumAccts() {

		try {
			return (file.length() / DATA_SIZE);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		
	}

}

