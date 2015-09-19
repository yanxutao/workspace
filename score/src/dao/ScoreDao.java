package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Score;
import score.HibernateSessionFactory;

public class ScoreDao {
	private Session session;
	private Transaction transaction;
	private Query query;
	private String hql;
	
	public ScoreDao() {
	}
	
	public void insertScore(int studentId, int courseId, double score) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "delete from Score where studentId = :studentId and courseId = :courseId";
		query = session.createQuery(hql);
		query.setInteger("studentId", studentId);
		query.setInteger("courseId", courseId);
		query.executeUpdate();
		Score s = new Score();
		s.setStudentId(studentId);
		s.setCourseId(courseId);
		s.setScore(score);
		session.save(s);
		transaction.commit();
		session.close();
	}
	
	public List<Score> findScoreByStudentId(int studentId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from Score where studentId = :studentId order by courseId";
		query = session.createQuery(hql);
		query.setInteger("studentId", studentId);
		@SuppressWarnings("unchecked")
		List<Score> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public double findScoreByStudentIdAndCourseId(int studentId, int courseId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from Score where studentId = :studentId and courseId =:courseId";
		query = session.createQuery(hql);
		query.setInteger("studentId", studentId);
		query.setInteger("courseId", courseId);
		@SuppressWarnings("unchecked")
		List<Score> list = query.list();
		transaction.commit();
		session.close();
		return list.get(0).getScore();
	}
	
	public List<Double> findScoreByClassIdAndCourseId(int classId, int courseId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select score from Score where courseId = :courseId and studentId in (select st.studentId from Student st where st.classId = :classId) order by studentId";
		query = session.createQuery(hql);
		query.setInteger("classId", classId);		
		query.setInteger("courseId", courseId);
		@SuppressWarnings("unchecked")
		List<Double> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Double> findScoreByGradeIdAndCourseId(int gradeId, int courseId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select score from Score where courseId = :courseId and studentId in (select st.studentId from Student st where st.classId in (select c.classId from Class c where c.gradeId = :gradeId)) order by studentId";
		query = session.createQuery(hql);
		query.setInteger("gradeId", gradeId);
		query.setInteger("courseId", courseId);
		@SuppressWarnings("unchecked")
		List<Double> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Double> findScoreByCourseId(int courseId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select score from Score where courseId = :courseId order by studentId";
		query = session.createQuery(hql);
		query.setInteger("courseId", courseId);
		@SuppressWarnings("unchecked")
		List<Double> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
}
