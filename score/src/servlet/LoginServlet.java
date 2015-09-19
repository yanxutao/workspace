package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserDao userDao = new UserDao();
		boolean result = userDao.findUserByUsernameAndPassword(username,
				password);
		if (result) {
			session.setAttribute("username", username);
			response.sendRedirect(request.getContextPath() + "/single.jsp");
			return;
		} else {
			session.setAttribute("loginMsg", "用户名或密码错误");
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
	}
}