package fr.ecotilt.webservice.strategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IStrategy {
	void execute(HttpServletRequest request, HttpServletResponse response);
}
