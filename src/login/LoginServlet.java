package login;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

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

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @SuppressWarnings("null")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String btn = request.getParameter("submit");

        HttpSession session = request.getSession();
        if (session == null) {
            session.setMaxInactiveInterval(600);
            response.sendRedirect("loginFailed.jsp");
        }
        // 10分何もしなかった場合セッションを切る

        RequestDispatcher rd;

        if (btn.equals("ログイン")) {
            String id = request.getParameter("id");
            String pass = request.getParameter("pass");

            LoginDB login = new LoginDB();

            LoginUserBean bean = login.getUserData(id, pass);

            // ③-2 モデルの情報を判定
            if (bean != null) {
                // ③-2-1 モデルの情報が存在する場合はsetAttributeメソッドを使ってセッションに情報をセット
                // モデル（ユーザ情報）
                session.setAttribute("login_user_bean", bean);
                // ログイン状態
                session.setAttribute("login_state", "login");

                // ③-2-2 つぎに表示させる画面（ビュー）を指定
                rd = request.getRequestDispatcher("myPage.jsp");
            } else {
                // ③-3 モデルの情報が存在しない（IDに紐づくユーザ情報がない）場合
                // ③-3-1 つぎに表示させる画面（ビュー）を指定
                request.setAttribute("errMsg", "ユーザー名またはパスワードが違います");
                rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
                System.out.println("無効な値です");
            }

            rd.forward(request, response);

        } else if (btn.equals("logout")) {
            // ④ クリックされたボタンが「logout」の場合はログアウト処理（セッションの破棄）を実施
            session.removeAttribute("login_state");
            session.removeAttribute("login_user_bean");
            response.sendRedirect("index.jsp");
        }

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
