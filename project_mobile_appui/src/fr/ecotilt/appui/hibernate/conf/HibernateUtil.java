package fr.ecotilt.appui.hibernate.conf;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Permet la connection vers hibernate
 * @author Philippe
 *
 */
public class HibernateUtil {

	private static SessionFactory	sessionFactory;
	private static ServiceRegistry	serviceRegistry;

	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("/fr/ecotilt/appui/hibernate/conf/hibernate.cfg.xml");
			
			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Integer saveToHibernate(Session session, Object obj) {
		Transaction tx = null;
		Integer out = null;
		try {
			tx = session.beginTransaction();
			session.save(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} 
		return out;
	}
	
	public static Integer deleteToHibernate(Session session, Object obj) {
		Transaction tx = null;
		Integer out = null;
		try {
			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} 
		return out;
	}
	
	public static Integer updateToHibernate(Session session, Object obj) {
		Transaction tx = null;
		Integer out = null;
		try {
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} 
		return out;
	}
	
	public void stdOut(@SuppressWarnings("rawtypes") List list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}

}
