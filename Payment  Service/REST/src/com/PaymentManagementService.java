package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	//insert item/s
	public String addItem(String data) {
		JsonObject result = new JsonObject();
		result.addProperty("status", "error");
		int userId = 0;
		int productId = 0;
		int quantity = 0;

		try {
			JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
			// request validation
			if (!RequestValidator.validate(itemObject.get("key").getAsString())) {
				return result.toString();
			}
			userId = itemObject.get("user_id").getAsInt();

			if (itemObject.has("items")) {

				for (JsonElement singleItem : itemObject.get("items").getAsJsonArray()) {
					JsonObject itemObj = singleItem.getAsJsonObject();
					productId = itemObj.get("product_id").getAsInt();
					quantity = itemObj.get("quantity").getAsInt();
					cart.addItemToCart(userId, productId, quantity);

				}
				result.addProperty("status", "done_all");

			} else if (itemObject.has("user_id")) {
				productId = itemObject.get("product_id").getAsInt();
				quantity = itemObject.get("quantity").getAsInt();
				if (cart.addItemToCart(userId, productId, quantity)) {
					result.addProperty("status", "done");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.addProperty("status", "error");
		}

		return result.toString();
	}

	@DELETE // delete cart item of user one by one or all
	@Path("/cartItems")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String removeItem(String data) {
		String returnValue = "failed";
		JsonObject itemObject = new JsonParser().parse(data).getAsJsonObject();
		String key = itemObject.get("key").getAsString();
		// request validation
		if (!RequestValidator.validate(key)) {
			JsonObject result = new JsonObject();
			result.addProperty("status", "error_unauthorized");
			return result.toString();
		}
		int userId = itemObject.get("user_id").getAsInt();
		if (!itemObject.has("product_id")) {
			// delete all
			returnValue = cart.removeItemFromCart(userId);
			return "{status:" + returnValue + "}";
		}
		int productId = itemObject.get("product_id").getAsInt();
		// delete one by one
		returnValue = cart.removeItemFromCart(userId, productId);

		return "{status:" + returnValue + "}";
	}

	@GET
	@Path("/cartItems")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	// read cart
	public String readItem(@DefaultValue("0") @QueryParam("user_id") Integer user,
			@DefaultValue("") @QueryParam("key") String key) {
		// request validation
		if (!RequestValidator.validate(key)) {
			JsonObject result = new JsonObject();
			result.addProperty("status", "error_unauthorized");
			return result.toString();
		}

		String returnValue = "";
		returnValue = cart.returnCartDetails(user);
		return returnValue;
	}
}
