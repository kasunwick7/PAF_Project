package model;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/")
public class ResearchServiceModel {
	@POST
	@Path("/test")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public String test(String data) {
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		return itemObject.toString();
	}
}
