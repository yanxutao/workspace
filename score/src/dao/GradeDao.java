package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Grade;
import score.HibernateSessionFactory;

public class GradeDao {
	private Session session;
	private Transaction transaction;
	private Query query;
	private String hql;
	
	public GradeDao() {
	}
	
	public void insertGrade(String grade) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from Grade where grade = :grade";
		query = session.createQuery(hql);
		query.setString("grade", grade);
		@SuppressWarnings("unchecked")
		List<Grade> list = query.list();
		if (list.size() == 0) {
			Grade gra = new Grade();
			gra.setGrade(grade);
			session.save(gra);
		}
		transaction.commit();
		session.close();
	}
	
	public int findGradeIdByGrade(String grade) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from Grade where grade = :grade";
		query = session.createQuery(hql);
		query.setString("grade", grade);
		@SuppressWarnings("unchecked")
		List<Grade> list = query.list();
		int gradeId = -1;
		if (list.size() > 0) {
			gradeId = list.get(0).getGradeId();
					
		}
		transaction.commit();
		session.close();
		return gradeId;
	}
	
	public List<String> findAllGrade() {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select grade from Grade";
		query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<String> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Integer> findAllGradeId() {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select gradeId from Grade";
		query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Integer> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
}
