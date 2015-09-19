package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Class;
import score.HibernateSessionFactory;

public class ClassDao {
	private Session session;
	private Transaction transaction;
	private Query query;
	private String hql;
	
	public ClassDao() {
	}
	
	public void insertClass(String classNo, int gradeId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "delete from Class where classNo = :classNo";
		query = session.createQuery(hql);
		query.setString("classNo", classNo);
		query.executeUpdate();
		Class cla = new Class();
		cla.setClassNo(classNo);
		cla.setGradeId(gradeId);
		session.save(cla);
		transaction.commit();
		session.close();
	}
	
	public int findClassIdByClassNo(String classNo) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from Class where classNo = :classNo";
		query = session.createQuery(hql);
		query.setString("classNo", classNo);
		@SuppressWarnings("unchecked")
		List<Class> list = query.list();
		int classId = -1;
		if (list.size() > 0) {
			classId = list.get(0).getClassId();	
		}
		transaction.commit();
		session.close();
		return classId;
	}
	
	public List<Integer> findClassIdsByGradeId(int gradeId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select classId from Class where gradeId = :gradeId";
		query = session.createQuery(hql);
		query.setInteger("gradeId", gradeId);
		@SuppressWarnings("unchecked")
		List<Integer> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<String> findClassNosByGradeId(int gradeId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select classNo from Class where gradeId = :gradeId";
		query = session.createQuery(hql);
		query.setInteger("gradeId", gradeId);
		@SuppressWarnings("unchecked")
		List<String> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
}
