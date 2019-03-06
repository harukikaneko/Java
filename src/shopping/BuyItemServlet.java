package shopping;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.concurrent.CountDownLatch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;

import account.AccountBean;
import account.AccountDao;
import login.LoginUserBean;;

/**
 * 商品購入ページ処理クラス.
 */
@WebServlet("/BuyItemServlet")
public class BuyItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public BuyItemServlet() {
		super();
	}

	/**
	 * GETメソッドで呼び出された場合の処理
	 *
	 * @param request
	 * @param response
	 * @throws javax.servlet.ServletException
	 * @throws java.io.IOException
	 */
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
		int add1 = (int) (1000 * 1.08);
		int add2 = (int) (1500 * 1.08);
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		// int purchased_total = price * quantity * urikai + add1;
		int purchased_total = urikai * quantity;
		String choice = request.getParameter("choice");
		String select = request.getParameter("select");
		String category = request.getParameter("category");
		System.out.println(category);

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
		System.out.println(user_id);
		AccountBean bean = AccountDao.getAccountBean1(user_id);
		int balance = bean.getBalance();
		System.out.println(balance);
		System.out.println(purchased_total);
		// 購入合計 < 口座残高
		if (purchased_total < balance) {

			HistoryBean bean1 = HistoryDao.getHistory(user_id, d);
			int day_count = bean1.getDay_count();
			System.out.println(day_count);

			if (purchased_total + day_count < 30000000) {

				// // 商品一覧をリクエストスコープの属性にセット

				request.setAttribute("choice", choice);
				request.setAttribute("select", select);
				request.setAttribute("name", name);
				request.setAttribute("code", code);
				request.setAttribute("quantity", quantity);
				request.setAttribute("orderstatus", orderstatus);
				request.setAttribute("sellbuy", sellbuy);
				request.setAttribute("category", category);
				System.out.println(sellbuy);
				// 注文確認画面に移動
				RequestDispatcher rd = request.getRequestDispatcher("purchase_confirm.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("errMsg2", "取引上限を超過しています。");
				RequestDispatcher rd = request.getRequestDispatcher("orderServlet?name=" + name);
				rd.forward(request, response);
				System.out.println("無効な値です");
			}

		} else {
			request.setAttribute("name", name);
			request.setAttribute("errMsg", "口座残高が不足しています。");
			RequestDispatcher rd = request.getRequestDispatcher("orderServlet?name=" + name);
			rd.forward(request, response);
			System.out.println("無効な値です");
		}
	}
}
