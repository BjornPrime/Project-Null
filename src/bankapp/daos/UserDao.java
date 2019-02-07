package bankapp.daos;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import bankapp.models.BankUser;
import bankapp.util.DatabaseConnect;

public class UserDao {
	
	//Connection AccountDao.conn = DatabaseConnect.getConnection();

	public BankUser insertUser(BankUser bankUser) {
		
//		Class.forName("bankapp.daos.jdbc.Driver");
		
		try {
			String query = "INSERT INTO bank_users (first_name, last_name, email, password) " + "VALUES (?, ?, ?, ?) RETURNING id";
			PreparedStatement statement = AccountDao.conn.prepareStatement(query);
			
			statement.setString(1, bankUser.getFirstName());
			statement.setString(2, bankUser.getLastName());
			statement.setString(3, bankUser.getEmail());
			statement.setString(4, bankUser.getPassword());
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				bankUser.setUserID(resultSet.getInt("id"));
			}
			
		} catch(SQLException e) {
			//how catch?????
			e.printStackTrace();
		}
		
		return bankUser;	
		
	}
	
	public static BankUser retrieveUser(int userID) {
		BankUser currentUser;
//		String[] input = new String [1];
//		input[0] = Integer.toString(userID);
//		ResultSet userInfo = DatabaseConnect.preparedQuery("SELECT * FROM bank_users WHERE ? = id", input);
		//start
//		Connection conn = DatabaseConnect.getConnection();
		
		try {
			String query = "SELECT * FROM bank_users WHERE ? = id";
			PreparedStatement statement = AccountDao.conn.prepareStatement(query);
			
			statement.setInt(1, userID);
			
			ResultSet userInfo = statement.executeQuery();
			currentUser = new BankUser(userID, userInfo);
			//add users accounts or make accounts separate? How to deal with no accounts?
			
			query = "SELECT account_id FROM account_user_junctions WHERE ? = user_id"; //find user accounts
			statement = AccountDao.conn.prepareStatement(query);
			statement.setInt(1, userID);
			ResultSet userAccounts = statement.executeQuery();
			
			Set<Integer> accountList = new HashSet<>(); 
			while(userAccounts.next()) {
				accountList.add(userAccounts.getInt("account_id"));
			}
			currentUser.setAccounts(accountList);
			
			return currentUser;	
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		//end
		
//		currentUser = new BankUser(userID, userInfo);
//		//add users accounts or make accounts separate? How to deal with no accounts?
//		
//		return currentUser;			
	}
}
