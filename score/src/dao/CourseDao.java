package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Course;
import score.HibernateSessionFactory;

public class CourseDao {
	private Session session;
	private Transaction transaction;
	private Query query;
	private String hql;
	
	public CourseDao() {
	}
	
	public void insertCourse(String courseName) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from Course where courseName = :courseName";
		query = session.createQuery(hql);
		query.setString("courseName", courseName);
		@SuppressWarnings("unchecked")
		List<Course> list = query.list();
		if (list.size() == 0) {
			Course course = new Course();
			course.setCourseName(courseName);
			session.save(course);
		}
		transaction.commit();
		session.close();
	}
	
	public List<Course> findAllCourse() {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from Course order by courseId";
		query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Course> list = query.list();
		transaction.commit();
		session.close();
		return list;
	}
	
	public int findCourseIdByCourseName(String courseName) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from Course where courseName = :courseName";
		query = session.createQuery(hql);
		query.setString("courseName", courseName);
		@SuppressWarnings("unchecked")
		List<Course> list = query.list();
		int courseId = -1;
		if (list.size() > 0) {
			courseId = list.get(0).getCourseId();
		}
		transaction.commit();
		session.close();
		return courseId;
	}
	
	public String findCourseNameByCourseId(int courseId) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from Course where courseId = :courseId";
		query = session.createQuery(hql);
		query.setInteger("courseId", courseId);
		@SuppressWarnings("unchecked")
		List<Course> list = query.list();
		String courseName = "";
		if (list.size() > 0) {
			courseName = list.get(0).getCourseName();	
		}
		transaction.commit();
		session.close();
		return courseName;
	}
}
