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

@WebServlet("/HistoryInfoServlet")
public class HistoryInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HistoryInfoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if (session == null) {
			session.setMaxInactiveInterval(600);
			response.sendRedirect("loginFailed.jsp");
		}

		RequestDispatcher rd;
		// GETメソッドのパラメータ名を取得
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		System.out.println(name);
		ArrayList<ItemBean> item_bean = infoDao.getItemInfo(name, code);
		request.setAttribute("item_bean", item_bean);

		ArrayList<HistoryBean> history_beans = HistoryDao.selectHistory2(name);
		request.setAttribute("history", history_beans);

		// 詳細画面に移動
		rd = request.getRequestDispatcher("historyInfo.jsp");
		rd.forward(request, response);

	}
}
