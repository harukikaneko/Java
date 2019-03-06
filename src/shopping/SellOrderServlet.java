package shopping;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SellOrderServlet
 */
@WebServlet("/SellOrderServlet")
public class SellOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellOrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null) {
			session.setMaxInactiveInterval(600);
			response.sendRedirect("loginFailed.jsp");
		}
		request.setCharacterEncoding("UTF-8");
		// String name = "";
		String name = request.getParameter("name");
		System.out.println(name);

		// 購入ボタンがクリックされている場合は「購入」のパラメータが取得
		// if ("注文".equals(request.getParameter(name))) {
		// code = name;
		// }
		ArrayList<ItemBean> item_bean = infoDao.getItemInfo3(name);
		request.setAttribute("item_bean", item_bean);

		// 詳細画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("sell.jsp");
		rd.forward(request, response);

	}

}
