package shopping;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/QueryListByNameServlet")
public class QueryListByNameServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("search_name");
		String code = req.getParameter("search_name");

		ArrayList<ItemBean> item_bean = ShoppingDao.getItemBeanListByName(name, code);

		if (item_bean.isEmpty()) {
			req.setAttribute("errMsg", "該当項目がありません");
			req.getRequestDispatcher("search.jsp").forward(req, resp);
		} else {
			req.setAttribute("item_bean", item_bean);
			req.getRequestDispatcher("itemList.jsp").forward(req, resp);
			req.setAttribute("search_name", name);
			req.setAttribute("search_code", code);
		}

	}
}
