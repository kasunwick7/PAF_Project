package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.AuthenticationModel;

@Path("/")
public class AuthenticationService {
	AuthenticationModel auth = new AuthenticationModel();

	@GET
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String newLoginRequest(String data) {
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		String username = itemObject.get("username").getAsString();
		String password = itemObject.get("password").getAsString();
		String status[] = auth.userValidation(username, password);
		return "{validation_status:" + status[0] + "," + "key:" + status[1] + "}";
	}

	// -----------------------------------------

	@POST
	@Path("/requestvalidator")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String requestvalidator(String data) {
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		String key = itemObject.get("key").getAsString();
		String status = auth.requestValidation(key);
		return "{validation_status:" + status + "}";
	}

	// -------------------------------------------

	@DELETE
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String logOut(String data) {
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		String key = itemObject.get("key").getAsString();
		String status = auth.logOut(key);
		return "{status:" + status + "}";
	}

	// -------------------------------------------

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String credentialUpdate(String data) {
		String status = "error";
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		String key = itemObject.get("key").getAsString();
		String id = itemObject.get("user_id").getAsString();
		String username = itemObject.get("username").getAsString();
		String password = itemObject.get("password").getAsString();
		// validating request
		if (auth.requestValidation(key).equals("valid")) {
			status = auth.credentialUpdate(Integer.valueOf(id), username, password);
		}
		return "{status:" + status + "}";
	}

	// -------------------------------------------

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addUser(String data) {
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		String status = "error";
		String key = itemObject.get("key").getAsString();
		int id = itemObject.get("user_id").getAsInt();
		String username = itemObject.get("username").getAsString();
		String password = itemObject.get("password").getAsString();

		if (auth.requestValidation(key).equals("valid")) {
			status = auth.addUser(id, username, password);
		}
		return "{status:" + status + "}";
	}
}
