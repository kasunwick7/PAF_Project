package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	

	public String addProducts(String data) {
		
		JsonObject result = new JsonObject();
		result.addProperty("status", "error");
		
	
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
		

			if (productObject.has("product")) {

				for (JsonElement singleItem : productObject.get("product").getAsJsonArray()) {
					JsonObject productObj = singleItem.getAsJsonObject();
					research_id = productObj.get("research_id").getAsInt();
					name = productObj.get("name").getAsString();
					description = productObj.get("description").getAsString();
					stock_quantity = productObj.get("stock_quantity").getAsInt();
					price = productObj.get("price").getAsDouble();
					added_date = productObj.get("added_date").getAsString();
					products.addProducts(research_id, name, description,stock_quantity,price,added_date);

				}
				result.addProperty("status", "done_all");

			}
			else{
				research_id = productObject.get("research_id").getAsInt();
				name = productObject.get("name").getAsString();
				description = productObject.get("description").getAsString();
				stock_quantity = productObject.get("stock_quantity").getAsInt();
				price = productObject.get("price").getAsDouble();
				added_date = productObject.get("added_date").getAsString();
				if (products.addProducts(research_id, name, description,stock_quantity,price,added_date)) {
					result.addProperty("status", "done");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.addProperty("status", "error");
		}

		return result.toString();
	}
	
	@DELETE 
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String removeProduct(String data) {
		
		String returnValue = "failed";
		JsonObject productObject = new JsonParser().parse(data).getAsJsonObject();
		String key = productObject.get("key").getAsString();
		
		// request validation
		if (!RequestValidator.validate(key)) {
			JsonObject result = new JsonObject();
			result.addProperty("status", "error_unauthorized");
			return result.toString();
		}
		
		int products_id = productObject.get("products_id").getAsInt();
		
		
		returnValue = products.removeProducts(products_id);

		return "{status:" + returnValue + "}";
	}
	
	
	@GET
	@Path("/")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	
	public String readProducts(@DefaultValue("0") @QueryParam("products_id") Integer products_id,
			@DefaultValue("") @QueryParam("key") String key) {
		
		// request validation
		if (!RequestValidator.validate(key)) {
			JsonObject result = new JsonObject();
			result.addProperty("status", "error_unauthorized");
			return result.toString();
		}
		
		String returnValue = "";
		if (products_id == 0) {
			
			returnValue = products.readProducts();
			
		}
		else {
			
			returnValue = products.readProducts(products_id);
			
		}
		
		
		
		return returnValue;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String updateProduct(String data)
	{
		//Convert the input string to a JSON object
		 JsonObject productObject = new JsonParser().parse(data).getAsJsonObject();
	
		//Read the values from the JSON object
		 int products_id = productObject.get("products_id").getAsInt();
		 int research_id = productObject.get("research_id").getAsInt();
		 String name = productObject.get("name").getAsString();
		 String description = productObject.get("description").getAsString();
		 int stock_quantity = productObject.get("stock_quantity").getAsInt();
		 double price = productObject.get("price").getAsDouble();
		 String added_date = productObject.get("added_date").getAsString();
		 
		 String output = products.updateProduct(products_id, research_id, name, description, stock_quantity,price,added_date);
		 
		return output;
	}
	
	

}
