package fr.projet.webservice;

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
 * Webservice xt data
 * @author Philippe
 */
public class Webservice extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 873448933642550071L;
	
	private static final int PAGE_SIZE = 5;
	private static int pageNumber = 0;
	
	@Override
	public void init() throws ServletException {
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//on défini l'entete
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		//on commence par creer la requete
		Map<String, String> queryParameters = new HashMap<String, String>();
		
		if(request.getParameter("p") != null) {
			System.out.println(request.getParameter("p"));
			pageNumber = Integer.valueOf(request.getParameter("p"));
		}
		
		if(request.getParameter("city") != null) {
			queryParameters.put("city", request.getParameter("city"));
		}
		
		if(request.getParameter("name") != null) {
			queryParameters.put("name", request.getParameter("name"));
		}
		
		if(request.getParameter("cp") != null) {
			queryParameters.put("cp", request.getParameter("cp"));
		}
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("from Pompe");
		
		//parameters construct
		String hqlQuery = null;
		if (queryParameters.size() != 0) {
			//construct final query
			queryBuilder.append(" where ");
			
			Set<String> cles = queryParameters.keySet();
			Iterator<String> it = cles.iterator();
			while (it.hasNext()){
			   String cle = it.next(); 
			   String valeur = queryParameters.get(cle);
			   queryBuilder.append(cle+"= '"+ valeur +"' ");
			   
				if (it.hasNext()) {
					queryBuilder.append("and ");
				}
			}
		}
		
		//check query
		hqlQuery = queryBuilder.toString();
		//System.out.println(hqlQuery);
			
		if (hqlQuery != null) {
			// call session hibernate
			Session session = HibernateUtil.getSessionFactory().openSession();
			Query query = session.createQuery(hqlQuery);
			query.setCacheable(true);
			query.setMaxResults(PAGE_SIZE);
			query.setFirstResult(PAGE_SIZE * pageNumber);
			List<Pompe> result = query.list();
			
			System.out.println(result);
			
			String responseJson = JsonApi.JacksonObjectToJson(result);
			response.getWriter().write(responseJson);
			session.close();
		}
		
				
		// if(request.getParameter("city") != null ||
		// request.getParameter("nom") != null) {
		// String city = request.getParameter("city");
		// String nom = request.getParameter("nom");
		// String value = request.getParameter("city");
		// //list =
		// session.createQuery("from BorneElectrique where city= ?").setString(0,
		// value).list();
		// Query query =
		// session.createQuery("from BorneElectrique where city= ?");
		// list = query.setString(0, value).list();
		// System.out.println(list);
		// }
		// b1 = new BorneElectrique("SALOPE", 15, "la seyne sur mer", 83500, new
		// Point2D.Double(83, -4.0487));
		// session.saveOrUpdate(b1);
		// String json2 = DataObject.GsonObjectToJson(new Student("TEST", 15,
		// "toulon", 83500, new GeoCoord(83, -4.0487)));
		// list.add(new BorneElectrique("SALOPE", 15, "toulon", 83500, new
		// Point2D.Double(83, -4.0487)));
		// list.add(new BorneElectrique("SALOPE", 15, "toulon", 83500, new
		// Point2D.Double(83, -4.0487)));
		// if(request.getParameter("d") != null) {
		// init vars
	}
}
