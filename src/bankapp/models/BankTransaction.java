package bankapp.models;

public class BankTransaction {
	private int transactionID;
	private String transactionType;
	private int toUserID;
	private int toAccountID;
	private int fromUserID;
	private int fromAccountID;
	private int amount;
	
	public BankTransaction(int transactionID, String transactionType, int toID, int fromID, int amount) {
		this.transactionID = transactionID;
		this.transactionType = transactionType;
		if (transactionType == "Transfer") {
			this.toAccountID = toID;
			this.fromAccountID = fromID;
		} else if (transactionType == "Withdrawal") {
			this.toUserID = toID;
			this.fromAccountID = fromID;
		} else if (transactionType == "Deposit") { //for deposits
			this.toAccountID = toID;
			this.fromUserID = fromID;
		}
		
		//look up relevant accounts
		//credit or debit said accounts
		//sync java objects with database
		//write transaction to ledger
		
		
	}
	
	

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}



	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}



	public void setToUserID(int toUserID) {
		this.toUserID = toUserID;
	}



	public void setToAccountID(int toAccountID) {
		this.toAccountID = toAccountID;
	}



	public void setFromUserID(int fromUserID) {
		this.fromUserID = fromUserID;
	}



	public void setFromAccountID(int fromAccountID) {
		this.fromAccountID = fromAccountID;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public int getTransactionID() {
		return transactionID;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public int getToUserID() {
		return toUserID;
	}

	public int getToAccountID() {
		return toAccountID;
	}

	public int getFromUserID() {
		return fromUserID;
	}

	public int getFromAccountID() {
		return fromAccountID;
	}

	public int getAmount() {
		return amount;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + fromAccountID;
		result = prime * result + fromUserID;
		result = prime * result + toAccountID;
		result = prime * result + toUserID;
		result = prime * result + transactionID;
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
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
		BankTransaction other = (BankTransaction) obj;
		if (amount != other.amount)
			return false;
		if (fromAccountID != other.fromAccountID)
			return false;
		if (fromUserID != other.fromUserID)
			return false;
		if (toAccountID != other.toAccountID)
			return false;
		if (toUserID != other.toUserID)
			return false;
		if (transactionID != other.transactionID)
			return false;
		if (transactionType == null) {
			if (other.transactionType != null)
				return false;
		} else if (!transactionType.equals(other.transactionType))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "BankTransaction [transactionID=" + transactionID + ", transactionType=" + transactionType
				+ ", toUserID=" + toUserID + ", toAccountID=" + toAccountID + ", fromUserID=" + fromUserID
				+ ", fromAccountID=" + fromAccountID + ", amount=" + amount + "]";
	}


	public BankTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
