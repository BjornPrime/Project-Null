package bankapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;


/*
 * Commonly Generated:
 * -Getters/Setters
 * -Hashcode/Equals
 * -toString
 * -Constructor using super
 * -Constructor using fields
 */
public class BankUser {
	//members
	private int userID;
	private String firstName;
	private String lastName;
	private Set<Integer> accounts;
	private boolean isAdmin = false;
	private String email;
	private String password; //figure out how passwords work
	private List<Integer> transactionHistory;
	
	//methods
//	public BankUser(String firstName, String lastName, String email, String password) {
////		this.userID = access DB and increment userID, maybe just add then get ID from DB
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.email = email;
//		this.password = password;	
////		add to DB
//// 		get userID from DB entry		
//	}

//	public BankUser(int userID) {
//		this.userID = userID;
//		//create query to retrieve user details from DB and set equal to class fields
//		//create another query to pull account numbers from junction table and add to accounts
//	}
	public BankUser(int userID, ResultSet userInfo) {
		this.userID = userID;
		try {
			if(userInfo.next()) {
				this.firstName = userInfo.getString("first_name");
				this.lastName = userInfo.getString("last_name");
				this.email = userInfo.getString("email");
				this.password = userInfo.getString("password");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public BankUser(int userID, String firstName, String lastName, Set<Integer> accounts, String email,
			String password) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accounts = accounts;
		this.email = email;
		this.password = password;
	}

	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Integer> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Integer> accounts) {
		this.accounts = accounts;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Integer> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(List<Integer> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accounts == null) ? 0 : accounts.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((transactionHistory == null) ? 0 : transactionHistory.hashCode());
		result = prime * result + userID;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankUser other = (BankUser) obj;
		if (accounts == null) {
			if (other.accounts != null)
				return false;
		} else if (!accounts.equals(other.accounts))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (isAdmin != other.isAdmin)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (transactionHistory == null) {
			if (other.transactionHistory != null)
				return false;
		} else if (!transactionHistory.equals(other.transactionHistory))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "BankUser [userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName + ", accounts="
				+ accounts + ", isAdmin=" + isAdmin + ", email=" + email + ", password=" + password
				+ ", transactionHistory=" + transactionHistory + "]";
	}




	public BankUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
