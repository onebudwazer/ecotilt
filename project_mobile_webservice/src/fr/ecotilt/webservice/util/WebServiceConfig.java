package fr.ecotilt.webservice.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import fr.ecotilt.appui.model.AGenericObject;
import fr.ecotilt.appui.model.Count;
import fr.ecotilt.appui.model.GeoCoord;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.appui.model.Velib;
import fr.ecotilt.appui.util.JsonApi;
import fr.ecotilt.appui.util.MapUtil;

/**
 * WebServiceConfig access with singleton for tomcat thread
 * 
 * @author Philippe
 * 
 */
public class WebServiceConfig {

	private Logger log = Logger.getLogger("WebServiceConfig");
	
	public static final int	PAGE_SIZE	= 5;
	public static int		pageNumber	= 0;
	private Double			latitude	= 0.0;
	private Double			longitude	= 0.0;

	/**
	 * oblige d'avoir un constructeur prive
	 */
	private WebServiceConfig() {
	}

	/**
	 * singleton en chargement lazy
	 * 
	 * @author Philippe
	 * 
	 */
	private static class LazySingleton {
		private static WebServiceConfig	instance	= new WebServiceConfig();
	}

	/**
	 * return 1 instance de WebServiceConfig
	 * 
	 * @return
	 */
	public static WebServiceConfig getInstance() {
		return LazySingleton.instance;
	}

	/**
	 * définie la sortie et utilisation de la lib json
	 * 
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	public void setReponseHttp(HttpServletResponse response, Object result,
			int numberOfResult) throws IOException {
		
		if (numberOfResult != 0) {
			String responseJson = JsonApi
					.JacksonObjectToJsonPrettyOutput(result);
			response.getWriter().write(responseJson);
			log.info(responseJson);
		} else {
			log.info("none");
			response.getWriter().write("none");
		}
	}

	/**
	 * defini l'entete du request http
	 * 
	 * @param response
	 */
	public void doConfigure(HttpServletResponse response) {
		// on défini l'entete
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	}

	/**
	 * Recupere l'ensemble des paramètre d'une requete http si ils existent
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, String> parametersManager(HttpServletRequest request) {
		// on commence par creer la requete
		Map params = request.getParameterMap();
		Iterator i = params.keySet().iterator();

		Map<String, String> queryParameters = new HashMap<String, String>();
		while (i.hasNext()) {
			String key = (String) i.next();
			String value = ((String[]) params.get(key))[0];

			if (key.equals("p")) {
				pageNumber = Integer.valueOf(value);
				log.info(String.valueOf(pageNumber));
			}
			if (key.equals("city")) {
				queryParameters.put(key, value);
			}
			if (key.equals("name")) {
				queryParameters.put(key, value);
			}
			if (key.equals("cp")) {
				queryParameters.put("CODE_POSTAL", value);
			}
			if (key.equals("lng")) {
				longitude = Double.valueOf(value);
			}
			if (key.equals("lat")) {
				latitude = Double.valueOf(value);
			}
		}
		
		return queryParameters;
	}

	@SuppressWarnings("rawtypes")
	public Map<String, String> countingManager(HttpServletRequest request) {
		// on commence par creer la requete
		Map params = request.getParameterMap();
		Iterator i = params.keySet().iterator();

		Map<String, String> queryParameters = new HashMap<String, String>();
		while (i.hasNext()) {
			String key = (String) i.next();
			String value = ((String[]) params.get(key))[0];

			if (key.equals("c")) {
				queryParameters.put(key, value);
			}
		}
		return queryParameters;
	}

	@SuppressWarnings("rawtypes")
	public Count queryCountingFrom(Session session,
			Map<String, String> queryParameters) {
		Count modelCount = new Count();
		Class clazz = null;
		
		if (queryParameters.get("c") != null) {
			String value = queryParameters.get("c");

			if (value.equals("pompe")) {
				clazz = Pompe.class;
				modelCount = internalCountQuery(session, clazz);
			} else if (value.equals("velib")) {
				clazz = Velib.class;
				modelCount = internalCountQuery(session, clazz);
			} else {
				modelCount.setValue(-1);
			}
		} else {
			modelCount.setValue(-1);
		}
		return modelCount;
	}
	
	private Count internalCountQuery(Session session, Class<?> clazz) {
		Count modelCount = new Count();
		
		long number = (Long) session.createCriteria(clazz)
				.setProjection(Projections.rowCount()).uniqueResult();
		
		modelCount.setValue(number);
		return modelCount;
	}

	public Query queryConstructor(Session session,
			Map<String, String> queryParameters, String beginQuery) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(beginQuery);
		if (queryParameters.size() != 0) {
			queryBuilder.append(" where ");
			Set<String> cles = queryParameters.keySet();
			Iterator<String> it = cles.iterator();
			while (it.hasNext()) {
				String cle = it.next();
				String valeur = queryParameters.get(cle);
				if (!cle.equals("lng") || !cle.equals("lat")) {
					queryBuilder.append(cle + "='" + valeur + "'");
				}
				if (it.hasNext()) {
					queryBuilder.append(" and ");
				}
			}
		}
		//log
		log.info("queryConstructor: " + queryBuilder.toString());
		// return query
		return session.createQuery(queryBuilder.toString());
	}

	public Query queryConfiguration(Query query) {
		query.setCacheable(true);
		query.setMaxResults(PAGE_SIZE);
		query.setFirstResult(PAGE_SIZE * pageNumber);
		return query;
	}

	public GeoCoord defineMypositionGeo(HttpServletRequest request) {
		GeoCoord myPosition;

		if (request.getParameter("lat") != null
				&& request.getParameter("lng") != null) {
			myPosition = new GeoCoord(latitude, longitude);
		} else {
			myPosition = new GeoCoord(0.0, 0.0);
		}
		log.info(myPosition.toString());
		return myPosition;
	}

	public List<?> setMyPositionGeo(GeoCoord myPosition, List<?> myList) {
		List<AGenericObject> newResult = new ArrayList<AGenericObject>();

		if (myPosition.getLatitude() == 0.0 && myPosition.getLongitude() == 0.0) {
			return myList;

		} else {
			for (int index = 0; index < myList.size(); index++) {
				AGenericObject instanceObject = (AGenericObject) myList
						.get(index);
				GeoCoord refGeoCoord = instanceObject.getGeoCoord();
				boolean value = MapUtil
						.distFrom2points(myPosition, refGeoCoord);
				if (value == true) {
					newResult.add(instanceObject);
				}
			}
			return newResult;
		}
	}

}
