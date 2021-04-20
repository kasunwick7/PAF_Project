package com.miniService.resource;

import java.util.ArrayList;
import java.util.List;

import com.miniService.model.ResearchProject;
import com.miniService.repo.researchProjectRepository;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/research")
public class researchResource 
{
	
	researchProjectRepository rpRepo = new researchProjectRepository();
	
	@GET
	@Path("/getResearchProjects")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<ResearchProject> getAllresearchProjects()
	{
		
		return rpRepo.getAllResearchProjects();
			
	}
	
	@GET
	@Path("/getResearchProject/{researchID}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ResearchProject getresearchProjects(@PathParam("researchID") int researchID)
	{
		
		return rpRepo.getResearchProject(researchID);
			
	}
	
	@GET
	@Path("/searchResearchProjects/{researchName}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<ResearchProject> searchResearch(@PathParam("researchName") String researchName)
	{
		
		return rpRepo.searchResearchProjects(researchName);
			
	}
	
	@GET
	@Path("/searchResearchProjects/{researchCategory}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<ResearchProject> searchResearchByCategory(@PathParam("researchCategory") String researchCategory)
	{
		
		return rpRepo.searchResearchProjectsByCategory(researchCategory);
			
	}
	
	@POST
	@Path("/insertresearchProject")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ResearchProject addResearchProject(ResearchProject rp1)
	{
		return rpRepo.insertResearchProject(rp1);
		
	}
	
	@PUT
	@Path("/updateresearchProject")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ResearchProject updateResearch(ResearchProject rp1)
	{
		if(rpRepo.getResearchProject(rp1.getResearcherId()).getResearcherId() == 0)
		{
			rpRepo.insertResearchProject(rp1);
		}
		else
		{
			rpRepo.updateResearchProject(rp1);
		}
		
		return rp1;
		
	}
	
	@DELETE
	@Path("/deleteresearchProject/{researchID}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ResearchProject deleteResearch(@PathParam("researchID") int researchID)
	{
		
		ResearchProject rp1 = rpRepo.getResearchProject(researchID);
		
		if(rp1.getResearcherId()!=0)
		{
			rpRepo.deleteResearchProject(researchID);
		}
		else
		{
			System.out.println("Record Not Found!");
		}
		
		return rp1;
		
	}
	
	
	
	
	
	

}
