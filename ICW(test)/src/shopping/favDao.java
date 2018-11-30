package shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class favDao {
	private static String url = "jdbc:postgresql://localhost:5432/favdb";
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

	public favDao() throws SQLException {
		// JDBCドライバのロード

		// データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
		this.con_ = DriverManager.getConnection(url, user, password);
	}

	/**
	 * ユーザの購入履歴を取得します.
	 *
	 * @param user_id
	 * @return 購入履歴（ResultSet）
	 * @throws SQLException
	 */
	public void close() {
		try {
			// データベースとの接続を解除する
			if (this.con_ != null) {
				this.con_.close();
			}
			if (this.ps_ != null) {
				this.ps_.close();
			}
			if (this.rs_ != null) {
				this.rs_.close();
			}
		} catch (SQLException se) {
			// データベースとの接続解除に失敗した場合
			se.printStackTrace();
		}
	}
	public static void updateFav(String name, String user_id) {
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "insert into fav (meigara_name, user_id)values(?, ?)";
		try {
			con = DriverManager.getConnection(url, user , password);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, user_id);
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
	
	public static void deleteFav(String name, String user_id) {
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "DELETE  FROM fav WHERE meigara_name = ? and user_id = ?";
		try {
			con = DriverManager.getConnection(url, user , password);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, user_id);
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

	public static ArrayList<FavBean> selectFav(String user_id) {
		System.out.println(user_id);
		String sql = "select * from fav where user_id = ?";
		ArrayList<FavBean> fav_beans = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			// 検索結果を1レコードずつ処理
			while (rs.next()) {
				FavBean fb = new FavBean();
				// 商品ID を取得
				
				// 商品名を取得
				fb.setItem_name(rs.getString("meigara_name"));
				
				fav_beans.add(fb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fav_beans;
	}
}
