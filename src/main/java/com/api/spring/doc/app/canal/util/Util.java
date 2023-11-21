package com.api.spring.doc.app.canal.util;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public abstract class Util {

	public static String feingDetailMessage(String sentence, String word) {
		int startingPosition = sentence.indexOf(word) + word.length();
		int endingPosition = sentence.length();

		return sentence.substring(startingPosition, endingPosition).replaceAll("\"", "").replaceAll(":", "")
				.replaceAll("}", "").replaceAll("]", "");
	}

	public static HttpHeaders getAuthorization(String usuario, String senha) {
		String plainCreds = usuario + ":" + senha;
		String basic = "Basic " + Base64.getEncoder().encodeToString(plainCreds.getBytes());
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", basic);
		return httpHeaders;
	}

	
	public static HttpHeaders bearerToken(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer "+accessToken);
		
		return headers;
	}
	
	public static String basicAuth(String usuario, String senha) {
		String plainCreds = usuario + ":" + senha;
		return"Basic " + Base64.getEncoder().encodeToString(plainCreds.getBytes());
	}
}
