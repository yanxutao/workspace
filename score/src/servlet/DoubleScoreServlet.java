package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClassDao;
import dao.CourseDao;
import dao.GradeDao;
import dao.ScoreDao;
import entity.Course;
import score.Statistic;

public class DoubleScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int courseId = Integer.valueOf(request.getParameter("course_no"));
		String grade = request.getParameter("grade");
		String classNo = request.getParameter("class_no");

		CourseDao courseDao = new CourseDao();
		List<Course> course = courseDao.findAllCourse();
		
		List<String> courseName = new ArrayList<String>();
		for (int i = 0; i < course.size(); i++) {
			courseName.add(course.get(i).getCourseName());
		}
		
		ScoreDao scoreDao = new ScoreDao();
		
		List<Double> score1 = new ArrayList<Double>();
		if (grade.equals("")) {
			score1 = scoreDao.findScoreByCourseId(courseId);	
		} else if (classNo.equals("")) {
			GradeDao gradeDao = new GradeDao();
			int gradeId = gradeDao.findGradeIdByGrade(grade);
			score1 = scoreDao.findScoreByGradeIdAndCourseId(gradeId, courseId);
		} else {
			ClassDao classDao = new ClassDao();
			int classId = classDao.findClassIdByClassNo(classNo);
			score1 = scoreDao.findScoreByClassIdAndCourseId(classId, courseId);
		}
		
		List<Double> relative = new ArrayList<Double>();
		for (int i = 0; i < course.size(); i++) {
			
			List<Double> score2 = new ArrayList<Double>();
			if (grade.equals("")) {
				score2 = scoreDao.findScoreByCourseId(course.get(i).getCourseId());	
			} else if (classNo.equals("")) {
				GradeDao gradeDao = new GradeDao();
				int gradeId = gradeDao.findGradeIdByGrade(grade);
				score2 = scoreDao.findScoreByGradeIdAndCourseId(gradeId, course.get(i).getCourseId());
			} else {
				ClassDao classDao = new ClassDao();
				int classId = classDao.findClassIdByClassNo(classNo);
				score2 = scoreDao.findScoreByClassIdAndCourseId(classId, course.get(i).getCourseId());
			}
			
			double r = Statistic.pearson(score1, score2);
			BigDecimal b = new BigDecimal(r);
			r = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			relative.add(r);
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("courseName", courseName);
		session.setAttribute("relative", relative);
		
		response.sendRedirect(request.getContextPath() + "/double.jsp");
	}

}
