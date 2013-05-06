package fr.ecotilt.webservice.strategy;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.GeoCoord;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.webservice.util.WebServiceConfig;

public class PompeQuery implements IStrategy {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> listParameters = WebServiceConfig.getInstance()
				.doConfigureServlet(request, response);

		// ouverture d'une session hibernate
		Session session = HibernateUtil.getSessionFactory().openSession();

		Criteria criteria = WebServiceConfig.getInstance().queryConstructor(
				session, listParameters, Pompe.class);
		criteria.addOrder(Order.asc("id"));
		
		Criteria requeteFinal = WebServiceConfig.getInstance()
				.queryConfiguration(criteria);

		// on remonte les donnees
		@SuppressWarnings("unchecked")
		List<Pompe> result = (List<Pompe>) requeteFinal.list();

		//on definie une position la position si elle existe
		GeoCoord myPosition = WebServiceConfig.getInstance()
				.defineMypositionGeo(request);

		@SuppressWarnings("unchecked")
		List<Pompe> finalResult = (List<Pompe>) WebServiceConfig.getInstance()
				.setMyPositionGeo(myPosition, result);

		WebServiceConfig.getInstance().setReponseHttp(response, finalResult,
				finalResult.size());

		// on ferme la session
		session.close();
	}

}
