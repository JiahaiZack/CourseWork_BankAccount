package HW8;

import java.util.*;
import java.io.*;

public class HW8 {

	/*	Method main
	 *	Input:
	 *		none
	 *
	 *	Process:
	 *		Prompts the user to enter the selection choice to access transaction type
	 *
	 *	Output:
	 * 		Prints out what the method contains in which the user has prompted the switch cases
	 */
	public static void main(String[] args) {
		
		try {
			//create an Bank Object; instantiating an array in the Constructor
			Bank bank = new Bank();
			//create I/O objects
			PrintWriter outputFile = new PrintWriter("C:\\Users\\ispek\\eclipse-workspace\\CISC 3115 Zeigler\\src\\HW8\\outputFile.txt");
			File myFiles = new File("C:\\Users\\ispek\\eclipse-workspace\\CISC 3115 Zeigler\\src\\HW8\\testCases.txt");
			Scanner inputFile = new Scanner(myFiles);
			
			char choice;
			boolean notDone = true;
			
			//call in method readAccts() to read in data from initial File
			readAccts(bank);
			
			//initialize the sum of balances in each and all types of account types
			Bank.totalAmountInSavingsAccts(bank);
			Bank.totalAmountInCheckingAccts(bank);
			Bank.totalAmountInCDAccts(bank);
			Bank.totalAmountInAllAccts(bank);
			
			//print out the initial accounts into the output file
			printAccts(bank, outputFile);
			
			do {
				
				//call in method menu() to prompt a menu in the console
				menu();
				
				//prompts the user
				choice = inputFile.next().charAt(0);
				try {
				//selections the user can enter
				switch(choice) {
				
					//Ends the do While loop
					case 'Q':
					case 'q':
						notDone = false;
						break;
						
					//Calls method withdrawal()
					case 'W':
					case 'w':
						withdrawal(bank, inputFile, outputFile);
						break;
						
					//Calls method deposit()
					case 'D':
					case 'd':
						deposit(bank, inputFile, outputFile);
						break;
						
					//Calls method clearCheck()
					case 'C':
					case 'c':
						clearCheck(bank, inputFile, outputFile);
						break;
						
					//Calls method newAcct()	
					case 'N':
					case 'n':
						newAcct(bank, inputFile, outputFile);
						break;
						
					//Calls method balance()
					case 'B':
					case 'b':
						balance(bank, inputFile, outputFile);
						break;
						
					//Calls method acctInfo()
					case 'I':
					case 'i':
						acctInfo(bank, inputFile, outputFile);
						break;
						
					//Calls method acctInfoHistory()
					case 'H':
					case 'h':
						acctInfoHistory(bank, inputFile, outputFile);
						break;
						
					//Calls method closeAcct()
					case 'S':
					case 's':
						closeAcct(bank, inputFile, outputFile);
						break;
					
					//Calls method reopenAcct() 
					case 'R':
					case 'r':
						reopenAcct(bank, inputFile, outputFile);
						break;
						
					//calls method deleteAcct()
					case 'X':
					case 'x':
						deleteAcct(bank, inputFile, outputFile);
						break;
						
					//Any other selections the user made beside the Cases are set to default
					//Prints out an error message if the user enters a default selection
					default:
						throw new InvalidMenuSelectionException(choice);
					}
				
				}catch(InvalidMenuSelectionException e) {
					System.out.println(e.getMessage());
				}
				
			}while(notDone);

			//Print out the final account database into the output file
			printAccts(bank, outputFile);
			
			//buffer the output file
			outputFile.flush();
			
			//close the I/O files
			outputFile.close();
			inputFile.close();
			
		}
		catch(IOException e) {
			System.out.println("!Error! File(s) Not Found");
		}
		
	}
	/*	Method readAccts
	 *	Input:
	 *		Bank bank - reference to an Bank Class
	 *
	 *	Process:
	 *		reads in from the init file and adds the accounts into the array in the Bank Class
	 *
	 *	Output:
	 * 		None
	 */
	public static void readAccts(Bank bank) throws IOException {
		
		String line;	
		File myFile;
		Scanner initFile;
		
		Account account;
		
		try {
			myFile = new File("C:\\Users\\ispek\\eclipse-workspace\\CISC 3115 Zeigler\\src\\HW8\\initAccts.txt");
			initFile = new Scanner(myFile);
			try {
				while(initFile.hasNext()) {
				
					line = initFile.nextLine();
					String[] token = line.split(" ");

					Name myName = new Name(token[0],
									   token[1]);
					Depositor myDepositor = new Depositor(token[2],
				                           			  myName);
				
					if(token[4].equals("CD")) {
						account = new CDAccount(Integer.parseInt(token[3]),
						    					 token[4],
						    					 token[5],
						    					 Double.parseDouble(token[6]),
						    					 token[7],
						    					 myDepositor);
					}
					else if(token[4].equals("Checking")){
						account = new CheckingAccount(Integer.parseInt(token[3]),
																token[4],
																token[5],
																Double.parseDouble(token[6]),
																"",
																myDepositor);
					}
					else {
						account = new SavingsAccount(Integer.parseInt(token[3]),
															   token[4],
															   token[5],
															   Double.parseDouble(token[6]),
															   "",
															   myDepositor);
					}
					
					bank.getRandomAccessFile().writeChars(bank.writeInitialAccountsToFile(account));
					bank.CreateTransactionHistoryFile(account.getAcctNumber());
				
				}
				
			} catch(InputMismatchException e) {
				System.out.println("!Error! Inputted Data is Invalid");
			} finally {
				initFile.close();
			}
			
		}
		catch(FileNotFoundException e) {
			System.out.println("!Error! Initial Input File not Found");
		}
		
	}
	/*	Method printAccts
	 *	Input:
	 *		Bank printBank - reference to a Bank Class
	 *		PrintWriter printMyOut - prints out to output file
	 *
	 *	Process:
	 *		Prints out the database of accounts
	 *
	 *	Output:
	 * 		Prints out the database of accounts
	 * 
	 */
	public static void printAccts(Bank printBank, PrintWriter printMyOut) {
		
		Account account;
		
		printMyOut.println();
		printMyOut.printf("%9s %15s %14s %18s %16s %15s %15s %15s\n", "Last Name", "First Name", "SSN", "Account Number", "Account Type", "Account Status", "Balance", "Maturity Date");
		printMyOut.println("----------------------------------------------------------------------------------------------------------------------------");
		
		for(int i = 0; i < printBank.getNumAccts(); i++) {
			if(printBank.readTypeOfAccount(i).getAcctType().equals("CD")) {
				account = new CDAccount(printBank.readTypeOfAccount(i).getAcctNumber(), printBank.readTypeOfAccount(i).getAcctType(), printBank.readTypeOfAccount(i).getAccountStatus(), printBank.readTypeOfAccount(i).getBalance(), printBank.readTypeOfAccount(i).getDate(), printBank.readTypeOfAccount(i).getDepositor());
				printMyOut.print(account.toString());
			}
			else if(printBank.readTypeOfAccount(i).getAcctType().equals("Checking")) {
				account = new CheckingAccount(printBank.readTypeOfAccount(i).getAcctNumber(), printBank.readTypeOfAccount(i).getAcctType(), printBank.readTypeOfAccount(i).getAccountStatus(), printBank.readTypeOfAccount(i).getBalance(), printBank.readTypeOfAccount(i).getDate(), printBank.readTypeOfAccount(i).getDepositor());
				printMyOut.print(account.toString());
			}
			else {
				account = new SavingsAccount(printBank.readTypeOfAccount(i).getAcctNumber(), printBank.readTypeOfAccount(i).getAcctType(), printBank.readTypeOfAccount(i).getAccountStatus(), printBank.readTypeOfAccount(i).getBalance(), printBank.readTypeOfAccount(i).getDate(), printBank.readTypeOfAccount(i).getDepositor());
				printMyOut.print(account.toString());
			}

		}
		
		
		printMyOut.println();
		printMyOut.printf("%s%.2f\n", " Sum of All Savings Account Balance: ", printBank.getAmountInSavingsAccts());
		printMyOut.printf("%s%.2f\n", " Sum of All Checking Account Balance: ", printBank.getAmountInCheckingAccts());
		printMyOut.printf("%s%.2f\n", " Sum of All CD Account Balance: ", printBank.getAmountInCDAccts());
		printMyOut.printf("%s%.2f\n", " Sum of All Account Balance: ", printBank.getAmountInAllAccts());
		
		printMyOut.println();
		
		printMyOut.flush();
		
	}
	/*	Method menu
	 *	Input:
	 *		none
	 *
	 *	Process:
	 *		Prints out to console
	 *
	 *	Output:
	 * 		Prints out to console
	 */
	public static void menu() {
		
		System.out.println("Select one of the following:");
		System.out.println("----------------------------");
		System.out.println("(W) - Withdrawal");
		System.out.println("(D) - Deposit");
		System.out.println("(C) - Clear Check");
		System.out.println("(N) - New Account");
		System.out.println("(B) - Balance");
		System.out.println("(I) - Account Info");
		System.out.println("(X) - Delete Account");
		System.out.println("(Q) - Quit");
		System.out.println();
		
	}
	
