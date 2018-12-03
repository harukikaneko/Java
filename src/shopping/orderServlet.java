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
@WebServlet("/orderServlet")
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public orderServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String name = "";
		String code = request.getParameter("code");
		
		// 購入ボタンがクリックされている場合は「購入」のパラメータが取得
		//if ("注文".equals(request.getParameter(name))) {
		//	code = name;
		//}
		ArrayList<ItemBean> item_bean = infoDao.getItemInfo2(code);
		request.setAttribute("item_bean", item_bean);

		// 詳細画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("order.jsp");
		rd.forward(request, response);

	}

}
