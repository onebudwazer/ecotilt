package fr.ecotilt.webservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ecotilt.appui.model.Pompe;
import fr.ecotilt.webservice.strategy.*;

/**
 * WsPompe
 * 
 * @author Philippe
 */
public class WsPompe extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= 5570405722726682764L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("c") != null) {

			String valueParam = request.getParameter("c");
			if (valueParam.equals("count")) {
				ContextWebService context = new ContextWebService(
						new Counting(Pompe.class));
				context.executeStrategy(request, response);
			}

		} else {
			ContextWebService context = new ContextWebService(new PompeQuery());
			context.executeStrategy(request, response);
		}
	}
}
