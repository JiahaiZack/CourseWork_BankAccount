package HW8;

import java.util.*;

public class CDAccount extends SavingsAccount{

	private Calendar date;

	//no-arg Constructor
	public CDAccount() {
		super();
		date = null;
	}

	//parameterized Constructor 
	public CDAccount(int num, String type, String status, double bal, String date, Depositor deposit) {
		super(num, type, status, bal, date, deposit);
		setDate(date);
	}

	//copy Constructor
	public CDAccount(CDAccount CD) {
		super(CD.getAcctNumber(), CD.getAcctType(), CD.getAccountStatus(), CD.getBalance(), CD.getDate(), CD.getDepositor());
		date = CD.getMatureDate();
	}

	//setter
	private void setDate(String date) {
		Calendar time = Calendar.getInstance();
		time.clear();
		String[] token = date.split("/");
		time.set(Integer.parseInt(token[2]),
				Integer.parseInt(token[0]) - 1,
				Integer.parseInt(token[1]));

		this.date = time;
	}

	public TransactionReceipt makeDeposit(TransactionTicket depositTicket) throws InvalidAmountException, AccountClosedException, CDMaturityDateException{

		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		String err;
		boolean Found = false;

		Calendar current = Calendar.getInstance();
		Calendar today = Calendar.getInstance();

		if(super.getAccountStatus().equals("Closed")){
			receipt = new TransactionReceipt(0, 0, Found, "", "Deposit", super.getBalance() , super.getBalance(), null, depositTicket, today, "", depositTicket.getAmountOfTransaction(), "Failed", "Account is Closed");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, depositTicket.getAccountNumber());
			err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Deposit",
					   									   "--------------------------",
					   									   "Account Number: ", depositTicket.getAccountNumber(),
					   									   "!Error! Account Status: Closed");
			throw new AccountClosedException(err);
		}
		else if(depositTicket.getAmountOfTransaction() < 0) {	
			receipt = new TransactionReceipt(0, 0, Found, "", "Deposit", super.getBalance(), super.getBalance(), null, depositTicket, today, "", depositTicket.getAmountOfTransaction(), "Failed", "$" + depositTicket.getAmountOfTransaction() + " is an Invalid Amount");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, depositTicket.getAccountNumber());
			err = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s%.2f%s\n\n", " Transaction Type - Deposit",
				   		 "--------------------------",
				   		 "Account Number: ", depositTicket.getAccountNumber(),
				   		 "Account Type: ", super.getAcctType(),
				   		 "Balance: ", super.getBalance(),
				   		 "Deposit Amount: ", depositTicket.getAmountOfTransaction(),
				   		 "!Error! ", depositTicket.getAmountOfTransaction(), " is an Invalid Deposit Amount");
			throw new InvalidAmountException(err);
		}
		else if(date.after(current)){
			String strDate = String.format("%02d/%02d/%4d", date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.YEAR));
			err = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s%s\n %s%s%s\n\n", " Transaction Type - Deposit", 
					"--------------------------",
					"Account Number: ", super.getAcctNumber(),
					"Account Type: ", super.getAcctType(),
					"Balance: ", super.getBalance(),
					"Deposit Amount: ", depositTicket.getAmountOfTransaction(),
					"CD Maturity Date: ", strDate,
					"!Error! CD Maturity Date ", strDate, " not reached");
			receipt = new TransactionReceipt(0, 0, Found, "", "Deposit", super.getBalance(), super.getBalance(), null, depositTicket, today, "", depositTicket.getAmountOfTransaction(), "Failed", "CD Maturity Date " + strDate + " not reached");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, depositTicket.getAccountNumber());
			throw new CDMaturityDateException(err);
		}
		else {
			Found = true;

			double pre = super.getBalance();
			double post = super.getBalance() + depositTicket.getAmountOfTransaction();
			Calendar date = Calendar.getInstance();
			date.add(Calendar.MONTH, depositTicket.getTermOfCD());
			String time = String.format("%02d/%02d/%4d", date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.YEAR));
			receipt = new TransactionReceipt(0, 0, Found, "", "Deposit", pre, pre, null, depositTicket, today, "", depositTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, depositTicket.getAccountNumber());
			super.setBalance(post);
			super.setTime(time);
			receipt = new TransactionReceipt(depositTicket.getAccountNumber(), depositTicket.getTermOfCD(), Found, "", super.getAcctType(), pre, post, date, depositTicket, today, "Deposit", depositTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
		}

		return receipt;

	}

	public TransactionReceipt makeWithdrawal(TransactionTicket withdrawTicket) throws InvalidAmountException, AccountClosedException, CDMaturityDateException, InsufficientFundsException  { 

		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		String err;
		boolean Found = false;

		Calendar current = Calendar.getInstance();

		if(super.getAcctType().equals("Closed")){
			Calendar today = Calendar.getInstance();
			err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Withdrawal",
					"-----------------------------",
					"Account Number: ", super.getAcctNumber(),
					"!Error! Account status: Closed");
			receipt = new TransactionReceipt(0, 0, Found, "", "Withdrawal", super.getBalance(), super.getBalance(), null, withdrawTicket, today, "", withdrawTicket.getAmountOfTransaction(), "Failed", "Account is Closed");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, withdrawTicket.getAccountNumber());
			throw new AccountClosedException(err);
		}
		else if(withdrawTicket.getAmountOfTransaction() < 0) {
			err = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s\n\n", " Transaction Type - Withdrawal", 
					"-----------------------------",
					"Account Number:", super.getAcctNumber(),
					"Account Type:", super.getAcctType(),
					"Balance:", super.getBalance(),
					"Withdraw Amount: ", withdrawTicket.getAmountOfTransaction(),
					"!Error! Withdraw Amount is Negative");
			Calendar today = Calendar.getInstance();
			receipt = new TransactionReceipt(0, 0, Found, "", "Withdrawal", super.getBalance(), super.getBalance(), null, withdrawTicket, today, "", withdrawTicket.getAmountOfTransaction(), "Failed", "$" + withdrawTicket.getAmountOfTransaction() + " is an Invalid Amount");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, withdrawTicket.getAccountNumber());
			throw new InvalidAmountException(err);
		}
		else if(withdrawTicket.getAmountOfTransaction() > super.getBalance()) {
			err = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s\n\n", " Transaction Type - Withdrawal", 
					"-----------------------------",
					"Account Number:", super.getAcctNumber(),
					"Account Type:", super.getAcctType(),
					"Balance:", super.getBalance(),
					"Withdraw Amount: ", withdrawTicket.getAmountOfTransaction(),
					"!Error! Insufficient Funds to Withdraw");
			Calendar today = Calendar.getInstance();
			receipt = new TransactionReceipt(0, 0, Found, "", "Withdrawal", super.getBalance(), super.getBalance(), null, withdrawTicket, today, "", withdrawTicket.getAmountOfTransaction(), "Failed", "Insufficient Funds");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, withdrawTicket.getAccountNumber());
			throw new InsufficientFundsException(err);
		}
		else if(date.after(current)){
			String strDate = String.format("%02d/%02d/%4d", date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.YEAR));
			err = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s%s\n %s%s%s\n\n", " Transaction Type - Withdrawal", 
					"-----------------------------",
					"Account Number: ", super.getAcctNumber(),
					"Account Type: ", super.getAcctType(),
					"Balance: ", super.getBalance(),
					"Withdraw Amount: ", withdrawTicket.getAmountOfTransaction(),
					"CD Maturity Date: ", strDate,
					"!Error! CD Maturity Date ", strDate, " not reached");
			Calendar today = Calendar.getInstance();
			receipt = new TransactionReceipt(0, 0, Found, "", "Withdrawal", super.getBalance(), super.getBalance(), null, withdrawTicket, today, "", withdrawTicket.getAmountOfTransaction(), "Failed", "CD Maturity Date " + strDate + " not reached");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, withdrawTicket.getAccountNumber());
			throw new CDMaturityDateException(err);
		}
		else {
			Found = true;
			
			double pre = super.getBalance();
			double post = super.getBalance() - withdrawTicket.getAmountOfTransaction();

			Calendar today = Calendar.getInstance();
			Calendar date = Calendar.getInstance();
			date.add(Calendar.MONTH, withdrawTicket.getTermOfCD());
			String time = String.format("%02d/%02d/%4d", date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.YEAR));

			receipt = new TransactionReceipt(0, 0, Found, "", "Withdrawal", pre, pre, null, withdrawTicket, today, "", withdrawTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, withdrawTicket.getAccountNumber());
			super.setBalance(post);
			super.setTime(time);
			receipt = new TransactionReceipt(withdrawTicket.getAccountNumber(), withdrawTicket.getTermOfCD(), Found, "", super.getAcctType(), pre, post, date, withdrawTicket, today, "Withdrawal", withdrawTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
		}

		return receipt;
	}

	//getter
	public Calendar getMatureDate() {
		return date;
	}

	public String toString() {

		String strDate = String.format("%02d/%02d/%4d", date.get(Calendar.MONTH) + 1, date.get(Calendar.DAY_OF_MONTH), date.get(Calendar.YEAR));		
		String str = String.format("%s%16s\n", super.toString(), strDate);

		return str;

	}

}
