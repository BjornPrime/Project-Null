package bankapp.view;

import bankapp.services.UserService;
import bankapp.util.UserInput;

public class MainView implements View {
	
	public int input;
	
	@Override
	public void showMenu() {
		System.out.println("Welcome to the First Bank of Springfield!");
		System.out.println("What would you like to do?");
		System.out.println("1 - Log In");
		System.out.println("2 - New User Account");
		System.out.println("0 - Close Program");
	}

	@Override
	public void getUserInput() {
		this.input = UserInput.integerInput(2);
	}
	
	@Override
	public View process() {
		switch (input) {
		case 0: 
			return null;
		case 1:
			int userId;
			do {
				userId = UserService.userLogIn();
			} while (userId == 0);
			if (userId == -1) {
				return new MainView();
			} else if (userId > 0) {
				return new UserWelcome(userId);
			}
			break;
		case 2: 
			UserService.createUser();
			return new MainView();
		}
		return null;
//		return new MainView();
	}

}