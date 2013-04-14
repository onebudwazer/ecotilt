package fr.projet.webservice;

import org.hibernate.SessionFactory;
import org.hibernate.metamodel.MetadataSources;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateQuery {

	static SessionFactory buildSessionFactory() {
	    try {
	        // Create the SessionFactory from hibernate.cfg.xml

	        ServiceRegistryBuilder srb = new ServiceRegistryBuilder();

	        //NOTE: THIS IS WHERE MY PROGRAM DIES!!
	        srb = srb.configure();

	        ServiceRegistry sr = srb.buildServiceRegistry();
	        MetadataSources mds = new MetadataSources(sr);
	        /*mds.addAnnotatedClass(com.fitterblog.objects.Article.class);
	        mds.addAnnotatedClass(com.fitterblog.objects.Nav.class);
	        mds.addAnnotatedClass(com.fitterblog.objects.Tag.class);
	        mds.addAnnotatedClass(com.fitterblog.objects.User.class);*/
	        return mds.buildMetadata().buildSessionFactory();
	    } catch (Throwable ex) {
	        System.err.println("Initial SessionFactory creation failed." + ex);
	        throw new ExceptionInInitializerError(ex);
	    }
	}

	
}
