package shopping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.LoginUserBean;

/**
 * Servlet implementation class favDelete
 */
@WebServlet("/favDelete")
public class favDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public favDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	String user_id = ((LoginUserBean) request.getSession().getAttribute("login_user_bean")).getId();
	String name = request.getParameter("name");
	System.out.println(name);
	

	
		favDao dao = null;
		try {
			/*
			 * 商品ID と購入数を元にDBを更新
			 */
			dao = new favDao();

			favDao.deleteFav(name, user_id);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
//		削除機能をあとでつける
		
		
		
		request.setAttribute("msg", "削除しますた");
		request.getRequestDispatcher("favlistServlet").forward(request, response);
		}

	



	{
}}
