package com.miniService.resource;

import java.util.List;

import com.miniService.model.ProjectFund;
import com.miniService.model.ResearchProject;
import com.miniService.repo.projectFundRepository;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("fund")
public class projectFundResource {

	projectFundRepository fundRepo = new projectFundRepository();
	
	@GET
	@Path("getFunds")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<ProjectFund> getAllProjectFunds()
	{
		
		return fundRepo.getAllFunds();
			
	}
	
	@GET
	@Path("getFunds/{researchID}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<ProjectFund> getresearchProjectFunds(@PathParam("researchID") int researchID)
	{
		
		return fundRepo.getProjectFunds(researchID);
			
	}
	
	@GET
	@Path("getResearchProject/{fundPaymentID}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ProjectFund getProjectFund(@PathParam("fundPaymentID") int fundPaymentID)
	{
		
		return fundRepo.getProjectFund(fundPaymentID);
			
	}
	
	@POST
	@Path("insertFund")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ProjectFund addFund(ProjectFund pf1)
	{
		return fundRepo.insertFund(pf1);
		
	}
	
	@PUT
	@Path("updateFund")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ProjectFund updateProjectFund(ProjectFund pf1)
	{
		if(fundRepo.getProjectFund(pf1.getFundPaymentID()).getFundPaymentID() == 0)
		{
			fundRepo.insertFund(pf1);
		}
		else
		{
			fundRepo.updateFund(pf1);
		}
		
		return pf1;
		
	}
	
	@DELETE
	@Path("deleteFund/{fundPaymentID}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public ProjectFund deleteProjectFund(@PathParam("fundPaymentID") int fundPaymentID)
	{
		
		ProjectFund pf1 = fundRepo.getProjectFund(fundPaymentID);
		
		if(pf1.getFundPaymentID()!=0)
		{
			fundRepo.deleteFund(fundPaymentID);
		}
		else
		{
			System.out.println("Record Not Found!");
		}
		
		return pf1;
		
	}
}
