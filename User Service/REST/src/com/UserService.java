package com;
import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/User")
public class UserService
{
 User user = new User();
 RequestValidator requestValidator =  new RequestValidator();
@GET
@Path("/")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
// read cart
public String ReadUsers(@DefaultValue("0") @QueryParam("user_id") Integer userID,
		@DefaultValue("") @QueryParam("key") String key) {
	// request validation
	if (!requestValidator.validate(key)) {
		JsonObject result = new JsonObject();
		result.addProperty("status", "error_unauthorized");
		return result.toString();
	}

	String returnValue = "";
	returnValue = user.readUsers(userID);
	return returnValue;
}

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String insertUser(String data) {
	JsonObject result = new JsonObject();
	result.addProperty("status", "error");
	int user_level = 0;
	String email ;
	String fname;
	String lname;
	String dob;
	String address;
	int tp_number = 0;

	try {
		JsonObject userObject = new JsonParser().parse(data).getAsJsonObject();
		// request validation
		if (!requestValidator.validate(userObject.get("key").getAsString())) {
			return result.toString();
		}
		

		if (userObject.has("MultipleUsers")) {

			for (JsonElement singleUser : userObject.get("MultipleUsers").getAsJsonArray()) {
				JsonObject userObj = singleUser.getAsJsonObject();
				user_level = userObj.get("user_level").getAsInt();
				email = userObj.get("email").getAsString();
				fname = userObj.get("fname").getAsString();
				lname = userObj.get("lname").getAsString();
				dob = userObj.get("dob").getAsString();
				address = userObj.get("address").getAsString();
				tp_number = userObj.get("tp_number").getAsInt();
				user.insertUser(user_level, email, fname, lname, dob, address, tp_number);

			}
			result.addProperty("status", "done_all");

		} else {
			user_level = userObject.get("user_level").getAsInt();
			email = userObject.get("email").getAsString();
			fname = userObject.get("fname").getAsString();
			lname = userObject.get("lname").getAsString();
			dob = userObject.get("dob").getAsString();
			address = userObject.get("address").getAsString();
			tp_number = userObject.get("tp_number").getAsInt();
			if(user.insertUser(user_level, email, fname, lname, dob, address, tp_number)) {
				result.addProperty("status", "done");
			}
		}

	} catch (Exception e) {
		e.printStackTrace();
		result.addProperty("status", "error");
	}

	return result.toString();
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String updateUser(String userData)
{
//Convert the input string to a JSON object
 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
 String key = userObject.get("key").getAsString();
//request validation
	if (!requestValidator.validate(key)) {
		JsonObject result = new JsonObject();
		result.addProperty("status", "error_unauthorized");
		return result.toString();
	}
//Read the values from the JSON object
 int user_id = userObject.get("user_id").getAsInt();
 int user_level = userObject.get("user_level").getAsInt();
 String email = userObject.get("email").getAsString();
 String fname = userObject.get("fname").getAsString();
 String lname = userObject.get("lname").getAsString();
 String dob = userObject.get("dob").getAsString();
 String address = userObject.get("address").getAsString();
 int tp_number = userObject.get("tp_number").getAsInt();
 String output = user.updateUser(user_id,user_level, email, fname, lname, dob, address, tp_number);
 return "{status:" + output + "}";
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String deleteUser(String data) {
	String returnValue = "failed";
	JsonObject userObject = new JsonParser().parse(data).getAsJsonObject();
	String key = userObject.get("key").getAsString();
	// request validation
	if (!requestValidator.validate(key)) {
		JsonObject result = new JsonObject();
		result.addProperty("status", "error_unauthorized");
		return result.toString();
	}
	int userId = userObject.get("user_id").getAsInt();
		returnValue = user.deleteUser(userId);
		return "{status:" + returnValue + "}";
}

@GET
@Path("userLevel/{user_id}")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
public String getUserLevel(@DefaultValue("0") @QueryParam("user_id") Integer user,
		@DefaultValue("") @QueryParam("key") String key) {
	// request validation
	if (!requestValidator.validate(key)) {
		JsonObject result = new JsonObject();
		result.addProperty("status", "error_unauthorized");
		return result.toString();
	}

	String returnValue = "";
	//returnValue = user.returnUserLevel(user);
	return returnValue;
}

}


