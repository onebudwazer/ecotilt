package fr.projet.webservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import fr.ecotilt.appui.hibernate.conf.HibernateUtil;
import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.appui.util.JsonApi;

/**
 * @author Philippe
 */
public class Count extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -941426869602722371L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// on défini l'entete
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Session session = HibernateUtil.getSessionFactory().openSession();
		long value = (Long) session.createCriteria(Pompe.class)
				.setProjection(Projections.rowCount()).uniqueResult();
		session.close();

		fr.ecotilt.appui.model.Count instance = new fr.ecotilt.appui.model.Count();
		instance.setValue(value);
		String responseJson = JsonApi
				.JacksonObjectToJsonPrettyOutput(instance);
		response.getWriter().write(responseJson);
	}

}
