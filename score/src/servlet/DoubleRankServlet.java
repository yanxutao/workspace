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
import dao.RankDao;
import entity.Course;
import score.Statistic;

public class DoubleRankServlet extends HttpServlet {
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
		
		RankDao rankDao = new RankDao();
		
		List<Double> rank1 = new ArrayList<Double>();
		if (grade.equals("")) {
			rank1 = rankDao.findRankByCourseId(courseId);	
		} else if (classNo.equals("")) {
			GradeDao gradeDao = new GradeDao();
			int gradeId = gradeDao.findGradeIdByGrade(grade);
			rank1 = rankDao.findRankByGradeIdAndCourseId(gradeId, courseId);
		} else {
			ClassDao classDao = new ClassDao();
			int classId = classDao.findClassIdByClassNo(classNo);
			rank1 = rankDao.findRankByClassIdAndCourseId(classId, courseId);
		}
		
		List<Double> relative = new ArrayList<Double>();
		for (int i = 0; i < course.size(); i++) {
			
			List<Double> rank2 = new ArrayList<Double>();
			if (grade.equals("")) {
				rank2 = rankDao.findRankByCourseId(course.get(i).getCourseId());	
			} else if (classNo.equals("")) {
				GradeDao gradeDao = new GradeDao();
				int gradeId = gradeDao.findGradeIdByGrade(grade);
				rank2 = rankDao.findRankByGradeIdAndCourseId(gradeId, course.get(i).getCourseId());
			} else {
				ClassDao classDao = new ClassDao();
				int classId = classDao.findClassIdByClassNo(classNo);
				rank2 = rankDao.findRankByClassIdAndCourseId(classId, course.get(i).getCourseId());
			}
			
			double r = Statistic.pearson(rank1, rank2);
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
