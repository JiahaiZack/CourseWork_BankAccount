package HW8;

import java.util.Calendar;

public class SavingsAccount extends Account{

	//no-arg Constructor
	public SavingsAccount() {
		super();
	}

	//parameterized Constructor
	public SavingsAccount(int num, String type, String status, double bal, String date, Depositor deposit) {
		super(num, type, status, bal, date, deposit);	
	}
	//copy Constructor
	public SavingsAccount(SavingsAccount account) {
		super(account.getAcctNumber(), account.getAcctType(), account.getAccountStatus(), account.getBalance(), account.getDate(), account.getDepositor());
	}

	public TransactionReceipt makeDeposit(TransactionTicket depositTicket) throws InvalidAmountException, AccountClosedException, CDMaturityDateException {

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
		else {
			double pre = super.getBalance();
			double post = super.getBalance() + depositTicket.getAmountOfTransaction();
			receipt = new TransactionReceipt(0, 0, Found, "", "Deposit", pre, pre, null, depositTicket, today, "", depositTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, depositTicket.getAccountNumber());
			super.setBalance(post);
			receipt = new TransactionReceipt(depositTicket.getAccountNumber(), depositTicket.getTermOfCD(), Found, "", super.getAcctType(), pre, post, null, depositTicket, today, "Deposit", depositTicket.getAmountOfTransaction(), "Processed", "");
			copyReceipt = new TransactionReceipt(receipt);
		}

		return copyReceipt;

	}

	public TransactionReceipt makeWithdrawal(TransactionTicket withdrawTicket) throws InvalidAmountException, AccountClosedException, CDMaturityDateException, InsufficientFundsException { 

		TransactionReceipt receipt;
		TransactionReceipt copyReceipt;
		String err;
		boolean Found = false;
		Calendar today = Calendar.getInstance();

		if(super.getAccountStatus().equals("Closed")){
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
			receipt = new TransactionReceipt(0, 0, Found, "", "Withdrawal", super.getBalance(), super.getBalance(), null, withdrawTicket, today, "", withdrawTicket.getAmountOfTransaction(), "Failed", "Insufficient Funds");
			copyReceipt = new TransactionReceipt(receipt);
			super.writeTransactionHistoryData(copyReceipt, withdrawTicket.getAccountNumber());
			throw new InsufficientFundsException(err);
		}
		else {
			Found = true;
			double pre = super.getBalance();
			double post = super.getBalance() - withdrawTicket.getAmountOfTransaction();
			
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
		String str;
		if(super.getAcctType().equals("CD")) {
			str = super.toString();
		}
		else
			str = String.format("%s\n", super.toString());

		return str;
	}

}
