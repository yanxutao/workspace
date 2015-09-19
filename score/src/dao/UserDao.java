package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import score.HibernateSessionFactory;
import entity.User;

public class UserDao {
	private Session session;
	private Transaction transaction;
	private String hql;
	private Query query;

	public UserDao() {
	}

	public boolean findUserByUsernameAndPassword(String username,
			String password) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		hql = "from User where username = :username and password = :password";
		query = session.createQuery(hql);
		query.setString("username", username);
		query.setString("password", password);
		@SuppressWarnings("unchecked")
		List<User> list = query.list();
		transaction.commit();
		session.close();
		if (list.size() == 1)
			return true;
		else {
			return false;
		}
	}
}
