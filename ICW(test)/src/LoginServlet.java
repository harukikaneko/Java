

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("loginhome.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		User employee = EmployeeDao.loginEmployee(login,password);
		
		if(employee == null) {
			response.sendRedirect("loginhome.jsp");
		}else {
			
			HttpSession session = request.getSession();
			session.setAttribute("employee", employee);
			response.sendRedirect("mypage.jsp");
		}
		
	}
	

}
