package com.miniService.resource;

import com.miniService.model.ResearchProject;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 



@Path("/researches")
public class researchResource 
{
	

	//ResearchProject researchObj = new ResearchProject();
	
	
	
	@POST
	@Path("/testapi")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String test(String data) {
		JsonObject result = new JsonObject();
		try {
			result = new JsonParser().parse(data).getAsJsonObject();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result.toString();
	}
	
//	
//	@GET
//	@Path("/getResearchProjects")
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public String getAllresearchProjects()
//	{
//		
//		return researchObj.getAllResearchProjects();
//			
//	}
//	
//	@GET
//	@Path("/getResearchProject")
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public String getresearchProjects(@PathParam("researchID") int researchID)
//	{
//		
//		return researchObj.getResearchProject(researchID);
//			
//	}
//	
//	@GET
//	@Path("/searchResearchProjects")
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public String searchResearch(@PathParam("researchName") String researchName)
//	{
//		
//		return researchObj.searchResearchProjects(researchName);
//			
//	}
//	
//	@GET
//	@Path("/searchResearchProjects")
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public String searchResearchByCategory(@PathParam("researchCategory") String researchCategory)
//	{
//		
//		return researchObj.searchResearchProjectsByCategory(researchCategory);
//			
//	}
//	
//	@POST
//	@Path("/insertresearchProject")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public String addResearchProject(String researchData)
//	{
//		JsonObject result = new JsonObject();
//		result.addProperty("ststus", "error");
//		int researchID = 0;
//		String researchName = "";
//		int researcherId = 0; 
//		String researcherName = "";
//		String researchCategory = "";
//		String researchDescription = "";
//		float researchCost = 0;
//		int researchDuration = 0;
//		String startDate = "";
//		
//		try {
//			JsonObject research = new JsonParser().parse(researchData).getAsJsonObject();
//			// request validation
//			//if (!RequestValidator.validate(itemObject.get("key").getAsString())) {
//				//return result.toString();
//			//}
//			researchID = research.get("researchID").getAsInt();
//
//			if (research.has("researches")) {
//
//				for (JsonElement singleItem : research.get("researches").getAsJsonArray()) 
//				{
//					JsonObject researchObject = singleItem.getAsJsonObject();
//					
//					researchID = researchObject.get("researchID").getAsInt();
//					researchName = researchObject.get("researchName").getAsString();
//					researcherId = researchObject.get("researcherId").getAsInt();
//					researcherName = researchObject.get("researcherName").getAsString();
//					researchCategory = researchObject.get("researchCategory").getAsString();
//					researchDescription = researchObject.get("researchDescription").getAsString();
//					researchCost = researchObject.get("researchCost").getAsFloat();
//					researchDuration = researchObject.get("researchDuration").getAsInt();
//					startDate = researchObject.get("researchName").getAsString();
//					
//					researchObj.insertResearchProject(researchID, researchName, researcherId, researcherName, researchCategory, researchDescription, researchCost, researchDuration, startDate);
//
//				}
//				
//				result.addProperty("status", "done all");
//				
//			} else if (research.has("researchID")) 
//			{
//				researchName = research.get("researchName").getAsString();
//				researcherId = research.get("researcherId").getAsInt();
//				researcherName = research.get("researcherName").getAsString();
//				researchCategory = research.get("researchCategory").getAsString();
//				researchDescription = research.get("researchDescription").getAsString();
//				researchCost = research.get("researchCost").getAsFloat();
//				researchDuration = research.get("researchDuration").getAsInt();
//				startDate = research.get("researchName").getAsString();
//				
//				if (researchObj.insertResearchProject(researchID, researchName, researcherId, researcherName, researchCategory, researchDescription, researchCost, researchDuration, startDate)) 
//				{
//					result.addProperty("status", "done");
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.addProperty("status", "error");
//		}
//
//		return result.toString();
//		
//	}
//	
//	@PUT
//	@Path("/updateresearchProject")
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public String updateResearch(String researchData)
//	{
//		
//		JsonObject researhObject = new JsonParser().parse(researchData).getAsJsonObject();
//		
//		int researchID = researhObject.get("researchID").getAsInt();
//		String researchName = researhObject.get("researchName").getAsString();
//		int researcherId = researhObject.get("researcherId").getAsInt();
//		String researcherName = researhObject.get("researcherName").getAsString();
//		String researchCategory = researhObject.get("researchCategory").getAsString();
//		String researchDescription = researhObject.get("researchDescription").getAsString();
//		float researchCost = researhObject.get("researchCost").getAsFloat();
//		int researchDuration  = researhObject.get("researchDuration").getAsInt();
//		String startDate = researhObject.get("startDate").getAsString();
//		
//		return researchObj.updateResearchProject(researchID, researchName, researcherId, researcherName, researchCategory, researchDescription, researchCost, researchDuration, startDate);
//		
//	}
//	
//	@DELETE
//	@Path("/deleteresearchProject")
//	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//	public String deleteResearch(String researchData)
//	{
//		
//		
//		JsonObject researhObject = new JsonParser().parse(researchData).getAsJsonObject();
//		
//		int researchID = researhObject.get("researchID").getAsInt();
//		
//		return researchObj.deleteResearchProject(researchID);
//		
//	}
	
}


