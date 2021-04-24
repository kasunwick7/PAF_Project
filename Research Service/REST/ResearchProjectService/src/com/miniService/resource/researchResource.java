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

	ResearchProject researchObj = new ResearchProject();
	
	@GET
	@Path("/getResearchProjects")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String getAllresearchProjects()
	{
		
		return researchObj.getAllResearchProjects();
			
	}
	
	@GET
	@Path("/getResearchProject")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String getresearchProjects(@PathParam("researchID") int researchID)
	{
		
		return researchObj.getResearchProject(researchID);
			
	}
	
	@GET
	@Path("/searchResearchProjects")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String searchResearch(@PathParam("researchName") String researchName)
	{
		
		return researchObj.searchResearchProjects(researchName);
			
	}
	
	@GET
	@Path("/searchResearchProjects")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String searchResearchByCategory(@PathParam("researchCategory") String researchCategory)
	{
		
		return researchObj.searchResearchProjectsByCategory(researchCategory);
			
	}
	
	@POST
	@Path("/insertresearchProject")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String addResearchProject(int researchID, String researchName, int researcherId, String researcherName, String researchCategory, String researchDescription, float researchCost, int researchDuration, String startDate)
	{
		return researchObj.insertResearchProject(researchID, researchName, researcherId, researcherName, researchCategory, researchDescription, researchCost, researchDuration, startDate);
		
	}
	
	@PUT
	@Path("/updateresearchProject")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String updateResearch(String researchData)
	{
		
		JsonObject researhObject = new JsonParser().parse(researchData).getAsJsonObject();
		
		int researchID = researhObject.get("researchID").getAsInt();
		String researchName = researhObject.get("researchName").getAsString();
		int researcherId = researhObject.get("researcherId").getAsInt();
		String researcherName = researhObject.get("researcherName").getAsString();
		String researchCategory = researhObject.get("researchCategory").getAsString();
		String researchDescription = researhObject.get("researchDescription").getAsString();
		float researchCost = researhObject.get("researchCost").getAsFloat();
		int researchDuration  = researhObject.get("researchDuration").getAsInt();
		String startDate = researhObject.get("startDate").getAsString();
		
		return researchObj.updateResearchProject(researchID, researchName, researcherId, researcherName, researchCategory, researchDescription, researchCost, researchDuration, startDate);
		
	}
	
	@DELETE
	@Path("/deleteresearchProject")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String deleteResearch(String researchData)
	{
		
		
		JsonObject researhObject = new JsonParser().parse(researchData).getAsJsonObject();
		
		int researchID = researhObject.get("researchID").getAsInt();
		
		return researchObj.deleteResearchProject(researchID);
		
	}
	
}

