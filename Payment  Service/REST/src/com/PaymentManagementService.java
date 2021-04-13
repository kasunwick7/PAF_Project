package com;

import com.RequestValidator;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.PaymentManagementModel;

@Path("/")
public class PaymentManagementService {
	PaymentManagementModel cart = new PaymentManagementModel();

	@POST
	@Path("/cartItems")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addItem(String data) {
		JsonObject result = new JsonObject();
		result.addProperty("status", "error");
		int userId = 0;
		int productId = 0;
		int quantity = 0;

		try {
			JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
			if (!RequestValidator.validate(itemObject.get("key").getAsString())) {
				return result.toString();
			}
			if (itemObject.has("user_id")) {
				userId = itemObject.get("user_id").getAsInt();
				productId = itemObject.get("product_id").getAsInt();
				quantity = itemObject.get("quantity").getAsInt();
				if (cart.addItemToCart(userId, productId, quantity)) {
					result.addProperty("status", "done");
				}
			} else if (itemObject.has("items")) {

				for (JsonElement singleItem : itemObject.get("items").getAsJsonArray()) {
					JsonObject itemObj = singleItem.getAsJsonObject();
					userId = itemObj.get("user_id").getAsInt();
					productId = itemObj.get("product_id").getAsInt();
					quantity = itemObj.get("quantity").getAsInt();
					cart.addItemToCart(userId, productId, quantity);

				}
				result.addProperty("status", "done_all");

			}
		} catch (Exception e) {
			e.printStackTrace();
			result.addProperty("status", "error");
		}

		return result.toString();
	}

	@DELETE
	@Path("/cartItems")
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

	@GET
	@Path("/cartItems")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String readItem(String data) {
		String returnValue = "";
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		int userId = itemObject.get("user_id").getAsInt();

		returnValue = cart.returnCartDetails(userId);

		return returnValue;
	}
}
