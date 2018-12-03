package logincahnge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginChangeDao {

	private static String url = "jdbc:postgresql://localhost:5432/usersdb";
	private static String user = "postgres";
	private static String password = "password";
	private static String jdbcDriver = "org.postgresql.Driver";

	static {
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection con_ = null;
	private ResultSet rs_ = null;
	private PreparedStatement ps_ = null;

	public LoginChangeDao() throws SQLException {

		// データベースと接続
		this.con_ = DriverManager.getConnection(url, user, password);

	}

	public static void changeLogin(String id, String id2, String pass, String pass2) throws SQLException {

		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE users SET pass_id = ?, user_pass = ? FROM account WHERE users.user_id = account.user_id AND account_id = ?";
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id2);
			stmt.setString(2, pass);
			stmt.setString(3, id);

			// クエリの実行
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void close() {
		// TODO 自動生成されたメソッド・スタブ

	}
}