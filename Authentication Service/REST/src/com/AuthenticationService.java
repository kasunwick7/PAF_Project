package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.AuthenticationModel;

@Path("/authentication")
public class AuthenticationService {
	AuthenticationModel auth = new AuthenticationModel();

	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String newLoginRequest(String data) {
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		String username = itemObject.get("username").getAsString();
		String password = itemObject.get("password").getAsString();
		String status = auth.uservalidation(username, password);
		return "{validation_status:" + status + "}";
	}

}
