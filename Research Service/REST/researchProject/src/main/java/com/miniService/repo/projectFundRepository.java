package com.miniService.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.miniService.model.ProjectFund;
import com.miniService.model.ResearchProject;

public class projectFundRepository {
	
	Connection con = null;
	
	List<ProjectFund> projectFunds;
	
	public projectFundRepository(){
		
		String url = "jdbc.mysql://127.0.0.1:3306/research_db";
		String username = "root";
		String password = "lekamge1998";
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		projectFunds = new ArrayList<>();
		
	}
	
	public List<ProjectFund> getAllFunds()
	{
		List<ProjectFund> projectFunds = new ArrayList<>();
		String readSql = "SELECT * FROM projectfund_tb";
		
		try {
			
			PreparedStatement statement = con.prepareStatement(readSql);
			ResultSet results = statement.executeQuery(readSql);
			while(results.next())
			{
				ProjectFund pf1 = new ProjectFund();
				pf1.setFundPaymentID(results.getInt(1));
				pf1.setResearchID(results.getInt(2));
				pf1.setFundingBodyID(results.getInt(3));
				pf1.setFundingAmount(results.getFloat(4));
				
				projectFunds.add(pf1);
			}
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		return projectFunds;
	}
	
	
	public List<ProjectFund> getProjectFunds(int researchID)
	{
		List<ProjectFund> projectFunds = new ArrayList<>();
		String readSql = "SELECT * FROM projectfund_tb WHERE researchID =" +researchID;
		
		try {
			PreparedStatement statement = con.prepareStatement(readSql);
			ResultSet results = statement.executeQuery(readSql);
			while(results.next())
			{
				ProjectFund pf1 = new ProjectFund();
				pf1.setFundPaymentID(results.getInt(1));
				pf1.setResearchID(results.getInt(2));
				pf1.setFundingBodyID(results.getInt(3));
				pf1.setFundingAmount(results.getFloat(4));
				
				projectFunds.add(pf1);
			}
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		return projectFunds;
	}
	
	public ProjectFund getProjectFund(int fundPaymentID)
	{
		String readSql = "SELECT * FROM projectfund_tb WHERE fundPaymentID =" +fundPaymentID;
		
		ProjectFund pf1 = new ProjectFund();
		
		try {
			
			PreparedStatement statement = con.prepareStatement(readSql);
			ResultSet results = statement.executeQuery(readSql);
			if(results.next())
			{
				
				pf1.setFundPaymentID(results.getInt(1));
				pf1.setResearchID(results.getInt(2));
				pf1.setFundingBodyID(results.getInt(3));
				pf1.setFundingAmount(results.getFloat(4));
				
				
				projectFunds.add(pf1);
			}
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		return pf1;
	}
	
	public ProjectFund insertFund(ProjectFund pf1)
	{
		String insertSql = "INSERT INTO projectfund_tb ('fundPaymentID', 'researchID', 'fundingBodyID', 'fundingAmount')" 
				+ " VALUES (?,?,?,?)";
		try {
			PreparedStatement statement = con.prepareStatement(insertSql);
			statement.setInt(1, pf1.fundPaymentID);
			statement.setInt(2, pf1.researchID);
			statement.setInt(3, pf1.fundingBodyID);
			statement.setFloat(4, pf1.fundingAmount);
			
			statement.executeUpdate();
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		return pf1;
		
	}
	
	
	public ProjectFund updateFund(ProjectFund pf1)
	{
		String insertSql = "UPDATE projectfund_tb SET 'researchID' = ?, 'fundingBodyID' = ?, 'fundingAmount' = ? WHERE 'fundPaymentID' = ?";
		try {
			PreparedStatement statement = con.prepareStatement(insertSql);
			statement.setInt(1, pf1.researchID);
			statement.setInt(2, pf1.fundingBodyID);
			statement.setFloat(3, pf1.fundingAmount);
			statement.setInt(4, pf1.fundPaymentID);
			
			statement.executeUpdate();
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		return pf1;
		
	}
	
	public void deleteFund(int fundPaymentID)
	{
		
		String deleteSql = "DELETE FROM projectfund_tb WHERE 'fundPaymentID' = ?";
		try 
		{
			PreparedStatement statement = con.prepareStatement(deleteSql);
			statement.setInt(1, fundPaymentID);
			statement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	
	
	
	

}
