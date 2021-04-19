package com.miniService.repo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.miniService.model.ResearchProject;

public class researchProjectRepository 
{
	
	Connection con = null;
	
	List<ResearchProject> researchProjects;
	
	public researchProjectRepository(){
		
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
		
		researchProjects = new ArrayList<>();
		
	}
	
	
	public List<ResearchProject> getAllResearchProjects()
	{
		List<ResearchProject> researchProjects = new ArrayList<>();
		String readSql = "SELECT * FROM researchprojects_tb";
		
		try {
			
			PreparedStatement statement = con.prepareStatement(readSql);
			ResultSet results = statement.executeQuery(readSql);
			while(results.next())
			{
				ResearchProject rp1 = new ResearchProject();
				rp1.setResearchID(results.getInt(1));
				rp1.setResearchName(results.getString(2));
				rp1.setResearcherId(results.getInt(3));
				rp1.setResearcherName(results.getString(4));
				rp1.setResearchCategory(results.getString(5));
				rp1.setResearchDescription(results.getString(6));
				rp1.setResearchCost(results.getFloat(7));
				rp1.setResearchDuration(results.getString(8));
				rp1.setStartDate(results.getInt(9));
				
				researchProjects.add(rp1);
			}
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		return researchProjects;
	}
	
	
	public ResearchProject getResearchProject(int researchID)
	{
		String readSql = "SELECT * FROM researchprojects_tb WHERE researchID =" +researchID;
		
		ResearchProject rp1 = new ResearchProject();
		
		try {
			
			PreparedStatement statement = con.prepareStatement(readSql);
			ResultSet results = statement.executeQuery(readSql);
			if(results.next())
			{
				
				rp1.setResearchID(results.getInt(1));
				rp1.setResearchName(results.getString(2));
				rp1.setResearcherId(results.getInt(3));
				rp1.setResearcherName(results.getString(4));
				rp1.setResearchCategory(results.getString(5));
				rp1.setResearchDescription(results.getString(6));
				rp1.setResearchCost(results.getFloat(7));
				rp1.setResearchDuration(results.getString(8));
				rp1.setStartDate(results.getInt(9));
				
				researchProjects.add(rp1);
			}
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		return rp1;
	}
	
	
	public List<ResearchProject> searchResearchProjects(String researchName)
	{
		List<ResearchProject> researchProjects = new ArrayList<>();
		String readSql = "SELECT * FROM researchprojects_tb WHERE researchName LIKE '%"+researchName+"%'";
		
		try {
			
			PreparedStatement statement = con.prepareStatement(readSql);
			ResultSet results = statement.executeQuery(readSql);
			while(results.next())
			{
				ResearchProject rp1 = new ResearchProject();
				rp1.setResearchID(results.getInt(1));
				rp1.setResearchName(results.getString(2));
				rp1.setResearcherId(results.getInt(3));
				rp1.setResearcherName(results.getString(4));
				rp1.setResearchCategory(results.getString(5));
				rp1.setResearchDescription(results.getString(6));
				rp1.setResearchCost(results.getFloat(7));
				rp1.setResearchDuration(results.getString(8));
				rp1.setStartDate(results.getInt(9));
				
				researchProjects.add(rp1);
			}
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		return researchProjects;
	}
	
	
	public List<ResearchProject> searchResearchProjectsByCategory(String researchCategory)
	{
		List<ResearchProject> researchProjects = new ArrayList<>();
		String readSql = "SELECT * FROM researchprojects_tb WHERE researchCategory ="+researchCategory;
		
		try {
			
			PreparedStatement statement = con.prepareStatement(readSql);
			ResultSet results = statement.executeQuery(readSql);
			while(results.next())
			{
				ResearchProject rp1 = new ResearchProject();
				rp1.setResearchID(results.getInt(1));
				rp1.setResearchName(results.getString(2));
				rp1.setResearcherId(results.getInt(3));
				rp1.setResearcherName(results.getString(4));
				rp1.setResearchCategory(results.getString(5));
				rp1.setResearchDescription(results.getString(6));
				rp1.setResearchCost(results.getFloat(7));
				rp1.setResearchDuration(results.getString(8));
				rp1.setStartDate(results.getInt(9));
				
				researchProjects.add(rp1);
			}
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
		return researchProjects;
	}
	
	
	
	public ResearchProject insertResearchProject(ResearchProject rp1)
	{
		String insertSql = "INSERT INTO researchprojects_tb ('researchID', 'researchName', 'researcherId', 'researcherName', 'researchCategory', 'researchDescription', 'researchCost', 'researchDuration', 'startDate')" 
				+ " VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement statement = con.prepareStatement(insertSql);
			statement.setInt(1, rp1.researchID);
			statement.setString(2, rp1.researchName);
			statement.setInt(3, rp1.researcherId);
			statement.setString(4, rp1.researcherName);
			statement.setString(5, rp1.researchCategory);
			statement.setString(6, rp1.researchDescription);
			statement.setFloat(7, rp1.researchCost);
			statement.setString(8, rp1.researchDuration);
			statement.setInt(9, rp1.startDate);
			
			statement.executeUpdate();
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		return rp1;
		
	}
	
	
	public ResearchProject updateResearchProject(ResearchProject rp1)
	{
		String insertSql = "UPDATE researchprojects_tb SET 'researchName' = ?, 'researcherId' = ?, 'researcherName' = ?, 'researchCategory' = ?, 'researchDescription' = ?, 'researchCost' = ?, 'researchDuration' = ?, 'startDate' = ? WHERE 'researchID' = ?";
		try {
			PreparedStatement statement = con.prepareStatement(insertSql);
			statement.setString(1, rp1.researchName);
			statement.setInt(2, rp1.researcherId);
			statement.setString(3, rp1.researcherName);
			statement.setString(4, rp1.researchCategory);
			statement.setString(5, rp1.researchDescription);
			statement.setFloat(6, rp1.researchCost);
			statement.setString(7, rp1.researchDuration);
			statement.setInt(8, rp1.startDate);
			statement.setInt(9, rp1.researchID);
			
			statement.executeUpdate();
			
		}catch(Exception e)
		{
			System.out.print(e);
		}
		return rp1;
		
	}
	
	public void deleteResearchProject(int researchID)
	{
		
		String deleteSql = "DELETE FROM researchprojects_tb WHERE 'researchID' = ?";
		try 
		{
			PreparedStatement statement = con.prepareStatement(deleteSql);
			statement.setInt(1, researchID);
			statement.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	
	

}
