package fr.ecotilt.webservice.strategy;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.Count;
import fr.ecotilt.webservice.util.WebServiceConfig;

@SuppressWarnings("rawtypes")
public class Counting implements IStrategy {

	private Class clazz;
	
	public Counting(Class clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, String> listParameters = WebServiceConfig.getInstance()
				.doConfigureServlet(request, response);

		Session session = HibernateUtil.getSessionFactory().openSession();

		Criteria criteria = WebServiceConfig.getInstance().queryConstructor(
				session, listParameters, clazz);

		// on remonte les donnees
		long value = (long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		try {
			WebServiceConfig.getInstance().setReponseHttp(response, new Count(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// on ferme la session
		session.close();
	}

}
