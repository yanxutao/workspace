package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.RankBean;
import score.Statistic;
import dao.ClassDao;
import dao.CourseDao;
import dao.GradeDao;
import dao.RankDao;
import dao.ScoreDao;
import dao.StudentDao;
import entity.Course;

public class UpdateRankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GradeDao gradeDao = new GradeDao();
		ClassDao classDao = new ClassDao();
		StudentDao studentDao = new StudentDao();
		ScoreDao scoreDao = new ScoreDao();
		RankDao rankDao = new RankDao();
		
		CourseDao courseDao = new CourseDao();
		List<Course> course = courseDao.findAllCourse();
		
		List<Integer> courseId = new ArrayList<Integer>();
		for (int i = 0; i < course.size(); i++) {
			courseId.add(course.get(i).getCourseId());
		}
			
		for (int i = 0; i < courseId.size(); i++) {
			
			List<Integer> gradeIds = gradeDao.findAllGradeId();
			for (Integer gradeId : gradeIds) {
				
				List<Double> gradeScore = scoreDao.findScoreByGradeIdAndCourseId(gradeId, courseId.get(i));
				
				List<Integer> classIds = classDao.findClassIdsByGradeId(gradeId);
				for (Integer classId : classIds) {
				
					List<Integer> studentIds = studentDao.findStudentIdsByClassId(classId);
					for (Integer studentId : studentIds) {
						
						double score = scoreDao.findScoreByStudentIdAndCourseId(studentId, courseId.get(i));
						RankBean rank = Statistic.getRank(gradeScore, score);
						double r = intRankToDoubleRank(rank.getIntHigher() + 1, gradeScore.size());
						rankDao.insertRank(studentId, courseId.get(i).intValue(), r);
						
					}
				}
			}
		}

		//HttpSession session = request.getSession();
		//session.setAttribute("msg", msg);
		response.sendRedirect(request.getContextPath() + "/upload.jsp");
	}

	double intRankToDoubleRank(int count, int size) {
		double doubleRank = count * 100.0 / size;
		BigDecimal b = new BigDecimal(doubleRank);
		doubleRank = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return doubleRank;
	}
	
}
