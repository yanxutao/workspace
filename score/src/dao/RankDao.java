package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import score.HibernateSessionFactory;
import entity.Rank;

public class RankDao {
	private Session session;
	private Transaction transaction;
	private Query query;
	private String hql;
	
	public RankDao() {
	}
	
	public void insertRank(int studentId, int courseId, double rank) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "delete from Rank where studentId = :studentId and courseId = :courseId";
		query = session.createQuery(hql);
		query.setInteger("studentId", studentId);
		query.setInteger("courseId", courseId);
		Rank r = new Rank();
		r.setStudentId(studentId);
		r.setCourseId(courseId);
		r.setRank(rank);
		session.save(r);
		transaction.commit();
		session.close();
	}
	
	public List<Rank> findRankByStudentId(int studentId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from Rank where studentId = :studentId order by courseId";
		query = session.createQuery(hql);
		query.setInteger("studentId", studentId);
		@SuppressWarnings("unchecked")
		List<Rank> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Double> findRankByClassIdAndCourseId(int classId, int courseId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select rank from Rank where courseId = :courseId and studentId in (select st.studentId from Student st where st.classId = :classId) order by studentId";
		query = session.createQuery(hql);
		query.setInteger("classId", classId);		
		query.setInteger("courseId", courseId);
		@SuppressWarnings("unchecked")
		List<Double> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Double> findRankByGradeIdAndCourseId(int gradeId, int courseId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select rank from Rank where courseId = :courseId and studentId in (select st.studentId from Student st where st.classId in (select c.classId from Class c where c.gradeId = :gradeId)) order by studentId";
		query = session.createQuery(hql);
		query.setInteger("gradeId", gradeId);
		query.setInteger("courseId", courseId);
		@SuppressWarnings("unchecked")
		List<Double> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Double> findRankByCourseId(int courseId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select rank from Rank where courseId = :courseId order by studentId";
		query = session.createQuery(hql);
		query.setInteger("courseId", courseId);
		@SuppressWarnings("unchecked")
		List<Double> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
}
