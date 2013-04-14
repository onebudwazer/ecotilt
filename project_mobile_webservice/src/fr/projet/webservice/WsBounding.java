package fr.projet.webservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.appui.util.MapUtil;
import fr.projet.webservice.util.WebServiceConfig;
import fr.projet.webservice.util.WebServiceUtil;

/**
 * @author Philippe
 */
public class WsBounding extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 873448933642550071L;
	private static final int	PAGE_SIZE			= 5;
	private static int			pageNumber			= 0;
	private static Double		lat					= 0.0;
	private static Double		lng					= 0.0;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		WebServiceConfig.doConfigure(response);
		Map<String, String> queryParameters = WebServiceConfig.parametersManager(request);

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = QueryConstructor(session,"from Pompe " ,queryParameters);
//		query.setMaxResults(PAGE_SIZE);
//		query.setFirstResult(PAGE_SIZE * pageNumber);
		showResult(response, query, new GeoCoord(lat, lng));
		session.close();
	}

////	Query q = session.createQuery("from Table order by abs(value - :v) asc";
//	q.setXxx("v", myValue);         /* Xxx is Float or Long or Integer or... */
//	q.setMaxResults(10);
//	List<Table> l = q.list();
	
	private void showResult(HttpServletResponse response, Query query,
			GeoCoord myLoc) throws IOException {
		List<Pompe> result = (List<Pompe>) query.list();
		List<Pompe> resultPompe = new ArrayList<Pompe>();
		for (int i = 0; i < result.size(); i++) {
			Pompe instancePompe = result.get(i);
			System.out.println(instancePompe.getGeoCoord());
			
			boolean value = MapUtil.getCalculetLocation(myLoc, instancePompe.getGeoCoord());
			System.out.println(value);
			if (value) {
				resultPompe.add(instancePompe);
			} 
		}
		System.out.println(resultPompe.size());
		//send response
		WebServiceUtil.setReponseHttp(response, resultPompe);
	}

	private Query QueryConstructor(Session session, String beginQuery, Map<String, String> queryParameters) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(beginQuery);
		return session.createQuery(queryBuilder.toString());
	}

}
