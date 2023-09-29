package HW8;

import java.util.Calendar;

public class CheckingAccount extends Account{

	//no-arg Constructor
	public CheckingAccount() {
		super();
	}

	//parameterized Constructor
	public CheckingAccount(int num, String type, String status, double bal, String date, Depositor deposit) {
		super(num, type, status, bal, date, deposit);
	}

	//copy Constructor
	public CheckingAccount(CheckingAccount check) {
		super(check.getAcctNumber(), check.getAcctType(), check.getAccountStatus(), check.getBalance(), check.getDate(), check.getDepositor());
	}

	public TransactionReceipt clearCheck(Check check) throws InvalidAmountException, AccountClosedException, InsufficientFundsException, PostDatedCheckException, CheckTooOldException{

		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		String err;
		Boolean Found = false;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -6);
		Calendar today = Calendar.getInstance();

		TransactionTicket ticket = new TransactionTicket(check.getAccount(), check.getTime(), "", check.getBalance(), 0);
		TransactionTicket copyTicket = new TransactionTicket(ticket);

		if(super.getAccountStatus().equals("Closed")){
			err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Clear Check",
					"------------------------------",
					"Account Number: ", super.getAcctNumber(),
					"!Error! Account status: Closed");
			receipt = new TransactionReceipt(0, 0, Found, "", "Clear Check", super.getBalance(), super.getBalance(), null, copyTicket, today, "", check.getBalance(), "Failed", "Account is Closed");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, check.getAccount());
			throw new AccountClosedException(err);
		}

		else if(check.getBalance() < 0) {
			err = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s\n\n", " Transaction Type - Clear Check", 
					"------------------------------",
					"Account Number: ", super.getAcctNumber(),
					"Account Type: ", super.getAcctType(),
					"Balance: ", super.getBalance(),
					"Check Amount: ", check.getBalance(),
					"!Error! Withdraw Amount is Negative");
			receipt = new TransactionReceipt(0, 0, Found, "", "Clear Check", super.getBalance(), super.getBalance(), null, copyTicket, today, "", check.getBalance(), "Failed", "$" + check.getBalance() + " is an Invalid Amount");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, check.getAccount());
			throw new InvalidAmountException(err);
		}
		else if(check.getBalance() > super.getBalance()) {
			double bounceFee = super.getBalance() - 2.5;
			err = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s\n %s%.2f\n\n", " Transaction Type - Clear Check", 
					"------------------------------",
					"Account Number: ", super.getAcctNumber(),
					"Account Type: ", super.getAcctType(),
					"Balance: ", super.getBalance(),
					"Check Amount: ", check.getBalance(),
					"!Error! Insufficient Funds to Withdraw - Bounce Fee ($2.50) Charged",
					"New Balance: ", bounceFee);
			Bank.subAmount();
			super.setBalance(bounceFee);
			receipt = new TransactionReceipt(0, 0, Found, "", "Clear Check", super.getBalance(), super.getBalance(), null, copyTicket, today, "", check.getBalance(), "Failed", "Insufficient Funds");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, check.getAccount());
			throw new InsufficientFundsException(err);
		}
		else if(check.getTime().after(today)) {
			err = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s\n %s\n\n", " Transaction Type - Clear Check", 
					"------------------------------",
					"Account Number: ", super.getAcctNumber(),
					"Account Type: ", super.getAcctType(),
					"Balance: ", super.getBalance(),
					check.toString(),
					"!Error! Check is a Post-dated Check");
			receipt = new TransactionReceipt(0, 0, Found, "", "Clear Check", super.getBalance(), super.getBalance(), null, copyTicket, today, "", check.getBalance(), "Failed", "Post-date Check");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, check.getAccount());
			throw new PostDatedCheckException(err);
		}
		else if(check.getTime().before(calendar)) {
			err = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s\n %s\n\n", " Transaction Type - Clear Check", 
					"------------------------------",
					"Account Number: ", super.getAcctNumber(),
					"Account Type: ", super.getAcctType(),
					"Balance: ", super.getBalance(),
					check.toString(),
					"!Error! Date of Check has Expired; Pass due 6 Months ago or Longer");
			receipt = new TransactionReceipt(0, 0, Found, "", "Clear Check", super.getBalance(), super.getBalance(), null, copyTicket, today, "", check.getBalance(), "Failed", "Expired Check");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, check.getAccount());
			throw new CheckTooOldException(err);
		}
		else {
			Found = true;
			double pre = super.getBalance();
			double post = super.getBalance() - check.getBalance();

			if(pre < 2500)
				post -= 1.5;

			receipt = new TransactionReceipt(0, 0, Found, "", "Clear Check", pre, pre, null, copyTicket, today, "", check.getBalance(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, check.getAccount());
			super.setBalance(post);
			receipt = new TransactionReceipt(check.getAccount(), 0, Found, "", super.getAcctType(), pre, post, null, copyTicket, today, "Clear Check", check.getBalance(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
		}

		return copyReceipt;

	}

	public TransactionReceipt makeDeposit(TransactionTicket depositTicket) throws InvalidAmountException, AccountClosedException{

		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		boolean Found = false;
		String err;
		Calendar today = Calendar.getInstance();
		if(super.getAccountStatus().equals("Closed")){
			receipt = new TransactionReceipt(0, 0, Found, "", "Deposit", super.getBalance(), super.getBalance(), null, depositTicket, today, "", depositTicket.getAmountOfTransaction(), "Failed", "Account is Closed");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, depositTicket.getAccountNumber());
			err = String.format("%s\n %s\n %s%d\n %s\n\n", " Transaction Type - Deposit",
					   "--------------------------",
					   "Account Number: ", depositTicket.getAccountNumber(),
					   "!Error! Account Status: Closed");
			throw new AccountClosedException();
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
		else {
			Found = true;
			double pre = super.getBalance();
			double post = super.getBalance() + depositTicket.getAmountOfTransaction();
			receipt = new TransactionReceipt(0, 0, Found, "", "Deposit", pre, pre, null, depositTicket, today, "", depositTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, depositTicket.getAccountNumber());
			super.setBalance(post);
			receipt = new TransactionReceipt(depositTicket.getAccountNumber(), depositTicket.getTermOfCD(), Found, "", super.getAcctType(), pre, post, null, depositTicket, today, "Deposit", depositTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
		}

		return receipt;

	}

	public TransactionReceipt makeWithdrawal(TransactionTicket withdrawTicket) throws InvalidAmountException, AccountClosedException, InsufficientFundsException{ 

		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		String err;
		boolean Found = false;

		if(super.getAccountStatus().equals("Closed")){
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
			Calendar today = Calendar.getInstance();
			err = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s\n\n", " Transaction Type - Withdrawal", 
					"-----------------------------",
					"Account Number: ", super.getAcctNumber(),
					"Account Type: ", super.getAcctType(),
					"Balance: ", super.getBalance(),
					"Withdraw Amount: ", withdrawTicket.getAmountOfTransaction(),
					"!Error! Withdraw Amount is Negative");
			receipt = new TransactionReceipt(0, 0, Found, "", "Withdrawal", super.getBalance(), super.getBalance(), null, withdrawTicket, today, "", withdrawTicket.getAmountOfTransaction(), "Failed", "$" + withdrawTicket.getAmountOfTransaction() + " is an Invalid Amount");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, withdrawTicket.getAccountNumber());
			throw new InvalidAmountException(err);
		}
		else if(withdrawTicket.getAmountOfTransaction() > super.getBalance()) {
			err = String.format("%s\n %s\n %s%d\n %s%s\n %s%.2f\n %s%.2f\n %s\n\n", " Transaction Type - Withdrawal", 
					"-----------------------------",
					"Account Number: ", super.getAcctNumber(),
					"Account Type: ", super.getAcctType(),
					"Balance: ", super.getBalance(),
					"Withdraw Amount: ", withdrawTicket.getAmountOfTransaction(),
					"!Error! Insufficient Funds to Withdraw");
			Calendar today = Calendar.getInstance();
			receipt = new TransactionReceipt(0, 0, Found, "", "Withdrawal", super.getBalance(), super.getBalance(), null, withdrawTicket, today, "", withdrawTicket.getAmountOfTransaction(), "Failed", "Insufficient Funds");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, withdrawTicket.getAccountNumber());
			throw new InsufficientFundsException(err);
		}
		else {
			Found = true;
			Calendar today = Calendar.getInstance();

			double pre = super.getBalance();
			double post = super.getBalance() - withdrawTicket.getAmountOfTransaction();

			if(pre < 2500)
				post -= 1.5;

			receipt = new TransactionReceipt(0, 0, Found, "", "Withdrawal", pre, pre, null, withdrawTicket, today, "", withdrawTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, withdrawTicket.getAccountNumber());
			super.setBalance(post);
			receipt = new TransactionReceipt(withdrawTicket.getAccountNumber(), withdrawTicket.getTermOfCD(), Found, null, super.getAcctType(), pre, post, null, withdrawTicket, today, "Withdrawal", withdrawTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
		}

		return receipt;

	}

	public String toString() {
		String str = String.format("%s\n", super.toString());
		return str;
	}

}
