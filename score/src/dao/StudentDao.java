package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Student;
import score.HibernateSessionFactory;

public class StudentDao {
	private Session session;
	private Transaction transaction;
	private Query query;
	private String hql;
	
	public StudentDao() {
	}
	
	public void insertStudent(String studentNo, int classId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "delete from Student where studentNo = :studentNo";
		query = session.createQuery(hql);
		query.setString("studentNo", studentNo);
		query.executeUpdate();
		Student student = new Student();
		student.setStudentNo(studentNo);
		student.setClassId(classId);
		session.save(student);
		transaction.commit();
		session.close();
	}
	
	public int findStudentIdByStudentNo(String studentNo) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from Student where studentNo = :studentNo";
		query = session.createQuery(hql);
		query.setString("studentNo", studentNo);
		@SuppressWarnings("unchecked")
		List<Student> list = query.list();
		int studentId = -1;
		if (list.size() > 0) {
			studentId = list.get(0).getStudentId();
		}
		transaction.commit();
		session.close();
		return studentId;
	}
	
	public List<Integer> findStudentIdsByClassId(int classId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select studentId from Student where classId = :classId";
		query = session.createQuery(hql);
		query.setInteger("classId", classId);
		@SuppressWarnings("unchecked")
		List<Integer> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<String> findStudentNosByClassId(int classId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select studentNo from Student where classId = :classId";
		query = session.createQuery(hql);
		query.setInteger("classId", classId);
		@SuppressWarnings("unchecked")
		List<String> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public List<Integer> findAllStudentIds() {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "select studentId from Student";
		query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Integer> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
}