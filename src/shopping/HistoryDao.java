package shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryDao {
	private static String url = "jdbc:postgresql://localhost:5432/shoppingdb";
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

	public HistoryDao() throws SQLException {
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
	public static ArrayList<HistoryBean> selectHistory(String user_id) {
		String sql = "select * from shopping where user_id = ?";
		ArrayList<HistoryBean> history_beans = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			// 検索結果を1レコードずつ処理
			while (rs.next()) {
				HistoryBean hb = new HistoryBean();
				// 商品ID を取得
				hb.setItemId(rs.getString("maigara_id"));
				// 商品名を取得
				hb.setItemName(rs.getString("maigara_name"));
				// 商品数を設定
				hb.setQuantity(rs.getInt("order_amt"));
				hb.setPurchased_total(rs.getInt("order_price"));
				hb.setSelect(rs.getString("order_cond"));
				hb.setChoice(rs.getString("exec_cond"));
				hb.setDateinfo(rs.getString("order_id"));
				hb.setDate(rs.getString("order_date"));
				hb.setOrderStatus(rs.getString("status"));
				hb.setSellBuy(rs.getString("sell_buy"));
				hb.setCategory(rs.getString("category"));
				history_beans.add(hb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return history_beans;
	}

	public static ArrayList<HistoryBean> selectHistory2(String name) {
		String sql = "select * from shopping where maigara_name = ?";
		ArrayList<HistoryBean> history_beans = new ArrayList<>();
		System.out.println(name);
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			// 検索結果を1レコードずつ処理
			while (rs.next()) {
				HistoryBean hb = new HistoryBean();
				// 商品ID を取得
				hb.setItemId(rs.getString("maigara_id"));
				// 商品名を取得
				hb.setItemName(rs.getString("maigara_name"));
				// 商品数を設定
				hb.setQuantity(rs.getInt("order_amt"));
				hb.setPurchased_total(rs.getInt("order_price"));
				hb.setSelect(rs.getString("order_cond"));
				hb.setChoice(rs.getString("exec_cond"));
				hb.setDateinfo(rs.getString("order_id"));
				hb.setDate(rs.getString("order_date"));
				hb.setOrderStatus(rs.getString("status"));
				hb.setSellBuy(rs.getString("sell_buy"));
				hb.setCategory(rs.getString("category"));
				history_beans.add(hb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return history_beans;
	}

	/**
	 * 購入履歴テーブルを更新します.
	 *
	 * @param user_id
	 *            ユーザID
	 * @param item_id
	 *            商品ID
	 * @param purchased_num
	 *            購入数
	 * @param date
	 * @param dateinfo
	 * @param choice
	 * @param select
	 * @param purchased_total
	 * @throws SQLException
	 */
	public void updateHistory(String user_id, String item_name, String item_id, int quatity, int purchased_total,
			String select, String choice, String dateinfo, String date, String orderstatus, String sellbuy, String category)
			throws SQLException {
		// SQL文を生成
		// TODO:2-⑩insert文を追加
		this.ps_ = this.con_.prepareStatement(
				"insert into shopping(user_id,maigara_name, maigara_id, order_amt, order_price, order_cond,exec_cond, order_id,order_date, status, sell_buy, category) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		this.ps_.setString(1, user_id);
		this.ps_.setString(2, item_name);
		this.ps_.setString(3, item_id);
		this.ps_.setInt(4, quatity);
		this.ps_.setInt(5, purchased_total);
		this.ps_.setString(6, select);
		this.ps_.setString(7, choice);
		this.ps_.setString(8, dateinfo);
		this.ps_.setString(9, date);
		this.ps_.setString(10, orderstatus);
		this.ps_.setString(11, sellbuy);
		this.ps_.setString(12, category);

		this.ps_.executeUpdate();

	}

	/**
	 * コネクションをクローズします.
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

	public static void cancelHistory(String dateinfo) {
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE shopping SET status = '取消済', sell_buy = '取消' WHERE order_id = ?";
		System.out.println(dateinfo);
		try {
			con = DriverManager.getConnection(url, user , password);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, dateinfo);
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

	// 受付番号を返す

	public static HistoryBean getHistory(String user_id, String d) {
		System.out.println(user_id);
		System.out.println(d);
		// SQL文を生成
		String SQL = "select sum(order_price) as day_count from shopping where user_id = ? and order_date = ?";
		HistoryBean bean = null;

		try (Connection con_ = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt_ = con_.prepareStatement(SQL)) {
			pstmt_.setString(1, user_id);
			pstmt_.setString(2, d);
			ResultSet rs_ = pstmt_.executeQuery();
			// if (rs_.next()) {
			// HistoryBean hb = new HistoryBean();
			// hb.setPurchased_total(rs_.getInt("1"));
			// }
			if (rs_.next()) {
				bean = new HistoryBean();
				bean.setDay_count(rs_.getInt("day_count"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return bean;
	}

	public static int getDayDeal(String d) {
		String sql = "select count (*) as count from shopping where order_date = ?";
		System.out.println(d);

		int day_deal = 0;
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, d);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				day_deal = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return day_deal;
	}
	
	public static HistoryBean getHistory2(String user_id, String d) {
		System.out.println(user_id);
		System.out.println(d);
		// SQL文を生成
		String SQL = "select order_amt from shopping where user_id = ? and order_date = ?";
		HistoryBean bean = null;

		try (Connection con_ = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt_ = con_.prepareStatement(SQL)) {
			pstmt_.setString(1, user_id);
			pstmt_.setString(2, d);
			ResultSet rs_ = pstmt_.executeQuery();
			// if (rs_.next()) {
			// HistoryBean hb = new HistoryBean();
			// hb.setPurchased_total(rs_.getInt("1"));
			// }
			if (rs_.next()) {
				bean = new HistoryBean();
				bean.setQuantity(rs_.getInt("order_amt"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return bean;
	}


}