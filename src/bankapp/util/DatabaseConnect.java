package bankapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnect {

	public static Connection getConnection() {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String role ="bank_project_jdbc";
		String password = "top-secret-password";
		Connection conn = null;
		
		try {conn = DriverManager.getConnection(url, role, password);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static ResultSet preparedQuery(String queryStatement, String[] inputs) {
		Connection conn = DatabaseConnect.getConnection();
		
		try {
			String query = queryStatement;
			PreparedStatement statement = conn.prepareStatement(query);
			
			for (int i = 0; i < inputs.length; i++) {
				statement.setString(i+1, inputs[i]);
			}
			
			return statement.executeQuery();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
