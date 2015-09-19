package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import score.Statistic;
import dao.ClassDao;
import dao.CourseDao;
import dao.GradeDao;
import dao.ScoreDao;
import dao.StudentDao;
import entity.Score;
import bean.IndividualScoreBean;
import bean.RankBean;

public class IndividualScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String studentNo = request.getParameter("studentNo");
		String classNo = request.getParameter("classNo");
		String grade = request.getParameter("grade");

		StudentDao studentDao = new StudentDao();
		int studentId = studentDao.findStudentIdByStudentNo(studentNo);
		
		ClassDao classDao = new ClassDao();
		int classId = classDao.findClassIdByClassNo(classNo);
		
		GradeDao gradeDao = new GradeDao();
		int gradeId = gradeDao.findGradeIdByGrade(grade);
		
		ScoreDao scoreDao = new ScoreDao();
		List<Score> score = scoreDao.findScoreByStudentId(studentId);
		
		CourseDao courseDao = new CourseDao();
		List<String> courseName = new ArrayList<String>();
		for (int i = 0; i < score.size(); i++) {
			courseName.add(courseDao.findCourseNameByCourseId(score.get(i).getCourseId()));
		}
		
		List<Double> studentScore = new ArrayList<Double>();
		List<Double> classScore = new ArrayList<Double>();
		List<Double> gradeScore = new ArrayList<Double>();
		List<RankBean> classRank = new ArrayList<RankBean>();
		List<RankBean> gradeRank = new ArrayList<RankBean>();
		
		for (int i = 0; i < score.size(); i++) {
			Score s = score.get(i);
			studentScore.add(s.getScore());
			
			RankBean rank;
			
			classScore = scoreDao.findScoreByClassIdAndCourseId(classId, s.getCourseId());
			rank = Statistic.getRank(classScore, s.getScore());
			classRank.add(rank);
			
			gradeScore = scoreDao.findScoreByGradeIdAndCourseId(gradeId, s.getCourseId());
			rank = Statistic.getRank(gradeScore, s.getScore());
			gradeRank.add(rank);
		}

		IndividualScoreBean individualScore = new IndividualScoreBean();
		individualScore.setCourseName(courseName);
		individualScore.setScore(studentScore);
		individualScore.setClassRank(classRank);
		individualScore.setGradeRank(gradeRank);
		
		HttpSession session = request.getSession();
		session.setAttribute("individualScore", individualScore);
		
		response.sendRedirect(request.getContextPath() + "/individualscore.jsp");
	}
}
