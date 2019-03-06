package shopping;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.AccountBean;
import account.AccountDao;
import login.LoginUserBean;

/**
 * Servlet implementation class SellItemServlet
 */
@WebServlet("/SellItemServlet")
public class SellItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SellItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(YEAR);
		int month = cal.get(MONTH) + 1;
		int day = cal.get(DATE);
		String d = year + "-" + month + "-" + day;
		// 日付
		HttpSession session = request.getSession();
		session.setAttribute("date", d);
		// 注文番号
		NumberFormat nFormat = NumberFormat.getInstance();
		nFormat.setMinimumIntegerDigits(2);
		String date = nFormat.format(day);
		String m = nFormat.format(month);

		DecimalFormat dformat = new DecimalFormat("000000");
		int a = HistoryDao.getDayDeal(d) + 1;
		System.out.println(a);
		String dateinfo = year + m + date + "-" + dformat.format(a);
		;
		session.setAttribute("dateinfo", dateinfo);

		// GETメソッドのパラメータ名を取得

		int price = Integer.parseInt(request.getParameter("price"));
		int urikai = Integer.parseInt(request.getParameter("urikai"));
		int add1 = 1000;
		int add2 = 1500;
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		// int purchased_total = price * quantity * urikai + add1;
		int purchased_total = urikai * quantity;
		String choice = request.getParameter("choice");
		String select = request.getParameter("select");

		// int purchased_total =
		// Integer.parseInt(request.getParameter("purchased_total"));

		String name = request.getParameter("name");
		String code = request.getParameter("code");
		String orderstatus = request.getParameter("orderstatus");
		String sellbuy = request.getParameter("sellbuy");
		//
		//

		if (select.equals("成行")) {
			purchased_total = purchased_total * urikai + add1;
			System.out.println(purchased_total);
			request.setAttribute("purchased_total", purchased_total);
		} else {
			int text = Integer.parseInt(request.getParameter("sashine"));
			purchased_total = purchased_total * text + add2;
			request.setAttribute("purchased_total", purchased_total);
		}
		// 口座
		String user_id = ((LoginUserBean) session.getAttribute("login_user_bean")).getId();
		HistoryBean bean1 = HistoryDao.getHistory2(user_id, d);
		int quantity1 = bean1.getQuantity();
		System.out.println(quantity1);

		if (quantity1 < quantity) {
			request.setAttribute("errMsg2", "空売りはできません。");
			RequestDispatcher rd = request.getRequestDispatcher("SellOrderServlet?name=" + name);
			rd.forward(request, response);
			System.out.println("無効な値です");
		}

		request.setAttribute("choice", choice);
		request.setAttribute("select", select);
		request.setAttribute("name", name);
		request.setAttribute("code", code);
		request.setAttribute("orderstatus", orderstatus);
		request.setAttribute("sellbuy", sellbuy);
		request.setAttribute("quantity", quantity);
		// 注文確認画面に移動
		RequestDispatcher rd = request.getRequestDispatcher("sell_confirm.jsp");
		rd.forward(request, response);

	}
}
