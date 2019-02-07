package bankapp.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import bankapp.daos.AccountDao;
import bankapp.daos.UserDao;
import bankapp.models.BankAccount;
import bankapp.models.BankTransaction;
import bankapp.models.BankUser;
import bankapp.util.DatabaseConnect;
import bankapp.util.UserInput;

public class UserService {
	
	private static UserDao userDao = new UserDao();
	
	public static BankUser createUser() {
		BankUser newUser = new BankUser();
		boolean passwordMatches = false;
		
		System.out.println("New User Account");
		System.out.println("----------");
		System.out.println("First name:");
		newUser.setFirstName(UserInput.stringInput(30));
		System.out.println("Last name:");
		newUser.setLastName(UserInput.stringInput(30));
		System.out.println("Email:");
		newUser.setEmail(UserInput.stringInput(30));
		while (!passwordMatches) { //making sure that passwords match
			System.out.println("Password:");
			newUser.setPassword(UserInput.stringInput(30));
			System.out.println("Retype Password:");
			if (newUser.getPassword().equals(UserInput.stringInput(30))) {
				passwordMatches = true;
			} else {
				System.out.println("Password mismatch. Please try again.");
			}
		}
		
		newUser = userDao.insertUser(newUser); //why can't this just be static?
		System.out.println("New User created successfully: " + newUser);
		return newUser;
	}
	
	private static AccountDao accountDao = new AccountDao();
	
	public static BankAccount createAccount(BankUser owner) {
		BankAccount newAccount = new BankAccount();
		
		System.out.println("Please enter either (1) for checking account or (0) for savings account:");
		newAccount.setAccountType(UserInput.integerInput(1) == 1 ? "Checking" : "Savings");
		System.out.println("Initial Deposit Value (in cents):");
		newAccount.setBalance(UserInput.integerInput(1000000000));
		newAccount.addToUserList(owner.getUserID());
		
		newAccount = accountDao.insertAccount(newAccount, owner);
		System.out.println("New Account Created: " + newAccount);
		return newAccount;
	}

	public static int userLogIn() { //checks credentials and returns userID if login successful
		int userID = 0;
		
		System.out.println("User Email:");
		String email = UserInput.stringInput(30);
		try(Connection conn = DatabaseConnect.getConnection()) {
			String query = "SELECT password, id FROM bank_users WHERE ? = email"; //RETURNING password, id";
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, email);
			
			ResultSet resultSet = statement.executeQuery();
//			System.out.println(resultSet);
			
			if(resultSet.next()) {
				System.out.println("Please enter your password:");
				String guess = UserInput.stringInput(30);
				if(guess.equals(resultSet.getString("password"))) {
//					System.out.println("Welcome!");
					return resultSet.getInt("id");
				} else {
					System.out.println("Password Incorrect");
					return -1;
				}
			} else {
				System.out.println("User not found");
				return -1;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userID;
//		int x = (int) Math.round(2*Math.random()-1);
//		System.out.println(x);
//		return x;
	}
	
	public static void printAccounts(BankUser user) {
//		Set<Integer> accounts = user.getAccounts();
//		for(Integer accountID:accounts) {
//			
//		}
		Iterable<Integer> accounts = user.getAccounts();
		for(Integer account: accounts) {
			BankAccount item = AccountDao.retrieveAccount(account);
			int a = item.getAccountID();
			String b = item.getAccountType();
			int c = item.getBalance();
			System.out.println("Account #" + a + " | " + b + " | Current Balance: $" + (c / 100) + "." + (c % 100));
		}
	}
	
	public static void addAccountUser(BankUser owner) {
		System.out.println("Add user to which account #?");
		Integer account = UserInput.integerInput(100);
		while(!owner.getAccounts().contains(account)) {
			System.out.println("Invalid account # entered");
			account = UserInput.integerInput(100);
		}
		System.out.println("Please enter the email of the user you wish to add to the account:");
		String email = UserInput.stringInput(30);
		AccountDao.addAccountUser(account, email);
		System.out.println("User added to account");
	}
	
	public static void createTransaction(BankUser user) {
		BankTransaction newTransaction = new BankTransaction();
		
		System.out.println("Please enter either (0) for deposit, (1) for withdrawal, (2) for transfer:");
		int type = UserInput.integerInput(2);
		switch(type) {
		case 0:
			newTransaction.setTransactionType("Deposit");
			break;
		case 1:
			newTransaction.setTransactionType("Withdrawal");
			break;
		case 2:
			newTransaction.setTransactionType("Transfer");
			break;
		}
		System.out.println(newTransaction.getTransactionType() + " selected");
		switch(type) {
		case 0:
			System.out.println("Deposit to which account #?");
			Integer depositAccount = UserInput.integerInput(100);//This block could be its own input function
			while(!user.getAccounts().contains(depositAccount)) {
				System.out.println("Invalid account # entered");
				depositAccount = UserInput.integerInput(100);
			}
			newTransaction.setToAccountID(depositAccount);
			System.out.println("Account #" + depositAccount + " selected");
			System.out.println("Amount to deposit (in cents):");
			newTransaction.setAmount(UserInput.integerInput(1000000000));
			AccountDao.creditAccount(newTransaction.getToAccountID(), newTransaction.getAmount());
			System.out.println("Transaction successful");
			break;
		case 1:
			System.out.println("Withdraw from which account #?");
			Integer withdrawalAccount = UserInput.integerInput(100);
			while(!user.getAccounts().contains(withdrawalAccount)) {
				System.out.println("Invalid account # entered");
				withdrawalAccount = UserInput.integerInput(100);
			}
			newTransaction.setFromAccountID(withdrawalAccount);
			System.out.println("Account #" + withdrawalAccount + " selected");
			System.out.println("Amount to withdraw (in cents):");
			newTransaction.setAmount(UserInput.integerInput(1000000000));
			if(!AccountDao.creditAccount(newTransaction.getFromAccountID(), -1*newTransaction.getAmount())) {
				System.out.println("Transaction cancelled! Insufficient funds!");
			} else {
				System.out.println("Transaction successful");
			}
			break;
		case 2:
			System.out.println("Transfer from which account #?");
			Integer fromAccount = UserInput.integerInput(100);
			while(!user.getAccounts().contains(fromAccount)) {
				System.out.println("Invalid account # entered");
				fromAccount = UserInput.integerInput(100);
			}
			newTransaction.setFromAccountID(fromAccount);
			System.out.println("Account #" + fromAccount + " selected");
			System.out.println("Transfer to which account #?");
			Integer toAccount = UserInput.integerInput(100);
			newTransaction.setToAccountID(toAccount);
			System.out.println("Transfer how much from account #" + fromAccount + " to account #" + toAccount + "?");
			newTransaction.setAmount(UserInput.integerInput(1000000000));
			if(AccountDao.creditAccount(newTransaction.getFromAccountID(), -1*newTransaction.getAmount())) {
				AccountDao.creditAccount(newTransaction.getToAccountID(), newTransaction.getAmount());
				System.out.println("Transaction successful");
			} else {
				System.out.println("Transaction cancelled! Insufficient funds!");
			}
			break;
		}
	}
}
