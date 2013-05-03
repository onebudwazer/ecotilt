package fr.ecotilt.webservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ecotilt.appui.model.Velib;
import fr.ecotilt.webservice.strategy.ContextWebService;
import fr.ecotilt.webservice.strategy.Counting;
import fr.ecotilt.webservice.strategy.VelibQuery;

public class WsVelib extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long	serialVersionUID	= -3684499643028794001L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("c") != null) {

			String valueParam = request.getParameter("c");
			if (valueParam.equals("count")) {
				ContextWebService context = new ContextWebService(new Counting(
						Velib.class));
				context.executeStrategy(request, response);
			}
		} else {
			ContextWebService context = new ContextWebService(new VelibQuery());
			context.executeStrategy(request, response);
		}
	}

}
