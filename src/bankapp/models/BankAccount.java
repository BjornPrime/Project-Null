package bankapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
//import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class BankAccount {
	private int accountID;
	private String accountType;
//	private String currency = "USD";
	private List<Integer> transactionHistory;
	private int balance;
	private Set<Integer> users = new HashSet<>();
	private String accountStatus = "Open";
	
	
	public BankAccount(int accountID, ResultSet accountInfo) {
		this.accountID = accountID;
		try {
			if(accountInfo.next()) {
				this.accountType = accountInfo.getString("account_type");
				this.balance = accountInfo.getInt("balance");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public BankAccount(int accountID, String accountType, int balance,
			Set<Integer> users) {
		super();
		this.accountID = accountID;
		this.accountType = accountType;
		this.balance = balance;
		this.users = users;
//		this.accountStatus = accountStatus;
	}
	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BankAccount [accountID=" + accountID + ", accountType=" + accountType + ", transactionHistory="
				+ transactionHistory + ", balance=" + balance + ", users=" + users + ", accountStatus=" + accountStatus
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountID;
		result = prime * result + ((accountStatus == null) ? 0 : accountStatus.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + balance;
		result = prime * result + ((transactionHistory == null) ? 0 : transactionHistory.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		BankAccount other = (BankAccount) obj;
		if (accountID != other.accountID)
			return false;
		if (accountStatus == null) {
			if (other.accountStatus != null)
				return false;
		} else if (!accountStatus.equals(other.accountStatus))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (balance != other.balance)
			return false;
		if (transactionHistory == null) {
			if (other.transactionHistory != null)
				return false;
		} else if (!transactionHistory.equals(other.transactionHistory))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
//	public String getCurrency() {
//		return currency;
//	}
//	public void setCurrency(String currency) {
//		this.currency = currency;
//	}
	public List<Integer> getTransactionHistory() {
		return transactionHistory;
	}
	public void setTransactionHistory(List<Integer> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Set<Integer> getUserList() {
		return users;
	}
	public void addToUserList(Integer user) {
		this.users.add(user);
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}	
	
}
