package fr.ecotilt.webservice;

import java.io.IOException;
import java.util.ArrayList;
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
import fr.ecotilt.appui.util.MapManager;
import fr.ecotilt.webservice.util.WebServiceConfig;

/**
 * @author Philippe
 */
public class WsBounding extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 873448933642550071L;
	private static Double		lat					= 0.0;
	private static Double		lng					= 0.0;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		WebServiceConfig.getInstance().doConfigure(response);
		Map<String, String> queryParameters = WebServiceConfig.getInstance().parametersManager(request);

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = WebServiceConfig.getInstance().queryConstructor(session, queryParameters, "from Pompe ");

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
			
			boolean value = MapManager.getInstance().distFrom2points(myLoc, instancePompe.getGeoCoord());
			System.out.println(value);
			if (value) {
				resultPompe.add(instancePompe);
			} 
		}
		//envoi de la réponse
		WebServiceConfig.getInstance().setReponseHttp(response, resultPompe, resultPompe.size());
	}

}
