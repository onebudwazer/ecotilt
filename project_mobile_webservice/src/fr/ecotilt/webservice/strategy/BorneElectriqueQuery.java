package fr.ecotilt.webservice.strategy;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.BorneElectrique;
import fr.ecotilt.appui.model.GeoCoord;
import fr.ecotilt.webservice.util.WebServiceConfig;

public class BorneElectriqueQuery implements IStrategy {

	@Override
	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> listParameters = WebServiceConfig.getInstance()
				.doConfigureServlet(request, response);

		// ouverture d'une session hibernate
		Session session = HibernateUtil.getSessionFactory().openSession();

		// construit une requete hibernate
		Criteria criteria = WebServiceConfig.getInstance().queryConstructor(
				session, listParameters, BorneElectrique.class);
		criteria.addOrder(Order.asc("id"));
		// on configure la requete (active cache)
		Criteria requeteFinal = WebServiceConfig.getInstance()
				.queryConfiguration(criteria);

		// on remonte les donnees
		List<BorneElectrique> result = (List<BorneElectrique>) requeteFinal
				.list();

		// on definie une position la position si elle existe
		GeoCoord myPosition = WebServiceConfig.getInstance()
				.defineMypositionGeo(request);

		List<BorneElectrique> finalResult = (List<BorneElectrique>) WebServiceConfig
				.getInstance().setMyPositionGeo(myPosition, result);

		// reponse
		WebServiceConfig.getInstance().setReponseHttp(response, finalResult,
				finalResult.size());

		// on ferme la session
		session.close();
	}

}
