package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GradeDao;

import java.util.List;

public class GradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		GradeDao gradeDao = new GradeDao();
		List<String> list = gradeDao.findAllGrade();
		
		String s;
		for (int i = 0; i < list.size(); i++) {
			s = (String)list.get(i);
			out.println("<option>" + s + "</option>");
		}
	}
}
