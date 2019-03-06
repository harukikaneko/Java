package account;

import java.io.Serializable;

public class AccountBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String account_id;
	private int balance;
	private String user_id;

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public AccountBean(String account_id, int balance, String user_id) {
		this.account_id = account_id;
		this.balance = balance;
		this.user_id = user_id;

	}

	public AccountBean() {
		this.account_id = "";
		this.balance = 0;
		this.user_id = "";
	}
}