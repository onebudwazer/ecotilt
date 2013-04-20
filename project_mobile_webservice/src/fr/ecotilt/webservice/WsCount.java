package fr.ecotilt.webservice;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.Count;
import fr.ecotilt.webservice.util.WebServiceConfig;

/**
 * comptage des objets en base
 * retourne -1 si mauvaise requetes
 * @author Philippe
 */
public class WsCount extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 3505809146898224456L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// on configure l'entete http
		WebServiceConfig.getInstance().doConfigure(response);

		// recupere l'ensemble des parametres
		Map<String, String> queryParameters = WebServiceConfig.getInstance()
				.countingManager(request);
		// ouverture d'une session hibernate
		Session session = HibernateUtil.getSessionFactory().openSession();

		Count modelCount = WebServiceConfig
				.getInstance().queryCountingFrom(session, queryParameters);
		// reponse
		WebServiceConfig.getInstance().setReponseHttp(response, modelCount);

		// on ferme la session
		session.close();
	}
}
