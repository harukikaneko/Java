package shopping;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 商品・履歴検索処理
 *
 */
public class Shopping {
    public ItemBean getItem(String item_id) {
        ShoppingDao dao = null;
        ResultSet rs;
        ItemBean item_bean = null;

        try {
            // DAOクラスをインスタンス化
            dao = new ShoppingDao();
            // 現在の商品一覧を検索
            rs = dao.selectItem(item_id);

            // 検索結果を1レコードずつ処理
            while (rs.next()) {
                item_bean = new ItemBean();
                // 商品IDを設定
                item_bean.setItemId(rs.getString("meigara_id"));
                // 商品名を設定
                item_bean.setItemName(rs.getString("meigara_name"));
                // 商品価格を設定
                item_bean.setItemCode(rs.getString("meigara_code"));

                // 商品一覧を格納するBeanクラスをインスタンス化

                System.out.println("itembean: " + item_bean);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            // 処理終了時に各接続を解除
            if (dao != null) {
                dao.close();
            }
        }
        return item_bean;
    }

}
