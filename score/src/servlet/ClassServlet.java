package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassDao;
import dao.GradeDao;

public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String grade = request.getParameter("grade");

		GradeDao gradeDao = new GradeDao();
		int gradeId = gradeDao.findGradeIdByGrade(grade);

		ClassDao classDao = new ClassDao();
		List<String> list = classDao.findClassNosByGradeId(gradeId);
		
		String s;
		for (int i = 0; i < list.size(); i++) {
			s = (String) list.get(i);
			out.println("<option>" + s + "</option>");
		}
	}
}
