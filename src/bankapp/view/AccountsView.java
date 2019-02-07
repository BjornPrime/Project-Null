package bankapp.view;

import bankapp.models.BankUser;

public class AccountsView implements View {
	
	BankUser user;
	
	@Override
	public void showMenu() {
		System.out.println("1 - Add User to Account");
		System.out.println("2 - Back");
		System.out.println("3 - Log Out");
		System.out.println("0 - Close Program");
		
	}

	@Override
	public View process() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getUserInput() {
		// TODO Auto-generated method stub
		
	}
	

}
