package logincahnge;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginChangeSarvlet
 */
@WebServlet("/LoginChangeSarvlet")
public class LoginChangeSarvlet extends HttpServlet {
       
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ログインしているか検証
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String id2 = request.getParameter("id2");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");
		System.out.println(id);
		 LoginChangeDao dao = null;
	        try {
	            /*
	             * 商品ID と購入数を元にDBを更新
	             */
	           dao = new LoginChangeDao();
	            
	           LoginChangeDao.changeLogin(id, id2, pass, pass2);
	        } catch (SQLException sqle) {
	            sqle.printStackTrace();
	        } finally {
	            if (dao != null) {
	                dao.close();
	            }
	        }
	        if (dao != null) {
	        	request.getRequestDispatcher("change.jsp").forward(request, response);
	        }else {
               
                // ③-3 モデルの情報が存在しない（IDに紐づくユーザ情報がない）場合
                // ③-3-1 つぎに表示させる画面（ビュー）を指定
            	request.setAttribute("errMsg", "ユーザー口座番号が違います");
            	RequestDispatcher rd;
                rd = request.getRequestDispatcher("FirstLogin.jsp");
                rd.forward(request, response);
                System.out.println("無効な値です");
            }
	       

	   		// 画面遷移
	   		
	    }
 
	}