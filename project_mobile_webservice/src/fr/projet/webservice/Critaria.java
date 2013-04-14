package fr.projet.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.appui.util.JsonApi;

/**
 * Webservice xt data
 * @author Philippe
 */
public class Critaria extends HttpServlet {

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
		
		
//		int value = Integer.parseInt(request.getParameter("cp"));
//		System.out.println(value);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Pompe> cats = session.createCriteria(Pompe.class)
				.setMaxResults(PAGE_SIZE).setFirstResult(PAGE_SIZE * pageNumber).list();
		
		String responseJson = JsonApi.JacksonObjectToJson(cats);
		response.getWriter().write(responseJson);
		session.close();
		
		//parameters construct
//		String hqlQuery = null;
//		hqlQuery = "";
			
//		if (hqlQuery != null) {
			// call session hibernate
//			Query query = session.createQuery(hqlQuery);
//			query.setCacheable(true);
//			query.setMaxResults(PAGE_SIZE);
//			query.setFirstResult(PAGE_SIZE * pageNumber);
//			List<?> result = query.list();
//			
	
//		}
		
	}
}
