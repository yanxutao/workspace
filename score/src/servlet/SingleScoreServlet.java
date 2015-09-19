package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClassDao;
import dao.GradeDao;
import dao.ScoreDao;
import score.Statistic;
import bean.SingleScoreBean;

public class SingleScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int courseId = Integer.valueOf(request.getParameter("courseId"));
		String grade = request.getParameter("grade");
		String classNo = request.getParameter("classNo");

		ScoreDao scoreDao = new ScoreDao();

		List<Double> score = new ArrayList<Double>();
		if (grade.equals("")) {
			score = scoreDao.findScoreByCourseId(courseId);
		} else if (classNo.equals("")) {
			GradeDao gradeDao = new GradeDao();
			int gradeId = gradeDao.findGradeIdByGrade(grade);
			score = scoreDao.findScoreByGradeIdAndCourseId(gradeId, courseId);
		} else {
			ClassDao classDao = new ClassDao();
			int classId = classDao.findClassIdByClassNo(classNo);
			score = scoreDao.findScoreByClassIdAndCourseId(classId, courseId);
		}

		SingleScoreBean singleScore = Statistic.getSingleScore(score);

		HttpSession session = request.getSession();
		session.setAttribute("singleScore", singleScore);

		response.sendRedirect(request.getContextPath() + "/singlescore.jsp");
	}

}
