package shopping;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class itemInfoServlet
 */
@WebServlet("/itemInfoServlet")
public class itemInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public itemInfoServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// GETメソッドのパラメータ名を取得
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		System.out.println(name);
		System.out.println(code);
		// 一週間分の株価情報
		ArrayList<weeklyStockBean> ws_bean = infoDao.getWeeklyStock(name, code);
		request.setAttribute("ws_bean", ws_bean);

		ArrayList<ItemBean> item_bean = infoDao.getItemInfo(name, code);
		request.setAttribute("item_bean", item_bean);

		for (ItemBean bean : item_bean) {
			if (bean.getKanriSeiri() != null) {
				request.setAttribute("errMsg", "この銘柄は" + bean.getKanriSeiri() + "です。");
			}
		}

		// 詳細画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("itemInfo.jsp");
		rd.forward(request, response);
	}
}
