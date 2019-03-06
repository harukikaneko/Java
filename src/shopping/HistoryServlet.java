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

import login.LoginUserBean;

/**
 *
 * @author nabana
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {

    /**
     * 
     */
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
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session == null) {
            session.setMaxInactiveInterval(600);
            response.sendRedirect("loginFailed.jsp");
        }

        RequestDispatcher rd;

        // session からユーザID を取得
        String user_id = ((LoginUserBean) session.getAttribute("login_user_bean")).getId();

        ArrayList<HistoryBean> history_beans = HistoryDao.selectHistory(user_id);

        if (history_beans.isEmpty()) {
            System.out.println("該当項目がありません");
            rd = request.getRequestDispatcher("historyError.jsp");
            rd.forward(request, response);
        } else {
            // リクエストスコープにセットして画面移動
            request.setAttribute("history", history_beans);
            request.setAttribute("id", user_id);
            rd = request.getRequestDispatcher("history.jsp");
            rd.forward(request, response);
        }
    }
}
