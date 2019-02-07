package bankapp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bankapp.models.BankAccount;
import bankapp.models.BankUser;
import bankapp.util.DatabaseConnect;

public class AccountDao {
	
	public static Connection conn = DatabaseConnect.getConnection();

	public BankAccount insertAccount(BankAccount account, BankUser owner) {
		
//		Connection conn = DatabaseConnect.getConnection();
		
		try {
			String query = "INSERT INTO bank_accounts (account_type, balance) " + "VALUES(?, ?) RETURNING id";
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, account.getAccountType());
			statement.setInt(2, account.getBalance());
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				account.setAccountID(resultSet.getInt("id"));
			}
			
			query = "INSERT INTO account_user_junctions (user_id, account_id) " + "VALUES(?, ?) RETURNING junction_id";
			statement = conn.prepareStatement(query);
			
			statement.setInt(1, owner.getUserID());
			statement.setInt(2, account.getAccountID());
			
			resultSet = statement.executeQuery();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return account;	
		
	}
	
	public static BankAccount retrieveAccount(int accountID) {
		BankAccount account = null;
		
//		Connection conn = DatabaseConnect.getConnection();
		
		try {
			String query = "SELECT * FROM bank_accounts WHERE ? = id";
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setInt(1,  accountID);
			
			ResultSet accountInfo = statement.executeQuery();
			account = new BankAccount(accountID, accountInfo);
//			System.out.println(account);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	public static boolean creditAccount(int accountID, int amount) {
//		Connection conn = DatabaseConnect.getConnection();
		BankAccount account = retrieveAccount(accountID);

		int balance = account.getBalance();
		
		if((amount + balance) >= 0) {
			try {
				String query = "UPDATE bank_accounts " + "SET balance = " + (balance + amount) + " " + "WHERE ? = id RETURNING balance";
				PreparedStatement statement = conn.prepareStatement(query);
				
				statement.setInt(1, accountID);
				
				ResultSet accountInfo = statement.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}
	
	public static void addAccountUser(int accountID, String email) {
//		Connection conn = DatabaseConnect.getConnection();
		int userID;
		
		try {
			String query = "SELECT id FROM bank_users WHERE email = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, email);
			
			ResultSet userInfo = statement.executeQuery();
			
			if (userInfo.next()) {
				userID = userInfo.getInt("id");
				query = "INSERT INTO account_user_junctions (user_id, account_id) " + "VALUES (?, ?) RETURNING junction_id";
				statement = conn.prepareStatement(query);
				
				statement.setInt(1,  userID);
				statement.setInt(2, accountID);
				
				statement.executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
