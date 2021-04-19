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
 User userObj = new User();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readItems()
 {
	return userObj.readUsers();
 }

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertUser(
 @FormParam("user_level") int user_level,
 @FormParam("email") String email,
 @FormParam("fname") String fname,
 @FormParam("lname") String lname,
 @FormParam("dob") String dob,
 @FormParam("address") String address,
 @FormParam("tp_number") int tp_number)
{
 String output = userObj.insertUser(user_level, email, fname, lname, dob, address, tp_number);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateUser(String userData)
{
//Convert the input string to a JSON object
 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
//Read the values from the JSON object
 int user_id = userObject.get("user_id").getAsInt();
 int user_level = userObject.get("user_level").getAsInt();
 String email = userObject.get("email").getAsString();
 String fname = userObject.get("fname").getAsString();
 String lname = userObject.get("lname").getAsString();
 String dob = userObject.get("dob").getAsString();
 String address = userObject.get("address").getAsString();
 int tp_number = userObject.get("tp_number").getAsInt();
 String output = userObj.updateUser(user_id,user_level, email, fname, lname, dob, address, tp_number);
return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteUser(String itemData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String userid = doc.select("user_id").text();
 String output = userObj.deleteUser(userid);
return output;
}

@GET
@Path("userLevel/{user_id}")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
public String getUserLevel(@DefaultValue("0") @QueryParam("user_id") Integer user,
		@DefaultValue("") @QueryParam("key") String key) {
	// request validation
	if (!RequestValidator.validate(key)) {
		JsonObject result = new JsonObject();
		result.addProperty("status", "error_unauthorized");
		return result.toString();
	}

	String returnValue = "";
	returnValue = userObj.returnUserLevel(user);
	return returnValue;
}

}