	/*	Method balance
	 *	Input:
	 *		Bank bankBalance - reference to a Bank Class
	 *		Scanner balanceMyIn - reads in from input file
	 *		PrintWriter balanceMyOut - prints out to output file
	 *
	 *	Process:
	 *		prompts the user to enter an Account number
	 *		returns the user's account data back
	 *
	 *	Output:
	 * 		what the user is being prompted on prints to the console
	 * 		Either an transaction failure reason or transaction success is printed to the output file
	 * 		
	 */
	public static void balance(Bank bankBalance, Scanner balanceMyIn, PrintWriter balanceMyOut) {
		
		int balanceAccount = balanceMyIn.nextInt();
		System.out.println("Enter the Account Number: " + balanceAccount);
		System.out.println();
		
		TransactionTicket balanceTicket = new TransactionTicket(balanceAccount, null, "", 0, 0);
		TransactionTicket copyTicket = new TransactionTicket(balanceTicket);
		
		TransactionReceipt receipt = bankBalance.getBalance(copyTicket);
		TransactionReceipt copyReceipt = new TransactionReceipt(receipt);
		
		Boolean successIndicator = copyReceipt.getTransactionSuccessIndicatorFlag();
		
		if(successIndicator == false) {
			balanceMyOut.println(copyReceipt.getTransactionFailureReason());
		}
		else {
			balanceMyOut.println(copyReceipt.toString());
		}
		
	}
	/*	Method deposit
	 *	Input:
	 *		Bank bankDeposit - reference to a Bank Class
	 *		Scanner depositMyIn - read in from input file
	 *		PrintWriter depositMyOut - prints to an output file
	 *
	 *	Process:
	 *		prompts the user to enter an Account number, the withdraw amount, and a new CD term
	 *		If there are no errors, then database is updated to the user's requests for deposits
	 *
	 *	Output:
	 * 		what the user is being prompted on prints to the console
	 * 		Either an transaction failure reason or transaction success is printed to the output file
	 * 
	 */
	public static void deposit(Bank bankDeposit, Scanner depositMyIn, PrintWriter depositMyOut) {

		int depositCD = 0;
		
		int depositAccount = depositMyIn.nextInt();
		System.out.println("Enter the Account Number: " + depositAccount);
		double depositAmount = depositMyIn.nextDouble();
		System.out.printf("%s%.2f\n","Enter the Deposit Amount: ", depositAmount);
		depositCD = depositMyIn.nextInt();
		System.out.println("Enter New CD Maturity Date(6,12,18,24, or 0 if Account Type is Not CD): " + depositCD);
		System.out.println();
		
		TransactionTicket depositTicket = new TransactionTicket(depositAccount, null, "", depositAmount, depositCD);
		TransactionTicket copyTicket = new TransactionTicket(depositTicket);
		
		TransactionReceipt receipt = bankDeposit.makeDeposit(copyTicket);
		TransactionReceipt copyReceipt = new TransactionReceipt(receipt);
		
		Boolean successIndicator = copyReceipt.getTransactionSuccessIndicatorFlag();
		
		if(successIndicator == false) {
			depositMyOut.println(copyReceipt.getTransactionFailureReason());
		}
		else {
			Bank.addAmount(bankDeposit, copyTicket);
			depositMyOut.println(copyReceipt.toString());
		}

		
	}
	/*	Method withdrawal
	 *	Input:
	 *		Bank bankWithdraw - reference to a Bank class
	 *		Scanner withdrawMyIn - reads in from input file
	 *		PrintWriter withdrawMyOut - prints to a output file
	 *
	 *	Process:
	 *		prompts the user to enter an Account number, the withdraw amount, and a new CD term
	 *		If there are no errors, then database is updated to the user's requests for withdrawal
	 *
	 *	Output:
	 * 		what the user is being prompted on prints to the console
	 * 		Either an transaction failure reason or transaction success is printed to the output
	 * 
	 */
	public static void withdrawal(Bank bankWithdraw, Scanner withdrawMyIn, PrintWriter withdrawMyOut) {
		
		int withdrawAccount = withdrawMyIn.nextInt();
		System.out.println("Enter the Account Number: " + withdrawAccount);
		double withdrawAmount = withdrawMyIn.nextDouble();
		System.out.printf("%s%.2f\n","Enter the Withdraw Amount: ", withdrawAmount);
		int withdrawCD = withdrawMyIn.nextInt();
		System.out.println("Enter New CD Maturity Date(6,12,18,24, or 0 if Account Type is not CD): " + withdrawCD);
		System.out.println();
		
		TransactionTicket withdrawTicket = new TransactionTicket(withdrawAccount, null, "", withdrawAmount, withdrawCD);
		TransactionTicket copyTicket = new TransactionTicket(withdrawTicket);
		
		TransactionReceipt receipt = bankWithdraw.makeWithdrawal(copyTicket);
		TransactionReceipt copyReceipt = new TransactionReceipt(receipt);
		
		Boolean successIndicator = copyReceipt.getTransactionSuccessIndicatorFlag();
		
		if(successIndicator == false) {
			withdrawMyOut.println(copyReceipt.getTransactionFailureReason());
		}
		else {
			Bank.subAmount(bankWithdraw, copyTicket);
			withdrawMyOut.println(copyReceipt.toString());
		}
			
		
	}
	/*	Method clearCheck
	 *	Input:
	 *		Bank bankCheck - reference to a Bank class
	 *		Scanner checkMyIn - read in from input file
	 *		PrintWriter checkMyOut - prints to an output file
	 *
	 *	Process:
	 *		prompts the user to enter an Account number, check amount, and the date of the check
	 *		If there are no errors, then database is updated to the user's requests for clearing checks
	 *
	 *	Output:
	 * 		what the user is being prompted on prints to the console
	 * 		Either an transaction failure reason or transaction success is printed to the output
	 * 
	 */
	public static void clearCheck(Bank bankCheck, Scanner checkMyIn, PrintWriter checkMyOut) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		
		int checkAccount = checkMyIn.nextInt();
		System.out.println("Enter the Account Number: " + checkAccount);
		double checkAmount = checkMyIn.nextDouble();
		System.out.printf("%s%.2f\n", "Enter the Check Amount to Withdraw: ", checkAmount);
		String checkDate = checkMyIn.next();
		System.out.println("Enter the Date of Check(MM/DD/YYYY): " + checkDate);
		System.out.println();
		
