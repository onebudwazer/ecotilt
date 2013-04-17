package fr.ecotilt.webservice;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.GeoCoord;
import fr.ecotilt.appui.model.Velib;
import fr.ecotilt.webservice.util.WebServiceConfig;

public class WsVelib extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -3684499643028794001L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// on configure l'entete http
		WebServiceConfig.getInstance().doConfigure(response);
		
		//recupere l'ensemble des parametres
		Map<String, String> queryParameters = WebServiceConfig.getInstance()
											  .parametersManager(request);
		
		//ouverture d'une session hibernate
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		//construit une requete hibernate
		Query requete = WebServiceConfig.getInstance().queryConstructor(session, queryParameters, "from Velib ");
		
		//on configure la requete (active cache)
		Query requeteFinal =  WebServiceConfig.getInstance().queryConfiguration(requete);

		//on remonte les donnees
		List<Velib> result = (List<Velib>) requeteFinal.list();
		
		//on definie une position la position si elle existe
		GeoCoord myPosition = WebServiceConfig.getInstance().defineMypositionGeo(request);
		
		//
		List<Velib> finalResult = (List<Velib>) WebServiceConfig.getInstance().setMyPositionGeo(myPosition, result);
	
		//reponse
		WebServiceConfig.getInstance().setReponseHttp(response, finalResult, finalResult.size());
		
		//on ferme la session 
		session.close();	
		System.out.println(finalResult.get(0).getDate().toString());
		
	}
	
}
