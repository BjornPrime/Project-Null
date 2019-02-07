package bankapp.view;

import bankapp.daos.UserDao;
import bankapp.models.BankUser;
import bankapp.services.UserService;
import bankapp.util.UserInput;

public class UserWelcome implements View {
	
	private int userID;
	public BankUser user;
	public int input;
	
	public UserWelcome(int userID) {
		this.userID = userID;
		this.user = UserDao.retrieveUser(userID);
	}
	
	//look up and set variable 'user' equal to user with data equivalent to that in row userID from DB 
	
	@Override
	public void showMenu() {
		System.out.println("Welcome " + this.user.getFirstName() + " " + this.user.getLastName() + "!");
		
		System.out.println("1 - New Transaction");
		System.out.println("2 - New Account");
		System.out.println("3 - Add Account User");
		System.out.println("4 - Log Out");
		System.out.println("0 - Close Program");
		
		UserService.printAccounts(this.user);
//		int i = 0;
//		for (Integer account: user.accounts) {
//			//use db to look up account
//			System.out.println(i + " - " + account.accountID + ": " + account.balance + " " + account.accountType);
//			i++;
//		}
	}

	@Override
	public View process() {
		switch(input) {
		case 0:
			return null;
		case 1:
			UserService.createTransaction(this.user);
			return new UserWelcome(this.userID);
		case 2:
			UserService.createAccount(this.user);
			return new UserWelcome(this.userID);
		case 3:
			UserService.addAccountUser(this.user);
			return new UserWelcome(this.userID);
		case 4:
			return new MainView();
		}
		return null;
	}

	@Override
	public void getUserInput() {
		this.input = UserInput.integerInput(4);
	}
		
		//pull accounts matching userID and present them along with current balances
	
}
