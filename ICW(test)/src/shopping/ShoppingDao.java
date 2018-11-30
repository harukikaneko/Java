package shopping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ショッピング風DAOクラス. DBの商品(item)テーブルと購入履歴(history)のテーブルを扱う
 */
public class ShoppingDao {
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

    public ShoppingDao() throws SQLException {
        // JDBCドライバのロード
        
            // データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
            this.con_ = DriverManager.getConnection(url,user,password);
    }
    public ResultSet selectItem(String item_id) throws SQLException {
        // SQL文を生成
        this.ps_ = this.con_.prepareStatement(
                "select meigara.meigara_id, meigara.meigara_name, meigara.code,  from meigara inner join stock on meigara.meigara_id = stock.meigara_id"
        );

        // SQLを実行
        this.rs_ = this.ps_.executeQuery();

        return this.rs_;
    }
  
 
    

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

	public static ArrayList<ItemBean> getItemBeanListByName(String name, String code) {
		String sql = "SELECT * FROM meigara WHERE meigara_name LIKE '%' || ? || '%' or meigara_code = ? ";
		ArrayList<ItemBean> item_bean = new ArrayList<>();
		

		try(Connection con = DriverManager.getConnection(url, user, password)){
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, code);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				ItemBean ib = new ItemBean();
				ib.setItemCode(rs.getString("meigara_code"));
				ib.setItemName(rs.getString("meigara_name"));
				ib.setMarket(rs.getString("market"));
				ib.setIndustry(rs.getString("industry"));
				ib.setItemId(rs.getString("meigara_id"));
				ib.setKanriSeiri(rs.getString("kanri_seiri"));
				item_bean.add(ib);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return item_bean;

	}


	
}