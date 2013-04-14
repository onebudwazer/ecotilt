package fr.projet.webservice.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

public class WebServiceConfig {

	public static final int PAGE_SIZE = 5;
	public static int pageNumber = 0;
	
	/**
	 * defini l'entete du request http
	 * @param response
	 */
	public static void doConfigure(HttpServletResponse response) {
		// on défini l'entete
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	}
	
	
	/**
	 * Recupere l'ensemble des paramètre d'une requete http
	 * si ils existent
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> parametersManager(HttpServletRequest request) {
		//on commence par creer la requete
		Map params = request.getParameterMap();
		Iterator i = params.keySet().iterator();
	
		Map<String, String> queryParameters = new HashMap<String, String>();
		while (i.hasNext()) {
			String key = (String) i.next();
			String value = ((String[]) params.get(key))[0];
	
			if (key.equals("p")) {
				pageNumber = Integer.valueOf(value);
			}
			if(key.equals("city")) {
				queryParameters.put(key, value);
			}
			if(key.equals("name")) {
				queryParameters.put(key, value);
			}
			if(key.equals("cp")) {
				queryParameters.put(key, value);
			}
			if (key.equals("lng")) {
				// queryParameters.put(key, value);
//				lng = Double.valueOf(value);
			}
			if (key.equals("lat")) {
				// queryParameters.put(key, value);
//				lat = Double.valueOf(value);
			}
		}
		return queryParameters;
	}
	
	
	
	
	private Query QueryConstructor(Session session, Map<String, String> queryParameters) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("from Pompe");
		if (queryParameters.size() != 0) {
			queryBuilder.append(" where ");
			Set<String> cles = queryParameters.keySet();
			Iterator<String> it = cles.iterator();
			while (it.hasNext()){
			   String cle = it.next(); 
			   String valeur = queryParameters.get(cle);
			   queryBuilder.append(cle+"='"+ valeur +"'");
				if (it.hasNext()) {
					queryBuilder.append(" and ");
				}
			}
			
			return session.createQuery(queryBuilder.toString());
		} else {
			//return session.createCriteria(Pompe.class).;
		}
		return null;
	}
	
	
}
