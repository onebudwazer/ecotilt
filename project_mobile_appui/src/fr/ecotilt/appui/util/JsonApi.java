package fr.ecotilt.appui.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Static class to call Jackson API
 * 
 * @author Philippe
 */
public class JsonApi {

	public static String JacksonObjectToJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String json;
		json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static String JacksonObjectToJsonPrettyOutput(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String json;
		json = null;
		try {
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

}
