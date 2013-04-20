package fr.ecotilt.webservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.hibernate.generatedatabase.GeneratePompe;

public class WsGenerateDatabase extends HttpServlet  {
	
	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -7262210942373280891L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		GeneratePompe.generateBorne(session);
		GeneratePompe.generatePompe(session);
		GeneratePompe.generateVelib(session);
		session.close();
	}

}