		String[] arrDate = checkDate.split("/");
		calendar.set(Integer.parseInt(arrDate[2]),
					 Integer.parseInt(arrDate[0]) - 1,
					 Integer.parseInt(arrDate[1]));
		Check check = new Check(checkAccount, checkAmount, calendar);
		Check copyCheck = new Check(check);
		
		TransactionReceipt receipt = bankCheck.clearCheck(copyCheck);
		TransactionReceipt copyReceipt = new TransactionReceipt(receipt);
		Boolean successIndicator = copyReceipt.getTransactionSuccessIndicatorFlag();
		
		if(successIndicator == false) {
			checkMyOut.println(copyReceipt.getTransactionFailureReason());
		}
		else {
			checkMyOut.println(copyReceipt.toString());
			Bank.subAmount(bankCheck, copyCheck);
		}
			
	}
			
	
	/*	Method acctInfo
	 *	Input:
	 *		Bank bank - reference to a Bank class
	 *		Scanner infoMyIn - reads in from input file
	 *		PrintWriter infoMyOut - prints to an output file
	 *
	 *	Process:
	 *		prompts the user to enter SSN 
	 *		returns all of the account data associated with the SSN
	 *
	 *	Output:
	 * 		what the user is being prompted on prints to the console
	 * 		Either an transaction failure reason or transaction success is printed to the output
	 * 
	 */
	public static void acctInfo(Bank bankAccountInfo, Scanner infoMyIn, PrintWriter infoMyOut) {
		
		Account account;
		
		String SSN = infoMyIn.next();
		System.out.println("Enter SSN Number: " + SSN);
		System.out.println();
		
		int accountFound = -1;
		 
		for(int i = 0; i < bankAccountInfo.getNumAccts(); i++) 
			if(bankAccountInfo.readTypeOfAccount(i).getDepositor().getSSN().equals(SSN))
				accountFound = i;
		
		if(accountFound == -1) {
			infoMyOut.println(" Transaction Type - Account Info");
			infoMyOut.println(" -------------------------------");
			infoMyOut.println(" !Error! SSN " + SSN + " does not Exist");
			infoMyOut.println();
			infoMyOut.println();
		}
		else {
			infoMyOut.println(" Transaction Type - Account Info");
			infoMyOut.println(" -------------------------------");
			infoMyOut.println(" SSN: " + SSN);
			infoMyOut.printf("%9s %15s %14s %18s %16s %15s %15s %15s\n", " Last Name", "First Name", "SSN", "Account Number", "Account Type", "Account Status", "Balance", "Maturity Date");
			infoMyOut.println(" ----------------------------------------------------------------------------------------------------------------------------");
			for(int k = 0; k < bankAccountInfo.getNumAccts(); k++) 
				if (bankAccountInfo.readTypeOfAccount(k).getDepositor().getSSN().equals(SSN)){
					account = bankAccountInfo.readTypeOfAccount(k);
					infoMyOut.print(" " + account.toString());
				}
			infoMyOut.println();
			infoMyOut.println();
		}
		
	}
	
	/*	Method acctInfoHistory
	 * 	Input: 
	 * 		Bank bankHistory - reference to a Bank Class
	 * 		Scanner historyMyIn - reads in from input file
	 * 		PrintWriter historyMyOut - prints to an output file
	 * 
	 * 	Process:
	 * 		prompts the user to enter SSN
	 * 		return all of the account data associated with the SSN alongs side with each account's transaction history
	 * 
	 *  Output:
	 * 		what the user is being prompted on prints to the console
	 * 		Either an transaction failure reason or transaction success is printed to the output
	 * 
	 */
	public static void acctInfoHistory(Bank bankHistory, Scanner historyMyIn, PrintWriter historyMyOut) {
		
		TransactionReceipt receipt;
		Account account;
		
		int count = 0;
		
		String SSN = historyMyIn.next();
		System.out.println("Enter SSN Number: " + SSN);
		System.out.println();
		
		int accountFound = -1;
		
		for(int i = 0; i < bankHistory.getNumAccts(); i++) 
			 if(bankHistory.readTypeOfAccount(i).getDepositor().getSSN().equals(SSN))
				 accountFound = i;
		
		if(accountFound == - 1) {
			historyMyOut.println(" Transaction Type - Account Info History");
			historyMyOut.println(" ---------------------------------------");
			historyMyOut.println(" !Error! SSN " + SSN + " does not Exist");
			historyMyOut.println();
			historyMyOut.println();
		}
		else {	
			historyMyOut.println(" Transaction Type - Account Info History");
			historyMyOut.println(" ---------------------------------------");
			historyMyOut.println(" SSN: " + SSN);
			for(int k = 0; k < bankHistory.getNumAccts(); k++) {	
				if (bankHistory.readTypeOfAccount(k).getDepositor().getSSN().equals(SSN)) {
					historyMyOut.printf("%9s %15s %14s %18s %16s %15s %15s %15s\n", " Last Name", "First Name", "SSN", "Account Number", "Account Type", "Account Status", "Balance", "Maturity Date");
					historyMyOut.println(" ----------------------------------------------------------------------------------------------------------------------------");
					
					account = bankHistory.readTypeOfAccount(k);
					Account copyAccount = new Account(account);
					historyMyOut.print(" " + copyAccount.toString() + "\n");
					
					historyMyOut.printf("%82s\n","-------------------- Account Transaction --------------------");
					historyMyOut.printf("%12s%17s%12s%12s%12s%25s\n", "Date", "Transaction", "Amount", "Status", "Balance", "Reason For Failure");
					for(int n = 0; n < account.getTransactionSize(account.getAcctNumber()); n++) {
						receipt = bankHistory.readAccountFile(n, account.getAcctNumber());
						historyMyOut.print(receipt.toString());
					}
					count++;
					historyMyOut.println();
					historyMyOut.println();
					
				}
				
			}
				historyMyOut.println(" " + count + " Account(s) were found");
				historyMyOut.println();
				historyMyOut.println();
		}
		
	}
	
	/*	Method newAcct
	 *	Input:
	 *		Bank bankNewAccount - reference to a Bank class
	 *		Scanner newMyIn - reads in from input file
	 *		PrintWriter newMyOut - prints to an output file
	 *
	 *	Process:
	 *		prompts the user to enter a last name, first name, SSN, a new account number, account type, a new balance, and CD term
	 *		If there are no errors, then database is updated to the user's requests
	 *
	 *	Output:
	 * 		what the user is being prompted on prints to the console
	 * 		Either an transaction failure reason or transaction success is printed to the output
	 * 
	 */
	public static void newAcct(Bank bankNewAccount, Scanner newMyIn, PrintWriter newMyOut) {
		
		TransactionReceipt receipt;
		
		String newLast = newMyIn.next();
		System.out.println("Enter Your Last Name: " + newLast);
		String newFirst = newMyIn.next();
		System.out.println("Enter Your First Name: " + newFirst);
		String newSSN = newMyIn.next();
		System.out.println("Enter Your SSN: " + newSSN);
		int newAccount = newMyIn.nextInt();
		System.out.println("Enter the New Account Number: " + newAccount);
		String newAccountType = newMyIn.next();
		System.out.println("Enter Account Type: " + newAccountType);
		String newAccountStatus = newMyIn.next();
		System.out.println("Enter Account Status(Open/Closed): " + newAccountStatus);
		double newBalance = newMyIn.nextDouble();
		System.out.printf("%s%.2f\n","Enter Your Balance Amount: ", newBalance);
		int newCDterm = newMyIn.nextInt();
		System.out.println("Enter CD Term(6,12,18,24, or 0 If Account Type is not CD): " + newCDterm);
		System.out.println();
		
		String date = "";
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, newCDterm);
		
		if(newAccountType.equals("CD")) 
			date = String.format("%02d/%02d/%4d", calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR));
		
		TransactionTicket newTicket = new TransactionTicket(newAccount, calendar, newAccountType, newBalance, newCDterm);
		TransactionTicket copyTicket = new TransactionTicket(newTicket);
		
		Name name = new Name(newLast, newFirst);
		Name copyName = new Name(name);
		
		Depositor deposit = new Depositor(newSSN, copyName);
		Depositor copyDeposit = new Depositor(deposit);
		
		Account account;
		
		if(newAccountType.equals("CD")) {
			account = new CDAccount(newAccount, newAccountType, newAccountStatus, newBalance, date, copyDeposit);
			receipt = bankNewAccount.openNewAcct(newTicket, account);
		}
		else if(newAccountType.equals("Checking")) {
			account = new CheckingAccount(newAccount, newAccountType, newAccountStatus, newBalance, date, copyDeposit);
			receipt = bankNewAccount.openNewAcct(newTicket, account);
		}
		else {
			account = new SavingsAccount(newAccount, newAccountType, newAccountStatus, newBalance, date, copyDeposit);
			receipt = bankNewAccount.openNewAcct(newTicket, account);
		}
		
		TransactionReceipt copyReceipt = new TransactionReceipt(receipt);
		
		Boolean successIndicator = copyReceipt.getTransactionSuccessIndicatorFlag();
		
		if(successIndicator == false) {
			newMyOut.println(copyReceipt.getTransactionFailureReason());
		}
		else {
			newMyOut.println(copyReceipt.toString());
			Bank.addAmount(bankNewAccount, copyTicket);	
		}
		
	}
	
	/*	Method closeAcct
	 * 	Input: 
	 * 		Bank closeBank - reference to a Bank class
	 * 		Scanner closeMyIn - reads in from input file
	 * 		PrintWriter closeMyOut - prints out to an output file
	 * 
	 * 	Process:
	 * 		changes the status of an account to close
	 * 
	 *  Output:
	 * 		what the user is being prompted on prints to the console
	 * 		Either an transaction failure reason or transaction success is printed to the output
	 * 
	 */
	public static void closeAcct(Bank closeBank, Scanner closeMyIn, PrintWriter closeMyOut) {
		
		int closeAccount = closeMyIn.nextInt();
		System.out.println("Enter The Account Number To Close: " + closeAccount);
		System.out.println();
		
		TransactionTicket closeTicket = new TransactionTicket(closeAccount, null, "", 0, 0);
		TransactionTicket copyTicket = new TransactionTicket(closeTicket);
		
		TransactionReceipt receipt = closeBank.closeAcct(copyTicket);
		TransactionReceipt copyReceipt = new TransactionReceipt(receipt);
		
		Boolean successIndicator = copyReceipt.getTransactionSuccessIndicatorFlag();
		
		if(successIndicator == false) {
			closeMyOut.println(copyReceipt.getTransactionFailureReason());
		}
		else {
			closeMyOut.println(copyReceipt.toString());
		}
		
	}
	
	/*	Method reopenAcct
	 * 	Input: 
	 * 		Bank reopenBank - reference to a Bank class
	 * 		Scanner reopenMyIn - reads in from input file
	 * 		PrintWriter reopenMyOut - prints to an output file
	 * 
	 * 	Process:
	 * 		changes the status of an account to open
	 * 
	 *  Output:
	 * 		what the user is being prompted on prints to the console
	 * 		Either an transaction failure reason or transaction success is printed to the output
	 * 
	 */
	public static void reopenAcct(Bank reopenBank, Scanner reopenMyIn, PrintWriter reopenMyOut) {
		
		TransactionReceipt receipt;
		
		int reopenAccount = reopenMyIn.nextInt();
		System.out.println("Enter The Account Number To Reopen: " + reopenAccount);
		System.out.println();
		
		TransactionTicket reopenTicket = new TransactionTicket(reopenAccount, null, "", 0, 0);
		receipt = reopenBank.reopenAcct(reopenTicket);
		Boolean successIndicator = receipt.getTransactionSuccessIndicatorFlag();
		
		if(successIndicator == false) {
			reopenMyOut.println(receipt.getTransactionFailureReason());
		}
		else {
			reopenMyOut.println(receipt.toString());
		}
		
	}
	
	/*	Method deleteAcct
	 *	Input:
	 *		Bank bankDeleteAccount - reference to a bank class
	 *		Scanner deleteMyIn - read in from input file
	 *		PrintWriter deleteMyOut - prints to an output file
	 *
	 *	Process:
	 *		prompts the user to enter an Account number
	 *		If there are no errors, then database is updated to the user's requests
	 *
	 *	Output:
	 * 		what the user is being prompted on prints to the console
	 * 		Either an transaction failure reason or transaction success is printed to the output
	 * 
	 */
	public static void deleteAcct(Bank bankDeleteAccount, Scanner deleteMyIn, PrintWriter deleteMyOut) {
		
		TransactionReceipt receipt;
		
		int deleteAccount = deleteMyIn.nextInt();
		System.out.println("Enter the Account Number to Delete: " + deleteAccount);
		System.out.println();
		
		TransactionTicket deleteTicket = new TransactionTicket(deleteAccount, null, "", 0 ,0);
		receipt = bankDeleteAccount.deleteAcct(deleteTicket);
		Boolean successIndicator = receipt.getTransactionSuccessIndicatorFlag();
		
		if(successIndicator == false)
			deleteMyOut.println(receipt.getTransactionFailureReason());
		else {
			deleteMyOut.println(receipt.toString());
		}
		
	}
	
}
