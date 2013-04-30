package fr.ecotilt.webservice.strategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextWebService {
	private IStrategy	strategy;

	public ContextWebService(IStrategy strategy) {
		this.strategy = strategy;
	}

	public void executeStrategy(HttpServletRequest request,
			HttpServletResponse response) {
		this.strategy.execute(request, response);
	}
}
