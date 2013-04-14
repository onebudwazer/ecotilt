package fr.projet.webservice.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import fr.ecotilt.appui.util.JsonApi;

public class WebServiceUtil {

	public static void setReponseHttp(HttpServletResponse response,
			Object result) throws IOException {
		
		if (!result.equals(null)) {
			String responseJson = JsonApi
					.JacksonObjectToJsonPrettyOutput(result);
			response.getWriter().write(responseJson);
		} else {
			response.getWriter().write("none");
		}
	}

}
