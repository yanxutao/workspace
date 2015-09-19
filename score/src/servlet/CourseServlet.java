package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;
import entity.Course;

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		CourseDao courseDao = new CourseDao();
		List<Course> list = courseDao.findAllCourse();
		
		Course course;
		int coursId;
		String courseName;
		
		for (int i = 0; i < list.size(); i++) {
			
			course = list.get(i);

			coursId = course.getCourseId();
			courseName = course.getCourseName();

			out.println("<option id='c" + coursId + "' value='" + coursId + "'>" + courseName + "</option>");
		}
	}
}
