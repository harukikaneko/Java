package shopping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.LoginUserBean;

/**
 * Servlet implementation class favServlet
 */
@WebServlet("/favServlet")
public class favServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ログインしているか検証
		HttpSession session = request.getSession();

		String user_id = ((LoginUserBean) request.getSession().getAttribute("login_user_bean")).getId();
		String name = request.getParameter("item_name");
		String data_condition = request.getParameter("data_condition");
		String code = request.getParameter("code");
		System.out.println(data_condition);
		System.out.println(name);

		favDao dao = null;
		try {
			/*
			 * 商品ID と購入数を元にDBを更新
			 */
			dao = new favDao();

			favDao.updateFav(name, user_id);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
		// 削除機能をあとでつける

		ArrayList<ItemBean> item_bean = ShoppingDao.getItemBeanListByName(name, code);
		request.setAttribute("item_bean", item_bean);
		request.setAttribute("msg", "登録しました");
		request.getRequestDispatcher("itemList.jsp").forward(request, response);
	}

}
