package shopping;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import login.LoginUserBean;

@WebServlet(urlPatterns = { "/ResultServlet" })
public class ResultServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // ログインid取得
        String user_id = ((LoginUserBean) request.getSession().getAttribute("login_user_bean")).getId();

        // 購入確認画面(purchase_confirm.jsp)にセットしていた値を取得(hiddenパラメータ)
        String item_id = request.getParameter("code");
        String item_name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int purchased_total = Integer.parseInt(request.getParameter("purchased_total"));
        String select = request.getParameter("select");
        String choice = request.getParameter("choice");
        String dateinfo = request.getParameter("dateinfo");
        String date = request.getParameter("date");
        String orderstatus = request.getParameter("orderstatus");
        String sellbuy = request.getParameter("sellbuy");
        String category = request.getParameter("category");
        System.out.println(sellbuy);
        System.out.println(orderstatus);
        System.out.println(category);

        HistoryDao dao = null;
        try {
            /*
             * 商品ID と購入数を元にDBを更新
             */
            dao = new HistoryDao();

            dao.updateHistory(user_id, item_name, item_id, quantity, purchased_total, select, choice, dateinfo, date,
                    orderstatus, sellbuy, category);// 履歴の更新,あとでBeanに追加する
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (dao != null) {
                dao.close();
            }
        }
        // 商品結果画面に移動
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
