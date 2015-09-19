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
import dao.ScoreDao;
import entity.Course;
import score.Statistic;

public class DoubleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pors = request.getParameter("p1");
		String grade = request.getParameter("p2");
		String classNo = request.getParameter("p3");
		String course1 = request.getParameter("p4");
		String course2 = request.getParameter("p5");
		
		CourseDao courseDao = new CourseDao();
		List<Course> course = courseDao.findAllCourse();
		
		List<String> courseName = new ArrayList<String>();
		for (int i = 0; i < course.size(); i++) {
			courseName.add(course.get(i).getCourseName());
		}
		
		if (pors.equals("Pearson")) {
			if (course1.equals("")) {
				return;
			} else if (course2.equals("")) {
				
				int courseId = Integer.valueOf(course1);
				
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
				
				String caption = courseName.get(courseId - 1) + "与所有科目的" + pors + "相关系数";
				HttpSession session = request.getSession();
				session.setAttribute("courseName", courseName);
				session.setAttribute("caption", caption);
				session.setAttribute("relative", relative);
				
				response.sendRedirect(request.getContextPath() + "/double1.jsp");
			} else {
				int c1 = Integer.valueOf(course1);
				int c2 = Integer.valueOf(course2);
				
				ScoreDao scoreDao = new ScoreDao();
				
				List<Double> score1 = new ArrayList<Double>();
				List<Double> score2 = new ArrayList<Double>();
				if (grade.equals("")) {
					score1 = scoreDao.findScoreByCourseId(c1);
					score2 = scoreDao.findScoreByCourseId(c2);
				} else if (classNo.equals("")) {
					GradeDao gradeDao = new GradeDao();
					int gradeId = gradeDao.findGradeIdByGrade(grade);
					score1 = scoreDao.findScoreByGradeIdAndCourseId(gradeId, c1);
					score2 = scoreDao.findScoreByGradeIdAndCourseId(gradeId, c2);
				} else {
					ClassDao classDao = new ClassDao();
					int classId = classDao.findClassIdByClassNo(classNo);
					score1 = scoreDao.findScoreByClassIdAndCourseId(classId, c1);
					score2 = scoreDao.findScoreByClassIdAndCourseId(classId, c2);
				}
				
				double r = Statistic.pearson(score1, score2);
				BigDecimal b = new BigDecimal(r);
				r = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				
				HttpSession session = request.getSession();
				session.setAttribute("c1", courseName.get(c1 - 1));
				session.setAttribute("c2", courseName.get(c2 - 1));
				session.setAttribute("r", r);
				session.setAttribute("x", score1);
				session.setAttribute("y", score2);
				
				response.sendRedirect(request.getContextPath() + "/double2.jsp");
			}
		} else if (pors.equals("Spearman")) {
			if (course1.equals("")) {
				return;
			} else if (course2.equals("")) {
				
				int courseId = Integer.valueOf(course1);
				
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
				
				String caption = courseName.get(courseId - 1) + "与所有科目的" + pors + "相关系数";
				HttpSession session = request.getSession();
				session.setAttribute("courseName", courseName);
				session.setAttribute("caption", caption);
				session.setAttribute("relative", relative);
				
				response.sendRedirect(request.getContextPath() + "/double1.jsp");
			} else {
				int c1 = Integer.valueOf(course1);
				int c2 = Integer.valueOf(course2);
				
				RankDao rankDao = new RankDao();
				
				List<Double> rank1 = new ArrayList<Double>();
				List<Double> rank2 = new ArrayList<Double>();
				if (grade.equals("")) {
					rank1 = rankDao.findRankByCourseId(c1);
					rank2 = rankDao.findRankByCourseId(c2);
				} else if (classNo.equals("")) {
					GradeDao gradeDao = new GradeDao();
					int gradeId = gradeDao.findGradeIdByGrade(grade);
					rank1 = rankDao.findRankByGradeIdAndCourseId(gradeId, c1);
					rank2 = rankDao.findRankByGradeIdAndCourseId(gradeId, c2);
				} else {
					ClassDao classDao = new ClassDao();
					int classId = classDao.findClassIdByClassNo(classNo);
					rank1 = rankDao.findRankByClassIdAndCourseId(classId, c1);
					rank2 = rankDao.findRankByClassIdAndCourseId(classId, c2);
				}
				
				double r = Statistic.pearson(rank1, rank2);
				BigDecimal b = new BigDecimal(r);
				r = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				HttpSession session = request.getSession();
				session.setAttribute("c1", courseName.get(c1 - 1));
				session.setAttribute("c2", courseName.get(c2 - 1));
				session.setAttribute("r", r);
				session.setAttribute("x", rank1);
				session.setAttribute("y", rank2);
				
				response.sendRedirect(request.getContextPath() + "/double2.jsp");
			}
		}
		

	}

}
