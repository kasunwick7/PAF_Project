package com.miniService.model;

public class ResearchProject 
{
	
	public int researchID;
	public String researchName;
	public int researcherId;
	public String researcherName;
	public String researchCategory;
	public String researchDescription;
	public float researchCost;
	public String researchDuration;
	public int startDate;
	
	
	
	public ResearchProject() {
	}

	public ResearchProject(int researchID, String researchName, int researcherId, String researcherName,
			String researchCategory, String researchDescription, float researchCost, String researchDuration,
			int startDate) {
		super();
		this.researchID = researchID;
		this.researchName = researchName;
		this.researcherId = researcherId;
		this.researcherName = researcherName;
		this.researchCategory = researchCategory;
		this.researchDescription = researchDescription;
		this.researchCost = researchCost;
		this.researchDuration = researchDuration;
		this.startDate = startDate;
	}
	
	
	
	public int getResearcherId() {
		return researcherId;
	}
	public void setResearcherId(int researcherId) {
		this.researcherId = researcherId;
	}
	public int getResearchID() {
		return researchID;
	}
	public void setResearchID(int researchID) {
		this.researchID = researchID;
	}
	public String getResearchName() {
		return researchName;
	}
	public void setResearchName(String researchName) {
		this.researchName = researchName;
	}
	public String getResearcherName() {
		return researcherName;
	}
	public void setResearcherName(String researcherName) {
		this.researcherName = researcherName;
	}
	public String getResearchCategory() {
		return researchCategory;
	}
	public void setResearchCategory(String researchCategory) {
		this.researchCategory = researchCategory;
	}
	public String getResearchDescription() {
		return researchDescription;
	}
	public void setResearchDescription(String researchDescription) {
		this.researchDescription = researchDescription;
	}
	public float getResearchCost() {
		return researchCost;
	}
	public void setResearchCost(float researchCost) {
		this.researchCost = researchCost;
	}
	public String getResearchDuration() {
		return researchDuration;
	}
	public void setResearchDuration(String researchDuration) {
		this.researchDuration = researchDuration;
	}
	public int getStartDate() {
		return startDate;
	}
	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	
	
}
