package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.PaymentManagementModel;

@Path("/cart")
public class PaymentManagementService {
	PaymentManagementModel cart = new PaymentManagementModel();

	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addItem(String data) {
		String returnValue = "failed";
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		int userId = itemObject.get("user_id").getAsInt();
		int productId = itemObject.get("product_id").getAsInt();
		int quantity = itemObject.get("quantity").getAsInt();
		if (cart.addItemToCart(userId, productId, quantity)) {
			returnValue = "done";
		}

		return "{status:" + returnValue + "}";
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String removeItem(String data) {
		String returnValue = "failed";
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		int userId = itemObject.get("user_id").getAsInt();
		int productId = itemObject.get("product_id").getAsInt();

		returnValue = cart.removeItemFromCart(userId, productId);

		return "{status:" + returnValue + "}";
	}

}
