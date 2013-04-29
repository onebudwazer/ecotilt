package fr.ecotilt.webservice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.GeoCoord;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.webservice.util.WebServiceConfig;

/**
 * WsPompe
 * 
 * @author Philippe
 */
public class WsPompe extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 5570405722726682764L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// on configure l'entete http
		WebServiceConfig.getInstance().doConfigure(response);

		// //recupere l'ensemble des parametres
		Map<String, String> listParameters = WebServiceConfig.getInstance()
				.getAllParametersFromServlet(request);

		// ouverture d'une session hibernate
		Session session = HibernateUtil.getSessionFactory().openSession();

		Criteria criteria = WebServiceConfig.getInstance().queryConstructor(
				session, listParameters, Pompe.class);

		Criteria requeteFinal = WebServiceConfig.getInstance()
				.queryConfiguration(criteria);

		// on remonte les donnees
		@SuppressWarnings("unchecked")
		List<Pompe> result = (List<Pompe>) requeteFinal.list();

		// //on definie une position la position si elle existe
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

