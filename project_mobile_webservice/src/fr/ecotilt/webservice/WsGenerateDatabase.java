package fr.ecotilt.webservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.hibernate.generatedatabase.GeneratePompe;
import fr.ecotilt.appui.model.BorneElectrique;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.appui.model.Velib;
import fr.ecotilt.webservice.util.WebServiceConfig;

public class WsGenerateDatabase extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -7262210942373280891L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		long countBorneElectrique = (long) session
				.createCriteria(BorneElectrique.class)
				.setProjection(Projections.rowCount()).uniqueResult();

		long countPompe = (long) session.createCriteria(Pompe.class)
				.setProjection(Projections.rowCount()).uniqueResult();

		long countVelib = (long) session.createCriteria(Velib.class)
				.setProjection(Projections.rowCount()).uniqueResult();

		if (countBorneElectrique == 0 && countPompe == 0 && countVelib == 0) {
			 GeneratePompe.generateBorne(session);
			 GeneratePompe.generatePompe(session);
			 GeneratePompe.generateVelib(session);
			 WebServiceConfig.getInstance().setReponseHttp(response, "Generation de la base...");
		} else {
			WebServiceConfig.getInstance().setReponseHttp(response, "Generation de la base...déjà fait");
		}

		session.close();
	}

}
