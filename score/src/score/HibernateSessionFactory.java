package score;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	private static Configuration cfg;
	private static SessionFactory sf;
	
	public HibernateSessionFactory() {
	}
	
	static {
		cfg = new Configuration().configure();
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
		sf = cfg.buildSessionFactory(serviceRegistry);
	}
	
	public static Session getSession() {
		return sf.openSession();
	}
}
