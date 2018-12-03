package shopping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.LoginUserBean;



@WebServlet("/cancelServlet")
public class cancelServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ログインしているか検証
		HttpSession session = request.getSession();

		String dateinfo = request.getParameter("dateinfo");
		System.out.println(dateinfo);
		 HistoryDao dao = null;
	        try {
	            /*
	             * 商品ID と購入数を元にDBを更新
	             */
	           dao = new HistoryDao();
	            
	           HistoryDao.cancelHistory(dateinfo);
	        } catch (SQLException sqle) {
	            sqle.printStackTrace();
	        } finally {
	            if (dao != null) {
	                dao.close();
	            }
	        }
	        String user_id = ((LoginUserBean) session.getAttribute("login_user_bean")).getId();
	        
	        ArrayList<HistoryBean> history_beans = HistoryDao.selectHistory(user_id);
	   		// 注文DTOを取得
	   		request.setAttribute("history", history_beans);

	   		// 画面遷移
	   		request.getRequestDispatcher("history.jsp").forward(request, response);
	    }
 
	}
