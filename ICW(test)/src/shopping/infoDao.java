package shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class infoDao {
	private static String url = "jdbc:postgresql://localhost:5432/meigaradb";
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

	public static ArrayList<ItemBean> getItemInfo(String name, String code) {
		String sql = "select * from meigara inner join stock on meigara.meigara_id = stock.meigara_id where meigara.meigara_name = ? or meigara.meigara_code = ? ";
		ArrayList<ItemBean> item_bean = new ArrayList<>();
  System.out.println(name);
		System.out.println(name);

		try (Connection con = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, code);
			ResultSet rs = pstmt.executeQuery();


			while (rs.next()) {
				ItemBean ib = new ItemBean();
				ib.setItemCode(rs.getString("meigara_code"));
				ib.setItemName(rs.getString("meigara_name"));
				ib.setMarket(rs.getString("market"));
				ib.setIndustry(rs.getString("industry"));
				ib.setPrice(rs.getInt("stock_price"));
				ib.setOpen(rs.getInt("open"));
				ib.setHigh(rs.getInt("high"));
				ib.setLow(rs.getInt("low"));
				ib.setAsk(rs.getInt("ask"));
				ib.setBid(rs.getInt("bid"));
				ib.setYearly_high(rs.getInt("yearly_high"));
				ib.setYearly_low(rs.getInt("yearly_low"));
				ib.setItemId(rs.getString("meigara_id"));
				ib.setUrikai(rs.getInt("meigara_unit"));
				ib.setKanriSeiri(rs.getString("kanri_seiri"));
				item_bean.add(ib);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item_bean;

	}
	
	public static ArrayList<ItemBean> getItemInfo2(String code) {
		String sql = "select * from meigara inner join stock on meigara.meigara_id = stock.meigara_id where meigara.meigara_code = ? ";
		ArrayList<ItemBean> item_bean = new ArrayList<>();

System.out.println(code);
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemBean ib = new ItemBean();
				ib.setItemCode(rs.getString("meigara_code"));
				ib.setItemName(rs.getString("meigara_name"));
				ib.setMarket(rs.getString("market"));
				ib.setIndustry(rs.getString("industry"));
				ib.setPrice(rs.getInt("stock_price"));
				ib.setOpen(rs.getInt("open"));
				ib.setHigh(rs.getInt("high"));
				ib.setLow(rs.getInt("low"));
				ib.setAsk(rs.getInt("ask"));
				ib.setBid(rs.getInt("bid"));
				ib.setYearly_high(rs.getInt("yearly_high"));
				ib.setYearly_low(rs.getInt("yearly_low"));
				ib.setItemId(rs.getString("meigara_id"));
				ib.setUrikai(rs.getInt("meigara_unit"));
				item_bean.add(ib);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item_bean;

	}
	public static ArrayList<ItemBean> getItemInfo3(String name) {
		String sql = "select * from meigara inner join stock on meigara.meigara_id = stock.meigara_id where meigara.meigara_name = ? ";
		ArrayList<ItemBean> item_bean = new ArrayList<>();
		System.out.println(name);
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemBean ib = new ItemBean();
				ib.setItemCode(rs.getString("meigara_code"));
				ib.setItemName(rs.getString("meigara_name"));
				ib.setMarket(rs.getString("market"));
				ib.setIndustry(rs.getString("industry"));
				ib.setPrice(rs.getInt("stock_price"));
				ib.setOpen(rs.getInt("open"));
				ib.setHigh(rs.getInt("high"));
				ib.setLow(rs.getInt("low"));
				ib.setAsk(rs.getInt("ask"));
				ib.setBid(rs.getInt("bid"));
				ib.setYearly_high(rs.getInt("yearly_high"));
				ib.setYearly_low(rs.getInt("yearly_low"));
				ib.setItemId(rs.getString("meigara_id"));
				ib.setUrikai(rs.getInt("meigara_unit"));
				item_bean.add(ib);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item_bean;

	}
	public static ArrayList<weeklyStockBean> getWeeklyStock(String name, String code) {
		System.out.println(name);
		String sql = "select * from meigara inner join weekly_stock on meigara.meigara_id = weekly_stock.meigara_id where meigara.meigara_name = ? or meigara.meigara_code = ? ";
		ArrayList<weeklyStockBean> ws_bean = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(url, user, password)) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, code);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				weeklyStockBean wsb = new weeklyStockBean();
				wsb.setMeigaraId(rs.getString("meigara_id"));
				wsb.setSevenDaysAgo(rs.getInt("sevendaysago"));
				wsb.setSixDaysAgo(rs.getInt("sixdaysago"));
				wsb.setFiveDaysAgo(rs.getInt("fivedaysago"));
				wsb.setFourDaysAgo(rs.getInt("fourdaysago"));
				wsb.setThreeDaysAgo(rs.getInt("threedaysago"));
				wsb.setTwoDaysAgo(rs.getInt("twodaysago"));
				wsb.setOneDayAgo(rs.getInt("onedayago"));
				ws_bean.add(wsb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ws_bean;
	}

}
