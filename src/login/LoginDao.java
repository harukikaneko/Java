package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
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

    public LoginDao() throws SQLException {

        // データベースと接続（本来はユーザやパスワードも別管理にしておくのが理想）
        this.con_ = DriverManager.getConnection(url, user, password);

    }

    /**
     * データベースから指定されたIDとパスワードを使ってユーザ情報を検索します.
     *
     * @param id   ログインID
     * @param pass パスワード
     * @return ユーザ情報（ResultSet）
     * @throws SQLException
     */
    public ResultSet selectUser(String id, String pass) throws SQLException {
        // SQL文を生成
        this.ps_ = this.con_.prepareStatement("select * from users where pass_id = ? and user_pass = ?");

        // 生成したSQL文の「？」の部分にIDとパスワードをセット
        this.ps_.setString(1, id);
        this.ps_.setString(2, pass);

        // SQLを実行
        this.rs_ = this.ps_.executeQuery();

        System.out.println(id);

        return this.rs_;
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
}
