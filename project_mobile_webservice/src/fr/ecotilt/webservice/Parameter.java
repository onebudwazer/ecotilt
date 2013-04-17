package fr.ecotilt.webservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.appui.util.JsonApi;

/**
 * @author Philippe
 */
public class Parameter extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 873448933642550071L;
	private static final int	PAGE_SIZE			= 5;
	private static int			pageNumber			= 0;
	
	@Override
	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// on défini l'entete
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		@SuppressWarnings("rawtypes")
		Map params = request.getParameterMap();
		@SuppressWarnings("rawtypes")
		Iterator i = params.keySet().iterator();
		
		Map<String, String> queryParameters = new HashMap<String, String>();
		while (i.hasNext()) {
			String key = (String) i.next();
			String value = ((String[]) params.get(key))[0];
			
			if(key.equals("p")) {
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
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = QueryConstructor(session, queryParameters);
		query.setCacheable(true);
//			query.setMaxResults(PAGE_SIZE);
//			query.setFirstResult(PAGE_SIZE * pageNumber);
//			showResult(response, query);
		session.close();
		
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private void showResult(HttpServletResponse response, Query query) throws IOException {
		List<Pompe> result = (List<Pompe>) query.list();
		if (result.size() != 0) {
			String responseJson = JsonApi.JacksonObjectToJsonPrettyOutput(result);
			response.getWriter().write(responseJson);
		} else {
			response.getWriter().write("none");
		} 
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


