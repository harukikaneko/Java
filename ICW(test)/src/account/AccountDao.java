package account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

	private static String url = "jdbc:postgresql://localhost:5432/accountdb";
	private static String user = "postgres";
	private static String password = "password";

	/**
	 * 指定した口座の情報を取得する（残高照会する）
	 *
	 * @param user_id
	 *            会員番号
	 * @return balance 残高（ResultSet）
	 * @throws SQLException
	 */
	private Connection con_ = null;
    private ResultSet rs_ = null;
    private PreparedStatement ps_ = null;

	public static AccountBean getAccountBean1(String user_id) {
		// SQL文を生成
		System.out.println(user_id);
		AccountBean ab = null;
		String SQL = "select * from account where user_id = ?";
		try (Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = con.prepareStatement(SQL)) {
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				ab = new AccountBean();
				ab.setBalance(rs.getInt("balance"));
				ab.setAccount_id(rs.getString("account_id"));
		}} catch (SQLException e) {
			System.out.println(e);
		}

		return ab;

		
	

}public static String getAccountBean2(String user_id) {
	// SQL文を生成
	System.out.println(user_id);
	AccountBean ab = null;
	String SQL = "select * from account where user_id = ?";
	try (Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt = con.prepareStatement(SQL)) {
		pstmt.setString(1, user_id);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()){
			ab = new AccountBean();
			ab.setBalance(rs.getInt("balance"));
			ab.setAccount_id(rs.getString("account_id"));
	}} catch (SQLException e) {
		System.out.println(e);
	}

	return SQL ;
	
}}