package com;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RequestValidator {

	public static boolean validate(String key) {

		// request validation

		Client client;
		String baseuri = "http://localhost:8081/authentication-service/API/requestvalidator";
		ClientResponse response;
		String output = null;

		try {

			client = Client.create();
			WebResource webResource = client.resource(baseuri);
			String input = "{key:" + key + "}";

			// POST method
			response = webResource.accept("application/json").type("application/json").post(ClientResponse.class,input);

			// check response status code
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			// response
			output = response.getEntity(String.class);
			System.out.print("-------------------------");
			System.out.print(output);


		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		// end of validation

		return true;
	}
}
