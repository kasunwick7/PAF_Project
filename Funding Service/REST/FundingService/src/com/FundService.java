package com;
import model.Funds;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Funds")
public class FundService {
	
	Funds fundsObj = new Funds();

	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON) 
	public String readItems() 
	{ 
	 return fundsObj.readItems(); 
	}
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(
	@FormParam("funderName") String funderName, 
	@FormParam("amount") String amount,
	@FormParam("fundingDate") String fundingDate,
	@FormParam("fundStatus") String fundStatus) 
	{ 
	 String output = fundsObj.insertItem(funderName,amount,fundingDate,fundStatus); 
	 return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String fundData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(fundData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String fundID = itemObject.get("fundID").getAsString(); 
	 String funderName = itemObject.get("funderName").getAsString(); 
	 String amount = itemObject.get("amount").getAsString(); 
	 String fundingDate = itemObject.get("fundingDate").getAsString(); 
	 String fundStatus = itemObject.get("fundStatus").getAsString(); 
	 String output = fundsObj.updateItem(fundID, funderName, amount, fundingDate, fundStatus); 
	 return output; 
	}

	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String fundData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(fundData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String fundID = doc.select("fundID").text(); 
	 String output = fundsObj.deleteItem(fundID); 
	return output; 
	}

}
