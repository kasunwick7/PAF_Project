package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.ProductManagementModel;

@Path("/Products")
public class ProductManagementService {
	
	ProductManagementModel products = new ProductManagementModel();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	//insert products
	public String addProducts(String data) {
		
		JsonObject result = new JsonObject();
		result.addProperty("status", "error");
		
		//int products_id = 0;
		int research_id = 0;
		String name = "";
		String description = "";
		int stock_quantity = 0;
		double price = 0.0;
		String added_date = "";
		

		try {
			JsonObject productObject = new JsonParser().parse(data).getAsJsonObject();
			// request validation
			if (!RequestValidator.validate(productObject.get("key").getAsString())) {
				return result.toString();
			}
			userId = productObject.get("user_id").getAsInt();

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

}
